<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
<!-- block -->
<div style="display:none;">
<OBJECT ID="FPSLID1" WIDTH=40 HEIGHT=40
								     CLASSID="CLSID:AEC85E68-EC97-4656-AE30-CFBB0B8DBA75">
								        <PARAM NAME="_ExtentX" VALUE="5133">
								        <PARAM NAME="_ExtentY" VALUE="5715">
								    </OBJECT>
							<COMMENT>
							    <EMBED type="application/x-eskerplus"
							        classid="clsid:AEC85E68-EC97-4656-AE30-CFBB0B8DBA75"
							        codebase="FPScanner.ocx"                
							        width=20 height=20>
							    </EMBED>
							</COMMENT>
</div>
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"> <a href='#'
						title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;">
						<a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a>
					</i>
					<li><a href="#">监控管理</a> <span class="divider">/</span></li>
					<li class="active">在线考试监控</li>
				</ul>
			
			</div>
		</div>
		<div class="row-fluid">
			<div class="block" style="border: 0px;">
				<div class="block-content collapse in">
						<div class="span12">
							<div class="span3 text-center">
								<label class="control-label" ><h4><span class="label label-info"><h4>考试名称：</h4></span>&nbsp;&nbsp;${exam.name}</h4></label> 
							</div>
							<div class="span2 text-center">
								<label class="control-label" ><h4><span class="label label-info"><h4>考试编号：</h4></span>&nbsp;&nbsp;${exam.code}</h4></label> 
							</div>
							<div class="span2 text-center">
								<label class="control-label"><h4><span class="label label-info"><h4>监考员：</h4></span>&nbsp;&nbsp;${userToken.vuser.user.name}</h4></label> 
							</div>
							<div class="span5 text-center">
								<label class="control-label" for="name"><h4><span class="label label-info"><h4>考生名称：</h4></span>
		                                <select  id="name" name="name" class="chzn-select">
		                                <option value=''></option>
		                                	<c:forEach var="examinee" items="${elist}">
												<option value='${examinee.id}'>${examinee.examineename}</option>
											</c:forEach>
		                                </select></h4>
		                        </label>
							</div>
					</div>
					<div class="span12 " style="margin-top:2%">
							<div class="span8 text-center">
							<label class="control-label" for="daotime"><span class="label label-info"><h1>倒计时：</h1></span>
								<span class="time-item">
										<strong id="hour_show"><span class="badge badge-important"><h1>0时</h1></span></strong>
										<strong id="minute_show"><span class="badge badge-important"><h1>0分</h1></span></strong>
										<strong id="second_show"><span class="badge badge-important"><h1>0秒</h1></span></strong>
									</span>
								</label> 
	                        <input class="input-medium focused" id="examId" name="examId" type="hidden" value="${examId}" />
	                        </div>
	                        <div class="span4 text-center">
	                        <button class="btn btn-large btn-info" type="button" onClick="submitInit()" id="lianjiebut">连接指纹仪</button>
	                        <button class="btn btn-large btn-primary" type="button" id="startexam">开始考试</button>
	                        <button class="btn btn-danger btn-large" type="button" id="endexam">结束考试</button>
	                        <textarea name="msg" id="msg" style="display:none;"></textarea>
	                        <textarea name="yamz" id="yamz"  style="display:none;"></textarea>
	                        </div>
					</div>
						<input type="hidden" id="subpages" name="subpages" />
						<input type="hidden" id="subrp" name="subrp" />
						<input type="hidden" id="fingerpath" name="fingerpath" />
						<input type="hidden" id="ids" name="ids" />
					<table id="userList" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th width="10%">机器编号</th>
								<th width="10%">姓名</th>
								<th width="20%">身份证号</th>
								<th width="15%">指纹对比</th>
								<th width="30%">头像</th>
								<th width="15%">操作</th>
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
<script language="javascript">
	var fingerstr ="";
	var idscgg="";
	function sssscc(finger,id){
		//alert(finger+"-------------------"+id);
	}
    function submitInit()
	{
      FPSLID1.ConnectfpScanner();
      //alert (FPSLID1.GetSerialNumber());
      alert("连接成功！");
      $("#lianjiebut").hide();
	}
	function submitRegister()
	{
    FPSLID1.EnrollCount =3;//这里设置登记次数
    FPSLID1.BeginEnroll();//开始触发登记
    $("#msg").val($("#msg").val() + "\r\n"+ "开始登记指纹");
	}

	function submitVerify(finger,id)
	{
		alert("请扫描指纹！");
		fingerstr =finger;
		idscgg = id;
		FPSLID1.StateMark = 2;//设置进入指纹验证状态
		$("#msg").val($("#msg").val() + "\r\n"+"开始指纹验证");
	}
	
	function submitGetVerTpl()
	{
		if (zkonline.GetVerTemplate()){
		   alert(zkonline.VerifyTemplate);
		}   
	}

