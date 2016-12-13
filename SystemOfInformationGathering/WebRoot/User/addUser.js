//1.失去焦点：查验登录名
   function checkName(){
   		var username=document.getElementById("employee.empName").value;
   		if(""==username.trim()){
   			var a=document.getElementById("ename");
   			a.innerHTML="登录名不能为空S";
   			a.className="red";
   		}else if(username.length>10){
   			var str=username.substring(0,10);
			document.getElementById("employee.empName").value=str;
			var b=document.getElementById("ename");
			b.innerHTML="登录名要小于10个字符，已自动截取了前10位";
			b.className="yellow";			
   		}else{
   			var c=document.getElementById("ename");
   			//异步判断用户是否存在			
   		   	var today = new Date();   		 
   			 $.ajax({   //后面跟一个时间的参数是为了防止有时候不提交的问题【冲破缓存，也可跟个随机数】	
   				 url:"employeePackage/employee_checkAjax.action?time="+today.getTime(),//访问路径
   				 type:"post",//提交方式
   				 data:"employee.empName="+username,//username是action类里的属性
   				 success:function(data){//回调函数：返回的是json串,out.print("")里的字符串
   					 if("1"==data){   						 
   						c.innerHTML="登录名已存在,请重新输入";
   						c.className="red";  
   						document.getElementById("employee.empName").value="";
   					 }else{
   			   			c.innerHTML="OK(^_^) ";
   			   			c.className="green";
   					 }
   				    username = data.employee.empName;//得到actoin类里的属性值    			     
   				  }
   			 });
   		}  		
   		
	}
   
   /*//获得焦点
   function clearName(){
   		document.getElementById("employee.empName").value="";   		
   }*/
   
   //2.校验密码
 /*  function warn(){
   		document.getElementById("employee.password").value="";
   }
   */
   function checkPassword(){
   		var pd=document.getElementById("employee.password").value;
   		if(pd.length==0){
   			var b=document.getElementById("passwordSpan");
   			b.innerHTML="密码不能为空";
   			b.className="red";
   		}else if(pd.length<6){
   			var c=document.getElementById("passwordSpan");
   			c.innerHTML="密码要大于6位";
   			c.className="red";
   		}else if(pd.length>10){
   			var d= document.getElementById("passwordSpan");
   			var str=pd.substring(0,10);
   			document.getElementById("employee.password").value=str;
   			d.innerHTML="密码要小于10位，已自动截取前10位";
   			d.className="yellow";
   		}else{
   			var e=document.getElementById("passwordSpan");
   			e.innerHTML="OK(^_^)";
   			e.className="green";
   		}
   }
  
   //3.检验真实姓名
   function checkRealName(){
   		var username=document.getElementById("employee.realName").value;
   		var a=document.getElementById("reName");
   		if(""==username.trim()){   			
   			a.innerHTML="真实姓名不能为空";
   			a.className="red";
   		}else if(username.length>10){   			
   			var str=username.substring(0,10);
			document.getElementById("employee.realName").value=str;
			a.innerHTML="要小于10个字符，已自动截取前10位";
			a.className="yellow";
   		}else{
   			a.innerHTML="OK (^_^)";
   			a.className="green";
   		}
   }
   /*function writedown(){
   		document.getElementById("employee.realName").value="";
   }*/
  
   //校验手机号
   function checkTel(){
	  var tel = document.getElementById("employee.tel").value;	//获得输入框的值
	  var telSpan=document.getElementById("telSpan");			//获得span标签	 
	 //var telReg = /^(130|131|132|133|134|135|136|137|138|139)\d{8}$/;//手机号正则表达式
	 var telReg=/^1(3|5|8)\d{9}$/;
	 // var telReg =/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	  if(tel==null||tel==""){
		  telSpan.innerHTML="留个电话吧"; 
			  telSpan.className="red";
	  }
	  //截取11位
	  if(tel.length>11){
		  str=tel.substring(0,11);
		  document.getElementById("employee.tel").value=str;
		  if(telReg.test(str)){
			  telSpan.innerHTML="OK (^_^)";
			  telSpan.className="green";		  
		  }else{
			  telSpan.innerHTML="电话号输入有误!";
			  telSpan.className="red";
			  document.getElementById("employee.tel").value="";
		  }
	  }else if(tel.length==11){
		  if(telReg.test(tel)){
			  telSpan.innerHTML="OK (^_^)";
			  telSpan.className="green";		  
		  }else{
			  telSpan.innerHTML="电话号输入有误!";
			  telSpan.className="red";
			  document.getElementById("employee.tel").value="";
		  }
	  }else if(tel.length>0&&tel.length<11){
		  strs="";
		  var s=11-tel.length;
		  for(var i=0;i<s;i++){
			  tel=tel+"1";
		  }		  
		 // document.getElementById("employee.tel").value="";
		  document.getElementById("employee.tel").value=tel;	  
		  
		  if(telReg.test(tel)){
			  telSpan.innerHTML="电话号位数不足,自动以1补上!";
			  telSpan.className="red";		  
		  }else{
			  telSpan.innerHTML="电话号输入有误!";
			  telSpan.className="red";
			  document.getElementById("employee.tel").value="";
		  }
		  
	  }
	  
   }
   //校验地址
   function checkAddress (){
	   var addressSpan=document.getElementById("addressSpan");//得到Span标签
	   var address=document.getElementById("employee.address").value;//得到输入框的地址
	   if(address==""){
		   addressSpan.innerHTML="留个地址吧";
		   addressSpan.className="red";
	   }else if(address.length>40){
		   addressSpan.innerHTML="要小于40个字符，已自动截取前40位";
		   addressSpan.className="yellow";
	   }else{
		   addressSpan.innerHTML="OK (^_^)";
		   addressSpan.className="green";
	   }
   }
   //4.校验邮箱
   function emailOnfocus(){
   		document.getElementById("employee.email").value="";
	};

	function emailOnblur(){
		var password=document.getElementById("employee.email").value;
   		var a=document.getElementById("emailSpan");
   		//邮箱正则表达式
	    var emailReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/; 
	    if(password==null||password==""){
	    	a.innerHTML="留个邮箱吧";
	    	a.className="red";
	    }else if ( emailReg.test(password) ) {
	    	a.innerHTML="OK (^_^)";
   			a.className="green";
	    }else {
	    	a.innerHTML="email格式不正确！";
	    	a.className="RED";
	    	document.getElementById("employee.email").value="";
	    }
	}
   
   
    
    //5.判空
    function checkRole() {    
		//校验登录名
		var username=document.getElementById("employee.empName").value;
   		if(""==username.trim()){
   			var a=document.getElementById("ename");
   			a.innerHTML="登录名不能为空11111";
   			a.className="red";
   			//document.getElementById("employee.empName").focus();该方法还有待解决
   			return false;
   		}
	
   		//校验密码
   		var passw=document.getElementById("employee.password").value;
   		if(passw==""){
   			var a=document.getElementById("passwordSpan");
   			a.innerHTML="密码不能为空";
   			//document.getElementById("employee.password").focus();
   			a.className="red";
   			return false;
   		}else if(passw.length<6){
   			var p=document.getElementById("passwordSpan");
   			p.innerHTML="密码不能少于6位";
   			p.className="red";
   			return false;
   		}
   		//校验真实姓名
   		var realName=document.getElementById("employee.realName").value;
   		if(""==realName.trim()){
   			var a=document.getElementById("reName");
   			a.innerHTML="真实姓名不能为空";
   			a.className="red";
   			return false;
   		}
   		//校验邮箱
   	/*	var email=document.getElementById("employee.email").value;		
	   if(email==null||email==""){
	    	a.innerHTML="Email格式不能为空！";
	    	a.className="RED";
	    	return false;
	    }*/
	   
   		//校验角色	
		var roleStr=document.getElementsByName("strs");
		var s=0;
		for(var i=0;i<roleStr.length;i++){
			if(roleStr[i].checked){
				s=1;
			}
		}
		if(s>0){			
			alert("添加成功！");
			document.getElementById("formId").submit();
			
		}else{
			alert("请选择角色");			
		}
   		
	}
    