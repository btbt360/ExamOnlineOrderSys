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
					<li><a href="#">考试管理</a> <span class="divider">/</span></li>
					<li class="active">试卷管理</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/exampapers/add">试卷列表</a></li>
			<li><a href="${basepath}/exampapers/addinfo">试卷添加</a></li>
			<li class="active"><a href="${basepath}/exampapers/addExampapersChoose">试卷选题</a></li>
			<li><a href="${basepath}/exampapers/showinfo">试卷预览</a></li>
		</ul>
			<div class="span11 text-center" style="margin-left:4%;">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>保存成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>保存失败！</strong></c:if>
				</div>
				<form id="subinfoform" class="form-horizontal" action="${basepath}/subject/savesub" method="post">
					<fieldset>
						<legend class="text-right" id="autolegend"><button type="button" id="autochoosebut" class="btn btn-primary">自动抽取</button>
						<button type="button" id="resetchoosebut" class="btn btn-primary">清除抽取</button></legend>
						<div class="control-group">
							<label class="control-label" for="exampapersid">试卷选择：</label>
							<div class="controls">
								<select class="m-wrap" id="exampapersid" name="exampapersid" placeholder="请选择试卷！">
										<option value=''>请选择试卷</option>
										<c:forEach var="exampapers" items="${exampaperslist}">
											<option value='${exampapers.id}' <c:if test="${exampapers.id==exampaperid}">selected</c:if>>${exampapers.name} | ${exampapers.code}</option>
										</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="subjectid">科目选择：</label>
							<div class="controls">
								<input id="citySel" type="text" readonly value="" style="width:120px;" onclick="showMenu(); return false;"/>
							<input name="subjectid" id ="subjectid" type="hidden" value="" >&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="questiontypeid">试题类型：</label>
							<div class="controls">
								<select class="m-wrap" id="questiontypeid" name="questiontypeid" placeholder="请选择试题类型！">
										<option value=''>请选择试题类型</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>
							</div>
						</div>
						<label class="control-label" for="itembankid">试题题库：</label>
						<div  class="text-left" id="cresscg">
						<span id="cress">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="itembankid"  name="itembankid" placeholder="请选择试题题库！" onblur="itembankchange();" multiple="multiple" class="chzn-select m-wrap">
						</select></span>&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-info btn-mini" id="query" type="button" >查询</button>
						</div>
						
					</fieldset>
				</form>
				<input type="hidden" id="sumtotal" name="sumtotal"/>
				<input type="hidden" id="sumscores" name="sumscores"/>
				<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th width="10%">序号</th>
								<th width="20%">试题编码</th>
								<th width="45%">试题标题</th>
								<th width="10%">试题分数</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						<!-- tbody是必须的 -->
				</table>
			</div>
		</div>
	</div>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color: #f5f5f5;border: 1px solid #ccc;">
		<ul id="otree" class="ztree"></ul>
	</div>
<script type="text/javascript">
var subjectid = '${subject.parentid}';
var setting = {
	view : {
		dblClickExpand : false
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
	callback : {
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
	var zTree = $.fn.zTree.getZTreeObj("otree"), nodes = zTree
			.getSelectedNodes(), v = "";
	var ids = '';
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		ids += nodes[i].id + ",";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	if (ids.length > 0)
		ids = ids.substring(0, ids.length - 1);
	$("#citySel").val(v);
	$("#subjectid").val(ids);
	$("#itembankid").empty();
	$("#itembankid").append("<option value=''>请选择题库</option>");
	var ids = $("#subjectid").val();
	$.ajax({
		type : 'post',
		url : '${basepath}/item/getSelectSubject?id=' + ids,
		cache : false,
		dataType : 'json',
		success : function(data) {
			jQuery.each(data, function(i, item) {
				$("#itembankid").append(
						"<option value='"+item.ID+"'>" + item.NAME
								+ "</option>");
			});
		}
	});
}

function showMenu() {
	var cityObj = $("#citySel");
	var cityOffset = $("#citySel").offset();
	$("#menuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

$(document).ready(
		function() {
			$.fn.zTree.init($("#otree"), setting);
		var allstr="";
		$(".chzn-select").chosen();
		
		$("#messagealert").hide();
		$("#resetchoosebut").hide();
		var flagcg = '${flagcg}';
		if(flagcg!=null&&flagcg!=''){
			$("#messagealert").show();
		}
		$("#closebut").click(function(){
			$("#messagealert").hide();
		});
		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/exampapers/finaQuestionsChoose"
		});
		$("#query").click(function(){
			reshcg();
		});
		$("#autochoosebut").click(function(){
			var exampapersid = $('#exampapersid').val();
			var subjectid = $('#subjectid').val();
			var questiontypeid = $('#questiontypeid').val();
			if(exampapersid==null||exampapersid==''){
				alert("请选择试卷！");
				return false;
			}
			if(subjectid==null||subjectid==''){
				alert("请选择科目！");
				return false;
			}
			if(questiontypeid==null||questiontypeid==''){
				alert("请选择试题类型！");
				return false;
			}
			if (confirm("是否进行自动抽题！")) {
				$.ajax({
					type : 'post',
					url : '${basepath}/exampapers/getAutochoose?exampapersid='+exampapersid+'&subjectid='+subjectid+'&questiontypeid='+questiontypeid,
					cache : false,
					dataType : 'json',
					success : function(data) {
						alert(data.message);
						reshcg();
						$("#autochoosebut").hide();
						$("#resetchoosebut").show();
					}
				});
			}
		});
		$("#resetchoosebut").click(function(){
			var exampapersid = $('#exampapersid').val();
			var subjectid = $('#subjectid').val();
			var questiontypeid = $('#questiontypeid').val();
			if(exampapersid==null||exampapersid==''){
				alert("请选择试卷！");
				return false;
			}
			if(subjectid==null||subjectid==''){
				alert("请选择科目！");
				return false;
			}
			if(questiontypeid==null||questiontypeid==''){
				alert("请选择试题类型！");
				return false;
			}
			if (confirm("是否清除已抽的题！")) {
				$.ajax({
					type : 'post',
					url : '${basepath}/exampapers/getResetAutochoose?exampapersid='+exampapersid+'&subjectid='+subjectid+'&questiontypeid='+questiontypeid,
					cache : false,
					dataType : 'json',
					success : function(data) {
						alert(data.message);
						reshcg();
						$("#autochoosebut").show();
						$("#resetchoosebut").hide();
					}
				});
			}
		});
		$("#exampapersid").change(function() {
			$.ajax({
				type : 'post',
				url : '${basepath}/exampapers/getQuestionstypesByExampapers?exampapersid='+$(this).val(),
				cache : false,
				dataType : 'json',
				success : function(data) {
					$("#questiontypeid").empty();
					 $("#questiontypeid").append("<option value=''>--全部类型--</option>");
					jQuery.each(data, function(i,item){
		                $("#questiontypeid").append("<option value='"+item.TYPE_ID+"'>"+item.TYPENAME+"</option>");
		            });
				}
			});
			
		});
		$("#questiontypeid").change(function(){
			var questiontypeid = $(this).val();
			var subjectid = $("#subjectid").val();
			$.ajax({
				type : 'post',
				url : '${basepath}/exampapers/getItembankBySubQtid?questiontypeid='+questiontypeid+'&subjectid='+subjectid,
				cache : false,
				dataType : 'json',
				success : function(data) {
					var onestr=allstr.split("|");
					if(onestr.length>0){
						for(var i = 0;i<onestr.length;i++){
							var twostr = onestr[i].split("-");
								if(twostr!=null&&twostr[0]==questiontypeid){
									$("#sumtotal").val(twostr[1]);
									$("#sumscores").val(twostr[2]);
								}
							}
					}
					$("#cress").empty();
					var html="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id='itembankid'  name='itembankid' placeholder='请选择试题题库！' multiple='multiple' class='chzn-select m-wrap'>";
					jQuery.each(data, function(i,item){
						 html=html+"<option value='"+item.ID+"'>"+item.NAME+"</option>";
		            }); 
					html=html+"</select>";
					$("#cress").append(html);
					$(".chzn-select").chosen();
					$("#autochoosebut").show();
					$("#resetchoosebut").hide();
					reshcg();
				}
			});
			
		});
		
	});
function reshcg() {
	var exampapersid = $('#exampapersid').val();
	var subjectid = $('#subjectid').val();
	var questiontypeid = $('#questiontypeid').val();
	var itembankids = $('#itembankid').val();
	var oSettings = [ {
		"name" : "exampapersid",
		"value" : exampapersid
	}, {
		"name" : "subjectid",
		"value" : subjectid
	}, {
		"name" : "questiontypeid",
		"value" : questiontypeid
	}, {
		"name" : "itembankids",
		"value" : itembankids
	}  ];
	oTable.gridSearch(this, oSettings);
}
function removebut(id,scoreid){
	var exampapersid = $('#exampapersid').val();
	if (confirm("是否从试卷内移除该题？")) {
		$.ajax({
			type : 'post',
			url : '${basepath}/exampapers/getRemoveQuestions?exampapersid='+exampapersid+'&questionid='+id,
			cache : false,
			dataType : 'json',
			success : function(data) {
				alert(data.message);
				reshcg();
			}
		});
	}
};

function addbut(id,scoreid){
	var exampapersid = $('#exampapersid').val();
	var questiontypeid = $('#questiontypeid').val();
	var score =$("#"+scoreid).val();
	if(score==null||score=='0'||score==''){
		alert("请填写分数！");
		return false;
	}
	$.ajax({
		type : 'post',
		url : '${basepath}/exampapers/getAddQuestions?exampapersid='+exampapersid+'&questionid='+id+'&score='+score+'&questiontypeid='+questiontypeid,
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