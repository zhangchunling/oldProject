package com.zrgk.manu.util;

import java.io.Serializable;

import com.zrgk.manu.util.ClobToString;
import com.zrgk.manu.util.Page;

public class Manu2Excel extends Page implements Serializable{
	
	private Integer mid;
	private String mname;
	private Integer mbknum;
	private String subMinfo;//截取前十个字符
	private String mstartTime;
	private String mendTime;
	private String mmaster;

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


	public String getMmaster() {
		return mmaster;
	}

	public void setMmaster(String mmaster) {
		this.mmaster = mmaster;
	}

	public String getSubMinfo() {
		return subMinfo;
	}

	public void setSubMinfo(String subMinfo) {
		this.subMinfo = subMinfo;
	}

	@Override
	public String toString() {
		return "Manu2Excel [mid=" + mid + ", mname=" + mname + ", mbknum="
				+ mbknum + ", mendTime=" + mendTime + ", mstartTime="
				+ ", subMinfo=" + subMinfo + "]";
	}
	

	
	
	

}
