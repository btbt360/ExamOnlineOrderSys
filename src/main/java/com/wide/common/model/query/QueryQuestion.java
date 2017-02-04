package com.wide.common.model.query;

public class QueryQuestion {
	
	private String code;
	private String subjectid;
	private String itembankid;
	private String itembankids;//多选题库编码
	private String questionstype;
	private String exampapersid;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getItembankid() {
		return itembankid;
	}
	public void setItembankid(String itembankid) {
		this.itembankid = itembankid;
	}
	public String getQuestionstype() {
		return questionstype;
	}
	public void setQuestionstype(String questionstype) {
		this.questionstype = questionstype;
	}
	public String getItembankids() {
		return itembankids;
	}
	public void setItembankids(String itembankids) {
		this.itembankids = itembankids;
	}
	public String getExampapersid() {
		return exampapersid;
	}
	public void setExampapersid(String exampapersid) {
		this.exampapersid = exampapersid;
	}
	
	

}
