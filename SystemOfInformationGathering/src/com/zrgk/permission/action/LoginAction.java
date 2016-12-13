package com.zrgk.permission.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.JsonChildMenu;
import com.zrgk.permission.model.JsonParentMenu;
import com.zrgk.permission.model.Menu;
import com.zrgk.permission.service.EmployeeServiceInter;

public class LoginAction extends ActionSupport{
	private EmployeeServiceInter employeeService;
	private Employee employee;	
	private String resultpath;	//路径
	private String getKey;		//验证码
	private List<Employee> list;//存查回的人员
	private String subSpan;	//声明的存登录提示信息的属性
	private List<Menu> urlList;//存登录后用户对应的菜单
	private String welName;//存欢迎名字
	private String json;//json串
	
	//登录
	public String login(){
		//得到request
		HttpServletRequest request=ServletActionContext.getRequest();		
		//得到用户输入的验证码
		String currentKey=request.getParameter("getKey");
		//得到验证码类里生成的存在session里的验证码
		String systemKey=(String) request.getSession().getAttribute("randomString");
		//如果验证码正确，去查用户和密码
		String keys=null;
		if(systemKey!=null&&systemKey.equals(currentKey)){
			Employee em=employeeService.login(employee);//查找数据库看看是否有该登录人
			
			if(em!=null){		
				HttpSession session = ServletActionContext.getRequest().getSession();
				
				session.setAttribute("user", em);//将登录人赋进session
				session.setAttribute("userInfo",em.getEmpName());//将登录名赋进session
				session.setAttribute("userId", em.getEid());//id
				employee=em;
				this.success();
				resultpath="login_success";
				keys="gotoaction"; 
			}else{
				subSpan="用户名或密码错误!";
				resultpath="/login.jsp";
				keys="gotojsp";  
			}
		}else{
			
			subSpan="验证码错误！";
			resultpath="/login.jsp";
			keys="gotojsp";  
		}		
		return keys;
	}

	//查询菜单
	public String success(){
		HttpSession session = ServletActionContext.getRequest().getSession();		
		welName=(String) session.getAttribute("userInfo");//获取登录名
		
		json="";
		urlList=employeeService.queryLoginMenu(employee.getEid());
		List<JsonParentMenu> parentList=new ArrayList<JsonParentMenu>();
		for(Menu pm:urlList){
			if(pm.getParentId()==1){
				JsonParentMenu jpm=new JsonParentMenu();//父菜单对象
				List<JsonChildMenu> childList=new ArrayList<JsonChildMenu>();
				jpm.setText(pm.getMenuName());//给父菜单赋名字
				
				for(Menu cm:urlList){
					//这里判断一定要用equals方法，==不行
					if(cm.getParentId().equals(pm.getMid())){
						JsonChildMenu jcm=new JsonChildMenu();//子菜单对象
						jcm.setId(cm.getMid());
						jcm.setText(cm.getMenuName());
						jcm.setHref(cm.getUrl());
						childList.add(jcm);//将子菜单赋进子菜单集合
					}					
				}
				jpm.setItems(childList);//将子菜单集合赋进父菜单
				parentList.add(jpm);//将父菜单赋进父菜单集合
			}
		}
		json="[{id:'1',menu:"+JSONArray.fromObject(parentList).toString()+"}]";
		
		resultpath="/index.jsp";
		return "gotojsp";  
	}
	
	//注销
	public String quit(){		
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		session.removeAttribute("userInfo");
		session.removeAttribute("user");
		session.removeAttribute("userId");	
		
		resultpath="/login.jsp";
		return "gotojsp"; 
	}

	public EmployeeServiceInter getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeServiceInter employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getResultpath() {
		return resultpath;
	}

	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}

	public String getGetKey() {
		return getKey;
	}

	public void setGetKey(String getKey) {
		this.getKey = getKey;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public String getSubSpan() {
		return subSpan;
	}

	public void setSubSpan(String subSpan) {
		this.subSpan = subSpan;
	}

	public List<Menu> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<Menu> urlList) {
		this.urlList = urlList;
	}

	public String getWelName() {
		return welName;
	}

	public void setWelName(String welName) {
		this.welName = welName;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	
	
}
