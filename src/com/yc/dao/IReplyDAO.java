package com.yc.dao;

import java.util.List;

import com.yc.po.ReplyPO;
import com.yc.vo.TopicVO;

public interface IReplyDAO {

	/**
	 * 根据tid（主题id）分页查询回复数量
	 * @param vo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	 public List<Long> findByTid(TopicVO vo, Integer pageNum, Integer pageSize) throws Exception;
	
	 /**
	  * 分页查询查询回复的内容
	  * @param vo
	  * @return
	  * @throws Exception
	  */
	 public List<TopicVO>findbypage(TopicVO vo, Integer pageNum, Integer pageSize)throws Exception;
	 
	 /**
	  * 查询楼主信息
	  * @param vo
	  * @return
	  * @throws Exception
	  */
	 public List<TopicVO> findLouzhu(TopicVO vo)throws Exception;
	
	 /**
	  * 删除回复信息
	  * @param vo
	  * @return
	  * @throws Exception
	  */
	 public  int deleteReply (TopicVO vo)throws Exception;
	
	/**
	 * 发表回复
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int addReply(ReplyPO po) throws Exception;

	/**
	 * 根据tid查询回复的数量
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<TopicVO> find(TopicVO vo) throws Exception;
}
