<%@page import="java.text.SimpleDateFormat"%>
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
    <title>新增约稿</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" tHttpSession session=ServletActionContext.getRequest().getSession();ype="text/css" href="<%=basePath %>/Css/style.css" />
    <script type="text/javascript" src="<%=basePath %>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath %>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath %>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath %>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath %>/Js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
 

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
    <script>
     	
     
      
	 var flag1=false;
	 var flag2=false;
	 var flag3=false;
	 var flag4=false;
	 $('#mname').focus();
	   $(function () {
		    $('#mname').blur(function(){
	   			if($('#mname').val().length==0){
	    			$('#mnameMsg').html("<img src='Images/warning.png'/>刊物名不能为空.");
	    		}else if($('#mname').val().length>20){
	    			$('#mnameMsg').html("<img src='Images/warning.png'/>长度不能大于20.");
	    		}
    			else{
    				$('#mnameMsg').html("<img src='Images/ok.PNG'/>");
    			flag1=true;
    			}
   	 			});
   	 			
             $("#mbknum").blur(function () {
                var textReg = /^[0-9]+$/;//必须为中文或数字
                if ($("#mbknum").val().trim()=="") {
               		$('#bkNum').html("<img src='Images/warning.png'/>期号不能为空.");
                } else if(textReg.test($("#mbknum").val())) {
                	$('#bkNum').html("<img src	='Images/ok.PNG'/>");
                	flag3=true;
                }else{
               		$('#bkNum').html("<img src='Images/warning.png'/>期号只能为数字.");
                	
                }
            });
            
                $("#minfo").blur(function () {
                if ($("#minfo").val().trim()=="") {
               		$('#minfoMsg').html("<img src='Images/warning.png'/>刊物要求信息不能为空.");
                } else if($("#minfo").val().length>10) {
                	$('#minfoMsg').html("<img src	='Images/ok.PNG'/>");
                	flag4=true;
                }else{
               		$('#minfoMsg').html("*最少输入10个字符.");
                }
            });
            
                $("#d411").blur(function () {
                if ($("#d411").val().trim()=="") {
               		$('#dateMsg').html("<img src='Images/warning.png'/>请选择截止日期.");
                } else {
                	$('#dateMsg').html("<img src='Images/ok.PNG'/>");
                	flag2=true;
                }
            });
            
        });
	
	 
		
     
       function saveSub(){
       	var alls=document.getElementsByName("check");
			var ids=new Array();
			var mony="";
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
					mony=mony+alls[i].value+",";
				}		
			}
			mony = mony.substring(0,mony.length-1);
			document.getElementById("mony").value=mony;
			if(flag1&&flag3&&flag4&&flag2){
				document.getElementById("saveManu").submit();
				}
			else if(ids.length<=0){
				alert("*为必填项，请完善！");
			}
		}
    
    
</script>
</head>
<body>
<form id="saveManu" action="<%=basePath %>Manu_insert.action?mu.currentPage=1" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">刊物名</td>
            <td><input type="text" id="mname" name="mu.mname"/><span id="mnameMsg" style=" color: red; font-size: 13px" >*</span></td>
             
        </tr>
        <tr>
            <td class="tableleft">刊物期号</td>
            <td><input type="text" id="mbknum" name="mu.mbknum"/><span id="bkNum" style=" color: red; font-size: 13px" >*</span></td>
        </tr>
        <tr>
            <td class="tableleft" >刊物要求信息</td>
            <td><textarea  style="width: 600px; height: 300px;" name="mu.minfo" id="minfo" class="control-row4 input-large"></textarea>
            <div id="minfoMsg" style="color: red;font-size: 5px;float: right;" >*最少输入10个字符</div>
            </td>
        </tr>
        <tr>
        	
            <td class="tableleft">约稿截止时间</td>
            <td><input type="text" id="d411" name="mu.mendTime" onFocus="WdatePicker({skin:'whyGreen',minDate:'<%
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            out.print(sdf.format(date));
            %>',maxDate:'2020-10-01'})"/><span id="dateMsg" style=" color: red; font-size: 13px" >*</span></td>
        </tr>
       <tr>
         <!-- 复选框  异步查询 USER表用户 -->
            <td class="tableleft">约稿人</td>
            <td>
            <c:forEach var="e" items="${emps}" varStatus="sss">
            <input type="checkbox" name="check" value="${e.eid }"/>${e.realName }
            </c:forEach>
            </td>
        </tr> 
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" type="button" onclick="saveSub();">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-primary" name="backid" onclick="javascript:window.history.go(-1)">返回列表</button>
            </td>
        </tr>
    </table>
   			 <input type="hidden" id="mony" name="users"/>
</form>
</body>
</html>