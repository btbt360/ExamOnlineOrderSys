package com.wide.config;

import java.util.List;

import com.wide.common.model.Menu;
import com.wide.viewmodel.ViewUser;

public class UserToken {

	private ViewUser vuser;
	private List<Menu> menulist;

	
	public ViewUser getVuser() {
		return vuser;
	}

	public void setVuser(ViewUser vuser) {
		this.vuser = vuser;
	}

	public List<Menu> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<Menu> menulist) {
		this.menulist = menulist;
	}
	
	
	
}
