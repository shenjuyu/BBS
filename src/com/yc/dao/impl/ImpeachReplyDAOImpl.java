package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IImpeachReplyDAO;
import com.yc.dao.IImpeachTopicDAO;
import com.yc.po.ImpeachReplyPO;
import com.yc.po.ImpeachTopicPO;

public class ImpeachReplyDAOImpl implements IImpeachReplyDAO {
	
	DbHepler db=new DbHepler();

	@Override
	public int addImpeachReply(ImpeachReplyPO po) throws Exception {
		String sql="insert into bbs_impeachreply values(null,?,?,?,?,?,?,1)";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getRi_uid());
		params.add(po.getRi_uname());
		params.add(po.getRi_rid());
		params.add(po.getRi_title());
		params.add(po.getRi_content());
		params.add(po.getRi_reason());
		return db.update(sql, params);
	}

	@Override
	public List<ImpeachReplyPO> findByPage(ImpeachReplyPO po,Integer pageNum,Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select ri_id,ri_uid,ri_uname,ri_rid,ri_title,ri_content,ri_reason,ri_state from bbs_impeachreply where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getRi_id()){
				params.add(po.getRi_id());
				sb.append(" and ri_id=? ");
			}
			if(null!=po.getRi_uid()){
				params.add(po.getRi_uid());
				sb.append(" and ri_uid=? ");
			}
			if(null!=po.getRi_uname()){
				params.add(po.getRi_uname());
				sb.append(" and ri_uname=? ");
			}
			if(null!=po.getRi_rid()){
				params.add(po.getRi_rid());
				sb.append(" and ri_rid=? ");
			}
			if(null!=po.getRi_title()){
				params.add(po.getRi_title());
				sb.append(" and ri_title=? ");
			}
			if(null!=po.getRi_content()){
				params.add(po.getRi_content());
				sb.append(" and ri_content=? ");
			}
			if(null!=po.getRi_reason()){
				params.add(po.getRi_reason());
				sb.append(" and ri_reason=? ");
			}
			if(null!=po.getRi_state()){
				params.add(po.getRi_state());
				sb.append(" and ri_state=? ");
			}
		}
		sb.append(" order by ri_id asc ");
		if(null!=pageSize && null!=pageNum){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, ImpeachReplyPO.class);
	}

	@Override
	public int totalPage(ImpeachReplyPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from bbs_impeachreply where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getRi_id()){
				params.add(po.getRi_id());
				sb.append(" and ri_id=? ");
			}
			if(null!=po.getRi_uid()){
				params.add(po.getRi_uid());
				sb.append(" and ri_uid=? ");
			}
			if(null!=po.getRi_uname()){
				params.add(po.getRi_uname());
				sb.append(" and ri_uname=? ");
			}
			if(null!=po.getRi_rid()){
				params.add(po.getRi_rid());
				sb.append(" and ri_rid=? ");
			}
			if(null!=po.getRi_title()){
				params.add(po.getRi_title());
				sb.append(" and ri_title=? ");
			}
			if(null!=po.getRi_content()){
				params.add(po.getRi_content());
				sb.append(" and ri_content=? ");
			}
			if(null!=po.getRi_reason()){
				params.add(po.getRi_reason());
				sb.append(" and ri_reason=? ");
			}
			if(null!=po.getRi_state()){
				params.add(po.getRi_state());
				sb.append(" and ri_state=? ");
			}
		}
		return (int)db.getPromer(sb.toString(), params);
	}

	@Override
	public int updateImpeachReply(ImpeachReplyPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update bbs_impeachreply set ri_id=ri_id ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getRi_state()){
				params.add(po.getRi_state());
				sb.append(",ri_state=? ");
			}
		}
		if(null!=po){
			if(null!=po.getRi_id()){
				params.add(po.getRi_id());
				sb.append(" where  ri_id=? ");
			}
		}
		
		List<List<Object>> paramsList=new ArrayList<List<Object>>();
		List<String> sqls=new ArrayList<String>();
		paramsList.add(params);
		sqls.add(sb.toString());
		
		List<Object> params2=new ArrayList<Object>();
		
		String sql="delete from bbs_reply where r_id=?";
		if(null != po.getRi_rid() && 3 != po.getRi_state()) {
			params2.add(po.getRi_rid());
			paramsList.add(params2);
			sqls.add(sql);
		}
		return db.update(sqls, paramsList);
	}

}
