<%@page import="com.zrgk.manu.bean.PageFile"%>
<%@page import="com.zrgk.manu.service.impl.PageFileServiceImpl"%>
<%@page import="com.zrgk.manu.service.PageFileServiceInter"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的稿件</title>
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
    
    $(document).ready(function(){//处理分页12345标签 加载页面执行
    	if($('#listSize').val()==null){
			$('#nullList').show();
		}else{
			$('#nullList').hide();
		}
    
    	var atag=$('#atag');
    	var totalPage=$('#totalPage').val();
    	var currentPage=$('#PageDomCtrl').val();
    	var h="PageFile_queryPf.action?pageFile.currentPage=";
    	var str="";
    	var sp="<span class='current'>"
    	if(currentPage%5==1||currentPage/5>1){//当当前页/5%1代表到了当前分页A标签的最后一个，执行下列生成方式
    		if(totalPage-currentPage>=4){//如果总页数-当前页>=4代表此页的分页A标签可以排满
	    		for(var i=currentPage;i<=currentPage-0+4;i++){
	    			if(currentPage==i){
	    				str=str+"<a href="+h+i+"><span style='color: red;'>"+i+"</span></a>";
	    				continue;
	    			}
	    			str=str+"<a href="+h+i+">"+i+"</a>";
    			}
    		}else{//如果总页数-当前页小于4，就根据totalpage-currentPage生成存在的分页标签
    			for(var i=currentPage;i<=totalPage;i++){
	    			if(currentPage==i){
	    				str=str+"<a href="+h+i+"><span style='color: red;'>"+i+"</span></a>";
	    				continue;//判断A标签组，给当前页对应的A标签高亮
	    			}
	    			str=str+"<a href="+h+i+">"+i+"</a>";
    			}
    		}	
    		atag.html(str);//生成标签
    	}else{//此为第一组1~5标签页生成方式
    		for(var i=1;i<=5;i++){
    			if(currentPage==i){
    				str=str+"<a href="+h+i+"><span style='color: red;'>"+i+"</span></a>";
    				continue;
    			}
    			str=str+"<a href="+h+i+">"+i+"</a>";
    		}
    	atag.html(str);//生成标签
    	}
    
    
    
    
    $("#msg").html($('#pageFileMsg').val()).show(300).delay(3000).hide(300); //信息提示效果
    		//如果当前页=1则隐藏首页与上一页
    		if($('#PageDomCtrl').val()==1){
    			$('#firstPage').hide();
    			$('#upPage').hide();
    		}
    		//如果当前页=总页数（尾页）则隐藏下一页与尾页
    		if($('#PageDomCtrl').val()==$('#totalPage').val()){
    			$('#nextPage').hide();
    			$('#lastPage').hide();
    		}
    
    
    });
    
    //导出excel
    function exportExcel(){
    		var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认导出选中的稿件信息?")){
					window.location.href="PageFile_exportExcel.action?exportExcelType="+ids;
				}
			}else{
				if(confirm("请勾选要导出的稿件,[确定] 将导出所有稿件信息！")){
					window.location.href="PageFile_exportExcel.action?exportExcelType=all";
				}
			}
    
    }

	//跳转稿件新增页面
	 $(function () {
		$('#newPf').click(function(){
				window.location.href="MyFile/addfile.jsp";
		 });
    });
    
    
    //置空条件查询
     $(function () {
		$('#claer').click(function(){
				var wq1=$('#wqtype').val("");
				var wq2=$('#wqstyle').val("");
				var wq3=$('#wqname').val("");
				
			
		 });
    });
		//全选 全不选
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
		//删除选中的项 不选则不操作
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认操作?")){
					window.location.href="PageFile_deletePfById.action?ids="+ids;
				}
			}else{
				alert("请选择要删除的稿件。");
			}
		}
    </script>
