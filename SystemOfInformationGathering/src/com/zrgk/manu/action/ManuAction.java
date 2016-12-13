package com.zrgk.manu.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.zrgk.manu.bean.Manu;
import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.manu.service.MyManuServiceInter;
import com.zrgk.manu.service.PageFileServiceInter;
import com.zrgk.manu.util.ExportExcel;
import com.zrgk.manu.util.Manu2Excel;
import com.zrgk.manu.util.PageFile2Excel;
import com.zrgk.permission.model.Employee;
import com.opensymphony.xwork2.ActionSupport;

public class ManuAction  extends ActionSupport{
	private String resultpath;
	private MyManuServiceInter myMuService;
	private PageFileServiceInter pfService;
	private Manu mu;
	private List<Manu> mus;
	private List<PageFile> pfs;
	private Manu_User muu;
	private String acceptState; //判断是哪个界面 用于指定不同的查询方法
	private String exportExcelType;
	private String EXCELINFO="约稿信息";
	private String muuId;
	private PageFile pageFile;
	private String subMsg;
	private String updateState;
	private String delManuIds;
	private List<Employee> emps;
	private String users;//接收要发布给谁的集
	private String check;
	private String queryNull;
	private String flag;
	private String exportType;
	//一审通过方法
	public String sub1Ok(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		mu=new Manu();
		mu.setMid(pageFile.getPf_mu_id());//重置属性 只要稿件所属约稿
		muu=new Manu_User();
		muu.setUuid(emp.getEid());//暂时写死
		muu.setMid(pageFile.getPf_mu_id());
		pageFile=new PageFile();//重置
		this.myMuService.updateState3Sub(muu);
		//修改约稿状态字段
		this.myMuService.updateMstateByMid(mu, 2);
		this.resultpath="Manu_queryWaitPageFile";
		return "goaction";
	}
	//一审不通过
	public String sub1No(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		mu=new Manu();
		mu.setMid(pageFile.getPf_mu_id());//重置属性 只要稿件所属约稿
		muu=new Manu_User();
		muu.setUuid(emp.getEid());//暂时写死
		muu.setMid(pageFile.getPf_mu_id());
		pageFile=new PageFile();//重置
		this.myMuService.updateStateNosub(muu);
		//修改约稿状态字段
		this.resultpath="Manu_queryWaitPageFile";
		//修改 稿件 状态 为未通过审核
		this.resultpath="Manu_queryWaitPageFile";
		return "goaction";
	}
	
	//二审通过的方法
	public String sub2Ok(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		mu=new Manu();
		mu.setMid(pageFile.getPf_mu_id());//重置属性 只要稿件所属约稿
		muu=new Manu_User();
		muu.setUuid(emp.getEid());//暂时写死
		muu.setMid(pageFile.getPf_mu_id());
		pageFile=new PageFile();//重置
		this.myMuService.updateState4Sub(muu);
//		//修改约稿状态字段
//		this.myMuService.updateMstateByMid(mu, 3);
//		this.resultpath="Manu_queryWaitPageFile";
		
		
		this.resultpath="Manu_queryOkedPageList";
		return "goaction";
	}
	
