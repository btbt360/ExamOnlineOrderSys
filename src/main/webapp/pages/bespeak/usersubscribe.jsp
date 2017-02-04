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
					<li class="active">预约考试</li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
				<!--  
					<ul class="nav nav-tabs">
						<li class="active"><a href="${basepath}/exam/addExam">日程预约考试</a></li>
						<li><a href="${basepath}/exam/addExamInfo">列表预约考试</a></li>
					</ul>
				-->
					<!-- 取消预约提示 -->
					<div class="span12">
						<div class="alert alert-success"
							style="margin-right: 8%;display: none; text-align: center;" id="successmessage">
							<button class="close" onclick="$('#successmessage').hide();">&times;</button>
							<strong><span id="messagess"></span></strong>
						</div>
					</div>
			<div class="span12" id="content">
                <div class="row-fluid">
                    <!-- block -->
                        <div class="block-content collapse in" >
                            <div class="span12">
                                <div id="myAlert" class="modal hide">
                                    <div class="modal-header">
                                        <button data-dismiss="modal" class="close" type="button">&times;</button>
                                        <h3>考试信息</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div class="control-group">
											<label class="control-label"><b>考试名称：</b></label>
											<div class="controls">
												<span id ="examname"></span>
											</div>
										</div>
										<div class="control-group" style="padding-top:2.5%;">
											<label class="control-label"><b>考试时间：</b></label>
											<div class="controls">
												<span id ="examtime"></span>
											</div>
										</div>
										<div class="control-group" style="padding-top:2.5%;">
											<label class="control-label"><b>考试时长：</b></label>
											<div class="controls">
												<span id ="exambetten"></span>
											</div>
										</div>
										<div class="control-group" style="padding-top:2.5%;">
											<label class="control-label"><b>预约人数：</b></label>
											<div class="controls">
												考试人员总数：<span id ="total"></span><br />
												已经预约考试人数：<font color="green"><span id ="alreadynum"></span></font><br />
												考试剩余预约人数：<font color="orange"><span id ="synum"></span></font><br />
											</div>
										</div>
										<div class="control-group" style="padding-top:2.5%;">
											<label class="control-label"><b>是否已经预约：</b></label>
											<div class="controls">
												<span id ="flag"></span>
											</div>
										</div>
										<div class="control-group" style="padding-top:2.5%;">
											<label class="control-label"><b>考试状态：</b></label>
											<div class="controls">
												<span id ="flagcg"></span>
												<input type="hidden" id ="examid" />
											</div>
										</div>
                                    </div>
                                    <div class="modal-footer">
                                        <a data-dismiss="modal" class="btn btn-primary" href="#" id="submityuyue">预约考试</a>
                                        <a data-dismiss="modal" class="btn btn-primary" href="#" id="jieshuyuyue" >解除预约</a>
                                        <a data-dismiss="modal" class="btn" href="#">关闭</a>
                                    </div>
                                </div>
                                <div id='calendar'></div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>
				</div>
			</div>
		</div>
