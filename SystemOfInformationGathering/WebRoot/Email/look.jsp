<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>查看--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/style.css" />
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ckeditor/ckeditor.js"></script>
 

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
    <script type="text/javascript">
     function backMenu(){
     
     window.location.href="mypackage/email_giveemail.action";
     }
    </script>
</head>
<body>
<form action="index.html" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">标题</td>
        <td>${looks.title }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收件人/发件人</td>
        <td>${looks.receive }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">发送时间</td>
        <td>${looks.sendTime }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">内容</td>
        <td><textarea name="" readonly="readonly" id="" class="control-row4 input-large" cols="8" rows="6">
            
           ${looks.content }
        </textarea></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">附件</td>
        <td>${looks.accessory }</td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
			<!-- 	<a href="email_geveemail" >返回</a> -->
		<button type="button" class="btn btn-success" name="backid" id="backid" onclick="backMenu();">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>