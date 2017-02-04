<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class='icon-chevron-left hide-sidebar'><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class='icon-chevron-right show-sidebar" style="display: none;'><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">我的练习</a> <span class="divider">/</span></li>
					<li class="active">练习管理</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/exercise/addExercise">练习管理列表</a></li>
			<li class="active"><a href="${basepath}/exercise/addExerciseinfo">练习管理添加</a></li>
		</ul>
			<div class="span12">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>保存成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>保存失败！</strong></c:if>
				</div>
				<form id="subinfoform" class="form-horizontal" action="${basepath}/exercise/saveExercise" method="post">
					<fieldset>
						<legend>练习管理</legend>
						<div class="control-group">
							<label class="control-label">练习名称：</label>
							<div class="controls">
							<input name="exercise.id" type="hidden" value="${exercise.id}" >
								<input class="input-xlarge focused" id="name" name="exercise.name"
									type="text" placeholder="请输入练习名称!" value="${exercise.name}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >属于科目：</label>												    
						  <div class="controls">
						  	<input id="citySel" type="text" readonly value="${subjectname}" style="width:120px;" onclick="showMenu(); return false;"/>
							<input name="exercise.subjectId" id ="subjectId" type="hidden" value="${exercise.subjectId}" >
							<input name="exercise.id" type="hidden" value="${exercise.id}" />
						</div>	
						</div>
						<div class="control-group">
							<label class="control-label" >属于题库：</label>												    
						  <div class="controls"> 
						  	<select class="span3 m-wrap" id="itembank" name="exercise.itembankId"  placeholder="请选择题库！" >
								<option value=''>请选择题库</option>
								<c:if test="${itembank!=null}">
									<option value='${itembank.id}' selected>${itembank.name}</option>
								</c:if>
							</select> 
						</div>	
						</div>
						<div class="control-group">
							<label class="control-label">练习总数：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="sumcount" name="exercise.sumcount"
									type="text"  value="${exercise.sumcount}"  readonly="readonly">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备注信息：</label>
							<div class="controls">
								<textarea id="institution" name="exercise.remark" placeholder="请输入备注信息!" rows="5" style="width: 50%;" >${exercise.remark}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="login_flag0">&nbsp;&nbsp;是&nbsp;&nbsp;
								<input type="radio" id="login_flag0" value="1" name="exercise.isenable" checked
								<c:if test="${exercise.isenable==1}">checked</c:if> />
								</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="login_flag1">&nbsp;&nbsp;否&nbsp;&nbsp;
								<input type="radio" id="login_flag1" value="0" name="exercise.isenable"
								<c:if test="${exercise.isenable==0}">checked</c:if> />
								</label>
							</div>
						</div>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/exercise/addExercise'">
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color: #f5f5f5;border: 1px solid #ccc;">
	<ul id="otree" class="ztree"></ul>
</div>
<script type="text/javascript">
var subjectid = '${subject.parentid}';
var setting = {
		view: {
			dblClickExpand: false
		},
		async : {
			enable : true, //设置 zTree 是否开启异步加载模式
			url : "${basepath}/subject/getSubjectTree", //Ajax 获取数据的 URL 地址。
			autoParam : [ "id", "name" ], //异步加载时需要自动提交父节点属性的参数。
			otherParam : { //Ajax 请求提交的静态参数键值对。
				"otherParam" : "zTreeAsyncTest",
				"subjectid" : subjectid
			},
			dataFilter : filter
		//用于对 Ajax 返回数据进行预处理的函数。
		},
		callback: {
			onClick : onClick, //用于捕获节点被点击的事件回调函数
			onAsyncSuccess : onAsyncSuccesso
		}
	};
var treeNodez;

function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for (var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

function onAsyncSuccesso(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj("otree");
	var nodes = treeObj.getNodesByParam("parentId", 0, null);
	if (nodes.length > 0) {
		treeObj.expandNode(nodes[0], true, false, false);
	}
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("otree"),
	nodes = zTree.getSelectedNodes(),
	v = "";
	var ids = '';
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		v += nodes[i].name + ",";
		ids +=nodes[i].id + ",";
	}
	if (v.length > 0 ) v = v.substring(0, v.length-1);
	if (ids.length > 0 ) ids = ids.substring(0, ids.length-1);
	$("#citySel").val(v);
	$("#subjectId").val(ids);
	$("#itembank").empty();
	$("#itembank").append("<option value=''>请选择题库</option>");
	var ids = $("#subjectId").val();
	$.ajax({
		type : 'post',
		url : '${basepath}/item/getSelectSubject?id=' + ids,
		cache : false,
		dataType : 'json',
		success : function(data) {
			jQuery.each(data, function(i,item){
                $("#itembank").append("<option value='"+item.ID+"'>"+item.NAME+"</option>");
            });
		}
	});
}

function showMenu() {
	var cityObj = $("#citySel");
	var cityOffset = $("#citySel").offset();
	$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}

$(document).ready(function() {
	$.fn.zTree.init($("#otree"), setting);
	$("#itembank").change(function(){
		var itembankid = $("#itembank").val();
		$.ajax({
			type : 'post',
			url : '${basepath}/item/getQuestionTypeByItemBankId?id=' + itembankid,
			cache : false,
			dataType : 'json',
			success : function(data) {
				$("#sumcount").val(data.itembank.SUMTOTAL);
			}
		});
	});
		
		var jqObj = new JQvalidate();
 	    var subform ="subinfoform"; 
     	jqObj.setform(subform);
 	    jqObj.set("exercise.name", "required",  "请输入试卷名称!"); 
 	    jqObj.set("exercise.starttime", "required",  "请输入开始时间!");
 	    jqObj.set("exercise.endtime", "required",  "请输入结束时间!");
 	    jqObj.set("exercise.address", "required",  "请输入考试地点!");
 	    jqObj.set("exercise.number", "required",  "请输入考试人数!");
 	    jqObj.set("exercise.invigilatenameone", "required",  "请输入监考员一姓名!");	
 	    jqObj.set("exercise.invigilatenametwo", "required",  "请输入监考员二姓名!");	 
 	    jqObj.Run();
 	    
	});
</script>
<c:import url="/pages/include/pageFoot.jsp" />