</script>
<script language="javascript" for='FPSLID1'   event='onFingerTouch(onTouch,ReaderSerNum)' >
 $("#msg").val($("#msg").val() +"\r\n" + "手指按下");
</script> 
 <script language="javascript" for='FPSLID1'    event='onFingerGone(onGone,ReaderSerNum)'>  //'手指离开事件
 $("#msg").val($("#msg").val() + "\r\n" + "手指离开");
 </script> 
 <script language="javascript" for='FPSLID1'    event='OnFpScannerDisConnect(DisConnect,ReaderSerNum)'> //'指纹仪按压事件
 $("#msg").val($("#msg").val() + "\r\n" + "指纹仪断开");
 </script>  
  <script language="javascript" for='FPSLID1'    event='OnFpScannerConnect(Connect,ReaderSerNum)'>//'指纹仪连接事件
  $("#msg").val($("#msg").val()+"\r\n" + "指纹仪已连接");
 </script>  
  <script language="javascript" for='FPSLID1'    event='EnrollIndex(Index)'>//'还需要按压多少次手指事件
  $("#msg").val($("#msg").val() + "\r\n" + "您还需要按压" + Index + "次手指");
</script>  
 <script language="javascript" for='FPSLID1'    event='OnImageReceived(Pict)'>//'指纹仪取图
   FPSLID1.SaveFingerprintToImage("C:\\finger.bmp");
 </script>  
 <script language="javascript" for='FPSLID1'    event='OnFingerQuality(ActionResult,ReaderSerNum)'>//'判断指纹是否合格事件
    if (ActionResult==1){
    	$("#msg").val($("#msg").val() +"\r\n" + "指纹合格");
    }
    else{
    	$("#msg").val($("#msg").val() +"\r\n" + "指纹不合格");
     }
</script>  
 

 <script language="javascript" for='FPSLID1'    event='OnFpEnroll(ActionResult,Atemplate,ReaderSerNum)'>//登记指纹事件
   $("#fingerprintone").val(FPSLID1.GetRegTemplateAsStr());
</script>  
 
<script language="javascript" for='FPSLID1'    event='OnFpCapture(ActionResult,Atemplate,ReaderSerNum)'>//验证指纹事件
//1:1 If FPSLID1.StateMark = 2 Then
	// alert(fingerstr);
     if (FPSLID1.VerifyTemplateFromStr(fingerstr,FPSLID1.GetVerTemplateAsStr())){
          alert("验证成功");
          $("#yamz").val("验证成功");
          fingerprintTime(idscgg,fingerstr);
        }else{
          $("#yamz").val("验证失败");
          alert("验证失败");  
       }
 	function fingerprintTime(ids,fingerpath){
 		//alert(ids);
		$.ajax({
			type : 'post',
			url : '${basepath}/invigilate/getfingerprint?id=' + ids+'&fingerpath='+fingerpath,
			cache : false,
			dataType : 'json',
			success : function(data) {
				reshcg();
			}
		});
	}
</script>  

