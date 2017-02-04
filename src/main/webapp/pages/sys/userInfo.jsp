<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
					title="Hide Sidebar" rel='tooltip'>&nbsp;</a>
				</i>
				<i class="icon-chevron-right show-sidebar" style="display: none;">
					<a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a>
				</i>
				<li><a href="#">系统管理</a> <span class="divider">/</span></li>
				<li class="active">用户信息维护</li>
			</ul>
		</div>
	</div>
	<form id="userform" class="form-horizontal"
		action="${basepath}/user/saveuserinfo" id="userform" method="post">
		<div class="block-content collapse in">
			<ul class="nav nav-tabs">
				<li><a href="${basepath}/user/add">用户列表</a></li>
				<li class="active"><a href="${basepath}/user/adduserinfo">用户添加</a>
				<li><a href="${basepath}/user/importUser">用户导入</a></li>
				</li>
			</ul>
			<div class="span12">
				<c:if test="${message!=null&&message!=''}">
					<div class="alert alert-success"
						style="margin-right: 8%; text-align: center;" id="successmessage">
						<button class="close" onclick="$('#successmessage').hide();">&times;</button>
						<strong>保存成功！</strong>
					</div>
				</c:if>
				<fieldset>
					<legend>
						用户信息维护
						<button type="button" class="btn btn-primary"
							style="float: right; margin-right: 8%;" onclick="checkhide()">隐藏详细信息</button>
						<button type="button" class="btn btn-primary"
							style="margin-right: 3%; float: right;" onclick="checkshow()">展开详细信息</button>
					</legend>
					<div class="control-group">
						<label class="control-label">用户姓名：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="username"
								name="user.name" type="text" placeholder="请输入用户姓名！"
								value="${user.name}"> <input id="nid" name="user.id"
								type="hidden" value="${user.id}" />
							<div style="color: red; margin-top: 5px;">${usernameMsg}</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">用户登录名：</label>
						<div class="controls">
							<input class="input-xlarge focused  checkloginname "
								id="loginName" name="user.loginName" type="text"
								placeholder="请输入登录名！" value="${user.loginName}">
							<div style="color: red; margin-top: 5px;">${loginNameMsg}</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">用户身份证号：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="cardno"
								name="user.cardno" type="text" placeholder="请输入身份证号！"
								value="${user.cardno}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="userType">用户类型： </label>
						<div class="controls">
							<select class="span6 m-wrap" id="usertype" name="user.userType">
								<option value=''>请选择</option>
								<c:forEach items="${usertypelist}" var="usertype">
									<option value="${usertype.dictkey}"
										<c:if test="${usertype.dictkey==user.userType}">selected</c:if>>${usertype.dictvalue}</option>
								</c:forEach>
							</select>
							<div style="color: red; margin-top: 5px;">${userTypeMsg}</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="userType">登录类型： </label>
						<div class="controls">
							<select class="span6 m-wrap" id="logintype" name="user.loginType">
								<option value=''>请选择</option>
								<c:forEach items="${logintypelist}" var="logintype">
									<option value="${logintype.dictkey}"
										<c:if test="${logintype.dictkey==user.loginType}">selected</c:if>>${logintype.dictvalue}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="isenable">是否启用：</label>
						<div class="controls">
							<label for="login_flag0"> 是&nbsp;&nbsp; <input
								type="radio" id="login_flag0" value="1" name="user.loginFlag"
								checked <c:if test="${user.loginFlag=='1'}">checked</c:if> />
							</label> &nbsp;&nbsp;&nbsp;&nbsp; <label for="login_flag1">
								否&nbsp;&nbsp; <input type="radio" id="login_flag1" value="0"
								name="user.loginFlag"
								<c:if test="${user.loginFlag=='0'}">checked</c:if> />
							</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">职称级别：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="technicalleave"
								name="user.technicalleave" type="text" placeholder="请输入职称级别！"
								value="${user.technicalleave}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">职务：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="duty" name="user.duty"
								type="text" placeholder="请输入职务！" value="${user.duty}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">籍贯：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="nativeplace"
								name="user.nativeplace" type="text" placeholder="请输入籍贯！"
								value="${user.nativeplace}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">性别：</label>
						<div class="controls">
							<label for="sex0"> 男&nbsp;&nbsp; <input type="radio"
								id="sex0" value="0" name="user.sex" checked
								<c:if test="${user.sex==0}">checked</c:if> />
							</label> &nbsp;&nbsp;&nbsp;&nbsp; <label for="sex1">
								女&nbsp;&nbsp; <input type="radio" id="sex1" value="1"
								name="user.sex" <c:if test="${user.sex==1}">checked</c:if> />
							</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">出生日期：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="birthdate" value="${user.birthdate}" name="user.birthdate">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">周岁：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="yearling"
								name="user.yearling" type="number" placeholder="请输入周岁！"
								value="${user.yearling}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">民族：</label>
						<div class="controls">
							<select class="span6 m-wrap" id="nation" name="user.nation">
								<option value=''>请选择</option>
								<c:forEach items="${nationlist}" var="nation">
									<option value="${nation.dictkey}"
										<c:if test="${nation.dictkey==user.nation}">selected</c:if>>${nation.dictvalue}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">政治面貌：</label>
						<div class="controls">
							<select class="span6 m-wrap" id="politicsstatus"
								name="user.politicsstatus">
								<option value=''>请选择</option>
								<c:forEach items="${politicsstatuslist}" var="politicsstatus">
									<option value="${politicsstatus.dictkey}"
										<c:if test="${politicsstatus.dictkey==user.politicsstatus}">selected</c:if>>${politicsstatus.dictvalue}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">用户角色：</label>
						<div class="controls">
							<c:forEach items="${rolelist}" var="role" varStatus="status">
								<label class="uniform" for="optionsCheckbox_${status.index+1}">
									<input class="uniform_on" type="checkbox"
									id="optionsCheckbox_${status.index+1}" value="${role.id}"
									<c:if test="${fn:indexOf(roleids,role.id)!=-1}">checked</c:if> />${role.name}
									&nbsp;&nbsp;&nbsp;&nbsp;
								</label>
							</c:forEach>
							<input class="input-xlarge focused" id="roleids" name="roleids"
								type="hidden" value="${roleids}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="fingerprintone">指纹信息：</label>
						<div class="controls">
							<div class="progress progress-success" style="width: 20%;">
								<div style="width: 0%;" class="bar" id="fingerprintoneempty"></div>
								<div style="width: 100%;" class="bar" id="fingerprintonefull"></div>
							</div>
							<input type="button" id="init" class="btn btn-primary" value="初始化指纹仪" onClick="submitInit()" />
							<button type="button" id="regbut" class="btn btn-primary" onclick="submitRegister()">录入指纹</button>
							<button type="button" id="resetregbut" class="btn btn-primary" >重新录入指纹</button>
							<input type="hidden" name="user.fingerprintone" id="fingerprintone" value="${user.fingerprintone}" />
						</div>
					</div>
					<div class="control-group" id = "relog">
						<label class="control-label" for="msg">指纹录入日志：</label>
						<div class="controls">
							<textarea name="msg" id="msg" rows="10"class="input-xlarge focused"></textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="remarks">备注信息：</label>
						<div class="controls">
							<textarea class="input-xlarge focused" id="remarks"
								name="user.remarks" placeholder="请输入备注信息">${user.remarks}</textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">所属机构：</label>
						<div class="controls">
							<!-- 弹出层 start -->
							<div class="modal hide fade" id="oModal" tabindex="-1"
								role="dialog">
								<div class="modal-header">
									<button class="close" type="button" data-dismiss="modal">×</button>
									<h3 id="myModalLabel">机构列表</h3>
								</div>
								<div class="modal-body">
									<div id="otree" class="ztree"></div>
								</div>
								<div class="modal-footer">
									<a href="#" class="btn" id="oclosed">关闭</a> <a href="#"
										class="btn btn-primary" id="saveoffice">保存</a>
								</div>
							</div>
							<!-- 弹出层 end -->
							<button type="button" id="editoff" class="btn btn-primary">修改所属机构</button>
							<span id="offnames">${officenames}</span> <input type="hidden"
								name="offids" id="offids" value="${offids}" />
						</div>
					</div>
					<div class="form-actions">
						<button type="button" class="btn btn-primary" id="savebutton">保存</button>
						<input type="button" value="返回" class="btn"
							onclick="javascript:window.location.href='${basepath}/user/add'">
					</div>
				</fieldset>
			</div>
		</div>
		<div class="block-content collapse in" id="xiangxi">
			<div class="span12" style="padding-left: 3%;">
				<fieldset>
					<legend>详细信息</legend>
					<div class="control-group">
						<label class="control-label">用户Email：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="email" name="user.email"
								type="text" placeholder="请输入用户Email！" value="${user.email}">
							<div style="color: red; margin-top: 5px;">${noemailMsg}</div>
							<div style="color: red; margin-top: 5px;">${erroremailMsg}</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">用户电话：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="phone" name="user.phone"
								type="text" placeholder="请输入用户电话！" value="${user.phone}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">用户手机：</label>
						<div class="controls">
							<input class="input-xlarge focused" maxlength="11" id="mobile"
								name="user.mobile" type="text" placeholder="请输入用户手机！"
								value="${user.mobile}">
							<div style="color: red; margin-top: 5px;">${mobileMsg}</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">入团党时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="rupartydate" value="${user.rupartydate}"
								name="user.rupartydate">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">评定时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="evaluatedate" value="${user.evaluatedate}"
								name="user.evaluatedate">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">聘用日期：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="employdate" value="${user.employdate}"
								name="user.employdate">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">专业：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="specialty"
								name="user.specialty" type="text" placeholder="请输入专业！"
								value="${user.specialty}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">鉴定工种：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="authenticatework"
								name="user.authenticatework" type="text" placeholder="请输入鉴定工种！"
								value="${user.authenticatework}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">干部级别：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="rankcadre"
								name="user.rankcadre" type="text" placeholder="请输入干部级别！"
								value="${user.rankcadre}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">工艺师系统：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="craftsmansys"
								name="user.craftsmansys" type="text" placeholder="请输入工艺师系统！"
								value="${user.craftsmansys}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">研究师系统：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="researchsys"
								name="user.researchsys" type="text" placeholder="请输入研究师系统！"
								value="${user.researchsys}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">有毒有害工种：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="poisonousgz"
								name="user.poisonousgz" type="text" placeholder="请输入有毒有害工种！"
								value="${user.poisonousgz}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">周岁之外的月数：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="othermonthly"
								name="user.othermonthly" type="number" placeholder="请输入周岁之外的月数！"
								value="${user.othermonthly}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">歇岗时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="restpost" value="${user.restpost}" name="user.restpost">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">退休日期：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="retiredate" value="${user.retiredate}"
								name="user.retiredate">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">工作时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="worktime" value="${user.worktime}" name="user.worktime">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">入航天时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="ruspaceflight" value="${user.ruspaceflight}"
								name="user.ruspaceflight">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">入本厂时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="rucampaing" value="${user.rucampaing}"
								name="user.rucampaing">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">学历：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="seniority"
								name="user.seniority" type="text" placeholder="请输入学历！"
								value="${user.seniority}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">学位：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="degree"
								name="user.degree" type="text" placeholder="请输入学位！"
								value="${user.degree}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">毕业院校：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="graduate"
								name="user.graduate" type="text" placeholder="请输入学位！"
								value="${user.graduate}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">毕业时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="graduatedate" value="${user.graduatedate}"
								name="user.graduatedate">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">所学专业：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="major" name="user.major"
								type="text" placeholder="请输入所学专业！" value="${user.major}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">后取学位：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="laterdegree"
								name="user.laterdegree" type="text" placeholder="请输入后取学位！"
								value="${user.laterdegree}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">后取学历：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="latereducation"
								name="user.latereducation" type="text" placeholder="请输入后取学历！"
								value="${user.latereducation}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">后取学位毕业院校：</label>
						<div class="controls">
							<input class="input-xlarge focused" id="graduatelater"
								name="user.graduatelater" type="text" placeholder="请输入后取学位毕业院校！"
								value="${user.graduatelater}" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">后取学历时间：</label>
						<div class="controls">
							<input type="text" class="input-medium datetimepicker"
								id="latergraduationdate" value="${user.latergraduationdate}"
								name="user.latergraduationdate">
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</form>
</div>
<script language="javascript">

    function submitInit()
	{
      FPSLID1.ConnectfpScanner();
      //alert (FPSLID1.GetSerialNumber());
      $("#init").hide();
	}
	function submitRegister()
	{
    FPSLID1.EnrollCount =3;//这里设置登记次数
    FPSLID1.BeginEnroll();//开始触发登记
    $("#msg").val($("#msg").val() + "\r\n"+ "开始登记指纹");
    $("#regbut").hide();
	}

	function submitVerify()
	{
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
   
    if (ActionResult==1)
    {
    	$("#msg").val($("#msg").val() +"\r\n" + "指纹合格");
    }
    else
    {
    	$("#msg").val($("#msg").val() +"\r\n" + "指纹不合格");
     }

</script>  
 

 <script language="javascript" for='FPSLID1'    event='OnFpEnroll(ActionResult,Atemplate,ReaderSerNum)'>//'登记指纹事件
   $("#fingerprintone").val(FPSLID1.GetRegTemplateAsStr());
   $("#fingerprintoneempty").hide();
   $("#fingerprintonefull").show();
   $("#resetregbut").show();
   $("#relog").hide();
</script>  
 
<script language="javascript" for='FPSLID1'    event='OnFpCapture(ActionResult,Atemplate,ReaderSerNum)'>//'验证指纹事件
    
  vertplbox.value = FPSLID1.GetVerTemplateAsStr(); 
   //1:1 If FPSLID1.StateMark = 2 Then
     if (FPSLID1.VerifyTemplateFromStr($("#fingerprintone").val(),vertplbox.value)){
          star.value  =   ("验证成功");
        }else
      {
          star.value  =  ("验证失败");  
       }
  
</script>  

<script type="text/javascript">
	$("#xiangxi").hide();
	$("#fingerprintonefull").hide();
	$("#resetregbut").hide();
	var fingerprintoneStr = $("#fingerprintone").val();
	if (fingerprintoneStr.trim() != '') {
		$("#fingerprintoneempty").hide();
		$("#fingerprintonefull").show();
		$("#resetregbut").show();
		$("#init").hide();
		$("#regbut").hide();
		$("#relog").hide();
	}
	$("#resetregbut").click(function(){
		$("#fingerprintoneempty").show();
		$("#fingerprintonefull").hide();
		$("#resetregbut").hide();
		$("#relog").show();
		$("#msg").val('');
		$("#init").show();
		$("#regbut").show();
	});
	function checkshow() {
		$("#xiangxi").show();
	}
	function checkhide() {
		$("#xiangxi").hide();
	}
	var userid = '${user.id}';
	var settingoffice = {
		check : {
			enable : true,
			chkStyle : "radio",
			radioType : "level"
		},
		async : {
			enable : true,
			url : "${basepath}/office/getOfficeTree",
			autoParam : [ "id", "name" ],
			otherParam : {
				"otherParam" : "zTreeAsyncTest",
				"userid" : userid
			},
			dataFilter : filter
		},
		callback : {
			onClick : zTreeOnClick,
			onAsyncSuccess : onAsyncSuccesso
		}
	};

	var treeNodez;

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	function onAsyncSuccesso(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("otree");
		var nodes = treeObj.getNodesByParam("parentId", 0, null);
		if (nodes.length > 0) {
			treeObj.expandNode(nodes[0], true, false, false);
		}
	}

	//机构树单击事件

	function zTreeOnClick(event, treeId, treeNode) {
		if (treeNode.nodetype == 1) {
			treeNodez = treeNode.nodetype;

		} else {
			treeNodez = treeNode.nodetype;

		}
	}

	function getAllCheckedNodeo() {
		var treeObj = $.fn.zTree.getZTreeObj("otree");
		var nodes = treeObj.getCheckedNodes(true);
		var str = "";
		var ids = "";
		for (var i = 0; i < nodes.length; i++) {
			str = str + nodes[i].name + "|";
			ids = ids + nodes[i].id + "|";
		}
		$("#offids").val(ids);
		$("#offnames").text(str);
	}

	function RegUser() {
		setTimeout("showFingerprintone();", 3000);

	}
	function showFingerprintone() {
		alert("录入成功");
		$("#fingerprintone").val("111111");
		$("#fingerprintoneempty").hide();
		$("#fingerprintonefull").show();
	}
	$(document).ready(function() {
		$('.datetimepicker').datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd',
			weekStart : 1,
			todayBtn : 1,
			autoclose : true,
			todayHighlight : 1,
			startView : 2,
			forceParse : true,
			minView : 2,//只到天
			showMeridian : 1
		}).on('changeDate', function(ev) {
			$(this).datetimepicker('hide');
		});

		$.fn.zTree.init($("#otree"), settingoffice);
		$("#editoff").click(function() {
			$('#oModal').modal('show');
		});
		$("#oclosed").click(function() {
			$('#oModal').modal('hide');
		});
		$("#saveoffice").click(function() {
			$("#offnames").text("");
			getAllCheckedNodeo();
			$('#oModal').modal('hide');
		});
		$("#savebutton").click(function() {
			var box = "";
			$("input[id^='optionsCheckbox']").each(function(i) {

				if ($(this).is(':checked')) {
					box = box + $(this).val() + "|";
				}
			});
			$("#roleids").val(box);
			$("#userform").submit();
		});
		var jqObj = new JQvalidate();
		var id = $('#nid').val();
		var userform = "userform";
		jqObj.setform(userform);
		jqObj.set("user.name", "required", "请输入用户姓名!");
		jqObj.set("user.loginName", "required", "请输入登录名!");
		jqObj.set("user.cardno", "required", "请输入身份证号码!");
		jqObj.set("user.userType", "required",  "请选择用户类型!");
		jqObj.set("user.loginType", "required",  "请选择主页类型!");
		jqObj.set("user.nation", "required",  "请选择民族!");
		jqObj.set("user.yearling", "required",  "请选择周岁!");
		/**
		if(id!=null&&id==0){
			  jqObj.set("user.loginName", "remote",  "登录名重复!");
		}
		jqObj.set("user.nation", "required",  "请输入民族!"); 
		jqObj.set("user.politicsstatus", "required",  "请输入政治面貌!");
		jqObj.set("user.yearling", "required",  "请输入周岁!");
		jqObj.set("user.birthdate", "required",  "请输入出生日期!");
		jqObj.set("user.email", "required",  "请输入用户邮箱!");  
		jqObj.set("user.email", "email",  "请输入正确的用户邮箱!");	   
		jqObj.set("user.mobile", "required",  "请输入用户手机!");
		jqObj.set("user.mobile", "number",  "请输入正确的手机号!");
		jqObj.set("user.mobile", "isMobile",  "请输入正确格式的手机号!");
		 */
		jqObj.Run();

	})
</script>
<c:import url="/pages/include/pageFoot.jsp" />