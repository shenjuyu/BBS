package com.yc.dao;

import java.util.List;

import com.yc.po.ImpeachUserPO;

public interface IImpeachUserDAO {

	/**
	 * 添加举报信息（举报用户）
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int addImpeachUser(ImpeachUserPO po) throws Exception;
	
	/**
	 * 分页查询   (条件)
	 * @param po
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ImpeachUserPO> findByPage(ImpeachUserPO po,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 总页数
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int totalPage(ImpeachUserPO po) throws Exception;
	
	/**
	 * 修改举报信息处理状态
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int updateImpeachUser(ImpeachUserPO po) throws Exception;
}
