<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>收件箱</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>Css/style.css" />
<script type="text/javascript" src="<%=basePath%>Js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>Js/jquery.sorted.js"></script>
<script type="text/javascript" src="<%=basePath%>Js/bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>Js/ckform.js"></script>
<script type="text/javascript" src="<%=basePath%>Js/common.js"></script>

<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
 
</style>
 
 

 
  <script type="text/javascript" src="<%=basePath%>Email/js/giveemail.js"></script>
</head>
<body >

	<form action="<%=basePath%>mypackage/email_receiveemail.action" method="post" class="definewidth m20" >
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">搜索条件：</td>
				<td><select name="title" id="title" >
						<option value="0" checked = "checked">请选择查询的条件</option>
						<option value="1">标题</option>
					</select>
				</td>
				<td width="10%" class="tableleft">关键字：</td>
				<td><input type="text" name="keyword" id = "keywork" value="${key }" /></td>

				<td width="10%" class="tableleft">排序：</td>
				<td><select name="order" id="order" >
					<option value="0" checked = "checked">请选择排序的条件</option>
						<option value="1">发送时间</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="6">
				
					<center>
							<input  type="submit" value="查询" class="btn btn-primary" /> 
						  
						 	<a href="mypackage/email_empty2.action" class="btn btn-success">清空</a>
					</center>
				</td>
			</tr>
		</table>
	</form>
						

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkall" onChange="checkall();">&nbsp;&nbsp;全选</th>
				<th>序号</th>
				<th>标题</th>
				<th>内容</th>
				<th>发件人</th>
				<th>发送时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${reList }" var="give" varStatus="i">
			<tr>
				<td style="vertical-align:middle;">
					<input type="checkbox" name="ids" value="${give.id }">
				</td>
				<td style="vertical-align:middle;">${i.count }</td>
				<td style="vertical-align:middle;">${give.title }</td>
				<td style="vertical-align:middle;">${give.content }</td>
				<td style="vertical-align:middle;">${give.send }</td>
				<td>${give.sendTime }</td>
				<td>
						<c:if test="${give.state==1 }"><font color="green">未读</font></c:if>
						<c:if test="${give.state==2 }"><font color="red">已读</font></c:if>
				 </td>
				
				
				<td style="vertical-align:middle;">
				
				<a href="<%=basePath%>mypackage/email_receives.action">回复</a> 
				<a href="<%=basePath%>mypackage/email_queryByIdReceives.action?id=${give.id}">详情>>></a> &nbsp;&nbsp;&nbsp; 
				<a href="javascript:onclick=deleteEmail2('${give.id}');"><font color="red">删除</font></a></td>
				


			</tr>
		</c:forEach>
	</table>

	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th colspan="5">
				<div class="inline pull-right page">
					<a href='mypackage/email_receiveemail.action?current2=1'>第一页</a> <a
						href='mypackage/email_receiveemail.action?current2=${upPage}'>上一页</a>

					 
					<c:forEach var="pag" items="${list}">
					
							<c:if test="${pag==current2 }"><a href="mypackage/email_receiveemail.action?current2=${pag}" class='current' >${pag}</a></c:if>
							 
							<c:if test="${pag!=current2 }"><a href="mypackage/email_receiveemail.action?current2=${pag}">${pag}</a></c:if>&nbsp;&nbsp;&nbsp;
					
		   			 </c:forEach>
				 
					<a href='mypackage/email_receiveemail.action?current2=${nextPage}'>下一页</a> 
					<a href='mypackage/email_receiveemail.action?current2=${endPage}'>最后一页</a>
					&nbsp;&nbsp;&nbsp;
					共<span class='current'>${total}</span>条记录
					<span class='current'> ${current2}/${endPage}</span>页
				</div>
				<div>
				
				<button  class="btn btn-success" onclick="backSelect();">反选</button>
					<button  class="btn btn-success" onclick="selectDelete('2');">删除</button>
					<button   class="btn btn-primary" onclick="outExcel2();">导出Excel</button>
				</div>

			</th>
		</tr>
	</table>

</body>
</html>
