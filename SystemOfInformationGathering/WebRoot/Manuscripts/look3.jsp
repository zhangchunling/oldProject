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
    <title>稿件详情</title>
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
        <td width="10%" class="tableleft">写稿人</td>
        <td>${pageFile.pf_author }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">标题</td>
        <td>${pageFile.pf_name}</td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">类别</td>
        <td>${pageFile.pf_type }</td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">文体</td>
        <td>${pageFile.pf_style }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">稿件内容</td>
        <td> <textarea style="width: 600px; height: 300px;" readonly="readonly" name="" id="" class="control-row4 input-large">
		${pageFile.content }
        </textarea></td>
    </tr>
   <tr>
        <td width="10%" class="tableleft">发稿日期</td>
        <td>${pageFile.pf_createtime }</td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
			<c:if test="${pageFile.pf_state!=9||pageFile.pf_state==4 }">
				<button type="button" class="btn btn-success"  onclick="javascript:window.location.href='<%=basePath%>Manu_sub2Ok.action?pageFile.pf_mu_id=${pageFile.pf_mu_id }'">录用</button>
                <button type="button" class="btn btn-success" onclick="javascript:window.location.href='<%=basePath%>Manu_sub2No.action?pageFile.pf_mu_id=${pageFile.pf_mu_id }'">不录用</button>
            </c:if>   
                <button type="button" class="btn btn-success" onclick="javascript:window.history.go(-1)">返回</button>
			
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>