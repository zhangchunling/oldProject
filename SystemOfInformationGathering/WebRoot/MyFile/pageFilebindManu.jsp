<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    <title>新增稿件</title>
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
    
	  <script charset="utf-8" src="<%=basePath %>kindEditor/kindeditor-min.js" 
             type="text/javascript"></script>
    <script type="text/javascript" charset="utf-8">
        KE.show({
            id: 'tc',
            resizeMode: 1,
            allowPreviewEmoticons: false,
            allowUpload: false,
        });
    </script>
 

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
            })
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
            })
        });
	
	
	//加载页面时隐藏指定的input标签
	$(document).ready(function(){
		var mselect=$('#pf_mu_id');
    	$.ajax(
    		 {
    			 url:"PageFile_ajaxManu.action",//后面跟一个时间的参数为了防止有时候不提交的问题
    			 type:"post",
    			// data:"test="+$('#ceshi').val(),//要传到action里的参数值
    			 success:function(data){//  回调函数
    			      var tempDate=data.split(",");
    			      var temp;
    			   	  for(var i=0;i<tempDate.length;i++){
    			   	  	temp=tempDate[i].split(":");
    			   	  	mselect.append(new Option(temp[0],temp[1]));
    			   	  }
    			 }
    		 }   
    	   );
    	
	
		$('#pf_name').focus();
		$('#pf_type').hide();
		$('#pf_style').hide();
		$('#pfStyle').hide();
		$('#pfType').hide();
		flag1=false;
		flag2=true;
	 	flag3=true;
	 	flag4=false;
	});
	//隐藏显示input标签.当select选择其他的时候，显示input输入框 ，隐藏select标签
    $(function () { 
    $('#selectPfType').change(function(){
    	$('#pf_type').val($('#selectPfType').val());
    	if($('#selectPfType').val()==""){
    		$('#pf_type').show();
			$('#pfType').show();
    		$('#pf_type').focus();
    		$('#selectPfType').hide();
    	}
   	 	});
   	 $('#selectPfStyle').change(function(){
    	$('#pf_style').val($('#selectPfStyle').val());
    	if($('#selectPfStyle').val()==""){
    		$('#pf_style').show();
    		$('#pfStyle').show();
    		$('#pf_style').focus();
    		$('#selectPfStyle').hide();
    	}
   	 	});	
   	 	
	 });
	//提交验证	 
    $(function () {       
		$('#savea').click(function(){
		
			if($('#pf_mu_id').val()!=0){
				if(confirm("[确认] 将此稿件添加到所选择的约稿下 （一个约稿只能绑定一个稿件，一经确定无法修改）")){
					document.getElementById("insertFile").submit();
				}
				
			}else{
				 $("#selectPfid").html("<img src='Images/warning.png' />请选择要绑定的约稿!").show(300).delay(3000).hide(300);
			}
			//$('#insertFile').submit;
		 });
    });
    
    </script>
</head>
<body>
<form id="insertFile" action="<%=basePath %>PageFile_bindPf.action" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <input type="hidden" name="pageFile.pf_id" value="${pageFile.pf_id }"/>
    <tr>
        <td width="10%" class="tableleft">标题</td>
       <td> ${pageFile.pf_name }</td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">作者</td>
        <td>${pageFile.pf_author }</td>
    </tr>
     <tr>
        <td  width="10%" class="tableleft" >类别</td>
        <td>${pageFile.pf_type }
      <!--    <select id="selectPfType" style="width: 57px">
        	<option value="小说">小说</option>
        	<option value="散文">散文</option>
        	<option value="诗歌">诗歌</option>
        	<option value="纪实">纪实</option>
        	<option value="传记">传记</option>
        	<option value="">其他</option>
        </select> -->
        </td>
    </tr>
     <tr>
        <td width="10%" class="tableleft">文体</td>
        <td>${pageFile.pf_style }
       <!--  <span id="pfStyle" style=" color: red; font-size: 13px" >*</span>
        <select id="selectPfStyle" style="width: 57px">
        	<option value="言情">言情</option>
        	<option value="惊悚">惊悚</option>
        	<option value="悬疑">悬疑</option>
        	<option value="盗墓">盗墓</option>
        	<option value="科幻">科幻</option>
        	<option value="">其他</option>
        </select> -->
        
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">稿件内容</td>
        <td > 
          <textarea  cols="60" name="pageFile.content"  readonly="readonly" style="width: 600px; height: 300px;">${pageFile.content }</textarea><div  style="color: red;font-size: 5px;float: right;" ><!-- *最少输入10个字符 --></div>
        </td>
        
    </tr>
     <tr>
        <td width="10%" class="tableleft" >选择约稿</td>
        <td>
            <select  id="pf_mu_id" name="pageFile.pf_mu_id" >
            	<option value="0" >--请选择--</option>
            </select><span id="selectPfid"><font color="red" id="submsg">*</font></span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
    </tr>
    <tr>
        <td colspan="2">
			<center>

				<!-- <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal"  name="backid" id="backid">提交</button> -->
                <button type="button" class="btn btn-primary"  id="savea"  >确定</button>
				<button type="button" class="btn btn-primary" onclick="javascript:history.go(-1);">返回</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>