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
					<li class="active">角色信息维护</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
			<ul class="nav nav-tabs">
				<li><a href="${basepath}/role/add">角色列表</a></li>
				<li class="active"><a href="${basepath}/role/addroleinfo">角色添加</a></li>
			</ul>
			<div class="span12">
				<c:if test="${message!=null&&message!=''}">
					<div class="alert alert-success"
						style="margin-right: 8%; text-align: center;" id="successmessage">
						<button class="close" onclick="$('#successmessage').hide();">&times;</button>
						<strong>保存成功！</strong>
					</div>
				</c:if>
				<form  id="roleform" class="form-horizontal" action="${basepath}/role/saveInfo"
					method="post">
					<fieldset>
						<legend>角色信息维护</legend>
						<div class="control-group">
							<label class="control-label">角色名称：</label>
							<div class="controls">
								<input class="input-xlarge focused" maxlength="10" id="name" placeholder="请输入角色名称最多10个字" name="role.name"
									type="text" placeholder="请输入角色名称！" value="${vrole.role.name}" class="required">
								<input name="role.id"  id = "cid"  type="hidden" value="${vrole.role.id}" /> 
								<div style="color: red; margin-top: 5px;">${rolenameMsg}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="roletype">角色类型：</label>
							<div class="controls">
								<select class="span6 m-wrap" id="roletype" name="role.roleType">
									<option	value=''>请选择</option>
									<c:forEach items="${listd}" var="dict">
										<option value="${dict.dictkey}"  <c:if test="${dict.dictkey==vroletype}">selected</c:if>>${dict.dictvalue}</option>
									</c:forEach>
								</select>		<div style="color: red; margin-top: 5px;">${roletypeMsg}</div>  						
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="roleright">拥有权限：</label>
							<div class="controls">
								<!-- 弹出层 start -->
								<div class="modal hide fade" id="myModal" tabindex="-1"
									role="dialog">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal">×</button>
										<h3 id="myModalLabel">权限列表</h3>
									</div>
									<div class="modal-body">
										<div id="ztree" class="ztree"></div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn" id="closed">关闭</a> <a href="#"
											class="btn btn-primary" id="saveroleright">保存</a>
									</div>
								</div>
								<!-- 弹出层 end -->
								<button type="button" id="editbt" class="btn btn-primary">修改权限</button>
								<span id="rolerightnames">${vrole.resnames}</span> <input
									type="hidden" name="resids" id="resids"  value="${vrole.resids}"/> <input
									type="hidden" name="rightids" id="rightids"  value="${vrole.rightids}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="isenable">是否启用：</label>
							<div class="controls">
								<label for="del_flag0">是&nbsp;&nbsp;<input type="radio"
									id="del_flag0" value="1" name="role.useable" checked /></label>
								&nbsp;&nbsp;&nbsp;&nbsp; <label for="del_flag1">否&nbsp;&nbsp;<input
									type="radio" id="del_flag1" value="0" name="role.useable"
									<c:if test="${vrole.role.useable=='0'}">checked</c:if> /></label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="remark">备注信息：</label>
							<div class="controls">
								<textarea class="input-xlarge focused" maxlength="255" id="remarks"
									name="role.remarks" placeholder="请输入备注信息,最多255字">${vrole.role.remarks}</textarea>
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
										<h3 id="myModalLabel">权限列表</h3>
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
								<span id="offnames">${vrole.offnames}</span> <input
									type="hidden" name="offids" id="offids" value="${vrole.offids}" />
							</div>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>
							<input type="button" value="返回"   class="btn"  onclick="javascript:window.location.href='${basepath}/role/add'" >
						</div>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
<script type="text/javascript">
	var roleid = '${vrole.role.id}';
	var setting = {
		check : {
			enable : true,
			chkStyle : "checkbox",
			chkboxType: {"Y": "ps", "N": "ps"}
		},
		async : {
			enable : true,
			url : "${basepath}/right/getRightTree",
			autoParam : [ "id", "name" ],
			otherParam : {
				"otherParam" : "zTreeAsyncTest",
				"roleid" : roleid
			},
			dataFilter : filter
		},
		callback : {
			onClick : zTreeOnClick,
			onAsyncSuccess : onAsyncSuccess
		}
	};
	var settingoffice = {
		check : {
			enable : true,
			chkStyle : "checkbox"
		},
		async : {
			enable : true,
			url : "${basepath}/office/getOfficeTree",
			autoParam : [ "id", "name" ],
			otherParam : {
				"otherParam" : "zTreeAsyncTest",
				"roleid" : roleid
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

	function onAsyncSuccess(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("ztree");
		var nodes = treeObj.getNodesByParam("parentId", 0, null);
		if (nodes.length > 0) {
			treeObj.expandNode(nodes[0], true, false, false);
		}
	}

	function onAsyncSuccesso(event, treeId, treeNode, msg) {
		var treeObj = $.fn.zTree.getZTreeObj("ztree");
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
	function getAllCheckedNode() {
		var treeObj = $.fn.zTree.getZTreeObj("ztree");
		var nodes = treeObj.getCheckedNodes(true);
		var str = "";
		var ids = "";
		var rightids = "";
		for (var i = 0; i < nodes.length; i++) {
			str = str + nodes[i].name + "|";
			ids = ids + nodes[i].id + "|";
			rightids = rightids + nodes[i].resid + "|";
		}
		$("#resids").val(ids);
		$("#rightids").val(rightids);
		$("#rolerightnames").text(str);
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
	$(document).ready(function() {
		$.fn.zTree.init($("#ztree"), setting);
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

		$("#editbt").click(function() {
			$('#myModal').modal('show');
		});
		$("#closed").click(function() {
			$('#myModal').modal('hide');
		});
		$("#saveroleright").click(function() {
			$("#rolerightnames").text("");
			getAllCheckedNode();
			$('#myModal').modal('hide');
		});
		  var jqObj = new JQvalidate();
		  var id = $('#cid').val();
   	        var roleform ="roleform"; 
   	       jqObj.setform(roleform);
         jqObj.set("role.name", "required",  "请输入角色名称!");
         if(id!=null&&id==0){
         jqObj.set("role.name", "remote",  "角色名称重复!");
         }
   	     jqObj.set("role.roleType", "required",  "请选择角色类型!");   	
   	     jqObj.Run();
	})
</script>
<c:import url="/pages/include/pageFoot.jsp" />