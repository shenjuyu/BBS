package com.yc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IReplyDAO;
import com.yc.dao.ITopicDAO;
import com.yc.dao.impl.ReplyDAOImpl;
import com.yc.dao.impl.TopicDAOImpl;
import com.yc.vo.TopicVO;
import com.yc.po.ReplyPO;
import com.yc.po.UserPO;
import com.yc.util.LogUtil;

@WebServlet("/reply.action")
public class ReplyServlet extends BaseServlet {
	
	IReplyDAO replydao=new ReplyDAOImpl();
	ITopicDAO topicdao =new TopicDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("addreply".equals(op)){
			doAddReply(request,response);
		}else if("find".equals(op)){
			dofindpage(request,response);
		}else if("findlouzhu".equals(op)){
			dofindzhulou(request,response);
		}else if("delete".equals(op)){
			doDeleteRpely(request,response);
		}else if("findById".equals(op)) {
			doFindById(request,response);
		}
	}
	
	private void doFindById(HttpServletRequest request, HttpServletResponse response) {
		ReplyPO po=parseRequestToObject(request, ReplyPO.class);
		try {
			ReplyPO replyPo=replydao.findReplyByTid(po);
			toPrintString(response, replyPo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//查询主楼
	private void dofindzhulou(HttpServletRequest request, HttpServletResponse response) {
		TopicVO vo=parseRequestToObject(request, TopicVO.class);
		List<TopicVO> list =new ArrayList<TopicVO>();
		try {
			list=replydao.findLouzhu(vo);
			toPrintString(response, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//分页查询
	private void dofindpage(HttpServletRequest request, HttpServletResponse response) {
		TopicVO vo=parseRequestToObject(request, TopicVO.class);
		//得到回复的条数size()
		List<TopicVO> list =new ArrayList<TopicVO>();
		//存储主楼信息
		List<TopicVO> list0 =new ArrayList<TopicVO>();
		//存储（回复信息）和（主楼信息）
		List<Object> params =new ArrayList<Object>();
		Integer nowpage = Integer.parseInt(request.getParameter("nowpage"));
		Integer pageSize =6;
		Integer AllpageNum =0;
		
		try {
			//
			list0=replydao.findLouzhu(vo);
			//根据tid查询的主题的回复总条数
			list=replydao.find(vo);
			//根据总条数得到总页数
			AllpageNum=list.size()/pageSize;
			if(list.size()%pageSize!=0){
				AllpageNum=AllpageNum+1;
			}
			
			//分页查询
			List<TopicVO>list1 =replydao.findbypage(vo, nowpage, pageSize);
			
			
			//将所有的信息（楼主信息）（回复信息）整合到一起
			//先添加（楼主信息，因为楼主只有一条）再用for循环添加（回复信息）到params中
			params.add(list0.get(0));
			for(int i=0;i<list1.size();i++){
				params.add(list1.get(i));
			}
			
			toPrintString(response, params,AllpageNum);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//删除信息
	private void doDeleteRpely(HttpServletRequest request, HttpServletResponse response){
		TopicVO vo=parseRequestToObject(request, TopicVO.class);
		Integer rid = Integer.parseInt(request.getParameter("ridd"));
		vo.setR_id(rid);
		try {
			Integer i=replydao.deleteReply(vo);
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

	private void doAddReply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReplyPO replypo=parseRequestToObject(request, ReplyPO.class);
		UserPO userpo=(UserPO)request.getSession().getAttribute("user");
		if(null==userpo){
			toPrintString(response, 2);
			return;
		}
		replypo.setU_id(userpo.getU_id());
		
		try {
			int result=replydao.addReply(replypo);
			LogUtil.log.debug(userpo.getU_name()+"回帖成功"+replypo.getT_id());
			toPrintString(response, result);
		} catch (Exception e) {
			LogUtil.log.debug(userpo.getU_name()+"回帖失败"+e.getMessage());
			e.printStackTrace();
		}
		
	}
}
