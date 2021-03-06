<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>中软高科信息采编系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="<%=basePath %>assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="header">

    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">${welName }</span><a href="<%=basePath%>employeePackage/login_quit.action" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">信息采编系统</div></li>

		</ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">
   
    </ul>
</div>

<script type="text/javascript" src="<%=basePath %>assets/js/jquery-1.6.js"></script>
<script type="text/javascript" src="<%=basePath %>assets/js/bui.js"></script>
<script type="text/javascript" src="<%=basePath %>assets/js/common/main-min.js"></script>
<script type="text/javascript" src="<%=basePath %>assets/js/config-min.js"></script>
<script>
    BUI.use('common/main',function(){
        var config = ${json}
    /*     [
		{id:'1',menu:[
		
		{text:'权限管理',items:[
		
		{id:'2',text:'人员管理',href:'employeePackage/employee_queryEmployee.action?employee.currentPage=1&employeekey=1'},
		{id:'3',text:'角色管理',href:'employeePackage/role_queryRoles.action?role.currentPage=1&rolekey=1'},
		{id:'4',text:'菜单管理',href:'employeePackage/menu_queryMenus.action?menu.currentPage=1&key=1'}
		]},
		{text:'邮件系统',items:[
				{id:'6',text:'发邮件',href:'Email/sendemail.html'},
				{id:'7',text:'发件箱',href:'Email/giveemail.html'},
				{id:'8',text:'收件箱',href:'Email/receiveemail.html'}
		]},
		{text:'通讯录',items:[
				{id:'10',text:'内部通讯录',href:'AddressList/index.html'},
				{id:'11',text:'个人通讯录',href:'addContactPackage/AddContact_queryContact.action'}	
			]},{text:'我的约稿',items:[
				{id:'13',text:'待回应约稿',href:'MyManuscripts/index.html'},
				{id:'14',text:'待提交约稿',href:'MyManuscripts/index2.html'},
				{id:'15',text:'已提交约稿',href:'MyManuscripts/index3.html'}
			]},{text:'约稿管理',items:[
				{id:'16',text:'已发布约稿',href:'Manuscripts/listinfo.html'},
				{id:'17',text:'待审核约稿',href:'Manuscripts/listinfo2.html'},
				{id:'22',text:'已审核约稿',href:'Manuscripts/listinfo3.html'}
			]},{text:'成刊管理',items:[
				{id:'18',text:'二次审核稿件',href:'Journal/listinfo3.html'}
			]},{text:'稿件信息管理',items:[
				{id:'19',text:'我的稿件',href:'MyFile/filelist.html'}
			]},{text:'个人信息管理',items:[
				{id:'20',text:'基本信息',href:'Self/selfinfo.html'},
				{id:'21',text:'修改密码',href:'Self/password.html'}
			]}
		
		]}		
		]; */
		//使用js迭代json字符串  
        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
</script> 
<%-- <img src='<%=basePath %>User/1.png' id='admin'/> --%>
	<%-- <c:forEach var="pMenu" items="${urlList}" >            		
     	<c:if test="${pMenu.parentId==1}">
     		  <label class="control-label">
     		  	<a href="<%=basePath %>${pMenu.url}">${pMenu.menuName }</a>     		  	
     		  </label>
     		  
     		  <c:forEach var="menu" items="${urlList}">             		
     			 	<c:if test="${menu.parentId==pMenu.mid }">     			 	
					<a href="<%=basePath %>${menu.url}">${menu.menuName }</a>
     			  	</c:if>            			           			
     		  </c:forEach>            		  
  		</c:if>             
	</c:forEach> --%>
</body>
</html>