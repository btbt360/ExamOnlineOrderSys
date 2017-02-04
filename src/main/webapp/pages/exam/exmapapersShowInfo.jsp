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
			<li><a href="${basepath}/exampapers/addExampapersChoose">试卷选题</a></li>
			<li class="active"><a href="${basepath}/exampapers/showinfo">试卷预览</a></li>
		</ul>
			<div class="span11 text-center" style="margin-left:4%;">
					<fieldset>
						<legend class="text-center">试卷选择：
							<select class="m-wrap" id="exampapersid" name="exampapersid" placeholder="请选择试卷！">
									<option value=''>请选择试卷</option>
								<c:forEach var="exampapers" items="${exampaperslist}">
									<option value='${exampapers.id}'>${exampapers.name} | ${exampapers.code}</option>
								</c:forEach>
							</select>
						</legend>
						<div class="span12 text-center">
							<div class="span6">
								<label class="control-label" for="name">试卷名称：<span id="exampapersname"></span></label> 
							</div>
							<div class="span6">
								<label class="control-label" for="subjectid">试卷编码：<span id="exampaperscode"></span></label> 
							</div>
						</div>
						<div class="span12 text-center">
							<div class="span6">
								<label class="control-label" for="questiontype">试卷总分数：<span id="sumscore"></span>分</label> 
							</div>
							<div class="span6">
								<label class="control-label" for="subjectid">试卷总题数：<span id="sumquestion"></span>题</label> 
							</div>
						</div>
					</fieldset>
			</div>
	</div>
	</div>
			<div class="block" id="danxuan" >
			<a href="#" onmouseover="this.style.cursor='hand'">
               <div class="navbar navbar-inner block-header">
                   <div class="muted pull-left">单项选择题</div>
               </div>
            </a>
               <div class="block-content collapse in" id="danxuanmiss">
                   <div class="span12">
  					<table class="table table-bordered">
						 <thead>
						     <tr>
						       <td width="20%"><strong>试题编码</strong></td>
						       <td width="60%"><strong>试题标题</strong></td>
						       <td width="20%"><strong>试题分数</strong></td>
						     </tr>
						 </thead>
						 <tbody id="danxuantbody">
						 </tbody>
					</table>
                   </div>
               </div>
             </div>
             <div class="block" id="duoxuan">
             <a href="#" onmouseover="this.style.cursor='hand'">
               <div class="navbar navbar-inner block-header">
                   <div class="muted pull-left">多项选择题</div>
               </div>
             </a>
               <div class="block-content collapse in" id="duoxuanmiss">
                   <div class="span12">
  					<table class="table table-bordered">
						 <thead>
						     <tr>
						       <td width="20%"><strong>试题编码</strong></td>
						       <td width="60%"><strong>试题标题</strong></td>
						       <td width="20%"><strong>试题分数</strong></td>
						     </tr>
						 </thead>
						 <tbody id="duoxuantbody">
						 </tbody>
					</table>
                   </div>
               </div>
             </div>
             <div class="block" id="panduan">
             <a href="#" onmouseover="this.style.cursor='hand'">
               <div class="navbar navbar-inner block-header">
                   <div class="muted pull-left">判断题</div>
               </div>
              </a>
               <div class="block-content collapse in" id="panduanmiss">
                   <div class="span12">
  					<table class="table table-bordered">
						 <thead>
						     <tr>
						       <td width="20%"><strong>试题编码</strong></td>
						       <td width="60%"><strong>试题标题</strong></td>
						       <td width="20%"><strong>试题分数</strong></td>
						     </tr>
						 </thead>
						 <tbody id="panduantbody">
						 </tbody>
					</table>
                   </div>
               </div>
             </div>
             <div class="block" id="wenda">
             <a href="#" onmouseover="this.style.cursor='hand'">
               <div class="navbar navbar-inner block-header">
                   <div class="muted pull-left">问答题</div>
               </div>
             </a>
               <div class="block-content collapse in" id="wendamiss">
                   <div class="span12">
  					<table class="table table-bordered">
						 <thead>
						     <tr>
						       <td width="20%"><strong>试题编码</strong></td>
						       <td width="60%"><strong>试题标题</strong></td>
						       <td width="20%"><strong>试题分数</strong></td>
						     </tr>
						 </thead>
						 <tbody id="wendatbody">
						 </tbody>
					</table>
                   </div>
               </div>
             </div>
             <div class="block" id="tiankong">
             <a href="#" onmouseover="this.style.cursor='hand'">
               <div class="navbar navbar-inner block-header">
                   <div class="muted pull-left">填空题</div>
               </div>
             </a>
               <div class="block-content collapse in" id="tiankongmiss">
                   <div class="span12">
  					<table class="table table-bordered">
						 <thead>
						     <tr>
						       <td width="20%"><strong>试题编码</strong></td>
						       <td width="60%"><strong>试题标题</strong></td>
						       <td width="20%"><strong>试题分数</strong></td>
						     </tr>
						 </thead>
						 <tbody id="tiankongtbody">
						 </tbody>
					</table>
                   </div>
               </div>
             </div>

