

/**
 *  验证类初始化
 */
function JQvalidate() {
	this.ValidateObjectList = new Array(); //  接收参数的list
	this.elementList = new Array();  // 验证标签list
	this.validateTypeList = new Array();// 验证类型list
	this.errorTypeList = new Array();  //   返回错误信息list
	this.setform = SetValidateForm;  // form 标签获取
	this.set = SetValidateObject;   //  获取参数
	this.Run = RunValidate;     //   执行jquery.validate
	this.itmun =0;        //标签计数
	this.success ="success";
	this.rules ="rules";
	this.messages = "messages";
	this.label ="label";
}
/**
 *  设置接收formid
 */
function SetValidateForm(formName) {
	this.FormName = formName;
}
/**
 *  接收传入参数  objName 验证标签名称 ， validateType 验证类型名称，errorInfo 返回错误信息
 */

function ValidateObject(objName, validateType,  errorInfo) {
	this.Obj = objName;
	this.Type = validateType;
	this.Error = errorInfo;
}
/**
 *  装载传入参数成list
 */
function SetValidateObject(objName, validateType,  errorInfo) {	
	this.ValidateObjectList[this.itmun] = new ValidateObject(
			objName, validateType,  errorInfo);
	this.itmun=this.itmun+1;
}
/**
 *  元素的rules验证要求串拼装 中间调用  Required(element,ValidateObjectList ,validateTypeList)
 */
function Rules(ValidateObjectList) {
	var elementList = new Array();
	var rules ="";           //rules内容串	
	var i =0;
	var ob="";
	for ( var n = 0; n < ValidateObjectList.length; n++) {
		elementList[i]=ValidateObjectList[n].Obj;			
		if(n<ValidateObjectList.length-1){
			ob=ValidateObjectList[n+1].Obj;
			if(elementList[i]==ob){			
				n=n+1;			
			}
		}
		i=i+1;		
	}
	for ( var j = 0; j < elementList.length; j++) {						
			rules= rules+ "'"+elementList[j]+"' :"+ RulesRequired(elementList[j],ValidateObjectList);					
			if(elementList.length>1&j<elementList.length){
				rules=rules+",";
			}				
	}
	rules= "{"+rules+"}"; 
	return rules;
}
/**
 * 元素的required验证要求串拼装
 * @param ValidateObjectList 参数list  element 元素标签 validateTypeList 验证类型
 * @returns
 */
function RulesRequired(element,ValidateObjectList) {
	var validateTypeList = new Array();

	var required="";         //required内容串
	var i =0;
	for ( var j = 0; j < ValidateObjectList.length; j++) {		
		if(element==ValidateObjectList[j].Obj){		
			validateTypeList[i]=ValidateObjectList[j].Type;				
			i=i+1;			
		}					
	}	 
	 
   for ( var m = 0; m < validateTypeList.length; m++) {	
	   
	   if(validateTypeList[m]=="isMobile"){
		   required= required+" \""+element+"\": \""+ element+"\"";
	   }else if(validateTypeList[m]=="remote"&element.indexOf("area")==0){
		   required= required+ "remote:"+ areacodesame(element);
	   }else if(validateTypeList[m]=="remote"&element.indexOf("office")==0){
		   required= required+ "remote:"+ officecodesame(element);
		
	   }
	   else if(validateTypeList[m]=="remote"&element.indexOf("dict.dictkey")==0){		
		   required= required+ "remote:"+ dictkeysamesame(element);
	   }
	   else if(validateTypeList[m]=="remote"&element.indexOf("user")==0){
		   required= required+ "remote:"+loginNamesame(element);
	   }
	   else if(validateTypeList[m]=="remote"&element.indexOf("dict.type")==0){		
		   required= required+ "remote:"+ dictkeysamesame(element.replace("type","dictkey"));
	   }
	   else if(validateTypeList[m]=="remote"&element.indexOf("role")==0){
		   required= required+ "remote:"+rolename(element);
	   }
	   else{
		   required= required+  validateTypeList[m]+":"+ Required(validateTypeList[m]);	
	   }				
		if(validateTypeList.length>1&m<validateTypeList.length){
			required=required+",";
		}		
	}
   required="{"+required+"}";
	return required;
}

