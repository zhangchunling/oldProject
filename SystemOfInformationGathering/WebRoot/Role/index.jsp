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
			font-size: 20;
		}	
	</style>
    
     <script type="text/javascript">
     $(function () {
		$('#newNav').click(function(){
				window.location.href="<%=basePath%>employeePackage/menu_queryAllMenu.action";
		 });
    });
    
	//点右边的删除时的跳转
	function deleteOne(id) {
		if(confirm("确定删除?")){
			alert("删除成功");
			window.location.href= "<%=basePath %>employeePackage/role_deleteById.action?RoleId="+id;
		}	
    }
	
		//全选
    function checkall(){
		var alls=document.getElementsByName("check");		
		for(var i=0;i<alls.length;i++){
			alls[i].checked=true;	
		}	
			
	}
	//反选
	function converse(){
		var alls=document.getElementsByName("check");
		for(var i=0;i<alls.length;i++){
			if(alls[i].checked){
				alls[i].checked=false;
			}else{
				alls[i].checked=true;
			}
		}
	}
	//点得复选框时全选或全不选
	function checkAll(){		
		var ch=document.getElementById("checkall");
		var alls=document.getElementsByName("check");
		if(ch.checked){					
			for(var i=0;i<alls.length;i++){
				alls[i].checked=true;	
			}
		}else{
			for(var i=0;i<alls.length;i++){
				alls[i].checked=false;	
			}
		}
	}
	//删除选中
	function delAll(){
		var alls=document.getElementsByName("check");
		var ids=new Array();
		for(var i=0;i<alls.length;i++){
			if(alls[i].checked){
				ids.push(alls[i].value);//这种拼接字符串真是不一样哈！
			}		
		}
		if(ids.length>0){
			if(confirm("确认删除?")){
				alert("删除成功!");
				window.location.href= "<%=basePath %>employeePackage/role_circleDeleteRole.action?roleStrValue="+ids;

			}
		}else{
			alert("请选中要删除的项");
		}
	}
	//清空查询里的条件
	function clear1(){
		document.getElementById("role.roleName").value="";
	} 
	//导出Excel
	function exportExcel(){
		var alls=document.getElementsByName("check");
		var ids=new Array();
		for(var i=0;i<alls.length;i++){
			if(alls[i].checked){
				ids.push(alls[i].value);//这种拼接字符串真是不一样哈！
			}		
		}
		if(ids.length>0){
			if(confirm("确认导出?")){
				window.location.href= "<%=basePath %>employeePackage/menu_exportExcel.action?strValue="+ids;

			}
		}else{
			alert("请选中要导出的项");
		}
	} 
    </script>   
    
</head>
<body>
<form action="<%=basePath %>employeePackage/role_queryRoles.action"  class="form-inline definewidth m20"  method="post">  
    角色名称：
    <input type="text" name="role.roleName" id="role.roleName" value="${role.roleName }" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>
    <input type="button" class="btn btn-primary" onclick="clear1();" value="清空"/>
    <c:if test="${roleMsg==0 }"><span class='red'>(@_@) 未找到!!!!!!</span></c:if>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onclick="checkAll();"></th>
        <th>角色名称</th>
        <th>状态</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    	<c:forEach var="role" items="${listRole }">
	     <tr>
         	<td style="vertical-align:middle;">
         	<input type="checkbox" name="check" id="check" value="${role.rid }"></td>
            <td>${role.roleName }</td>
            <td><c:if test="${role.r_state==1 }">启用</c:if> 
            	<c:if test="${role.r_state==0 }">禁用</c:if>
            </td>
            <td><a href="<%=basePath %>employeePackage/role_editRole.action?editRid=${role.rid}">编辑</a>&nbsp;&nbsp;&nbsp;
            <a href="javascript:onclick=deleteOne(${role.rid});">删除</a> </td>
        </tr>
        </c:forEach>
</table>
        
        
   <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
  		<a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=1">首页</a> 
        <a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=${minus5}" ><<&nbsp;上5页</a>
	    <a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=${role.currentPage>1?role.currentPage-1:1}" ><&nbsp;上一页 </a> 
	  
    	 <c:forEach var="lis" items="${listPage }" varStatus="sta">
    	 	<c:choose>
    	 		<c:when test="${lis==role.currentPage }">
    	 			<a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=${lis }">
    	 			<span class='current'>${lis }</span>
    	 			</a> 
    	 		</c:when>
    	 		<c:otherwise>
    	 			<a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=${lis }">${lis }</a> 
    	 		</c:otherwise>
    	 	
    	 	</c:choose>
    	 	
    	 </c:forEach>
          <a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=${role.currentPage==role.totalPage?role.totalPage:role.currentPage+1}">下一页&nbsp;></a>  
	    <a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=${plus5}" >下5页&nbsp;>></a>
	    <a href="<%=basePath%>employeePackage/role_queryRoles.action?role.currentPage=${role.totalPage}">尾页</a>
       	 第<span class='current'>${role.currentPage }</span> / ${role.totalPage}页 ,共${role.totalSize}条记录
        </div>
               
               <div> 
                <button type="button" class="btn btn-success" id="newNav">添加角色 </button>&nbsp;&nbsp;&nbsp;
        	   	<button type="button" class="btn btn-success" id="delPro" onClick="checkall();">全选</button>
  				<button type="button" class="btn btn-success" id="delPro" onClick="converse();">反选</button>  		
        		<button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button>
          <button type="button" class="btn btn-success" id="delPro" onClick="exportExcel();">导出Excel</button>
          
        	   </div></th></tr>
  </table>     
 </body>
</html>