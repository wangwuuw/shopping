/**
 * 
 */
package com.beidoudxt.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.beidoudxt.entity.persistent.UserPt;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月8日下午6:03:11
 */
public interface UserMapper {
	@Insert("insert into user(name,password,createDt,updateDt) values(#{name},#{password},#{createDt},#{updateDt})")
	int insertUser(UserPt userPt);

	/**
	 * @Description
	 * @param userPt
	 */
	@Select("select count(1) from user where name=#{name} and password=#{password}")
	int selectByNameAndPwd(UserPt userPt);

	/**
	 * @Description
	 * @param userPt
	 * @return
	 */
	@Select("select count(1) from user where name=#{name}")
	int checkName(UserPt userPt);

}
