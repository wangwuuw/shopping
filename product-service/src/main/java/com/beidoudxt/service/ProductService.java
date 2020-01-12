/**
 * 
 */
package com.beidoudxt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beidoudxt.dao.ProductMapper;
import com.beidoudxt.entity.persistent.Product;
import com.beidoudxt.entity.persistent.ProductDetail;
import com.beidoudxt.entity.request.UpdateStockRequest;
import com.beidoudxt.entity.response.ProductDetailResponse;
import com.beidoudxt.entity.response.ProductResponse;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月15日下午8:19:25
 */
@Service
public class ProductService {
	@Autowired
 private ProductMapper productMapper;
	public List<ProductResponse> getList(){
		List<Product> list = productMapper.getList();
		List<ProductResponse> listRes = new ArrayList<ProductResponse>();
		for (Product product : list) {
			ProductResponse res = new ProductResponse();
			res.setAd(product.getAd());
			res.setId(product.getId());
			res.setPrice(product.getPrice());
			listRes.add(res);
		}
		return listRes;
		
	}
	public ProductResponse getOne(long productId){
		Product product = productMapper.getOne(productId);
			ProductResponse res = new ProductResponse();
			res.setAd(product.getAd());
			res.setId(product.getId());
			res.setPrice(product.getPrice());
		return res;
		
	}
	public ProductDetailResponse getDetail(long productId){
		ProductDetail detail = productMapper.getDetail(productId);
		ProductDetailResponse pdr = new ProductDetailResponse();
		pdr.setBrand(detail.getBrand());
		pdr.setName(detail.getName());
		pdr.setPlace(detail.getPlace());
		pdr.setProductId(detail.getProductId());
		pdr.setSpecifications(detail.getSpecifications());
		pdr.setStock(detail.getStock());
		return pdr;
	}
	public boolean updateStock(UpdateStockRequest request){
		int updateStock = productMapper.updateStock(request.getProductId(), request.getQuantity());
	return updateStock>0;
	}
}