	//二审不通过的方法
	public String sub2No(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		muu=new Manu_User();
		muu.setUuid(emp.getEid());
		muu.setMid(pageFile.getPf_mu_id());
		pageFile=new PageFile();//重置
		this.myMuService.updateStateNoOk(muu);
		//修改约稿状态字段
		//修改 稿件 状态 为未通过2此审核
		this.resultpath="Manu_queryOkedPageList";
		return "goaction";
	}
	//查询已审核稿件列表
	public String queryOkedPageList(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(flag!=null&&mu!=null&&session.getAttribute("spf")!=null){
			
			if(!"".equals(pageFile.getPf_name())||!"".equals(pageFile.getPf_style())||!"".equals(pageFile.getPf_type())
			){
			int tempcurrent=pageFile.getCurrentPage();
			pageFile=(PageFile)session.getAttribute("spf");
			pageFile.setCurrentPage((tempcurrent));
			session.setAttribute("spf", pageFile);
			}
		}else{
			session.setAttribute("spf", pageFile);
		}
		if(pageFile==null){
			pageFile=new PageFile();
			pageFile.setCount(this.pfService.queryOkedPageFile(pageFile,"true").size());
			pfs=this.pfService.queryOkedPageFile(pageFile,null);
		}else{
			PageFile spf=(PageFile)session.getAttribute("spf");
			spf.setCount(this.pfService.queryOkedPageFile(spf,"true").size());
			pfs=this.pfService.queryOkedPageFile(spf,null);
		}
		
//		Integer mid=pageFile.getPf_mu_id();
//		if(!"".equals(pageFile.getPf_style())||!"".equals(pageFile.getPf_type())
//				||!"".equals(pageFile.getPf_name())
//				){
//			pageFile.setCount(this.pfService.queryOkedPageFile(pageFile,"true").size());//条数
//			pfs=this.pfService.queryOkedPageFile(pageFile,null);
//		}else{
//			pageFile=new PageFile();
//			pageFile.setCount(this.pfService.queryOkedPageFile(pageFile,"true").size());
//			pfs=this.pfService.queryOkedPageFile(pageFile,null);
//		}
			
		this.resultpath="Manuscripts/filelist2.jsp";
		return "gojsp";
	}
	//查询待审核稿件列表
	public String queryWaitPageFile(){
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(flag!=null&&mu!=null&&session.getAttribute("spf")!=null){
			
			if(!"".equals(pageFile.getPf_name())||!"".equals(pageFile.getPf_style())||!"".equals(pageFile.getPf_type())
			){
			int tempcurrent=pageFile.getCurrentPage();
			pageFile=(PageFile)session.getAttribute("spf");
			pageFile.setCurrentPage((tempcurrent));
			session.setAttribute("spf", pageFile);
			}
		}else{
			session.setAttribute("spf", pageFile);
		}
		Integer mid=pageFile.getPf_mu_id();
		if(pageFile==null){
			pageFile=new PageFile();
			pageFile.setCount(this.pfService.queryWaitPageFile(pageFile,"true").size());
			pfs=this.pfService.queryWaitPageFile(pageFile,null);
		}else{
			PageFile spf=(PageFile)session.getAttribute("spf");
			spf.setPf_mu_id(mid);
			spf.setCount(this.pfService.queryWaitPageFile(spf,"true").size());
			pfs=this.pfService.queryWaitPageFile(spf,null);
		}
			
		this.resultpath="Manuscripts/filelist.jsp";
		return "gojsp";
	}
	
	
	//查询未发布
	public String queryNpList(){
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(flag!=null&&mu!=null&&session.getAttribute("mymanu")!=null){
			
			if(!"".equals(mu.getMname())||!"".equals(mu.getMstartTime())||mu.getMbknum()!=null
				||!"".equals(mu.getMendTime())){
			int tempcurrent=mu.getCurrentPage();
			mu=(Manu)session.getAttribute("mymanu");
			mu.setCurrentPage((tempcurrent));
			session.setAttribute("mymanu", mu);
			}
		}else{
			session.setAttribute("mymanu", mu);
		}
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(mu==null){
			mu=new Manu();
			mu.setMmaster(emp.getEmpName());
			mu.setCount(myMuService.queryManu(mu,0,"true").size());
			mus=myMuService.queryManu(mu,0,null);
		}else{
			Manu mymanu=(Manu)session.getAttribute("mymanu");
			mymanu.setMmaster(emp.getEmpName());
			mymanu.setCount(myMuService.queryManu(mymanu,0,"true").size());
			mus=myMuService.queryManu(mymanu,0,null);
		}
		this.resultpath="Manuscripts/listinfo.jsp";
		return "gojsp";
	}
	//查询待审核
	public String queryWaitList(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(flag!=null&&mu!=null&&session.getAttribute("mymanu")!=null){
			
			if(!"".equals(mu.getMname())||!"".equals(mu.getMstartTime())||mu.getMbknum()!=null
				||!"".equals(mu.getMendTime())){
			int tempcurrent=mu.getCurrentPage();
			mu=(Manu)session.getAttribute("mymanu");
			mu.setCurrentPage((tempcurrent));
			session.setAttribute("mymanu", mu);
			}
		}else{
			session.setAttribute("mymanu", mu);
		}
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(mu==null){
			mu=new Manu();
			mu.setMmaster(emp.getEmpName());
			mu.setCount(myMuService.queryManu(mu,1,"true").size());
			mus=myMuService.queryManu(mu,1,null);
		}else{
			
			Manu mymanu=(Manu)session.getAttribute("mymanu");
			
			mymanu.setMmaster(emp.getEmpName());
			mymanu.setCount(myMuService.queryManu(mymanu,1,"true").size());
			mus=myMuService.queryManu(mymanu,1,null);
		}
		this.resultpath="Manuscripts/waitManuList.jsp";
		return "gojsp";
	}
	//查询已审核 2审
	public String queryOkedList(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(flag!=null&&mu!=null&&session.getAttribute("mymanu")!=null){
			
			if(!"".equals(mu.getMname())||!"".equals(mu.getMstartTime())||mu.getMbknum()!=null
				||!"".equals(mu.getMendTime())){
			int tempcurrent=mu.getCurrentPage();
			mu=(Manu)session.getAttribute("mymanu");
			mu.setCurrentPage((tempcurrent));
			session.setAttribute("mymanu", mu);
			}
		}else{
			session.setAttribute("mymanu", mu);
		}
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(mu==null){
			mu=new Manu();
			mu.setMmaster(emp.getEmpName());
			mu.setCount(myMuService.queryManu(mu,2,"true").size());
			mus=myMuService.queryManu(mu,2,null);
		}else{
			
			
			Manu mymanu=(Manu)session.getAttribute("mymanu");
			mymanu.setMmaster(emp.getEmpName());
			mymanu.setCount(myMuService.queryManu(mymanu,2,"true").size());
			mus=myMuService.queryManu(mymanu,2,null);
		}
		this.resultpath="Manuscripts/okedManuList.jsp";
		return "gojsp";
	}

