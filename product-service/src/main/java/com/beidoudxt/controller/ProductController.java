/**
 * 
 */
package com.beidoudxt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidoudxt.entity.request.UpdateStockRequest;
import com.beidoudxt.entity.response.ProductDetailResponse;
import com.beidoudxt.entity.response.ProductResponse;
import com.beidoudxt.service.ProductService;
import com.beidoudxt.util.ResponseWrapper;

/**
 * @Company 北斗大学堂 qq群：903672759
 * @author 令狐冲老师 qq号 1058737002
 * @Description:
 * @date: 2019年12月15日下午8:18:40
 */
@RestController
@RequestMapping("/v1/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/getList")
	public ResponseWrapper<List<ProductResponse>> getList() {
		List<ProductResponse> list = productService.getList();
		return ResponseWrapper.ok("查询商品列表成功", list);

	}

	@GetMapping("/getDetail")
	public ResponseWrapper<ProductDetailResponse> getDetail(long productId) {
		ProductDetailResponse detail = productService.getDetail(productId);
		return ResponseWrapper.ok("查询商品详情成功", detail);
	}

	@PostMapping("/updateStock")
	public ResponseWrapper updateStock(@RequestBody UpdateStockRequest request) {
		boolean updateStock = productService.updateStock(request);
		return updateStock ? ResponseWrapper.ok("修改库存成功") : ResponseWrapper
				.fail("修改库存失败");

	}
	@GetMapping("/getOne")
	public ResponseWrapper<ProductResponse> getList(long productId) {
		ProductResponse one = productService.getOne(productId);
		return ResponseWrapper.ok("查询商品列表成功", one);

	}
}
