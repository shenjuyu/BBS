package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IImpeachUserDAO;
import com.yc.dao.impl.ImpeachUserDAOImpl;
import com.yc.po.AdminPO;
import com.yc.po.ImpeachUserPO;

@WebServlet("/impeachuser.action")
public class ImpeachUserServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IImpeachUserDAO impeachuserdao=new ImpeachUserDAOImpl();

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
		ImpeachUserPO impeachUserPO=parseRequestToObject(request, ImpeachUserPO.class);
		String dowhat=request.getParameter("dowhat");
		if("1".equals(dowhat)){
			impeachUserPO.setUi_state(2);
		}else if("2".equals(dowhat)){
			impeachUserPO.setUi_state(3);
		}
		if(impeachUserPO.getUi_state()==null){
			toPrintString(response, 2);
			return;
		}
		try {
			int result=impeachuserdao.updateImpeachUser(impeachUserPO);
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
		ImpeachUserPO impeachUserPO=parseRequestToObject(request, ImpeachUserPO.class);

		//第几页
		String pageNum=request.getParameter("page");
		//每页行数
		String pageSize=request.getParameter("rows");
		AdminPO adminpo=(AdminPO)request.getSession().getAttribute("admin");
		if(null==adminpo){
			return;
		}
		List<ImpeachUserPO> list=null;
		try {
			if(null!=pageNum && null!=pageSize && !"".equals(pageNum) && !"".equals(pageSize)){
				list=impeachuserdao.findByPage(impeachUserPO, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				toPrintString(response, list,impeachuserdao.totalPage(impeachUserPO));
			}else {
				list=impeachuserdao.findByPage(impeachUserPO, null, null);
				toPrintString(response, list);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImpeachUserPO po=parseRequestToObject(request, ImpeachUserPO.class);
		
		try {
			int result=impeachuserdao.addImpeachUser(po);
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
