package com.yc.po;

import java.io.Serializable;

public class ReplyPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757336198214079158L;
	private Integer r_id;//回复id
	private String r_title;//回复标题
	private String r_content;//回复的内容（贴主发的最上面的内容）
	private String	r_publishtime;//回复的时间
	private String r_modifytime;//修改的时间
	private Integer u_id;//用户id
	private Integer t_id;//话题id
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
	public Integer getT_id() {
		return t_id;
	}
	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}
	
}
