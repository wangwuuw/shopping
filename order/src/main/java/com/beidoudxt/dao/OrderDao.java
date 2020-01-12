/**
 * 
 */
package com.beidoudxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.beidoudxt.common.OrderStatus;
import com.beidoudxt.entity.presistent.Order;
import com.beidoudxt.entity.presistent.OrderProduct;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月22日下午3:41:09
 */
public interface OrderDao {
	@Insert("insert into `order`(id,createDt,updateDt,status,userId) values(#{id},#{createDt},#{updateDt},#{status},#{userId})")
  int createOrder(Order order);
	@Select("select * from `order` where id = #{id}")
	Order selectOrderById(long id);
	@Update("update `order` set status = #{status} where id =#{id}")
	int updateOrderStatusById(@Param("id") long id,@Param("status")OrderStatus status);
	@Insert({"<script>",
		"insert into order_product(orderId,productId,productAd,quantity,price,createDt,updateDt)",
		"values ",
		"<foreach collection='list' item='item' index='index' separator=','>",
		"(#{item.orderId},#{item.productId},#{item.productAd},#{item.quantity},#{item.price},#{item.createDt},#{item.updateDt})",
		"</foreach>",
		"</script>"	
	})
	int insertBatchOrderProduct(@Param(value ="list") List<OrderProduct> orderProducts);
	@Select({"<script>",
		"select * from order_product where orderId=#{orderId}",
		"</script>"	
	})
	List<OrderProduct> selectOrderProductByOrderId(long orderId);
	/**
	 * @Description
	 * @param userId
	 * @return
	 */
	@Select({"<script>",
		"select * from `order` where userId=#{userId}",
		"</script>"	
	})
	List<Order> selectOrdersByUserId(long userId);
}
