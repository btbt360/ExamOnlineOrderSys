package com.wide.constant;

public enum EnumOptType {
	
	/**
	 * 登录
	 */
	Login(1, "登录"), 
	/**
	 * 注销
	 */
	Logout(2, "注销"),
	/**
	 * 密码修改
	 */
	password(3, "密码修改"),
	/**
	 * 密码重置
	 */
	reset(4, "注销"),
	/**
	 * 添加
	 */
	add(5, "添加"),
	/**
	 * 修改
	 */
	edit(6, "修改"),
	/**
	 * 删除
	 */
	del(7, "删除"),
	/**
	 * 同步
	 */
	syn(8, "同步"),
	/**
	 * 阅读
	 */
	read(9, "阅读"),
	/**
	 * 审批
	 */
	appr(10, "审批"),
	/**
	 * 群发
	 */
	mass(11, "群发"),
	/**
	 * 导出
	 */
	export(12, "导出");
	private int enumKey;
	private String enumText;

	private EnumOptType(int key, String text) {
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
		for (EnumOptType e : EnumOptType.values()) {
			if (e.getEnumKey() == key) {
				return e.getEnumText();
			}
		}
		return null;
	}
}
