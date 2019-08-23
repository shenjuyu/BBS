package com.yc.po;

import java.io.Serializable;

public class ImpeachTopicPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ti_id;//话题(帖子)举报编号
	private Integer ti_uid;//举报用户编号
	private String ti_uname;//举报用户姓名
	private Integer ti_tid;//被举报帖子编号
	private String ti_title;//被举报帖子标题
	private String ti_reason;//举报原因
	private Integer ti_state;//举报信息的处理状态    1 待处理    2 处理完成    3 驳回
	
	
	public Integer getTi_state() {
		return ti_state;
	}
	public void setTi_state(Integer ti_state) {
		this.ti_state = ti_state;
	}
	public Integer getTi_id() {
		return ti_id;
	}
	public void setTi_id(Integer ti_id) {
		this.ti_id = ti_id;
	}
	public Integer getTi_uid() {
		return ti_uid;
	}
	public void setTi_uid(Integer ti_uid) {
		this.ti_uid = ti_uid;
	}
	public String getTi_uname() {
		return ti_uname;
	}
	public void setTi_uname(String ti_uname) {
		this.ti_uname = ti_uname;
	}
	public Integer getTi_tid() {
		return ti_tid;
	}
	public void setTi_tid(Integer ti_tid) {
		this.ti_tid = ti_tid;
	}
	public String getTi_title() {
		return ti_title;
	}
	public void setTi_title(String ti_title) {
		this.ti_title = ti_title;
	}
	public String getTi_reason() {
		return ti_reason;
	}
	public void setTi_reason(String ti_reason) {
		this.ti_reason = ti_reason;
	}
	
	
}
