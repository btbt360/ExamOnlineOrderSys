package com.wide.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * 邮件发送模板
 *
 * @author  cg
 * @since   1.0
 */
public class MailTemplate {
 
    private String templateBody;
    private List<String> ccMails = new ArrayList<String>();
    private List<String> toMails = new ArrayList<String>();
    private List<String> fileList = new ArrayList<String>();
     
    public MailTemplate() {
    }
     
    public MailTemplate(String templetPath, String toMail, String ccMail) throws IOException {
         
        loadTemplet(templetPath);
         
        if(isEmail(toMail)){
            this.toMails.add(toMail);
        }
         
        if(isEmail(ccMail)){
            this.ccMails.add(ccMail);
        }
    }
     
    /**
     * 添加附件
     * @param filePath
     * @return
     */
    public MailTemplate addFile(String filePath){
    	File file=new File(filePath);   
        if(file.exists()){
            this.fileList.add(filePath);
        }
        return this;
    }
     
    /**
     * 添加附件列表
     * @param filePath
     * @return
     */
    public MailTemplate addFiles(List<String> files){
        if(null != files && files.size() > 0){
            this.fileList.addAll(files);
        }
        return this;
    }
     
    /**
     * 发送给谁
     * @param toMail
     * @return
     */
    public MailTemplate toMail(String ... toMails){
        if(null != toMails && toMails.length > 0){
            for(String toMail : toMails){
                if(isEmail(toMail)){
                    this.toMails.add(toMail);
                }
            }
        }
        return this;
    }
     
    public MailTemplate ccMail(String... ccMails){
        if(null != ccMails && ccMails.length > 0){
            for(String ccMail : ccMails){
                if(isEmail(ccMail)){
                    this.ccMails.add(ccMail);
                }
            }
        }
        return this;
    }
     
    /**
     * 加载模板
     * @param templetPath
     * @return
     * @throws IOException
     */
    public MailTemplate loadTemplet(String templetPath) throws IOException {
        InputStream input = null;
        InputStreamReader read = null;
        BufferedReader reader = null;
 
        if (!new File(templetPath).exists()) {
            templateBody = "";
        }
        try {
            input = new FileInputStream(templetPath);
            read = new InputStreamReader(input, "UTF-8");
            reader = new BufferedReader(read);
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            templateBody = result.substring(result.indexOf("<html>"));
        } catch (Exception e) {
            e.printStackTrace();
            templateBody = "";
        } finally {
            reader.close();
            read.close();
            input.close();
        }
         
        return this;
    }
 
    @Override
    public String toString() {
        return this.templateBody;
    }
 
    public String getToMail() {
        if(null != toMails && toMails.size() > 0){
        	StringUtils.join(toMails, ",").substring(1);
        }
        return null;
    }
 
    public String getCcMail() {
        if(null != ccMails && ccMails.size() > 0){
        	StringUtils.join(ccMails, ",").substring(1);
        }
        return null;
    }
     
    public List<String> getFileList() {
        return fileList;
    }
     
    /**
     * 检测邮箱地址是否合法
     * @param email
     * @return true合法 false不合法
     */
    public boolean isEmail(String email){
          if (null==email || "".equals(email)) return false;  
//        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
          Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
          Matcher m = p.matcher(email);
          return m.matches();
         }
}