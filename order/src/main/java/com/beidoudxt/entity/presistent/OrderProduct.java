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
 * @date: 2019年12月22日下午6:23:38
 */
@Setter
@Getter
public class OrderProduct {
    private long id;
    private long orderId;
    private BigDecimal price;
    private int quantity;
    private String productAd;
    private long productId;
    private Date createDt = new Date();
    private Date updateDt = new Date();
}
