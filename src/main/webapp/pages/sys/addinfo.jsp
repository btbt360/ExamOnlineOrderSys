<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">我的面板</a> <span class="divider">/</span></li>
					<li class="active">我的信息</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<c:if test="${message!=null&&message!=''}">
						<div class="alert alert-success" style="margin-right: 8%;text-align: center;">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>保存成功！</strong>
						</div>
				</c:if>
				<form id="userinfoform" class="form-horizontal" action="${basepath}/user/saveInfo" method="post">
					<fieldset>
						<legend>个人信息维护</legend>
						<div class="control-group">
							<label class="control-label">用户姓名：</label>
							<div class="controls">
								<span class="input-xlarge uneditable-input" >${userToken.vuser.user.name}</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">用户电话：</label>
							<div class="controls">
							<input name="user.id" type="hidden" value="${userToken.vuser.user.id}" >
								<input class="input-xlarge focused" id="user.phone" name="user.phone"
									type="text" placeholder="请输入您的电话！" value="${userToken.vuser.user.phone}" >${phoneMsg}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="mobile">用户手机：</label>
							<div class="controls">
								<input class="input-xlarge focused" maxlength="11" id="usermobile" name="user.mobile"
									type="text" placeholder="请输入您的手机号码！" value="${userToken.vuser.user.mobile}" >${mobileMsg}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="email">用户邮箱：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="useremail" name="user.email"
									type="text" placeholder="请输入您的邮箱地址！" value="${userToken.vuser.user.email}" >${emailMsg}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="remark">备注信息：</label>
							<div class="controls">
								<textarea class="input-xlarge focused" id="user.remarks" name="user.remarks" placeholder="请输入备注信息" >${userToken.vuser.user.remarks}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >所属机构：</label>
							<div class="controls">
								<c:forEach items="${userToken.vuser.officenames}" var="officename" ><c:out value="${officename.value}" />|</c:forEach>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >所属角色：</label>
							<div class="controls">
								<c:forEach items="${userToken.vuser.rolenames}" var="rolenames" ><c:out value="${rolenames.value}" />|</c:forEach>
							</div>
						</div>
						
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/user'">
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {
		var jqObj = new JQvalidate();
 	     var userform ="userinfoform"; 
     	jqObj.setform(userform);
 	    jqObj.set("user.email", "required",  "请输入用户邮箱!");  
 	    jqObj.set("user.email", "email",  "请输入正确的用户邮箱!");	   
 	    jqObj.set("user.mobile", "required",  "请输入用户手机!");
 	    jqObj.set("user.mobile", "number",  "请输入正确的手机号!");
 	    jqObj.set("user.mobile", "isMobile",  "请输入正确格式的手机号!");
 	    jqObj.Run();
	})
</script>
<c:import url="/pages/include/pageFoot.jsp" />