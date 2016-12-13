package com.zrgk.manu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.manu.dao.MyManuDaoInter;
import com.zrgk.manu.dao.PageFileDaoInter;
import com.zrgk.manu.service.PageFileServiceInter;

public class PageFileServiceImpl implements PageFileServiceInter {
	private PageFileDaoInter pfdao;
	private MyManuDaoInter mudao;
	@Override
	public boolean insertPageFile(PageFile pf,int uid) {//要改为USERS BEAN
		
		//添加创建时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		pf.setPf_createtime(sdf.format(new Date()));
		//修改中间表关系
//		Manu_User qtempMuu=new Manu_User();
//		qtempMuu.setMid(pf.getPf_mu_id());
//		qtempMuu.setUuid(uid);
//		Manu_User tempMuu=new Manu_User();
//		//根据 UUID与MID查询中间表中对应的数据
//		tempMuu=mudao.queryManu_UserByUidAndMid(qtempMuu);
//		//修改中间表pfid
//		//1.新增稿件
		pfdao.insertPageFile(pf);
		//2.查询刚新增完的稿件
		pf=pfdao.queryPageFileByPF_mu_id(pf);
//		tempMuu.setPfid(pf.getPf_id());
//		mudao.updateManu_UserPfidByMuid(tempMuu);
		return true;
	}

	@Override
	public List<PageFile> queryPageFile(PageFile pf) {
		
		return pfdao.queryPageFile(pf);
	}

	@Override
	public boolean deletePageFileByID(PageFile pf) {
		
		return pfdao.deletePageFileByID(pf);
	}

	@Override
	public PageFile queryPageFileByID(PageFile pf) {
		
		return pfdao.queryPageFileByID(pf);
	}

	
	public PageFileDaoInter getPfdao() {
		return pfdao;
	}

	public void setPfdao(PageFileDaoInter pfdao) {
		this.pfdao = pfdao;
	}

	@Override
	public Integer queryCountByPageFile(PageFile pf) {
		
		return this.pfdao.queryCountByPageFile(pf);
	}

	@Override
	public List<PageFile> queryPageFile() {
		
		return this.pfdao.queryPageFile();
	}

	@Override
	public PageFile queryPageFileByPUID(PageFile pf) {
		return this.pfdao.queryPageFileByPUID(pf);
	}

	public MyManuDaoInter getMudao() {
		return mudao;
	}

	public void setMudao(MyManuDaoInter mudao) {
		this.mudao = mudao;
	}

	@Override
	public List<PageFile> queryWaitPageFile(PageFile pf,String iscount ) {
		
		
		return this.pfdao.queryWaitPageFile(pf,"=2",iscount);
	}
	public List<PageFile> queryOkedPageFile(PageFile pf,String iscount) {
		
		
		return this.pfdao.queryWaitPageFile(pf,">=3",iscount);
	}

	@Override
	public void updatePf_mu_idByID(PageFile pf,int uid) {
		int pf_mu_id=pf.getPf_mu_id();
		pf=this.pfdao.queryPageFileByID(pf);//查询稿件
		pf.setPf_mu_id(pf_mu_id);
		this.pfdao.updatePageFilePf_mu_id(pf);//约稿修改
		//修改中间表
		Manu_User muu=new Manu_User();
		muu.setMid(pf.getPf_mu_id());
		muu.setUuid(uid);
		muu=this.mudao.queryManu_UserByUidAndMid(muu);
		muu.setPfid(pf.getPf_id());
		this.mudao.updateManu_UserPfidByMuid(muu);
	}

	@Override
	public void updatePageFile(PageFile pf) {
		this.pfdao.updatePageFile(pf);
	}



}
