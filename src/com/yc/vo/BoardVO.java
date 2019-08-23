package com.yc.vo;

import java.io.Serializable;

public class BoardVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer t_id;//话题id
	private String t_title;//话题名字
	private String t_content;//话题内容（贴主发的最上面的内容）
	private String	t_publishtime;//发出时间
	private String t_modifytime;//修改时间
	private Integer u_id;//用户id
	private Integer b_id;//板块id
	
	private String u_name;//用户名
	private String u_pwd;//用户密码
	private String u_head;//头像
	private String u_regtime;//注册时间
	private String u_sex;//性别
	
	private String b_name;//子板块名
	private Integer b_parentid;//板块父类id
	
	private Long num;//话题数量
	private String parent_name;//父板块名
	
	
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	public String getT_title() {
		return t_title;
	}
	public void setT_title(String t_title) {
		this.t_title = t_title;
	}
	public String getT_content() {
		return t_content;
	}
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}
	public String getT_publishtime() {
		return t_publishtime;
	}
	public void setT_publishtime(String t_publishtime) {
		this.t_publishtime = t_publishtime;
	}
	public String getT_modifytime() {
		return t_modifytime;
	}
	public void setT_modifytime(String t_modifytime) {
		this.t_modifytime = t_modifytime;
	}
	public Integer getU_id() {
		return u_id;
	}
	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}
	public Integer getB_id() {
		return b_id;
	}
	public void setB_id(Integer b_id) {
		this.b_id = b_id;
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
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
}
