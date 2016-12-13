<%@page import="com.zrgk.manu.bean.PageFile"%>
<%@page import="com.zrgk.manu.service.impl.PageFileServiceImpl"%>
<%@page import="com.zrgk.manu.service.PageFileServiceInter"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <title>待提交约稿</title>
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
	   
	   //清空
	$(function () {
		$('#claer').click(function(){
				$('#mu_mname').val("");
				$('#mu_mbknum').val("");
				$('#mu_mstartTime').val("");
				$('#mu_mendTime').val("");
			
		 });
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
				if(confirm("确认导出选中的约稿?")){
					window.location.href="MyManu_exportExcl.action?acceptState=accept&exportExcelType="+ids;
				}
			}else{
				if(confirm("请勾选要导出的稿件,[确定] 将导出所有约稿！")){
					window.location.href="MyManu_exportExcl.action?acceptState=accept&exportExcelType=all";
				}
			}
    
    }
	
	
	 //根据隐藏的input标签判断有没有数据 有的话显示提示标签 否则隐藏提示标签
	 $(document).ready(function(){
		if($('#listSize').val()==null){
			$('#nullList').show();
		}else{
			$('#nullList').hide();
		}
    	var atag=$('#atag');
    	var totalPage=$('#totalPage').val();
    	var currentPage=$('#PageDomCtrl').val();
    	var h="MyManu_queryManu.action?acceptState=accept&flag=heh&mu.currentPage=";
    	var str="";
    	var sp="<span class='current'>"
    	 $("#msg").html($('#ManuAcceptedMsg').val()).show(300).delay(4500).hide(300); //信息提示效果
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
    		if(totalPage<5){//小于5生成小于5条
	    		for(var i=1;i<=totalPage;i++){
	    			if(currentPage==i){
	    				str=str+"<a href="+h+i+"><span style='color: red;'>"+i+"</span></a>";
	    				continue;
	    			}
	    			str=str+"<a href="+h+i+">"+i+"</a>";
	    		}
	    	}else{
		    	for(var i=1;i<=5;i++){
		    			if(currentPage==i){
		    				str=str+"<a href="+h+i+"><span style='color: red;'>"+i+"</span></a>";
		    				continue;
		    			}
		    			str=str+"<a href="+h+i+">"+i+"</a>";
		    		}
	    	
	    	}
    		atag.html(str);//生成标签
    	}
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
    
    if($('#subMsg').val()!=""){
	 		alert($('#subMsg').val());
	 	}
		
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
		
		
		function subValidate(){
		
		
		
		
		
		}
    </script>
</head>
<body>

<form action="<%=basePath %>MyManu_queryManu.action?mu.currentPage=1" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">刊物名：</td>
        <td><input type="text" id="mu_mname" name="mu.mname" value="${mymanu.mname }"/></td>
        <td width="10%" class="tableleft">刊物编号：</td>
        <td><input type="text" id="mu_mbknum" name="mu.mbknum" value="${mymanu.mbknum }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">约稿发布时间：</td>
        <td><input type="text" id="mu_mstartTime" name="mu.mstartTime" value="${mymanu.mstartTime }"/></td>

        <td width="10%" class="tableleft">约稿截止时间：</td>
        <td><input type="text" id="mu_mendTime" name="mu.mendTime" value="${mymanu.mendTime }"/></td>
    </tr>
    <tr>
		  <td colspan="6">
		  	<input id="acceptState" name="acceptState" type="hidden" value="accept">
			<center>
            <button type="submit" class="btn btn-primary" type="button">查询</button> 
            <button type="button" id="claer" class="btn btn-primary" type="button">清空</button> 
			</center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
   
    <tr >
   	<th colspan="10"><div align="center" style="color: #483d8b;font-family:'微软雅黑'" id="msg"></div></th><!-- 动态提示效果 -->
   </tr>
   
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>序号</th>
        <th>刊物期号</th>
        <th>刊物名</th>
        <th>约稿信息</th>
        <th>约稿发布时间</th>
        <th>约稿截止时间</th>
        <th>发稿人</th>
        <th>约稿状态</th>
        <th style="text-align: center;">操作</th>
    </tr>
    </thead>
    	<c:forEach  var="m" items="${mus}" varStatus="status">
    	<tr>
    		<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${m.mid }"></td>
            <td style="vertical-align:middle;">${status.count }</td>
            <td style="vertical-align:middle;">${m.mbknum }</td>
            <td style="vertical-align:middle;">${m.mname }</td>
            <td style="vertical-align:middle;">${m.subMinfo }</td>
            <td style="vertical-align:middle;">${m.mstartTime }</td>
            <td style="vertical-align:middle;">${m.mendTime }</td>
            <td style="vertical-align:middle;">${m.mmaster }</td>
            <td style="vertical-align:middle;">已接受</td>
    		<td style="text-align: center;" >
    		<input id="listSize" type="hidden" value="${m.mid }">
    		
    		<%-- <a href="<%=basePath%>MyManu_subMyManu?muu.mid=${m.mid}">提交</a>&nbsp;&nbsp;&nbsp; --%>
    		<a href="<%=basePath%>MyManu_subMyManu.action?muu.mid=${m.mid}">提交</a>&nbsp;&nbsp;&nbsp;
    		<a href="<%=basePath%>MyManu_queryManuById.action?mu.mid=${m.mid}">查看详情</a>&nbsp;&nbsp;&nbsp;
    		<%-- <a class="btn btn-primary" href="<%=basePath %>MyManu_editPageFile.action?mu.mid=${m.mid}">编辑我的稿件</a> --%>
    		<input id="listSize" type="hidden" value="${m.mid }">
    		</td>
    	</tr>
    	</c:forEach>
    	<tr id="nullList" ><td colspan="10"><center>暂无数据</center></td></tr>
  </table>
  <table class="table table-bordered table-hover definewidth m10" >
  	 	<tr><th colspan="5">  <div class="inline pull-right page">
          <a id="firstPage" href='<%=basePath %>MyManu_queryManu.action?mu.currentPage=1&acceptState=accept&flag=heh' >首页</a> 
          <a id="upPage" href='<%=basePath %>MyManu_queryManu.action?mu.currentPage=${mu.currentPage<=1?mu.currentPage:mu.currentPage-1}&acceptState=accept&flag=heh'>上一页</a>    
       	 <span id="atag">
       	 </span> 
           <!--&queryType=null是为了清空action里按条件查询留下的记录  -->
         	<%--  <a id="upPage" href='<%=basePath %>PageFile_queryPf?pageFile.currentPage=${pageFile.currentPage<=1?pageFile.currentPage:pageFile.currentPage-1}'>上一页</a>    
           <a id="firstPage" href='<%=basePath %>PageFile_queryPf?pageFile.currentPage=1' >首页</a> --%>
           <a id="nextPage" href='<%=basePath %>MyManu_queryManu.action?mu.currentPage=${mu.currentPage>=mu.totalPage?mu.currentPage:mu.currentPage+1}&acceptState=accept&flag=heh' >下一页</a>
           <a id="lastPage" href='<%=basePath %>MyManu_queryManu.action?mu.currentPage=${mu.totalPage}&acceptState=accept&flag=heh' >尾页</a>
		  &nbsp;&nbsp;&nbsp;共<span class='current'>${mu.count}</span>条记录<span class='current'> ${mu.count==0?"0":mu.currentPage }/${mu.totalPage} </span>页
		  </div>
		 <div>
		 <button type="button" class="btn btn-primary" id="delPro" onclick="exportExcel();">导出Excel</button>&nbsp;
		 <button type="button" class="btn btn-primary" id="delPro1" onclick="javascript:window.location.href='<%=basePath %>PageFile_queryPf.action?pageFile.currentPage=1'">稿件管理</button>
		 </div>
		 
		 </th></tr>
  </table>
  <!--  控制器 -->
 <input type="hidden" value="${subMsg}" id="subMsg"/>
  <input type="hidden" value="${mu.currentPage }" id="PageDomCtrl">
  <input type="hidden" value="${mu.totalPage }" id="totalPage"/>
  <input type="hidden" value="${ManuAcceptedMsg }" id="ManuAcceptedMsg"/>
</body>
</html>
