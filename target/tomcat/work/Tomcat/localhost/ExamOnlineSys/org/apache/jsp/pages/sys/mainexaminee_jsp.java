/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2017-01-14 10:32:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages.sys;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class mainexaminee_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
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
      if (_jspx_meth_c_005fimport_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t<div style=\"margin-left: 10%; margin-right: 10%\" class=\"row\">\r\n");
      out.write("\t\t<div class=\".col-xs-12 .col-sm-12 .col-md-12 .col-lg-12\">\r\n");
      out.write("\t\t\t<!-- block -->\r\n");
      out.write("\t\t\t<div class=\"block\">\r\n");
      out.write("\t\t\t\t<div class=\"navbar navbar-inner block-header\">\r\n");
      out.write("\t\t\t\t\t<div class=\"muted pull-left\">\r\n");
      out.write("\t\t\t\t\t考生考试成绩 <small>统计图</small>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t\t\t <div class=\"span12\">\r\n");
      out.write("                              <div id=\"hero-graph\" style=\"height: 230px;\"></div>\r\n");
      out.write("                      </div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- /block -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"row\" style=\"margin-left: 10%; margin-right: 10%\">\r\n");
      out.write("\t\t<div class=\"pull-left\" style=\"width: 30%\">\r\n");
      out.write("\t\t\t<div class=\"col-md-4\">\r\n");
      out.write("\t\t\t\t<!-- block -->\r\n");
      out.write("\t\t\t\t<div class=\"block\">\r\n");
      out.write("\t\t\t\t\t<div class=\"navbar navbar-inner block-header\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"muted pull-left\">快捷入口</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t\t\t\t<table width=\"100%\" border=\"0\">\r\n");
      out.write("\t\t\t\t\t\t\t<tr height=\"100px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"33%\"><div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"../examinee/addJoinExam\"><img alt=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tsrc=\"../static/img/cjks.png\" width=\"40px\" style=\"margin-bottom: 4px;\" class=\"img-rounded\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<br/>参加考试\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"33%\"><div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"../achievement/addExamRecordList\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<img alt=\"\" src=\"../static/img/wdcj.png\" width=\"40px\" style=\"margin-bottom: 4px;\" class=\"img-rounded\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<br/>我的成绩\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"33%\"><div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"../exercise/addExercise\"><img alt=\"\" style=\"margin-bottom: 4px;\"  src=\"../static/img/lxgl.png\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\twidth=\"40px\" class=\"img-rounded\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<br/>练习管理\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr height=\"100px\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"33%\"><div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"../errorsubject/errorpritics\"><img alt=\"\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tsrc=\"../static/img/ctlx.png\" width=\"40px\" style=\"margin-bottom: 4px;\" class=\"img-rounded\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<br/>错题练习\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"33%\"><div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"../user/addInfo\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<img alt=\"\" src=\"../static/img/grxx.png\" width=\"40px\" style=\"margin-bottom: 4px;\" class=\"img-rounded\"></a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<br/>个人信息\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td width=\"33%\"><div align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /block -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"pull-left\" style=\"margin-left: 3%; width: 67%\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"col-md-9\">\r\n");
      out.write("\t\t\t\t<!-- block -->\r\n");
      out.write("\t\t\t\t<div class=\"block\">\r\n");
      out.write("\t\t\t\t\t<div class=\"navbar navbar-inner block-header\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"muted pull-left\">正在考试中</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"muted pull-right\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/examinee/addJoinExam\">更多..</a></div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t\t\t\t\t<table class=\"table table-bordered\">\r\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<!-- /block -->\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<!-- block -->\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function() {\r\n");
      out.write("\t\t\t$(\".flagbtn\").click(function(){\r\n");
      out.write("\t\t\t\tvar id=this.id;\r\n");
      out.write("\t\t\t\tvar url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/bbs/updateis_check\";\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\ttype : 'post',\r\n");
      out.write("\t\t\t\t\turl : url,\r\n");
      out.write("\t\t\t\t\tdata : {\r\n");
      out.write("\t\t\t\t\t\tid : id\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\t\tdataType : 'json',\r\n");
      out.write("\t\t\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\t\t\t$(\"#\"+id).hide();\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\terror : function() {\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\".img-rounded\").mouseover(function(){\r\n");
      out.write("\t\t\t\t$(this).addClass(\"hoverimg\");\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\".img-rounded\").mouseout(function(){\r\n");
      out.write("\t\t\t\t$(this).removeClass(\"hoverimg\");\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// Morris Line Chart\r\n");
      out.write("\t\t\tvar tax_data = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${chartlist}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tMorris.Line({\r\n");
      out.write("\t\t\t\telement : 'hero-graph',\r\n");
      out.write("\t\t\t\tdata : eval('(' + tax_data + ')'),\r\n");
      out.write("\t\t\t\txkey : 'kaoshixStr',\r\n");
      out.write("\t\t\t\txLabels : \"month\",\r\n");
      out.write("\t\t\t\tykeys : [ 'noqualified','qualified','excellent'],\r\n");
      out.write("\t\t\t\tlabels : [ '不及格','及格','优秀']\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t</script>\r\n");
      if (_jspx_meth_c_005fimport_005f1(_jspx_page_context))
        return;
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
    // /pages/sys/mainexaminee.jsp(4,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fimport_005f0.setUrl("/pages/include/pageNavigation.jsp");
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

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /pages/sys/mainexaminee.jsp(84,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/pages/sys/mainexaminee.jsp(84,8) '${examList}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${examList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /pages/sys/mainexaminee.jsp(84,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("exam");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<tr style=\"height:42px !important;\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<td width=\"50%\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${exam.name }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<td  width=\"30%\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${exam.duration }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("分钟</td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<td  width=\"20%\"><a href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("/examinee/addJoinExam\">开始考试</a></td>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t</tr>\r\n");
          out.write("\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fimport_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_005fimport_005f1 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_005fimport_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fimport_005f1.setParent(null);
    // /pages/sys/mainexaminee.jsp(142,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fimport_005f1.setUrl("/pages/include/pageFoot.jsp");
    int[] _jspx_push_body_count_c_005fimport_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fimport_005f1 = _jspx_th_c_005fimport_005f1.doStartTag();
      if (_jspx_th_c_005fimport_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fimport_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fimport_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fimport_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.reuse(_jspx_th_c_005fimport_005f1);
    }
    return false;
  }
}
