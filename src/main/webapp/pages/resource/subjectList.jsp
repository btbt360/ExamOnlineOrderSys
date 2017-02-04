<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"> <a href='#'
						title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;">
						<a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a>
					</i>
					<li><a href="#">资源管理</a> <span class="divider">/</span></li>
					<li class="active">科目管理</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
					<ul class="nav nav-tabs">
						<li class="active"><a href="${basepath}/subject/addsub">科目列表</a></li>
						<li><a href="${basepath}/subject/addsubinfo">科目添加</a></li>
					</ul>
					
					<!-- 删除用户提示 -->
					<div class="span12">
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
						<div class="alert alert-success"
							style="margin-right: 8%;display: none; text-align: center;" id="successmessage">
							<button class="close" onclick="$('#successmessage').hide();">&times;</button>
							<strong><span id="messagess"></span></strong>
						</div>
						<div class="alert alert-error"
							style="margin-right: 8%;display: none; text-align: center;" id="errormessage">
							<button class="close" onclick="$('#errormessage').hide();">&times;</button>
							<strong><span id="messageee"></span></strong>
						</div>
					</div>
					<form action="${basepath}/subject/addsub" method="post"
						id="subform">
						<div class="span12">
							<div class="span4">
								<label class="control-label" for="name">科目名称：<input
									class="input-medium focused" id="name" name="name"
									type="text" /></label> 
							</div>
							<div class="span4">
								<label class="control-label" for="code">科目编码：<input
									class="input-medium focused" id="code" name="code"
									type="text" /></label> 
							</div>
							<div class="span4 text-right" >
						<button class="btn btn-medium btn-primary" type="submit"
							id="query">查询</button>
<!-- 						<button class="btn btn-medium btn-primary" type="button" -->
<!-- 							id="export">导出</button> -->
					</div>
						</div>
						<input type="hidden" id="subpages" name="subpages" /><input
							type="hidden" id="subrp" name="subrp" />
					</form>
					
					<table id="treeTable" class="table table-striped table-bordered" style="width: 100%">
						<thead>
							<tr>
								<th>科目名称</th>
								<th>科目编码</th>
								<th>科目描述</th>
								<th>是否启用</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach items="${subjectlist}" var="subject">
									<tr id="${subject.id}"
										pId="${subject.parentid ne '1'?subject.parentid:'0'}">
										<td nowrap><span>${subject.name}</span>
											<input type="hidden" name="ids" value="${subject.id}" /></td>
										<td title="${subject.code}">${subject.code}</td>
										<td title="${subject.info}">${subject.info}</td>
										<td>${subject.isenable eq '1'?'显示':'隐藏'}</td>
										<td nowrap><a href="${basepath}/subject/addsubinfo?id=${subject.id}">修改</a>|
											<a href="${basepath}/subject/delsub?id=${subject.id}" onclick="return confirm('要删除该菜单项吗？', this.href)">删除</a>
										</td>
									</tr>
								</c:forEach>
						</tbody>
						<!-- tbody是必须的 -->
					</table>
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
</script>
<c:import url="/pages/include/pageFoot.jsp" />