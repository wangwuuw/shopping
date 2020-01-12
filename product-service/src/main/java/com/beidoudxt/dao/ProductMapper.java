/**
 * 
 */
package com.beidoudxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.beidoudxt.entity.persistent.Product;
import com.beidoudxt.entity.persistent.ProductDetail;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月15日下午8:19:08
 */
public interface ProductMapper {
	@Select("select * from product")
	List<Product> getList();
	@Select("select * from product where id = #{productId}")
	Product getOne(long productId);
	@Select("select * from product_detail where productId = #{id}")
	ProductDetail getDetail(long productId);
	@Update("update product_detail set stock = stock-#{quantity} where stock>=#{quantity} and productId=#{productId}")
	int updateStock(@Param("productId")long productId,@Param("quantity")int quantity);

}
