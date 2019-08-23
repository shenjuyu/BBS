package com.yc.dao;

import java.util.List;

import com.yc.po.UserPO;

public interface IUserDAO {

	/**
	 * 注册用户
	 * @param UserPO po
	 * @return
	 * @throws Exception
	 */
	public int regUser(UserPO po) throws Exception;
	
	/**
	 * 用户登录
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public UserPO login(UserPO po) throws Exception;
	
	/**
	 * 
	 * @param po
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<UserPO> findUserByPage(UserPO po,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 总页数
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int totalPage(UserPO po) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int updateUser(UserPO po) throws Exception;
}
