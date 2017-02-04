<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class='icon-chevron-left hide-sidebar'><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class='icon-chevron-right show-sidebar" style="display: none;'><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">资源管理</a> <span class="divider">/</span></li>
					<li class="active">科目管理</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
		<ul class="nav nav-tabs">
			<li><a href="${basepath}/subject/addsub">科目列表</a></li>
			<li class="active"><a href="${basepath}/subject/addsubinfo">科目添加</a></li>
		</ul>
			<div class="span12">
				<div <c:if test="${flagcg==1}">class="alert alert-success"</c:if>
					 <c:if test="${flagcg==0}">class="alert alert-error"</c:if>
					 style="margin-right: 8%;text-align: center;" id ="messagealert">
						<button class="close" id="closebut">&times;</button>
						<c:if test="${flagcg==1}"><strong>保存成功！</strong></c:if>
						<c:if test="${flagcg==0}"><strong>保存失败！</strong></c:if>
				</div>
				<form id="subinfoform" class="form-horizontal" action="${basepath}/subject/savesub" method="post">
					<fieldset>
						<legend>科目管理</legend>
						<div class="control-group">
							<label class="control-label">科目名称：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="name" name="subject.name"
									type="text" placeholder="请输入科目名称!" value="${subject.name}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上级科目：</label>
							<div class="controls">
								<input class="input-xlarge focused" type="text" id="menunames"
									name="subject.name" value="${psubject.name}" readonly="readonly" />
								<input class="input-xlarge focused" type="hidden" id="menuids"
									name="subject.parentid" value="${psubject.id}" readonly="readonly" />
								<!-- 弹出层 start -->
								<div class="modal hide fade" id="oModal" tabindex="-1"
									role="dialog">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal">×</button>
										<h3 id="myModalLabel">上级科目</h3>
									</div>
									<div class="modal-body">
										<div id="otree" class="ztree"></div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn" id="oclosed">关闭</a> <a href="#"
											class="btn btn-primary" id="savemenu">保存</a>
									</div>
								</div>
								<!-- 弹出层 end -->
								<button type="button" id="edithigh" class="btn btn-primary">修改上级科目</button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="phone">科目编码：</label>
							<div class="controls">
							<input name="subject.id" type="hidden" value="${subject.id}" >
								<input class="input-xlarge focused" id="code" name="subject.code"
									type="text" placeholder="请输入科目编码!" value="${subject.code}" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="mobile">科目描述：</label>
							<div class="controls">
								<textarea id="info" name="subject.info" placeholder="请输入描述信息!" rows="10" style="width: 50%;	" >${subject.info}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="login_flag0">&nbsp;&nbsp;是&nbsp;&nbsp;
								<input type="radio" id="login_flag0" value="1" name="subject.isenable" checked
								<c:if test="${subject.isenable==1}">checked</c:if> />
								</label>&nbsp;&nbsp;&nbsp;&nbsp;<label for="login_flag1">&nbsp;&nbsp;否&nbsp;&nbsp;
								<input type="radio" id="login_flag1" value="0" name="subject.isenable"
								<c:if test="${subject.isenable==0}">checked</c:if> />
								</label>
							</div>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>							
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/subject/addsub'">
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">
var subjectid = '${subject.parentid}';
var settingmenu = {
	check : {
		enable : true, //设置 zTree 的节点上是否显示 checkbox / radio
		chkStyle : "radio", //设置为单选框
		radioType : "all"
	},
	async : {
		enable : true, //设置 zTree 是否开启异步加载模式
		url : "${basepath}/subject/getSubjectTree", //Ajax 获取数据的 URL 地址。
		autoParam : [ "id", "name" ], //异步加载时需要自动提交父节点属性的参数。
		otherParam : { //Ajax 请求提交的静态参数键值对。
			"otherParam" : "zTreeAsyncTest",
			"subjectid" : subjectid
		},
		dataFilter : filter
	//用于对 Ajax 返回数据进行预处理的函数。
	},
	callback : {
		onClick : zTreeOnClick, //用于捕获节点被点击的事件回调函数
		onAsyncSuccess : onAsyncSuccesso
	//用于捕获异步加载正常结束的事件回调函数
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
		str = str + nodes[i].name;
		ids = ids + nodes[i].id;
	}
	$("#menuids").val(ids);
	$("#menunames").val(str);
}
$(document).ready(function() {
	$.fn.zTree.init($("#otree"), settingmenu);
	$("#edithigh").click(function() {
		$('#oModal').modal('show');
	});
	$("#oclosed").click(function() {
		$('#oModal').modal('hide');
	});
	$("#savemenu").click(function() {
		$("#menunames").text("");
		getAllCheckedNodeo();
		$('#oModal').modal('hide');
	});
	$("#savebutton").click(function() {
		var box = "";
		$("input[id^='optionsCheckbox_']").each(function(i) {
			box = box + $(this).val() + "|";
		});
		$("#menuids").val(box);
		$("#menuform").submit();
	});
	
	$("#messagealert").hide();
	var flagcg = '${flagcg}';
	if(flagcg!=null&&flagcg!=''){
		$("#messagealert").show();
	}
	$("#closebut").click(function(){
		$("#messagealert").hide();
	});
	var jqObj = new JQvalidate();
	    var subform ="subinfoform"; 
 		jqObj.setform(subform);
	    jqObj.set("subject.name", "required",  "请输入科目名称!");  
	    jqObj.set("subject.code", "required",  "请输入科目编码!");	 
	    jqObj.set("subject.code", "required",  "请输入科目编码!");	 
	    jqObj.Run();
	
});
</script>
<c:import url="/pages/include/pageFoot.jsp" />