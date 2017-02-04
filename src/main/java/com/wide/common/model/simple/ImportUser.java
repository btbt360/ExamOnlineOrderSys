package com.wide.common.model.simple;

import com.wide.util.ExcelVOAttribute;

public class ImportUser {
	@ExcelVOAttribute(column="A", name = "")
	private String officestr;
	@ExcelVOAttribute(column="B", name = "")
	private String name;
	@ExcelVOAttribute(column="C", name = "")
	private String departmaent;
	@ExcelVOAttribute(column="D", name = "")
	private String post;
	@ExcelVOAttribute(column="E", name = "")
	private String duty;
	@ExcelVOAttribute(column="F", name = "")
	private String usertype;
	@ExcelVOAttribute(column="G", name = "")
	private String cardno;
	@ExcelVOAttribute(column="H", name = "")
	private String sex;
	@ExcelVOAttribute(column="I", name = "")
	private String minzu;
	@ExcelVOAttribute(column="J", name = "")
	private String zhouyear;
	@ExcelVOAttribute(column="K", name = "")
	private String seniority;
	@ExcelVOAttribute(column="L", name = "")
	private String politicsstatus;
	public String getOfficestr() {
		return officestr;
	}
	public void setOfficestr(String officestr) {
		this.officestr = officestr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmaent() {
		return departmaent;
	}
	public void setDepartmaent(String departmaent) {
		this.departmaent = departmaent;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMinzu() {
		return minzu;
	}
	public void setMinzu(String minzu) {
		this.minzu = minzu;
	}
	public String getZhouyear() {
		return zhouyear;
	}
	public void setZhouyear(String zhouyear) {
		this.zhouyear = zhouyear;
	}
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}
	public String getPoliticsstatus() {
		return politicsstatus;
	}
	public void setPoliticsstatus(String politicsstatus) {
		this.politicsstatus = politicsstatus;
	}
	
	

}
