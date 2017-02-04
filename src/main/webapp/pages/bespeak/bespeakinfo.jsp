<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class='icon-chevron-left hide-sidebar'><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
				<!-- 	<i class='icon-chevron-right show-sidebar" style="display: none;'><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i> -->
					<li><a href="#">考试预约管理</a> <span class="divider">/</span></li>
					<li class="active">考试预约管理安排</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/bespeak/add">考试预约管理列表</a></li>
			<li class="active"><a href="${basepath}/bespeak/addbespeak">添加考试预约管理</a></li>
		</ul>
			<div class="span12">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>保存成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>保存失败！</strong></c:if>
				</div>
				<form id="subinfoform" class="form-horizontal" action="${basepath}/bespeak/savebespeak" method="post" onsubmit="return check();">
					<fieldset>
						<legend>添加考试安排</legend>
						<div class="control-group">
							<label class="control-label">考试名称：</label>
							<div class="controls">
							<input name="bespeakexam.id" type="hidden" value="${bespeakexam.id}" >
								<input class="input-xlarge focused" id="name" name="bespeakexam.examname"
									type="text" placeholder="请输入试卷名称!" value="${bespeakexam.examname}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试编号：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="code" name="bespeakexam.examcode"
									type="text" placeholder="请输入考试编号!" value="${bespeakexam.examcode}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开始时间：</label>
							<div class="controls">
								<input class="input-medium datepicker" id="starttimes" name="bespeakexam.starttime"
									type="text" placeholder="请输入开始时间!" value="${starttimestr}" onchange="compare()" readonly="readonly">
									<input  id="starttimestr" name="starttimestr"
									type="hidden" value="${starttimestr}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结束时间：</label>
							<div class="controls">
								<input class="input-medium datepicker" id="endtimes" name="bespeakexam.endtime"
									type="text" placeholder="请输入结束时间!" value="${endtimestr}" onchange="compare()" readonly="readonly">
								<input  id="endtimestr" name="endtimestr"
									type="hidden" value="${endtimestr}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试时长：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="duration" name="bespeakexam.duration"
									type="text" value="${bespeakexam.duration}" readonly="readonly">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试地点：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="address" name="bespeakexam.address"
									type="text" placeholder="请输入考试地点!" value="${bespeakexam.address}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试人数：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="number" name="bespeakexam.number"
									type="number" placeholder="请输入考试人数!" value="${bespeakexam.number}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试要求：</label>
							<div class="controls">
								<textarea id="demand" name="bespeakexam.demand" placeholder="请输入考试要求!" rows="7" style="width: 50%;" >${bespeakexam.demand}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试制度：</label>
							<div class="controls">
								<textarea id="institution" name="bespeakexam.institution" placeholder="请输入考试制度!" rows="10" style="width: 50%;" >${bespeakexam.institution}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="login_flag0">&nbsp;&nbsp;是&nbsp;&nbsp;
								<input type="radio" id="login_flag0" value="1" name="bespeakexam.isenable" checked
								<c:if test="${bespeakexam.isenable==1}">checked</c:if> />
								</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="login_flag1">&nbsp;&nbsp;否&nbsp;&nbsp;
								<input type="radio" id="login_flag1" value="0" name="bespeakexam.isenable"
								<c:if test="${bespeakexam.isenable==0}">checked</c:if> />
								</label>
							</div>
						</div>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/bespeakexam/add'">
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">

$(document).ready(function() {

		$("#editoff").click(function() {
			$('#oModal').modal('show');
		});
		$("#oclosed").click(function() {
			$('#oModal').modal('hide');
		});
		$("#saveoffice").click(function() {
			$("#usernames").val("");
			$("#userids").val("");
			$("#usernamesview").text("");
			getAllCheckedNodeo();
			$('#oModal').modal('hide');
		});
	
		
		$('.datepicker').datetimepicker({  
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
		
		var jqObj = new JQvalidate();
 	    var subform ="subinfoform"; 
     	jqObj.setform(subform);
 	    jqObj.set("bespeakexam.name", "required",  "请输入试卷名称!"); 
 	    jqObj.set("bespeakexam.code", "required",  "请输入考试编号!"); 
 	    jqObj.set("bespeakexam.starttime", "required",  "请输入开始时间!");
 	    jqObj.set("bespeakexam.endtime", "required",  "请输入结束时间!");
 	    jqObj.set("bespeakexam.address", "required",  "请输入考试地点!");
 	    jqObj.set("bespeakexam.number", "required",  "请输入考试人数!");
 	    jqObj.set("bespeakexam.invigilatenameone", "required",  "请输入监考员一姓名!");	
 	    jqObj.set("bespeakexam.invigilatenametwo", "required",  "请输入监考员二姓名!");	 
 	    jqObj.Run();
	})
	
	function compare(){
		var startTime=$('#starttimes').val();
		var endTime=$('#endtimes').val();
		if(startTime != ""){
			$('#starttimestr').val(startTime);
			var starttimes = startTime.substring(0, 10).split('-');
			var starttime = starttimes[1] + '-' + starttimes[2] + '-' + starttimes[0] + ' ' + startTime.substring(10, 19);
			starttime = starttime.replace(/-/g, "/");
			var nowTime = getNowFormatDate();
			nowTime = nowTime.replace(/-/g, "/");
			var starthour=(Date.parse(starttime)-Date.parse(nowTime))/3600/1000;
			if(starthour < 0){
				 alert("开始时间不能小于当前时间！");
				 startTime.focus();
			}
		}
		if(endTime != ""){
			$('#endtimestr').val(endTime);
		}
		if(startTime != "" && endTime != ""){
			var beginTimes = startTime.substring(0, 10).split('-');
			var endTimes = endTime.substring(0, 10).split('-');
			
			
		beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' ' + startTime.substring(10, 19);
	    endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19);
	    beginTime = beginTime.replace(/-/g, "/");
	    endTime = endTime.replace(/-/g, "/");
	   
	    var hour=(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;
	    
		if (hour < 0) { 
			  alert("结束时间不能小于开始时间！");
			  endTime.focus();
		}else{
			var minutes = parseInt(Math.abs((Date.parse(endTime)-Date.parse(beginTime))/(1000*60)));
			$("#duration").val(minutes);
		}
	}
	}
	
	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    return currentdate;
	}
	
	function check(){
		var number = $("#number").val();
		if(number > 30){
			alert("参加考生人数不得大于30人,请重新选择！")
			return false;
		}else{
			return true;
		}
	}
	
</script>
<c:import url="/pages/include/pageFoot.jsp" />