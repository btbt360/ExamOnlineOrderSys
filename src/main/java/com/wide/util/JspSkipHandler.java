package com.wide.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;
/**
 * 拦截jsp后缀
 * */
public class JspSkipHandler extends Handler {  
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {  
        int index = target.lastIndexOf(".jsp");  
        if (index != -1)  
        target = target.substring(0, index);
        next.handle(target, request, response, isHandled);  
    }  
}