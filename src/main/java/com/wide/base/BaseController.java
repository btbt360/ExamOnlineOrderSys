package com.wide.base;


import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.wide.common.model.User;
import com.wide.config.UserToken;
import com.jfinal.core.Controller;

public class BaseController extends Controller{
	public RenturnInfo returninfo; 
	
	/**
	 * 获取usertoken
	 * */
	public UserToken getToken(){
		HttpSession session=getSession();
		UserToken ut=(UserToken) session.getAttribute("userToken");
		return ut;
	}

	/**
	 * 获取user
	 * */
	public User getUser(){
		UserToken ut = getToken();
		User user = null;
		if(ut!=null&&!ut.equals("")){
			user =ut.getVuser().getUser();
		}else{
			user = new User();
		}
		return user;
	}
	/**
	 * @author cg
	 * 生成uuid
	 * */
	public static String createUUid(){
		UUID uuid = UUID.randomUUID();
		return uuid+"";
	}
	
	public RenturnInfo getReturninfo() {
		return returninfo;
	}

	public void setReturninfo(RenturnInfo returninfo) {
		this.returninfo = returninfo;
	};
	
	
	/**
	 * 
	 * */
	
	
	
	
	
}
