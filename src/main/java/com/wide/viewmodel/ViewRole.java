package com.wide.viewmodel;

import java.util.List;

import com.wide.common.model.Right;
import com.wide.common.model.Role;
public class ViewRole {

	private Role role;
	private List<Right> roleList;
	private String resnames;
	private String resids;
	private String offnames;
	private String offids;
	private String rightids;
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Right> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Right> roleList) {
		this.roleList = roleList;
	}
	public String getResnames() {
		return resnames;
	}
	public void setResnames(String resnames) {
		this.resnames = resnames;
	}
	public String getResids() {
		return resids;
	}
	public void setResids(String resids) {
		this.resids = resids;
	}
	public String getOffnames() {
		return offnames;
	}
	public void setOffnames(String offnames) {
		this.offnames = offnames;
	}
	public String getOffids() {
		return offids;
	}
	public void setOffids(String offids) {
		this.offids = offids;
	}
	public String getRightids() {
		return rightids;
	}
	public void setRightids(String rightids) {
		this.rightids = rightids;
	}
	
	
	
}
