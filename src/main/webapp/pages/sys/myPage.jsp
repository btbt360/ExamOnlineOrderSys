<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<!-- block -->
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">我的面板</a> <span class="divider">/</span></li>
					<li class="active">我的信息</li>
				</ul>
			</div>
		</div>
		<form class="form-horizontal" action="${basepath}/user/saveInfo" method="post">
		<div class="block-content collapse in">
			<div class="span12">
				<c:if test="${message!=null&&message!=''}">
						<div class="alert alert-success" style="margin-right: 8%;text-align: center;">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>保存成功！</strong>
						</div>
				</c:if>
					<fieldset>
						<legend>个人信息维护</legend>
						<div class="control-group">
							<label class="control-label">用户姓名：</label>
							<div class="controls">
								<span class="input-xlarge uneditable-input" >${userToken.vuser.user.name}</span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="user.phone">用户电话：</label>
							<div class="controls">
							<input name="user.id" type="hidden" value="${userToken.vuser.user.id}" >
								<input class="input-xlarge focused" id="user.phone" name="user.phone"
									type="text" placeholder="请输入您的电话！" value="${userToken.vuser.user.phone}" >${phoneMsg}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="user.mobile">用户手机：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="user.mobile" name="user.mobile"
									type="text" placeholder="请输入您的手机号码！" value="${userToken.vuser.user.mobile}" >${mobileMsg}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="user.email">用户邮箱：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="user.email" name="user.email"
									type="text" placeholder="请输入您的邮箱地址！" value="${userToken.vuser.user.email}" >${emailMsg}
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="user.cardno">用户身份证号：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="user.cardno" name="user.cardno"
									type="text" placeholder="请输入您的身份证号！" value="${userToken.vuser.user.cardno}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="user.userType">用户类型：</label>
							<div class="controls">
								<select class="span6 m-wrap" id="user.userType" name="user.userType">
									<option value=''>请选择</option>
									<c:forEach items="${listusertype}" var="usertype">
										<option value="${usertype.dictkey}"
											<c:if test="${usertype.dictkey==utype}">selected</c:if>>${usertype.dictvalue}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="user.loginType">用户登录类型：</label>
							<div class="controls">
								<select class="span6 m-wrap" id="user.loginType" name="user.loginType">
									<option value=''>请选择</option>
									<c:forEach items="${listlogintype}" var="logintype">
										<option value="${logintype.dictkey}"
											<c:if test="${logintype.dictkey==utype}">selected</c:if>>${logintype.dictvalue}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="remark">备注信息：</label>
							<div class="controls">
								<textarea class="input-xlarge focused" id="user.remarks" name="user.remarks" placeholder="请输入备注信息" >${userToken.vuser.user.remarks}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >所属机构：</label>
							<div class="controls">
								<c:forEach items="${userToken.vuser.officenames}" var="officename" ><c:out value="${officename.value}" />|</c:forEach>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >所属角色：</label>
							<div class="controls">
								<c:forEach items="${userToken.vuser.rolenames}" var="rolenames" ><c:out value="${rolenames.value}" />|</c:forEach>
							</div>
						</div>
						<div class="form-actions">
							<button type="button" class="btn btn-primary" onclick="checkshow()">展开详细信息</button>
							<button type="button" class="btn btn-primary" onclick="checkhide()">隐藏详细信息</button>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>
							<button type="reset" class="btn">取消</button>
						</div>
					</fieldset>
			</div>
		</div>
		<div class="block-content collapse in" id="xiangxi">
			<div class="span12">
			<fieldset>
			<legend>详细信息</legend>
				<div class="control-group">
					<label class="control-label">性别：</label>
							<div class="controls">
								<label for="sex0"> 男&nbsp;&nbsp; <input
									type="radio" id="sex0" value="1" name="user.sex"
									checked <c:if test="${userToken.vuser.user.sex==0}">checked</c:if> />
								</label> &nbsp;&nbsp;&nbsp;&nbsp; <label for="sex1">
									女&nbsp;&nbsp; <input type="radio" id="sex1" value="0"
									name="user.sex"
									<c:if test="${userToken.vuser.user.sex==1}">checked</c:if> />
								</label>
						</div>
				</div>
				<div class="control-group">
					<label class="control-label">民族：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.nation}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">出生日期：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.birthdate}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">周岁：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.yearling}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">职务：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.duty}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">籍贯：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.nativeplace}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">政治面貌：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.politicsstatus}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">入团党时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.rupartydate}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">职称级别：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.technicalleave}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">评定时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.evaluatedate}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">聘用日期：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.employdate}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">专业：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.specialty}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">鉴定工种：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.authenticatework}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">干部级别：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.rankcadre}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">工艺师系统：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.craftsmansys}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">研究师系统：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.researchsys}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">有毒有害工种：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.poisonousgz}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">周岁之外的月数：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.othermonthly}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">歇岗时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.restpost}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">退休日期：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.retiredate}</span>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">工作时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.worktime}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">入航天时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.ruspaceflight}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">入本厂时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.rucampaing}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学历：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.seniority}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">学位：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.degree}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">毕业院校：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.graduate}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">毕业时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.graduatedate}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">所学专业：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.major}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">后取学位：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.laterdegree}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">后取学历：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.latereducation}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">后取学位毕业院校：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.graduatelater}</span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">后取学历时间：</label>
					<div class="controls">
						<span class="input-xlarge uneditable-input" >${userToken.vuser.user.latergraduationdate}</span>
					</div>
				</div>
				
				</fieldset>
			</div>
		</div>
		</form>
	</div>
	<script type="text/javascript">
	$("#xiangxi").hide();
	function checkshow(){
		$("#xiangxi").show();
	}
	function checkhide(){
		$("#xiangxi").hide();
	}
	</script>
<c:import url="/pages/include/pageFoot.jsp" />