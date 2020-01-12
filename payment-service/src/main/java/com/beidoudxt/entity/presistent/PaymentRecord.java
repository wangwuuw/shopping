/**
 * 
 */
package com.beidoudxt.entity.presistent;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月29日下午8:38:10
 */
@Getter
@Setter
public class PaymentRecord {
	private long id;
	private long orderId;
	private long userId;
	private BigDecimal price;
	private Date createDt;
	private Date updateDt;

}
