package com.yc.dao;

import java.util.List;

import com.yc.po.BoardPO;
import com.yc.vo.BoardVO;


public interface IBoardDAO {

	
	/**
	 * 添加板块
	 * @param BoardPO po
	 * @return
	 * @throws Exception
	 */
	public int addBoard(BoardPO po) throws Exception;
	
	/**
	 * 查找板块(可分页查询)
	 * @param BoardPO po
	 * @param pageNum 第几页
	 * @param pageSize 每页的条数
	 * @return
	 * @throws Exception
	 */
	public List<BoardPO> findByPage(BoardPO po,Integer pageNum,Integer pageSize) throws Exception;
	
	/**
	 * 分页 总条数
	 * @param BoardPO po
	 * @return
	 * @throws Exception
	 */
	public int totalPage(BoardPO po) throws Exception;
	
	/**
	 * 根据父板块id查询所有子版块的详细信息  子版块名、子版块中各板块帖子总数、
	 * 子版块中最后发表的帖子的标题、发帖人、发帖时间
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> findBoardDetils(BoardVO vo) throws Exception;
	
	/**
	 * 修改板块信息
	 * @param po
	 * @return
	 * @throws Exception
	 */
	public int updataBoard(BoardPO po) throws Exception;
}
