<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />

<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">
			<ul class="breadcrumb">
				<li class="icon-chevron-left hide-sidebar"><a href='#'
					title="Hide Sidebar" rel='tooltip'>&nbsp;</a></li>
				<li class="icon-chevron-right show-sidebar" style="display: none;"><a
					href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></li>
				<li><a href="#">系统管理</a> <span class="divider">/</span></li>
				<li class="active">菜单列表</li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="block-content collapse in" style="border: 0px;">
			<div id="notice">
				<c:if test="${message!=null&&message!=''}">
					<c:if test="${message=='success'}">
						<div class="alert alert-success" style="text-align: center;">
							<button class="close" data-dismiss="alert">&times;</button>
							<strong>删除成功！</strong>
						</div>
					</c:if>
					<c:if test="${message=='error'}">
						<div class="alert alert-error" style="text-align: center;">
							<button class="close" data-dismiss="alert">&times;</button>
							<strong>该菜单含有下级菜单，请清空下级菜单后重试！</strong>
						</div>
					</c:if>
				</c:if>
			</div>

			<div class="row-fluid">
				<div class="block-content collapse in" style="border: 0px;">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#">菜单列表</a></li>
						<li><a href="${basepath}/menu/addmenuinfo">菜单添加</a></li>
					</ul>
					<div class="span12" style="padding-right: 4.5%;">
						<div class="alert alert-success"
							style="display: none; text-align: center;" id="successmessage">
							<button class="close" onclick="$('#successmessage').hide();">&times;</button>
							<strong><span id="messagess"></span></strong>
						</div>
						<div class="alert alert-error"
							style="display: none; text-align: center;" id="errormessage">
							<button class="close" onclick="$('#errormessage').hide();">&times;</button>
							<strong><span id="messageee"></span></strong>
						</div>
					</div>
					<div class="span12 text-right"
						style="padding-right: 3%; padding-bottom: 1%;">
						<button class="btn btn-medium btn-primary" type="button"
							onclick="menusynchronization();" id="menusynchronization">菜单同步</button>
					</div>
					<form id="listForm" method="post">
						<table id="treeTable" class="table table-striped table-bordered"
							style="width: 100%">
							<thead>
								<tr>
									<th>名称</th>
									<th>链接</th>
									<th>可见</th>
									<th>权限标识</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listmenu}" var="menu">
									<tr id="${menu.id}"
										pId="${menu.parentId ne '1'?menu.parentId:'0'}">
										<td nowrap><i
											class="icon-${not empty menu.icon?menu.icon:' hide'}"></i><span>${menu.name}</span>
											<input type="hidden" name="ids" value="${menu.id}" /></td>
										<td title="${menu.href}">${menu.href}</td>
										<td>${menu.isShow eq '1'?'显示':'隐藏'}</td>
										<td title="${menu.permission}">${menu.permission}</td>
										<td nowrap><a
											href="${basepath}/menu/addmenuinfo?id=${menu.id}">修改</a>|<a
											href="${basepath}/menu/delmenuinfo?id=${menu.id}"
											onclick="return confirm('要删除该菜单项吗？', this.href)">删除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
	setTimeout(function() {
		$("#notice").css("display", "none");
	}, 5000);
	$(document).ready(function() {
		$("#treeTable").treeTable({
			expandLevel : 3
		}).show();
	});
	function menusynchronization() {
		$.ajax({
			type : 'post',
			url : '${basepath}/menu/menusynchronization',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data == '2') {
					$("#successmessage").hide();
					$("#errormessage").show();
					$("#messageee").text("菜单同步异常！");
				} else {
					$("#errormessage").hide();
					$("#successmessage").show();
					$("#messagess").text("菜单同步成功！");
				}
			}
		});
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />