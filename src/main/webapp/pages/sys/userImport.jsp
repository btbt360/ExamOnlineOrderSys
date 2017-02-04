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
					<li><a href="#">系统管理</a> <span class="divider">/</span></li>
					<li class="active">用户管理</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/user/add">用户列表</a></li>
			<li><a href="${basepath}/user/adduserinfo">用户添加</a></li>
			<li class="active"><a href="${basepath}/user/importUser">用户导入</a></li>
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
				<form id="itemform" class="form-horizontal" action="${basepath}/user/uploadExcel" method="post" enctype="multipart/form-data">
					<fieldset>
						<div class="control-group">
						<label class="control-label" >模板下载：</label>	
							<button type="button" id="editoff" class="btn btn-primary" style="margin-left:20px;" onclick="downFile();">下载模版</button>
						</div>
						<div class="control-group">
						<label class="control-label" >导入用户：</label>	
							<input class="input-xlarge uneditable-input" id="file" name="file"
									type="file"  style="margin-left:20px;">
						</div>
						<div class="form-actions">
							<button type="submit" id="savebutton" class="btn btn-primary" >上传</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/user/add'">
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
  						<c:if test="${listuser!=null&&listuser!=''}">
  						 <thead>
						     <tr>
						       <td ><strong>单位</strong></td>
						       <td ><strong>姓名</strong></td>
						       <td ><strong>班级</strong></td>
						       <td ><strong>工作岗位</strong></td>
						       <td ><strong>职务</strong></td>
						       <td ><strong>人员类别</strong></td>
						       <td ><strong>身份证号</strong></td>
						       <td ><strong>性别</strong></td>
						       <td ><strong>民族</strong></td>
						       <td ><strong>年龄(周岁)</strong></td>
						       <td ><strong>学历</strong></td>
						       <td ><strong>政治面貌</strong></td>
						     </tr>
						 </thead>
						 <tbody>
						 <c:forEach var="user" items="${listuser}">
						 	<tr>
						 		<td>${user.officestr}</td>
						 		<td>${user.name}</td>
						 		<td>${user.departmaent}</td>
						 		<td>${user.post}</td>
						 		<td>${user.duty}</td>
						 		<td>${user.usertype}</td>
						 		<td>${user.cardno}</td>
						 		<td>${user.sex}</td>
						 		<td>${user.minzu}</td>
						 		<td>${user.zhouyear}</td>
						 		<td>${user.seniority}</td>
						 		<td>${user.politicsstatus}</td>
						 	</tr>
						 </c:forEach>
						 </tbody>
						 </c:if>
					</table>
                   </div>
               </div>
             </div>
<script type="text/javascript">
$(document).ready(function() {
		$("#messagealert").hide();
		var flagcg = '${flagcg}';
		if(flagcg!=null&&flagcg!=''){
			$("#messagealert").show();
		}
		$("#closebut").click(function(){
			$("#messagealert").hide();
		});
		$('#savebutton').click(function() {
			var file = document.getElementById('file'); 
			if (file.value == "") { 
	            alert("请选择您需要上传的文件！"); 
	            return false;
	         }
		});
	})
	function downFile(){
	    window.location.href="${basepath}/user/downloadExcel";
    }
</script>
<c:import url="/pages/include/pageFoot.jsp" />