/**
 * 判断验证方法类型
 */
function Required(type){
	switch (type) {
	case "required": {
		return "true";
		break;
	}
	case "number": {
		return "true";
		break;
	}
	case "email": {
		return "true";
		break;
	}
	case "url": {
		return "true";
		break;
	}
	case "digits": {
		return "true";
		break;
	}	
	default: {
		return type;
		break;
	}
}
}
/**
 * 区域编码重复验证ajax
 */
function areacodesame(element){
	var u="{ url: \"areacheck\","; //action地址
	var t="type: \"post\",";  // 提交方式
	var dT="dataType: \"json\",";  // 数据格式
	var d="data: {areacode: function () {  var id =document.getElementsByName('"+element+"')[0].id;   return $('#'+id).val(); } },";  // 接收参数
	var dF="dataFilter:  function (data) { if (data == \"0\") {  return true;   }    else {   return false; } }   }";  //返回值处理	
	var area=u+t+dT+d+dF;
	   return area;
	}
/**
 * 机构编码重复验证ajax
 */
function officecodesame(element){
	var u="{ url: \"checkoffice\","; //action地址
	var t="type: \"post\",";  // 提交方式
	var dT="dataType: \"json\",";  // 数据格式
	var d="data: {officecode: function () { var id =document.getElementsByName('"+element+"')[0].id;   return $('#'+id).val(); } },";  // 接收参数
	var dF="dataFilter:  function (data) { if (data == \"0\") {  return true;   }    else {   return false; } }   }";  //返回值处理	
	var office=u+t+dT+d+dF;
	   return office;
	}
/**
 * 数据字典key重复验证ajax
 */
function dictkeysamesame(element){
	var u="{ url: \"dictkeycheck\","; //action地址
	var t="type: \"post\",";  // 提交方式
	var dT="dataType: \"json\",";  // 数据格式
	var d="data: {dictkey: function () {var id =document.getElementsByName('"+element+"')[0].id;  return $('#'+id).val(); },type: function () { var tpyeid =document.getElementsByName('"+element.replace("dictkey","type")+"')[0].id; return $('#'+tpyeid).val(); } },";  // 接收参数
	var dF="dataFilter:  function (data) { if (data == \"0\") {  return true;   }    else {   return false; } }   }";  //返回值处理	
	var dictkey=u+t+dT+d+dF;
	   return dictkey;
	}
/**
 * 登录名重复验证ajax
 */
function loginNamesame(element){
	var u="{ url: \"checkloginname\","; //action地址
	var t="type: \"post\",";  // 提交方式
	var dT="dataType: \"json\",";  // 数据格式
	var d="data: {loginName: function () {  var id =document.getElementsByName('"+element+"')[0].id;   return $('#'+id).val(); } },";  // 接收参数
	var dF="dataFilter:  function (data) { if (data == \"0\") {  return true;   }    else {   return false; } }   }";  //返回值处理	
	var loginName=u+t+dT+d+dF;
	   return loginName;
	}
/**
 * 角色名称重复验证ajax
 */
function rolename(element){
	var u="{ url: \"checkrolename\","; //action地址
	var t="type: \"post\",";  // 提交方式
	var dT="dataType: \"json\",";  // 数据格式
	var d="data: {rolename: function () {  var id =document.getElementsByName('"+element+"')[0].id;   return $('#'+id).val(); } },";  // 接收参数
	var dF="dataFilter:  function (data) { if (data == \"0\") {  return true;   }    else {   return false; } }   }";  //返回值处理	
	var rolename=u+t+dT+d+dF;
	   return rolename;
}
	

/**
 * 错误消息Messages拼装 调用MessagesRequired(element,ValidateObjectList ,errorTypeList)
 */
