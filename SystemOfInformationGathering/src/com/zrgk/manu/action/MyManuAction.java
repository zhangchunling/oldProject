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

public class MyManuAction  extends ActionSupport{
	private String resultpath;
	private MyManuServiceInter myMuService;
	private PageFileServiceInter  pfService;
	private Manu mu;
	private List<Manu> mus;
	private Manu_User muu;
	private String acceptState; //判断是哪个界面 用于指定不同的查询方法
	private String exportExcelType;
	private String EXCELINFO="我的约稿";
	private String muuId;
	private PageFile pageFile;
	private String subMsg;
	private String queryNull;
	private Manu mau;
	private String flag;
	private String ManuAcceptMsg;
	private String ManuAcceptedMsg;
	private String ManuSubedMsg;
	public String subMyManu(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		acceptState="accept";
		if(this.myMuService.queryManuIsPf(muu, emp.getEid())){
			
			muu.setUuid(emp.getEid());
			myMuService.updateState2Sub(muu);
			
			this.resultpath="MyManu_queryManu";
			ManuAcceptedMsg="约稿提交成功";
			return "goaction";
		}else{
			ManuAcceptedMsg="所提交的约稿下没有稿件,无法提交！请选择[稿件管理]-->绑定约稿";
			this.resultpath="MyManu_queryManu";
			return "goaction";
		}
	}
	
	
	
