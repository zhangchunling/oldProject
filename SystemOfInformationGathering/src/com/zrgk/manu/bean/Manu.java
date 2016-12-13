package com.zrgk.manu.bean;

import java.io.Serializable;

import com.zrgk.manu.util.ClobToString;
import com.zrgk.manu.util.Page;

public class Manu extends Page implements Serializable{
	private static final long serialVersionUID = 1110662468783823740L;
	
	private Integer mid;
	private String mname;
	private Integer mbknum;
	private String name;
	private String minfo;
	private String mendTime;
	private String mstartTime;
	private Integer mstate;//0 未发布
	private String mmaster;
	
	private String subMinfo;//截取前十个字符
	
	private String umstate;//用于存放中间表中对应用户的约稿状态
	
	public String getSubMinfo() {
		return subMinfo;
	}
	public void setSubMinfo(String subMinfo) {
		if(subMinfo!=null){
			if(subMinfo.length()>=10){
				subMinfo=(subMinfo.trim().substring(0,10)+"···");//转存内容前十，用于列表展示
			}
		}
		this.subMinfo = subMinfo;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}

	public Integer getMbknum() {
		return mbknum;
	}
	public void setMbknum(Integer mbknum) {
		this.mbknum = mbknum;
	}
	public String getMinfo() {
		return minfo;
	}
	public void setMinfo(String minfo) {
		this.setSubMinfo(minfo);
		this.minfo = minfo;
	}
	public String getMendTime() {
		return mendTime;
	}
	public void setMendTime(String mendTime) {
		this.mendTime = mendTime;
	}
	public String getMstartTime() {
		return mstartTime;
	}
	public void setMstartTime(String mstartTime) {
		this.mstartTime = mstartTime;
	}
	public Integer getMstate() {
		return mstate;
	}
	public void setMstate(Integer mstate) {
		this.mstate = mstate;
	}
	public String getMmaster() {
		return mmaster;
	}
	public void setMmaster(String mmaster) {
		this.mmaster = mmaster;
	}
	
	
	public String getUmstate() {
		return umstate;
	}
	public void setUmstate(int umstate) {
		System.out.println(umstate);
		this.umstate = (umstate==2?"待审核":umstate==3?"审核通过":umstate==4?"已录用":umstate==5?"未录用":umstate==8?"审核未通过":umstate==9?"未录用":"");
	}
	
	@Override
	public String toString() {
		return "Manu [mid=" + mid + ", mname=" + mname + ", mbknum=" + mbknum
				+ ", minfo=" + minfo + ", mendTime=" + mendTime
				+ ", mstartTime=" + mstartTime + ", mstate=" + mstate
				+ ", mmaster=" + mmaster + "]";
	}
	
	
	

}
