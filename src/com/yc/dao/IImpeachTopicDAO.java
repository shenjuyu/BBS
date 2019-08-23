package com.yc.dao;

import java.util.List;

import com.yc.po.ImpeachTopicPO;

public interface IImpeachTopicDAO {

	/**
	 * 添加举报信息（举报帖子）
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int addImpeachTopic(ImpeachTopicPO po) throws Exception;
	
	/**
	 * 分页查询   (条件)
	 * @param po
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ImpeachTopicPO> findByPage(ImpeachTopicPO po,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 总页数
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int totalPage(ImpeachTopicPO po) throws Exception;
	
	/**
	 * 修改举报信息处理状态
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int updateImpeachTopic(ImpeachTopicPO po) throws Exception;
}
