package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IImpeachTopicDAO;
import com.yc.dao.impl.ImpeachTopicDAOImpl;
import com.yc.po.AdminPO;
import com.yc.po.ImpeachTopicPO;
import com.yc.po.ImpeachTopicPO;

@WebServlet("/impeachtopic.action")
public class ImpeachTopicServlet extends BaseServlet {
	
	IImpeachTopicDAO impeachtopicdao=new ImpeachTopicDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("add".equals(op)){
			doAdd(request,response);
		}else if("findbypage".equals(op)){
			doFindByPage(request,response);
		}else if("updatestate".equals(op)){
			doUpdateState(request,response);
		}
	}
	
	private void doUpdateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImpeachTopicPO impeachTopicPO=parseRequestToObject(request, ImpeachTopicPO.class);
		String dowhat=request.getParameter("dowhat");
		if("1".equals(dowhat)){
			impeachTopicPO.setTi_state(2);
		}else if("2".equals(dowhat)){
			impeachTopicPO.setTi_state(3);
		}
		if(impeachTopicPO.getTi_state()==null){
			toPrintString(response, 2);
			return;
		}
		try {
			int result=impeachtopicdao.updateImpeachTopic(impeachTopicPO);
			if(result>=1){
				//修改成功
				toPrintString(response, 1);
			}else{
				//修改失败
				toPrintString(response, 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doFindByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImpeachTopicPO ImpeachTopicPO=parseRequestToObject(request, ImpeachTopicPO.class);

		//第几页
		String pageNum=request.getParameter("page");
		//每页行数
		String pageSize=request.getParameter("rows");
		AdminPO adminpo=(AdminPO)request.getSession().getAttribute("admin");
		if(null==adminpo){
			return;
		}
		List<ImpeachTopicPO> list=null;
		try {
			if(null!=pageNum && null!=pageSize && !"".equals(pageNum) && !"".equals(pageSize)){
				list=impeachtopicdao.findByPage(ImpeachTopicPO, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				toPrintString(response, list,impeachtopicdao.totalPage(ImpeachTopicPO));
			}else {
				list=impeachtopicdao.findByPage(ImpeachTopicPO, null, null);
				toPrintString(response, list);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImpeachTopicPO po=parseRequestToObject(request, ImpeachTopicPO.class);
		
		try {
			int result=impeachtopicdao.addImpeachTopic(po);
			if(result>=1){
				//举报成功
				toPrintString(response, 1);
			}else{
				//举报失败
				toPrintString(response, 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
