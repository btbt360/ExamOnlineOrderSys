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
					<li><a href="#">资源管理</a> <span class="divider">/</span></li>
					<li class="active">题库管理</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/item/add">题库列表</a></li>
			<li><a href="${basepath}/item/addinfo">题库添加</a></li>
			<li class="active"><a href="${basepath}/item/importExcel">试题导入</a></li>
		</ul>
			<div class="span12">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>上传成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>上传出现错误，请稍后再上传</strong></c:if>
						<c:if test="${flagcg==-1}"><strong>文件写入服务器出现错误，请稍后再上传</strong></c:if>
				</div>
				<form id="itemform" class="form-horizontal" action="${basepath}/item/uploadExcel" method="post" enctype="multipart/form-data">
					<fieldset>
						<legend>题库导入</legend>
						<div class="control-group">
							<label class="control-label" >属于科目：</label>												    
						  <div class="controls">
								<input id="citySel" type="text" readonly value="${subjectname}" style="width:120px;" onclick="showMenu(); return false;"/>
								<input name="subjectid" id ="subjectid" type="hidden" value="${subjectid}" >
						</div>	
						</div>
						<div class="control-group">
							<label class="control-label" >属于题库：</label>												    
						  <div class="controls"> 
						  	<select class="span3 m-wrap" id="itembank" name="itembankId"  placeholder="请选择题库！" >
								<option value=''>请选择题库</option>
								<c:if test="${itembank!=null}">
									<option value='${itembank.id}' selected>${itembank.name}</option>
								</c:if>
							</select> 
						</div>	
						</div>
						<div class="control-group">
							<label class="control-label" >试题类型：</label>												    
						  <div class="controls">
						  <input class="input-xlarge uneditable-input" id="questiontypename" name="questiontypename"
									type="text" value="${questiontypename}" readonly>
						  	<input class="input-xlarge uneditable-input"id="questiontype" name="questiontype"
									type="hidden" value="${questions.questiontype}" >
						</div>	
						</div>
						<div class="control-group">
						<label class="control-label" >模板下载：</label>	
							<button type="button" id="editoff" class="btn btn-primary" style="margin-left:20px;" onclick="downFile();">下载模版</button>
						</div>
						<div class="control-group">
						<label class="control-label" >导入试题：</label>	
							<input class="input-xlarge uneditable-input" id="file" name="file"
									type="file"  style="margin-left:20px;">
						</div>
						<div class="form-actions">
							<button type="submit" id="savebutton" class="btn btn-primary" >上传</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/item/add'">
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<div class="block" id="error">
               <div class="navbar navbar-inner block-header">
                   <div class="muted pull-left">错误的导入数据</div>
               </div>
               <div class="block-content collapse in">
                   <div class="span12">
  					<table class="table table-bordered">
  						<c:if test="${listdax!=null&&listdax!=''}">
  						 <thead>
						     <tr>
						       <td ><strong>试题内容</strong></td>
						       <td ><strong>试题描述</strong></td>
						       <td ><strong>试题选项A</strong></td>
						       <td ><strong>试题选项B</strong></td>
						       <td ><strong>试题选项C</strong></td>
						       <td ><strong>试题选项D</strong></td>
						       <td ><strong>试题答案</strong></td>
						       <td ><strong>试题答案解答</strong></td>
						       <td ><strong>备注信息</strong></td>
						     </tr>
						 </thead>
						 <tbody>
						 <c:forEach var="dax" items="${listdax}">
						 	<tr>
						 		<td>${dax.qtitle}</td>
						 		<td>${dax.qinfo}</td>
						 		<td>${dax.optionA}</td>
						 		<td>${dax.optionB}</td>
						 		<td>${dax.optionC}</td>
						 		<td>${dax.optionD}</td>
						 		<td>${dax.answer}</td>
						 		<td>${dax.answerinfo}</td>
						 		<td>${dax.remark}</td>
						 	</tr>
						 </c:forEach>
						 </tbody>
						 </c:if>
						 <c:if test="${listdox!=null&&listdox!=''}">
						 <thead>
						     <tr>
						       <td ><strong>试题内容</strong></td>
						       <td ><strong>试题描述</strong></td>
						       <td ><strong>试题选项A</strong></td>
						       <td ><strong>试题选项B</strong></td>
						       <td ><strong>试题选项C</strong></td>
						       <td ><strong>试题选项D</strong></td>
						       <td ><strong>试题选项E</strong></td>
						       <td ><strong>试题选项F</strong></td>
						       <td ><strong>试题选项G</strong></td>
						       <td ><strong>试题答案</strong></td>
						       <td ><strong>试题答案解答</strong></td>
						       <td ><strong>备注信息</strong></td>
						     </tr>
						 </thead>
						 <tbody>
						 <c:forEach var="dox" items="${listdox}">
						 	<tr>
						 		<td>${dax.qtitle}</td>
						 		<td>${dax.qinfo}</td>
						 		<td>${dax.optionA}</td>
						 		<td>${dax.optionB}</td>
						 		<td>${dax.optionC}</td>
						 		<td>${dax.optionD}</td>
						 		<td>${dax.optionE}</td>
						 		<td>${dax.optionF}</td>
						 		<td>${dax.optionG}</td>
						 		<td>${dax.answer}</td>
						 		<td>${dax.answerinfo}</td>
						 		<td>${dax.remark}</td>
						 	</tr>
						 </c:forEach>
						 </tbody>
						 </c:if>
						 <c:if test="${listpd!=null&&listpd!=''}">
						 <thead>
						     <tr>
						       <td ><strong>试题内容</strong></td>
						       <td ><strong>试题描述</strong></td>
						       <td ><strong>试题答案</strong></td>
						       <td ><strong>试题答案解答</strong></td>
						       <td ><strong>备注信息</strong></td>
						     </tr>
						 </thead>
						 <tbody>
						 <c:forEach var="pd" items="${listpd}">
						 	<tr>
						 		<td>${pd.qtitle}</td>
						 		<td>${pd.qinfo}</td>
						 		<td>${pd.answer}</td>
						 		<td>${pd.answerinfo}</td>
						 		<td>${pd.remark}</td>
						 	</tr>
						 </c:forEach>
						 </tbody>
						 </c:if>
						 <c:if test="${listtk!=null&&listtk!=''}">
						 <thead>
						     <tr>
						       <td ><strong>试题内容</strong></td>
						       <td ><strong>试题描述</strong></td>
						       <td ><strong>试题答案</strong></td>
						       <td ><strong>试题答案解答</strong></td>
						       <td ><strong>备注信息</strong></td>
						     </tr>
						 </thead>
						 <tbody>
						 <c:forEach var="tk" items="${listtk}">
						 	<tr>
						 		<td>${tk.qtitle}</td>
						 		<td>${tk.qinfo}</td>
						 		<td>${tk.answer}</td>
						 		<td>${tk.answerinfo}</td>
						 		<td>${tk.remark}</td>
						 	</tr>
						 </c:forEach>
						 </tbody>
						 </c:if>
						 <c:if test="${listwd!=null&&listwd!=''}">
						<thead>
						     <tr>
						       <td ><strong>试题内容</strong></td>
						       <td ><strong>试题描述</strong></td>
						       <td ><strong>试题答案</strong></td>
						       <td ><strong>试题答案解答</strong></td>
						       <td ><strong>备注信息</strong></td>
						     </tr>
						 </thead>
						 <tbody>
						 <c:forEach var="wd" items="${listwd}">
						 	<tr>
						 		<td>${wd.qtitle}</td>
						 		<td>${wd.qinfo}</td>
						 		<td>${wd.answer}</td>
						 		<td>${wd.answerinfo}</td>
						 		<td>${wd.remark}</td>
						 	</tr>
						 </c:forEach>
						 </tbody>
						 </c:if>
					</table>
                   </div>
               </div>
             </div>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color: #f5f5f5;border: 1px solid #ccc;">
		<ul id="otree" class="ztree"></ul>
	</div>
