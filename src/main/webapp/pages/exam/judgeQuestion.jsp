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
						<li ><a href="${basepath}/achievement/add">试卷复评</a></li>
						<li class="active"><a href="${basepath}/achievement/addJudge">试题复评</a></li>
					</ul>
						<div class="span12">
							<div class="span4">
								<label class="control-label" for="name">考试名称：
								<select class="m-wrap" id="examid" name="examid" placeholder="请选择考试！">
									<option value=''>请选择考试</option>
								<c:forEach var="exam" items="${examlist}">
									<option value='${exam.id}' <c:if test='${exam.id==examid}'>selected</c:if>>${exam.code} | ${exam.name}</option>
								</c:forEach>
								</select>
								</label> 
							</div>
							<div class="span4">
								<label class="control-label" for="name">考生姓名：
		                                <select  id="examineeid" name="examineeid" class="chzn-select">
		                                <c:if test='${examinee!=null}'>
		                                	<option value='${examinee.id}'>${examinee.examineename}</option>
		                                </c:if>
		                                <option value=''></option>
		                                </select>
		                        </label>
							</div>
							<div class="span4"><button class="btn btn-medium btn-primary" type="button"id="query">查询</button>
							</div>
							</div>
						
						<input type="hidden" id="subpages" name="subpages" />
						<input type="hidden" id="subrp" name="subrp" />
					<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th width="5%">试题序号</th>
								<th width="25%">试题内容</th>
								<th width="25%">试题正确答案</th>
								<th width="5%">试题分数</th>
								<th width="25%">考生答案</th>
								<th width="5%">考生得分</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						<!-- tbody是必须的 -->
					</table>
					<div class="span12 text-center">
						<button class="btn btn-info btn-large" onclick="onekeypass();">批量复评</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="btn btn-success btn-large" onclick="passAlreadyJudge()">复评完成</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
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
			"sAjaxSource" : "${basepath}/achievement/addJudgeList"
		});
		
		$("#query").click(function() {
			reshcg();
		});
	});
	function reshcg() {
		var examid = $('#examid').val();
		var examineeid = $('#examineeid').val();
		var oSettings = [ {
			"name" : "examid",
			"value" : examid
		}, {
			"name" : "examineeid",
			"value" : examineeid
		}];
		oTable.gridSearch(this, oSettings);
	}
	function addpass(id,idname){
		var score = $('#'+idname).val();
		var examid = $('#examid').val();
		var examineeid = $('#examineeid').val();
		$.ajax({
			type : 'post',
			url : '${basepath}/achievement/passJudgeList?examid='+examid+'&examineeid='+examineeid+'&score='+score+'&questionsid='+id,
			cache : false,
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				reshcg();
			}
		});
	}
	function onekeypass(){
		var examid = $('#examid').val();
		var examineeid = $('#examineeid').val();
		$.ajax({
			type : 'post',
			url : '${basepath}/achievement/onekeypass?examid='+examid+'&examineeid='+examineeid,
			cache : false,
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				reshcg();
			}
		});
	}
	function passAlreadyJudge(){
		var examid = $('#examid').val();
		var examineeid = $('#examineeid').val();
		$.ajax({
			type : 'post',
			url : '${basepath}/achievement/passAlreadyJudge?examid='+examid+'&examineeid='+examineeid,
			cache : false,
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				reshcg();
			}
		});
	}
	
</script>
<c:import url="/pages/include/pageFoot.jsp" />