package com.wide.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.wide.common.model.CaptchaUsernamePasswordToken;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;


public class CaptchaFormAuthenticationInterceptor extends FormAuthenticationFilter implements Interceptor {

    private String captchaParam = "captcha";

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    protected AuthenticationToken createToken(HttpServletRequest request) {
        String username = getUsername(request)==null?"":getUsername(request);
        String password = getPassword(request)==null?"":getPassword(request);
        String captcha = getCaptcha(request)==null?"":getCaptcha(request);
        String pwd = new Sha256Hash(password, username, 1024).toBase64(); // 将用户输入密码与用户名salt加密
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, pwd, rememberMe, host, captcha);
    }
    
    public void intercept(Invocation ai) {
        HttpServletRequest request = ai.getController().getRequest();
        AuthenticationToken authenticationToken = createToken(request);
        request.setAttribute("shiroToken", authenticationToken);
        ai.invoke();
    }

}
