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
					<li><a href="#">统计分析</a> <span class="divider">/</span></li>
					<li class="active">考试成绩统计</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
					<div class="block-content collapse in">
					 <div class="span12">
					 <div class="span3 text-center">
						<label class="control-label" for="starttimes"><a href='#'
							id="ceatetimes" style="color: black; text-decoration: none;">考试时间：</a></label>
						<input type="text" class="input-medium datetimepicker" id="starttimes"
							value="" name="starttimes">
					</div>
					<div class="span3 text-center">
						<label class="control-label" for="endtimes"><a href='#'
							id="ceatetimee" style="color: black; text-decoration: none;">至：</a></label>
						<input type="text" class="input-medium datetimepicker" id="endtimes"
							value="" name="endtimes">
					</div>
					<div class="span4 text-center">
								<label class="control-label" for="name">考试名称：
								<select class="m-wrap" id="examid" name="examid" placeholder="请选择考试！">
									<option value=''>请选择考试</option>
								<c:forEach var="exam" items="${examlist}">
									<option value='${exam.id}'>${exam.code} | ${exam.name}</option>
								</c:forEach>
								</select>
								</label> 
					</div>
					<div class="span2 text-right" >
						<button class="btn btn-medium btn-primary" type="button" id="query">查询</button>
					</div>
					 </div>
					 <div class="span12">
                        <div id="hero-bar" style="height: 30%;width:95%"></div>
                     </div>
				    </div>
					
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
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
		$("#query").click(function() {
			chartdatas();
		});
		$('#ceatetimes').click(function() {
			$('#starttimes').val('');
		});
		$('#ceatetimee').click(function() {
			$('#endtimes').val('');
		});
		chartdatas();
		// Morris Bar Chart
			
	});
	function chartdatas(){
		$("#hero-bar").empty();
		var datastr='';
		var starttimes = $("#starttimes").val();
		var endtimes = $("#endtimes").val();
		var examid = $("#examid").val();
		$.ajax({
			type : 'post',
			url : '${basepath}/statistics/examineeChartDatas?starttimes='+starttimes+'&endtimes='+endtimes+'&examid='+examid,
			cache : false,
			dataType : 'json',
			success : function(data) {
				//datastr =JSON.stringify(data);
				Morris.Bar({
		            element: 'hero-bar',
		            data: data,
		            xkey: 'kaoshixStr',
		            ykeys: ['qualified','noqualified','excellent'],
		            labels: ['及格','不及格','优秀'],
		            barRatio: 0.4,
		            xLabelMargin: 10,
		            xLabelAngle: 60,
		            hideHover: 'auto',
		            barColors: ["#3d88ba",'#f56954','#00a65a']
		        });
			}
		});
		
	}
	
</script>
<c:import url="/pages/include/pageFoot.jsp" />