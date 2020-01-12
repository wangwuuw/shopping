/**
 * 
 */
package com.beidoudxt.entity.request;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

import com.beidoudxt.common.OrderStatus;
import com.beidoudxt.entity.presistent.OrderProduct;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月22日下午3:31:07
 */
@Getter
@Setter
public class OrderRequest {
	private long id;
	private List<OrderProduct> products;
	private long userId;
	
}
