<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/pages/include/pageNavigation.jsp" />
<%@page import="com.wide.constant.EnumDictType"%>
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<ul class="breadcrumb">
					<i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
					<i class="icon-chevron-right show-sidebar" style="display: none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
					<li><a href="#">系统管理</a> <span class="divider">/</span></li>
					<li class="active">数据字典维护</li>
				</ul>
			</div>
		</div>
	<div class="block-content collapse in">
	<ul class="nav nav-tabs">
		<li ><a href="${basepath}/dict/add">数据字典列表</a></li>
		<li class="active"><a href="${basepath}/dict/adddictinfo">添加数据字典</a></li>
	</ul>	
			<div class="span12">	  
				<c:if test="${message!=null&&message!=''}">
						<div class="alert alert-success" style="margin-right: 8%;text-align: center;">
										<button class="close" data-dismiss="alert">&times;</button>
										<strong>保存成功！</strong>
						</div>
				</c:if>
				<form  id="dict" class="form-horizontal" action="${basepath}/dict/save" method="post">			
					  <fieldset>
						<legend>数据字典信息维护</legend>	
					
					<!-- 	<div class="control-group">
							<label class="control-label" >所属字典：</label>
							
						     <div class="controls">
								<input class="input-xlarge focused" disabled="disabled" id="parentNAME" name="parentNAME"
									type="text"  value="${parentNAME}" >
							</div> 
				       
							
						</div> -->
						
						<div class="control-group">
							<label class="control-label" for="dictdictvalue">字典名称：</label>
							<input id="did" name="dict.id" type="hidden" value="${dict.id}" >										  
							<div class="controls">							
						      <input class="input-xlarge focused" id="dictdictvalue" name="dict.dictvalue"
									type="text" placeholder="请输入数据字典名称！" value="${dict.dictvalue}" >
									<div style="color: red; margin-top: 5px;">${dictnameMsg}</div>				        
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" >键值：</label>														
						      <div class="controls">
								<input class="input-xlarge focused" id="dictdictkey" name="dict.dictkey" id = 'cid'
									type="text" placeholder="请输入数据字典键值！" value="${dict.dictkey}" > 
									<div style="color: red; margin-top: 5px;">${dictkeyMsg}</div>	   
							</div>				       							
						</div>	
						<div class="control-group">
							<label class="control-label" >字典类型：</label>												    
						  <div class="controls">  <select
								class="span3 m-wrap" id="dicttype" name="dict.type" onchange="gradeChange(this.id)" placeholder="请选择数据字典类型！" >
								<option
									
									value=''>请选择</option>
								<c:forEach var="typelist" items="<%=EnumDictType.values()%>">
								<option
									<c:if test="${dict.type==typelist.enumKey}">selected</c:if>
									value='${typelist.enumKey}'>${typelist.enumText}</option>
							</c:forEach>
							</select> 
							      <input type="hidden" id="hidTypeInfo" name="dict.typeinfo"
						value="${dict.typeinfo}">	
						   <input type="hidden" id="hidType" name="dict.type"
						value="${dict.type}"> <div style="color: red; margin-top: 5px;"> ${dicttypeMsg}</div>	  
							</div>						
						</div>						
						<div class="control-group">
							<label class="control-label" >关联数据：</label>														
						      <div class="controls">
								<input class="input-xlarge focused" id="dict.description" name="dict.description"
									type="text"  value="${dict.description}" >
							</div>				       							
						</div>
						<div class="control-group">
							<label class="control-label" >备注：</label>														
						      <div class="controls">
								<input class="input-xlarge focused" id="dict.remarks" name="dict.remarks"
									type="text"  value="${dict.remarks}" >
							</div>				       							
						</div>				
						     <div class="form-actions">
							<button type="submit" class="btn btn-primary">保存</button>
							<input type="button" value="返回"   class="btn"  onclick="javascript:window.location.href='${basepath}/dict/add'" >
						</div>				      								
						</fieldset>	
				
				</form>

			</div>
		</div>
	</div>
<script type="text/JavaScript">
        function gradeChange(s){       
         var typeval = $("#"+s).val();      
         var typename = $("#"+s).find("option:selected").text(); 
         $("#hidTypeInfo").val(typename);
         $("#hidType").val(typeval);      
        }
        $().ready(function() {
         var jqObj = new JQvalidate();
         var dict ="dict"; 
         var id = $('#did').val();
        	jqObj.setform(dict);
        	jqObj.set("dict.dictvalue", "required",  "请输入数据字典名称!");
        	jqObj.set("dict.dictkey", "required",  "请输入数据字典键值!"); 
        	jqObj.set("dict.type", "required",  "请选择数据字典类型!");
        	jqObj.Run();
        
        });
 </script>
 <c:import url="/pages/include/pageFoot.jsp" />