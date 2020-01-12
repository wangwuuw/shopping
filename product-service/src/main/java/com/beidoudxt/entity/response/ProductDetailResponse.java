/**
 * 
 */
package com.beidoudxt.entity.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月15日下午8:37:00
 */
@Getter
@Setter
public class ProductDetailResponse {
	private long productId;
	private String name;
	private String brand;
	private String place;
	private String specifications;
	private int stock;
}
