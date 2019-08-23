package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IUserDAO;
import com.yc.po.UserPO;

public class UserDAOImpl implements IUserDAO {
	
	DbHepler db=new DbHepler();

	@Override
	public int regUser(UserPO po) throws Exception {
		String sql ="insert into bbs_user values (null,?,?,?,now(),?,1)";
		List<Object> params =new  ArrayList<Object>();
		params.add(po.getU_name());
		params.add(po.getU_pwd());
		params.add(po.getU_head());
		params.add(po.getU_sex());
		
		return db.update(sql, params);
	}

	@Override
	public UserPO login(UserPO po) throws Exception {
		String sql="select u_id,u_name,u_pwd,u_head,u_regtime,u_sex,u_state"
				+ " from bbs_user where u_name=? and u_pwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getU_name());
		params.add(po.getU_pwd());
		List<UserPO> list=db.findMutil(sql, params, UserPO.class);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<UserPO> findUserByPage(UserPO po, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select u_id,u_name,u_pwd,u_head,u_regtime,u_sex,u_state from bbs_user where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getU_id()){
				params.add(po.getU_id());
				sb.append(" and u_id=? ");
			}
			if(null!=po.getU_name()){
				params.add(po.getU_name());
				sb.append(" and u_name=? ");
			}
			if(null!=po.getU_pwd()){
				params.add(po.getU_pwd());
				sb.append(" and u_pwd=? ");
			}
			if(null!=po.getU_head()){
				params.add(po.getU_head());
				sb.append(" and u_head=? ");
			}
			if(null!=po.getU_regtime()){
				params.add(po.getU_regtime());
				sb.append(" and u_regtime=? ");
			}
			if(null!=po.getU_sex()){
				params.add(po.getU_sex());
				sb.append(" and u_sex=? ");
			}
			if(null!=po.getU_state()){
				params.add(po.getU_state());
				sb.append(" and u_state=? ");
			}
		}
		sb.append(" order by u_id asc ");
		if(null!=pageSize && null!=pageNum){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, UserPO.class);
	}

	@Override
	public int totalPage(UserPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from bbs_user where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getU_id()){
				params.add(po.getU_id());
				sb.append(" and u_id=? ");
			}
			if(null!=po.getU_name()){
				params.add(po.getU_name());
				sb.append(" and u_name=? ");
			}
			if(null!=po.getU_pwd()){
				params.add(po.getU_pwd());
				sb.append(" and u_pwd=? ");
			}
			if(null!=po.getU_head()){
				params.add(po.getU_head());
				sb.append(" and u_head=? ");
			}
			if(null!=po.getU_regtime()){
				params.add(po.getU_regtime());
				sb.append(" and u_regtime=? ");
			}
			if(null!=po.getU_sex()){
				params.add(po.getU_sex());
				sb.append(" and u_sex=? ");
			}
			if(null!=po.getU_state()){
				params.add(po.getU_state());
				sb.append(" and u_state=? ");
			}
		}
		return (int)db.getPromer(sb.toString(), params);
	}

	@Override
	public int updateUser(UserPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update bbs_user  set u_id=u_id ");
		List<Object> params=new ArrayList<Object>();
		if(null!=po){
			if(null!=po.getU_state()){
				params.add(po.getU_state());
				sb.append(" ,u_state=? ");
			}
			if(null!=po.getU_name()){
				params.add(po.getU_name());
				sb.append(" ,u_name=? ");
			}
			if(null!=po.getU_pwd()){
				params.add(po.getU_pwd());
				sb.append(" ,u_pwd=? ");
			}
			if(null!=po.getU_sex()){
				params.add(po.getU_sex());
				sb.append(" ,u_sex=? ");
			}
			if(null!=po.getU_head()){
				params.add(po.getU_head());
				sb.append(" ,u_head=? ");
			}
		}
		sb.append(" where 1=1  ");
		if(null!=po){
			if(null!=po.getU_id()||!"".equals(po.getU_id())){
				params.add(po.getU_id());
				sb.append(" and u_id=? ");
			}
		}
		return db.update(sb.toString(), params);
	}

}
