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
			<li class="active"><a href="${basepath}/exampapers/addinfo">试卷添加</a></li>
			<li><a href="${basepath}/exampapers/addExampapersChoose">试卷选题</a></li>
			<li><a href="${basepath}/exampapers/showinfo">试卷预览</a></li>
		</ul>
			<div class="span12">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>保存成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>保存失败！</strong></c:if>
				</div>
				<form id="exampapersinfoform" class="form-horizontal" action="${basepath}/exampapers/save" method="post">
					<fieldset>
						<legend>试卷管理</legend>
						<div class="control-group">
							<label class="control-label">试卷编码：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="code" name="exampapers.code"
									type="text" placeholder="请输入试卷编码!" value="${exampapers.code}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">试卷名称：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="name" name="exampapers.name"
									type="text" placeholder="请输入试卷名称!" value="${exampapers.name}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="mobile">试卷总题数：</label>
							<div class="controls">
								<input id="sumquestion" class="input-xlarge focused" name="exampapers.sumquestion" placeholder="请输入试卷总题数!"  type="text" value="${exampapers.sumquestion}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">试卷总分：</label>
							<div class="controls">
							<input name="exampapers.id" type="hidden" value="${exampapers.id}" >
							<input name="numcount" id="numcount" type="hidden" value="${numcount}" >
								<input class="input-xlarge focused" id="sumscore" name="exampapers.sumscore" type="text" placeholder="请输入试卷总分!" value="${exampapers.sumscore}" >
							</div>
						</div>
						<div class="control-group" id="operationadd">
								<label class="control-label">试题选项添加：</label>
								<div class="controls">
									<button class="btn btn-success" onclick="addbutton()" type="button"><i class="icon-plus-sign icon-white"></i> 添加</button>
									&nbsp;&nbsp;&nbsp;&nbsp;<font color ="red">温馨提示：试题选项每种类型只能选择一次！</font>
								</div>
								<div id="cress"></div>
						</div>
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="login_flag0">&nbsp;&nbsp;是&nbsp;&nbsp;
								<input type="radio" id="login_flag0" value="1" name="exampapers.isenable" checked
								<c:if test="${exampapers.isenable==1}">checked</c:if> />
								</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="login_flag1">&nbsp;&nbsp;否&nbsp;&nbsp;
								<input type="radio" id="login_flag1" value="0" name="exampapers.isenable"
								<c:if test="${exampapers.isenable==0}">checked</c:if> />
								</label>
							</div>
						</div> 
						<div class="form-actions">
							<button type="submit" class="btn btn-primary" id="savebutton">保存</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/exampapers/add'">
						</div>
					</fieldset>
				</form>
			</div>
		</div>
</div>
<script type="text/javascript">
var num = 0 ;
var typeids="";
$(document).ready(function() {
		$("#messagealert").hide();
		var flagcg = '${flagcg}';
		if(flagcg!=null&&flagcg!=''){
			$("#messagealert").show();
		}
		$("#closebut").click(function(){
			$("#messagealert").hide();
		});
		
		var questiontypelist ='${questiontypelist}';
		if(questiontypelist!=null){
			var listsize=0;
			if(questiontypelist.length>0){
				var addhtml='<br/><c:forEach var="questiontype" items="${questiontypelist}" varStatus="status">'+
				'<div class="span12  text-center" id="options${status.index}" style="margin-top:1%;">'+
				'<div class="span1"></div><div class="span8">'+
				'类型选择：<select class="m-wrap" id="questiontype_${status.index}" name="questiontype${status.index}" placeholder="请选择试题类型！"><option value="">请选择试题类型</option>'+
				'<c:forEach var="dict" items="${dictlist}"><option value="${dict.dictkey}" <c:if test="${questiontype.typeId==dict.dictkey}">selected</c:if>>${dict.dictvalue}</option></c:forEach></select>&nbsp;&nbsp;'+
				'类型试题总数：<input class="input-mini focused" id="sumquestion_${status.index}" name="sumquestion${status.index}" type="text" value="${questiontype.sumtotal}">&nbsp;&nbsp;'+
				'类型试题总分数：<input class="input-mini focused" id="sumscore_${status.index}" name="sumscore${status.index}" type="text" value="${questiontype.sumscores}"></div>'+
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
	 		$("#numcount").val(num);
	 		var sumquestioncg = 0;
	 		var sumcorecg = 0;
	 		var sumquestion = $("#sumquestion").val()==''?0:$("#sumquestion").val();
	 		var sumscore = $("#sumscore").val()==''?0:$("#sumscore").val();
	 		$("input[id^='sumquestion_']").each(function(i){
	 			sumquestioncg = Number(sumquestioncg)+Number($(this).val());
	 	    });
	 		$("input[id^='sumscore_']").each(function(i){
	 			sumcorecg =  Number(sumcorecg)+Number($(this).val());
	 	    });
	 		if(Number(sumquestion)!=sumquestioncg){
	 			alert("试题总数量出错，请查证！");
	 			$("#sumquestion").focus();
	 			return false;
	 		}
			if(Number(sumscore)!=sumcorecg){
				alert("试题总分数出错，请查证！");
				$("#sumscore").focus();
				return false;
	 		}
	 	 });
		var jqObj = new JQvalidate();
 	    var subform ="exampapersinfoform"; 
     	jqObj.setform(subform);
 	    jqObj.set("exampapers.code", "required",  "请输入试卷编码!");  
 	    jqObj.set("exampapers.name", "required",  "请输入试卷名称!");	 
 	    jqObj.set("exampapers.sumscore", "required",  "请输入试卷总分!");
 	    jqObj.set("exampapers.sumquestion", "required",  "请输入试卷总题数!");
 	    
 	    jqObj.Run();
	});

function addbutton(){
	var ssr='';
	var tines= 0;
	var sumq = 0;
	var sums = 0;
	var sumquestion = $("#sumquestion").val()==''?0:$("#sumquestion").val();
	var sumscore = $("#sumscore").val()==''?0:$("#sumscore").val();
	$("select[id^='questiontype_']").each(function(i){
		if($(this).val()==null||$(this).val()==''){
			alert("请选择试题类型");
			$(this).focus();
			tines = -1;
			return false;
		}else{
			ssr=ssr+"|"+ $(this).val();
		}
    });
	if(ssr){
		for(var i=1;i<6;i++){
			if((ssr.split(i).length-1)>1){
				alert("试题选项每种类型只能选择一次!");
				tines = -1;
				return false;
			}
		}
	}
	if(tines<0){
		return false;
	}
	$("input[id^='sumquestion_']").each(function(i){
		
		if($(this).val()==null||$(this).val()==''){
			alert("请输入类型试题总数");
			$(this).focus();
			tines = -1;
			return false;
		}else{
			sumq =Number(sumq)+Number($(this).val());
		}
    });
	if(sumquestion<sumq){
		alert("类型试题总数大于试卷总题数");
		return false;
	}
	if(tines<0){
		return false;
	}
	
	$("input[id^='sumscore_']").each(function(i){
		if($(this).val()==null||$(this).val()==''){
			alert("请输入类型试题总分数");
			$(this).focus();
			tines = -1;
			return false;
		}else{
			sums =Number(sums)+Number($(this).val());
		}
    });
	if(sumscore<sums){
		alert("类型试题总分数大于试卷总分数");
		return false;
	}
	if(tines<0){
		return false;
	}
	num=num+1;
	var addhtml='<div class="span12 text-center" id="options'+num+'" style="margin-top:1%;">'+
	'<div class="span1"></div>'+
	'<div class="span8">'+
	'类型选择：<select class="m-wrap" id="questiontype_'+num+'" name="questiontype'+num+'" placeholder="请选择试题类型！"><option value="">请选择试题类型</option>'+
	'<c:forEach var="dict" items="${dictlist}"><option value="${dict.dictkey}">${dict.dictvalue}</option></c:forEach></select>&nbsp;&nbsp;'+
	'类型试题总数：<input class="input-mini focused"  id="sumquestion_'+num+'" name="sumquestion'+num+'" type="text" >&nbsp;&nbsp;'+
	'类型试题总分数：<input class="input-mini focused" id="sumscore_'+num+'" name="sumscore'+num+'" type="text" ></div>'+
	'<div class="span3 text-left">'+
	'&nbsp;&nbsp;<button class="btn btn-danger" onclick="deloption('+num+')" type="button"><i class="icon-remove icon-white"></i> 删除</button>'+
	'</div></div>';
	$("#cress").append(addhtml);
	
};
function deloption(numcgg){
	$("#options"+numcgg).remove();
}
</script>
<c:import url="/pages/include/pageFoot.jsp" />