package com.yc.po;

import java.io.Serializable;

public class TopicPO implements Serializable{
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

}
