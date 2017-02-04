<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
	<!-- block -->
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"><a href='#'
						title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;"><a
						href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">系统管理</a> <span class="divider">/</span></li>
					<li class="active">组织机构维护</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
			<ul class="nav nav-tabs">
				<li><a href="${basepath}/office/add">机构列表</a></li>
				<li class="active"><a href="${basepath}/office/addofficeinfo">机构添加</a></li>
			</ul>
			<div class="span12">
				<c:if test="${message!=null&&message!=''}">
					<div class="alert alert-success"
						style="margin-right: 8%; text-align: center;" id="successmessage">
						<button class="close" onclick="$('#successmessage').hide();">&times;</button>
						<strong>保存成功！</strong>
					</div>
				</c:if>
				<form class="form-horizontal"
					action="${basepath}/office/saveofficeinfo" id="officeform"
					method="post">
					<fieldset>
						<legend>机构信息维护</legend>
						<div class="control-group">
							<label class="control-label">机构类型：</label>
							<div class="controls">
								<c:if test="${typename==''}">
								   机构
								</c:if>
								${typename} <input type="hidden" name="office.type"
									value="${type==''||type==null?1:type}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上级机构：</label>
							<div class="controls">
								<input class="input-xlarge focused" type="text" id="menunames"
									 value="${lastpname}" readonly="readonly" />
								<input class="input-xlarge focused" type="hidden" id="menuids"
									name="office.parentId" value="${parentId}" readonly="readonly" />
								<!-- 弹出层 start -->
								<div class="modal hide fade" id="bModal" tabindex="-1"
									role="dialog">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal">×</button>
										<h3 id="myModalLabel">上级机构</h3>
									</div>
									<div class="modal-body">
										<div id="btree" class="ztree"></div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn" id="bclosed">关闭</a> <a href="#"
											class="btn btn-primary" id="savemenu">保存</a>
									</div>
								</div>
								<!-- 弹出层 end -->
								<button type="button" id="editlast" class="btn btn-primary">修改上级机构</button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span id="group_1">机构</span>编码：</label>
							<div class="controls">
								<input onkeyup="this.value=this.value.replace(/\D/g, '')" class="input-xlarge focused" id="code" name="office.code"
									type="text" placeholder="请填写编码！" value="${office.code}">
								<input id="offid" name="office.id" type="hidden" value="${office.id}" /> *
								<div style="color: red; margin-top: 5px;">${codeMsg}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span id="group_2">机构</span>名称：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="name" name="office.name"
									type="text" placeholder="请输入名称！" value="${office.name}"> *
								<div style="color: red; margin-top: 5px;">${nameMsg}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">属于地区：</label>
							<div class="controls">
								<input class="input-xlarge focused" type="text" id="areanames"
									name="area.name" value="${areaname}" readonly="readonly" /> <input
									class="input-xlarge focused" type="hidden" id="areaids"
									name="office.areaId" value="${areaid}" readonly="readonly" />

								<!-- 弹出层 start -->
								<div class="modal hide fade" id="oModal" tabindex="-1"
									role="dialog">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal">×</button>
										<h3 id="myModalLabel">地区列表</h3>
									</div>
									<div class="modal-body">
										<div id="otree" class="ztree"></div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn" id="oclosed">关闭</a> <a href="#"
											class="btn btn-primary" id="savearea">保存</a>
									</div>
								</div>
								<!-- 弹出层 end -->
								<button type="button" id="edithigh" class="btn btn-primary">修改上级地区</button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span id="group_3">机构</span>负责人姓名：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="master"
									name="office.master" type="text" placeholder="请输入负责人姓名！"
									value="${office.master}"> *
								<div style="color: red; margin-top: 5px;">${masterMsg}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span id="group_4">机构</span>负责人电话：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="phone"
									name="office.phone" type="text" placeholder="请输入负责人电话！"
									value="${office.phone}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span id="group_5">机构</span>负责人邮箱：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="email"
									name="office.email" type="text" placeholder="请输入负责人邮箱！"
									value="${office.email}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><span id="group_5">机构</span>联系地址：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="address"
									name="office.address" type="text" placeholder="请输入联系地址！"
									value="${office.address}">
							</div>
						</div>
						<!-- 
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="delFlag0">是&nbsp;&nbsp;<input
									type="radio" id="delFlag0" value="1" name="office.delFlag"
									checked /></label> &nbsp;&nbsp;&nbsp;&nbsp; <label for="delFlag1">否&nbsp;&nbsp;<input
									type="radio" id="delFlag1" value="0" name="office.delFlag"
									<c:if test="${office.delFlag=='0'}">checked</c:if> /></label>
							</div>
						</div> -->
						<div class="control-group">
							<label class="control-label" for="remark">备注信息：</label>
							<div class="controls">
								<textarea class="input-xlarge focused" id="remarks"
									name="office.remarks" placeholder="请输入备注信息">${office.remarks}</textarea>
							</div>
						</div>
						<div class="form-actions">
							<button type="button" class="btn btn-primary" id="savebutton">保存</button>
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/office/add'">
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">
	

	var areaid = '${areaid}';
	var officeid = '${parentId}';
	officeid = officeid == '' ? '${office.id}' : officeid;
	var settingarea = {
		check : {
			enable : true, //设置 zTree 的节点上是否显示 checkbox / radio
			chkStyle : "radio", //设置为单选框
			radioType : "all"
		},
		async : {
			enable : true, //设置 zTree 是否开启异步加载模式
			url : "${basepath}/office/getAreaTree", //Ajax 获取数据的 URL 地址。
			autoParam : [ "id", "name" ], //异步加载时需要自动提交父节点属性的参数。
			otherParam : { //Ajax 请求提交的静态参数键值对。
				"otherParam" : "zTreeAsyncTest",
				"areaid" : areaid,
				"officeid" : officeid
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
		$("#areaids").val(ids);
		$("#areanames").val(str);
	}
	$(document).ready(function() {
		$.fn.zTree.init($("#otree"), settingarea);
		$.fn.zTree.init($("#btree"), settinglastparent);
		var officetype = '${type}';
		if (officetype == '2') {
			$("span[id^='group_']").each(function(i) {
				$(this).text("部门");
			});
		} else if (officetype == '3') {
			$("span[id^='group_']").each(function(i) {
				$(this).text("岗位");
			});
		} else {
			$("span[id^='group_']").each(function(i) {
				$(this).text("机构");
			});
		}
		$("#savebutton").click(function() {
			$("#officeform").submit();
		});
		
		$("#edithigh").click(function() {
			$('#oModal').modal('show');
		});
		$("#oclosed").click(function() {
			$('#oModal').modal('hide');
		});
		$("#savearea").click(function() {
			$("#areanames").text("");
			getAllCheckedNodeo();
			$('#oModal').modal('hide');
		});
		
		
		$("#editlast").click(function() {
			$('#bModal').modal('show');
		});
		$("#bclosed").click(function() {
			$('#bModal').modal('hide');
		});
		$("#savemenu").click(function() {
			$("#menunames").text("");
			getAllCheckedNodeb();
			$('#bModal').modal('hide');
		});
		
		var jqObj = new JQvalidate();
  	     var officeform ="officeform"; 
  	     var id = $('#offid').val();
      	jqObj.setform(officeform);
  	    jqObj.set("office.code", "required",  "请填写机构编码!");
  	    if(id!=null&&id==0){
  	    jqObj.set("office.code", "remote",  "机构编码重复!");
  	    }
  	    jqObj.set("office.name", "required",  "请填写机构名称!");
  	    jqObj.set("office.master", "required",  "请填写负责人姓名!");       
  	    jqObj.Run();
	});
	
	var idss = '${parentId}';
	var settinglastparent = {
		check : {
			enable : true, //设置 zTree 的节点上是否显示 checkbox / radio
			chkStyle : "radio", //设置为单选框
			radioType : "all"
		},
		async : {
			enable : true, //设置 zTree 是否开启异步加载模式
			url : "${basepath}/office/getOfficeTree", //Ajax 获取数据的 URL 地址。
			autoParam : [ "id", "name" ], //异步加载时需要自动提交父节点属性的参数。
			otherParam : { //Ajax 请求提交的静态参数键值对。
				"otherParam" : "zTreeAsyncTest",
				"ids" : idss
			},
			dataFilter : filterb
		//用于对 Ajax 返回数据进行预处理的函数。
		},
		callback : {
			onClick : zTreeOnClickb, //用于捕获节点被点击的事件回调函数
			onAsyncSuccess : onAsyncSuccessob
		//用于捕获异步加载正常结束的事件回调函数
		}
	};

	var treeNodezb;

	function filterb(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}

	function onAsyncSuccessob(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("btree");
		var nodes = treeObj.getNodesByParam("parentId", 0, null);
		if (nodes.length > 0) {
			treeObj.expandNode(nodes[0], true, false, false);
		}
	}

	//机构树单击事件
	function zTreeOnClickb(event, treeId, treeNode) {
		if (treeNode.nodetype == 1) {
			treeNodezb = treeNode.nodetype;

		} else {
			treeNodezb = treeNode.nodetype;

		}
	}
	function getAllCheckedNodeb() {
		var treeObj = $.fn.zTree.getZTreeObj("btree");
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
</script>
<c:import url="/pages/include/pageFoot.jsp" />