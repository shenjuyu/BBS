package com.yc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.dao.IBoardDAO;
import com.yc.dao.impl.BoardDAOImpl;
import com.yc.po.AdminPO;
import com.yc.po.BoardPO;
import com.yc.util.LogUtil;
import com.yc.vo.BoardVO;
@WebServlet("/board.action")
public class BoardServlet extends BaseServlet {
	
	IBoardDAO boarddao=new BoardDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("find".equals(op)){
			doFind(request,response);
		}else if("findbypage".equals(op)){
			doFindByPage(request,response);
		}else if("updateboard".equals(op)){
			doUpdateBoard(request,response);
		}else if("addboard".equals(op)){
			doAddBoard(request,response);
		}
	}


	private void doAddBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardPO boardPO=parseRequestToObject(request, BoardPO.class);
		
		try {
			int result=boarddao.addBoard(boardPO);
			if(result>=1){
				//添加成功
				toPrintString(response, 1);
			}else{
				//添加失败
				toPrintString(response, 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void doUpdateBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardPO boardPO=parseRequestToObject(request, BoardPO.class);
		
		try {
			int result=boarddao.updataBoard(boardPO);
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
		BoardPO boardpo=parseRequestToObject(request, BoardPO.class);

		//第几页
		String pageNum=request.getParameter("page");
		//每页行数
		String pageSize=request.getParameter("rows");
		AdminPO adminpo=(AdminPO)request.getSession().getAttribute("admin");
		if(null==adminpo){
			return;
		}
		List<BoardPO> list=null;
		try {
			if(null!=pageNum && null!=pageSize && !"".equals(pageNum) && !"".equals(pageSize)){
				list=boarddao.findByPage(boardpo, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
				toPrintString(response, list,boarddao.totalPage(boardpo));
			}else {
				list=boarddao.findByPage(boardpo, null, null);
				toPrintString(response, list);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	/**
	 * 查询板块
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doFind(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//只传入了父板块id  0
		BoardPO po=parseRequestToObject(request, BoardPO.class);
		
		try {
			//主界面显示的详细信息
			List<BoardVO> boarddetils=new ArrayList<BoardVO>();
			//查询所有主板块名 id
			List<BoardPO> list=boarddao.findByPage(po, null, null);
			
			for (BoardPO boardPO : list) {
				//声明主板块名对象
				BoardVO vo=new BoardVO();
				//获得每一行的主板块名
				vo.setParent_name(boardPO.getB_name());
				boarddetils.add(vo);
				
				//声明查询子版块用 BoardPO 并赋值setB_parentid
				BoardPO childboardPO=new BoardPO();
				childboardPO.setB_parentid(boardPO.getB_id());
				//根据父板块id查询子版块
				List<BoardPO> childboard=boarddao.findByPage(childboardPO, null, null);
				for (BoardPO boardPO2 : childboard) {
					//声明查询详细信息用 |存储主界面显示的详细信息列表 用  BoardVO
					BoardVO childboardvo=new BoardVO();
					childboardvo.setB_parentid(boardPO2.getB_parentid());
					childboardvo.setB_name(boardPO2.getB_name());
					childboardvo.setB_id(boardPO2.getB_id());
					//得到每一行的详细信息
					List<BoardVO> childboarddetils=boarddao.findBoardDetils(childboardvo);
					if(childboarddetils==null||childboarddetils.size()<=0){
						boarddetils.add(childboardvo);
					}else{
						boarddetils.add(childboarddetils.get(0));
					}
					
				}
			}
			//将主界面显示的详细信息列表以json格式传到index.html中
			toPrintString(response, boarddetils);
		} catch (Exception e) {
			LogUtil.log.debug("查询板块错误"+e.getMessage());
			e.printStackTrace();
		}
		
	}
}
