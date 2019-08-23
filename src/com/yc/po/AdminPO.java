package com.yc.po;

import java.io.Serializable;

public class AdminPO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2830828014262309095L;
	private Integer a_id;//管理员id
	private String a_name;//管理用户名
	private String a_pwd;//管理员密码
	private String a_tel;//管理员电话
	private Integer a_power;//权限   1 超级管理员    2 普通管理员
	private Integer a_state;//管理员状态    1启用   2禁用     3待审核
	
	public Integer getA_state() {
		return a_state;
	}
	public void setA_state(Integer a_state) {
		this.a_state = a_state;
	}
	public Integer getA_id() {
		return a_id;
	}
	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public String getA_pwd() {
		return a_pwd;
	}
	public void setA_pwd(String a_pwd) {
		this.a_pwd = a_pwd;
	}
	public String getA_tel() {
		return a_tel;
	}
	public void setA_tel(String a_tel) {
		this.a_tel = a_tel;
	}
	public Integer getA_power() {
		return a_power;
	}
	public void setA_power(Integer a_power) {
		this.a_power = a_power;
	}
	
	
	
}
