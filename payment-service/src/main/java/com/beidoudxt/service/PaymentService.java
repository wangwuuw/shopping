/**
 * 
 */
package com.beidoudxt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beidoudxt.entity.presistent.PaymentRecord;
import com.beidoudxt.feign.OrderFeign;
import com.beidoudxt.util.ResponseWrapper;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月29日下午8:43:00
 */
@Service
public class PaymentService {
	  @Autowired
      private com.beidoudxt.dao.PaymentDao PaymentDao;
	  @Autowired
	  private OrderFeign orderFeign;
	  public List<PaymentRecord> queryPaymentRecords(long userId){
		  return PaymentDao.selectPaymentRecord(userId);
	  }
	/**
	 * @Description
	 * @param orderId
	 */
	public boolean pay(long orderId) {
		ResponseWrapper updateOrderStatu = orderFeign.updateOrderStatu(orderId);
		String status = updateOrderStatu.getStatus();
		if ("success".equals(status)) {
			return true;
		}
		return false;
		
	}
}
