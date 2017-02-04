<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
<!-- block -->
	<div class="block" >
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar">
						<a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a>
					</i>
					<i class="icon-chevron-right show-sidebar" style="display: none;">
						<a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a>
					</i>
					<li>
						<a href="#">系统管理</a>
						<span class="divider">/</span>
					</li>
					<li class="active">地区信息维护</li>
				</ul>
			</div>
		</div>
		<div class="block-content collapse in">
			<ul class="nav nav-tabs">
				<li>
					<a href="${basepath}/area/add">地区列表</a>
				</li>
				<li class="active">
					<a href="${basepath}/area/addareainfo">地区添加</a>
				</li>
			</ul>
			<div class="span12">
				<c:if test="${message!=null&&message!=''}">
					<div class="alert alert-success" style="margin-right: 8%; text-align: center;"
						id="successmessage">
						<button class="close" onclick="$('#successmessage').hide();">&times;</button>
						<strong>保存成功！</strong>
					</div>
				</c:if>
				<form id="area" class="form-horizontal" action="${basepath}/area/areaSave" id="areaform"
					method="post">
					<fieldset>
						<legend>地区信息维护</legend>
						<div class="control-group">
							<label class="control-label">地区名称：</label>
							<div class="controls">
								<input class="input-xlarge focused" id="areaid" name="area.id" type="hidden"
									value="${area.id}">
								<input class="input-xlarge focused" id="areaname" name="area.name" type="text"
									placeholder="请输入地区名称！" value="${area.name}">
								*
								<div style="color: red; margin-top: 5px;">${nameMsg}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上级地区：</label>
							<div class="controls">
								<input class="input-xlarge focused" type="text" id="areanames" name="area.name"
									value="${parea.name}" readonly="readonly" />
								<input class="input-xlarge focused" type="hidden" id="areaids" name="area.parentId"
									value="${parea.id}" readonly="readonly" />
								<!-- 弹出层 start -->
								<div class="modal hide fade" id="oModal" tabindex="-1" role="dialog">
									<div class="modal-header">
										<button class="close" type="button" data-dismiss="modal">×</button>
										<h3 id="myModalLabel">地区列表</h3>
									</div>
									<div class="modal-body">
										<div id="otree" class="ztree"></div>
									</div>
									<div class="modal-footer">
										<a href="#" class="btn" id="oclosed">关闭</a>
										<a href="#" class="btn btn-primary" id="savearea">保存</a>
									</div>
								</div>
								<!-- 弹出层 end -->
								<button type="button" id="edithigh" class="btn btn-primary">修改上级地区</button>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">区域编码：</label>
							<div class="controls">
								<input onkeyup="this.value=this.value.replace(/\D/g, '')" class="input-xlarge focused"
									id="areacode" name="area.code" type="text" placeholder="请输入区域编码！" value="${area.code}">
								*
								<div style="color: red; margin-top: 5px;">${codeMsg}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">区域类型：</label>
							<div class="controls">
								<select class="input-xlarge focused" id="areatype" name="area.type">
									<option value="">---请选择区域类型---</option>
									<c:forEach items="${listdict}" var="dict">
										<option value="${dict.dictkey}">${dict.dictvalue}</option>
									</c:forEach>
								</select>
								*
								<div style="color: red; margin-top: 5px;">${typeMsg}</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">备注信息：</label>
							<div class="controls">
								<textarea class="input-xlarge focused" id="area.remarks" name="area.remarks"
									placeholder="请输入备注信息">${area.remarks}</textarea>
							</div>
						</div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>
							<input type="button" value="取消" class="btn"
								onclick="javascript:window.location.href='${basepath}/area/add'">
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
	var areaid = '${area.parentId}';
	var settingarea = {
		check : {
			enable : true, //设置 zTree 的节点上是否显示 checkbox / radio
			chkStyle : "radio", //设置为单选框
			radioType : "all"
		},
		async : {
			enable : true, //设置 zTree 是否开启异步加载模式
			url : "${basepath}/area/getAreaTree", //Ajax 获取数据的 URL 地址。
			autoParam : [ "id", "name" ], //异步加载时需要自动提交父节点属性的参数。
			otherParam : { //Ajax 请求提交的静态参数键值对。
				"otherParam" : "zTreeAsyncTest",
				"areaid" : areaid
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
				$("#savebutton").click(function() {
					var box = "";
					$("input[id^='optionsCheckbox_']").each(function(i) {
						box = box + $(this).val() + "|";
					});
					$("#areaids").val(box);
					$("#areaform").submit();
				});
				var type = '${area.type}';
				if (type != '' || type != null) {
					$("#areatype option[value='" + type + "']").attr(
							"selected", "selected");
				}
				;
				var jqObj = new JQvalidate(); 
				var id=$('#areaid').val();
				var area = "area";
				jqObj.setform(area);
				jqObj.set("area.name", "required", "请输入地区名称!");
				jqObj.set("area.code", "required", "请输入区域编码!");
				jqObj.set("area.type", "required", "请选择区域类型!");
				if(id!=null&&id==0){
					jqObj.set("area.code", "remote", "区域编码重复!");
		 	    }
				
				jqObj.Run();
			})
</script>
<c:import url="/pages/include/pageFoot.jsp" />