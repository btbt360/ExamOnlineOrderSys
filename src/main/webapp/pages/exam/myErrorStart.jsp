<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试系统</title>
</head>
<c:import url="/pages/include/pageHead.jsp" />
<body style="padding-top: 0px;">
	<div class="block" id="info" style="width: 95%; margin-left: 2.5%;">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">开始错题练习</div>
		</div>
		<div class="block-content collapse in">
			<div class="span11">
				<div class="span4 text-center">
					<label class="control-label"><h4>
							<span class="label label-info"><h4>练习人：</h4></span>&nbsp;&nbsp;${username}
						</h4></label>
				</div>
			</div>
		</div>
	</div>
	<div class="block" style="width: 95%; margin-left: 2.5%;">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">试题编码：${questions.code}</div>
		</div>
		<form action="${basepath}/errorsubject/errorStart">
			<div class="block-content collapse in">
				<div class="span12">
					<div class="span2"></div>
					<div class="span10">${questions.title}</div>
				</div>
				<div class="span12">
					<div class="span4 text-left" id="isanswer"></div>
					<div class="span6 text-right">
						<button class="btn btn-medium btn-primary" type="button"
							id="checkquestion">查看答案</button>
						&nbsp;&nbsp;
						<button class="btn btn-medium btn-primary" type="submit"
							id="nextquestion">下一题</button>
						<button class="btn btn-medium btn-primary" type="submit"
							id="reloadquestion">重新答题</button>
						<input type="hidden" id="sort" name="sort" value="${sort}" />
						<input type="hidden" id="anstr" value="${questions.questionanswer}" />
						<input type="hidden" id="flag" name="flag" value="${flag}" />
					</div>
					<div class="span2"></div>
				</div>
			</div>
			<div class="block-content collapse in">
				<div class="span11">
					<table class="table table-bordered" id="answerinfo">
						<thead>
						</thead>
						<tbody>
							<tr>
								<td width="20%">答案：</td>
								<td width="80%">${questions.questionanswer}</td>
							</tr>
							<tr>
								<td>解答：</td>
								<td>${questions.questionanswerinfo}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
	<div id="questions"></div>
	<div id="answers"></div>
	<div class="span12 text-center" style="margin-bottom: 2%;">
		<button type='button' class='btn btn-warning btn-large'
			onclick='closewindows()'>关闭</button>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#answerinfo').hide();
		$('#reloadquestion').hide();
		var flag = $("#flag").val();
		if(flag == 1){
			$('#reloadquestion').show();
			$('#nextquestion').hide();
			$('#checkquestion').hide();
		}
		$('#reloadquestion').click(function(){
			$('#sort').val(0);
		});
		$("#checkquestion").click(function(){
			$('#answerinfo').show();
		});
	});
	function ischeck(str) {
		var anstr = $("#anstr").val();
		var questionsid = '${questions.id}';
		if (anstr.indexOf(str) == -1) {
			$("#isanswer").empty();
			$("#isanswer").append(
					"&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'><h4>此题回答有误</h4></font>");
			$('#answerinfo').show();
		} else {
			uperror(questionsid);
		}
	}
	function isanswer() {
		var anstr = $("#anstr").val();
		var answertext = $("#answertext").val();
		var questionsid = '${questions.id}';
		if (anstr.indexOf(answertext) == -1) {
			$("#isanswer").empty();
			$("#isanswer").append(
					"&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'><h4>此题回答有误</h4></font>");
			$('#answerinfo').show();
		} else {
			uperror(questionsid);
		}
	}
	function uperror(questionsid) {
		$.ajax({
			type : 'post',
			url : '${basepath}/errorsubject/getErrorUpdate?questionsid='
					+ questionsid,
			cache : false,
			dataType : 'json',
			success : function(data) {
				//
			}
		});
	}
	function clockclosewindle(i) {
		i = i - 1
		document.title = "本窗口将在" + i + "秒后自动关闭!";
		if (i > 0)
			setTimeout("clockclosewindle();", 1000);
		else
			closewin();
	}
	function closewin() {
		self.opener = null;
		self.close();
	}
	function closewindows() {
		clockclosewindle(3);
	}
</script>
</html>