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
					<li class="active">密码修改</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<c:if test="${message!=null}">
						<div class="alert alert-success" style="margin-right: 8%;text-align: center;">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>保存成功！</strong>
						</div>
				</c:if>
				<form class="form-horizontal" action="${basepath}/user/updatepass" method="post" id="commentForm">
					<fieldset>
						<legend>密码信息维护</legend>
						<div class="control-group">
							<label class="control-label" for="oldpassword">原始密码：</label>
							<div class="controls">
							<input name="user.id" type="hidden" value="${userToken.vuser.user.id}" >
								<input class="input-xlarge focused" id="oldpassword" name="oldpassword"
									type="password" placeholder="请输入您的原始密码！" ><span class="help-inline" id="oldline" style="color: red;"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="">新密码：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="password" name="password"
									type="password" placeholder="请输入您的新密码！"  ><span class="help-inline" id="newpass" style="color: red;"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="email">重复密码：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="cfpassword" name="cfpassword"
									type="password" placeholder="请重复输入您的新密码！"  ><span class="help-inline" id="newline" style="color: red;"></span>
							</div>
						</div>
						<div class="form-actions">
							<button type="button" class="btn btn-primary" id="save">保存</button>
							<button type="reset" class="btn">取消</button>
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#oldpassword").blur(function(){
		var pass=$("#oldpassword").val();
		$.ajax({
	        type: "GET",
	        url: "${basepath}/user/checkpass",
	        data: {oldpassword:pass},
	        dataType: "json",
	        success: function(data){
	                    if(data==1){
	                    	$("#oldline").html("<font color='green'>输入正确</font>");
	                    }else{
	                    	$("#oldline").html("<font color='red'>输入错误</font>");
	                    	$("#oldpassword").val('');
	                    }
	                 }
	    });
	});
	$("#cfpassword").blur(function(){
		var cfpassword = $("#cfpassword").val();
		var password = $("#password").val();
		if(cfpassword==password){
			$("#newline").html("<font color='green'>重复密码输入正确</font>");
		}else{
			$("#newline").html("<font color='red'>重复密码输入错误</font>");
			$("#cfpassword").val('');
		}
		
	});
	
	$("#save").click(function(){
		var oldpass=$("#oldpassword").val();
		var cfpass = $("#cfpassword").val();
		var newpass = $("#password").val();
		
		if(oldpass ==''||oldpass ==null){
			$("#oldline").html("<font color='red'>请输入原密码</font>");
			return false;
		}
		if(newpass ==''||newpass ==null){
			$("#newpass").html("<font color='red'>请输入新密码</font>");
			return false;
		}
		if(cfpass ==''||cfpass ==null){
			$("#newline").html("<font color='red'>请重复输入新密码</font>");
			return false;
		}
		
		$("#commentForm").submit();
	});
});
</script>
<c:import url="/pages/include/pageFoot.jsp" />