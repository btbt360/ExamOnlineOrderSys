package com.wide.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * Java 日期时间工具类
 */
public class DownUtil {

	

	/**  
     * 文件下载   公用方法
     * @param response  
     * @param str  
     */  
    public static void downFile(HttpServletResponse response, String str,String fileName) {
        try {
            String path = str;
            File file = new File(path);
            if (file.exists()) {
                InputStream ins = new FileInputStream(path);
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.setContentType("application/x-download");// 设置response内容的类型
                response.setHeader(   
                        "Content-disposition",
                        "attachment;filename="
                                + URLEncoder.encode(fileName, "UTF-8"));// 设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[2048];
                // 开始向网络传输文件流 
                while ((bytesRead = bins.read(buffer, 0, buffer.length)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                }
                bouts.flush();// 这里一定要调用flush()方法
                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            } else {
                //response.sendRedirect("../error.jsp");
            }
        } catch (IOException e) {
            System.out.println("下载出错了！");
        }
}

	 
}
