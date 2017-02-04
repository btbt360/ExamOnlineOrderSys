<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"><a href='#'
						title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;"><a
						href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">系统管理</a> <span class="divider">/</span></li>
					<li class="active">地区管理</li>
				</ul>
			</div>
		</div>

		<div class="row-fluid">
			<div class="block-content collapse in" style="border: 0px;">
				<div id="notice">
					<c:if test="${message!=null&&message!=''}">
						<c:if test="${message=='success'}">
							<div class="alert alert-success"
								style="text-align: center;">
								<button class="close" data-dismiss="alert">&times;</button>
								<strong>删除成功！</strong>
							</div>
						</c:if>
						<c:if test="${message=='error'}">
							<div class="alert alert-error"
								style="text-align: center;">
								<button class="close" data-dismiss="alert">&times;</button>
								<strong>该菜单含有下级地区，请清空下级地区后重试！</strong>
							</div>
						</c:if>
					</c:if>
				</div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="${basepath}/area/add">地区列表</a></li>
					<li><a href="${basepath}/area/addareainfo">地区添加</a></li>
				</ul>
			<form action="${basepath}/area/add" method="post">
				<div class="span12" style="text-align: center;">
					<div class="span3">
						<label class="control-label" for="areaname">地区名：<input
							class="input-medium focused" id="areaname" name="areaname"
							type="text" value = "${areaname}" /></label> 
					</div>
					<div class="span3">
						<label class="control-label" for="areatype">区域类型：<select
							class="span6 m-wrap" id="areatype" name="areatype">
							<option value="">--全部--</option>
							<c:forEach items="${listdict}" var="dict">
								<option value="${dict.dictkey}" <c:if test="${areatype==dict.dictkey}">selected</c:if>>${dict.dictvalue}</option>
							</c:forEach>
						</select></label> 
					</div>
					<button class="btn btn-medium btn-primary" type="submit" id="query">查询</button>
				</div>
			</form>
				<table id="treeTable" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>名称</th>
							<th>区域编码</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listarea}" var="area">
							<tr id="${area.id}"
								pId="${area.parentId ne '1'?area.parentId:'0'}">
								<td nowrap>${area.name}</td>
								<td nowrap>${area.code}</td>
								<td nowrap><c:if test="${area.type=='1'}">国家</c:if> <c:if
										test="${area.type=='2'}">省份/直辖市</c:if> <c:if
										test="${area.type=='3'}">地市</c:if> <c:if
										test="${area.type=='4'}">区县</c:if></td>
								<td nowrap><a
									href="${basepath}/area/addareainfo?id=${area.id}"> 修改 </a> | <a
									href=" ${basepath}/area/delareainfo?id=${area.id}"
									onclick="return confirm('要删除该地区及所有子地区项吗？', this.href)"> 删除
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({
			expandLevel : 4
		}).show();
	});

</script>
<c:import url="/pages/include/pageFoot.jsp" />