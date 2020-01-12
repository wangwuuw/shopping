/**
 * 
 */
package com.beidoudxt.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidoudxt.entity.presistent.PaymentRecord;
import com.beidoudxt.service.PaymentService;
import com.beidoudxt.util.ResponseWrapper;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月29日下午8:36:09
 */
@RestController
@RequestMapping("/v1/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
    @Autowired
	StringRedisTemplate redisTemplate;
	@GetMapping("/queryPaymentRecords")
	public ResponseWrapper<List<PaymentRecord>> queryPaymentRecords(long userId){
		List<PaymentRecord> paymentRecords = paymentService.queryPaymentRecords(userId);
		return ResponseWrapper.ok("查询支付记录成功",paymentRecords);
	}
	@GetMapping("/pay")
	public ResponseWrapper pay(long orderId){
 		String isOk = redisTemplate.opsForValue().get(String.valueOf(orderId));
 		if (!"ok".equals(isOk)) {
 			return	ResponseWrapper.ok("订单已失效,请重新下单");
		}
		boolean pay = paymentService.pay(orderId);
		return pay?ResponseWrapper.ok("支付成功"):ResponseWrapper.fail("支付失败");
		
	}

}
