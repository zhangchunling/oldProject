<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>

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
		$('#newNav').click(function(){
				window.location.href="addnew.jsp";
		 });
    });
	
    	function checkall(){
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
    	
    	/* function del(uri){//删除方法
    		var boxName=document.getElementsByName("check");
    		var flag=true;
    		var str="";
    		for(var i=0;i<boxName.length;i++){	
    			if(boxName[i].checked){
    				str=str+boxName[i].value+",";
    				flag=false;    				
    			}
    		}
    		if(flag==true){
    			alert("亲！请至少选择一条信息！");
    		}else{
    			boxId=document.getElementById("boxId").value;
    			if(confirm("确认删除么？")){
    				location.href = uri+"?boxId="+str.substring(0,str.length-1);
    			}
    		}
    	} */
    	//清空
    	function clear1(){
		document.getElementById("cname").value="";
		document.getElementById("cphone").value="";
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
				window.location.href= "<%=basePath %>addContactPackage/self_exporContact.action?employeeStrValue="+ids;
										
			}
		}else{
			alert("请选中要导出的项");
		}
	} 
		 
    </script>
</head>
<body>

<form action="<%=basePath %>addContactPackage/self_queryEmployee.action" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名：</td>
        <td><input  type="text" id="cname" name="cname" value="${cname }" /></td>

         <td width="10%" class="tableleft">电话号码：</td>
        <td><input type="text" id="cphone" name="cphone" value="${cphone }"/></td>
    </tr>
    <tr>
		  <td colspan="6"><center>
            <button type="submit" class="btn btn-primary" type="button">查询</button> 
          	<button onclick="clear1();" class="btn btn-primary" type="button">清空</button> 
			</center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>姓名</th>
        <th>性别</th>
        <th>出生日期</th>
        <th>电话</th>
        <th>地址</th>
        <th>Email</th>
    </tr>
    </thead>
    	<c:forEach var="con" items="${list }">
	     <tr >
         	<td style="vertical-align:middle;">
         	<input type="checkbox" name="check" value="${con.eid }">
         	<input type="hidden" id="boxId" name="boxId"/>
         	</td>
            <td style="vertical-align:middle;">${con.empName }</td>
            <td style="vertical-align:middle;">
            <c:if test="${con.sex==1 }">男</c:if>
            <c:if test="${con.sex==0 }">女</c:if>
            </td>
            <td style="vertical-align:middle;">${con.birthday}</td>
            <td style="vertical-align:middle;">${con.tel }</td>
            <td style="vertical-align:middle;">${con.address }</td>
            <td style="vertical-align:middle;">${con.email }</td>
            
        </tr>
        </c:forEach>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  
  	<div class="inline pull-right page">
          <a href='<%=basePath %>addContactPackage/self_queryEmployee.action?current=1' >第一页</a> 
          <a href='<%=basePath %>addContactPackage/self_queryEmployee.action?current=${upPage}'>上一页</a>   
          
           <c:forEach var="pag" items="${list1}">
					
						<c:if test="${pag==current}" >
							<a href="<%=basePath%>addContactPackage/self_queryEmployee.action?current=${pag}" class='current' >${pag}</a>&nbsp;&nbsp;&nbsp;
						</c:if>
						<c:if test="${pag!=current}" >
							<a href="<%=basePath%>addContactPackage/self_queryEmployee.action?current=${pag}" >${pag}</a>&nbsp;&nbsp;&nbsp;
						</c:if>
		   			 </c:forEach>
          	<a href="<%=basePath%>addContactPackage/self_queryEmployee.action?current=${nextPage}">下一页</a> <a
						href="<%=basePath%>addContactPackage/self_queryEmployee.action?current=${endPage}">最后一页</a>
					&nbsp;&nbsp;&nbsp;共<span class='current'>${total}</span>条记录<span
						class='current'>${current}/${endPage} </span>页
				</div>
		<%--  <div><a href="<%=basePath %>AddressList/addnew.jsp"><button type="button" class="btn btn-success" id="newNav">添加新的联系人</button></a> --%>
		 <%--  <a href="<%=basePath %>addContactPackage/AddContact_outExport.action"  class="btn btn-success" >导出Excel<</a>  --%>
		  <button type="button" class="btn btn-success" id="delPro" onclick="exportExcel();">导出Excel</button>  
		<!--  <button type="button" class="btn btn-success" id="delPro" onclick="outExport();">导出Excel</button> -->
			<!-- <a onclick="del('addContactPackage/AddContact_delAll.action');">
         <button  type="button" class="btn btn-success" id="delPro">批量删除</button></a> -->
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
