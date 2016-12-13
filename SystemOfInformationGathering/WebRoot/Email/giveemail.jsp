<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发件箱</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>Css/bootstrap-responsive.css" />
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

	<form action="mypackage/email_giveemail.action" method="post" class="definewidth m20" >
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
							 
							 <a href="email_empty.action" class="btn btn-success">清空</a>
					</center>
				</td>
			</tr>
		</table>
	</form>
						

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th><input type="checkbox" id="checkall" onChange="checkall();" >&nbsp;&nbsp;全选</th>
				<th>序号</th>
				<th>标题</th>
				<th>内容</th>
				<th>收件人</th>
				<th>发送时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${giveemail}" var="give" varStatus="i">
			<tr>
				<td style="vertical-align:middle;">
					<input type="checkbox" name="ids" value="${give.id }">
				</td>
				<td style="vertical-align:middle;">${i.count }</td>
				<td style="vertical-align:middle;">${give.title }</td>
				<td style="vertical-align:middle;">${give.content }</td>
				<td style="vertical-align:middle;">${give.receive }</td>
				<td>${give.sendTime }</td>
				<td style="vertical-align:middle;">
					<a href="<%=basePath%>mypackage/email_queryById.action?id=${give.id}">详情>>></a> &nbsp;&nbsp;&nbsp; 
					<a href="javascript:onclick=deleteEmail('${give.id}');"><font color="red">删除</font></a>
				</td>



			</tr>
		</c:forEach>
	</table>

	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th colspan="5">
				<div class="inline pull-right page">
					<a href='<%=basePath%>mypackage/email_giveemail.action?current=1'>第一页</a> <a
						href='<%=basePath%>mypackage/email_giveemail.action?current=${upPage}'>上一页</a>

					 
					<c:forEach var="pag" items="${list}">
					
						<c:if test="${pag==current}" >
							<a href="<%=basePath%>mypackage/email_giveemail.action?current=${pag}" class='current' >${pag}</a>&nbsp;&nbsp;&nbsp;
						</c:if>
						<c:if test="${pag!=current}" >
							<a href="<%=basePath%>mypackage/email_giveemail.action?current=${pag}" >${pag}</a>&nbsp;&nbsp;&nbsp;
						</c:if>
		   	 </c:forEach>
				 
					<a href="<%=basePath%>mypackage/email_giveemail.action?current=${nextPage}">下一页</a> <a
						href="<%=basePath%>mypackage/email_giveemail.action?current=${endPage}">最后一页</a>
					&nbsp;&nbsp;&nbsp;共<span class='current'>${total}</span>条记录<span
						class='current'>${current}/${endPage} </span>页
				</div>
				<div>
					<button  class="btn btn-success" onclick="backSelect();">反选</button>
					<button  class="btn btn-success" onclick="selectDelete('1');">删除</button>
					<button   class="btn btn-primary" onclick="outExcel();">导出Excel</button>
				</div>

			</th>
		</tr>
	</table>

</body>
</html>
