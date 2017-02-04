package com.wide.common.model.simple;

import com.wide.util.ExcelVOAttribute;

public class ImportDAX {
	@ExcelVOAttribute(column="A", name = "")
	private String qtitle;
	@ExcelVOAttribute(column="B", name = "")
	private String qinfo;
	@ExcelVOAttribute(column="C", name = "")
	private String optionA;
	@ExcelVOAttribute(column="D", name = "")
	private String optionB;
	@ExcelVOAttribute(column="E", name = "")
	private String optionC;
	@ExcelVOAttribute(column="F", name = "")
	private String optionD;
	@ExcelVOAttribute(column="G", name = "")
	private String answer;
	@ExcelVOAttribute(column="H", name = "")
	private String answerinfo;
	@ExcelVOAttribute(column="I", name = "")
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
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
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
