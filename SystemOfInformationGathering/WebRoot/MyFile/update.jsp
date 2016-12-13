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
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
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
    <script type="text/javascript" charset="utf-8">
        KE.show({
            id: 'content',
            resizeMode: 1,
            allowPreviewEmoticons: false,
            allowUpload: false,
        });
    </script>
    
    <script type="text/javascript">
   var flag1=false;
	 var flag2=true;
	 var flag3=true;
	 var flag4=false;
	 
	    $(function () {
	    	$('#pf_name').blur(function(){
    	if($('#pf_name').val().length==0){
    		$('#pfName').html("<img src='Images/warning.png'/>标题不能为空.");
    	}else if($('#pf_name').val().length>20){
    		$('#pfName').html("<img src='Images/warning.png'/>长度不能大于20.");
    	}
    	else{
    		$('#pfName').html("<img src='Images/ok.PNG'/>");
    		flag1=true;
    	}
   	 	});
	    
            $("#pf_type").blur(function () {
                var textReg = /^[\u4e00-\u9fa50-9]+$/;
                  if ($("#pf_type").val().trim()=="") {
               		$('#pfType').html("<img src='Images/warning.png'/>类别不能为空.");
               		flag3=false;
                } else if(textReg.test($('#pf_type').val())) {
                	$('#pfType').html("<img src='Images/ok.PNG'/>");
                	flag2=true;
                }else{
                	flag2=false;
               		$('#pfType').html("<img src='Images/warning.png'/>类别只能为中文或数字.");
                }
            });
             $("#pf_style").blur(function () {
                var textReg = /^[\u4e00-\u9fa50-9]+$/;//必须为中文或数字
                if ($("#pf_style").val().trim()=="") {
               		$('#pfStyle').html("<img src='Images/warning.png'/>文体不能为空.");
               		flag3=false;
                } else if(textReg.test($('#pf_style').val())) {
                	$('#pfStyle').html("<img src='Images/ok.PNG'/>");
                	flag3=true;
                }else{
               		$('#pfStyle').html("<img src='Images/warning.png'/>文体只能为中文或数字.");
                	flag3=false;
                }
            });
             $("#contentA").blur(function () {
                if ($("#contentA").val().trim()=="") {
               		$('#minfoMsg').html("<img src='Images/warning.png'/>刊物要求信息不能为空.");
                } else if($("#contentA").val().length>10) {
                	$('#minfoMsg').html("<img src	='Images/ok.PNG'/>");
                	flag4=true;
                }else{
               		$('#minfoMsg').html("*最少输入10个字符.");
                }
            });
            
        });
    function subUpdate(){
    	if(flag1&&flag2&&flag3&&flag4){
				document.getElementById("insertFile").submit();
			}else{
				 $("#submsg").html("请完善页面信息!").show(300).delay(3000).hide(300);
			}
    
    
    }
    
    
    </script>
</head>
<body>
<form action="<%=basePath %>PageFile_updatePageFile.action" id="insertFile" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft" colspan="2"><span id="111" style=" color:silver; font-size: 13px" >Tip:*为必填项</span></td>
    </tr>
    
    <tr>
        <td width="10%" class="tableleft">写稿人</td>
        <td>${pageFile.pf_author }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">标题</td>
        <td><input  id="pf_name" name="pageFile.pf_name" value="${pageFile.pf_name }"><span id="pfName" style=" color: red; font-size: 13px" >*</span></td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">类别</td>
        <td><input  id="pf_type" name="pageFile.pf_type" value="${pageFile.pf_type }"><span id="pfType" style=" color: red; font-size: 13px" >*</span></td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">文体</td>
        <td><input  id="pf_style" name="pageFile.pf_style" value="${pageFile.pf_style }">
    	<span id="pfStyle" style=" color: red; font-size: 13px" >*</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">稿件内容</td>
        <td>
         <textarea cols="60"   name="pageFile.content" id="contentA" style="width: 600px; height: 300px;" runat="server" 
       	  >${pageFile.content }</textarea><div id="minfoMsg" style="color: red;font-size: 5px;float: right;" >*最少输入10个字符</div>
        </td>
    </tr>
    
     <tr>
    </tr>
    <tr>
        <td colspan="2">
			<center>

				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="subUpdate();"  >修改</button>
                <button type="button" class="btn btn-primary" onclick="javascript:window.history.go(-1)" >返回</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>