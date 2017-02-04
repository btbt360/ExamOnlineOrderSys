<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"><a href='#'
						title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;">
						<a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a>
					</i>
					<li><a href="#">我的练习</a> <span class="divider">/</span></li>
					<li class="active">我的错题</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
				<div class="span12">
					<div class="span4"></div>
					<div class="span4"><button type="button" class="btn btn-large btn-block btn-primary" onclick="alink();">开始错题练习</button></div>
					<div class="span4"></div>
				</div>
					<input type="hidden" id="subpages" name="subpages" />
						<input type="hidden" id="subrp" name="subrp" />
					<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th width="30%">错题内容</th>
								<th width="5%">属于题库</th>
								<th width="25%">错题答案</th>
								<th width="20%">错题描述</th>
								<th width="20%">错题解答</th>
<!-- 								<th width="10%">操作</th> -->
							</tr>
						</thead>
						<tbody>
						</tbody>
						<!-- tbody是必须的 -->
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	oTable = $('#userList').initDT({
		serverSide : true,
		"sAjaxSource" : "${basepath}/errorsubject/getErrorList"
	});
});
function reshcg() {
	oTable.gridSearch(this, oSettings);
}
function alink(){
	var count = '${count}';
	if(Number(count)>0){
		var tempwindow = window.open ('_blank', 'newwindow','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
		tempwindow.location="${basepath}/errorsubject/errorStart";
	}else{
		alert("当前没有错题！");
	}
}
function removenerror(id){
	$.ajax({
		type : 'post',
		url : '${basepath}/errorsubject/removenError?errorid='+id,
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