<script type="text/javascript">
	var intDiffs = parseInt(3600);//倒计时总秒数量
	var timerss;
	var examineeitemid='';
	function timer(intDiff){
		timerss=window.setInterval(function(){
		var day=0,
			hour=0,
			minute=0,
			second=0;//时间默认值		
		if(intDiff > 0){
			day = Math.floor(intDiff / (60 * 60 * 24));
			hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		}else{
			$.ajax({
				type : 'post',
				url : '${basepath}/invigilate/gettofinish?id=' + $("#examId").val(),
				cache : false,
				dataType : 'json',
				success : function(data) {
					alert(data.message);
					reshcg();
					$("#endexam").hide();
					clearInterval(timerss);
					return;
				}
			});
		}
		if (minute <= 9) minute = '0' + minute;
		if (second <= 9) second = '0' + second;
		$('#hour_show').html('<span class="badge badge-important"><h1>'+hour+'时</h1></span>');
		$('#minute_show').html('<span class="badge badge-important"><h1>'+minute+'分</h1></span>');
		$('#second_show').html('<span class="badge badge-important"><h1>'+second+'秒</h1></span>');
		intDiff--;
		}, 1000);
	} 
	//考生下机
	function goDown(ids) {
		if (confirm("是否将该考生执行下机？")) {
			$.ajax({
				type : 'post',
				url : '${basepath}/invigilate/getgoDown?id=' + ids,
				cache : false,
				dataType : 'json',
				success : function(data) {
					alert(data.message);
					reshcg();
				}
			});
		}
	}
	//考生缺考
	function toAbsent(ids) {
		if (confirm("是否将该考生设为缺考？")) {
			$.ajax({
				type : 'post',
				url : '${basepath}/invigilate/gettoAbsent?id='+ids,
				cache : false,
				dataType : 'json',
				success : function(data) {
					alert(data.message);
					reshcg();
				}
			});
		}
	}
	$(document).ready(function() {
		$("#endexam").hide();
		$(document).bind("contextmenu",function(e) { 
			alert("不能进行右键操作！"); 
			return false; 
		});
		$(document).bind("keydown",function(e){ 
			e=window.event||e; 
			if(e.keyCode==116){ 
				e.keyCode = 0; 
				alert("正在考试不要进行刷新操作！"); 
				return false; 
			} 
		}); 
		$(".chzn-select").chosen();
		var examId = $("#examId").val();
		oTable = $('#userList').initDT({
			serverSide : true,
			"sAjaxSource" : "${basepath}/invigilate/getEETable?examId="+examId
		});
		$(".uniform_on").uniform();
		//开始考试
		var examEnddistancetime = '${exam.enddistancetime}';
		if(examEnddistancetime>0){
			$("#startexam").hide();
			$("#endexam").show();
			timer(Number(examEnddistancetime));
		}
		$("#startexam").click(function() {
			$.ajax({
				type : 'post',
				url : '${basepath}/invigilate/gettoStart?id=' + examId,
				cache : false,
				dataType : 'json',
				success : function(data) {
					alert("开始考试！");
					timer(Number(data.message)*60);
					reshcg();
				}
			});
			
			$("#startexam").hide();
			$("#endexam").show();
		});
		//结束考试
		$("#endexam").click(function(){
			if (confirm("确定停止该考试？")) {
				$.ajax({
					type : 'post',
					url : '${basepath}/invigilate/gettofinish?id=' + examId,
					cache : false,
					dataType : 'json',
					success : function(data) {
						alert(data.message);
						reshcg();
					}
				});
				clearInterval(timerss);
				$('#hour_show').html('<span class="badge badge-important"><h1>0时</h1></span>');
				$('#minute_show').html('<span class="badge badge-important"><h1>0分</h1></span>');
				$('#second_show').html('<span class="badge badge-important"><h1>0秒</h1></span>');
			}
			$("#endexam").hide();
		});
		$("#name").change(function(){
			reshcg();
		});
		
	});
	function reshcg() {
		var examineeId = $('#name').val();
		var examId = $('#examId').val();
		var oSettings = [ {
			"name" : "examineeId",
			"value" : examineeId
		},{
			"name" : "examId",
			"value" : examId
		}];
		oTable.gridSearch(this, oSettings);
	}
	//使用input框打开ckfinder
	function BrowseServer(inputId,id) {
		var finder = new CKFinder();
		finder.basePath = '${basepath}/ckfinder'; //导入CKFinder的路径 
		finder.selectActionFunction = SetFileField; //设置文件被选中时的函数 
		finder.selectActionData = inputId; //接收地址的input ID 
		finder.popup();
		examineeitemid = id;
	}
	//文件选中时执行 
	function SetFileField(fileUrl, data) {
		document.getElementById(data["selectActionData"]).value = fileUrl;
		var ids=examineeitemid!=null&&examineeitemid!=''?examineeitemid:"";
		$.ajax({
			type : 'post',
			url : '${basepath}/invigilate/getSculpturepath?id='+ids+'&sculpturepath='+fileUrl,
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