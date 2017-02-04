package com.wide.baseproject.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.wide.base.MailKit;
import com.wide.util.PathUtil;
import com.jfinal.core.Controller;

public class FileController extends Controller{
	/**
	 * @author cg
	 * 进入文件管理
	 * */
	public void add(){
		render("fileSys.jsp");
	}
	/**
	 * @author cg
	 * 邮件发送
	 * */
	public void addmailinfo(){
		render("sendEmail.jsp");
	}
	/**
	 * @author cg
	 * 发送邮件
	 * @param toMail        收件人地址
     * @param ccMail        抄送地址
     * @param subject       发送主题
     * @param content       发送内容
	 * */
	 public void sendmail(){
		 //调用初始化函数
		 String toMail = getPara("toMail");
		 String ccMail = getPara("ccMail");
		 String subject = getPara("subject");
		 String content = getPara("content");
		
		 try {
			content = PathUtil.replaceMultimedia(getRequest(),URLDecoder.decode(content, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 MailKit.config();
		 boolean bl = MailKit.sendAndCc(toMail,ccMail,subject,content);
		 if(bl){
			 renderJson("2");
		 }else{
			 renderJson("1");
		 }
		 
	 }
}
