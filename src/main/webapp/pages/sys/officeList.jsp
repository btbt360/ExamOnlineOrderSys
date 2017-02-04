<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"><a href='#'
						title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;"><a
						href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">系统管理</a> <span class="divider">/</span></li>
					<li class="active">组织机构管理</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<div id="notice">
					<c:if test="${message!=null&&message!=''}">
						<c:if test="${message=='success'}">
							<div class="alert alert-success"
								style="text-align: center;">
								<button class="close" data-dismiss="alert">&times;</button>
								<strong>操作成功！</strong>
							</div>
						</c:if>
						<c:if test="${message=='error'}">
							<div class="alert alert-error"
								style="text-align: center;">
								<button class="close" data-dismiss="alert">&times;</button>
								<strong>当前机构下存在下级机构，操作失败！</strong>
							</div>
						</c:if>
					</c:if>
				</div>
				<ul class="nav nav-tabs">
					<li class="active"><a href="${basepath}/office/add">机构列表</a></li>
					<li><a href="${basepath}/office/addofficeinfo">机构添加</a></li>
				</ul>
			
				<form id="listForm" method="post">
					<table id="treeTable"
						class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>机构名称</th>
								<th >机构编码</th>
								<th style="text-align: center;">机构类型</th>
								<th>负责人</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${officelist}" var="office">
								<tr id="${office.id}"
									pId="${office.parentId ne '1'?office.parentId:'0'}">
									<td nowrap><c:if test="${office.type==1}">
											<i class="icon-home"></i>
										</c:if> <c:if test="${office.type==2}">
											<i class="icon-th-list"></i>
										</c:if> <c:if test="${office.type==3}">
											<i class="icon-user"></i>
										</c:if> <a href="${basepath}/office/addofficeinfo?id=${office.id}&pid=${office.parentId}&type=${office.type}">${office.name}</a></td>
									<td title="${office.code}">${office.code}</td>
									<td style="text-align: center;">${office.fax}</td>
									<!-- <td>${office.delFlag eq '0'?'否':'是'}</td> -->
									<td>${office.master}</td>
									<td title="${office.remarks}"></td>
									<td nowrap><c:if test="${office.type==1}">
											<a href="#" onclick="editoffice('${office.id}',1)">添加下级机构</a>
											<a href="#" onclick="editoffice('${office.id}',2)">添加部门</a>
											<a href="#" onclick="editoffice('${office.id}',3)">添加岗位</a>
											<a href="${basepath}/office/delofficeinfo?id=${office.id}"
									onclick="return confirm('确定要删除该机构？', this.href)" > 删除 
									</a>
										</c:if> <c:if test="${office.type==2}">
											<a href="#" onclick="editoffice('${office.id}',2)">添加部门</a>
											<a href="#" onclick="editoffice('${office.id}',3)">添加岗位</a>
											<a href="${basepath}/office/delofficeinfo?id=${office.id}"
									onclick="return confirm('确定要删除该机构？', this.href)" > 删除
									</a>
										</c:if> <c:if test="${office.type==3}">
											<a href="${basepath}/office/delofficeinfo?id=${office.id}"
									onclick="return confirm('确定要删除该机构？', this.href)" > 删除 
									</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({
			expandLevel : 3
		}).show();
	});
	function deloffice(ids) {
		if (confirm("确定要删除该机构？")) {
			$.ajax({
				type : 'post',
				url : '${basepath}/office/delofficeinfo?id=' + ids,
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data == 1) {
						$("#successmessage").hide();
						$("#errormessage").show();
						$("#messageee").text("当前机构下存在下级机构，无法删除！");
					} else {
						$("#errormessage").hide();
						$("#successmessage").show();
						$("#messagess").text("删除成功！");
						
					}
				}
			});
		}
	};
	function editoffice(ids, flag) {
		window.location.href = '${basepath}/office/addofficeinfo?pid=' + ids
				+ '&type=' + flag;
	};
	function reload() {
		$('#successmessage').hide();
		window.location.reload();
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />