package com.yc.po;

import java.io.Serializable;

public class UserPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8665473491517553694L;
	private Integer u_id;//用户id
	private String u_name;//用户名
	private String u_pwd;//用户密码
	private String u_head;//头像
	private String u_regtime;//注册时间
	private String u_sex;//性别
	private Integer u_state;//用户状态   1正常    2 已禁用
	
	public Integer getU_state() {
		return u_state;
	}
	public void setU_state(Integer u_state) {
		this.u_state = u_state;
	}
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public String getU_head() {
		return u_head;
	}
	public void setU_head(String u_head) {
		this.u_head = u_head;
	}
	public String getU_regtime() {
		return u_regtime;
	}
	public void setU_regtime(String u_regtime) {
		this.u_regtime = u_regtime;
	}
	public String getU_sex() {
		return u_sex;
	}
	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}
	
}
