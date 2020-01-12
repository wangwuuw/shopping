/**
 * 
 */
package com.beidoudxt.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beidoudxt.util.ResponseWrapper;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月29日下午8:51:39
 */
@FeignClient(value = "order")
@RequestMapping("/v1/order")
public interface OrderFeign {
	@GetMapping("/updateOrderStatu")
	ResponseWrapper updateOrderStatu(@RequestParam(value = "orderId") long orderId);
}