<script type="text/javascript">
$(document).ready(function() {
		$("#danxuan").hide();
		$("#duoxuan").hide();
		$("#panduan").hide();
		$("#wenda").hide();
		$("#tiankong").hide();
		
		var danxuanboolean = false;
		var duoxuanboolean = false;
		var panduanboolean = false;
		var wendaboolean = false;
		var tiankongboolean =false;
		
		$("#exampapersid").change(function() {
			$.ajax({
				type : 'post',
				url : '${basepath}/exampapers/getExampapersByid?exampapersid='+$(this).val(),
				cache : false,
				dataType : 'json',
				success : function(data) {
					$("#exampapersname").html(data.exampapers.NAME);
					$("#exampaperscode").html(data.exampapers.CODE);
					$("#sumscore").html(data.exampapers.SUMSCORE);
					$("#sumquestion").html(data.exampapers.SUMQUESTION);
					istype();
				}
			});
			
		});
		$("#danxuan").click(function(){
			if(danxuanboolean){
				danxuanboolean = false;
				$("#danxuanmiss").show();
			}else{
				danxuanboolean = true;
				$("#danxuanmiss").hide();
			}
		});
		$("#duoxuan").click(function(){
			if(duoxuanboolean){
				duoxuanboolean = false;
				$("#duoxuanmiss").show();
			}else{
				duoxuanboolean = true;
				$("#duoxuanmiss").hide();
			}
		});
		$("#panduan").click(function(){
			if(panduanboolean){
				panduanboolean = false;
				$("#panduanmiss").show();
			}else{
				panduanboolean = true;
				$("#panduanmiss").hide();
			}
		});
		$("#wenda").click(function(){
			if(wendaboolean){
				wendaboolean = false;
				$("#wendamiss").show();
			}else{
				wendaboolean = true;
				$("#wendamiss").hide();
			}
		});
		$("#tiankong").click(function(){
			if(tiankongboolean){
				tiankongboolean = false;
				$("#tiankongmiss").show();
			}else{
				tiankongboolean = true;
				$("#tiankongmiss").hide();
			}
		});
	});
function istype(){
	var exampapersid = $("#exampapersid").val();
	$.ajax({
		type : 'post',
		url : '${basepath}/exampapers/getQuestionsByExampaperid?exampapersid='+exampapersid,
		cache : false,
		dataType : 'json',
		success : function(data) {
			$("#danxuan").hide();
			$("#duoxuan").hide();
			$("#panduan").hide();
			$("#wenda").hide();
			$("#tiankong").hide();
			$("#danxuantbody").empty();
			$("#duoxuantbody").empty();
			$("#panduantbody").empty();
			$("#wendatbody").empty();
			$("#tiankongtbody").empty();
			jQuery.each(data, function(i,item){
				var html = "<tr><td>"+item[0]+"</td><td>"+item[1]+"</td><td>"+item[2]+"</td></tr>";
				switch(Number(item[3]))
				{
				case 1:
					$("#danxuan").show();
					$("#danxuantbody").append(html);
				  break;
				case 2:
					$("#duoxuan").show();
					$("#duoxuantbody").append(html);
				  break;
				case 3:
					$("#panduan").show();
					$("#panduantbody").append(html);
				  break;
				case 4:
					$("#wenda").show();
					$("#wendatbody").append(html);
				  break;
				case 5:
					$("#tiankong").show();
					$("#tiankongtbody").append(html);
			      break;					  
				default:
				  
				}
            });
		}
	});
}
</script>
<c:import url="/pages/include/pageFoot.jsp" />