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
					<li class="active">菜单信息维护</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
			<ul class="nav nav-tabs">
				<li><a href="${basepath}/menu/add">菜单列表</a></li>
				<li class="active"><a href="${basepath}/menu/addmenuinfo">菜单添加</a></li>
			</ul>
			<div class="span12">
				<c:if test="${message!=null&&message!=''}">
					<div class="alert alert-success"
						style="margin-right: 8%; text-align: center;" id="successmessage">
						<button class="close" onclick="$('#successmessage').hide();">&times;</button>
						<strong>保存成功！</strong>
					</div>
				</c:if>
				<form class="form-horizontal" action="${basepath}/menu/menuSave"
					id="menuform" method="post">
					<fieldset>
						<legend>菜单信息维护</legend>
						<div class="control-group">
							<label class="control-label">菜单名称：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="menu.id" name="menu.id"
									type="hidden" value="${menu.id}"> <input
									class="input-xlarge focused" id="menuname" name="menu.name"
									type="text" placeholder="请输入菜单名称！" value="${menu.name}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上级菜单：</label>
							<div class="controls">
								<input class="input-xlarge focused" type="text" id="menunames"
									name="menu.name" value="${pmenu.name}" readonly="readonly" />
								<input class="input-xlarge focused" type="hidden" id="menuids"
									name="menu.parentId" value="${pmenu.id}" readonly="readonly" />
								<!-- 弹出层 start -->
								<div class="modal hide fade" id="oModal" tabindex="-1"
									role="dialog">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal">×</button>
										<h3 id="myModalLabel">菜单列表</h3>
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
								<button type="button" id="edithigh" class="btn btn-primary">修改上级菜单</button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">链接：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="menuhref"
									name="menu.href" type="text" placeholder="请输入链接地址！"
									value="${menu.href}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">是否显示：</label>
							<div class="controls">
								<input class="rad" type="radio" name="menu.isShow" id="menu.isShow"
									value="1" checked="checked"/>是
									 <input class="rad" type="radio" name="menu.isShow"
									id="menu.isShow" value="2" />否
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">权限标识：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="menu.permission" type="text"
									name="menu.permission" placeholder="权限标识" value="${menu.permission}"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备注信息：</label>
							<div class="controls">
								<textarea class="input-xlarge focused" id="menu.remarks"
									name="menu.remarks" placeholder="请输入备注信息">${menu.remarks}</textarea>
							</div>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>							
							<input type="button" value="取消"   class="btn"  onclick="javascript:window.location.href='${basepath}/menu/add'" >
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
	var menuid = '${menu.parentId}';
	var settingmenu = {
		check : {
			enable : true, //设置 zTree 的节点上是否显示 checkbox / radio
			chkStyle : "radio", //设置为单选框
			radioType : "all"
		},
		async : {
			enable : true, //设置 zTree 是否开启异步加载模式
			url : "${basepath}/menu/getMenuTree", //Ajax 获取数据的 URL 地址。
			autoParam : [ "id", "name" ], //异步加载时需要自动提交父节点属性的参数。
			otherParam : { //Ajax 请求提交的静态参数键值对。
				"otherParam" : "zTreeAsyncTest",
				"menuid" : menuid
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
		
		var jqObj = new JQvalidate();
 	    var menuform ="menuform"; 
     	jqObj.setform(menuform);
 	    jqObj.set("menu.name", "required",  "请填写菜单名称!");
 	    jqObj.set("menu.href", "required",  "请填写链接地址!");
 	    //jqObj.set("menu.href", "url",  "请填写正确的链接地址!");    
 	    jqObj.set("menu.permission", "required",  "请填写权限标识!"); 
 	    jqObj.Run();
		
	})
</script>
<c:import url="/pages/include/pageFoot.jsp" />