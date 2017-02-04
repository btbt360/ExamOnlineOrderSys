package com.wide.viewmodel;

public class ViewChartData {
	private int qualified;//合格人数
	private int noqualified;//不合格人数
	private int excellent;//优秀人数
	private String kaoshixStr;//考试纵轴
	public int getQualified() {
		return qualified;
	}
	public void setQualified(int qualified) {
		this.qualified = qualified;
	}
	public int getNoqualified() {
		return noqualified;
	}
	public void setNoqualified(int noqualified) {
		this.noqualified = noqualified;
	}
	public int getExcellent() {
		return excellent;
	}
	public void setExcellent(int excellent) {
		this.excellent = excellent;
	}
	public String getKaoshixStr() {
		return kaoshixStr;
	}
	public void setKaoshixStr(String kaoshixStr) {
		this.kaoshixStr = kaoshixStr;
	}
	
	
}
