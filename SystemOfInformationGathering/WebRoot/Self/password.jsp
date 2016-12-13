<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>Css/style.css" />
   <%--  <script type="text/javascript" src="<%=basePath %>Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath %>Js/jquery.sorted.js"></script> --%>
    <script type="text/javascript" src="<%=basePath %>Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath %>Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath %>Js/common.js"></script>
    
    <script type="text/javascript" src="<%=basePath %>js-ajax/jquery-1.8.2.js"></script>
	<style type="text/css">
		.green{
			color: green;
			font-size: 15;
		}
		.red{
			color:red;
		}
		.yellow{
			color:yellow;
		}	
	</style>
<script type="text/javascript">
function checkPassword() {
	//校验角色名称
   		var roleSpan=document.getElementById("roleSpan");
		var rn=document.getElementById("oldPassword").value;
		if(""==rn.trim()){
			roleSpan.innerHTML="密码不能为空";
			roleSpan.className="red";		
		}else{					
   			//异步判断是否存在			
   		   //	var today = new Date();   		 
   			 $.ajax({   //后面跟一个时间的参数是为了防止有时候不提交的问题【冲破缓存，也可跟个随机数】	
   				 url:"addContactPackage/self_checkPasswordAjax.action",//访问路径
   				 type:"post",//提交方式
   				 data:"oldPassword="+rn,//username是action类里的属性
   				 success:function(data){//回调函数：返回的是json串,out.print("")里的字符串
   					 if("1"==data){   						 
   						roleSpan.innerHTML="正确";
   						roleSpan.className="green";  
   					 }else{
   			   			roleSpan.innerHTML="输入有误";
						roleSpan.className="red";						
   						document.getElementById("oldPassword").value="";
   					 }
   				   	     
   				  }
   			 });
   		}  		
   		
}

function confirmPassword() {
	var rn=document.getElementById("oldPassword").value;
	var newPassword=document.getElementById("employee.password").value;
	var confirmnewPassword=document.getElementById("confirmP").value;
	var sp=document.getElementById("conPass");
	if(newPassword!=confirmnewPassword){
		sp.innerHTML="两次输入的密码不一致";
		sp.className="red";
	}else{
	sp.innerHTML="OK";
		sp.className="green";
	}
}
function checkSub() {
	var rn=document.getElementById("oldPassword").value;
	var sp=document.getElementById("conPass");
	if(rn.length==0){
		var roleSpan=document.getElementById("roleSpan");
		roleSpan.innerHTML="请输入原密码";
		roleSpan.className="red";
		return false;
	}

	var newPassword=document.getElementById("employee.password").value;
	var confirmnewPassword=document.getElementById("confirmP").value;
	
	if(newPassword!=confirmnewPassword){
		sp.innerHTML="两次输入的密码不一致";
		sp.className="red";
		return false;
	}else{
		document.getElementById("formId").submit();
		alert("更改成功");
	}
}
</script>
</head>
<body>
<form id="formId" class="form-inline definewidth m20" action="<%=basePath %>addContactPackage/self_updatePassword.action" method="post">    
   <table>
		<tr>
			<td align="right">原密码:</td><td><input maxlength="20" type="password" id="oldPassword" name="oldPassword" onblur="checkPassword();"/>
			<span id="roleSpan"></span></td>
		</tr>
		<tr>
			<td align="right">新密码:</td><td><input maxlength="20" id="employee.password" name="employee.password" type="password" /></td>
		</tr>
		<tr>
			<td align="right">确认密码:</td><td><input maxlength="20" id="confirmP" name="confirmP" type="password" onblur="confirmPassword();"/>
			<span id="conPass"></span></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><br/>
				<input type="button" value="保存" onclick="checkSub();" class="btn btn-primary"/>
			</td>
		</tr>
   </table>
</form>
</body>
</html>