	//查看未审核稿件
	public String queryPageFileById(){
		pageFile=this.pfService.queryPageFileByID(pageFile);
		this.resultpath="Manuscripts/look2.jsp";
		return "gojsp";
	}
	//查看一次审核后的稿件
	public String queryOkedPageFileById(){
		pageFile=this.pfService.queryPageFileByID(pageFile);
		this.resultpath="Manuscripts/look3.jsp";
		return "gojsp";
	}
	//修改发布状态
	public String updateMstate(){
		boolean flag=this.myMuService.updateMstateByMid(mu,1);
		if(flag){
			updateState="发布成功！";
		}else{
			updateState="发布失败，约稿已发布！";
		}
		this.resultpath="Manu_queryNpList";
		mu=new Manu();
		return "goaction";
	}
	//查看详情
	public String queryManuById(){
		mu=myMuService.queryManuById(mu);
		resultpath="Manuscripts/look.jsp";
		return "gojsp";
		
	}
	//删除约稿
	public String delManusById(){
		boolean flag=false;
		
		if(delManuIds==null||"".equals(delManuIds.trim())){
			flag=this.myMuService.updateMstateByMid(mu,-1);
		}
		//批量删除 
		else{
			String ids[]=delManuIds.split(",");
			for(int i=0;i<ids.length;i++){
				mu=new Manu();
				mu.setMid(Integer.parseInt(ids[i]));
				flag=this.myMuService.updateMstateByMid(mu,-1);
			}
		}
		
		if(flag){
			updateState="删除成功！";
		}else{
			updateState="删除失败，约稿已发布！";
		}
		mu=new Manu();//置空对象
		this.resultpath="Manu_queryNpList";
		return "goaction";
	}
	
