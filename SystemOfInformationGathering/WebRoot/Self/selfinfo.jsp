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
    <script type="text/javascript" src="<%=basePath %>Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath %>Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath %>Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath %>Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath %>Js/common.js"></script>

 

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
    <script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="<%=basePath%>index.jsp";
		 });
    });
</script>
</head>
<body>
<form action="index.html" method="post" class="definewidth m20">
<input type="hidden" name="id" value="{$user.id}" />
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">角色</td>
            <td>${employee.strRole }</td>
        </tr>
        <tr>
            <td class="tableleft">姓名</td>
            <td>${employee.empName }</td>
        </tr>
        <tr>
            <td class="tableleft">性别</td>
            <td><c:if test="${employee.sex==0 }">女</c:if>
            <c:if test="${employee.sex==1 }">男</c:if>
            </td>
        </tr>
        <tr>
            <td class="tableleft">生日</td>
            <td>${employee.birthday }</td>
        </tr>
        <tr>
            <td class="tableleft">联系电话</td>
            <td>${employee.tel }</td>
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td>${employee.email }</td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            
            <td>
           		 <c:if test="${employee.e_state==1 }">正常</c:if>
            	<c:if test="${employee.e_state==0 }">禁用</c:if>
        	</td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
               <button type="button" class="btn btn-success" name="backid" id="backid">返回</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>