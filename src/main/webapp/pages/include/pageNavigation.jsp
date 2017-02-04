<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试预约系统</title>
</head>
<c:import url="/pages/include/pageHead.jsp" />
<body>
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
                                        <a tabindex="-1" href="${basepath}/user/addInfo">个人基本信息</a>
                                    </li>
                                   
                                    <li>
                                        <a tabindex="-1" href="${basepath}/user/addpass">修改密码</a>
                                    </li>
                                   
                                    <li>
                                        <a tabindex="-1" href="${basepath}/logout">注销</a>
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