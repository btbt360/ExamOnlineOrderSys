<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String baseURL = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<!-- JS -->
<script
	src="${basepath}/static/frame/theme/vendors/jquery-validation/lib/jquery.js"></script>
<script src="${basepath}/static/frame/theme/vendors/jquery-1.9.1.min.js"></script>
<style>
body {
	background: url(static/img/login_bg.jpg) no-repeat;
	background-size: 100%;
	background-color: #104a9c;
}

input:-webkit-autofill {
	-webkit-box-shadow: 0 0 0px 1000px white inset !important;
}

.logo {
	text-align: center;
	padding-top: 90px;
	padding-bottom: 30px;
}

.inputbg {
	background: url(static/img/input_bg.png) no-repeat center;
	height: 401px;
	position: relative;
}

.inputdiv {
	position: absolute;
	left: 50%;
	top: 70px;
	margin: 0px 0 0 -151px;
}

.input01 {
	border: 1px solid #CCC;
	border-radius: 8px;
	height: 35px;
	width: 310px;
	line-height: 35px;
	background-color: #fff !important;
	padding-left: 40px;
	margin-top: 30px;
	position: relative
}

.icon01 {
	position: absolute;
	top: 30px;
	left: 3px;
	z-index: 100
}

.icon02 {
	position: absolute;
	top: 86px;
	left: 3px;
	z-index: 100
}

.input02 {
	border: 1px solid #CCC;
	border-radius: 8px;
	height: 35px;
	width: 310px;
	line-height: 35px;
	background-color: #fff;
	padding-left: 40px;
}

.input03 {
	border: 1px solid #CCC;
	border-radius: 8px;
	height: 35px;
	width: 250px;
	line-height: 35px;
	background-color: #fff;
	padding-left: 10px;
	margin－top: 5px;
}

.btn {
	background: url(static/img/login_btn.png);
	border: 0;
	width: 350px;
	height: 40px;
}

.alert-error {
	color: #b94a48;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#error").hide();
		$("#loginbutton").click(function() {
			if (verification() == false) {
				return false;
			}
			;
		});
		var msg = '${msg}';
		if (msg != null && msg != '') {
			$("#error").show();
			$("#msg").html(msg);
		}
		$("#closealerterror").click(function() {
			$("#error").hide();
			return false;
		});

	});
	function verification() {
		var userloginName = $("#userloginName").val();
		var userpassword = $("#userpassword").val();
		//var captcha = $("#captcha").val();
		if (userloginName == null || userloginName == '') {
			$("#error").show();
			$("#msg").html("请输入用户名");
			return false;
		}
		if (userpassword == null || userpassword == '') {
			$("#error").show();
			$("#msg").html("请输入密码");
			return false;
		}
		/**
		if (captcha == null || captcha == '') {
			$("#error").show();
			$("#msg").html("请输入验证码");
			return false;
		}*/
	}
</script>
</head>
<body id="login">
	<div class="logo">
		<img src="static/img/login_logo.png" />
	</div>


	<div class="inputbg">
		<form id="loginform" method="post" action="${basepath}/doLogin">
			<div class="inputdiv">
				<input type="text" class="input01" placeholder="用户名" name="username"
					id="userloginName" /><br>
				<br /> <img src="static/img/user.png" class="icon01" /><input
					type="password" class="input02" placeholder="密码" name="password"
					id="userpassword" /><br><br /> 
				<%-- <img src="static/img/password.png" class="icon02" /> <input
					type="text" class="input03" placeholder="验证码" name="captcha"
					id="captcha" /><img src="${basepath}/img"
					style="vertical-align: middle; padding-left: 10px;" />  --%>
				<br><br />
				<div class="alert alert-error" style="margin-bottom: 10px;"
					id="error">
					<!-- <button id="closealerterror" class="close" >&times;</button> -->
					<strong><span id="msg"></span></strong> <br>
				</div>
				<button class="btn" type="submit" id="loginbutton">&nbsp;</button>
			</div>

		</form>
	</div>



</body>
<script>
	$(function() {
		//回车登录
		document.onkeydown = function(e) {
			var ev = document.all ? window.event : e;
			if (ev.keyCode == 13) {
				if (verification() == false) {
					return false;
				}
				;
				$('#loginform').submit();
			}
		}
	});
</script>
</html>