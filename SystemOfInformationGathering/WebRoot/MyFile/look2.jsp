<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>查看详情</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css\ href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
     <script type="text/javascript" src="Js/modal.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/ckeditor/ckeditor.js"></script>

 

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
    <!--文本编辑器  -->
      <script charset="utf-8" src="<%=basePath %>kindEditor/kindeditor-min.js" 
             type="text/javascript"></script>
    
    <script type="text/javascript">
    $(document).ready(function(){ 
    if($('#pf_state').val()!=0){
    	$('#subButton').hide();
    }
    
    
}) ;
    
    </script>
</head>
<body id="lookPageFile">
<form action="<%=basePath %>PageFile" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">写稿人</td>
        <td>${pageFile.pf_author }</td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">所属约稿</td>
        <td>${mu.getmname }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">标题</td>
        <td><input readonly="readonly" id="pf_name" name="pageFile.pf_name" value="${pageFile.pf_name }"></td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">类别</td>
        <td><input readonly="readonly" id="pf_type" name="pageFile.pf_type" value="${pageFile.pf_type }"></td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">文体</td>
        <td><input readonly="readonly" id="pf_style" name="pageFile.pf_style" value="${pageFile.pf_style }"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">稿件内容</td>
        <td>
         <textarea cols="60"   name="pageFile.content" id="content" style="width: 600px; height: 300px;" runat="server" 
       	 readonly="readonly" >${pageFile.content }</textarea>
        </td>
    </tr>
     <tr>
      <tr>
        <td width="10%" class="tableleft">创建日期</td>
        <td>${pageFile.pf_createTime }</td>
    </tr>
     <!--    <td width="10%" class="tableleft">选择约稿信息</td>
        <td>
            <select i>
                <option value="">青年文摘201507</option>
                <option value="">青年文摘201508</option>
                <option value="">青年文摘201509</option>
                <option value="">青年文摘2015012</option>
            </select>
        </td> -->
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<input type="hidden" id="pf_state" value="${pageFile.pf_state }">
				<button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)" >返回</button>
				<input id ="subButton" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" value="提交" />
                <button type="button" class="btn btn-primary" name="editPageFile" id="editPageFile">编辑</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>