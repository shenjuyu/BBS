package com.yc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yc.dao.ITopicDAO;
import com.yc.dao.impl.TopicDAOImpl;
import com.yc.vo.TopicVO;

public class Topictest {
	
	ITopicDAO dao =new TopicDAOImpl();
	
	@Test
	public void findpagetest() throws Exception{
		int pageSize =4 ;
		int pageNum =1;
		TopicVO vo =new TopicVO();
		List<TopicVO>list =new ArrayList<TopicVO>();
		 list=dao.findByPage(vo, pageNum, pageSize);
		System.out.println(list);
	}
	
	@Test
	public void findtest() throws Exception{
		TopicVO vo =new TopicVO();
		List<TopicVO>list =new ArrayList<TopicVO>();
		 list=dao.findByPage(vo, null, null);
		 //System.out.println(list.size());
		 for(int i = 0 ; i<=list.size();i++){
			 System.out.println(list.get(i).getT_id()); 
		 }
		
	}
	
	

}
