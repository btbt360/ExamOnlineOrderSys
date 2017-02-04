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
					<li><a href="#">统计分析</a> <span class="divider">/</span></li>
					<li class="active">试题错误率统计</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
						<div class="span12">
							<div class="span4 text-center">
								<label class="control-label" for="name">考试名称：
								<select class="m-wrap" id="examid" name="examid" placeholder="请选择考试！">
									<option value=''>请选择考试</option>
								<c:forEach var="exam" items="${examlist}">
									<option value='${exam.id}'>${exam.code} | ${exam.name}</option>
								</c:forEach>
								</select>
								</label> 
						</div>
						<div class="span4"></div>
						<div class="span4 text-center" >
						    	<button class="btn btn-medium btn-primary" type="button" id="query">查询</button>
 								<input type="hidden" id="subpages" name="subpages" /><input type="hidden" id="subrp" name="subrp" />
						</div>
						</div>
						<table id="userList" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="10%">考试名称</th>
									<th width="10%">试题编码</th>
									<th width="30%">试题内容</th>
									<th width="10%">正确答案</th>
									<th width="10%">出错次数</th>
									<th width="30%">试题解析</th>
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
			"sAjaxSource" : "${basepath}/statistics/errorrateCountfind"
		});
		$("#query").click(function() {
			reshcg();
		});
	});
	function reshcg() {
		var examid = $('#examid').val();
		var oSettings = [{
			"name" : "examid",
			"value" : examid
		}];
		oTable.gridSearch(this, oSettings);
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />