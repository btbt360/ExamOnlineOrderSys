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
					<li class="active">试题管理</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/questions/add">试题列表</a></li>
			<li class="active"><a href="${basepath}/questions/addinfo">试题添加</a></li>
		</ul>
			<div class="span12">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>保存成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>保存失败！</strong></c:if>
				</div>
				<form id="questioninfoform" class="form-horizontal" action="${basepath}/questions/save" method="post">
					<fieldset>
						<legend>试题管理</legend>
						<div class="control-group">
							<label class="control-label" >属于科目：</label>												    
						  <div class="controls"> 
						  <input id="citySel" type="text" readonly value="${subjectname}" style="width:120px;" onclick="showMenu(); return false;"/>
							<input name="questions.subjectId" id ="subjectId" type="hidden" value="${questions.subjectId}" >
							<input name="questions.id" type="hidden" value="${questions.id}" >
							<input name="numcount" id="numcount" type="hidden" value="${numcount}" >
						</div>	
						</div>
						<div class="control-group">
							<label class="control-label" >属于题库：</label>												    
						  <div class="controls"> 
						  	<select class="span3 m-wrap" id="itembank" name="questions.itembankId"  placeholder="请选择题库！" >
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
						  	<input class="input-xlarge uneditable-input"id="questiontype" name="questions.questiontype"
									type="hidden" value="${questions.questiontype}" >
						</div>	
						</div>
						<%-- <div class="control-group">
							<label class="control-label">试题编码：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="code" name="questions.code"
									type="text" placeholder="请输入试题编码!" value="${questions.code}" >
							</div>
						</div> --%>
						<div class="control-group" >
							<label class="control-label">试题内容：</label>
							<div class="controls">
								<textarea id="cgtitle" name="questions.title">${questions.title}</textarea>
							</div>
						</div>
						<div id="content"></div>
						<c:choose>
						<c:when test="${questions.questiontype<3}">
							<div class="control-group" id="operationadd">
								<label class="control-label">选项添加：</label>
								<div class="controls">
									<button class="btn btn-success" onclick="addbutton()" type="button"><i class="icon-plus-sign icon-white"></i> 添加</button>
									<div id="cress"></div>
								</div>
							</div>
							<div class="control-group"  id="operationanswer">
								<label class="control-label">试题答案：</label>
								<div class="controls">
									<input class="input-xlarge focused" id="questionanswer" name="questions.questionanswer"
									type="text" value="${questions.questionanswer}" placeholder="请输入试题答案!">
								</div>
							</div>
						</c:when>
						<c:when test="${questions.questiontype==3}">
							<div class="control-group" id="panduananswer">
								<label class="control-label">试题答案：</label>
								<div class="controls">
									<label for="questionanswer">&nbsp;&nbsp;对&nbsp;&nbsp;
									<input type="radio" id="questionanswer0" value="1" name="questions.questionanswer" checked
									<c:if test="${questions.questionanswer=='1'}">checked</c:if> />
									</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="questionanswer1">&nbsp;&nbsp;错&nbsp;&nbsp;
									<input type="radio" id="questionanswer1" value="0" name="questions.questionanswer"
									<c:if test="${questions.questionanswer=='0'}">checked</c:if> />
									</label>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="control-group" id="wendaanswer">
								<label class="control-label">试题答案：</label>
								<div class="controls">
									<textarea id="questionanswer" name="questions.questionanswer" placeholder="请输入试题答案!" rows="10" style="width: 50%;" >${questions.questionanswer}</textarea>
								</div>
							</div>
						</c:otherwise>
						</c:choose>
						<div class="control-group">
								<label class="control-label">试题描述：</label>
								<div class="controls">
									<textarea id="info" name="questions.info" placeholder="请输入试题描述!" rows="10" style="width: 50%;" >${questions.info}</textarea>
								</div>
						</div>
						<div class="control-group">
								<label class="control-label">试题解答：</label>
								<div class="controls">
									<textarea id="questionanswerinfo" name="questions.questionanswerinfo" placeholder="请输入试题解答!" rows="10" style="width: 50%;" >${questions.questionanswerinfo}</textarea>
								</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="login_flag0">&nbsp;&nbsp;是&nbsp;&nbsp;
								<input type="radio" id="login_flag0" value="1" name="questions.isenable" checked
								<c:if test="${questions.isenable==1}">checked</c:if> />
								</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="login_flag1">&nbsp;&nbsp;否&nbsp;&nbsp;
								<input type="radio" id="login_flag1" value="0" name="questions.isenable"
								<c:if test="${questions.isenable==0}">checked</c:if> />
								</label>
							</div>
						</div> 
							
						<div class="form-actions">
							<button type="submit" id="savebutton" class="btn btn-primary" >保存</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/case/add'">
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
	
		var editor = null;
		editor = CKEDITOR.replace('questions.title'); //参数‘content’是textarea元素的name属性值，而非id属性值
		
		$("#messagealert").hide();
		var flagcg = '${flagcg}';
		if(flagcg!=null&&flagcg!=''){
			$("#messagealert").show();
		}
		$("#closebut").click(function(){
			$("#messagealert").hide();
		});
		
		
		var questionoptionslist ='${questionoptionslist}';
		if(questionoptionslist!=null){
			var listsize=0;
			if(questionoptionslist.length>0){
				var addhtml='<br/><c:forEach var="questionoptions" items="${questionoptionslist}" varStatus="status">'+
				'<div class="span11" id="options${status.index}" style="margin-top:1%;">'+
				'<div class="span8">'+
				'选项编码：<input class="input-mini focused" id="intitle_${status.index}" name="code${status.index}" type="text" value="${questionoptions.code}">&nbsp;&nbsp;'+
				'选项内容：<input class="input-xlarge focused" id="incontant_${status.index}" name="contant${status.index}" type="text" value="${questionoptions.contant}"></div>'+
				'<div class="span3 text-left">&nbsp;&nbsp;'+
				'<button class="btn btn-danger" onclick="deloption(${status.index})" type="button"><i class="icon-remove icon-white"></i> 删除</button><input type="hidden" id="indexcount${status.count}" value="${status.count}">'+
				'</div></div></c:forEach>';
				$("#cress").append(addhtml);
			}
			$("input[id^='indexcount']").each(function(i){
				listsize = $(this).val();
		    });
			num = num + Number(listsize);
		}
		  $("#savebutton").click(function(){
	 		  if(editor.getData()==null||editor.getData()==''){
	 			  alert("请填写试题内容！");
	 			  return false;
	 		  }
	 		$("#numcount").val(num);
	 		editor.updateElement();
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
					$("#content").empty();
					if(data.questiontype.DICTKEY<3){
						$("#panduananswer").remove();
						$("#wendaanswer").remove();
						$("#operationadd").remove();
						$("#operationanswer").remove();
						var cghtml='<div class="control-group" id="operationadd">'+
						'<label class="control-label">选项添加：</label>'+
						'<div class="controls">'+
						'<button class="btn btn-success" onclick="addbutton()" type="button"><i class="icon-plus-sign icon-white"></i> 添加</button><div id="cress"></div></div></div>'+
						'<div class="control-group"  id="operationanswer">'+
						'<label class="control-label">试题答案：</label>'+
						'<div class="controls">'+
						'<input class="input-xlarge focused" id="questionanswer" name="questions.questionanswer" type="text" value="" placeholder="请输入试题答案!">'+
						'</div></div>';
						$("#content").append(cghtml);
					}else if(data.questiontype.DICTKEY==3){
						$("#panduananswer").remove();
						$("#wendaanswer").remove();
						$("#operationadd").remove();
						$("#operationanswer").remove();
							var cghtml='<div class="control-group" id="panduananswer">'+
							'<label class="control-label">试题答案：</label>'+
							'<div class="controls">'+
							'<label for="questionanswer">&nbsp;&nbsp;对&nbsp;&nbsp;<input type="radio" id="questionanswer0" value="1" name="questions.questionanswer" checked'+
							'<c:if test="${questions.questiontype==3&&questions.questionanswer==1}">checked</c:if> />'+
							'</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="questionanswer1">&nbsp;&nbsp;错&nbsp;&nbsp;'+
							'<input type="radio" id="questionanswer1" value="0" name="questions.questionanswer"'+
							'<c:if test="${questions.questiontype==3&&questions.questionanswer==0}">checked</c:if> />'+
							'</label></div></div>';
							$("#content").append(cghtml);
					}else{
						$("#panduananswer").remove();
						$("#wendaanswer").remove();
						$("#operationadd").remove();
						$("#operationanswer").remove();
						var cghtml='<div class="control-group" id="wendaanswer">'+
						'<label class="control-label">试题答案：</label>'+
						'<div class="controls">'+
						'<textarea id="questionanswer" name="questions.questionanswer" placeholder="请输入试题答案!" rows="10" style="width: 50%;" ></textarea>'+
						'</div></div>';
						$("#content").append(cghtml);
					}
				}
			});
		});
		
		var jqObj = new JQvalidate();
 	    var subform ="questioninfoform"; 
     	jqObj.setform(subform);
 	    jqObj.set("questions.subjectId", "required",  "请选择科目!");  
 	    jqObj.set("questions.itembankId", "required",  "请选择题库!");	 
 	  //  jqObj.set("questions.code", "required",  "请输入试题编码!");
 	    jqObj.set("questions.questionanswer", "required",  "请输入试题答案!");
 	    jqObj.Run();
 	 
 	    
	})
function addbutton(){
			var tines= 0;
			$("input[id^='intitle_']").each(function(i){
				if($(this).val()==null||$(this).val()==''){
					alert("请输入选项编码");
					$(this).focus();
					tines = -1;
					return false;
				}
		    }); 
			if(tines<0){
				return false;
			}
			$("input[id^='incontant_']").each(function(i){
				if($(this).val()==null||$(this).val()==''){
					alert("请输入选项内容");
					$(this).focus();
					tines = -1;
					return false;
				}
		    });
			if(tines<0){
				return false;
			}
			num=num+1;
			var addhtml='<div class="span12 text-center" id="options'+num+'" style="margin-top:1%;">'+
			'<div class="span1"></div>'+
			'<div class="span7">'+
			'选项编码：<input class="input-mini focused"  id="intitle_'+num+'" name="code'+num+'" type="text" >&nbsp;&nbsp;'+
			'选项内容：<input class="input-xlarge focused" id="incontant_'+num+'" name="contant'+num+'" type="text" ></div>'+
			'<div class="span4 text-left">'+
			'<button class="btn btn-danger" onclick="deloption('+num+')" type="button"><i class="icon-remove icon-white"></i> 删除</button>'+
			'</div></div>';
			$("#operationadd").append(addhtml);
			
		};
function deloption(numcgg){
	$("#options"+numcgg).remove();
}
</script>
<c:import url="/pages/include/pageFoot.jsp" />