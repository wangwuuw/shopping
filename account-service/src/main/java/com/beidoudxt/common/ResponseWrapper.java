package com.beidoudxt.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper<T> {
	private String status;
	private String msg;
	private T data;
	 
	
	public static <T> ResponseWrapper<T> createResponse(String status,String msg,T data){
		ResponseWrapper<T> responseWrapper = new ResponseWrapper<T>();
		responseWrapper.setData(data);
		responseWrapper.setMsg(msg);
		responseWrapper.setStatus(status);
		return responseWrapper;
		
	}
	public static  <T> ResponseWrapper<T> ok(String msg,T data){
		return createResponse("success",msg,data);
	}
	public static  <T> ResponseWrapper<T> ok(String msg){
		return createResponse("success",msg,null);
	}
	public static <T> ResponseWrapper<T> fail(String msg,T data){
		return createResponse("fail",msg,data);
	}
	public static <T> ResponseWrapper<T> fail(String msg){
		return createResponse("fail",msg,null);
	}

}
