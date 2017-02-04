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
					<li><a href="#">我的考试</a> <span class="divider">/</span></li>
					<li class="active">参加考试</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
						<div class="span12">
							<div class="span3">
								<label class="control-label" for="name">考试名称：<input
									class="input-medium focused" id="name" name="name"
									type="text" /></label> 
							</div>
							<div class="span3">
								<label class="control-label" for="starttimes"><a href='#'
									id="ceatetimes" style="color: black; text-decoration: none;">考试开始时间：</a><input type="text" class="input-medium datetimepicker"
									id="starttimes" value="" name="starttimes"></label>
							</div>
							<div class="span3">
								<label class="control-label" for="endtimes"><a href='#'
									id="ceatetimee" style="color: black; text-decoration: none;">至：</a><input type="text" class="input-medium datetimepicker" id="endtimes"
									value="" name="endtimes"></label>
							</div>
							<div class="span3 text-right" >
						<button class="btn btn-medium btn-primary" type="button"
							id="query">查询</button>
					</div>
						</div>
						<input type="hidden" id="subpages" name="subpages" /><input
							type="hidden" id="subrp" name="subrp" />
					
					<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>考试名称</th>
								<th>考试编号</th>
								<th>考试开始时间</th>
								<th>考试结束时间</th>
								<th>考试时长</th>
								<th>考试人数</th>
								<th>监考人</th>
								<th>考试状态</th>
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
	function startExam(ids) {
		$.ajax({
			type : 'post',
			url : '${basepath}/invigilate/getExaminee?id='+ ids,
			cache : false,
			dataType : 'json',
			success : function(data) {
				//更新剩余时间
				if(data.flag==0){
					alert("您未参加此次考试！");
				}else if(data.flag==1){	
					var tempwindow = window.open ('_blank', 'newwindow','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
					tempwindow.location="${basepath}/examinee/addStartExam?id=" + ids;
				}else if(data.flag>1&&data.flag<5){	
					alert("您已经参加完此次考试或考试已经结束！");
				}else if(data.flag==5){	
					alert("考试还未开始，请联系监考员开始考试！");
				}else if(data.flag==6){	
					alert("您的考试座位坐错了，请退出本机系统到正确的座位上参加考试！");
				}
			}
		});
	}
	$(document).ready(function() {
		$('.datetimepicker').datetimepicker({  
            language:  'zh-CN',
            format: 'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,  
            todayBtn:  1,  
            autoclose: true,  
            todayHighlight: 1,  
            startView: 2,  
            forceParse: true,  
            showMeridian: 1  
        }).on('changeDate', function (ev) {  
            $(this).datetimepicker('hide');  
        });
		
		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/examinee/getJoinExamlist"
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
		var name = $('#name').val();
		var starttimes = $('#starttimes').val();
		var endtimes = $('#endtimes').val();
		var oSettings = [ {
			"name" : "name",
			"value" : name
		}, {
			"name" : "starttimes",
			"value" : starttimes
		}, {
			"name" : "endtimes",
			"value" : endtimes
		}  ];
		oTable.gridSearch(this, oSettings);
	}
</script>
<c:import url="/pages/include/pageFoot.jsp" />