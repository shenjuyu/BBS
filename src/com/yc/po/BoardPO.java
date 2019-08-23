package com.yc.po;

import java.io.Serializable;

public class BoardPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247221249565297971L;
	private Integer b_id;//板块id
	private String b_name;//板块标题
	private Integer b_parentid;//板块父类id
	public Integer getB_id() {
		return b_id;
	}
	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public Integer getB_parentid() {
		return b_parentid;
	}
	public void setB_parentid(Integer b_parentid) {
		this.b_parentid = b_parentid;
	}
}
