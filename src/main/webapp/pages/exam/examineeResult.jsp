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
					<li><a href="#">我的考试</a> <span class="divider">/</span></li>
					<li class="active">我的成绩</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
						<div class="span12 text-center" style="margin-top:2%;">
							<div class="span6">
								<label class="control-label" for="name">考试名称：
								<select class="m-wrap" id="examid" name="examid" placeholder="请选择试卷！">
									<option value=''>请选择考试</option>
								<c:forEach var="exam" items="${examlist}">
									<option value='${exam.id}' <c:if test='${exam.id==examid}'>selected</c:if>>${exam.code} | ${exam.name}</option>
								</c:forEach>
								</select>
								</label> 
							</div>
							<div class="span6"><button class="btn btn-medium btn-primary" type="button"id="query">查询</button>
							</div>
							</div>
						<input type="hidden" id="subpages" name="subpages" />
						<input type="hidden" id="subrp" name="subrp" />
					<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th width="10%">考试编码</th>
								<th width="15%">考试名称</th>
								<th width="25%">考试时间</th>
								<th width="10%">考试状态</th>
								<th width="10%">考生姓名</th>
								<th width="10%">考生分数</th>
								<th width="10%">考生状态</th>
								<th width="10%">判卷状态</th>
<!-- 								<th width="10%">操作</th> -->
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
	$(document).ready(function() {
		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/achievement/getExamRecordList"
		});
		
		$("#query").click(function() {
			reshcg();
		});
	});
	function reshcg() {
		var examid = $('#examid').val();
		var oSettings = [ {
			"name" : "examid",
			"value" : examid
		}];
		oTable.gridSearch(this, oSettings);
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />