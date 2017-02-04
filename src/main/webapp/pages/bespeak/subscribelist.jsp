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
					<li><a href="#">考试管理</a> <span class="divider">/</span></li>
					<li class="active">考试安排</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
					<ul class="nav nav-tabs">
						<li class="active"><a href="${basepath}/exam/addExam">考试安排列表</a></li>
						<li><a href="${basepath}/exam/addExamInfo">添加考试安排</a></li>
					</ul>
					
					<!-- 删除用户提示 -->
					<div class="span12">
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
					<form action="${basepath}/exam/exportSubject" method="post" id="subform">
						<div class="span12">
							<div class="span4">
								<label class="control-label" for="name">考试名称：<input
									class="input-medium focused" id="name" name="name"
									type="text" /></label> 
							</div>
							<div class="span4">
								<label class="control-label" for="code">考试编码：<input
									class="input-medium focused" id="code" name="code"
									type="text" /></label> 
							</div>
							<div class="span4 text-right" >
						<button class="btn btn-medium btn-primary" type="button"
							id="query">查询</button>
 						<!-- <button class="btn btn-medium btn-primary" type="button" 
							id="export">计算分数</button> --> 
					</div>
						</div>
						<input type="hidden" id="subpages" name="subpages" /><input
							type="hidden" id="subrp" name="subrp" />
					</form>
					
					<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>考试名称</th>
								<th>考试编号</th>
								<th>考试开始时间</th>
								<th>考试结束时间</th>
								<th>考试时长</th>
								<th>考试人数</th>
								<th>监考人</th>
								<th>考试状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						<!-- tbody是必须的 -->
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function edit(ids) {
	location.href = "${basepath}/exam/addExamInfo?id=" + ids;
}

function del(ids) {
	if (confirm("确定要删除该考试？")) {
		$.ajax({
			type : 'post',
			url : '${basepath}/exam/delExam?id=' + ids,
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.result == 1) {
					$("#successmessage").hide();
					$("#errormessage").show();
					$("#messageee").text("删除失败，请联系管理员！");
				} else {
					$("#errormessage").hide();
					$("#successmessage").show();
					$("#messagess").text("删除成功！");
				}
				reshcg();
			}
		});
	}
}
	$(document).ready(function() {
		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/exam/getExamlist"
		});

		$("#query").click(function() {
			reshcg();
		});
		$("#export").click(function() {
			alert(1111);
			/* $("#subpages").val(oTable.getCurrentPage());
			$("#subrp").val(oTable.getPageSize());
			$("#subform").submit(); */
			location.href = "${basepath}/exam/countScore";
		});

	});
	function reshcg() {
		var name = $('#name').val();
		var code = $('#code').val();
		var oSettings = [ {
			"name" : "name",
			"value" : name
		}, {
			"name" : "code",
			"value" : code
		} ];
		oTable.gridSearch(this, oSettings);
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />