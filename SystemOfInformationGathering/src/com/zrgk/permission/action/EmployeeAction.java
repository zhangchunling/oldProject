package com.zrgk.permission.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zrgk.permission.model.Emp_role;
import com.zrgk.permission.model.Employee;
import com.zrgk.permission.model.ExportEmployee;
import com.zrgk.permission.model.Role;
import com.zrgk.permission.service.EmployeeServiceInter;
import com.zrgk.utils.ExportExcel;

public class EmployeeAction extends ActionSupport{//继承ActionSupport成为action类
	private EmployeeServiceInter employeeService;//依赖注入：声明一个存service层接口的属性
	private String resultpath;
	private Employee employee;
	private List<Employee> list;
	private Role role;
	private String strs;//接收面传来的自动按name拼接的角色字符串，拆开后居然有空格？？？
	private Emp_role empRole;
	private Integer msg;
	private Integer deleteById;//存点右边删除时的id
	private String employeeStrValue;//存选中删除传来的拼接字符串
	private int employeekey;//标识，防止返回本想查所有，而按名字查询了
	private int editId;		//点编辑时传id
	private List listPage=new ArrayList();//用于存页数	
	private int currentFirstPage;//用当前页计算出的每5页的第一页
	private int plus5;//之所以要将currentPage加5存变量里,是因jsp页面currentPage直接加5;
	private int minus5;//之所以要将currentPage加5存变量里,是因jsp页面currentPage直接减5;
	private String employeeName;//异步判断是否重名时付来的名字
	//1.查询所有员工
	public String queryEmployee(){
		if(employeekey==1){
			employee.setEmpName("");
			employeekey=0;
		}
		//根据employee查回总的数条数
		int nums=employeeService.totalSize(employee);
		
		//分页
		if(employee!=null){
			
			//将总的条数据塞进page对象时，就自动算出了总页数。Employee对象继承了Page对象
			employee.setTotalSize(nums);//【【其实可以直接用查回的人员list.size()得到总条数，跟后一句代码换个位置】】			
			//查询所有员工
			list=employeeService.queryEmployee(employee);	
			if(list.size()==0){
				employee.setCurrentPage(1);
				msg=0;
			}else{
				msg=1;
			}
			//处理数字的页数
			listPage.clear();//清空上一次的
			//将页数放在一个list里，用于在页面循环使用	
			currentFirstPage=(((employee.getCurrentPage()-1)/5+1)-1)*5+1;
			//处理点上5页时的传的参		
			if(currentFirstPage>=(((employee.getTotalPage()-1)/5+1)-1)*5+1){
				plus5=currentFirstPage;
				for(int i=currentFirstPage;i<=employee.getTotalPage();i++){			
					listPage.add(i);
				}
			}else{
				plus5=currentFirstPage+5;
				for(int i=currentFirstPage;i<currentFirstPage+5;i++){			
					listPage.add(i);
				}
			}
			//处理下5页时传的参	
			if(currentFirstPage>5){
				minus5=currentFirstPage-5;
			}else{
				minus5=1;
			}
		}		
		
		//跳转路径
		resultpath="/User/index.jsp";
		return "gotojsp";
	}
	
	//2.插入员工——插入中间表
	public String insertEmployee(){
		//1.插入人员表
		employee.setStrRole(strs);//将拼接字符串赋给人员对象
		employeeService.insertEmployee(employee);
		//2.插入人员—角色中间表
		
		//得到当前插入人员的id，也就是最大eid
		int maxEid=employeeService.getMaxId();
		
		//拆角色字符串
		String[] roleArray=strs.split(",");	
		
		//然后插入中间表，还有当前人员id
		for(int i=0;i<roleArray.length;i++){
			int r=Integer.parseInt(roleArray[i].trim());//拆分角色的拼接字符串，居然有空格？？			
			//【这儿要new，不new直接用上面的属性会报空，而new的前提是要在model层配置它映射文件】
			Emp_role er=new Emp_role();
			er.setRid(r);
			er.setEid(maxEid);
			employeeService.insertEmpRole(er);
		}
		resultpath="employee_queryEmployee";//【千万别加斜杠：/】
		return "gotoaction";		
			
	}
	
	//3.编辑人员信息
	public String editEmployee(){
		/*//得到页面传来的要编辑人员的id
		String  strId=ServletActionContext.getRequest().getParameter("editId");
		//解析：页面传来的全都是字符串		
		int eid=Integer.parseInt(strId.trim());	*/
		//根据Id查回员工
		employee=employeeService.queryEmpById(editId);
		/*下面之所以要赋空，是因为：
		如果不赋空，则点编辑更新跳回主页面之前走的查询所有人员信息时，就会按这个名字查询了*/
		//employee.setEmpName(null);
		//最后还要查角色，在角色action里跳转
		resultpath="role_editQueryAll";//【千万别加斜杠：/】
		return "gotoaction";
	}
	
	//4.修改人员信息:中间表需要先删除再添加 来实现更新，因为一个人员对应多个角色
	public String updateEmployee(){
		//1.更新人员表
		employee.setStrRole(strs);
		employeeService.updateEmployee(employee);
		//2.【更新中间表：先删除，再插入】
		employeeService.deleteEmp_role(editId);
		//拆角色字符串
		String[] roleArray=strs.split(",");			
		//然后插入中间表，还有当前人员id
		for(int i=0;i<roleArray.length;i++){
			int r=Integer.parseInt(roleArray[i].trim());//拆分角色的拼接字符串，居然有空格？？			
			//【这儿要new，不new直接用上面的属性会报空，而new的前提是要在model层配置它映射文件】
			Emp_role er=new Emp_role();
			er.setRid(r);
			er.setEid(editId);
			employeeService.insertEmpRole(er);
		}
		/*下面之所以要赋空，是因为：
		如果不赋空，则点编辑更新跳回主页面之前走的查询所有人员信息时，就会按这个名字查询了*/
		employee.setEmpName(null);
		resultpath="employee_queryEmployee";//【千万别加斜杠：/】
		return "gotoaction";
	}
	
