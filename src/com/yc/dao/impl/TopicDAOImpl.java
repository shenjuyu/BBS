package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IReplyDAO;
import com.yc.dao.ITopicDAO;
import com.yc.po.TopicPO;
import com.yc.vo.TopicVO;

public class TopicDAOImpl implements ITopicDAO {
	
	DbHepler db=new DbHepler();

	@Override
	public int addTopic(TopicPO po) throws Exception {
		String sql="insert into bbs_topic values(null,?,?,now(),now(),?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getT_title());
		params.add(po.getT_content());
		params.add(po.getU_id());
		params.add(po.getB_id());
		return db.update(sql, params);
	}
	
	@Override
	public List<TopicVO> findByPage(TopicVO vo, Integer pageNum,Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select t.b_id,t.t_id,t.t_title,t.t_content,t.t_modifytime,t.t_publishtime,"
				+ "u.u_id,u.u_name,u.u_regtime,u.u_sex,u.u_head,u.u_state from bbs_topic"
				+ " t ,bbs_user u where u.u_id=t.u_id  ");
		List<Object> params=null;
		if(null!=vo){
			params=new ArrayList<Object>();
			if(null!=vo.getT_id()){
				params.add(vo.getT_id());
				sb.append(" and t.t_id=? ");
			}
			if(null!=vo.getB_id()){
				params.add(vo.getB_id());
				sb.append(" and t.b_id=? ");
			}
			if(null!=vo.getU_id()){
				params.add(vo.getU_id());
				sb.append(" and u.u_id=? ");
			}
			if(null!=vo.getU_name()){
				params.add(vo.getU_name());
				sb.append(" and u.u_name=? ");
			}
		}
		sb.append(" order by t.t_id asc ");
		if(null!=pageNum && null!=pageSize){
			sb.append(" limit  "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params,TopicVO.class );
	}
	
	@Override
	public int totalPage(TopicVO vo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<TopicVO> findreply(TopicVO vo) throws Exception {
		String sql = " select r_content,r_id ,u_id, t_id from bbs_reply WHERE t_id =? ";
		List<Object> params = new ArrayList<Object>();
		params.add(vo.getT_id());
		return db.findMutil(sql, params, TopicVO.class);
	}

	@Override
	public int deleteReply(TopicVO vo) throws Exception {
		String sql =" delete from bbs_reply  where  t_id =?  ";
		String sql1 =" delete from bbs_topic   where  t_id =?  ";
		List<TopicVO> reply=findreply(vo);
		if(null==reply||reply.size()<=0){
			List<Object> params=new ArrayList<Object>();
			params.add(vo.getT_id());
			return db.update(sql1, params);
		}else{
			List<String>sqls =new ArrayList<String>();
			sqls.add(sql);
			sqls.add(sql1);
			
			List<Object> list=new ArrayList<Object>();
			list.add(vo.getT_id());
			List< List<Object> > params= new ArrayList<List<Object>>();
			params.add(list);
			params.add(list);
			
			return db.update(sqls, params);
		}
		
	}

}