	public String queryManu(){
		System.out.println(acceptState);
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(flag!=null&&mu!=null&&session.getAttribute("mymanu")!=null){
			System.out.println(mu.getMname()!=null||mu.getMstartTime()!=null||mu.getMbknum()!=null
				||mu.getMendTime()!=null||mu.getMmaster()!=null);
			if(!"".equals(mu.getMname())||!"".equals(mu.getMstartTime())||mu.getMbknum()!=null
				||!"".equals(mu.getMendTime())||!"".equals(mu.getMmaster())){
			int tempcurrent=mu.getCurrentPage();
			mu=(Manu)session.getAttribute("mymanu");
			mu.setCurrentPage((tempcurrent));
			session.setAttribute("mymanu", mu);
			}
		}else{
			session.setAttribute("mymanu", mu);
		}
		if(mu==null){
			if("notAccept".equals(acceptState)){
				mu=new Manu();
//				session.removeAttribute("mymanu");
				mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), "=0"));
				mus=myMuService.queryMyManuByUstateNotAccept(mu, emp.getEid());
				resultpath="MyManuscripts/manuList.jsp";
			}
			else if("accept".equals(acceptState)){
				mu=new Manu();
//				session.removeAttribute("mymanu");
				mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), "=1"));
				mus=myMuService.queryMyManuByUstateAccept(mu, emp.getEid());
				resultpath="MyManuscripts/manuListAccept.jsp";
			}
			else if("subed".equals(acceptState)){
				mu=new Manu();
//				session.removeAttribute("mymanu");
				mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), ">=2"));
				mus=myMuService.queryMyManuByUstateSubed(mu, emp.getEid());
				resultpath="MyManuscripts/manuSubList.jsp";
			}
		}else{
//			if(mu!=null){
//				if("".equals(mu.getMname().trim())&&"".equals(mu.getMstartTime().trim())&&mu.getMbknum()==null
//					&&"".equals(mu.getMendTime().trim())&&"".equals(mu.getMmaster().trim())){
//				session.setAttribute("mymanu", mu);
//				}
//			}
			if("notAccept".equals(acceptState)){
				Manu mymanu=(Manu)session.getAttribute("mymanu");
				mymanu.setCount(myMuService.queryMyManuCount(mymanu, emp.getEid(), "=0"));
				mus=myMuService.queryMyManuByUstateNotAccept(mymanu, emp.getEid());
			
				resultpath="MyManuscripts/manuList.jsp";
			}
			else if("accept".equals(acceptState)){
				Manu mymanu=(Manu)session.getAttribute("mymanu");
				mymanu.setCount(myMuService.queryMyManuCount(mymanu, emp.getEid(), "=1"));
				mus=myMuService.queryMyManuByUstateAccept(mymanu, emp.getEid());
				resultpath="MyManuscripts/manuListAccept.jsp";
			}
			else if("subed".equals(acceptState)){
				Manu mymanu=(Manu)session.getAttribute("mymanu");
				
				mymanu.setCount(myMuService.queryMyManuCount(mymanu, emp.getEid(), ">=2"));
				mus=myMuService.queryMyManuByUstateSubed(mymanu, emp.getEid());
				resultpath="MyManuscripts/manuSubList.jsp";
			}
			
			/*if("null".equals(queryNull)&&mu.getCurrentPage()==1&&"".equals(mu.getMname())
					&&"".equals(mu.getMbknum())&&"".equals(mu.getMstartTime())&&"".equals(mu.getMendTime())
					&&"".equals(mu.getMmaster())
					){*/
			/*if("null".equals(queryNull)){
				if("notAccept".equals(acceptState)){
					mu=new Manu();
					mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), "=0"));
					mus=myMuService.queryMyManuByUstateNotAccept(mu, emp.getEid());
					resultpath="MyManuscripts/manuList.jsp";
				}
				if("accept".equals(acceptState)){
					mu=new Manu();
					mus=myMuService.queryMyManuByUstateAccept(mu, emp.getEid());
					mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), "=1"));
					resultpath="MyManuscripts/manuListAccept.jsp";
				}
				if("subed".equals(acceptState)){
					mu=new Manu();
					mus=myMuService.queryMyManuByUstateSubed(mu, emp.getEid());
					mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), ">=2"));
					resultpath="MyManuscripts/manuSubList.jsp";
				}
			}else{
				if("notAccept".equals(acceptState)){
					mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), "=0"));
					mus=myMuService.queryMyManuByUstateNotAccept(mu, emp.getEid());
					queryNull=null;
					resultpath="MyManuscripts/manuList.jsp";
				}
				if("accept".equals(acceptState)){
					mus=myMuService.queryMyManuByUstateAccept(mu, emp.getEid());
					mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), "=1"));
					queryNull=null;
					resultpath="MyManuscripts/manuListAccept.jsp";
				}
				if("subed".equals(acceptState)){
					mus=myMuService.queryMyManuByUstateSubed(mu, emp.getEid());
					mu.setCount(myMuService.queryMyManuCount(mu, emp.getEid(), ">=2"));
					queryNull=null;
					resultpath="MyManuscripts/manuSubList.jsp";
				}
				
			}*/
			
		}
		return "gojsp";
		
	}
	
	public String queryManuById(){
		mau=myMuService.queryMyManuByID(mu.getMid());
		resultpath="MyManuscripts/look.jsp";
		return "gojsp";
		
	}
	//接受
	public String updateState(){
		System.out.println(muuId);
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(muuId==null||"".equals(muuId.trim())){
			muu.setUuid(emp.getEid());//暂时写死
		myMuService.updateState(muu);
		}
		//批量接受 修改中间表接受状态
		else{
			String muuids[]=muuId.split(",");
			for(int i=0;i<muuids.length;i++){
				muu=new Manu_User();
				muu.setUuid(emp.getEid());//暂时写死
				muu.setMid(Integer.parseInt(muuids[i]));
				myMuService.updateState(muu);
			}
		}
		acceptState="notAccept";//附带状态
		ManuAcceptMsg="约稿接受成功！";
		resultpath="MyManu_queryManu";
		return "goaction";
	}
	//拒绝
	public String refuse(){
		//从Session获取登陆用户信息
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		muu.setUuid(emp.getEid());
		myMuService.updateUmstate(muu);
		acceptState="notAccept";
		resultpath="MyManu_queryManu";
		return "goaction";
		
		
	}
	public String editPageFile(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		pageFile=new PageFile();
		pageFile.setPf_mu_id(mu.getMid());
		pageFile.setPf_author(emp.getRealName());
		pageFile=this.pfService.queryPageFileByPUID(pageFile);
		if(pageFile!=null){
			this.resultpath="MyFile/look.jsp";
		}else{
			this.resultpath="MyFile/addfile.jsp";
		}
		return "gojsp";
		
	}
	public String exportExcl(){
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.addHeader("Content-Disposition",
                "attachment;filename=manuscripts.xls");
        ExportExcel<Manu2Excel> ex = new ExportExcel<Manu2Excel>();//实例化导excel工具类
        //声明一个数组  里面数据是设置导出的excel头部分
        Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
        
        String[] headers = { "约稿编号","刊物期号", "刊物名", "约稿信息", "约稿发布时间", "约稿结束时间","约稿发布人" };
        //声明一个list用来装从数据库查询来的商品信息
        List<Manu2Excel> dataset = new ArrayList<Manu2Excel>();
        if("all".equals(exportExcelType)){//判断界面传来的value是哪种导出方式
        	List<Manu> list=new ArrayList<Manu>();
        		mu=new Manu();
        		if("notAccept".equals(acceptState)){
        			list=myMuService.queryMyManuByUstateNotAccept(mu, emp.getEid());
        		}else if("accept".equals(acceptState)){
        			list=myMuService.queryMyManuByUstateAccept(mu, emp.getEid());
        		}else if("subed".equals(acceptState)){
        			list=myMuService.queryMyManuByUstateSubed(mu, emp.getEid());
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
        	System.out.println(exportExcelType);
        	String values[]=exportExcelType.split(",");
			for (String m_id : values) {
				Manu2Excel m2x=new Manu2Excel();
				Manu manu=new Manu();
				manu=this.myMuService.queryMyManuByID(Integer.parseInt(m_id));
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





	public PageFileServiceInter getPfService() {
		return pfService;
	}





	public void setPfService(PageFileServiceInter pfService) {
		this.pfService = pfService;
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





	public String getQueryNull() {
		return queryNull;
	}





	public void setQueryNull(String queryNull) {
		this.queryNull = queryNull;
	}





	public Manu getMau() {
		return mau;
	}





	public void setMau(Manu mau) {
		this.mau = mau;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	public String getManuAcceptMsg() {
		return ManuAcceptMsg;
	}



	public void setManuAcceptMsg(String manuAcceptMsg) {
		ManuAcceptMsg = manuAcceptMsg;
	}



	public String getManuAcceptedMsg() {
		return ManuAcceptedMsg;
	}



	public void setManuAcceptedMsg(String manuAcceptedMsg) {
		ManuAcceptedMsg = manuAcceptedMsg;
	}



	public String getManuSubedMsg() {
		return ManuSubedMsg;
	}



	public void setManuSubedMsg(String manuSubedMsg) {
		ManuSubedMsg = manuSubedMsg;
	}
		
}
