package com.yc.dao;

import java.util.List;

import com.yc.po.TopicPO;
import com.yc.vo.TopicVO;

public interface ITopicDAO {
	
	/**
	 * 添加话题
	 * @param TopicPO po
	 * @return i
	 * @throws Exception
	 */
	public int addTopic(TopicPO po) throws Exception;

	/**
	 * 查询话题(可分页查询)
	 * @param TopicPO po
	 * @param pageNum 页数
	 * @param pageSize 每页行数
	 * @return List<TopicPO>
	 * @throws Exception
	 */
	public List<TopicVO> findByPage(TopicVO vo,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 总条数
	 * @param po
	 * @return i
	 * @throws Exception
	 */
	public int totalPage(TopicVO vo) throws Exception;
	
	/**
	  * 删除话题
	  * @param vo
	  * @return
	  * @throws Exception
	  */
	 public  int deleteReply (TopicVO vo)throws Exception;
}
