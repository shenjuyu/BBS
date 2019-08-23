package com.yc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IBoardDAO;
import com.yc.dao.IReplyDAO;
import com.yc.dao.ITopicDAO;
import com.yc.dao.impl.BoardDAOImpl;
import com.yc.dao.impl.ReplyDAOImpl;
import com.yc.dao.impl.TopicDAOImpl;
import com.yc.po.BoardPO;
import com.yc.po.TopicPO;
import com.yc.po.UserPO;
import com.yc.util.LogUtil;
import com.yc.vo.BoardVO;
import com.yc.vo.TopicVO;

@WebServlet("/topic.action")
public class TopicServlet extends BaseServlet {
	IBoardDAO boarddao =new BoardDAOImpl();
	ITopicDAO topicdao=new TopicDAOImpl();
	IReplyDAO rplydao=new ReplyDAOImpl(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("addtopic".equals(op)){
			doAddTopic(request,response);
		}else if("find".equals(op)){
			dofindBypage(request,response);
		}else if("delete".equals(op)){
			doDeleteRpely(request,response);
		}
	}
	
	
	private void doDeleteRpely(HttpServletRequest request, HttpServletResponse response) {
		TopicVO vo=parseRequestToObject(request, TopicVO.class);
		try {
			Integer i=topicdao.deleteReply(vo);
			if(i>=1){
				i=1;
			}else{
				i=0;
			}
			toPrintString(response, i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

	private void dofindBypage(HttpServletRequest request, HttpServletResponse response) {
	
		TopicVO vo =parseRequestToObject(request, TopicVO.class);
		//存放板块信息
		List<TopicVO> Bids =new ArrayList<TopicVO>();
		List<Object> params =new ArrayList<Object>();
		
		
		//现在的页数
		Integer nowpage = Integer.parseInt(request.getParameter("nowpage"));
		Integer AllpageNum =0;
		Integer pageSize =6;
		List<TopicVO> list =new ArrayList<TopicVO>();
		List<TopicVO> list1=new ArrayList<TopicVO>();
		try {
			//得到每个话题的回复数量
			List<Long> num = rplydao.findByTid(vo, nowpage, pageSize);
			//查询总条数
			list=topicdao.findByPage(vo, null, null);
			
			//得到总页数
			AllpageNum=list.size()/pageSize;
			if(list.size()%pageSize!=0){
				AllpageNum=AllpageNum+1;
			}
			
			//显示的话题信息
			list1=topicdao.findByPage(vo, nowpage, pageSize);
			
			//显示板块信息
			BoardPO po =new BoardPO();
			for(TopicVO topicvo:list1){
				po.setB_id(topicvo.getB_id());
				List<BoardPO>list2=boarddao.findByPage(po, null, null);
				topicvo.setB_name(list2.get(0).getB_name());
			}
			
			toPrintString(response, num, list1, AllpageNum);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doAddTopic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TopicPO topicpo=parseRequestToObject(request, TopicPO.class);
		UserPO userpo=(UserPO)request.getSession().getAttribute("user");
		if(null==userpo){
			toPrintString(response, 2);
			return;
		}
		topicpo.setU_id(userpo.getU_id());
		
		try {
			int result=topicdao.addTopic(topicpo);
			LogUtil.log.debug(userpo.getU_name()+"发帖成功");
			toPrintString(response, result);
		} catch (Exception e) {
			LogUtil.log.debug(userpo.getU_name()+"发帖失败"+e.getMessage());
			e.printStackTrace();
		}
	}
}
