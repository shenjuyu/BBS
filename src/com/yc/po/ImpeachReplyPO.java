package com.yc.po;

import java.io.Serializable;

public class ImpeachReplyPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ri_id;//话题(帖子)举报编号
	private Integer ri_uid;//举报用户编号
	private String ri_uname;//举报用户姓名
	private Integer ri_rid;//被举报回复编号
	private String ri_title;//被举报回复标题
	private String ri_content;//被举报的回复的内容
	private String ri_reason;//举报原因
	private Integer ri_state;//举报信息的处理状态    1 待处理    2 处理完成    3 驳回
	
	public Integer getRi_id() {
		return ri_id;
	}
	public void setRi_id(Integer ri_id) {
		this.ri_id = ri_id;
	}
	public Integer getRi_uid() {
		return ri_uid;
	}
	public void setRi_uid(Integer ri_uid) {
		this.ri_uid = ri_uid;
	}
	public String getRi_uname() {
		return ri_uname;
	}
	public void setRi_uname(String ri_uname) {
		this.ri_uname = ri_uname;
	}
	public Integer getRi_rid() {
		return ri_rid;
	}
	public void setRi_rid(Integer ri_rid) {
		this.ri_rid = ri_rid;
	}
	public String getRi_title() {
		return ri_title;
	}
	public void setRi_title(String ri_title) {
		this.ri_title = ri_title;
	}
	public String getRi_content() {
		return ri_content;
	}
	public void setRi_content(String ri_content) {
		this.ri_content = ri_content;
	}
	public String getRi_reason() {
		return ri_reason;
	}
	public void setRi_reason(String ri_reason) {
		this.ri_reason = ri_reason;
	}
	public Integer getRi_state() {
		return ri_state;
	}
	public void setRi_state(Integer ri_state) {
		this.ri_state = ri_state;
	}
	@Override
	public String toString() {
		return "ImpeachReplyPO [ri_id=" + ri_id + ", ri_uid=" + ri_uid + ", ri_uname=" + ri_uname + ", ri_rid=" + ri_rid
				+ ", ri_title=" + ri_title + ", ri_content=" + ri_content + ", ri_reason=" + ri_reason + ", ri_state="
				+ ri_state + "]";
	}
	
}
