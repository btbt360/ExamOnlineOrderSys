<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"> <a href='#'
						title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;">
						<a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a>
					</i>
					<li><a href="#">系统管理</a> <span class="divider">/</span></li>
					<li class="active">角色管理</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">

				<div class="block-content collapse in">
					<ul class="nav nav-tabs">
						<li class="active"><a href="${basepath}/role/add">角色列表</a></li>
						<li><a href="${basepath}/role/addroleinfo">角色添加</a></li>
					</ul>
					<div class="span12">
						<div class="alert alert-success"
							style="margin-right: 8%; display: none; text-align: center; " id="successmessage">
							<button class="close" onclick="$('#successmessage').hide();" >&times;</button>
							<strong><span id="messagess"></span></strong>
						</div>
						<div class="alert alert-error"
							style="margin-right: 8%; display: none; text-align: center; " id="errormessage">
							<button class="close" onclick="$('#errormessage').hide();" >&times;</button>
							<strong><span id="messageee"></span></strong>
						</div>
					</div>
					<form action="${basepath}/role/exportRole" method="post"
						id="roleform">
					<div class="span12"  style="text-align: center;">
						<div class="span3">
							<label class="control-label" for="rolename">角色名称：<input
								class="input-medium focused" id="rolename" name="rolename"
								type="text" /></label> 
						</div>
						<div class="span3">
							<label class="control-label" for="usertype">角色类型：
							<select class="span6 m-wrap" id="roletype" name="roletype">
								<option value="">--全部--</option>
								<c:forEach items="${listdict}" var="dict">
									<option value="${dict.dictkey}">${dict.dictvalue}</option>
								</c:forEach>
							</select></label> 
						</div>
						<div class="span3">
							<label class="control-label" for="starttimes"><a href='#'
								id="ceatetimes" style="color: black; text-decoration: none;">创建时间：</a><input type="text" class="input-medium datetimepicker"
								id="starttimes" value="" name="starttimes"></label>
						</div>
						<div class="span3">
							<label class="control-label" for="endtimes"><a href='#'
								id="ceatetimee" style="color: black; text-decoration: none;">至：</a><input type="text" class="input-medium datetimepicker" id="endtimes"
								value="" name="endtimes"></label>
						</div>
					</div>
					<input type="hidden" id="rolepages" name="rolepages" /><input
							type="hidden" id="rolerp" name="rolerp" />
					</form>
					<div class="span12 text-right" style="padding-right: 5%;">
						<button class="btn btn-medium btn-primary" type="button"
							id="query">查询</button>
						<button class="btn btn-medium btn-primary" type="button"
							id="export">导出</button>
					</div>
					<table id="roleList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>角色名称</th>
								<th>角色类型</th>
								<th>创建人</th>
								<th>创建时间</th>
								<th>拥有权限</th>
								<th>操作</th>
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
	function edit(ids) {
		location.href = "${basepath}/role/addroleinfo?id=" + ids;
	}
	
	function del(ids) {
		if(confirm("确定要删除该角色？")){
			$.ajax({
				type : 'post',
				url : '${basepath}/role/delroleinfo?id=' + ids,
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data == '1') {
						$("#successmessage").hide();
						$("#errormessage").show();
						$("#messageee").text("当前角色已被用户使用中，无法删除！");
					} else {
						$("#errormessage").hide();
						$("#successmessage").show();
						$("#messagess").text("删除成功！");
					}
					reshcg();
				}
	
			});
		}
	}
	$(document).ready(function() {
		$('.datetimepicker').datetimepicker({  
            language:  'zh-CN',
            format: 'yyyy-mm-dd',
            weekStart: 1,  
            todayBtn:  1,  
            autoclose: true,  
            todayHighlight: 1,  
            startView: 2,  
            forceParse: true,  
            minView:2,//只到天
            showMeridian: 1  
        }).on('changeDate', function (ev) {  
            $(this).datetimepicker('hide');  
        });

		oTable = $('#roleList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/role/addinfo"
		});

		$("#query").click(function() {
			reshcg();
		});

		$('#ceatetimes').click(function() {
			$('#starttimes').val('');
		});
		$('#ceatetimee').click(function() {
			$('#endtimes').val('');
		});
		$("#export").click(function() {
			$("#rolepages").val(oTable.getCurrentPage());
			$("#rolerp").val(oTable.getPageSize());
			$("#roleform").submit();
		});

	});
	function reshcg(){
		var rolename = $('#rolename').val();
		var roletype = $('#roletype').val();
		var starttimes = $('#starttimes').val();
		var endtimes = $('#endtimes').val();
		var oSettings = [ {
			"name" : "rolename",
			"value" : rolename
		}, {
			"name" : "roletype",
			"value" : roletype
		}, {
			"name" : "starttimes",
			"value" : starttimes
		}, {
			"name" : "endtimes",
			"value" : endtimes
		} ];
		oTable.gridSearch(this, oSettings);
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />