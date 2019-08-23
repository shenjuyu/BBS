package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IAdminDAO;
import com.yc.dao.impl.AdminDAOImpl;
import com.yc.po.AdminPO;
import com.yc.po.BoardPO;

@WebServlet("/admin.action")
public class AdminServlet extends BaseServlet {
	
	IAdminDAO admindao=new AdminDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("login".equals(op)){
			doLogin(request,response);
		}else if("reg".equals(op)){
			doReg(request,response);
		}else if("findbypage".equals(op)){
			doFindByPage(request,response);
		}else if("findsession".equals(op)){
			doFindSession(request,response);
		}else if("updateadmin".equals(op)){
			doUpdateAdmin(request,response);
		}else if("outlogin".equals(op)){
			doOutLogin(request,response);
		}
	}

	private void doOutLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("admin");
		toPrintString(response, 1);
	}

	private void doUpdateAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminPO po=parseRequestToObject(request, AdminPO.class);
		
		//待审核 3 -->通过  1-->封禁 2 -->通过  1
		if(po.getA_state()==1){
			po.setA_state(2);
		}else if(po.getA_state()==2){
			po.setA_state(1);
		}else if(po.getA_state()==3){
			po.setA_state(1);
		}
		
		try {
			int result=admindao.updateAdmin(po);
			if(result>=1){
				//修改管理员状态成功
				toPrintString(response, 1);
			}else{
				//修改管理员状态失败
				toPrintString(response, 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doFindSession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminPO po=(AdminPO)request.getSession().getAttribute("admin");
		if(po==null){
			//未登录 无法修改
			toPrintString(response, 6);
			return;
		}
		toPrintString(response, po);
		
	}

	private void doFindByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminPO po=parseRequestToObject(request, AdminPO.class);
		//第几页
		String pageNum=request.getParameter("page");
		//每页行数
		String pageSize=request.getParameter("rows");
		AdminPO adminpo=(AdminPO)request.getSession().getAttribute("admin");
		if(null==adminpo){
			return;
		}
		List<AdminPO> list=null;
		try {
			if(null!=pageNum && null!=pageSize && !"".equals(pageNum) && !"".equals(pageSize)){
				list=admindao.findByPage(po, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				toPrintString(response, list,admindao.totalPage(po));
			}else {
				list=admindao.findByPage(po, null, null);
				toPrintString(response, list);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doReg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminPO po=parseRequestToObject(request, AdminPO.class);
		try {
			int result=admindao.reg(po);
			if(1<=result){
				toPrintString(response, 1);
			}else{
				toPrintString(response, 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminPO adminpo=parseRequestToObject(request,AdminPO.class);
		try {
			AdminPO po=admindao.login(adminpo);
			if(null!=po){
				//该账号处于未审核状态
				if(po.getA_state()==3){
					toPrintString(response, 3);
					return;
				}
				//该账户处于封禁状态
				if(po.getA_state()==2){
					toPrintString(response, 4);
					return;
				}
				//登陆成功
				request.getSession().setAttribute("admin", po);
				toPrintString(response, 1);
			}else{
				//登陆失败
				toPrintString(response, 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
