package com.wide.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTextbook<M extends BaseTextbook<M>> extends Model<M> implements IBean {

	public void setId(java.lang.String id) {
		set("id", id);
	}

	public java.lang.String getId() {
		return get("id");
	}

	public void setSubjectId(java.lang.String subjectId) {
		set("subject_id", subjectId);
	}

	public java.lang.String getSubjectId() {
		return get("subject_id");
	}

	public void setCreatorId(java.lang.String creatorId) {
		set("creator_id", creatorId);
	}

	public java.lang.String getCreatorId() {
		return get("creator_id");
	}

	public void setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
	}

	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public void setUpdateDate(java.util.Date updateDate) {
		set("update_date", updateDate);
	}

	public java.util.Date getUpdateDate() {
		return get("update_date");
	}

	public void setUpdateBy(java.lang.String updateBy) {
		set("update_by", updateBy);
	}

	public java.lang.String getUpdateBy() {
		return get("update_by");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setIsenable(java.lang.Integer isenable) {
		set("isenable", isenable);
	}

	public java.lang.Integer getIsenable() {
		return get("isenable");
	}

	public void setIsdel(java.lang.Integer isdel) {
		set("isdel", isdel);
	}

	public java.lang.Integer getIsdel() {
		return get("isdel");
	}

	public void setCode(java.lang.String code) {
		set("code", code);
	}

	public java.lang.String getCode() {
		return get("code");
	}

	public void setIssuedate(java.util.Date issuedate) {
		set("issuedate", issuedate);
	}

	public java.util.Date getIssuedate() {
		return get("issuedate");
	}

	public void setEditorinchief(java.lang.String editorinchief) {
		set("editorinchief", editorinchief);
	}

	public java.lang.String getEditorinchief() {
		return get("editorinchief");
	}

}
