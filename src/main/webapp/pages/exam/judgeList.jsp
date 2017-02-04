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
					<li class="active">人工复评</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
					<ul class="nav nav-tabs">
						<li class="active"><a href="${basepath}/achievement/add">试卷复评</a></li>
						<li><a href="${basepath}/achievement/addJudge">试题复评</a></li>
					</ul>
						<div class="span12">
							<div class="span4">
								<label class="control-label" for="name">考试名称：
								<select class="m-wrap" id="examid" name="examid" placeholder="请选择试卷！">
									<option value=''>请选择考试</option>
								<c:forEach var="exam" items="${examlist}">
									<option value='${exam.id}'>${exam.code} | ${exam.name}</option>
								</c:forEach>
								</select>
								</label> 
							</div>
							<div class="span4">
								<label class="control-label" for="name">考生姓名：
		                                <select  id="examineeid" name="examineeid" class="chzn-select">
		                                <option value=''></option>
		                                </select>
		                        </label>
							</div>
							<div class="span4">
							</div>
							</div>
						<div class="span12" >
							<div class="span4">
								<label class="control-label" for="starttimes"><a href='#'
									id="ceatetimes" style="color: black; text-decoration: none;">开始时间：</a>&nbsp;<input type="text" class="input-xlarge datetimepicker"
									id="starttimes" value="" name="createtimes"></label>
							</div>
							<div class="span4">
								<label class="control-label" for="endtimes"><a href='#'
									id="ceatetimee" style="color: black; text-decoration: none;">至：</a><input type="text" class="input-xlarge datetimepicker" 
									id="endtimes" value="" name="createtimee"></label>
							</div>
							<div class="span4 text-center">
								<button class="btn btn-medium btn-primary" type="button"id="query">查询</button>
							</div>
						</div>
						<input type="hidden" id="subpages" name="subpages" />
						<input type="hidden" id="subrp" name="subrp" />
					<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th width="10%">考试编码</th>
								<th width="10%">考试名称</th>
								<th width="20%">考试开始时间</th>
								<th width="20%">考试结束时间</th>
								<th width="10%">考生姓名</th>
								<th width="10%">考生总分数</th>
								<th width="10%">考生状态</th>
								<th width="10%">操作</th>
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
	        format: 'yyyy-mm-dd hh:ii',
	        weekStart: 1,  
	        todayBtn:  1,  
	        autoclose: 1,  
	        todayHighlight: 1,  
	        startView: 2,  
	        forceParse: 0,  
	        showMeridian: 1  
	    }).on('changeDate', function (ev) {   
	        $(this).datetimepicker('hide'); 
	    });
		$(".chzn-select").chosen();
		$("#examid").change(function() {
			$.ajax({
				type : 'post',
				url : '${basepath}/achievement/getExaminee?examid='+$(this).val(),
				cache : false,
				dataType : 'json',
				success : function(data) {
					$("#examineeid").empty();
					$("#examineeid").append("<option value=''></option>");
					jQuery.each(data, function(i,item){
		                $("#examineeid").append("<option value='"+item.ID+"'>"+item.EXAMINEENAME+"</option>");
		            });
					$("#examineeid").trigger("liszt:updated"); 
				}
			});
		});
		
		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/achievement/getJudgeList"
		});
		$('#ceatetimes').click(function() {
			$('#starttimes').val('');
		});
		$('#ceatetimee').click(function() {
			$('#endtimes').val('');
		});
		$("#query").click(function() {
			reshcg();
		});
	});
	function reshcg() {
		var examid = $('#examid').val();
		var examineeid = $('#examineeid').val();
		var createtimes = $('#starttimes').val();
		var createtimee = $('#endtimes').val();
		var oSettings = [ {
			"name" : "examid",
			"value" : examid
		}, {
			"name" : "examineeid",
			"value" : examineeid
		}, {
			"name" : "createtimes",
			"value" : createtimes
		}, {
			"name" : "createtimee",
			"value" : createtimee
		} ];
		oTable.gridSearch(this, oSettings);
	}
	function addJudgeList(id,examidss){
// 		var examid = $("#examid").val();
// 		if(examid!=null&&examid!=''){
// 			location.href = '${basepath}/achievement/addJudge?examineeid='+id+'&examid='+examidss;
// 		}else{
// 			alert("请选择考试！");
// 			$("#examid").force();
// 		}
		location.href = '${basepath}/achievement/addJudge?examineeid='+id+'&examid='+examidss;
		
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />