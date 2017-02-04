package com.wide.constant;

public enum EnumFuncType {
	
	/**
	 * 用户
	 */
	user(1, "用户"), 
	/**
	 * 地区
	 */
	area(2, "地区"), 
	/**
	 * 机构
	 */
	office(3, "机构"),
	/**
	 * 菜单
	 */
	menu(4, "菜单"),
	/**
	 * 角色
	 */
	role(5, "角色"),
	/**
	 * 数据字典
	 */
	dict(6, "数据字典"),
	/**
	 * 微信信息
	 */
	wxmessage(7, "微信信息"),
	/**
	 * 微信菜单
	 */
	wxmenu(8, "微信菜单"),
	/**
	 * 投诉意见
	 */
	complaint(9, "投诉意见"),
	/**
	 * 联系方式
	 */
	contact(10, "联系方式"),
	/**
	 * APP下载方式
	 */
	APP(11, "APP下载方式"),
	/**
	 * 微信基础配置
	 */
	wxbase(12, "微信基础配置");


	private int enumKey;
	private String enumText;

	private EnumFuncType(int key, String text) {
		this.enumKey = key;
		this.enumText = text;
	}

	public int getEnumKey() {
		return enumKey;
	}

	public String getEnumText() {
		return enumText;
	}
	
	public static final String getFromKey(int key) {
		for (EnumFuncType e : EnumFuncType.values()) {
			if (e.getEnumKey() == key) {
				return e.getEnumText();
			}
		}
		return null;
	}
}