function Messages(ValidateObjectList) {
	var elementList = new Array();
	var messages ="";           //rules内容串	
	var i =0;	
	var ob="";
	for ( var n = 0; n < ValidateObjectList.length; n++) {
		elementList[i]=ValidateObjectList[n].Obj;	
		if(n<ValidateObjectList.length-1){
			ob=ValidateObjectList[n+1].Obj;
			if(elementList[i]==ob){			
				n=n+1;
			}
		}		
		i=i+1;
	}
	for(var k = 0; k<elementList.length; k++){		
		for ( var j = 0; j < ValidateObjectList.length; j++) {			
			if(elementList[k]==ValidateObjectList[j].Obj){
				messages= messages+"'"+elementList[k]+"' :{"+ MessagesRequired(elementList[k],ValidateObjectList) +"}";
				if(elementList.length>1&k<elementList.length){
					messages=messages+",";
				}
			}		
		}		
	}	
	 messages="{"+messages+"}";
	return messages;
}
/**
 * 错误消息Messages 内部的具体返回消息拼装
 * element 判断的元素标签  ValidateObjectList 参数list  validateTypeList 验证类型list 返回错误信息list
 */
function MessagesRequired(element,ValidateObjectList) {	
	var validateTypeList = new Array();
	var errorTypeList = new Array();	
	var messagesrequired="";         //required内容串
	var i =0;
	for ( var j = 0; j < ValidateObjectList.length; j++) {				
		if(element==ValidateObjectList[j].Obj){
			validateTypeList[i]=ValidateObjectList[j].Type;
			errorTypeList[i]=ValidateObjectList[j].Error;
			i=i+1;
		}					
	}		 
   for ( var m = 0,n=0; m < validateTypeList.length,n<errorTypeList.length; m++,n++) {		
	 
		   messagesrequired= messagesrequired+ validateTypeList[m]+":"+ Rfunc(element,errorTypeList[n]);
	   
	   
			if(validateTypeList.length>1&errorTypeList.length>1&m<validateTypeList.length&n<errorTypeList.length){
				messagesrequired=messagesrequired+",";
			}		
	}
   
	return messagesrequired;
}
/**
 * 验证错误时的标签样式
 */
function Rfunc(element,errorType){
	
  var f=	"function(label){ var id =document.getElementsByName('"+element+"')[0].id;  $('#'+id).parent().parent().removeClass('control-group success'); 	$('#'+id).parent().parent().addClass('control-group error');   return \"<label class='help-inline' >"+errorType+"</label>\"; }";

   return f;
}
/**
 * 验证通过的的标签样式
 */
function lfunc(){
	
	  var l=	"function(label){";    
	  var  a= 	"label.parent().parent().removeClass('control-group error');"; 
	  var  b =  "label.parent().parent().addClass('control-group success');";     	
	  var  e =	"}";
	  var v=l+a+b+e;
	   return eval('('+v+')');
	}
/**
 * 自定义验证出现错误时的标签样式-手机号
 */
function Selfunc(element){
	
  var s=	"function(label){ var id =document.getElementsByName('"+element+"')[0].id;  $('#'+id).parent().parent().removeClass('control-group success'); 	$('#'+id).parent().parent().addClass('control-group error');   return \"<label class='help-inline' >请输入有效的手机号码</label>\"; }";

   return s;
}


/**
 *  参数拼装jq  Validate的验证方法
 */
function RunValidate() {
	
	
	var success = this.success;	
	var rules = this.rules;
	var messages =this.messages;
	var FormName = this.FormName;
	var label= lfunc();
	var labels=eval('(' + label + ')');  //验证通过的的标签样式
	var ruless=Rules(this.ValidateObjectList) ;
	var messagess= Messages(this.ValidateObjectList) ;	
	var rulesss= eval('('+ruless+')');  //验证规则
	var messagesss= eval('('+messagess+')') ; //错误信息返回

	 $("#"+FormName).validate({       	
		 success:labels,    			
    	rules:rulesss ,
    	messages:messagesss  
        });
	   /**
		 * 添加自定义的方法
		 */
	 $.validator.addMethod("user.mobile", function(value, element) { //手机正则表达式
		 var mobilenumber = /^(((1))+\d{10})$/; //以1开头
			return  (mobilenumber.test(value));
			}, eval('('+Selfunc("user.mobile")+')'));
}


  









