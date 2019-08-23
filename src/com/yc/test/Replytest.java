package com.yc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yc.dao.IReplyDAO;
import com.yc.dao.impl.ReplyDAOImpl;
import com.yc.vo.TopicVO;

public class Replytest {
	IReplyDAO dao =new ReplyDAOImpl();
	TopicVO vo =new TopicVO();
	
	@Test
	public void findReplyNumtest() throws Exception{
		
		List<Long> num = new ArrayList<Long>();
		Integer pageNum =2;
		Integer pageSize =3;
		num=dao.findByTid(vo, pageNum, pageSize);
		for(int i=0 ; i<num.size();i++){
			System.out.println(num.get(i));
			
		}
	}
}
