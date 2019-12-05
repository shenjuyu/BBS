package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IImpeachReplyDAO;
import com.yc.dao.IImpeachUserDAO;
import com.yc.dao.impl.ImpeachReplyDAOImpl;
import com.yc.dao.impl.ImpeachUserDAOImpl;
import com.yc.po.AdminPO;
import com.yc.po.ImpeachReplyPO;
import com.yc.po.ImpeachUserPO;

@WebServlet("/impeachreply.action")
public class ImpeachReplyServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IImpeachReplyDAO impeachReplyDAO=new ImpeachReplyDAOImpl();

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
		ImpeachReplyPO impeachReplyPO=parseRequestToObject(request, ImpeachReplyPO.class);
		String dowhat=request.getParameter("dowhat");
		if("1".equals(dowhat)){
			impeachReplyPO.setRi_state(2);
		}else if("2".equals(dowhat)){
			impeachReplyPO.setRi_state(3);
		}
		if(impeachReplyPO.getRi_state()==null){
			toPrintString(response, 2);
			return;
		}
		try {
			int result=impeachReplyDAO.updateImpeachReply(impeachReplyPO);
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
		ImpeachReplyPO impeachReplyPO=parseRequestToObject(request, ImpeachReplyPO.class);
		//第几页
		String pageNum=request.getParameter("page");
		//每页行数
		String pageSize=request.getParameter("rows");
		AdminPO adminpo=(AdminPO)request.getSession().getAttribute("admin");
		if(null==adminpo){
			return;
		}
		List<ImpeachReplyPO> list=null;
		try {
			if(null!=pageNum && null!=pageSize && !"".equals(pageNum) && !"".equals(pageSize)){
				list=impeachReplyDAO.findByPage(impeachReplyPO, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				toPrintString(response, list,impeachReplyDAO.totalPage(impeachReplyPO));
			}else {
				list=impeachReplyDAO.findByPage(impeachReplyPO, null, null);
				toPrintString(response, list);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImpeachReplyPO po=parseRequestToObject(request, ImpeachReplyPO.class);
		System.out.println(po);
		try {
			int result=impeachReplyDAO.addImpeachReply(po);
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
