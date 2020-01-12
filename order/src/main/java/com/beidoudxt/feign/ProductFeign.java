/**
 * 
 */
package com.beidoudxt.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.beidoudxt.entity.request.UpdateStockRequest;
import com.beidoudxt.entity.response.ProductDetailResponse;
import com.beidoudxt.entity.response.ProductResponse;
import com.beidoudxt.util.ResponseWrapper;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月22日下午8:29:36
 */
@FeignClient(value = "product")
@RequestMapping("/v1/product")
public interface ProductFeign {
	@GetMapping("/getOne")
	ResponseWrapper<ProductResponse> queryProduct(@RequestParam(value = "productId")long productId);
	@PostMapping("/updateStock")
	ResponseWrapper updateStock(@RequestBody UpdateStockRequest request);
	

}
