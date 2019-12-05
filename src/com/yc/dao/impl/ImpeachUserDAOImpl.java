package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IImpeachUserDAO;
import com.yc.po.ImpeachUserPO;

public class ImpeachUserDAOImpl implements IImpeachUserDAO {
	
	DbHepler db=new DbHepler();

	@Override
	public int addImpeachUser(ImpeachUserPO po) throws Exception {
		String sql="insert into bbs_impeachuser values(null,?,?,?,?,?,1)";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getUi_uid());
		params.add(po.getUi_uname());
		params.add(po.getUi_rid());
		params.add(po.getUi_rname());
		params.add(po.getUi_reason());
		return db.update(sql, params);
	}

	@Override
	public List<ImpeachUserPO> findByPage(ImpeachUserPO po, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select ui_id,ui_uid,ui_uname,ui_rid,ui_rname,ui_reason,ui_state from bbs_impeachuser where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getUi_id()){
				params.add(po.getUi_id());
				sb.append(" and ui_id=? ");
			}
			if(null!=po.getUi_uid()){
				params.add(po.getUi_uid());
				sb.append(" and ti_uid=? ");
			}
			if(null!=po.getUi_uname()){
				params.add(po.getUi_uname());
				sb.append(" and ti_uname=? ");
			}
			if(null!=po.getUi_rid()){
				params.add(po.getUi_rid());
				sb.append(" and ui_rid=? ");
			}
			if(null!=po.getUi_rname()){
				params.add(po.getUi_rname());
				sb.append(" and ui_rname=? ");
			}
			if(null!=po.getUi_reason()){
				params.add(po.getUi_reason());
				sb.append(" and ti_reason=? ");
			}
			if(null!=po.getUi_state()){
				params.add(po.getUi_state());
				sb.append(" and ui_state=? ");
			}
		}
		sb.append(" order by ui_id asc ");
		if(null!=pageSize && null!=pageNum){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, ImpeachUserPO.class);
	}

	@Override
	public int totalPage(ImpeachUserPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from bbs_impeachuser where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getUi_id()){
				params.add(po.getUi_id());
				sb.append(" and ui_id=? ");
			}
			if(null!=po.getUi_uid()){
				params.add(po.getUi_uid());
				sb.append(" and ti_uid=? ");
			}
			if(null!=po.getUi_uname()){
				params.add(po.getUi_uname());
				sb.append(" and ti_uname=? ");
			}
			if(null!=po.getUi_rid()){
				params.add(po.getUi_rid());
				sb.append(" and ui_rid=? ");
			}
			if(null!=po.getUi_rname()){
				params.add(po.getUi_rname());
				sb.append(" and ui_rname=? ");
			}
			if(null!=po.getUi_reason()){
				params.add(po.getUi_reason());
				sb.append(" and ti_reason=? ");
			}
			if(null!=po.getUi_state()){
				params.add(po.getUi_state());
				sb.append(" and ui_state=? ");
			}
		}
		return (int)db.getPromer(sb.toString(), params);
	}

	@Override
	public int updateImpeachUser(ImpeachUserPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update bbs_impeachuser set ui_id=ui_id ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getUi_state()){
				params.add(po.getUi_state());
				sb.append(" ,ui_state=? ");
			}
		}
		if(null!=po){
			if(null!=po.getUi_id()){
				params.add(po.getUi_id());
				sb.append(" where ui_id=? ");
			}
		}
		
		List<String> sqls=new ArrayList<String>();
		List<List<Object>> params1=new ArrayList<List<Object>>();
		params1.add(params);
		sqls.add(sb.toString());
		
		if(po.getUi_state()==2) {
			String sql="update bbs_user  set u_state=2 where u_id=?";
			List<Object> param=new ArrayList<Object>();
			param.add(po.getUi_rid());
			sqls.add(sql);
			params1.add(param);
		}
		System.out.println(po);
		System.out.println(sqls);
		System.out.println(params1);
		return db.update(sqls, params1);
	}

}
