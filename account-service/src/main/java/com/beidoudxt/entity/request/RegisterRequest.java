/**
 * 
 */
package com.beidoudxt.entity.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月8日下午5:26:50
 */
@Getter
@Setter
public class RegisterRequest {
	@NotBlank
  private String name;
	@NotBlank
  private String password;
    @NotBlank
  private String repassword;	
}
