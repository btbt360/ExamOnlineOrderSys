/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2017-01-22 02:10:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages.resource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class itembankList_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!-- block -->\r\n");
      out.write("\t<div class=\"block\">\r\n");
      out.write("\t\t<div class=\"navbar navbar-inner block-header\">\r\n");
      out.write("\t\t\t<div class=\"muted pull-left\">\r\n");
      out.write("\t\t\t\t<ul class=\"breadcrumb\">\r\n");
      out.write("\t\t\t\t\t<i class=\"icon-chevron-left hide-sidebar\"> <a href='#'\r\n");
      out.write("\t\t\t\t\t\ttitle=\"Hide Sidebar\" rel='tooltip'>&nbsp;</a></i>\r\n");
      out.write("\t\t\t\t\t<i class=\"icon-chevron-right show-sidebar\" style=\"display: none;\">\r\n");
      out.write("\t\t\t\t\t\t<a href='#' title=\"Show Sidebar\" rel='tooltip'>&nbsp;</a>\r\n");
      out.write("\t\t\t\t\t</i>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">资源管理</a> <span class=\"divider\">/</span></li>\r\n");
      out.write("\t\t\t\t\t<li class=\"active\">题库管理</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"row-fluid\">\r\n");
      out.write("\t\t\t<div class=\"block\" style=\"border: 0px;\">\r\n");
      out.write("\t\t\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t\t\t<ul class=\"nav nav-tabs\">\r\n");
      out.write("\t\t\t\t\t\t<li class=\"active\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/item/add\">题库列表</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/item/addinfo\">题库添加</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/item/importExcel\">试题导入</a></li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<!-- 删除用户提示 -->\r\n");
      out.write("\t\t\t\t\t<div class=\"span12\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"alert alert-success\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"margin-right: 8%;display: none; text-align: center;\" id=\"successmessage\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"close\" onclick=\"$('#successmessage').hide();\">&times;</button>\r\n");
      out.write("\t\t\t\t\t\t\t<strong><span id=\"messagess\"></span></strong>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"alert alert-error\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"margin-right: 8%;display: none; text-align: center;\" id=\"errormessage\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"close\" onclick=\"$('#errormessage').hide();\">&times;</button>\r\n");
      out.write("\t\t\t\t\t\t\t<strong><span id=\"messageee\"></span></strong>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/case/exportCase\" method=\"post\"\r\n");
      out.write("\t\t\t\t\t\tid=\"subform\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"span12 text-center\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"span3\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"control-label\" for=\"name\">题库名称：<input\r\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"input-medium focused\" id=\"name\" name=\"name\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype=\"text\" /></label> \r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"span4\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"control-label\" for=\"subjectid\">科目名称：\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input id=\"citySel\" type=\"text\" readonly value=\"\" style=\"width:120px;\" onclick=\"showMenu(); return false;\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input name=\"subjectid\" id =\"subjectid\" type=\"hidden\" value=\"\" >\r\n");
      out.write("\t\t\t\t\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"span4\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label class=\"control-label\" for=\"questiontype\">试题类型：\r\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"m-wrap\" id=\"questiontype\" name=\"questiontype\" placeholder=\"请选择试题类型！\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<option value='0'>请选择试题类型</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t</label> \r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"span1 text-right\" >\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"btn btn-medium btn-primary\" type=\"button\"\r\n");
      out.write("\t\t\t\t\t\t\tid=\"query\">查询</button>\r\n");
      out.write("<!-- \t\t\t\t\t\t\t<button class=\"btn btn-medium btn-primary\" type=\"button\" -->\r\n");
      out.write("<!-- \t\t\t\t\t\t\t\tid=\"export\">导出</button> -->\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"subpages\" name=\"subpages\" /><input\r\n");
      out.write("\t\t\t\t\t\t\ttype=\"hidden\" id=\"subrp\" name=\"subrp\" />\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<table id=\"userList\" class=\"table table-striped table-bordered\">\r\n");
      out.write("\t\t\t\t\t\t<thead>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>题库名称</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>科目名称</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>总题数</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>试题类型</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>选择次数</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>是否启用</th>\r\n");
      out.write("\t\t\t\t\t\t\t\t<th>操作</th>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t\t\t\t<!-- tbody是必须的 -->\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"menuContent\" class=\"menuContent\" style=\"display:none; position: absolute;background-color: #f5f5f5;border: 1px solid #ccc;\">\r\n");
      out.write("\t\t<ul id=\"otree\" class=\"ztree\"></ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function edit(ids) {\r\n");
      out.write("\tlocation.href = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/item/addinfo?id=\" + ids;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function del(ids) {\r\n");
      out.write("\tif (confirm(\"确定要删除该题库？\")) {\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\ttype : 'post',\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/item/del?id=' + ids,\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tdataType : 'json',\r\n");
      out.write("\t\t\tsuccess : function(data) {\r\n");
      out.write("\t\t\t\tif (data.result == 1) {\r\n");
      out.write("\t\t\t\t\t$(\"#successmessage\").hide();\r\n");
      out.write("\t\t\t\t\t$(\"#errormessage\").show();\r\n");
      out.write("\t\t\t\t\t$(\"#messageee\").text(\"删除失败，请联系管理员！\");\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t$(\"#errormessage\").hide();\r\n");
      out.write("\t\t\t\t\t$(\"#successmessage\").show();\r\n");
      out.write("\t\t\t\t\t$(\"#messagess\").text(\"删除成功！\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treshcg();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("var subjectid = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subject.parentid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("var setting = {\r\n");
      out.write("\tview : {\r\n");
      out.write("\t\tdblClickExpand : false\r\n");
      out.write("\t},\r\n");
      out.write("\tasync : {\r\n");
      out.write("\t\tenable : true, //设置 zTree 是否开启异步加载模式\r\n");
      out.write("\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/subject/getSubjectTree\", //Ajax 获取数据的 URL 地址。\r\n");
      out.write("\t\tautoParam : [ \"id\", \"name\" ], //异步加载时需要自动提交父节点属性的参数。\r\n");
      out.write("\t\totherParam : { //Ajax 请求提交的静态参数键值对。\r\n");
      out.write("\t\t\t\"otherParam\" : \"zTreeAsyncTest\",\r\n");
      out.write("\t\t\t\"subjectid\" : subjectid\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tdataFilter : filter\r\n");
      out.write("\t//用于对 Ajax 返回数据进行预处理的函数。\r\n");
      out.write("\t},\r\n");
      out.write("\tcallback : {\r\n");
      out.write("\t\tonClick : onClick, //用于捕获节点被点击的事件回调函数\r\n");
      out.write("\t\tonAsyncSuccess : onAsyncSuccesso\r\n");
      out.write("\t}\r\n");
      out.write("};\r\n");
      out.write("var treeNodez;\r\n");
      out.write("\r\n");
      out.write("function filter(treeId, parentNode, childNodes) {\r\n");
      out.write("\tif (!childNodes)\r\n");
      out.write("\t\treturn null;\r\n");
      out.write("\tfor (var i = 0, l = childNodes.length; i < l; i++) {\r\n");
      out.write("\t\tchildNodes[i].name = childNodes[i].name.replace(/\\.n/g, '.');\r\n");
      out.write("\t}\r\n");
      out.write("\treturn childNodes;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function onAsyncSuccesso(event, treeId, treeNode, msg) {\r\n");
      out.write("\tvar treeObj = $.fn.zTree.getZTreeObj(\"otree\");\r\n");
      out.write("\tvar nodes = treeObj.getNodesByParam(\"parentId\", 0, null);\r\n");
      out.write("\tif (nodes.length > 0) {\r\n");
      out.write("\t\ttreeObj.expandNode(nodes[0], true, false, false);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function onClick(e, treeId, treeNode) {\r\n");
      out.write("\tvar zTree = $.fn.zTree.getZTreeObj(\"otree\"), nodes = zTree\r\n");
      out.write("\t\t\t.getSelectedNodes(), v = \"\";\r\n");
      out.write("\tvar ids = '';\r\n");
      out.write("\tnodes.sort(function compare(a, b) {\r\n");
      out.write("\t\treturn a.id - b.id;\r\n");
      out.write("\t});\r\n");
      out.write("\tfor (var i = 0, l = nodes.length; i < l; i++) {\r\n");
      out.write("\t\tv += nodes[i].name + \",\";\r\n");
      out.write("\t\tids += nodes[i].id + \",\";\r\n");
      out.write("\t}\r\n");
      out.write("\tif (v.length > 0)\r\n");
      out.write("\t\tv = v.substring(0, v.length - 1);\r\n");
      out.write("\tif (ids.length > 0)\r\n");
      out.write("\t\tids = ids.substring(0, ids.length - 1);\r\n");
      out.write("\t$(\"#citySel\").val(v);\r\n");
      out.write("\t$(\"#subjectid\").val(ids);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function showMenu() {\r\n");
      out.write("\tvar cityObj = $(\"#citySel\");\r\n");
      out.write("\tvar cityOffset = $(\"#citySel\").offset();\r\n");
      out.write("\t$(\"#menuContent\").css({\r\n");
      out.write("\t\tleft : cityOffset.left + \"px\",\r\n");
      out.write("\t\ttop : cityOffset.top + cityObj.outerHeight() + \"px\"\r\n");
      out.write("\t}).slideDown(\"fast\");\r\n");
      out.write("\t$(\"body\").bind(\"mousedown\", onBodyDown);\r\n");
      out.write("}\r\n");
      out.write("function hideMenu() {\r\n");
      out.write("\t$(\"#menuContent\").fadeOut(\"fast\");\r\n");
      out.write("\t$(\"body\").unbind(\"mousedown\", onBodyDown);\r\n");
      out.write("}\r\n");
      out.write("function onBodyDown(event) {\r\n");
      out.write("\tif (!(event.target.id == \"menuBtn\" || event.target.id == \"menuContent\" || $(\r\n");
      out.write("\t\t\tevent.target).parents(\"#menuContent\").length > 0)) {\r\n");
      out.write("\t\thideMenu();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function checkinfo(itembid){\r\n");
      out.write("\tlocation.href = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/questions/add?itembid=\" + itembid;\r\n");
      out.write("}\r\n");
      out.write("$(document).ready(\r\n");
      out.write("\t\tfunction() {\r\n");
      out.write("\t\t\t$.fn.zTree.init($(\"#otree\"), setting);\r\n");
      out.write("\t\toTable = $('#userList').initDT({\r\n");
      out.write("\t\t\tserverSide : true,\r\n");
      out.write("\t\t\t\"sAjaxSource\" : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/item/find\"\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t$(\"#query\").click(function() {\r\n");
      out.write("\t\t\treshcg();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#export\").click(function() {\r\n");
      out.write("\t\t\t$(\"#subpages\").val(oTable.getCurrentPage());\r\n");
      out.write("\t\t\t$(\"#subrp\").val(oTable.getPageSize());\r\n");
      out.write("\t\t\t$(\"#subform\").submit();\r\n");
      out.write("\t\t});\r\n");
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction reshcg() {\r\n");
      out.write("\t\tvar name = $('#name').val();\r\n");
      out.write("\t\tvar subjectid = $('#subjectid').val();\r\n");
      out.write("\t\tvar questiontype = $('#questiontype').val();\r\n");
      out.write("\t\tvar oSettings = [ {\r\n");
      out.write("\t\t\t\"name\" : \"name\",\r\n");
      out.write("\t\t\t\"value\" : name\r\n");
      out.write("\t\t}, {\r\n");
      out.write("\t\t\t\"name\" : \"subjectid\",\r\n");
      out.write("\t\t\t\"value\" : subjectid\r\n");
      out.write("\t\t}, {\r\n");
      out.write("\t\t\t\"name\" : \"questiontype\",\r\n");
      out.write("\t\t\t\"value\" : questiontype\r\n");
      out.write("\t\t} ];\r\n");
      out.write("\t\toTable.gridSearch(this, oSettings);\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
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
    // /pages/resource/itembankList.jsp(4,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /pages/resource/itembankList.jsp(60,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("dict");
    // /pages/resource/itembankList.jsp(60,10) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/pages/resource/itembankList.jsp(60,10) '${dictlist}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${dictlist}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t<option value='");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dict.dictkey}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write('\'');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dict.dictvalue}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t");
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
    // /pages/resource/itembankList.jsp(245,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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