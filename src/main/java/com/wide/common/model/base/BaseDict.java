package com.wide.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.wide.config.DbModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDict<M extends BaseDict<M>> extends DbModel<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setDictkey(java.lang.String dictkey) {
		set("dictkey", dictkey);
	}

	public java.lang.String getDictkey() {
		return get("dictkey");
	}

	public void setDictvalue(java.lang.String dictvalue) {
		set("dictvalue", dictvalue);
	}

	public java.lang.String getDictvalue() {
		return get("dictvalue");
	}

	public void setDescription(java.lang.String description) {
		set("description", description);
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}

	public java.lang.String getType() {
		return get("type");
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

	public void setTypeinfo(java.lang.String typeinfo) {
		set("typeinfo", typeinfo);
	}

	public java.lang.String getTypeinfo() {
		return get("typeinfo");
	}

}
