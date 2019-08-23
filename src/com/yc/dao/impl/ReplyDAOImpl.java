package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IReplyDAO;
import com.yc.dao.ITopicDAO;
import com.yc.po.ReplyPO;
import com.yc.vo.TopicVO;

public class ReplyDAOImpl implements IReplyDAO {

	DbHepler db = new DbHepler();

	private ITopicDAO topicdao = new TopicDAOImpl();

	/**
	 * 根据tid来的查询回复的数量
	 */
	@Override
	public List<Long> findByTid(TopicVO vo, Integer pageNum, Integer pageSize) throws Exception {
		int i = 0;
		int x = 1;
		List<Long> num = new ArrayList<Long>();
		List<TopicVO> list = new ArrayList<TopicVO>();
		List<TopicVO> list1 = new ArrayList<TopicVO>();
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select  r.r_content  , r.u_id , r.t_id,count(r.t_id) num from bbs_reply r  where 1=1 and  r.t_id=?  GROUP BY r.t_id    ");

		// 查询 所有话题的数量用list.size()
		list = topicdao.findByPage(vo, null, null);
		if(pageNum==1){
			if(list.size()<pageSize){
				x=list.size();
			}else{
				x=pageSize;
			}
			
		}else if (list.size() - ((pageNum) * pageSize) <= 0) {
			x = list.size();
		}else{
			x = (pageNum) * pageSize;
		}
		// 循环得到tid 再用tid查询回复的话题数量
		
		for (int n = (pageNum - 1) * pageSize; n < x; n++) {
			params.clear();
			i = topicdao.findByPage(vo, null, null).get(n).getT_id();
			params.add(i);
			list1 = db.findMutil(sb.toString(), params, TopicVO.class);
			if (list1 != null && list1.size() > 0) {
				num.add(list1.get(0).getNum());
			} else {
				num.add((long) 0);
			}
		}
		return num;

	}

	@Override
	public List<TopicVO> findbypage(TopicVO vo, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb =new StringBuffer();
		List<Object>params =new ArrayList<Object>();
		sb.append(" select r.t_id ,u.u_name ,u.u_id,u.u_head,u.u_regtime , r.r_modifytime, r.r_publishtime "
				+ ",r.r_content ,r.r_id from bbs_reply r ,bbs_user u where u.u_id=r.u_id "); 
				
		if(vo!=null){
			if(vo.getT_id()!=null){
				sb.append("and t_id =?" );
				params.add(vo.getT_id());
			}else if (vo.getU_id()!=null){
				sb.append("and u.u_id=?");
				params.add(vo.getU_id());
			}
		}
		sb.append(" order by t_id asc ");
			if(pageNum!=null&&pageSize!=null){
				sb.append(" limit "+ (pageNum - 1) * pageSize + "," + pageSize );
			}
		return db.findMutil(sb.toString(), params, TopicVO.class);
	}
	
	@Override
	public List<TopicVO> find(TopicVO vo) throws Exception {
		String sql = " select r_content,r_id ,u_id, t_id from bbs_reply WHERE t_id =? ";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getT_id());
		return db.findMutil(sql, params, TopicVO.class);
	}

	@Override
	public List<TopicVO> findLouzhu(TopicVO vo) throws Exception {
		String sql = "select t.b_id,t.t_content,t.t_id,t.t_modifytime,t.t_publishtime,t.t_title,t.u_id,u.u_head,u.u_name,u.u_regtime,u.u_sex,u.u_state from bbs_topic t ,bbs_user u where u.u_id=t.u_id and t.t_id=? ";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getT_id());
		return db.findMutil(sql, params, TopicVO.class);
	}

	@Override
	public int deleteReply(TopicVO vo) throws Exception {
		String sql = " delete from bbs_reply  where  r_id =?  ";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getR_id());
		return db.update(sql, params);
	}

	@Override
	public int addReply(ReplyPO po) throws Exception {
		String sql = "insert into bbs_reply values(null,'1',?,now(),now(),?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(po.getR_content());
		params.add(po.getU_id());
		params.add(po.getT_id());
		return db.update(sql, params);
	}

}
