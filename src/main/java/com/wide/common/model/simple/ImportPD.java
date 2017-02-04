package com.wide.common.model.simple;

import com.wide.util.ExcelVOAttribute;

public class ImportPD {
	@ExcelVOAttribute(column="A", name = "")
	private String qtitle;
	@ExcelVOAttribute(column="B", name = "")
	private String qinfo;
	@ExcelVOAttribute(column="C", name = "")
	private String answer;
	@ExcelVOAttribute(column="D", name = "")
	private String answerinfo;
	@ExcelVOAttribute(column="E", name = "")
	private String remark;
	public String getQtitle() {
		return qtitle;
	}
	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}
	public String getQinfo() {
		return qinfo;
	}
	public void setQinfo(String qinfo) {
		this.qinfo = qinfo;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerinfo() {
		return answerinfo;
	}
	public void setAnswerinfo(String answerinfo) {
		this.answerinfo = answerinfo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
