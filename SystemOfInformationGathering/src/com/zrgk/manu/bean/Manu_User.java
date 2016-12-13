package com.zrgk.manu.bean;

public class Manu_User {
	private Integer muid;

	private Integer mid;//manu主键
	private Integer umstate;//用户对约稿的接收状态 0为接收 1 已接受
	private Integer uuid;//用户主键
	private Integer pfid;//稿件主键
	
	
	public Integer getMuid() {
		return muid;
	}
	public void setMuid(Integer muid) {
		this.muid = muid;
	}
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getUmstate() {
		return umstate;
	}
	public void setUmstate(Integer umstate) {
		this.umstate = umstate;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	
	
	public Integer getPfid() {
		return pfid;
	}
	public void setPfid(Integer pfid) {
		this.pfid = pfid;
	}
	@Override
	public String toString() {
		return "Manu_User [muid=" + muid + ", mid=" + mid + ", umstate="
				+ umstate + ", uuid=" + uuid + ", pfid=" + pfid + "]";
	}

	
	

}
