<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查看--中软高科-2015</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/Css/style.css" />
<script type="text/javascript" src="<%=basePath%>/Js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/Js/jquery.sorted.js"></script>
<script type="text/javascript" src="<%=basePath%>/Js/bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/Js/ckform.js"></script>
<script type="text/javascript" src="<%=basePath%>/Js/common.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/Js/ckeditor/ckeditor.js"></script>


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
<script type="text/javascript">
	function backMenu() {

		window.location.href = "mypackage/email_receiveemail.action";
	}

	function download() {
		var msg = document.getElementById("msg");
		var files = document.getElementById("");
		var filename = document.getElementById("filename").value;
		 
		if (filename==""&&filename.length==0) {
		
			msg.innerHTML = "输入的文件不能为空!";
			msg.style.color = "red";
			return false;
		}
		
		
		  
	}
</script>
</head>
<body>
	<form action="index.html" method="post" class="definewidth m20">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<td width="10%" class="tableleft">标题</td>
				<td>${receives.title }</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">发件人</td>
				<td>${receives.send }</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">发送时间</td>
				<td>${receives.sendTime }</td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">状态</td>
				<td><c:if test="${receives.state==1 }">未读</c:if> <c:if
						test="${receives.state==2 }">已读</c:if></td>
			</tr>
			<tr>
				<td width="10%" class="tableleft">内容</td>
				<td><textarea name="" readonly="readonly" id=""
						class="control-row4 input-large" cols="8" rows="6">
            
           ${receives.content }
        </textarea></td>
			</tr>
			
			<c:forEach items="${accessorys}" var="acc" >
			<tr>
				<td width="10%" class="tableleft">附件</td>
				<td>${acc }&nbsp; &nbsp;&nbsp;&nbsp;
					<a href="mypackage/DownloadAction.action?filename=${acc }" class="btn btn-success" >下载</a>
					<%-- <input type="hidden" name="filename" value="${acc}"> --%>
			</td>
			</tr>
			
			</c:forEach>
			<tr>
				<td colspan="2">
					<center>
						<a href="email_state.action?id=${receives.id}"
							class="btn btn-success">已读</a> &nbsp; &nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-success" name="backid"
							id="backid" onclick="backMenu();">返回列表</button>
					</center>
				</td>
			</tr>
		</table>
	</form>
	 
	<%-- <br />
	<center>
			<center><font color="red"><s:fielderror name="error"></s:fielderror></font></center>
		<form action="DownloadAction.action" method="post"
			onsubmit="return download();">
			请输入要下载的文件名： <input type="text" id="filename" name="filename">&nbsp;
			&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<span style="color: green" id="msg">文件名是以","分割开的！</span>
			<br /> 
				<input type="hidden" value="${receives.id }" name="id"/>
				<input type="hidden" value="${receives.accessory }"
				name="file" id="file"> <input type="submit" value="下载"
				class="btn btn-success" />
		</form>

	</center> --%>
</body>
</html>