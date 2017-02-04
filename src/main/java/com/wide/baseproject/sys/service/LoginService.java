package com.wide.baseproject.sys.service;

import java.util.ArrayList;
import java.util.List;

import com.wide.common.model.User;

public class LoginService {

	/**
	 * 验证用户登录信息
	 * @author cg
	 * @param user对象
	 * @return List<User>
	 * */
	public  List<User> loginex(User user){
		List<User> users = new ArrayList<User>();
		if(user.getLoginName()!=null&&user.getPassword()!=null){
			users = User.dao.findLogin(user);
		}
		return users;
	}
}
