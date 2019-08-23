package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IAdminDAO;
import com.yc.po.AdminPO;

public class AdminDAOImpl implements IAdminDAO {
	
	DbHepler db=new DbHepler();

	@Override
	public AdminPO login(AdminPO po) throws Exception {
		String sql="select a_id,a_pwd,a_tel,a_name,a_power,a_state from bbs_admin where a_name=? and a_pwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getA_name());
		params.add(po.getA_pwd());
		List<AdminPO> list=db.findMutil(sql, params, AdminPO.class);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int reg(AdminPO po) throws Exception {
		String sql="insert into bbs_admin values(null,?,?,?,2,3)";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getA_name());
		params.add(po.getA_pwd());
		params.add(po.getA_tel());
		return db.update(sql, params);
	}

	@Override
	public List<AdminPO> findByPage(AdminPO po,Integer pageNum,Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select a_id,a_name,a_pwd,a_tel,a_power,a_state from bbs_admin where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getA_id()){
				params.add(po.getA_id());
				sb.append(" and a_id=? ");
			}
			if(null!=po.getA_name()){
				params.add(po.getA_name());
				sb.append(" and a_name=? ");
			}
			if(null!=po.getA_pwd()){
				params.add(po.getA_pwd());
				sb.append(" and a_pwd=? ");
			}
			if(null!=po.getA_state()){
				params.add(po.getA_state());
				sb.append(" and a_state=? ");
			}
			if(null!=po.getA_power()){
				params.add(po.getA_power());
				sb.append(" and a_power=? ");
			}
			if(null!=po.getA_tel()){
				params.add(po.getA_tel());
				sb.append(" and a_tel=? ");
			}
		}
		sb.append(" order by a_id asc ");
		if(null!=pageSize && null!=pageNum){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, AdminPO.class);
	}

	@Override
	public int totalPage(AdminPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from bbs_admin where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getA_id()){
				params.add(po.getA_id());
				sb.append(" and a_id=? ");
			}
			if(null!=po.getA_name()){
				params.add(po.getA_name());
				sb.append(" and a_name=? ");
			}
			if(null!=po.getA_pwd()){
				params.add(po.getA_pwd());
				sb.append(" and a_pwd=? ");
			}
			if(null!=po.getA_state()){
				params.add(po.getA_state());
				sb.append(" and a_state=? ");
			}
			if(null!=po.getA_power()){
				params.add(po.getA_power());
				sb.append(" and a_power=? ");
			}
			if(null!=po.getA_tel()){
				params.add(po.getA_tel());
				sb.append(" and a_tel=? ");
			}
		}
		return (int)db.getPromer(sb.toString(), params);
	}

	@Override
	public int updateAdmin(AdminPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update bbs_admin  set a_id=a_id ");
		List<Object> params=new ArrayList<Object>();
		if(null!=po){
			if(null!=po.getA_name()){
				params.add(po.getA_name());
				sb.append(" ,a_name=? ");
			}
			if(null!=po.getA_pwd()){
				params.add(po.getA_pwd());
				sb.append(" ,a_pwd=? ");
			}
			if(null!=po.getA_state()){
				params.add(po.getA_state());
				sb.append(" ,a_state=? ");
			}
			if(null!=po.getA_power()){
				params.add(po.getA_power());
				sb.append(" ,a_power=? ");
			}
			if(null!=po.getA_tel()){
				params.add(po.getA_tel());
				sb.append(" ,a_tel=? ");
			}
		}
		sb.append(" where 1=1  ");
		if(null!=po){
			if(null!=po.getA_id()){
				params.add(po.getA_id());
				sb.append(" and a_id=? ");
			}
		}
		return db.update(sb.toString(), params);
	}

}
