<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <title>添加医生--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/style.css" />
    <script type="text/javascript" src="<%=basePath%>Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath%>Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath%>Js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>Js/ckeditor/ckeditor.js"></script>
 

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
		$('#saveid').click(function(){
				document.getElementById("saveAdd").submit();
		 });
		
		$('#backid').click(function(){
			window.location.href="<%=basePath %>addContactPackage/AddContact_queryContact.action";
	 });
		
		
    });
    
    //校验姓名
    function checkname(){
    	var name = document.getElementById("name").value;
    	var name2 = document.getElementById("name2");
    		name2.innerHTML="";
    	if(name==null||name==""){
    		name2.innerHTML="姓名不能为空！！";
    		name2.className="red";
    		return false;
    	}else if(name.length>10){
    		 var str=name.substring(0,10);
    		document.getElementById("name");
    		name2.innerHTML="要小于10个字符，已自动截取前10位";
    		name2.className="red";
    		return false;
    	}else{
    	name2.innerHTML="正确";
    	name2.className="green";
    		return true;
    		
    	}
    }
    
    //校验电话号码
    function checkphone(){
    	var phone = document.getElementById("phone").value;
    	var phone2 = document.getElementById("phone2");
    	var reg= /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    	 phone2.innerHTML="";
    	if(phone==null||phone==""){
    		phone2.innerHTML="电话号码不能为空！！";
    		phone2.className="red";
    		return false;
    	}if(isNaN(phone)){
    		phone2.innerHTML="需要为数字！！";
    		phone2.className="red";
    		return false;
    	}else if(phone.length!=11){
    		phone2.innerHTML="电话号码为11位！！";
    		phone2.className="red";
    		return false;
    	}else if(!reg.test(phone)){
    		phone2.innerHTML="格式不正确，请输入正确格式！";
    		phone2.className="red";
    		return false;
    	}else
    	{
    		phone2.innerHTML="正确";
    		phone2.className="green";
    		return true;
    	}
    }
    function checkemail(){
    	var email = document.getElementById("email").value;
    	var email2 = document.getElementById("email2");
    	email2.innerHTML="";
    	if(email==null||email==""){
    		email2.innerHTML="邮箱不能为空！！";
    		email2.className="red";
    		return false;
    	}
    	if(!/^[\d\w]+@[\d\w]+.[\d\w]+(.[\w\d])?$/g.test(email)){
    		email2.innerHTML="邮箱格式不正确，请输入正确格式！";
    		email2.className="red";
    		return false;
    	}else{
    	email2.innerHTML="正确";
    	email2.className="green";
    	return true;
    	}
    }
    //校验地址
    function checkcaddress(){
    	var  caddress= document.getElementById("caddress").value;
    	var caddress2 = document.getElementById("caddress2");
    		caddress2.innerHTML="";
    	if(caddress==null||caddress==""){
    		caddress2.innerHTML="地址不能为空！！";
    		caddress2.className="red";
    		return false;
    	}else if(caddress.length>40){
    		caddress2.innerHTML="要小于40个字符，已自动截取前10位";
    		caddress2.className="red";
    		return false;
    	}else{
    	caddress2.innerHTML="正确";
    	caddress2.className="green";
    		return true;
    	}
    }
    function check(){
    	if(checkname().valueOf()==true && checkphone().valueOf()==true){
    		alert("添加成功");
    	}else{
    		alert("输入有误，不能提交");
    	}
    }
    
  
    </script>
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
</head>

<body>
<form id="saveAdd" action="<%=basePath %>addContactPackage/AddContact_insertContact.action" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><input type="text" name="myContact.cname" id="name" value="" onblur="checkname()"/><span id="name2" ></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电话号码</td>
        <td><input type="text" name="myContact.cphone" id="phone" value="" onblur="checkphone()"/><span id="phone2" ></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="myContact.csex" value="1" checked/>男&nbsp;&nbsp;&nbsp;<input type="radio" name="myContact.csex" value="2"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><input type="text" name="myContact.cdate" value="" onFocus="WdatePicker()" class="Wdate"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><input type="text" name="myContact.cemail"  id="email" value="" onblur="checkemail()"/><span id="email2" ></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">地址</td>
        <td><input type="text" name="myContact.caddress" id="caddress" value="" onblur="checkcaddress()"/><span id="caddress2" ></span></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				
				<button class="btn btn-primary" type="button" id="saveid" name="saveid" onclick="check();">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>