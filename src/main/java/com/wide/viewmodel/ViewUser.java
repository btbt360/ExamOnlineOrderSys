package com.wide.viewmodel;


import java.util.Map;
import com.wide.common.model.User;

public class ViewUser {
	private User user;
	private Map<String, String> officenames;
	private Map<String, String> rolenames;
	private String officeids;
	private String offnames;
	private String roleids;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, String> getOfficenames() {
		return officenames;
	}

	public void setOfficenames(Map<String, String> officenames) {
		this.officenames = officenames;
	}

	public Map<String, String> getRolenames() {
		return rolenames;
	}

	public void setRolenames(Map<String, String> rolenames) {
		this.rolenames = rolenames;
	}

	public String getOfficeids() {
		return officeids;
	}

	public void setOfficeids(String officeids) {
		this.officeids = officeids;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public String getOffnames() {
		return offnames;
	}

	public void setOffnames(String offnames) {
		this.offnames = offnames;
	}

}
