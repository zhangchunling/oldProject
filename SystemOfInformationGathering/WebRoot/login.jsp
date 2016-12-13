<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

   	<title>中软高科信息采编系统</title>
	<meta charset="UTF-8">
	<script src="<%=basePath %>Js/jquery.js" type="text/javascript" ></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>Css/register.css"/>
<style type="text/css">
		.green{
			color: green;
			font-size: 15;
		}
		.red{
			color:red;
			font-size: 15;
		}
			
	</style>
<script type="text/javascript">

/* $(function(){ */

   /*  $('.rem').click(function(){
        $(this).toggleClass('selected');
    }); */

  /*   $('#signup_select').click(function(){
        $('.form_row ul').show();
        event.cancelBubble = true;
    });

    $('#d').click(function(){
        $('.form_row ul').toggle();
        event.cancelBubble = true;
    });

    $('body').click(function(){
        $('.form_row ul').hide();
    });

    $('.form_row li').click(function(){
        var v  = $(this).text();
        $('#signup_select').val(v);
        $('.form_row ul').hide();
    });

}); */
	//登录提交
	function login(){
		var userName=document.getElementById("employee.empName").value;//用户名
		var password=document.getElementById("employee.password").value;
		var subSpan=document.getElementById("submitSpan");
		 if(password.length>10){
			str=userName.substring(0,10);
			document.getElementById("employee.password").value=str;
		}
		
		if(userName.length==0){
			subSpan.innerHTML="亲，请输入用户名再登录";
			subSpan.className="red";
			return false;
		}else if(password.length==0){
			subSpan.innerHTML="亲，请输入密码再登录";
			subSpan.className="red";
			return false;
		}else{
			document.getElementById("formId").submit();
		}
		
	} 
	 //验证码改变
	function changeImage(){
	//验证码请求的servlet地址 后面传随机生成的a的参数目的：解决浏览的缓存
  	var url="<%=basePath%>getKey?a="+Math.random();
   	document.getElementById("imgkey").src=url;//给img标签的src属性赋一个新请求地址实现刷新验证码
  }
   
</script>

  </head>
  
  <body>
    <div class='signup_container'>

    <h1 class='signup_title'>用户登陆</h1>
    <img src='<%=basePath %>User/1.png' id='admin'/></br>
     <span id="submitSpan" name="submitSpan" Style="color:red" >${subSpan }</span>
    <div id="signup_forms" class="signup_forms clearfix">
   
            <form id="formId"  action="<%=basePath %>employeePackage/login_login.action" method="post"  class="signup_form_form">
                    
                    <div class="form_row first_row">
                        <label for="signup_email">请输入用户名</label><div class='tip ok'></div>
                        <input type="text"  name="employee.empName" placeholder="请输入用户名" id="employee.empName" onblur="checkName();" data-required="required">
                    </div>
                    <div class="form_row">
                        <label for="signup_password">请输入密码</label><div class='tip error'></div>
                        <input maxlength="10" type="password" name="employee.password" placeholder="请输入密码" id="employee.password" data-required="required">
                    </div>
                     <div class="form_row"> 
                     <!-- 验证码是个servlet类 -->
                      <label for="signup_email">请输入验证码</label><div class='tip ok'></div>
                        <input type="text" id="getKey" name="getKey" placeholder="请输入验证码"  data-required="required">
						点击图片更换	<a href="javascript:onclick=changeImage()">	<img alt="点击刷新验证码" src="<%=basePath%>getKey" id="imgkey"/></a> &nbsp;&nbsp;		
						<!-- 图片里的src就是引用图片，所以一点登录页面就会出现第一张验证码 -->								
                    </div>
           </form>
    </div>

    <div class="login-btn-set">
    <div >
    	<table>
    		<tr><td><input type="checkbox" id="remember" name="remenber" value="1"  >记住我</td>
    		<td><a href="javascript:onclick=login();" > <img src='<%=basePath %>User/2.png' id='admin'/></a></td></tr>
    	</table>
    
    </div>
     </div>
    <p class='copyright'>版权所有 @张春玲</p>
</div>
  </body>
</html>
