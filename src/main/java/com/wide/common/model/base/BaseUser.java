package com.wide.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.wide.config.DbModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUser<M extends BaseUser<M>> extends DbModel<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setLoginName(java.lang.String loginName) {
		set("login_name", loginName);
	}

	public java.lang.String getLoginName() {
		return get("login_name");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setNo(java.lang.String no) {
		set("no", no);
	}

	public java.lang.String getNo() {
		return get("no");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setEmail(java.lang.String email) {
		set("email", email);
	}

	public java.lang.String getEmail() {
		return get("email");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setCardno(java.lang.String cardno) {
		set("cardno", cardno);
	}

	public java.lang.String getCardno() {
		return get("cardno");
	}

	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}

	public java.lang.String getMobile() {
		return get("mobile");
	}

	public void setUserType(java.lang.Integer userType) {
		set("user_type", userType);
	}

	public java.lang.Integer getUserType() {
		return get("user_type");
	}

	public void setLoginType(java.lang.Integer loginType) {
		set("login_type", loginType);
	}

	public java.lang.Integer getLoginType() {
		return get("login_type");
	}

	public void setPhoto(java.lang.String photo) {
		set("photo", photo);
	}

	public java.lang.String getPhoto() {
		return get("photo");
	}

	public void setLoginIp(java.lang.String loginIp) {
		set("login_ip", loginIp);
	}

	public java.lang.String getLoginIp() {
		return get("login_ip");
	}

	public void setLoginDate(java.util.Date loginDate) {
		set("login_date", loginDate);
	}

	public java.util.Date getLoginDate() {
		return get("login_date");
	}

	public void setLoginFlag(java.lang.String loginFlag) {
		set("login_flag", loginFlag);
	}

	public java.lang.String getLoginFlag() {
		return get("login_flag");
	}

	public void setFingerprintone(java.lang.String fingerprintone) {
		set("fingerprintone", fingerprintone);
	}

	public java.lang.String getFingerprintone() {
		return get("fingerprintone");
	}

	public void setFingerprinttwo(java.lang.String fingerprinttwo) {
		set("fingerprinttwo", fingerprinttwo);
	}

	public java.lang.String getFingerprinttwo() {
		return get("fingerprinttwo");
	}

	public void setIsonline(java.lang.Integer isonline) {
		set("isonline", isonline);
	}

	public java.lang.Integer getIsonline() {
		return get("isonline");
	}

	public void setSex(java.lang.Integer sex) {
		set("sex", sex);
	}

	public java.lang.Integer getSex() {
		return get("sex");
	}

	public void setNation(java.lang.Integer nation) {
		set("nation", nation);
	}

	public java.lang.Integer getNation() {
		return get("nation");
	}

	public void setBirthdate(java.util.Date birthdate) {
		set("birthdate", birthdate);
	}

	public java.util.Date getBirthdate() {
		return get("birthdate");
	}

	public void setYearling(java.lang.Integer yearling) {
		set("yearling", yearling);
	}

	public java.lang.Integer getYearling() {
		return get("yearling");
	}

	public void setCreateBy(java.lang.String createBy) {
		set("create_by", createBy);
	}

	public java.lang.String getCreateBy() {
		return get("create_by");
	}

	public void setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
	}

	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public void setUpdateBy(java.lang.String updateBy) {
		set("update_by", updateBy);
	}

	public java.lang.String getUpdateBy() {
		return get("update_by");
	}

	public void setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
	}

	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public void setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
	}

	public java.lang.String getRemarks() {
		return get("remarks");
	}

	public void setDelFlag(java.lang.String delFlag) {
		set("del_flag", delFlag);
	}

	public java.lang.String getDelFlag() {
		return get("del_flag");
	}

	public void setDuty(java.lang.String duty) {
		set("duty", duty);
	}

	public java.lang.String getDuty() {
		return get("duty");
	}

	public void setRankcadre(java.lang.String rankcadre) {
		set("rankcadre", rankcadre);
	}

	public java.lang.String getRankcadre() {
		return get("rankcadre");
	}

	public void setCraftsmansys(java.lang.String craftsmansys) {
		set("craftsmansys", craftsmansys);
	}

	public java.lang.String getCraftsmansys() {
		return get("craftsmansys");
	}

	public void setResearchsys(java.lang.String researchsys) {
		set("researchsys", researchsys);
	}

	public java.lang.String getResearchsys() {
		return get("researchsys");
	}

	public void setPoisonousgz(java.lang.String poisonousgz) {
		set("poisonousgz", poisonousgz);
	}

	public java.lang.String getPoisonousgz() {
		return get("poisonousgz");
	}

	public void setOthermonthly(java.lang.Integer othermonthly) {
		set("othermonthly", othermonthly);
	}

	public java.lang.Integer getOthermonthly() {
		return get("othermonthly");
	}

	public void setRestpost(java.util.Date restpost) {
		set("restpost", restpost);
	}

	public java.util.Date getRestpost() {
		return get("restpost");
	}

	public void setRetiredate(java.util.Date retiredate) {
		set("retiredate", retiredate);
	}

	public java.util.Date getRetiredate() {
		return get("retiredate");
	}

	public void setNativeplace(java.lang.String nativeplace) {
		set("nativeplace", nativeplace);
	}

	public java.lang.String getNativeplace() {
		return get("nativeplace");
	}

	public void setWorktime(java.util.Date worktime) {
		set("worktime", worktime);
	}

	public java.util.Date getWorktime() {
		return get("worktime");
	}

	public void setRuspaceflight(java.util.Date ruspaceflight) {
		set("ruspaceflight", ruspaceflight);
	}

	public java.util.Date getRuspaceflight() {
		return get("ruspaceflight");
	}

	public void setRucampaing(java.util.Date rucampaing) {
		set("rucampaing", rucampaing);
	}

	public java.util.Date getRucampaing() {
		return get("rucampaing");
	}

	public void setSeniority(java.lang.String seniority) {
		set("seniority", seniority);
	}

	public java.lang.String getSeniority() {
		return get("seniority");
	}

	public void setLaterdegree(java.lang.String laterdegree) {
		set("laterdegree", laterdegree);
	}

	public java.lang.String getLaterdegree() {
		return get("laterdegree");
	}

	public void setGraduate(java.lang.String graduate) {
		set("graduate", graduate);
	}

	public java.lang.String getGraduate() {
		return get("graduate");
	}

	public void setGraduatelater(java.lang.String graduatelater) {
		set("graduatelater", graduatelater);
	}

	public java.lang.String getGraduatelater() {
		return get("graduatelater");
	}

	public void setLatereducation(java.lang.String latereducation) {
		set("latereducation", latereducation);
	}

	public java.lang.String getLatereducation() {
		return get("latereducation");
	}

	public void setLatergraduationdate(java.util.Date latergraduationdate) {
		set("latergraduationdate", latergraduationdate);
	}

	public java.util.Date getLatergraduationdate() {
		return get("latergraduationdate");
	}

	public void setGraduatedate(java.util.Date graduatedate) {
		set("graduatedate", graduatedate);
	}

	public java.util.Date getGraduatedate() {
		return get("graduatedate");
	}

	public void setMajor(java.lang.String major) {
		set("major", major);
	}

	public java.lang.String getMajor() {
		return get("major");
	}

	public void setDegree(java.lang.String degree) {
		set("degree", degree);
	}

	public java.lang.String getDegree() {
		return get("degree");
	}

	public void setPoliticsstatus(java.lang.Integer politicsstatus) {
		set("politicsstatus", politicsstatus);
	}

	public java.lang.Integer getPoliticsstatus() {
		return get("politicsstatus");
	}

	public void setRupartydate(java.util.Date rupartydate) {
		set("rupartydate", rupartydate);
	}

	public java.util.Date getRupartydate() {
		return get("rupartydate");
	}

	public void setTechnicalleave(java.lang.String technicalleave) {
		set("technicalleave", technicalleave);
	}

	public java.lang.String getTechnicalleave() {
		return get("technicalleave");
	}

	public void setEvaluatedate(java.util.Date evaluatedate) {
		set("evaluatedate", evaluatedate);
	}

	public java.util.Date getEvaluatedate() {
		return get("evaluatedate");
	}

	public void setEmploydate(java.util.Date employdate) {
		set("employdate", employdate);
	}

	public java.util.Date getEmploydate() {
		return get("employdate");
	}

	public void setSpecialty(java.lang.String specialty) {
		set("specialty", specialty);
	}

	public java.lang.String getSpecialty() {
		return get("specialty");
	}

	public void setAuthenticatework(java.lang.String authenticatework) {
		set("authenticatework", authenticatework);
	}

	public java.lang.String getAuthenticatework() {
		return get("authenticatework");
	}

}