<script type="text/javascript">
var num =0;
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
	$("#subjectid").val(ids);
	$("#itembank").empty();
	$("#itembank").append("<option value=''>请选择题库</option>");
	var ids = $("#subjectid").val();
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
		
		$("#messagealert").hide();
		var flagcg = '${flagcg}';
		if(flagcg!=null&&flagcg!=''){
			$("#messagealert").show();
		}
		$("#closebut").click(function(){
			$("#messagealert").hide();
		});
		
		$("#subjectid").change(function(){
			$("#itembank").empty();
			$("#itembank").append("<option value=''>请选择题库</option>");
			var ids = $("#subjectid").val();
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
		});
		
		$("#itembank").change(function(){
			$("#questiontypename").val('');
			$("#questiontype").val('');
			var itembankid = $("#itembank").val();
			$.ajax({
				type : 'post',
				url : '${basepath}/item/getQuestionTypeByItemBankId?id=' + itembankid,
				cache : false,
				dataType : 'json',
				success : function(data) {
					$("#questiontypename").val(data.questiontype.DICTVALUE);
					$("#questiontype").val(data.questiontype.DICTKEY);
				}
			});
		});
		
		$('#savebutton').click(function() {
			var file = document.getElementById('file'); 
			if (file.value == "") { 
	            alert("请选择您需要上传的文件！"); 
	            return false;
	         }else{
	        	var jqObj = new JQvalidate();
	       	    var itemform ="itemform"; 
	           	jqObj.setform(subform);
	       	    jqObj.set("subjectid", "required",  "请选择科目!");  
	       	    jqObj.set("itembankId", "required",  "请选择题库!");
	       	    jqObj.Run();
	         }
		});
	})
	function downFile(){
		var subjectid=$("#subjectid").val();
		var itembank=$("#itembank").val();
		var questiontype = $("#questiontype").val();
		if(subjectid==null||subjectid==''){
			alert("请选择科目");
			$("#subjectid").focus();
			return false;
		}
		if(itembank==null||itembank==''){
			alert("请选择题库");
			$("#itembank").focus();
			return false;
		}
	    window.location.href="${basepath}/item/downloadExcel?questiontype="+questiontype;
    }

	function deloption(numcgg){
		$("#options"+numcgg).remove();
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />