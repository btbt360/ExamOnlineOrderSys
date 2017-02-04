package com.wide.util;

import javax.servlet.http.HttpServletRequest;

import com.wide.config.Global;
import com.jfinal.kit.PropKit;

public class PathUtil {

	
	/**
	 * 替换图片视频音频路径
	 * @author cg
	 * @param HttpServletRequest request
	 * @param paragraphHtml上传的ckediterHmtl
	 * */
	public static String replaceMultimedia(HttpServletRequest request,String paragraphHtml){
		PropKit.use("sys.properties");
		//项目地址
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+Global.USERFILES_BASE_URL;
		//替换新串进入
		paragraphHtml = paragraphHtml.replace(PropKit.get("porjectbasedir"), basePath);
		
		return paragraphHtml;
		
	}
}
