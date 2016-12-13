package com.zrgk.manu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zrgk.manu.bean.Manu;
import com.zrgk.manu.bean.Manu_User;
import com.zrgk.manu.bean.PageFile;
import com.zrgk.manu.dao.MyManuDaoInter;
import com.zrgk.manu.service.MyManuServiceInter;
import com.zrgk.permission.model.Employee;

public class MyManuServiceImpl implements MyManuServiceInter {
	private MyManuDaoInter myMuDao;

	@Override
	public List<Manu> queryMyManuByUid(Manu mu, int uid) {
		//获得 中间表中发布状态为已发布且与用户相关联的约稿ID
//		List<Manu> manus=null;
//		List<Manu_User> mus=myMuDao.queryManu_userByuid(uid);
//		for (Manu_User manu_User : mus) {
//			Manu idmu=new Manu();
//			idmu.setMid(manu_User.getMid());
//			myMuDao.
//		}
		return null;
	}

	@Override
	public boolean deleteMyManuByID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Manu queryMyManuByID(int id) {
		
		return myMuDao.queryMyManuByID(id);
	}

	@Override
	public Integer queryCountByManu(Manu mu) {
		// TODO Auto-generated method stub
		return null;
	}
	//查询未接受
	@Override
	public List<Manu> queryMyManuByUstateNotAccept(Manu mu,int uid) {
		return myMuDao.queryMyManu(mu, uid,"=0");
	}
	//查询已接受
	@Override
	public List<Manu> queryMyManuByUstateAccept(Manu mu, int uid) {
		 return myMuDao.queryMyManu(mu, uid,"=1");
	}
	//查询已提交
	@Override
	public List<Manu> queryMyManuByUstateSubed(Manu mu, int uid) {
		return myMuDao.queryMyManu(mu, uid,">=2");
	}
	

	public MyManuDaoInter getMyMuDao() {
		return myMuDao;
	}

	public void setMyMuDao(MyManuDaoInter myMuDao) {
		this.myMuDao = myMuDao;
	}


	@Override
	public void updateState(Manu_User muu) {
		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
		queryMuu.setUmstate(1);//改为已接受
		this.myMuDao.updateStateById(queryMuu);
	}
	
	@Override
	public void updateState2Sub(Manu_User muu) {
		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
		queryMuu.setUmstate(2);//改为已提交
		PageFile pf=new PageFile();
		pf.setPf_id(queryMuu.getPfid());
		pf=this.myMuDao.queryPageFileById(pf);
		pf.setPf_state(2);
		this.myMuDao.updatePfStateById(pf);
		this.myMuDao.updateStateById(queryMuu);
	}

	@Override
	public void updateUmstate(Manu_User muu) {
		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
		queryMuu.setUmstate(-1);//-1为拒绝  0为未发布 1为已发布
		this.myMuDao.updateStateById(queryMuu);
	}

	@Override
	public Integer queryMyManuCount(Manu mu,int uid,String accept) {
		Integer count=0;
		count= myMuDao.queryMyManuCount(mu, uid, accept);
		return count;
	}

	@Override
	public List<Manu> queryAllManu(int uid, int accept) {
		return myMuDao.queryAllManu(uid, accept);
	}

	@Override
	public List<Manu> queryNotExitsPageFileOfManus(int uid) {
		return this.myMuDao.queryNotExitsPageFileOfManus(uid);
	}

	@Override
	public boolean queryManuIsPf(Manu_User muu,int uid) {
		return this.myMuDao.queryManuIsPf(muu, uid);
	}

	@Override
	public List<Manu> queryManu(Manu mu,int state,String iscount) {
		if(state==0){
		return this.myMuDao.queryManu(mu,iscount);	
		}else if(state==1){
			return this.myMuDao.queryWaitManu(mu,iscount);
		}else if(state==2){
			return this.myMuDao.queryOkedManu(mu,iscount);
		}
		return null;
	}
	@Override
	public boolean updateMstateByMid(Manu mu,int model) {
		Manu tempMu=new Manu();
		//根据ID查询MANU信息
		tempMu=this.myMuDao.queryManuById(mu);
		//如果状体为0 修改为1 发布
		if(model==1){
			if(tempMu.getMstate()==1){
				return false;
			}
			tempMu.setMstate(1);
			this.myMuDao.updateMstateByMid(tempMu);
			return true;
		}
		//删除
		else if(model==-1){
			if(tempMu.getMstate()==1){
				return false;
			}
			tempMu.setMstate(-1);
			//bleach
			this.myMuDao.updateMstateByMid(tempMu);
			return true;
		//审核通过
		}else if(model==2){
			tempMu.setMstate(2);
			//bleach
			this.myMuDao.updateMstateByMid(tempMu);
			return true;
			
		}else 
			if(model==3){
				tempMu.setMstate(8);//8 为审核不通过
				this.myMuDao.updateMstateByMid(tempMu);
				return true;
			}
		//不为0 发布失败
		return false;
	}

	@Override
	public Manu queryManuById(Manu mu) {
		return this.myMuDao.queryManuById(mu);
	}

	@Override
	public void inserManu(Manu mu) {
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		mu.setMstartTime(sm.format(new Date()));
		//新增中间表数据
		
		this.myMuDao.insertManu(mu);
	}
	public void updateStateNosub(Manu_User muu){
		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
		queryMuu.setUmstate(8);//改为审核不通过
		PageFile pf=new PageFile();
		pf.setPf_id(queryMuu.getPfid());
		pf=this.myMuDao.queryPageFileById(pf);
		pf.setPf_state(8);
		this.myMuDao.updatePfStateById(pf);
		this.myMuDao.updateStateById(queryMuu);
		
		
	}
	
	
	public void updateStateNoOk(Manu_User muu){
		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
		queryMuu.setUmstate(9);//改为审核不录用
		PageFile pf=new PageFile();
		pf.setPf_id(queryMuu.getPfid());
		pf=this.myMuDao.queryPageFileById(pf);
		pf.setPf_state(9);
		this.myMuDao.updatePfStateById(pf);
		this.myMuDao.updateStateById(queryMuu);
		
		
	}
	@Override
	public void updateState3Sub(Manu_User muu) {
		
//		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
//		queryMuu.setUmstate(2);//改为已提交
//		PageFile pf=new PageFile();
//		pf.setPf_id(queryMuu.getPfid());
//		pf=this.myMuDao.queryPageFileById(pf);
//		pf.setPf_state(2);
//		this.myMuDao.updatePfStateById(pf);
//		this.myMuDao.updateStateById(queryMuu);
		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
		queryMuu.setUmstate(3);//改为已审核
		PageFile pf=new PageFile();
		pf.setPf_id(queryMuu.getPfid());
		pf=this.myMuDao.queryPageFileById(pf);
		pf.setPf_state(3);
		this.myMuDao.updatePfStateById(pf);
		this.myMuDao.updateStateById(queryMuu);
		
	}
	public void updateState4Sub(Manu_User muu) {
		Manu_User queryMuu=this.myMuDao.queryStateByUidAndMid(muu);
		queryMuu.setUmstate(4);//改为已录用
		PageFile pf=new PageFile();
		pf.setPf_id(queryMuu.getPfid());
		pf=this.myMuDao.queryPageFileById(pf);
		pf.setPf_state(5);
		this.myMuDao.updatePfStateById(pf);
		this.myMuDao.updateStateById(queryMuu);
		
	}

	@Override
	public void insertManu_User(Manu_User muu) {
		this.myMuDao.insertManu_User(muu);
	}

	@Override
	public List<Employee> queryAllEmp() {
		return this.myMuDao.queryAllEmp();
	}

	@Override
	public boolean addManuAndManu_User(Manu mu,String users) {
		mu.setMstate(0);
		Manu_User muu=new Manu_User();
		String uuids[]=users.split(",");
		//判断新增的约稿要发给几个用户
		muu.setMuid(mu.getMid());
		muu.setUmstate(0);
		muu.setPfid(0);
		this.inserManu(mu);
		muu.setMid(mu.getMid());
		if(uuids.length==1){
			muu.setUuid(Integer.valueOf(uuids[0]));
			this.insertManu_User(muu);
		}else{
			for (String uuid : uuids) {
				muu.setUuid(Integer.valueOf(uuid));
				this.insertManu_User(muu);
			}
		}
		return true;
	}







	


}