	public String gotoInsert(){
		mu=new Manu();//置空
		emps=this.myMuService.queryAllEmp();//查询所有用户
		System.out.println(emps.size());
	
		this.resultpath="Manuscripts/addinfo.jsp";
		return "gojsp";
	}
	/**
	 * 新增未发布约稿
	 * @return
	 */
	public String insert(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		mu.setMmaster(emp.getEmpName());//添加发布约稿用户真实姓名
		this.myMuService.addManuAndManu_User(mu,users);
		this.resultpath="Manu_queryNpList";
		this.muu=new Manu_User();
		this.mu=new Manu();//置空
		return "go_redirect_action";
	}
	
	
	public String subMyManu(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(this.myMuService.queryManuIsPf(muu, emp.getEid())){
			muu.setUuid(emp.getEid());//暂时写死
			myMuService.updateState2Sub(muu);
			this.resultpath="MyManu_queryManu";
			return "goaction";
		}else{
			subMsg="这个约稿下没有稿件,无法提交！请选择[编辑我的稿件],新增稿件。";
			this.resultpath="MyManu_queryManu";
			return "goaction";
		}
	}
	
//导出未发布的约稿信息	
	public String exportExcl(){
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.addHeader("Content-Disposition",
                "attachment;filename=Manu.xls");
        ExportExcel<Manu2Excel> ex = new ExportExcel<Manu2Excel>();//实例化导excel工具类
        //声明一个数组  里面数据是设置导出的excel头部分
        String[] headers = { "约稿编号","刊物期号", "刊物名", "约稿信息", "约稿发布时间", "约稿结束时间","发稿人"};
        //声明一个list用来装从数据库查询来的商品信息
        Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
        List<Manu2Excel> dataset = new ArrayList<Manu2Excel>();
        if("all".equals(exportExcelType)){//判断界面传来的value是哪种导出方式
        	List<Manu> list=new ArrayList<Manu>();
        	mu=new Manu();
        	mu.setMmaster(emp.getEmpName());
        		if("wait".equals(exportType)){
        			list=myMuService.queryManu(mu,1,null);
        		}else if("Np".equals(exportType)){
        			list=myMuService.queryManu(mu,0,null);
        		}else if("OK".equals(exportType)){
        			list=myMuService.queryManu(mu,2,null);
        		}
        	for (Manu m : list) {
        		Manu2Excel m2x=new Manu2Excel();
//        		int pfState=p.getPf_state();//获取状态代码
        		//0 未提交 1 未审核 2审核通过、3审核未通过、4未录用 5已录用
//        		String pf_stateStr=pfState==0?"未提交":pfState==1?"未审核":pfState==2?"审核通过":pfState==3?"审核未通过":pfState==4?"未录用":"已录用";
        		m2x.setMid(m.getMid());
        		m2x.setMbknum(m.getMbknum());
        		m2x.setMname(m.getMname());
        		String info=m.getSubMinfo();
        		m2x.setSubMinfo(info);
        		m2x.setMstartTime(m.getMstartTime());
        		m2x.setMendTime(m.getMendTime());
//        		m2x.setMstate(m.getMstate());
        		m2x.setMmaster(m.getMmaster());
        		dataset.add(m2x);
			}
        	
        }
        else{
        	String values[]=exportExcelType.split(",");
			for (String m_id : values) {
				Manu2Excel m2x=new Manu2Excel();
				Manu manu=new Manu();
				mu=new Manu();
				mu.setMid(Integer.valueOf(m_id));file:///C:/Users/Administrator/Desktop/%E4%B8%89%E6%9C%9F%E9%A1%B9%E7%9B%AE/%E4%B8%AD%E8%BD%AF%E9%AB%98%E7%A7%91%E4%BF%A1%E6%81%AF%E9%87%87%E7%BC%96%E7%B3%BB%E7%BB%9F/Manuscripts/listinfo2.html
				manu=this.myMuService.queryManuById(mu);
				//组装需要导出EXCL的pagefile字段
//				int pfState=m.getPf_state();//获取状态代码
//        		//0 未提交 1 未审核 2审核通过、3审核未通过、4未录用 5已录用
//        		String pf_stateStr=pfState==0?"未提交":pfState==1?"未审核":pfState==2?"审核通过":pfState==3?"审核未通过":pfState==4?"未录用":"已录用";
			
				m2x.setMid(manu.getMid());
        		m2x.setMbknum(manu.getMbknum());
        		m2x.setMname(manu.getMname());
        		m2x.setSubMinfo(manu.getSubMinfo());
        		m2x.setMstartTime(manu.getMstartTime());
        		m2x.setMendTime(manu.getMendTime());
//        		m2x.setMstate(manu.getMstate());
        		m2x.setMmaster(manu.getMmaster());
        		System.out.println("--end"+m2x);
        		dataset.add(m2x);
			}
        }
        OutputStream out;
		try {
			out = resp.getOutputStream();
			ex.exportExcel(EXCELINFO,headers, dataset, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public String getResultpath() {
		return resultpath;
	}

	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}


	public MyManuServiceInter getMyMuService() {
		return myMuService;
	}



	public void setMyMuService(MyManuServiceInter myMuService) {
		this.myMuService = myMuService;
	}



	public Manu getMu() {
		return mu;
	}
	public void setMu(Manu mu) {
		this.mu = mu;
	}



	public List<Manu> getMus() {
		return mus;
	}


	public void setMus(List<Manu> mus) {
		this.mus = mus;
	}

	public Manu_User getMuu() {
		return muu;
	}

	public void setMuu(Manu_User muu) {
		this.muu = muu;
	}

	public String getAcceptState() {
		return acceptState;
	}

	public void setAcceptState(String acceptState) {
		this.acceptState = acceptState;
	}

	public String getExportExcelType() {
		return exportExcelType;
	}

	public void setExportExcelType(String exportExcelType) {
		this.exportExcelType = exportExcelType;
	}

	public String getMuuId() {
		return muuId;
	}

	public void setMuuId(String muuId) {
		this.muuId = muuId;
	}



	public PageFile getPageFile() {
		return pageFile;
	}




	public void setPageFile(PageFile pageFile) {
		this.pageFile = pageFile;
	}





	public String getSubMsg() {
		return subMsg;
	}





	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}
	public String getUpdateState() {
		return updateState;
	}
	public void setUpdateState(String updateState) {
		this.updateState = updateState;
	}
	public String getDelManuIds() {
		return delManuIds;
	}
	public void setDelManuIds(String delManuIds) {
		this.delManuIds = delManuIds;
	}



	public PageFileServiceInter getPfService() {
		return pfService;
	}


	public void setPfService(PageFileServiceInter pfService) {
		this.pfService = pfService;
	}


	public List<PageFile> getPfs() {
		return pfs;
	}


	public void setPfs(List<PageFile> pfs) {
		this.pfs = pfs;
	}
	public List<Employee> getEmps() {
		return emps;
	}
	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getQueryNull() {
		return queryNull;
	}
	public void setQueryNull(String queryNull) {
		this.queryNull = queryNull;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}



	
		

}
