<%@page import="com.zrgk.manu.bean.PageFile"%>
<%@page import="com.zrgk.manu.service.impl.PageFileServiceImpl"%>
<%@page import="com.zrgk.manu.service.PageFileServiceInter"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>查看约稿信息</title>
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
    <script type="text/javascript">
    $(function () { 
    //返回按钮      
		$('#backid').click(function(){
				history.go(-1);
		 });
    });
    </script>
</head>
<body>
<form action="index.html" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">刊物名</td>
        <td>${mau.mname }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">刊物期号</td>
        <td>${mau.mbknum }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">约稿详细要求</td>
        <td> <textarea readonly="readonly" name="" id="" style="width: 600px; height: 300px;"">
        ${mau.minfo }
        </textarea></td>
    </tr>

	<tr>
        <td width="10%" class="tableleft">约稿发布时间</td>
        <td>${mau.mstartTime }</td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">约稿截止时间</td>
        <td>${mau.mendTime }</td>
    </tr>
        <tr>
        <td width="10%" class="tableleft">约稿发布人</td>
        <td>${mau.mmaster }</td>
    </tr> 
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>