	//5.查看新添加人员的登录名是否重复
	public String checkAjax() throws IOException{
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();//out打印的字符串可在js里用data得到
		
		list=employeeService.queryEmployee(null);
		for(Employee emp:list){
			
			if((employee.getEmpName()).equals(emp.getEmpName())){
				out.print("1");
				break;
			}
		}		
		 out.close();
		 return  null;	
	}
	//编加人员时，查看登录名是否重复
	public String checkEditAjax() throws IOException{
		
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();//out打印的字符串可在js里用data得到
		list=employeeService.queryEmployee(null);
		//去除自身
		for(int i=0;i<list.size();i++){	
			if(employee.getEid()==list.get(i).getEid()){
				list.remove(i);
				break;
			}
			
		}
		
		//判断是否重名
		for(Employee emp:list){
			if((emp.getEmpName().equals(employeeName))){
				out.print("1");
				break;
			}
		}		
		 out.close();
		 return  null;	
	}
	//按Id删除
	public String deleteById(){
		//1.删除人员表
		employeeService.deleteEmployee(deleteById);
		//2.删除中间表
		employeeService.deleteEmp_role(deleteById);
		
		resultpath="employee_queryEmployee";//【千万别加斜杠：/】
		return "gotoaction";
	}	
	
	//循环删除
	public String deleteEmployeeCircle(){
		String[] strs = employeeStrValue.split(",");
		for(int i=0;i<strs.length;i++){
			int delId=Integer.parseInt(strs[i]);
			employeeService.deleteEmployee(delId);//删除人员表
			employeeService.deleteEmp_role(delId);//删除中间表
		}
		resultpath="employee_queryEmployee";//【千万别加斜杠：/】
		return "gotoaction";
	}

	//导出Execl
	public String exportEmployee() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
        response.setContentType("octets/stream");
        response.addHeader("Content-Disposition", "attachment;filename=employee.xls");
        
		//创建导出Excel对象，来源工具包
		ExportExcel<ExportEmployee> ex=new ExportExcel<ExportEmployee>();
		 //声明一个数组  里面数据是设置导出的excel头部分（列名）
		String[] headers = { "人员名称", "性别", "真实姓名", "生日", "地址","邮箱","电话","状态","角色"};
	    List<ExportEmployee> listEmpExcel=new ArrayList<ExportEmployee>();
		if(employeeStrValue!=null&&!"".equals(employeeStrValue.trim())){
	    	String[] strArray=employeeStrValue.split(",");
	    	for(int i=0;i<strArray.length;i++){
	    		Employee m=employeeService.queryEmpById((Integer.parseInt(strArray[i].trim())));
	    		ExportEmployee exm=new ExportEmployee();
	    		if(m.getE_state()==1){
	    			exm.setE_state("有效");
	    		}
	    		if(m.getE_state()==0){
	    			exm.setE_state("无效");
	    		}
	    		exm.setAddress(m.getAddress());
	    		exm.setBirthday(m.getBirthday());
	    		exm.setEmail(m.getEmail());
	    		exm.setEmpName(m.getEmpName());
	    		exm.setRelName(m.getRealName());
	    		if(m.getSex()==1){
	    			exm.setSex('男');
	    		}
	    		if(m.getSex()==0){
	    			exm.setSex('女');
	    		}
	    		exm.setTel(m.getTel());
	    		exm.setStrRole(m.getStrRole());
	    		listEmpExcel.add(exm);
	    	}
	    }
		//创建输出流
		OutputStream outputStream=response.getOutputStream();
		//导出
		ex.exportExcel(headers, listEmpExcel, outputStream);
		outputStream.close();
		return null;
	}

	
	public EmployeeServiceInter getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeServiceInter employeeService) {
		this.employeeService = employeeService;
	}

	public String getResultpath() {
		return resultpath;
	}

	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getStrs() {
		return strs;
	}

	public void setStrs(String strs) {
		this.strs = strs;
	}

	public Emp_role getEmpRole() {
		return empRole;
	}

	public void setEmpRole(Emp_role empRole) {
		this.empRole = empRole;
	}

	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	public Integer getDeleteById() {
		return deleteById;
	}

	public void setDeleteById(Integer deleteById) {
		this.deleteById = deleteById;
	}

	public String getEmployeeStrValue() {
		return employeeStrValue;
	}

	public void setEmployeeStrValue(String employeeStrValue) {
		this.employeeStrValue = employeeStrValue;
	}

	public List getListPage() {
		return listPage;
	}

	public void setListPage(List listPage) {
		this.listPage = listPage;
	}

	public int getCurrentFirstPage() {
		return currentFirstPage;
	}

	public void setCurrentFirstPage(int currentFirstPage) {
		this.currentFirstPage = currentFirstPage;
	}

	public int getPlus5() {
		return plus5;
	}

	public void setPlus5(int plus5) {
		this.plus5 = plus5;
	}

	
	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public void setEmployeekey(int employeekey) {
		this.employeekey = employeekey;
	}

	public int getMinus5() {
		return minus5;
	}

	public void setMinus5(int minus5) {
		this.minus5 = minus5;
	}

	public Integer getEmployeekey() {
		return employeekey;
	}

	public void setEmployeekey(Integer employeekey) {
		this.employeekey = employeekey;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
 		
		
}
