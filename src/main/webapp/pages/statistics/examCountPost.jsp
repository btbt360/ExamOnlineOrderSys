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
					<li class="active">考试统计</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
					<ul class="nav nav-tabs">
						<li ><a href="${basepath}/statistics/examCountDapartment">部门考试统计</a></li>
						<li class="active"><a href="${basepath}/statistics/examCountPost">岗位考试统计</a></li>
						<li ><a href="${basepath}/statistics/examCount">人员考试统计</a></li>
					</ul>
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
						<div class="span3 text-center">
						<label class="control-label" for="starttimes"><a href='#'
							id="ceatetimes" style="color: black; text-decoration: none;">考试时间：</a></label>
						<input type="text" class="input-medium datetimepicker" id="starttimes"
							value="" name="starttimes">
						</div>
						<div class="span3 text-center">
							<label class="control-label" for="endtimes"><a href='#'
								id="ceatetimee" style="color: black; text-decoration: none;">至：</a></label>
							<input type="text" class="input-medium datetimepicker" id="endtimes"
								value="" name="endtimes">
						</div>
						<div class="span2 text-center">
						    <button class="btn btn-medium btn-primary" type="button" id="query">查询</button>
 							<input type="hidden" id="subpages" name="subpages" /><input type="hidden" id="subrp" name="subrp" />
						</div>
					</div>
						<table id="userList" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th >岗位名称</th>
									<th >考试次数</th>
									<th >考试不合格次数</th>
									<th >考试合格次数</th>
									<th >考试优秀次数</th>
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
		$('.datetimepicker').datetimepicker({  
            language:  'zh-CN',
            format: 'yyyy-mm-dd',
            weekStart: 1,  
            todayBtn:  1,  
            autoclose: true,  
            todayHighlight: 1,  
            startView: 2,  
            forceParse: true,  
            minView:2,//只到天
            showMeridian: 1  
        }).on('changeDate', function (ev) {  
            $(this).datetimepicker('hide');  
        });
		$('#ceatetimes').click(function() {
			$('#starttimes').val('');
		});
		$('#ceatetimee').click(function() {
			$('#endtimes').val('');
		});
		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/statistics/examCountPostfind"
		});
		$("#query").click(function() {
			reshcg();
		});
	});
	function reshcg() {
		var examid = $('#examid').val();
		var starttime=$('#starttimes').val();
		var endtime=$('#endtimes').val();
		var oSettings = [{
			"name" : "examid",
			"value" : examid
		},{
			"name" : "starttime",
			"value" : starttime
		},{
			"name" : "endtime",
			"value" : endtime
		}];
		oTable.gridSearch(this, oSettings);
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />