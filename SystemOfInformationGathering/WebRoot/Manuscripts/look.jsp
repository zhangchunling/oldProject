<%@page import="com.zrgk.manu.bean.PageFile"%>
<%@page import="com.zrgk.manu.service.impl.PageFileServiceImpl"%>
<%@page import="com.zrgk.manu.service.PageFileServiceInter"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>约稿详情</title>
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
		$('#backid').click(function(){
				window.location.href="index.html";
		 });
    });
    </script>
</head>
<body>
<form action="index.html" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">刊物名</td>
        <td>${mu.mname }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">刊物期号</td>
        <td>${mu.mbknum }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">约稿详细要求</td>
        <td> <textarea style="width: 600px; height: 300px;" readonly="readonly" name="" id="" class="control-row4 input-large">
        ${mu.minfo }
        </textarea></td>
    </tr>
  <%--  <tr>
        <td width="10%" class="tableleft">约稿状态</td>
        <td>${mu.mstate==0?"未发布":"已发布" }</td>
    </tr> --%>
	<tr>
        <td width="10%" class="tableleft">约稿发布时间</td>
        <td>${mu.mstartTime }</td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">约稿截止时间</td>
        <td>${mu.mendTime }</td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" onclick="javascript:window.history.go(-1)">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>