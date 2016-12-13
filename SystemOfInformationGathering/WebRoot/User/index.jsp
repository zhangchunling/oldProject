<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		.green{
			color: green;
			font-size: 15;
		}
		.red{
			color:red;
			font-size: 20;
		}	
	</style>
 

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
     
	
    /* function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
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
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认删除?")){
					alert("删除成功!");
				}
			}else{
				alert("请选中要删除的项");
			}
		}
					 */
	//点右边的删除时的跳转
	function deleteOne(id) {
		if(confirm("确定删除?")){
			alert("删除成功");
			window.location.href= "<%=basePath %>employeePackage/employee_deleteById.action?deleteById="+id;
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
	//点击复选框时全选或全不选
	function checkBoxAll(){		
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
				window.location.href= "<%=basePath %>employeePackage/employee_deleteEmployeeCircle.action?employeeStrValue="+ids;

			}
		}else{
			alert("请选中要删除的项");
		}
	}
	//清空查询里的条件
	function clear1(){
		document.getElementById("employee.empName").value="";
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
				window.location.href= "<%=basePath %>employeePackage/employee_exportEmployee.action?employeeStrValue="+ids;

			}
		}else{
			alert("请选中要导出的项");
		}
	} 

	function aa() {
		var jump=document.getElementById("atpage").value;
		if(jump>0){
				window.location.href= "<%=basePath %>employeePackage/employee_queryEmployee.action?employee.currentPage="+jump;		
		}
	}
	function cesi() {
		document.getElementById("bbb").style.display="234565432363t43";
	}
	function ooo() {
		document.getElementById("bbb").style.display="none";
	}
    </script>
</head>
<body>
<form class="form-inline definewidth m20" action="<%=basePath %>employeePackage/employee_queryEmployee.action" method="post">    
    用户名称：
    <input type="text" name="employee.empName" id="employee.empName"class="abc input-default" placeholder="" value="${employee.empName }">&nbsp;&nbsp;  
    <button id="bbb" type="submit" class="btn btn-primary" onmouseover="cesi();" onmouseout="ooo();">查询</button>
    <div title="这里是完整显示的文字" style="width:100px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis">这里是超出部分自动隐藏的文字</div>
    <input type="button" class="btn btn-primary" onclick="clear1();" value="清空" style="width:50px"/>
    <c:if test="${msg==0 }"><span class='red'>(@_@) 未找到!!!!!!</span></c:if>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>  
    	<th width="5%"><input type="checkbox" id="checkall" onclick="checkBoxAll();"></th>
        <th>用户账户</th>
        <th>真实姓名</th>
        <th>角色</th>
        <th  width="10%">操作</th>
    </tr>
    </thead>
    	<c:forEach var="emp" items="${list }">
	     <tr>	     
         	<td style="vertical-align:middle;">
         	<input type="checkbox" name="check" value="${emp.eid }"></td>
            <td>${emp.empName }</td>
            <td>${emp.realName }</td>
            <td>${emp.strRole }</td>
            <td>
                <a href="<%=basePath%>employeePackage/employee_editEmployee.action?editId=${emp.eid}">编辑</a>&nbsp;&nbsp;&nbsp;
                <a href="javascript:onclick=deleteOne(${emp.eid});">删除</a>             
            </td>
        </tr>
        </c:forEach>	
</table>

<table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5"> 
  	<div >
         <button type="button" class="btn btn-success" onclick="location='<%=basePath%>employeePackage/role_queryAllRole.action'">添加用户</button>      
        	   	<button type="button" class="btn btn-success" id="delPro" onClick="checkall();">全选</button>
  				<button type="button" class="btn btn-success" id="delPro" onClick="converse();">反选</button>  		
        		<button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button>
          <button type="button" class="btn btn-success" id="delPro" onclick="exportExcel();">导出Excel</button>  
         </div>
  	 </br><div class="inline pull-right page">         
        <a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=1">首页</a> 
        <a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=${minus5}" ><<&nbsp;上5页</a>
	    <a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=${employee.currentPage>1?employee.currentPage-1:1}" ><&nbsp;上一页 </a> 
	  
    	 <c:forEach var="lis" items="${listPage }" varStatus="sta">
    	 	<c:choose>
    	 		<c:when test="${lis==employee.currentPage }">
    	 			<a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=${lis }">
    	 			<span class='current'>${lis }</span></a> 
    	 		</c:when>
    	 		<c:otherwise>
    	 			<a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=${lis }">${lis }</a> 
    	 		</c:otherwise>
    	 	
    	 	</c:choose>
    	 	
    	 </c:forEach>	        
	    <a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=${employee.currentPage==employee.totalPage?employee.totalPage:employee.currentPage+1}">下一页&nbsp;></a>  
	    <a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=${plus5}" >下5页&nbsp;>></a>
	    <a href="<%=basePath%>employeePackage/employee_queryEmployee.action?employee.currentPage=${employee.totalPage}">尾页</a>
       	 第<span class='current'>${employee.currentPage }</span> / ${employee.totalPage}页 ,共${employee.totalSize}条记录
          ,跳到<input type="text" style="width:40px" name="atpage" id="atpage" >页
         <input type="submit" value="跳转" onclick="aa();" class="btn btn-success" style="width:50px">
         </div>  
         </th>
       
    </tr> 		
  </table>
</body>
</html>