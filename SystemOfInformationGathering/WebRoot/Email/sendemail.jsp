<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>发邮件</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/style.css" />
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/common.js"></script>

 

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
    
    <script type="text/javascript" src="<%=basePath%>Email/js/giveemail.js"></script>
	 
</head>

 

<body ><br/>
<center><span><font color="red" size="5" >带*号的为必填项</font></span></center>
<hr/>
<form name ="xx" action="<%=basePath%>mypackage/email_sendEmail.action"   class="definewidth m20" enctype="multipart/form-data"  method="post" onsubmit="return submitform();" >
 	 
    <table  id = "tb" class="table table-bordered table-hover definewidth m10" >

       <tr>
            <td width="10%" class="tableleft">收件人
            
            </td>
            <td>
            <c:forEach items="${emp }" var = "emp" >
            
            <input type="checkbox" name="email.receive"  id="receive" value="${emp.empName}" onblur="Receives();" />${emp.empName}&nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
            <span id = "msgReceives">*</span> 
            
            </td>
        </tr>
        <tr>
            <td class="tableleft">标题</td>
            <td><input  name="email.title" id = "title" onblur="Title();"/>
            <span id = "msgTitle">*</span>
            
            </td>
        </tr>
       <tr>
            <td class="tableleft">内容</td>
            <td> <textarea name="email.content" id = "content" onblur="Content();"  class="control-row4 input-large" cols="8" rows="6"></textarea>
            
            <span id = "msgContent">*</span>
            </td> 
       </tr>
        <tr>
            <td class="tableleft">附件</td>
            <td>
            	<input type="file" name="emailFile" onblur="file();" />
            	<button onclick="del(this);" class="btn btn-primary">删除</button>
             <span id = "msgFile"></span>
            </td> 
       </tr>
        
    </table>
    <br/>
  	 <center> <input onclick="add();" type="button" value="增加上传文件" class="btn btn-primary">&nbsp;&nbsp;&nbsp;
     <input type="submit" class="btn btn-primary"   value="发送"/> </center>
</form>
</body>

</html>