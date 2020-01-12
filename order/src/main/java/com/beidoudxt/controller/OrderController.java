/**
 * 
 */
package com.beidoudxt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidoudxt.feign.*;
import com.beidoudxt.entity.persistent.UserPt;
import com.beidoudxt.entity.presistent.Order;
import com.beidoudxt.entity.request.OrderRequest;
import com.beidoudxt.entity.response.OrderResponse;
import com.beidoudxt.service.OrderService;
import com.beidoudxt.util.ResponseWrapper;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月22日下午5:09:10
 */
@RestController
@RequestMapping("/v1/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

	/**
	 * 下单
	 * @Description
	 * @param request
	 * @return
	 */
	@PostMapping("/order")
   public ResponseWrapper order(@RequestBody @Valid OrderRequest request,@RequestHeader("Authorization") String Authorization) {
		UserPt user = (UserPt)redisTemplate.opsForValue().get(Authorization);
		request.setUserId(user.getId());
		boolean createOrder = orderService.createOrder(request);
	return createOrder?ResponseWrapper.ok("下单成功"):ResponseWrapper.fail("下单失败");
}
	@GetMapping("/queryOrderDetails")
	public ResponseWrapper queryOrderDetails(long orderId){
		OrderResponse queryOrder = orderService.queryOrder(orderId);
		return ResponseWrapper.ok("查询订单信息成功",queryOrder);
	}
	@GetMapping("/queryUserOrders")
	public ResponseWrapper queryUserOrders(@RequestHeader String Authorization){
		UserPt user = (UserPt)redisTemplate.opsForValue().get(Authorization);
		List<Order> orders = orderService.queryOrdersByUserId(user.getId());
		return ResponseWrapper.ok("查询用户全部订单信息成功",orders);
	}
	@GetMapping("/updateOrderStatu")
	public ResponseWrapper updateOrderStatu(long orderId){
		boolean flag = orderService.updateOrderStatu(orderId);
		return flag?ResponseWrapper.ok("下单成功"):ResponseWrapper.fail("下单失败");
	}
	
}
