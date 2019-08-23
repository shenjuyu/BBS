package com.yc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IUserDAO;
import com.yc.dao.impl.UserDAOImpl;
import com.yc.po.AdminPO;
import com.yc.po.BoardPO;
import com.yc.po.UserPO;
import com.yc.util.LogUtil;

@WebServlet("/user.action")
public class UserServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserDAO userdao=new UserDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("login".equals(op)){
			doLogin(request,response);
		}else if("findcookie".equals(op)){
			doFindCookie(request,response);
		}else if("findsession".equals(op)){
			doFindSession(request,response);
		}else if("findbypage".equals(op)){
			doFindByPage(request,response);
		}else if("updatestate".equals(op)){
			doUpdateState(request,response);
		}else if("reg".equals(op)){
			doreg(request,response);
		}else if("outlogin".equals(op)){
			doOutLogin(request,response);
		}
	}

	private void doOutLogin(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		request.getSession().removeAttribute("user");
		toPrintString(response, 1);
	}

	private void doreg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserPO po =parseRequestToObject(request, UserPO.class);
		try {
			int user =userdao.regUser(po);
			if(1==user){
				response.sendRedirect("login.html");
			}else{
				response.sendRedirect("reg.html");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doUpdateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserPO po=parseRequestToObject(request, UserPO.class);
		
		if(po.getU_state()==1){
			po.setU_state(2);
		}else if(po.getU_state()==2){
			po.setU_state(1);
		}
		try {
			int result=userdao.updateUser(po);
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

	/**
	 * 条件分页查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doFindByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserPO po=parseRequestToObject(request, UserPO.class);
		//第几页
		String pageNum=request.getParameter("page");
		//每页行数
		String pageSize=request.getParameter("rows");
		AdminPO adminpo=(AdminPO)request.getSession().getAttribute("admin");
		if(null==adminpo){
			return;
		}
		List<UserPO> list=null;
		
		try {
			if(null!=pageNum && null!=pageSize && !"".equals(pageNum) && !"".equals(pageSize)){
				list=userdao.findUserByPage(po, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				toPrintString(response, list,userdao.totalPage(po));
			}else{
				list=userdao.findUserByPage(po, null, null);
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
	 * 得到已登录的用户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doFindSession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserPO po=(UserPO)request.getSession().getAttribute("user");
		if(null==po){
			toPrintString(response, 2);
			return;
		}
		toPrintString(response, po);
	}

	/**
	 * 查找cookie信息 记住密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doFindCookie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie [] cookies=request.getCookies();
		UserPO po=new UserPO();
		if(null!=cookies){
			for(Cookie c:cookies){
				if("u_name".equals(c.getName())){
					String str=java.net.URLDecoder.decode(c.getValue(),"UTF-8");
					po.setU_name(str);
					
				}else if("u_pwd".equals(c.getName())){
					po.setU_pwd(c.getValue());
				}
			}
		}
		toPrintString(response, po);
		
	}

	/**
	 * 用户登录操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserPO po=parseRequestToObject(request, UserPO.class);
		String rempwd=request.getParameter("rem_pwd");
		try {
			UserPO userpo=userdao.login(po);
			if(null!=userpo){
				if(2==userpo.getU_state()){
					//该用户已被封禁
					toPrintString(response, 3);
					return;
				}
				
				request.getSession().setAttribute("user", userpo);
				if("yes".equals(rempwd)){
					//将用户名和密码添加到Cookie中
					String str=java.net.URLEncoder.encode(userpo.getU_name(),"UTF-8");
					Cookie cookie=new Cookie("u_name",str);
					Cookie cookie2=new Cookie("u_pwd",userpo.getU_pwd());
					cookie.setMaxAge(Integer.MAX_VALUE);
					cookie2.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(cookie);
					response.addCookie(cookie2);
				}else{
					//未记住密码
					Cookie [] cookie= request.getCookies();
					if(null!=cookie){
						for(Cookie c:cookie){
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
				}
				//登陆成功
				LogUtil.log.debug(userpo.getU_name()+"登陆成功");
				toPrintString(response, 1);
			}else{
				//登录失败
				toPrintString(response, 2);
			}
		} catch (Exception e) {
			LogUtil.log.debug(po.getU_name()+"登录失败"+e.getMessage());
			e.printStackTrace();
		}
		
	}
}
