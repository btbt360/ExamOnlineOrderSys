/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2017-02-04 07:08:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages.bespeak;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class usersubscribe_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      if (_jspx_meth_c_005fimport_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<!-- block -->\r\n");
      out.write("\t<div class=\"block\">\r\n");
      out.write("\t\r\n");
      out.write("\t\t<div class=\"navbar navbar-inner block-header\">\r\n");
      out.write("\t\t\t<div class=\"muted pull-left\">\r\n");
      out.write("\t\t\t\t<ul class=\"breadcrumb\">\r\n");
      out.write("\t\t\t\t\t<i class=\"icon-chevron-left hide-sidebar\"> <a href='#'\r\n");
      out.write("\t\t\t\t\t\ttitle=\"Hide Sidebar\" rel='tooltip'>&nbsp;</a></i>\r\n");
      out.write("\t\t\t\t\t<i class=\"icon-chevron-right show-sidebar\" style=\"display: none;\">\r\n");
      out.write("\t\t\t\t\t\t<a href='#' title=\"Show Sidebar\" rel='tooltip'>&nbsp;</a>\r\n");
      out.write("\t\t\t\t\t</i>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"#\">我的考试</a> <span class=\"divider\">/</span></li>\r\n");
      out.write("\t\t\t\t\t<li class=\"active\">预约考试</li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"row-fluid\">\r\n");
      out.write("\t\t\t<div class=\"block\" style=\"border: 0px;\">\r\n");
      out.write("\t\t\t\t<div class=\"block-content collapse in\">\r\n");
      out.write("\t\t\t\t<!--  \r\n");
      out.write("\t\t\t\t\t<ul class=\"nav nav-tabs\">\r\n");
      out.write("\t\t\t\t\t\t<li class=\"active\"><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/exam/addExam\">日程预约考试</a></li>\r\n");
      out.write("\t\t\t\t\t\t<li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/exam/addExamInfo\">列表预约考试</a></li>\r\n");
      out.write("\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t-->\r\n");
      out.write("\t\t\t\t\t<!-- 取消预约提示 -->\r\n");
      out.write("\t\t\t\t\t<div class=\"span12\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"alert alert-success\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"margin-right: 8%;display: none; text-align: center;\" id=\"successmessage\">\r\n");
      out.write("\t\t\t\t\t\t\t<button class=\"close\" onclick=\"$('#successmessage').hide();\">&times;</button>\r\n");
      out.write("\t\t\t\t\t\t\t<strong><span id=\"messagess\"></span></strong>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"span12\" id=\"content\">\r\n");
      out.write("                <div class=\"row-fluid\">\r\n");
      out.write("                    <!-- block -->\r\n");
      out.write("                        <div class=\"block-content collapse in\" >\r\n");
      out.write("                            <div class=\"span12\">\r\n");
      out.write("                                <div id=\"myAlert\" class=\"modal hide\">\r\n");
      out.write("                                    <div class=\"modal-header\">\r\n");
      out.write("                                        <button data-dismiss=\"modal\" class=\"close\" type=\"button\">&times;</button>\r\n");
      out.write("                                        <h3>考试信息</h3>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"modal-body\">\r\n");
      out.write("                                        <div class=\"control-group\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label class=\"control-label\"><b>考试名称：</b></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span id =\"examname\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"control-group\" style=\"padding-top:2.5%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label class=\"control-label\"><b>考试时间：</b></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span id =\"examtime\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"control-group\" style=\"padding-top:2.5%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label class=\"control-label\"><b>考试时长：</b></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span id =\"exambetten\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"control-group\" style=\"padding-top:2.5%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label class=\"control-label\"><b>预约人数：</b></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t考试人员总数：<span id =\"total\"></span><br />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t已经预约考试人数：<font color=\"green\"><span id =\"alreadynum\"></span></font><br />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t考试剩余预约人数：<font color=\"orange\"><span id =\"synum\"></span></font><br />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"control-group\" style=\"padding-top:2.5%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label class=\"control-label\"><b>是否已经预约：</b></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span id =\"flag\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"control-group\" style=\"padding-top:2.5%;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<label class=\"control-label\"><b>考试状态：</b></label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"controls\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<span id =\"flagcg\"></span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"hidden\" id =\"examid\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                    <div class=\"modal-footer\">\r\n");
      out.write("                                        <a data-dismiss=\"modal\" class=\"btn btn-primary\" href=\"#\" id=\"submityuyue\">预约考试</a>\r\n");
      out.write("                                        <a data-dismiss=\"modal\" class=\"btn btn-primary\" href=\"#\" id=\"jieshuyuyue\" >解除预约</a>\r\n");
      out.write("                                        <a data-dismiss=\"modal\" class=\"btn\" href=\"#\">关闭</a>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div id='calendar'></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <!-- /block -->\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\t$(document).ready(function() {\r\n");
      out.write("\t\tvar vclist = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${vclist}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t$(\"#jieshuyuyue\").hide();\r\n");
      out.write("\t\tvar message =\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\tif(message=='success'){\r\n");
      out.write("\t\t\t$(\"#successmessage\").show();\r\n");
      out.write("\t\t\t$(\"#messagess\").text(\"预定成功\");\r\n");
      out.write("\t\t}else if(message=='qxsuccess'){\r\n");
      out.write("\t\t\t$(\"#successmessage\").show();\r\n");
      out.write("\t\t\t$(\"#messagess\").text(\"取消预定成功\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t // Easy pie charts\r\n");
      out.write("        var calendar = $('#calendar').fullCalendar({\r\n");
      out.write("            buttonText: {\r\n");
      out.write("                today: '今天',\r\n");
      out.write("                month: '月视图',\r\n");
      out.write("                week: '周视图',\r\n");
      out.write("                day: '日视图'\r\n");
      out.write("            },\r\n");
      out.write("            allDayText: \"全天\",\r\n");
      out.write("            timeFormat: {\r\n");
      out.write("                '': 'H:mm{-H:mm}'\r\n");
      out.write("            },\r\n");
      out.write("            weekMode: \"variable\",\r\n");
      out.write("            columnFormat: {\r\n");
      out.write("                month: 'dddd',\r\n");
      out.write("                week: 'dddd M-d',\r\n");
      out.write("                day: 'dddd M-d'\r\n");
      out.write("            },\r\n");
      out.write("            titleFormat: {\r\n");
      out.write("                month: 'yyyy年 MMMM月',\r\n");
      out.write("                week: \"[yyyy年] MMMM月d日 { '&#8212;' [yyyy年] MMMM月d日}\",\r\n");
      out.write("                day: 'yyyy年 MMMM月d日 dddd'\r\n");
      out.write("            },\r\n");
      out.write("            monthNames: [\"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\"],\r\n");
      out.write("            dayNames: [\"星期天\", \"星期一\", \"星期二\", \"星期三\", \"星期四\", \"星期五\", \"星期六\"],\r\n");
      out.write("            header: {\r\n");
      out.write("                left: 'prev,next',\r\n");
      out.write("                center: 'title',\r\n");
      out.write("                right: 'month,basicWeek,basicDay'\r\n");
      out.write("            },\r\n");
      out.write("            //设置是否可被单击或者拖动选择\r\n");
      out.write("            selectable: true,\r\n");
      out.write("            //点击或者拖动选择时，是否显示时间范围的提示信息，该属性只在agenda视图里可用\r\n");
      out.write("            selectHelper: true,\r\n");
      out.write("            //点击或者拖动选中之后，点击日历外的空白区域是否取消选中状态 true为取消 false为不取消，只有重新选择时才会取消\r\n");
      out.write("            unselectAuto: true,\r\n");
      out.write("           \r\n");
      out.write("            droppable: false, // this allows things to be dropped onto the calendar !!!\r\n");
      out.write("            drop: function(date, allDay) { // this function is called when something is dropped\r\n");
      out.write("\r\n");
      out.write("                // retrieve the dropped element's stored Event Object\r\n");
      out.write("                var originalEventObject = $(this).data('eventObject');\r\n");
      out.write("\r\n");
      out.write("                // we need to copy it, so that multiple events don't have a reference to the same object\r\n");
      out.write("                var copiedEventObject = $.extend({}, originalEventObject);\r\n");
      out.write("\r\n");
      out.write("                // assign it the date that was reported\r\n");
      out.write("                copiedEventObject.start = date;\r\n");
      out.write("                copiedEventObject.allDay = allDay;\r\n");
      out.write("\r\n");
      out.write("                // render the event on the calendar\r\n");
      out.write("                // the last `true` argument determines if the event \"sticks\" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)\r\n");
      out.write("                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);\r\n");
      out.write("\r\n");
      out.write("                // is the \"remove after drop\" checkbox checked?\r\n");
      out.write("                if ($('#drop-remove').is(':checked')) {\r\n");
      out.write("                    // if so, remove the element from the \"Draggable Events\" list\r\n");
      out.write("                    $(this).remove();\r\n");
      out.write("                }\r\n");
      out.write("\r\n");
      out.write("            },\r\n");
      out.write("            editable: true,\r\n");
      out.write("            //月视图下日历格子宽度和高度的比例\r\n");
      out.write("            aspectRatio: 1.35,\r\n");
      out.write("            //月视图的显示模式，fixed：固定显示6周高；liquid：高度随周数变化；variable: 高度固定\r\n");
      out.write("            weekMode: 'liquid',\r\n");
      out.write("            //初始化时的默认视图，month、agendaWeek、agendaDay\r\n");
      out.write("            defaultView: 'month',\r\n");
      out.write("            //agenda视图下是否显示all-day\r\n");
      out.write("            allDaySlot: true,\r\n");
      out.write("            //agenda视图下all-day的显示文本\r\n");
      out.write("            allDayText: '全天',\r\n");
      out.write("            //agenda视图下两个相邻时间之间的间隔\r\n");
      out.write("            slotMinutes: 30,\r\n");
      out.write("            //区分工作时间\r\n");
      out.write("            businessHours: true,\r\n");
      out.write("            //非all-day时，如果没有指定结束时间，默认执行120分钟\r\n");
      out.write("            defaultEventMinutes: 120,\r\n");
      out.write("            //设置为true时，如果数据过多超过日历格子显示的高度时，多出去的数据不会将格子挤开，而是显示为 +...more ，点击后才会完整显示所有的数据\r\n");
      out.write("            eventLimit: true,\r\n");
      out.write("            events:  JSON.parse(vclist), // US Holidays\r\n");
      out.write("            eventMouseover: function(event) {\r\n");
      out.write("                //do something here...\r\n");
      out.write("                console.log('鼠标经过 ...');\r\n");
      out.write("                console.log('eventMouseover被执行，选中Event的title属性值为：', event.title);\r\n");
      out.write("                // ...\r\n");
      out.write("            },\r\n");
      out.write("            eventMouseout: function(event) {\r\n");
      out.write("                //do something here...\r\n");
      out.write("                console.log('eventMouseout被执行，选中Event的title属性值为：', event.title);\r\n");
      out.write("                console.log('鼠标离开 ...');\r\n");
      out.write("                // ...\r\n");
      out.write("            },\r\n");
      out.write("            eventClick: function(event) {\r\n");
      out.write("            \t$.ajax({\r\n");
      out.write("        \t\t\ttype : 'post',\r\n");
      out.write("        \t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/bespeak/getUsersubscribe?id=' + event.id,\r\n");
      out.write("        \t\t\tcache : false,\r\n");
      out.write("        \t\t\tdataType : 'json',\r\n");
      out.write("        \t\t\tsuccess : function(data) {\r\n");
      out.write("        \t\t\t\t$(\"#examname\").text(data.bespeakexam.EXAMNAME);\r\n");
      out.write("        \t\t\t\t$(\"#examtime\").text(data.bespeakexam.STARTTIME+' 至 '+data.bespeakexam.ENDTIME);\r\n");
      out.write("        \t\t\t\t$(\"#exambetten\").text(data.bespeakexam.DURATION+'分钟');\r\n");
      out.write("        \t\t\t\t$(\"#total\").text(data.bespeakexam.NUMBER);\r\n");
      out.write("        \t\t\t\t$(\"#alreadynum\").text(data.alreadynum);\r\n");
      out.write("        \t\t\t\t$(\"#synum\").text(data.bespeakexam.NUMBER-data.alreadynum);\r\n");
      out.write("        \t\t\t\t$(\"#examid\").val(event.id);\r\n");
      out.write("        \t\t\t\tif(data.flag){\r\n");
      out.write("        \t\t\t\t\t$(\"#jieshuyuyue\").show();\r\n");
      out.write("        \t\t\t\t\t$(\"#submityuyue\").hide();\r\n");
      out.write("            \t\t\t\t$(\"#flag\").html(\"<font color = 'green'>已经预约</font>\");\r\n");
      out.write("        \t\t\t\t}else{\r\n");
      out.write("        \t\t\t\t\t$(\"#jieshuyuyue\").hide();\r\n");
      out.write("        \t\t\t\t\t$(\"#submityuyue\").show();\r\n");
      out.write("        \t\t\t\t\t$(\"#flag\").html(\"<font color = 'blue' >未预约</font>\");\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("        \t\t\t\tif(data.flagcg){\r\n");
      out.write("        \t\t\t\t\t$(\"#flagcg\").html(\"<font color = 'green'>考试预定中</font>\");\r\n");
      out.write("        \t\t\t\t}else{\r\n");
      out.write("        \t\t\t\t\t$(\"#submityuyue\").hide();\r\n");
      out.write("        \t\t\t\t\t$(\"#jieshuyuyue\").hide();\r\n");
      out.write("        \t\t\t\t\t$(\"#flagcg\").html(\"<font color = 'red'>考试已开始不能预约！</font>\");\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("        \t\t\t\t\r\n");
      out.write("        \t\t\t\t$(\"#myAlert\").modal('show');\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("        \t\t});\r\n");
      out.write("                calendar.fullCalendar('removeEventSource', event);\r\n");
      out.write("            },\r\n");
      out.write("            eventResize: function(event, dayDelta, revertFunc) {\r\n");
      out.write("                //do something here...\r\n");
      out.write("                console.log(' --- start --- eventResize');\r\n");
      out.write("                console.log('eventResize被执行，Event的title属性值为：', event.title);\r\n");
      out.write("                if (dayDelta._days != 0) {\r\n");
      out.write("                    console.log('eventResize被执行，Event的start和end时间改变了：', dayDelta._days + '天！');\r\n");
      out.write("                } else if (dayDelta._milliseconds != 0) {\r\n");
      out.write("                    console.log('eventResize被执行，Event的start和end时间改变了：', dayDelta._milliseconds / 1000 + '秒！');\r\n");
      out.write("                } else {\r\n");
      out.write("                    console.log('eventResize被执行，Event的start和end时间没有改变！');\r\n");
      out.write("                }\r\n");
      out.write("                //revertFunc();\r\n");
      out.write("                console.log('--- end --- eventResize');\r\n");
      out.write("                // ...\r\n");
      out.write("            },\r\n");
      out.write("            eventDrop: function(event, dayDelta, revertFunc) {\r\n");
      out.write("                //do something here...\r\n");
      out.write("                console.log('eventDrop --- start ---');\r\n");
      out.write("                console.log('eventDrop被执行，Event的title属性值为：', event.title);\r\n");
      out.write("                if (dayDelta._days != 0) {\r\n");
      out.write("                    console.log('eventDrop被执行，Event的start和end时间改变了：', dayDelta._days + '天！');\r\n");
      out.write("                } else if (dayDelta._milliseconds != 0) {\r\n");
      out.write("                    console.log('eventDrop被执行，Event的start和end时间改变了：', dayDelta._milliseconds / 1000 + '秒！');\r\n");
      out.write("                } else {\r\n");
      out.write("                    console.log('eventDrop被执行，Event的start和end时间没有改变！');\r\n");
      out.write("                }\r\n");
      out.write("                //revertFunc();\r\n");
      out.write("                console.log('eventDrop --- end ---');\r\n");
      out.write("                // ...\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\t\t$(\"#submityuyue\").click(function(){\r\n");
      out.write("\t\t\tvar num = $(\"#synum\").text();\r\n");
      out.write("\t\t\tif(num>0){\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t    \t\t\ttype : 'post',\r\n");
      out.write("\t    \t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/bespeak/savebespeakinfo?id=' + $(\"#examid\").val() + \"&flag=1\",\r\n");
      out.write("\t    \t\t\tcache : false,\r\n");
      out.write("\t    \t\t\tdataType : 'json',\r\n");
      out.write("\t    \t\t\tsuccess : function(data) {\r\n");
      out.write("\t    \t\t\t\talert(\"预约成功！\");\r\n");
      out.write("\t    \t\t\t\twindow.location.reload(true);\r\n");
      out.write("\t    \t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\talert(\"预定人员已满！\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#jieshuyuyue\").click(function(){\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("    \t\t\ttype : 'post',\r\n");
      out.write("    \t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${basepath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/bespeak/cancelbespeak?id=' + $(\"#examid\").val() + \"&flag=1\",\r\n");
      out.write("    \t\t\tcache : false,\r\n");
      out.write("    \t\t\tdataType : 'json',\r\n");
      out.write("    \t\t\tsuccess : function(data) {\r\n");
      out.write("    \t\t\t\talert(\"取消预约成功！\");\r\n");
      out.write("    \t\t\t\twindow.location.reload(true);\r\n");
      out.write("    \t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\r\n");
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
    // /pages/bespeak/usersubscribe.jsp(4,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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

  private boolean _jspx_meth_c_005fimport_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_005fimport_005f1 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_005fimport_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fimport_005f1.setParent(null);
    // /pages/bespeak/usersubscribe.jsp(313,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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