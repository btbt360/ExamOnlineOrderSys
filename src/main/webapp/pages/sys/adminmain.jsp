<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<div style=" margin-left: 10%; margin-right: 10%" class="row">
		<div class=".col-xs-12 .col-sm-12 .col-md-12 .col-lg-12">
			<!-- block -->
			<div class="block">
				<div class="navbar navbar-inner block-header">
					<div class="muted pull-left">
					考试<small>统计图</small>
					</div>
				</div>
				<div class="block-content collapse in">
					 <div class="span12">
                              <div id="hero-bar" style="height: 230px;"></div>
                      </div>
				</div>
			</div>
			<!-- /block -->
		</div>
	</div>
	<div class="row" style="margin-left: 10%; margin-right: 10%">
		<div class="pull-left" style="width: 30%">

			<div class="col-md-4">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">快捷入口</div>
					</div>
					<div class="block-content collapse in">
						<table width="100%" border="0">
							<tr height="100px">
								<td width="33%"><div align="center">
										<a href="../item/add"><img alt=""
											src="../static/img/tkgl.png" width="40px" style="margin-bottom: 4px;" class="img-rounded"></a>
										<br/>题库管理
									</div></td>
								<td width="33%"><div align="center">
										<a href="../questions/add">
										<img alt="" src="../static/img/stgl.png" width="40px" style="margin-bottom: 4px;" class="img-rounded"></a>
										<br/>试题管理
									</div></td>
								<td width="33%"><div align="center"></div></td>
							</tr>
							<tr height="100px">
								<td width="33%"><div align="center"></div></td>
								<td width="33%"><div align="center"></div></td>
								<td width="33%"><div align="center"></div></td>
							</tr>
						</table>

					</div>
				</div>
				<!-- /block -->
			</div>
		</div>
		<div class="pull-left" style="margin-left: 3%; width: 67%">

			<div class="col-md-9">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">正在监考中</div>
						<div class="muted pull-right"><a href="${basepath}/invigilate/add">更多..</a></div>
					</div>
					<div class="block-content collapse in">
							<table class="table table-bordered">
								<c:forEach items="${examList}" var="exam">
									<tr style="height:42px !important;">
										<td width="50%">${exam.name }</td>
										<td  width="30%">${exam.duration}分钟</td>
										<td  width="20%"><a href="${basepath}/invigilate/add">开始监考</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
				</div>
				<!-- /block -->
			</div>
		</div>
	</div>


	<!-- block -->
	<script type="text/javascript">
		$(function() {
			$(".flagbtn").click(function(){
				var id=this.id;
				var url="${basepath}/bbs/updateis_check";
				$.ajax({
					type : 'post',
					url : url,
					data : {
						id : id
					},
					cache : false,
					dataType : 'json',
					success : function(data) {
						$("#"+id).hide();
					},
					error : function() {
					}
				});
			});
			$(".img-rounded").mouseover(function(){
				$(this).addClass("hoverimg");
			});
			$(".img-rounded").mouseout(function(){
				$(this).removeClass("hoverimg");
			});
			var dates = '${chartlist}';
			// Morris Bar Chart
	        Morris.Bar({
	            element: 'hero-bar',
	            data: eval('(' + dates + ')'),
	            xkey: 'datetime',
	            ykeys: ['average'],
	            labels: ['平均分'],
	            barRatio: 0.4,
	            xLabelMargin: 10,
	            hideHover: 'auto',
	            barColors: ['#3d88ba']
	        });
		});
	</script>
<c:import url="/pages/include/pageFoot.jsp" />