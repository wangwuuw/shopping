/**
 * 
 */
package com.beidoudxt.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beidoudxt.common.OrderStatus;
import com.beidoudxt.common.SnowFlake;
import com.beidoudxt.dao.OrderDao;
import com.beidoudxt.entity.presistent.Order;
import com.beidoudxt.entity.presistent.OrderProduct;
import com.beidoudxt.entity.request.OrderRequest;
import com.beidoudxt.entity.request.UpdateStockRequest;
import com.beidoudxt.entity.response.OrderResponse;
import com.beidoudxt.entity.response.ProductResponse;
import com.beidoudxt.feign.ProductFeign;
import com.beidoudxt.util.ResponseWrapper;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月22日下午5:11:18
 */
@Slf4j
@Service
public class OrderService {
   @Autowired
	private OrderDao orderDao;
	@Autowired
	ProductFeign ProductFeign;
    private SnowFlake snowFlake = new SnowFlake(1, 1);
    @Autowired
	StringRedisTemplate redisTemplate;
    @SuppressWarnings("rawtypes")
    @Transactional
    public boolean createOrder(OrderRequest request) {
    	log.info("开始创建订单");
    	Order order = new Order();
    	order.setStatus(OrderStatus.ORDERED);
    	BeanUtils.copyProperties(request, order);
    	order.setId(snowFlake.nextId());
    	List<OrderProduct> products = request.getProducts();
    	/**
    	 * 获取商品信息
    	 */
    	for (OrderProduct orderProduct : products) {
    		long productId = orderProduct.getProductId();
    		//调商品服务获取商品的价格和广告语
    		ResponseWrapper<ProductResponse> productResponse = ProductFeign.queryProduct(productId);
    		ProductResponse data = productResponse.getData();
    		orderProduct.setOrderId(order.getId());
    		orderProduct.setPrice(data.getPrice());
    		orderProduct.setProductAd(data.getAd());
    		//调商品服务修改库存
    		UpdateStockRequest updateStockRequest = new UpdateStockRequest();
    		updateStockRequest.setProductId(productId);
    		updateStockRequest.setQuantity(orderProduct.getQuantity());
    		
			ResponseWrapper updateStock = ProductFeign.updateStock(updateStockRequest);
            if (!"success".equals(updateStock.getStatus())) {
				return false;
			}
    		
		}
    	List<OrderProduct> list = request.getProducts();
    	orderDao.insertBatchOrderProduct(list);
    	int createOrder = orderDao.createOrder(order);
    	if (createOrder>0) {
    		redisTemplate.opsForValue().set(String.valueOf(order.getId()), "ok",600,TimeUnit.SECONDS);
    		return true;
		}
    	return false;
	}   
    public boolean updateOrderStatu(long orderId){
    	int updateOrderStatusById = orderDao.updateOrderStatusById(orderId,OrderStatus.PAYED);
		return updateOrderStatusById>0;
    }
    public OrderResponse queryOrder(long id){
    	OrderResponse orderResponse = new OrderResponse();
    	Order order = orderDao.selectOrderById(id);
    	BeanUtils.copyProperties(order, orderResponse);
    	List<OrderProduct> products = orderDao.selectOrderProductByOrderId(id);
    	orderResponse.setProducts(products);
		return orderResponse;
    }
    public List<Order> queryOrdersByUserId(long userId){
    	List<Order> order = orderDao.selectOrdersByUserId(userId);
		return order;
    }
    
}
