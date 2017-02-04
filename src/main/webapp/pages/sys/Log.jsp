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
					<i class="icon-chevron-right show-sidebar" style="display: none;"><a
						href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">系统管理</a> <span class="divider">/</span></li>
					<li class="active">日志管理</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid" style="width: 98%;margin-left: 1%;">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
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
							<label class="control-label" for="logname">日志标题：</label> <input
								class="input-medium focused" id="logname" name="logname"
								type="text" />
						</div>
						<div class="span3">
							<label class="control-label" for="starttimes"><a href='#'
								id="ceatetimes" style="color: black; text-decoration: none;">创建时间：</a></label>
							<input type="text" class="input-medium datetimepicker"
								id="starttimes" value="" name="starttimes">
						</div>
						<div class="span3">
							<label class="control-label" for="endtimes"><a href='#'
								id="ceatetimee" style="color: black; text-decoration: none;">至：</a></label>
							<input type="text" class="input-medium datetimepicker" id="endtimes"
								value="" name="endtimes">
						</div>
						<div class="span3 text-right" style="padding-right: 3%;">
							<button class="btn btn-medium btn-primary" type="button"
								id="query">查询</button>
							<button class="btn btn-medium btn-primary" type="button"
								id="clear">清空本页日志</button>
						</div>
					</div>
				</div>

				<table id="listlog" class="table table-striped table-bordered" >
					<thead>
						<tr>

							<th width="8%">类型</th>
							<th width="27%">日志标题</th>
							<th width="40%">内容</th>						
							<th width="10%">操作员姓名</th>
							<th width="10%">创建时间</th>
							<th width="5%">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
					<!-- tbody是必须的 -->
				</table>
			</div>
		</div>
	</div>
	<input type="hidden" id="resultids" />

<script type="text/javascript">

function reshcg() {
	var logname = $('#logname').val();
	var starttimes = $('#starttimes').val();
	var endtimes = $('#endtimes').val();
	var otherParams = [ {
		"name" : "logname",
		"value" : logname
	}, {
		"name" : "starttimes",
		"value" : starttimes
	}, {
		"name" : "endtimes",
		"value" : endtimes
	} ];
	oTable.gridSearch(this, otherParams);
}
	$(document).ready(function() {
		$("#successmessage").hide();
		$("#errormessage").hide();
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
		
		oTable = $('#listlog').initDT({
			"serverSide" : true,
			"sAjaxSource" : "${basepath}/log/loglist"
			
		});

		$("#query").click(function() {
			reshcg();
		});

		$("#clear").click(function() {
			var ids = $('#resultids').val();
			del(ids);
		});

		$('#ceatetimes').click(function() {
			$('#starttimes').val('');
		});
		$('#ceatetimee').click(function() {
			$('#endtimes').val('');
		});

	});
	

	function del(ids) {
		if (confirm("确定要删除？")) {
			$.ajax({
				type : 'post',
				url : '${basepath}/log/delLog?resultids=' + ids,
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data == '1') {
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
</script>
<c:import url="/pages/include/pageFoot.jsp" />