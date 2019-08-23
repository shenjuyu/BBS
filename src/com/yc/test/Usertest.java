package com.yc.test;



import org.junit.Test;

import com.yc.commons.DbHepler;
import com.yc.dao.IUserDAO;
import com.yc.dao.impl.UserDAOImpl;
import com.yc.po.UserPO;

public class Usertest {
	DbHepler db =new DbHepler();
	IUserDAO dao=new UserDAOImpl();
	
	@Test
	public void userRegtest() throws Exception{
		UserPO po =new UserPO();
		po.setU_name("zhangsan");
		po.setU_pwd("a");
		po.setU_head("haha");
		po.setU_sex("男");
		int i=dao.regUser(po);
		System.out.println(i);
		
	}

}
