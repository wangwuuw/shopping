/**
 * 
 */
package com.beidoudxt.entity.presistent;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.beidoudxt.common.OrderStatus;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月22日下午3:31:07
 */
@Getter
@Setter
public class Order {
	private long id;
	private Date createDt = new Date();
	private Date updateDt = new Date();
	private OrderStatus status;
	private long userId;
	
}
