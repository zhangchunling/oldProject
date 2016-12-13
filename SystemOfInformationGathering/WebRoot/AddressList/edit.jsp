<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <title>修改医生--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/ckeditor/ckeditor.js"></script>
 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript" src="<%=basePath %>/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.location.href="<%=basePath %>addContactPackage/AddContact_queryContact.action";
		 });
    });
    function checkname(){
    	var name = document.getElementById("name").value;
    	var name2 =document.getElementById("name2");
    		name2.innerHTML="";
    	if(name==null||name==""){
    		name2.innerHTML="姓名不能为空！！";
    		return false;
    	}else{
    		return true;
    	}
    }
    function checkphone(){
    	var phone = document.getElementById("phone").value;
    	var phone2 = document.getElementById("phone2");
    	phone2.innerHTML="";
    	if(phone==null||phone==""){
    		phone2.innerHTML="手机号不能为空！！";
    		return false;
    	}else if(isNaN(phone)){
    		phone2.innerHTML="手机号必须为数字！！";
    		return false;
    	}else if(phone.length!=11){
    		phone2.innerHTML="电话号码必须为11位！！";
    		return false;
    	}else{
    		return true;
    	}
    }
    function checkemail(){
    	var email = document.getElementById("email").value;
    	var email2 = document.getElementById("email2");
    	email2.innerHTML="";
    	if(!/^[\d\w]+@[\d\w]+.[\d\w]+(.[\w\d])?$/g.test(email)){
    		email2.innerHTML="邮箱格式不正确，请输入正确格式！"
    	}
    }
    function checkaddress(){
    	var address = document.getElementById("address").value;
    	var address2 =document.getElementById("address2");
    	address2.innerHTML="";
    	if(address==null||address==""){
    		address2.innerHTML="地址不能为空！！";
    		return false;
    	}else{
    		return true;
    	}
    	
    }
    function check(){
    	if(checkname().valueOf()==true && checkphone().valueOf()==true&&checkaddress().valueOf()){
    		alert("添加成功");
    	}else{
    		alert("输入有误，不能提交");
    	}
    }
    
    </script>
</head>
<body>

<form  action="<%=basePath %>addContactPackage/AddContact_updateContact.action" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
   <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="myContact.cname"  id="name" value="${myContact.cname }" onblur="checkname()"/><span id="name2" style="color: red;"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电话号码</td>
        <td><input type="text" name="myContact.cphone" id="phone" value="${myContact.cphone }" onblur="checkphone()"/><span id="phone2" style="color: red;"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="myContact.csex" value="1" checked/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="myContact.csex" value="2"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input type="text" name="myContact.cdate" onFocus="WdatePicker()" value="${myContact.cdate }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input type="text" name="myContact.cemail" id="email" value="${myContact.cemail }" onblur="checkemail()"/><span id="email2" style="color: red;"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">地址</td>
        <td><input type="text" name="myContact.caddress"  id="address" value="${myContact.caddress }" onblur="checkaddress()"/><span id="address2" style="color: red;"></span></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" type="button" onclick="check();">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>