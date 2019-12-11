package com.beidoudxt.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidoudxt.common.RedisService;
import com.beidoudxt.common.ResponseWrapper;
import com.beidoudxt.entity.request.LoginRequest;
import com.beidoudxt.entity.request.RegisterRequest;
import com.beidoudxt.service.UserService;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 注册和登录
 * @date: 2019年12月8日下午8:26:39
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	/**
	 * @Description: 要求：
	 *               用户名：
	 *               必须是6-10位字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含） 不能以数字开头
	 *               密码：
	 *               必须是6-20位的字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含）
	 * @return
	 */
	@PostMapping("/register")
	public ResponseWrapper register(@RequestBody @Valid RegisterRequest request){
		/**
		 * 校验两次密码是否一致
		 */
		String name = request.getName();
		String password = request.getPassword();
		String repassword = request.getRepassword();
		if (!password.equals(repassword)) {
			return ResponseWrapper.fail("两次密码不一致");
		}
		/**
		 * 校验用户名和密码格式是否一致
		 */
		boolean checkNameFormat = checkNameFormat(name);
		if (!checkNameFormat) {
			return ResponseWrapper.fail("用户名格式错误");
		}
		boolean checkPwdFormat = checkPwdFormat(password);
		if (!checkPwdFormat) {
			return ResponseWrapper.fail("密码格式错误");
		}
		/**
		 * 检验用户名是否存在
		 */
		boolean checkIsExist = userService.checkIsExist(name);
		if (checkIsExist) {
			return ResponseWrapper.fail("用户名已存在");
		}
		boolean register = userService.register(request);
		if (!register) {
			return ResponseWrapper.fail("注册失败");
		}
		String token = getToken();
		redisService.setObject(name, token, 60L);
		return ResponseWrapper.ok("注册成功",token);
		
	}
	@PostMapping("/login")
	public ResponseWrapper login(@RequestBody @Valid LoginRequest request){
		/**
		 * 校验用户名和密码格式是否一致
		 */
		String name = request.getName();
		String password = request.getPassword();
		boolean checkNameFormat = checkNameFormat(name);
		if (!checkNameFormat) {
			return ResponseWrapper.fail("用户名格式错误");
		}
		boolean checkPwdFormat = checkPwdFormat(password);
		if (!checkPwdFormat) {
			return ResponseWrapper.fail("密码格式错误");
		}
		/**
		 * 为了逻辑上简单 测试方便注册登录都没做md5加盐等加密
		 *
		 */
		boolean checkNameAndPwd = userService.checkNameAndPwd(request);
		if (!checkNameAndPwd) {
			return ResponseWrapper.fail("用户名或密码错误");
		}
		String token = getToken();
		redisService.setObject(name, token, 60L);
		return ResponseWrapper.ok("登录成功",token);
		
	}
	public String getToken(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	public boolean checkNameFormat(String name){
		String reg = "^[^0-9][\\w]{5,9}$";
		if (name.matches(reg)) {
			return true;
		}
		return false;
		
	}
	public boolean checkPwdFormat(String pwd){
		String reg = "^[\\w]{6,20}$";
		if (pwd.matches(reg)) {
			return true;
		}
		return false;
		
	}
	


}
