package com.wide.interceptor;

import com.wide.config.UserToken;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor  implements Interceptor {


	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller controller = inv.getController();
	    UserToken loginUser = controller.getSessionAttr("userToken");
	    if (loginUser != null)
	    	if(loginUser.getVuser().getUser()!=null)
	    		inv.invoke();
	    	else
	    		controller.redirect("/");
	    else
	      controller.redirect("/");
	}

}
