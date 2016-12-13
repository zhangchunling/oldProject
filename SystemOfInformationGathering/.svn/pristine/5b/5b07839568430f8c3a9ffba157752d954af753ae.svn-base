<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>Resource/add.js"></script>
 

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
	<script type="text/javascript" src="<%=basePath %>js-ajax/jquery-1.8.2.js"></script>
 <script>
    $(function () {       
		$('#backid').click(function(){
			if(confirm("确定放弃？")){
				document.getElementById("menu.menuName").value="";
				window.location.href="<%=basePath %>employeePackage/menu_queryMenus.action?key=1";
		 	}
		});
    });
 </script>
</head>
<body>
<form id="formId" action="<%=basePath %>employeePackage/menu_insertMenu.action" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">父菜单</td>
            <td><select name="menu.parentId">
            		<option value="0">--请选择--</option>
            		<c:forEach var="menu" items="${listMenu }">
            			<option value="${menu.mid }">${menu.menuName }</option>
            			
            		</c:forEach>        		
       			 </select>
       			 <input type="hidden" id="menu.parentMenu" name="menu.parentMenu" value="${menu.menuName }"/>
       		</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">资源名称</td>
            <td><input type="text" name="menu.menuName" id="menu.menuName" onblur="checkMenuName();"/>
            <span id="menuNameSpan">*必填</span> </td>
        </tr>
         <tr>
            <td class="tableleft">资源编号</td>
            <td><input type="text" id="menu.menuNum" name="menu.menuNum" onblur="checkMenuNum();" />
            <span id="menuNumSpan"></span></td>
        </tr>
        <tr>
            <td class="tableleft">url</td>
            <td><input type="text" id="menu.url" name="menu.url" onblur="checkMenuUrl();" />
            <span id="menuUrlSpan">*必填</span> </td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="menu.m_state" value="1" checked/> 启用
              <input type="radio" name="menu.m_state" value="0" /> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" onclick="checkSubmit();" >保存</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>