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
    <script type="text/javascript" src="/Js/jquery.js"></script>
    <script type="text/javascript" src="/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="/Js/bootstrap.js"></script>
    <script type="text/javascript" src="/Js/ckform.js"></script>
    <script type="text/javascript" src="/Js/common.js"></script> 

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
	
   <script type="text/javascript" src="<%=basePath%>User/addUser.js"></script>
   <script type="text/javascript" src="<%=basePath %>js-ajax/jquery-1.8.2.js"></script>
<script type="text/javascript">
$(function () {       
		$('#backid').click(function(){
			if(confirm("确定放弃？")){
				document.getElementById("employee.empName").value="";
				window.location.href="<%=basePath %>employeePackage/employee_queryEmployee.action?employeekey=1";
		 	}
		});
    });
</script>
	<script type="text/javascript" src="<%=basePath %>/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form id="formId" action="<%=basePath%>employeePackage/employee_insertEmployee.action?employeekey=1" method="post" class="definewidth m20">
<!-- <input type="hidden" name="id" value="{$user.id}" /> -->
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">登录名</td>
            <td><input type="text" id="employee.empName" name="employee.empName" onblur="checkName();" onfocus="clearName();"/>
            	
            	<span id="ename" name="ename" >*必填</span>
            </td>
        	
        </tr>
        <tr>
            <td class="tableleft">密码</td>
            <td><input type="password" id="employee.password" name="employee.password" onfocus="warn();" onblur="checkPassword();" maxlength="20"/>            
            <span id="passwordSpan" name="passwordSpan" >*必填</span>
            </td>
        </tr>
        <tr>
            <td class="tableleft">真实姓名</td>
            <td><input type="text" id="employee.realName" name="employee.realName" onblur="checkRealName();" onfocus="writedown();" />
            	<span id="reName" name="reName">*必填</span>
            </td>
        </tr>
         <tr>
         <tr>
            <td class="tableleft">性别</td>
            <td>
                <input type="radio" name="employee.sex" value="1" checked/> 男
              <input type="radio" name="employee.sex" value="0" /> 女
            </td>
        </tr>
        <tr>
            <td class="tableleft">电话</td>
            <td><input type="text" id="employee.tel" name="employee.tel" onblur="checkTel();" maxlength="11" />
            	<span id="telSpan" name="telSpan"></span>
            </td>
        </tr>
        <tr>
            <td class="tableleft">出生日期</td>
            <td><input type="text" id="employee.birthday" name="employee.birthday"  onFocus="WdatePicker()"" class="Wdate"/>
           <!--  WdatePicker({maxDate:'#F{$dp.$D(\'employee.birthday\')||\'2020-10-01\'}'})" class="Wdate"/>
             -->    		
            </td>       		
        </tr>
        <tr>
            <td class="tableleft">地址</td>
            <td><input type="text" id="employee.address" name="employee.address" onblur="checkAddress();" />
            <span id="addressSpan" name="addressSpan"></span>
            </td>       		
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="text" id="employee.email" name="employee.email" onfocus="emailOnfocus();" onblur="emailOnblur();" />
            <span id="emailSpan" name="emailSpan"></span>
            </td>       		
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="employee.e_state" value="1" checked/> 启用
              <input type="radio" name="employee.e_state" value="0" /> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft" valign="middle">角色</td>
            <td valign="middle">
            	<table>
            		<tr>
            			<c:forEach var="r" items="${listRole }" varStatus="status">
            				<td align="left"  width="90"  >
        					<input type="checkbox" id="strs" name="strs" value="${r.rid }"/>&nbsp;&nbsp;${r.roleName }
        					</td>
        					<input type="hidden" id="employee.strRole" name="employee.strRole" />
        				</c:forEach>
            		</tr>
            	</table>
        		
        	</td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td >
                <button type="button" class="btn btn-primary"  onclick="checkRole();">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid" >返回列表</button>  
                <span id="hide" name="hide"></span>
            </td>
        </tr>
    </table>
</form>

</body>
</html>