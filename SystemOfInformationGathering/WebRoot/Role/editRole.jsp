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
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>js-ajax/jquery-1.8.2.js"></script>
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
	 //返回列表
	 function backRoleList(){
		if(confirm("确定放弃？")){
			window.location.href="<%=basePath %>employeePackage/role_queryRoles.action?rolekey=1";
		}
	}
	</script>  


<script type="text/javascript">
   function writeBacks() {	  		
		//回写状态
		var backValue = document.getElementById("stateHidden");
		var stateArray = document.getElementsByName("role.r_state");
			for(var i=0;i<stateArray.length;i++){
				if(stateArray[i].value==backValue.value){
					stateArray[i].checked=true;
				}
			}

		//回写菜单
	 	var hobs=document.getElementById("strMenuHidden").value;//得到拼接串
		var hobArray=hobs.split(",");//转成一个数组方便下面处理
		
		var hobbys=document.getElementsByName("strMenu");//得到爱好数组
			for(var i=0;i<hobbys.length;i++){
				for(var j=0;j<hobArray.length;j++){
					if(hobbys[i].value==parseInt(hobArray[j])){//字符串的数据字要解析下				
						hobbys[i].checked=true;						
					}
				}
			}  
	} 
	
	function checkRole() {
  		//校验角色名称
		var rn=document.getElementById("role.roleName").value;
		var roleSpan=document.getElementById("roleSpan");
		if(""==rn.trim()){
			roleSpan.innerHTML="角色名称不能为空";
			roleSpan.className="red";
		}else if(rn.length>10){
			var str=rn.substring(0,10);
			document.getElementById("role.roleName").value=str;
			roleSpan.innerHTML="角色名称不能超过10个字符，已自动截取前10个";
			roleSpan.className="yellow";
		}else{
   			//异步判断用户是否存在			
   		   	var today = new Date();   		 
   			$.ajax({   //后面跟一个时间的参数是为了防止有时候不提交的问题【冲破缓存，也可跟个随机数】	   				
   			url:"employeePackage/role_checkEditRoleAjax.action?time="+today.getTime(),//访问路径
  			type:"post",//提交方式
  			data:"rolename="+rn, //username是action类里的属性
  			success:function(data){//回调函数：返回的是json串,out.print("")里的字符串
  					 
  				if("1"==data){   						 
  	   				roleSpan.innerHTML="已存在,请重新输入";
  	   				roleSpan.className="red";  
  	   				document.getElementById("role.roleName").value="";
  	   			}else{
  	   				roleSpan.innerHTML="OK(^_^)";
  	   				roleSpan.className="green";
  	   				}  					 		     
  				}
  			 }); 
  			/*  var today = new Date();
  			 alert(rn);
  			 $.ajax({
  			 	url:"employeePackage/role_checkEditRoleAjax.action",
  			 	type:"post",
  			 	data:"rolename="+rn,
  			 	success:function(){
  			 	alert("sss");
  			 	}
  			 }); */
		}		
	}
	//校验提交
	function checkSubmit(){
		//校验名称
		var rn=document.getElementById("role.roleName").value;
		var roleSpan=document.getElementById("roleSpan");
		if(""==rn.trim()){
			roleSpan.innerHTML="角色名称不能为空";
			roleSpan.className="red";
			return false;
		}
		//校验菜单
		var menuStr=document.getElementsByName("strMenu");
		var s=0;
		for(var i=0;i<menuStr.length;i++){
			if(menuStr[i].checked){
				s=1;
				break;
			}
		}
		if(s>0){			
			alert("更新成功！");
			document.getElementById("formId").submit();			
		}else{
			alert("请选择菜单");			
		}		
	}	
	
	//二级菜单选中时，一级也要选中
	function selectFather(pid) {
		document.getElementById(pid).checked=true;
	}
	//一级菜单选中，二级全选
	function selectChild(pmid) {
		var childArray=document.getElementsByName("hiddenStr");//得到所有子菜单
		var parent=document.getElementById(pmid);//得到父菜单
		for(var i=0;i<childArray.length;i++){
			if(parent.checked){			
				if(pmid==childArray[i].value){
					document.getElementById(childArray[i].id-1000).checked=true;
				}
			}else{
				if(pmid==childArray[i].value){
					document.getElementById(childArray[i].id-1000).checked=false;
				}
			}
			
		}
		
	}
</script>

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
</head>

<body onload="writeBacks();">
<form id="formId" action="<%=basePath %>employeePackage/role_updateRole.action?rolekey=1" method="post" class="definewidth m20">
 
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">角色名称</td>
            <td><input type="text" id="role.roleName" name="role.roleName" value="${role.roleName }" onblur="checkRole();"/>
            <span id="roleSpan" >*必填</span> </td></td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" id="role.r_state" name="role.r_state" value="1"/> 启用 
                <input type="radio" id="role.r_state" name="role.r_state" value="0"/> 禁用
                <input type="hidden" id="stateHidden" value="${role.r_state }" />
            </td>
        </tr>
        <tr>
            <td class="tableleft">权限</td>
            <td><div class="control-group">
            
            	<c:forEach var="pMenu" items="${listMenu}" >            		
            		<c:if test="${pMenu.parentId==1}">
            		  <label class="control-label">
            		  	<input type="checkbox" id="${pMenu.mid }" name="strMenu" value="${pMenu.mid}" onclick="selectChild(${pMenu.mid });">${pMenu.menuName}
            		  </label>
            		  
            		  <c:forEach var="menu" items="${listMenu}">             		
            			 	<c:if test="${menu.parentId==pMenu.mid }">
            			 	<label class="checkbox" for="">
            					<input type="checkbox" id="${menu.mid }" name="strMenu" value="${menu.mid}" onclick="selectFather(${menu.parentId});">${menu.menuName}
            			  		<input type="hidden" id="${menu.mid+1000 }" name="hiddenStr" value="${menu.parentId }" />
            			  	</label>
            			  	</c:if>            			           			
            		  </c:forEach>            		  
            		</c:if>             
                </c:forEach>
                <input type="hidden" id="strMenuHidden"  name="strMenuHidden" value="${role.strRole }" />
                </div>        
            </td>
        </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button  class="btn btn-primary" type="button" onclick="checkSubmit();">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" onclick="backRoleList();">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
