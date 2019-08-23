package com.yc.vo;

import java.io.Serializable;

public class TopicVO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer t_id;//话题id
	private String t_title;//话题名字
	private String t_content;//话题内容（贴主发的最上面的内容）
	private String	t_publishtime;//发出时间
	private String t_modifytime;//修改时间
	private Long num;//回复的条数
	
	
	private Integer r_id;//回复id
	private String r_title;//回复标题
	private String r_content;//回复的内容（贴主发的最上面的内容）
	private String	r_publishtime;//回复的时间
	private String r_modifytime;//修改的时间
	private Integer u_id;//用户id
	private Integer b_id;//板块id
	private String b_name;//板块name
	
	
	private String u_name;//用户名
	private String u_pwd;//用户密码
	private String u_head;//头像
	private String u_regtime;//注册时间
	private String u_sex;//性别
	
	private Integer pageNum;//页数
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Integer getT_id()  {
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
	public Integer getR_id() {
		return r_id;
	}
	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}
	public String getR_title() {
		return r_title;
	}
	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getR_publishtime() {
		return r_publishtime;
	}
	public void setR_publishtime(String r_publishtime) {
		this.r_publishtime = r_publishtime;
	}
	public String getR_modifytime() {
		return r_modifytime;
	}
	public void setR_modifytime(String r_modifytime) {
		this.r_modifytime = r_modifytime;
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	
	
	
}
