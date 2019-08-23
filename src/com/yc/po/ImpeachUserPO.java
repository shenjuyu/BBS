package com.yc.po;

import java.io.Serializable;

public class ImpeachUserPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ui_id;//举报编号
	private Integer ui_uid;//举报用户编号
	private String ui_uname;//举报用户姓名
	private Integer ui_rid;//被举报用户编号
	private String ui_rname;//被举报用户姓名
	private String 	ui_reason;//举报原因
	private Integer ui_state;////举报信息的处理状态    1 待处理    2 处理完成    3 驳回
	
	public Integer getUi_state() {
		return ui_state;
	}
	public void setUi_state(Integer ui_state) {
		this.ui_state = ui_state;
	}
	public Integer getUi_id() {
		return ui_id;
	}
	public void setUi_id(Integer ui_id) {
		this.ui_id = ui_id;
	}
	public Integer getUi_uid() {
		return ui_uid;
	}
	public void setUi_uid(Integer ui_uid) {
		this.ui_uid = ui_uid;
	}
	public String getUi_uname() {
		return ui_uname;
	}
	public void setUi_uname(String ui_uname) {
		this.ui_uname = ui_uname;
	}
	public Integer getUi_rid() {
		return ui_rid;
	}
	public void setUi_rid(Integer ui_rid) {
		this.ui_rid = ui_rid;
	}
	public String getUi_rname() {
		return ui_rname;
	}
	public void setUi_rname(String ui_rname) {
		this.ui_rname = ui_rname;
	}
	public String getUi_reason() {
		return ui_reason;
	}
	public void setUi_reason(String ui_reason) {
		this.ui_reason = ui_reason;
	}
}
