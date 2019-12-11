/**
 * 
 */
package com.beidoudxt.service;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.beidoudxt.dao.UserMapper;
import com.beidoudxt.entity.persistent.UserPt;
import com.beidoudxt.entity.request.LoginRequest;
import com.beidoudxt.entity.request.RegisterRequest;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月8日下午6:00:40
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public boolean register(@RequestBody @Valid RegisterRequest request) {
        UserPt userPt = new UserPt();
        userPt.setName(request.getName());
        userPt.setPassword(request.getPassword());
        userPt.setCreateDt(new Date());
        userPt.setUpdateDt(new Date());
		int flag = userMapper.insertUser(userPt);
		return flag>0;

	}

	/**
	 * @Description
	 * @param request
	 */
	public boolean checkNameAndPwd(@Valid LoginRequest request) {
	       UserPt userPt = new UserPt();
	        userPt.setName(request.getName());
	        userPt.setPassword(request.getPassword());
	        int flag = userMapper.selectByNameAndPwd(userPt);
		return flag>0;		
	}

	/**
	 * @Description
	 * @param checkName
	 */
	public boolean checkIsExist(String name) {
	      UserPt userPt = new UserPt();
	        userPt.setName(name);
	        int flag = userMapper.checkName(userPt);
		return flag>0;	
		
	}

}
