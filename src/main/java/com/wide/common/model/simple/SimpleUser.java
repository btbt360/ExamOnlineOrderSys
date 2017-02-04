package com.wide.common.model.simple;

public class SimpleUser {

	private String id ;
	private String loginName;
	private String remark;
	private String type;
	
	public SimpleUser(String id, String loginName,String remark,String type){
		this.id = id;
		this.loginName = loginName;
		this.remark = remark;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
