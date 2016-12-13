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
<script type="text/javascript" src="<%=basePath %>Resource/editMenu.js"></script>
 

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
    <script type="text/javascript">
    	function writeBack() {
    		//回写状态
    		var sta=document.getElementById("state").value;
    		var stas=document.getElementsByName("menu.m_state");
    		for(var i=0;i<stas.length;i++){
    			if(stas[i].value==sta){
    				stas[i].checked=true;
    			}
    		}
    		//回写父菜单t
			var hiddenPid=document.getElementById("pid");
			var select=document.getElementById("menu.parentId");
			var optionsArray=select.options;
			
			for(var i=0;i<optionsArray.length;i++){
				if(optionsArray[i].value==hiddenPid.value){
					optionsArray[i].selected=true;
					break;
				}
			}
		}
    </script>
</head>
<body onload="writeBack();">
<form id="formId" action="<%=basePath %>employeePackage/menu_updateMenu.action?key=1" method="post" class="definewidth m20">
	 <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">父菜单</td>
            <td><select id="menu.parentId" name="menu.parentId">
            		<c:forEach var="me" items="${listMenu }">
            			<c:if test="${menu.mid!=me.mid }"><option value="${me.mid }">${me.menuName }</option></c:if>
            			<!-- **父菜单不能有自己** -->
            		</c:forEach>        		
       			 </select>
       			 <input type="hidden" id="pid" name="pid" value="${menu.parentId }" />
       		</td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">资源名称</td>
            <td><input type="text"  id="menu.menuName" name="menu.menuName" value="${menu.menuName }" onblur="checkMenuName();" />
            <span id="menuNameSpan">*必填</span> </td>
        </tr>
        <tr>
            <td class="tableleft">url</td>
            <td><input type="text" id="menu.url" name="menu.url" value="${menu.url }" onblur="checkMenuUrl();"/>
            <span id="menuUrlSpan">*必填</span></td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="menu.m_state" value="1" checked/> 启用
              <input type="radio" name="menu.m_state" value="0" /> 禁用
              <input type="hidden" id="state" name="state" value="${menu.m_state }" />
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" onclick="checkSubmit();" >更新</button>&nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>   
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
			if(confirm("放弃?")){
				window.location.href="<%=basePath %>employeePackage/menu_queryMenus.action?key=1";
			}else{
				return false;
			}
				
		 });
    });
</script>