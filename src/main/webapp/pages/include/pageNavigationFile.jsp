<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${basepath}/static/frame/theme/bootstrap/css/bootstrap.min.css"  rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/treeTable/themes/vsStyle/treeTable.min.css"  rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/bootstrap/css/bootstrap-responsive.min.css"  rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/assets/styles.css"  rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"  rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/vendors/datepicker.css" rel="stylesheet" media="screen" >
        <link href="${basepath}/static/frame/theme/vendors/uniform.default.css" rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/vendors/chosen.min.css" rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/vendors/wysiwyg/bootstrap-wysihtml5.css" rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/vendors/jGrowl/jquery.jgrowl.css" rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/vendors/morris/morris.css" rel="stylesheet" media="screen">
        <link href="${basepath}/static/frame/theme/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
        <link href="${basepath}/static/css/style.css" rel="stylesheet" media="screen">
         <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="${basepath}/static/frame/theme/vendors/jquery-validation/lib/jquery.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.extend.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery-1.9.1.min.js"></script>
		<script src="${basepath}/ckeditor/ckeditor.js"></script>
        <script src="${basepath}/ckfinder/ckfinder.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
		<script src="${basepath}/ckfinder/plugins/gallery/colorbox/jquery.min.js"></script>
        <script src="${basepath}/static/frame/theme/bootstrap/js/bootstrap.min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/easypiechart/jquery.easy-pie-chart.js"></script>
        <script src="${basepath}/static/frame/theme/assets/scripts.js"></script>
        <script src="${basepath}/static/js/common.js"></script>
        <script src="${basepath}/static/frame/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"></script>
        <script src="${basepath}/static/frame/treeTable/jquery.treeTable.min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery-ui-1.10.3.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/fullcalendar/fullcalendar.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/fullcalendar/gcal.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/bootstrap-wysihtml5/lib/js/wysihtml5-0.3.0.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/bootstrap-wysihtml5/src/bootstrap-wysihtml5.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/tinymce/js/tinymce/tinymce.min.js" type="text/javascript" ></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery.uniform.min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/chosen.jquery.min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/bootstrap-datepicker.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/wysiwyg/wysihtml5-0.3.0.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/wysiwyg/bootstrap-wysihtml5.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/wizard/jquery.bootstrap.wizard.min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${basepath}/static/frame/theme/vendors/datatables/js/jquery.dataTables.min.js"></script>
        <script src="${basepath}/static/frame/theme/assets/form-validation.js"></script>
        <script src="${basepath}/static/frame/theme/assets/DT_bootstrap.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jGrowl/jquery.jgrowl.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/jquery.knob.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/raphael-min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/morris/morris.min.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.categories.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.pie.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.time.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.stack.js"></script>
        <script src="${basepath}/static/frame/theme/vendors/flot/jquery.flot.resize.js"></script>
<title>在线考试系统</title>
</head>
<body >
	<div class="navbar navbar-fixed-top" >
            <div class="navbar-inner" >
                <div class="container-fluid">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="#" style="margin-top: 0.5%;margin-bottom: 0.5%;"></a>
                    
                    <div class="nav-collapse collapse" style="margin-top: 0.5%;margin-bottom: 0.5%;">
                        <ul class="nav pull-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
									<i class="icon-user"></i>欢迎您登录：${userToken.vuser.user.name}<i class="caret"></i>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a tabindex="-1" href="${basepath}/user/addpass">修改密码</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <a tabindex="-1" href="${basepath}/delSession">注销</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
                            <li class="dropdown">
                                <a href="${basepath}/user">首页</a>
                            </li>
                            <c:forEach items="${userToken.menulist}" var="menu" varStatus="stat0">
                            <c:if test="${menu.parentId==''}">
                            <li class="dropdown">
                                <a href="${basepath}${menu.href}" data-toggle="dropdown" class="dropdown-toggle">${menu.name}<b class="caret"></b></a>
                                <ul class="dropdown-menu" id="menu_${stat0.index+1}">
                               	 <c:forEach items="${userToken.menulist}" var="menusubtwo" varStatus="stat1">
                               	 <c:if test="${menusubtwo.parentId==menu.id}">
                                    <li>
                                    	<a href="${basepath}${menusubtwo.href}">${menusubtwo.name}</a>
                                    	<!-- 开启三层
                                        <ul class="dropdown-menu sub-menu"><i class="icon-arrow-right"></i>
                                        	<c:forEach items="${userToken.menulist}" var="menusubthree" varStatus="stat2">
                                        	<c:if test="${menusubthree.parentId==menusubtwo.id}">
                                            <li>
                                                <a href="#">${menusubthree.name}</a>
                                            </li>
                                            </c:if>
                                            </c:forEach>
                                        </ul>
                                         -->
                                    </li>
                                    </c:if>
                                  </c:forEach>
                                </ul>
                            </li>
                            </c:if>
                          </c:forEach>  
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
          <div class="container-fluid">
            <div class="row-fluid">
            <!-- 
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li class="active">
                            <a href="index.html"><i class="icon-chevron-right"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="calendar.html"><i class="icon-chevron-right"></i> Calendar</a>
                        </li>
                        <li>
                            <a href="stats.html"><i class="icon-chevron-right"></i> Statistics (Charts)</a>
                        </li>
                        <li>
                            <a href="form.html"><i class="icon-chevron-right"></i> Forms</a>
                        </li>
                        <li>
                            <a href="tables.html"><i class="icon-chevron-right"></i> Tables</a>
                        </li>
                        <li>
                            <a href="buttons.html"><i class="icon-chevron-right"></i> Buttons & Icons</a>
                        </li>
                        <li>
                            <a href="editors.html"><i class="icon-chevron-right"></i> WYSIWYG Editors</a>
                        </li>
                        <li>
                            <a href="interface.html"><i class="icon-chevron-right"></i> UI & Interface</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-success pull-right">731</span> Orders</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-success pull-right">812</span> Invoices</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-info pull-right">27</span> Clients</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-info pull-right">1,234</span> Users</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-info pull-right">2,221</span> Messages</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-info pull-right">11</span> Reports</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-important pull-right">83</span> Errors</a>
                        </li>
                        <li>
                            <a href="#"><span class="badge badge-warning pull-right">4,231</span> Logs</a>
                        </li>
                    </ul>
                </div>
  -->