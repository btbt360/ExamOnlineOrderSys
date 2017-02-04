package com.wide.common.model.query;

public class QueryExaminee {
	private String name ;// 考生姓名
	private String examId;//试题id
	private String exampapers_id;//试卷id
	private String examineeId;//考生id
	private String starttime;//考试开始时间
	private String endtime;//考试结束时间
	private String userid;//用户编码
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getExampapers_id() {
		return exampapers_id;
	}

	public void setExampapers_id(String exampapers_id) {
		this.exampapers_id = exampapers_id;
	}

	public String getExamineeId() {
		return examineeId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setExamineeId(String examineeId) {
		this.examineeId = examineeId;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}
