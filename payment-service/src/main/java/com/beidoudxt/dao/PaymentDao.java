/**
 * 
 */
package com.beidoudxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.beidoudxt.entity.presistent.PaymentRecord;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月29日下午8:36:58
 */
public interface PaymentDao {
	@Select("select * from payment_record where userId = #{userId}")
	List<PaymentRecord> selectPaymentRecord(long userId);

}
