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
					<li class="active">我已预约</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
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
							<label class="control-label" for="starttimes"><a href='#'
								id="ceatetimes" style="color: black; text-decoration: none;">创建时间：</a><input type="text" class="input-medium datetimepicker"
								id="starttimes" value="" name="starttimes"></label>
							</div>
							<div class="span4">
								<label class="control-label" for="endtimes"><a href='#'
									id="ceatetimee" style="color: black; text-decoration: none;">至：</a><input type="text" class="input-medium datetimepicker" id="endtimes"
									value="" name="endtimes"></label>
							</div>
							<div class="span4 text-center" >
							<button class="btn btn-medium btn-primary" type="button" id="query">查询</button>
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
								<th>考试人数</th>
								<th>考试状态</th>
								<th>启用状态</th>
								<th>预约状态</th>
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

		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/bespeak/schedulebespeak"
		});

		$("#query").click(function() {
			reshcg();
		});

		$('#ceatetimes').click(function() {
			$('#starttimes').val('');
		});
		$('#ceatetimee').click(function() {
			$('#endtimes').val('');
		});

	});
	function reshcg() {
		var starttimes = $('#starttimes').val();
		var endtimes = $('#endtimes').val();
		var oSettings = [ {
			"name" : "starttimes",
			"value" : starttimes
		}, {
			"name" : "endtimes",
			"value" : endtimes
		} ];
		oTable.gridSearch(this, oSettings);
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />