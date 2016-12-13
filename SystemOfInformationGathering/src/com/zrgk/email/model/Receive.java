package com.zrgk.email.model;

  
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * Email的javebean
 * 
 * @author wfh
 * 
 * 2015-7-14
 *
 */
public class Receive implements Serializable{

	 
	private static final long serialVersionUID = 857916400084079534L;

 
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer rid;
	
	private String rname;
 
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}
 

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	 
	
	 
	
	
}
