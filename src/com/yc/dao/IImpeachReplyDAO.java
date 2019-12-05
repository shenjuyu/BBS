package com.yc.dao;

import java.util.List;

import com.yc.po.ImpeachReplyPO;

public interface IImpeachReplyDAO {

	/**
	 * 添加举报信息（举报回复）
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int addImpeachReply(ImpeachReplyPO po) throws Exception;
	
	/**
	 * 分页查询   (条件)
	 * @param po
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ImpeachReplyPO> findByPage(ImpeachReplyPO po,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 总页数
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int totalPage(ImpeachReplyPO po) throws Exception;
	
	/**
	 * 修改举报信息处理状态
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int updateImpeachReply(ImpeachReplyPO po) throws Exception;
}
