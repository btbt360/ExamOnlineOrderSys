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
					<li><a href="#">考试管理</a> <span class="divider">/</span></li>
					<li class="active">考试安排</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/exam/addExam">考试安排列表</a></li>
			<li class="active"><a href="${basepath}/exam/addExaminfo">添加考试安排</a></li>
		</ul>
			<div class="span12">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>保存成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>保存失败！</strong></c:if>
				</div>
				<form id="subinfoform" class="form-horizontal" action="${basepath}/exam/saveExam" method="post" onsubmit="return check();">
					<fieldset>
						<legend>添加考试安排</legend>
						<div class="control-group">
							<label class="control-label">考试名称：</label>
							<div class="controls">
							<input name="exam.id" type="hidden" value="${exam.id}" >
								<input class="input-xlarge focused" id="name" name="exam.name"
									type="text" placeholder="请输入试卷名称!" value="${exam.name}" >
							</div>
						</div>
						<%-- <div class="control-group">
							<label class="control-label">考试编号：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="name" name="exam.code"
									type="text" placeholder="请输入考试编号!" value="${exam.code}" >
							</div>
						</div> --%>
						<div class="control-group">
							<label class="control-label">开始时间：</label>
							<div class="controls">
								<input class="input-medium datepicker" id="starttimes" name="exam.starttime"
									type="text" placeholder="请输入开始时间!" value="${starttimestr}" onchange="compare()" readonly="readonly">
									<input  id="starttimestr" name="starttimestr"
									type="hidden" value="${starttimestr}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结束时间：</label>
							<div class="controls">
								<input class="input-medium datepicker" id="endtimes" name="exam.endtime"
									type="text" placeholder="请输入结束时间!" value="${endtimestr}" onchange="compare()" readonly="readonly">
								<input  id="endtimestr" name="endtimestr"
									type="hidden" value="${endtimestr}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试时长：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="duration" name="exam.duration"
									type="text" value="${exam.duration}" readonly="readonly">分钟
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试地点：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="address" name="exam.address"
									type="text" placeholder="请输入考试地点!" value="${exam.address}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >选择试卷：</label>												    
						  <div class="controls">  <select class="span3 m-wrap" id="exampapersid" name="exam.exampapersId"  placeholder="请选择科目！" >
								<option value=''>请选择试卷</option>
								<c:forEach var="exampapers" items="${exampaperslist}">
								<option
									<c:if test="${exampapers.id==exam.exampapersId}">selected</c:if>
									value='${exampapers.id}'>${exampapers.name}</option>
							</c:forEach>
							</select> 
							<input name="exercise.id" type="hidden" value="${exercise.id}" >
						</div>	
						</div>
						<div class="control-group">
							<label class="control-label">考试人员：</label>
							<div class="controls">
								<!-- 弹出层 start -->
								<div class="modal hide fade" id="oModal" tabindex="-1"
									role="dialog">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal">×</button>
										<h3 id="myModalLabel">考生列表</h3>
									</div>
									<div class="modal-body">
										<div id="otree" class="ztree"></div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn" id="oclosed">关闭</a> <a href="#"
											class="btn btn-primary" id="saveoffice">保存</a>
									</div>
								</div>
								<!-- 弹出层 end -->
								<button type="button" id="editoff" class="btn btn-primary">选择考生</button>
								<span id="usernamesview">${usernamesview}</span> <input
									type="hidden" name="userids" id="userids" value="${userids}" />
									<input
									type="hidden" name="usernames" id="usernames" value="${usernames}" />
									<input
									type="hidden" name="allids" id="allids" value="${allids}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">监考员一姓名：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="endtime" name="exam.invigilatenameone"
									type="text" placeholder="请输入监控员一姓名!" value="${exam.invigilatenameone}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">监考员二姓名：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="invigilatenametwo" name="exam.invigilatenametwo"
									type="text" placeholder="请输入监考员二姓名!" value="${exam.invigilatenametwo}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试要求：</label>
							<div class="controls">
								<textarea id="demand" name="exam.demand" placeholder="请输入考试要求!" rows="7" style="width: 50%;	" >${exam.demand}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">考试制度：</label>
							<div class="controls">
								<textarea id="institution" name="exam.institution" placeholder="请输入考试制度!" rows="10" style="width: 50%;	" >${exam.institution}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="login_flag0">&nbsp;&nbsp;是&nbsp;&nbsp;
								<input type="radio" id="login_flag0" value="1" name="exam.isenable" checked
								<c:if test="${exam.isenable==1}">checked</c:if> />
								</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="login_flag1">&nbsp;&nbsp;否&nbsp;&nbsp;
								<input type="radio" id="login_flag1" value="0" name="exam.isenable"
								<c:if test="${exam.isenable==0}">checked</c:if> />
								</label>
							</div>
						</div>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/exam/addExam'">
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">

    var roleid = '';
    var userids = $("#userids").val();
	var settingoffice = {
		check : {
			enable : true,
			chkStyle : "checkbox"
		},
		async : {
			enable : true,
			url : "${basepath}/office/getUserTree",
			autoParam : [ "id", "name" ],
			otherParam : {
				"otherParam" : "zTreeAsyncTest",
				"roleid" : roleid,
				"userids": userids
			},
			dataFilter : filter
		},
		callback : {
			onClick : zTreeOnClick,
			onAsyncSuccess : onAsyncSuccesso
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("ztree");
		var nodes = treeObj.getNodesByParam("parentId", 0, null);
		if (nodes.length > 0) {
			treeObj.expandNode(nodes[0], true, false, false);
		}
	}

	function onAsyncSuccesso(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("ztree");
		var nodes = treeObj.getNodesByParam("parentId", 0, null);
		if (nodes.length > 0) {
			treeObj.expandNode(nodes[0], true, false, false);
		}
	}

//机构树单击事件

function zTreeOnClick(event, treeId, treeNode) {
	if (treeNode.nodetype == 1) {
		treeNodez = treeNode.nodetype;

	} else {
		treeNodez = treeNode.nodetype;

	}
}
function getAllCheckedNode() {
	var treeObj = $.fn.zTree.getZTreeObj("ztree");
	var nodes = treeObj.getCheckedNodes(true);
	var str = "";
	var ids = "";
	var rightids = "";
	for (var i = 0; i < nodes.length; i++) {
		str = str + nodes[i].name + "|";
		ids = ids + nodes[i].id + "|";
		rightids = rightids + nodes[i].resid + "|";
	}
	$("#resids").val(ids);
	$("#rightids").val(rightids);
	$("#rolerightnames").text(str);
}

function getAllCheckedNodeo() {
	var treeObj = $.fn.zTree.getZTreeObj("otree");
	var nodes = treeObj.getCheckedNodes(true);
	var str = "";
	var ids = "";
	var allids = "";
	for (var i = 0; i < nodes.length; i++) {
		allids = ids + nodes[i].id + "|";
		if(nodes[i].type == "1"){
			str = str + nodes[i].name + "|";
			ids = ids + nodes[i].id + "|";
		}
	}
	$("#userids").val(ids);
	$("#allids").val(allids);
	$("#usernamesview").text(str);
	$("#usernames").val(str);
	
	
}
$(document).ready(function() {

	$.fn.zTree.init($("#otree"), settingoffice);
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
 	    jqObj.set("exam.name", "required",  "请输入试卷名称!"); 
 	   // jqObj.set("exam.code", "required",  "请输入考试编号!"); 
 	    jqObj.set("exam.starttime", "required",  "请输入开始时间!");
 	    jqObj.set("exam.endtime", "required",  "请输入结束时间!");
 	    jqObj.set("exam.address", "required",  "请输入考试地点!");
 	    jqObj.set("exam.number", "required",  "请输入考试人数!");
 	    jqObj.set("exam.invigilatenameone", "required",  "请输入监考员一姓名!");	
 	    jqObj.set("exam.invigilatenametwo", "required",  "请输入监考员二姓名!");	 
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
				 $('#starttimes').val('');
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
				  $('#endtimes').val('');
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
		var userIds = $("#userids").val();
		if(userIds==''){
			alert("请重新选择参加考生！");
			return false;
		}
		var ids= new Array(); //定义一数组 
		ids = userIds.split("\|");
		if((ids.length-1) >30){
			alert("参加考生人数不得大于30人,请重新选择！");
			$("#userids").val('');
			$("#usernamesview").text('');
			return false;
		}
		
		return true;
		
	}
	
</script>
<c:import url="/pages/include/pageFoot.jsp" />