</head>
<body>
<form action="<%=basePath %>Manu_queryOkedPageList.action?pageFile.currentPage=1" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">类别</td>
        <td><input type="text" name="pageFile.pf_type" value="${spf.pf_type }" id="wqtype"/></td>

        <td width="10%" class="tableleft">文体</td>
        <td><input type="text" name="pageFile.pf_style" value="${spf.pf_style }" id="wqstyle"/></td>

        <td width="10%" class="tableleft">标题</td>
        <td><input type="text" name="pageFile.pf_name" value="${spf.pf_name }" id="wqname"/></td>
    </tr>
    <tr>
		  <td colspan="6"><center>
            <button type="submit" class="btn btn-primary" type="button">查询</button> 
            <button type="button" id="claer"class="btn btn-primary" type="button">清空</button> 
			</center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
   <tr >
   	<th colspan="9"><div align="center" style="color: #483d8b;font-family:'微软雅黑'" id="msg"></div></th><!-- 动态提示效果 -->
   </tr>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
    	<th>序号</th>
        <th>标题</th>
        <th>类别</th>
        <th>文体</th>
        <th>内容</th>
        <th>发稿日期</th>
        <th>审核状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <!--遍历pf list  -->
    	<c:forEach  var="pf" items="${pfs}" varStatus="status">
    	<tr>
    		<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${pf.pf_id }"></td>
            <td style="vertical-align:middle;">${status.count }</td>
            <td style="vertical-align:middle;">${pf.pf_name }</td>
            <td style="vertical-align:middle;">${pf.pf_type }</td>
            <td style="vertical-align:middle;">${pf.pf_style }</td>
            <td style="vertical-align:middle;">${pf.subContent }</td>
            <td style="vertical-align:middle;">${pf.pf_createtime }</td>
            <td style="vertical-align:middle;">${pf.pf_state==0?"未提交":pf.pf_state==1?"未审核":pf.pf_state==2?"已提交[未审核]":pf.pf_state==3?"审核通过":pf.pf_state==4?"审核未通过":pf.pf_state==5?"已录用":"未录用"}</td>
            <td style="vertical-align:middle;">
            <a href="<%=basePath %>Manu_queryOkedPageFileById.action?pageFile.pf_id=${pf.pf_id}">查看详情</a><%-- --<a href="<%=basePath%>PageFile_deletePfById?pageFile.pf_id=${pf.pf_id}">删除</a> --%>
           <input id="listSize" type="hidden" value="${pf.pf_id }">
            </td>
    	</tr>
    	</c:forEach>
    	<tr id="nullList" ><td colspan="8"><center>暂无数据</center></td></tr>
  </table>
  <input type="hidden" value="${pageFile.currentPage }" id="PageDomCtrl">
  <input type="hidden" value="${pageFile.totalPage }" id="totalPage"/>
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
          <a id="firstPage" href='<%=basePath %>PageFile_queryPf.action?pageFile.currentPage=1&flag=223' >首页</a> 
          <a id="upPage" href='<%=basePath %>PageFile_queryPf.action?pageFile.currentPage=${pageFile.currentPage<=1?pageFile.currentPage:pageFile.currentPage-1}&flag=223'>上一页</a>    
       	 <span id="atag">
       	 </span> 
           <!--&queryType=null是为了清空action里按条件查询留下的记录  -->
         	<%--  <a id="upPage" href='<%=basePath %>PageFile_queryPf?pageFile.currentPage=${pageFile.currentPage<=1?pageFile.currentPage:pageFile.currentPage-1}'>上一页</a>    
           <a id="firstPage" href='<%=basePath %>PageFile_queryPf?pageFile.currentPage=1' >首页</a> --%>
           <a id="nextPage" href='<%=basePath %>PageFile_queryPf.action?pageFile.currentPage=${pageFile.currentPage>=pageFile.totalPage?pageFile.currentPage:pageFile.currentPage+1}&flag=223' >下一页</a>
           <a id="lastPage" href='<%=basePath %>PageFile_queryPf.action?pageFile.currentPage=${pageFile.totalPage}&flag=223' >尾页</a>
		  &nbsp;&nbsp;&nbsp;共<span class='current'>${pageFile.count}</span>条记录<span class='current'> ${pageFile.count==0?"0":pageFile.currentPage }/${pageFile.totalPage} </span>页
		  </div>
         <input type="hidden" id="pageFileMsg" value="${msg }">
		 </th></tr>
  </table>
  
</body>
</html>
