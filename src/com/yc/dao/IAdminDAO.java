package com.yc.dao;

import java.util.List;

import com.yc.po.AdminPO;

public interface IAdminDAO {

	
	/**
	 * 管理员登陆
	 * @param AdminPO po  
	 * @return
	 * @throws Exception
	 */
	public AdminPO login(AdminPO po) throws Exception;
	
	/**
	 * 管理员注册
	 * @param AdminPO po
	 * @return
	 * @throws Exception
	 */
	public int reg(AdminPO po) throws Exception;
	
	/**
	 * 条件分页查找
	 * @param po
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<AdminPO> findByPage(AdminPO po,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 总条数
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int totalPage(AdminPO po) throws Exception;
	
	/**
	 * 修改管理员信息
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int updateAdmin(AdminPO po) throws Exception;
	
}
