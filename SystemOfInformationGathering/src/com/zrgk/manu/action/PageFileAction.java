package com.zrgk.manu.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.zrgk.manu.bean.Manu;
import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.manu.service.MyManuServiceInter;
import com.zrgk.manu.service.PageFileServiceInter;
import com.zrgk.manu.util.ExportExcel;
import com.zrgk.manu.util.PageFile2Excel;
import com.zrgk.permission.model.Employee;
import com.opensymphony.xwork2.ActionSupport;


public class PageFileAction extends ActionSupport{
	private String resultpath;
	
	private PageFileServiceInter pfService ;
	private MyManuServiceInter mmservice;
	private List<PageFile> pfs ;
	private PageFile pageFile;
	private String ids;
	private String exportExcelType;
	private final String EXCELINFO="导出稿件列表";
	private String msg;//向页面推送信息
	private String setNull;
	public String queryById(){
		pageFile=pfService.queryPageFileByID(pageFile);
		this.resultpath="/MyFile/look.jsp";
		return "gojsp";
		
	}
	
	
	/**
	 * 根据 类别 文体 标题 条件查询
	 * @return
	 */
//	public String whereQuery(){
//		pfs=pfService.queryPageFile(pageFile);
//		this.resultpath="/MyFile/filelist.jsp";
//		return "gojsp";
//	}
//	
	public String updatePageFile(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		pageFile.setPf_author(emp.getEmpName());
		pageFile.setPf_u_id(emp.getEid());
		this.pfService.updatePageFile(pageFile);
		this.resultpath="PageFile_queryPf";
		this.msg="修改成功！";
		return "goaction";
	}
	public String queryPf(){
		if(pageFile==null){
			pageFile=new PageFile();
			pageFile.setCount(pfService.queryCountByPageFile(pageFile));
			pfs=pfService.queryPageFile(pageFile);
		}else{ 
			if(pageFile.getContent()!=null){
				pageFile=new PageFile();
				pageFile.setCount(pfService.queryCountByPageFile(pageFile));
				pfs=pfService.queryPageFile(new PageFile());
			}else{
				msg=null;
				int count=pfService.queryCountByPageFile(pageFile);
				pageFile.setCount(count);
				pfs=pfService.queryPageFile(pageFile);
			}
			}
		this.resultpath="/MyFile/filelist.jsp";
		return "gojsp";
		
	}
	
	
	public String exportExcel() throws IOException{
		msg=null;
		HttpServletResponse resp=ServletActionContext.getResponse();
		resp.addHeader("Content-Disposition",
                "attachment;filename=Task.xls");
        ExportExcel<PageFile2Excel> ex = new ExportExcel<PageFile2Excel>();//实例化导excel工具类
        //声明一个数组  里面数据是设置导出的excel头部分
        String[] headers = { "稿件编号", "作者", "稿件标题", "类型", "文体","创建时间","审核状态","内容简述" };
        //声明一个list用来装从数据库查询来的商品信息
        List<PageFile2Excel> dataset = new ArrayList<PageFile2Excel>();
        
        if("all".equals(exportExcelType)){//判断界面传来的value是哪种导出方式
        	List<PageFile> list=null;
        	if(pageFile==null){
    			pageFile=new PageFile();
    			list=pfService.queryPageFile(pageFile);
    		}else{ 
    				list=pfService.queryPageFile(pageFile);
    			}
        	
        	for (PageFile p : list) {
        		PageFile2Excel pe=new PageFile2Excel();
        		int pfState=p.getPf_state();//获取状态代码
        		//0 未提交 1 未审核 2审核通过、3审核未通过、4未录用 5已录用
        		String pf_stateStr=pfState==0?"未提交":pfState==1?"未审核":pfState==2?"审核通过":pfState==3?"审核未通过":pfState==4?"未录用":"已录用";
        		pe.setPf_id(p.getPf_id());
        		pe.setPf_author(p.getPf_author());
        		pe.setPf_createtime(p.getPf_createtime());
        		pe.setPf_name(p.getPf_name());
        		pe.setPf_type(p.getPf_type());
        		pe.setPf_style(p.getPf_style());
        		pe.setPf_state(pf_stateStr);
        		pe.setSubContent(p.getSubContent());
        		dataset.add(pe);
			}
        	
        }
        else{
        	String values[]=exportExcelType.split(",");
			for (String pf_id : values) {
				PageFile p=pfService.queryPageFileByID(new PageFile(Integer.parseInt(pf_id)));
				//组装需要导出EXCL的pagefile字段
				int pfState=p.getPf_state();//获取状态代码
        		//0 未提交 1 未审核 2审核通过、3审核未通过、4未录用 5已录用
        		String pf_stateStr=pfState==0?"未提交":pfState==1?"未审核":pfState==2?"审核通过":pfState==3?"审核未通过":pfState==4?"未录用":"已录用";
			
        		PageFile2Excel pe=new PageFile2Excel();
        		pe.setPf_id(p.getPf_id());
        		pe.setPf_author(p.getPf_author());
        		pe.setPf_createtime(p.getPf_createtime());
        		pe.setPf_name(p.getPf_name());
        		pe.setPf_type(p.getPf_type());
        		pe.setPf_style(p.getPf_style());
        		pe.setPf_state(pf_stateStr);
        		pe.setSubContent(p.getSubContent());
        		dataset.add(pe);
			}
        }
        OutputStream out = resp.getOutputStream();
			ex.exportExcel(EXCELINFO,headers, dataset, out);
			out.close();
    	
		return null;
		
	}
	//回传未绑定稿件的约稿
	public String ajaxManu(){
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		try {
			StringBuffer sb=new StringBuffer();
			List<Manu> ms=this.mmservice.queryNotExitsPageFileOfManus(emp.getEid());//暫時寫死
			HttpServletResponse resp=ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out=resp.getWriter();
			
			for (Manu manu : ms) {
				sb.append(manu.getMname()).append("---").append(manu.getMbknum()).append(":").append(manu.getMid()).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			out.print(sb.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	public String insertPf(){
		Manu_User muu=new Manu_User();
		Employee emp=(Employee)ServletActionContext.getRequest().getSession().getAttribute("user");
		//添加作者
		pageFile.setPf_author(emp.getEmpName());//Session 获取登陆用户ID 暂时写死
		pageFile.setPf_state(0);
		pfService.insertPageFile(pageFile,emp.getEid());
		this.resultpath="PageFile_queryPf";
		msg="新增成功";
		return "go_redirect_action";
	}
	
	//跳往绑定约稿页面
	public String bindManuById(){
		pageFile=pfService.queryPageFileByID(pageFile);
		this.resultpath="MyFile/pageFilebindManu.jsp";
		return "gojsp";
	}
	//跳转修改页面
	public String goUpdate(){
		pageFile=this.pfService.queryPageFileByID(pageFile);
		this.resultpath="MyFile/update.jsp";
		return "gojsp";
	}
	
	public String bindPf(){
		this.pfService.updatePf_mu_idByID(pageFile,((Employee)ServletActionContext.getRequest().getSession().getAttribute("user")).getEid());
		this.resultpath="PageFile_queryPf";
		return "go_redirect_action";
	}
	//通过页面选中的项 删除
	public String deletePfById(){
		PageFile tempPf=new PageFile();
		pageFile=new PageFile();
		String idsStr[]=ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			pageFile.setPf_id(Integer.parseInt(idsStr[i]));
			tempPf=this.pfService.queryPageFileByID(pageFile);
			if(tempPf.getPf_mu_id()!=null){
				this.msg="已绑定约稿的稿件不能删除！";
				this.resultpath="PageFile_queryPf";
				return "goaction";
			}
			pfService.deletePageFileByID(pageFile);
		}
		pageFile=null;
		msg="删除成功";
		this.resultpath="PageFile_queryPf";
		return "goaction";
	}
	
	
	public PageFileServiceInter getPfService() {
		return pfService;
	}



	public void setPfService(PageFileServiceInter pfService) {
		this.pfService = pfService;
	}



	public String getResultpath() {
		return resultpath;
	}
	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}

	public List<PageFile> getPfs() {
		return pfs;
	}

	public void setPfs(List<PageFile> pfs) {
		this.pfs = pfs;
	}


	public PageFile getPageFile() {
		return pageFile;
	}


	public void setPageFile(PageFile pageFile) {
		this.pageFile = pageFile;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getExportExcelType() {
		return exportExcelType;
	}

	public void setExportExcelType(String exportExcelType) {
		this.exportExcelType = exportExcelType;
	}




	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getSetNull() {
		return setNull;
	}


	public void setSetNull(String setNull) {
		this.setNull = setNull;
	}


	public MyManuServiceInter getMmservice() {
		return mmservice;
	}


	public void setMmservice(MyManuServiceInter mmservice) {
		this.mmservice = mmservice;
	}

	

	
	

}
