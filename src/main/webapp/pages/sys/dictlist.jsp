<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
<%@page import="com.wide.constant.EnumDictType"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">
			<ul class="breadcrumb">
				<i class="icon-chevron-left hide-sidebar"><a href='#'
					title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
				<i class="icon-chevron-right show-sidebar" style="display: none;"><a
					href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
				<li><a href="#">系统管理</a> <span class="divider">/</span></li>
				<li class="active">数据字典列表</li>
			</ul>
		</div>
	</div>

	<div class="row-fluid">
		<div class="block" style="border: 0px;">
			<div class="block-content collapse in">
				<ul class="nav nav-tabs">
					<li class="active"><a href="${basepath}/dict/add">数据字典列表</a></li>
					<li><a href="${basepath}/dict/adddictinfo">添加数据字典</a></li>
				</ul>
				<!-- 删除用户提示 -->
				<div class="span12">
					<div class="alert alert-success"
						style="margin-right: 8%; display: none; text-align: center;"
						id="successmessage">
						<button class="close" onclick="$('#successmessage').hide();">&times;</button>
						<strong><span id="messagess"></span></strong>
					</div>
					<div class="alert alert-error"
						style="margin-right: 8%; display: none; text-align: center;"
						id="errormessage">
						<button class="close" onclick="$('#errormessage').hide();">&times;</button>
						<strong><span id="messageee"></span></strong>
					</div>
				</div>
				<div class="span12">
					<div class="span3">
						<label class="control-label" for="dictname">字典名称：</label> <input
							class="input-medium focused" id="dictname" name="dictname"
							type="text" />
					</div>
					<div class="span3">
						<label class="control-label" for="dicttype">字典类型：</label> <select
							class="span6 m-wrap" id="dicttype" name="dicttype">
							<option value='0'>请选择</option>
							<c:forEach var="typelist" items="<%=EnumDictType.values()%>">
								<option value='${typelist.enumKey}'>${typelist.enumText}</option>
							</c:forEach>
						</select>
					</div>
					<div class="span3">
						<label class="control-label" for="starttimes"><a href='#'
							id="ceatetimes" style="color: black; text-decoration: none;">创建时间：</a></label>
						<input type="text" class="input-medium datetimepicker" id="starttimes"
							value="" name="starttimes">
					</div>
					<div class="span3">
						<label class="control-label" for="endtimes"><a href='#'
							id="ceatetimee" style="color: black; text-decoration: none;">至：</a></label>
						<input type="text" class="input-medium datetimepicker" id="endtimes"
							value="" name="endtimes">
					</div>
				</div>
				<div class="span12 text-right" style="padding-right: 3%;">
					<button class="btn btn-medium btn-primary" type="button" id="query">查询</button>

				</div>

				<table id="dictList" class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>数据字典名称</th>
							<th>键值</th>
							<th>类型</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
					<!-- tbody是必须的 -->

				</table>

				<!-- <iframe id="officeContent" src="${basepath}/office/list?id=&parentIds=" width="100%" height="100%" frameborder="1" onLoad="IFrameReSizeWidth()" ></iframe> -->
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function edit(ids) {
		location.href = "${basepath}/dict/adddictinfo?id=" + ids;
	}

	function del(ids) {
		if (confirm("确定要删除该字典？")) {
			$.ajax({
				type : 'post',
				url : '${basepath}/dict/delete?id=' + ids,
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.result == 1) {
						$("#successmessage").hide();
						$("#errormessage").show();
						$("#messageee").text("删除失败，请联系管理员！");
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
		oTable = $('#dictList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/dict/findlist"
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
	});
	function reshcg() {
		var dictname = $('#dictname').val();
		var dicttype = $('#dicttype').val();
		var starttimes = $('#starttimes').val();
		var endtimes = $('#endtimes').val();
		var oSettings = [ {
			"name" : "dictname",
			"value" : dictname
		}, {
			"name" : "dicttype",
			"value" : dicttype
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