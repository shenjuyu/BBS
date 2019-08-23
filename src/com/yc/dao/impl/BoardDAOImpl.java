package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.commons.DbHepler;
import com.yc.dao.IBoardDAO;
import com.yc.po.BoardPO;
import com.yc.vo.BoardVO;

public class BoardDAOImpl implements IBoardDAO {
	
	DbHepler db=new DbHepler();

	@Override
	public int addBoard(BoardPO po) throws Exception {
		String sql="insert into bbs_board values(null,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(po.getB_name());
		params.add(po.getB_parentid());
		return db.update(sql, params);
	}

	@Override
	public List<BoardPO> findByPage(BoardPO po, Integer pageNum, Integer pageSize) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select b_id,b_name,b_parentid from bbs_board where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getB_id()){
				params.add(po.getB_id());
				sb.append(" and b_id=? ");
			}
			if(null!=po.getB_name()){
				params.add(po.getB_name());
				sb.append(" and b_name=? ");
			}
			if(null!=po.getB_parentid()){
				params.add(po.getB_parentid());
				sb.append(" and b_parentid=? ");
			}
		}
		
		sb.append(" order by b_id asc ");
		if(null!=pageSize && null!=pageNum){
			sb.append(" limit "+(pageNum-1)*pageSize+","+pageSize);
		}
		return db.findMutil(sb.toString(), params, BoardPO.class);
	}

	@Override
	public int totalPage(BoardPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select count(*) from bbs_board where 1=1 ");
		List<Object> params=null;
		if(null!=po){
			params=new ArrayList<Object>();
			if(null!=po.getB_id()){
				params.add(po.getB_id());
				sb.append(" and b_id=? ");
			}
			if(null!=po.getB_parentid()){
				params.add(po.getB_parentid());
				sb.append(" and b_parentid=? ");
			}
			if(null!=po.getB_name()){
				params.add(po.getB_name());
				sb.append(" and b_name=? ");
			}
		}
		
		return (int)db.getPromer(sb.toString(), params);
	}

	@Override
	public List<BoardVO> findBoardDetils(BoardVO vo) throws Exception {
		String sql="select a.*,count(a.t_id) num from (select * "
				+ "from bbs_boardview order by t_modifytime desc limit 11111111111111 ) "
				+ "a where a.b_id=? group by a.b_id";
		List<Object> params=new ArrayList<Object>();
		params.add(vo.getB_id());
		return db.findMutil(sql, params, BoardVO.class);
	}

	@Override
	public int updataBoard(BoardPO po) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update bbs_board  set b_id=b_id ");
		List<Object> params=new ArrayList<Object>();
		if(null!=po){
			if(null!=po.getB_parentid()||!"".equals(po.getB_parentid())){
				params.add(po.getB_parentid());
				sb.append(" ,b_parentid=? ");
			}
			if(null!=po.getB_name()||!"".equals(po.getB_name())){
				params.add(po.getB_name());
				sb.append(" ,b_name=? ");
			}
		}
		sb.append(" where 1=1  ");
		if(null!=po){
			if(null!=po.getB_id()||!"".equals(po.getB_id())){
				params.add(po.getB_id());
				sb.append(" and b_id=? ");
			}
		}
		return db.update(sb.toString(), params);
	}

}
