/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2016-12-29 15:18:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages.exercise;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class startExercise_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zh-cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>在线考试系统</title>\r\n");
      out.write("</head>\r\n");
      if (_jspx_meth_c_005fimport_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<body style=\"padding-top: 0px;\">\r\n");
      out.write("\t<div class=\"block\" id=\"info\" style=\"width: 95%; margin-left: 2.5%;\">\r\n");
      out.write("\t\t<div class=\"navbar navbar-inner block-header\">\r\n");
      out.write("\t\t\t<div class=\"muted pull-left\">开始练习</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t<div class=\"span11\">\r\n");
      out.write("\t\t\t\t<div class=\"span4 text-center\">\r\n");
      out.write("\t\t\t\t\t<label class=\"control-label\"><h4>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"label label-info\"><h4>练习人：</h4></span>&nbsp;&nbsp;");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</h4></label>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"block\" style=\"width: 95%; margin-left: 2.5%;\">\r\n");
      out.write("\t\t<div class=\"navbar navbar-inner block-header\">\r\n");
      out.write("\t\t\t<div class=\"muted pull-left\">试题编码：");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questions.code}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/exercise/startExercise\">\r\n");
      out.write("\t\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t\t<div class=\"span12\">\r\n");
      out.write("\t\t\t\t\t<div class=\"span2\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"span10\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questions.title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"span12\">\r\n");
      out.write("\t\t\t\t\t<div class=\"span4 text-left\" id=\"isanswer\"></div>\r\n");
      out.write("\t\t\t\t\t<div class=\"span6 text-right\">\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-medium btn-primary\" type=\"button\"\r\n");
      out.write("\t\t\t\t\t\t\tid=\"checkquestion\">查看答案</button>\r\n");
      out.write("\t\t\t\t\t\t&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-medium btn-primary\" type=\"submit\"\r\n");
      out.write("\t\t\t\t\t\t\tid=\"nextquestion\">下一题</button>\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-medium btn-primary\" type=\"submit\"\r\n");
      out.write("\t\t\t\t\t\t\tid=\"reloadquestion\">重新答题</button>\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"sort\" name=\"sort\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sort}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"anstr\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questions.questionanswer}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"exerciseid\" name=\"exerciseid\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${exerciseid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"flag\" name=\"flag\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${flag}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"span2\"></div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t\t<div class=\"span11\">\r\n");
      out.write("\t\t\t\t\t<table class=\"table table-bordered\" id=\"answerinfo\">\r\n");
      out.write("\t\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"20%\">答案：</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"80%\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questions.questionanswer}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>解答：</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questions.questionanswerinfo}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"questions\"></div>\r\n");
      out.write("\t<div id=\"answers\"></div>\r\n");
      out.write("\t<div class=\"span12 text-center\" style=\"margin-bottom: 2%;\">\r\n");
      out.write("\t\t<button type='button' class='btn btn-warning btn-large'\r\n");
      out.write("\t\t\tonclick='closewindows()'>关闭</button>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\t$('#answerinfo').hide();\r\n");
      out.write("\t\t$('#reloadquestion').hide();\r\n");
      out.write("\t\tvar flag = $(\"#flag\").val();\r\n");
      out.write("\t\tif(flag == 1){\r\n");
      out.write("\t\t\t$('#reloadquestion').show();\r\n");
      out.write("\t\t\t$('#nextquestion').hide();\r\n");
      out.write("\t\t\t$('#checkquestion').hide();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$('#reloadquestion').click(function(){\r\n");
      out.write("\t\t\t$('#sort').val(0);\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#checkquestion\").click(function(){\r\n");
      out.write("\t\t\t$('#answerinfo').show();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction ischeck(str) {\r\n");
      out.write("\t\tvar anstr = $(\"#anstr\").val();\r\n");
      out.write("\t\tvar questionsid = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questions.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\tif (anstr.indexOf(str) == -1) {\r\n");
      out.write("\t\t\t$(\"#isanswer\").empty();\r\n");
      out.write("\t\t\t$(\"#isanswer\").append(\r\n");
      out.write("\t\t\t\t\t\"&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'><h4>此题回答有误</h4></font>\");\r\n");
      out.write("\t\t\t$('#answerinfo').show();\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tuperror(questionsid);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction isanswer() {\r\n");
      out.write("\t\tvar anstr = $(\"#anstr\").val();\r\n");
      out.write("\t\tvar answertext = $(\"#answertext\").val();\r\n");
      out.write("\t\tvar questionsid = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${questions.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\tif (anstr.indexOf(answertext) == -1) {\r\n");
      out.write("\t\t\t$(\"#isanswer\").empty();\r\n");
      out.write("\t\t\t$(\"#isanswer\").append(\r\n");
      out.write("\t\t\t\t\t\"&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'><h4>此题回答有误</h4></font>\");\r\n");
      out.write("\t\t\t$('#answerinfo').show();\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tuperror(questionsid);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction uperror(questionsid) {\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : 'post',\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/errorsubject/getErrorUpdate?questionsid='\r\n");
      out.write("\t\t\t\t\t+ questionsid,\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tdataType : 'json',\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t//\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction clockclosewindle(i) {\r\n");
      out.write("\t\ti = i - 1\r\n");
      out.write("\t\tdocument.title = \"本窗口将在\" + i + \"秒后自动关闭!\";\r\n");
      out.write("\t\tif (i > 0)\r\n");
      out.write("\t\t\tsetTimeout(\"clockclosewindle();\", 1000);\r\n");
      out.write("\t\telse\r\n");
      out.write("\t\t\tclosewin();\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction closewin() {\r\n");
      out.write("\t\tself.opener = null;\r\n");
      out.write("\t\tself.close();\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction closewindows() {\r\n");
      out.write("\t\twindow.opener.reshcg();\r\n");
      out.write("\t\tclockclosewindle(3);\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fimport_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_005fimport_005f0 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_005fimport_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fimport_005f0.setParent(null);
    // /pages/exercise/startExercise.jsp(10,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fimport_005f0.setUrl("/pages/include/pageHead.jsp");
    int[] _jspx_push_body_count_c_005fimport_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fimport_005f0 = _jspx_th_c_005fimport_005f0.doStartTag();
      if (_jspx_th_c_005fimport_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fimport_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fimport_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fimport_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.reuse(_jspx_th_c_005fimport_005f0);
    }
    return false;
  }
}