<script type="text/javascript">

	$(document).ready(function() {
		var vclist = '${vclist}';
		$("#jieshuyuyue").hide();
		var message ="${message}";
		if(message=='success'){
			$("#successmessage").show();
			$("#messagess").text("预定成功");
		}else if(message=='qxsuccess'){
			$("#successmessage").show();
			$("#messagess").text("取消预定成功");
		}
		 // Easy pie charts
        var calendar = $('#calendar').fullCalendar({
            buttonText: {
                today: '今天',
                month: '月视图',
                week: '周视图',
                day: '日视图'
            },
            allDayText: "全天",
            timeFormat: {
                '': 'H:mm{-H:mm}'
            },
            weekMode: "variable",
            columnFormat: {
                month: 'dddd',
                week: 'dddd M-d',
                day: 'dddd M-d'
            },
            titleFormat: {
                month: 'yyyy年 MMMM月',
                week: "[yyyy年] MMMM月d日 { '&#8212;' [yyyy年] MMMM月d日}",
                day: 'yyyy年 MMMM月d日 dddd'
            },
            monthNames: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
            dayNames: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            header: {
                left: 'prev,next',
                center: 'title',
                right: 'month,basicWeek,basicDay'
            },
            //设置是否可被单击或者拖动选择
            selectable: true,
            //点击或者拖动选择时，是否显示时间范围的提示信息，该属性只在agenda视图里可用
            selectHelper: true,
            //点击或者拖动选中之后，点击日历外的空白区域是否取消选中状态 true为取消 false为不取消，只有重新选择时才会取消
            unselectAuto: true,
           
            droppable: false, // this allows things to be dropped onto the calendar !!!
            drop: function(date, allDay) { // this function is called when something is dropped

                // retrieve the dropped element's stored Event Object
                var originalEventObject = $(this).data('eventObject');

                // we need to copy it, so that multiple events don't have a reference to the same object
                var copiedEventObject = $.extend({}, originalEventObject);

                // assign it the date that was reported
                copiedEventObject.start = date;
                copiedEventObject.allDay = allDay;

                // render the event on the calendar
                // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }

            },
            editable: true,
            //月视图下日历格子宽度和高度的比例
            aspectRatio: 1.35,
            //月视图的显示模式，fixed：固定显示6周高；liquid：高度随周数变化；variable: 高度固定
            weekMode: 'liquid',
            //初始化时的默认视图，month、agendaWeek、agendaDay
            defaultView: 'month',
            //agenda视图下是否显示all-day
            allDaySlot: true,
            //agenda视图下all-day的显示文本
            allDayText: '全天',
            //agenda视图下两个相邻时间之间的间隔
            slotMinutes: 30,
            //区分工作时间
            businessHours: true,
            //非all-day时，如果没有指定结束时间，默认执行120分钟
            defaultEventMinutes: 120,
            //设置为true时，如果数据过多超过日历格子显示的高度时，多出去的数据不会将格子挤开，而是显示为 +...more ，点击后才会完整显示所有的数据
            eventLimit: true,
            events:  JSON.parse(vclist), // US Holidays
            eventMouseover: function(event) {
                //do something here...
                console.log('鼠标经过 ...');
                console.log('eventMouseover被执行，选中Event的title属性值为：', event.title);
                // ...
            },
            eventMouseout: function(event) {
                //do something here...
                console.log('eventMouseout被执行，选中Event的title属性值为：', event.title);
                console.log('鼠标离开 ...');
                // ...
            },
            eventClick: function(event) {
            	$.ajax({
        			type : 'post',
        			url : '${basepath}/bespeak/getUsersubscribe?id=' + event.id,
        			cache : false,
        			dataType : 'json',
        			success : function(data) {
        				$("#examname").text(data.bespeakexam.EXAMNAME);
        				$("#examtime").text(data.bespeakexam.STARTTIME+' 至 '+data.bespeakexam.ENDTIME);
        				$("#exambetten").text(data.bespeakexam.DURATION+'分钟');
        				$("#total").text(data.bespeakexam.NUMBER);
        				$("#alreadynum").text(data.alreadynum);
        				$("#synum").text(data.bespeakexam.NUMBER-data.alreadynum);
        				$("#examid").val(event.id);
        				if(data.flag){
        					$("#jieshuyuyue").show();
        					$("#submityuyue").hide();
            				$("#flag").html("<font color = 'green'>已经预约</font>");
        				}else{
        					$("#jieshuyuyue").hide();
        					$("#submityuyue").show();
        					$("#flag").html("<font color = 'blue' >未预约</font>");
        				}
        				if(data.flagcg){
        					$("#flagcg").html("<font color = 'green'>考试预定中</font>");
        				}else{
        					$("#submityuyue").hide();
        					$("#jieshuyuyue").hide();
        					$("#flagcg").html("<font color = 'red'>考试已开始不能预约！</font>");
        				}
        				
        				$("#myAlert").modal('show');
        			}
        		});
                calendar.fullCalendar('removeEventSource', event);
            },
            eventResize: function(event, dayDelta, revertFunc) {
                //do something here...
                console.log(' --- start --- eventResize');
                console.log('eventResize被执行，Event的title属性值为：', event.title);
                if (dayDelta._days != 0) {
                    console.log('eventResize被执行，Event的start和end时间改变了：', dayDelta._days + '天！');
                } else if (dayDelta._milliseconds != 0) {
                    console.log('eventResize被执行，Event的start和end时间改变了：', dayDelta._milliseconds / 1000 + '秒！');
                } else {
                    console.log('eventResize被执行，Event的start和end时间没有改变！');
                }
                //revertFunc();
                console.log('--- end --- eventResize');
                // ...
            },
            eventDrop: function(event, dayDelta, revertFunc) {
                //do something here...
                console.log('eventDrop --- start ---');
                console.log('eventDrop被执行，Event的title属性值为：', event.title);
                if (dayDelta._days != 0) {
                    console.log('eventDrop被执行，Event的start和end时间改变了：', dayDelta._days + '天！');
                } else if (dayDelta._milliseconds != 0) {
                    console.log('eventDrop被执行，Event的start和end时间改变了：', dayDelta._milliseconds / 1000 + '秒！');
                } else {
                    console.log('eventDrop被执行，Event的start和end时间没有改变！');
                }
                //revertFunc();
                console.log('eventDrop --- end ---');
                // ...
            }
        });
		$("#submityuyue").click(function(){
			var num = $("#synum").text();
			if(num>0){
				$.ajax({
	    			type : 'post',
	    			url : '${basepath}/bespeak/savebespeakinfo?id=' + $("#examid").val() + "&flag=1",
	    			cache : false,
	    			dataType : 'json',
	    			success : function(data) {
	    				alert("预约成功！");
	    				window.location.reload(true);
	    			}
				});
			}else{
				alert("预定人员已满！");
			}
			
			
		});
		$("#jieshuyuyue").click(function(){
			$.ajax({
    			type : 'post',
    			url : '${basepath}/bespeak/cancelbespeak?id=' + $("#examid").val() + "&flag=1",
    			cache : false,
    			dataType : 'json',
    			success : function(data) {
    				alert("取消预约成功！");
    				window.location.reload(true);
    			}
			});
			
		});
	});

</script>
<c:import url="/pages/include/pageFoot.jsp" />