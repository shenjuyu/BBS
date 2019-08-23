package com.yc.commons;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
//import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * 对加载驱动、获取连接对象、关闭所有资源
 * 设置参数、一次处理一条sql语句、批处理操作 一次处理一条或多条sql语句
 * 返回单条记录、返回多条记录、获取结果集中所有的列名进行封装
 * @author DELL
 *
 */
public class DbHepler {

	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private Context ctx=null;
	private Context envContext=null;
	private DataSource ds=null;
	
    public DbHepler(){
    	try {
			//初始化查找命名空间
			ctx = new InitialContext();  
			//参数java:/comp/env为固定路径   
			envContext = (Context)ctx.lookup("java:/comp/env"); 
			//参数jdbc/mysql为数据源和JNDI绑定的名字
			ds = (DataSource)envContext.lookup("jdbc/mysql");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	/**
	 * 获取连接对象
	 * @return
	 * @throws SQLException
	 */
	public Connection getConn() throws SQLException{
		conn =ds.getConnection();
		return conn;
	}
	
	/**
	 * 关闭所有资源
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(null!=rs){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=pstmt){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=conn){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置参数对象
	 * @param pstmt	表示预编译的SQL语句的对象，解决了sql注入的问题
	 * @param params	将参数放在list集合中方便使用,Object是因为不知道是什么数据类型所以使用，添加进来的参数的顺序必须和?顺序一致
	 * @throws SQLException 
	 */
	public void setParamterObject(PreparedStatement pstmt,List<Object> params) throws SQLException{
		if(null==params ||params.size()<=0){	//PreparedStatement中无sql语句
			return;
		}
		for (int i = 0; i < params.size(); i++) {
			pstmt.setObject(i+1, params.get(i));	
			//List中下标从0开始，PreparedStatement的setObject(parameterIndex, x)的方法中parameterIndex从1开始计数
		}
	}
	
	/**
	 * jdbc2.0默认事务都是自动提交 setAutoCommit(true);  单条sql语句的更新操作
	 * 一次处理一条sql语句
	 * @param sql	传入sql语句
	 * @param params	
	 * @return
	 * @throws SQLException
	 */
	public int update(String sql,List<Object> params) throws SQLException{
		int result=0;
		try {
			//获取连接对象
			conn=getConn();  //本类方法
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamterObject(pstmt,params); //本类方法
			//执行
			result=pstmt.executeUpdate();
		} finally {
			closeAll(conn,pstmt,rs);
		}
		
		return result;
	}
	
	/**
	 * 批处理操作 
	 * 一次处理一条或多条sql语句  insert update delete
	 * 但是这些操作在同一事务中
	 * @param sql	多条sql语句
	 * @param params	每条sql语句必须对应param集合
	 * @return
	 * @throws SQLException 
	 */
	public int update(List<String> sqls,List< List<Object> > params) throws SQLException{
		int result=0;
		try {
			conn=getConn();
			//将事务设置手动提交
			conn.setAutoCommit(false);
			if (null==sqls || sqls.size()<=0) {
				return 0;
			}
			//循环sql语句
			for(int i=0 ;i<sqls.size();i++){
				//获取单个sql语句创建预编译对象
				pstmt=conn.prepareStatement(sqls.get(i));
				//获取对应sql语句参数
				List<Object> param = params.get(i);
				//设置参数
				setParamterObject(pstmt, param);
				result=pstmt.executeUpdate();
				//执行的过程中，如果某条sql语句未执行成功，此时直接回滚，并跳出方法
				if (result==0) {
					conn.rollback();
					return result;
				}
			}
			conn.commit();
		} catch (Exception e) {
			//执行操作发生异常，必须进行回滚
			conn.rollback();
		}finally {
			//事务恢复初始状态
			conn.setAutoCommit(true);
			closeAll(conn, pstmt, rs);
		}
		return result;
	}
	
	/**
	 * 查询操作返回对象
	 * @param sql sql语句
	 * @param params 参数
	 * @param cls 类
	 * @return  返回对象集合
	 * @throws Exception
	 */
	public <E>List<E> findMutil(String sql,List<Object> params,Class<E> cls) throws Exception{
		List<E> list=new ArrayList<E>();
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamterObject(pstmt, params);
			rs=pstmt.executeQuery();
			List<String> columNames=getallColumnNames(rs);
			E e;//对象
			Object obj;//获取对应字段的值
			//获取所有的方法  通过反射
			Method [] methods=cls.getDeclaredMethods();
			
			while(rs.next()){
				//创建对象
				e=cls.newInstance();//调用无参的构造方法
				//循环列名
				for(String columName:columNames){
					String typeName=null;//值的数据类型
					//获取到值
					obj=rs.getObject(columName);
					//判断是否为空
					if(null==obj){
						continue;
					}
					typeName=obj.getClass().getName();
					//激活setXXX 完成赋值操作
					for(Method m:methods){		
						//找到对应列的名字与之匹配的set方法
						if(("set"+columName).equalsIgnoreCase(m.getName())){
							//值的数据类型与固定类型字符串进行比较
							if("java.math.BigDecimal".equals(typeName)){
								m.invoke(e, rs.getInt(columName));//oracle整形处理
							}else if("java.lang.Integer".equals(typeName)){
								m.invoke(e, rs.getInt(columName));
							}else if("java.lang.Double".equals(typeName)){
								m.invoke(e, rs.getDouble(columName));
							}else if("java.lang.Long".equals(typeName)){
								m.invoke(e, rs.getLong(columName));
							}else if("java.lang.Float".equals(typeName)){
								m.invoke(e, rs.getFloat(columName));
							}else if("java.lang.String".equals(typeName)){
								m.invoke(e, rs.getString(columName));
							}else if ("oracle.sql.BLOB".equals(typeName)) {
//							//getObject(name)  获取此的当前行中指定列的值
//							Blob blob=(Blob)obj;
//							//blob 表示（映射）在Java编程语言中的一个SQL BLOB值
//							//getBinaryStream() 将此 Blob实例指定的 BLOB值作为流 Blob 
//							InputStream in=blob.getBinaryStream();
//							byte[] bt = new byte[(int)blob.length()];
//							in.read(bt);
//							m.invoke(e, bt);
							}else{
								m.invoke(e, obj.toString());
							}
							break;
						}
					}
				}
				list.add(e);//添加对象到集合中
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 返回单条记录  select * from tableName where id=?;
	 * @param sql
	 * @param params
	 * @return map Map键值对刚好用于列和数据的对应
	 * @throws SQLException
	 * @throws IOException
	 */
	public Map<String,Object> selectSingle(String sql,List<Object> params) throws Exception{
		Map<String,Object> map=null;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamterObject(pstmt,params);
			rs=pstmt.executeQuery();
			//根据rs获取所有的列名
			List<String> columnNames=getallColumnNames(rs);
			if (rs.next()) {
				map=new HashMap<String,Object>();
				//循环列名
				for (String name : columnNames) {
					Object obj=rs.getObject(name);
					String typeName=null;
					if(null!=obj){//消除空指针异常
						//获取值的类型
						typeName=obj.getClass().getName();
					}
					//System.out.println(typeName);
					//对图片的处理
					if ("oracle.sql.BLOB".equals(typeName)) {
//						//getObject(name)  获取此的当前行中指定列的值
//						Blob blob=(Blob)rs.getObject(name);
//						//blob 表示（映射）在Java编程语言中的一个SQL BLOB值
//						//getBinaryStream() 将此 Blob实例指定的 BLOB值作为流 Blob 
//						InputStream in=blob.getBinaryStream();
//						byte[] bt = new byte[(int)blob.length()];
//						in.read(bt);
//						map.put(name, bt);
					} else {
						map.put(name, rs.getObject(name));
					}
				}
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return map;
	}
	
	/**
	 * 返回多条记录
	 * @param sql
	 * @param params
	 * @return List<Map<String , Object>> 
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<Map<String , Object>> selectMutil(String sql,List<Object> params) throws Exception{
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamterObject(pstmt, params);
			rs=pstmt.executeQuery();
			//根据rs获取所有的列名
			List<String> columnNames=getallColumnNames(rs);
			while (rs.next()) {
				map=new HashMap<String,Object>();
				//循环列名
				for (String name : columnNames) {
					Object obj=rs.getObject(name);
					String typeName=null;
					if (null!=obj) {
						//getClass() 返回此 Object的运行时类
						//获取值的类型
						typeName=obj.getClass().getName(); 
						//getName()  返回由 类对象表示的实体（类，接口，数组类，原始类型或空白）的名称
					}
					//System.out.println(typeName);
					if ("oracle.sql.BLOB".equals(typeName)) {
//						Blob blob=(Blob)rs.getObject(name);
//						InputStream in=blob.getBinaryStream();
//						byte [] bt=new byte[(int)blob.length()];
//						in.read(bt);
//						map.put(name,bt);
					} else {
						map.put(name, rs.getObject(name));
					}
				}
				//添加到list
				list.add(map);
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 获取结果集中所有的列名
	 * @param rs 结果集
	 * @return 获取的结果集中所有的列名
	 * @throws SQLException 
	 */
	public List<String> getallColumnNames(ResultSet rs) throws SQLException {
		List<String> columnNames=new ArrayList<String>();
		//ResultSetMetaData 可用于获取有关ResultSet对象中列的类型和属性的信息的对象
		ResultSetMetaData data=rs.getMetaData(); 	
		//getMetaData() 检索此 ResultSet对象的列的数量，类型和属性
		//获取总列数
		int count =data.getColumnCount();  //getColumnCount  返回此 ResultSet对象中的列数
		for(int i=1;i<=count;i++){//列的编号 从1开始
			columnNames.add(data.getColumnName(i));//根据列的编号获取对应的列名
		}
		return columnNames;
	}
	
	/**
	 * 聚合函数 max min avg sum count()    select count(*) from emp;
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public double getPromer(String sql,List<Object> params) throws SQLException{
		double result=0;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			//设置参数
			setParamterObject(pstmt, params);
			rs=pstmt.executeQuery();
			if(rs.next()){
				result=rs.getDouble(1);
			}
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return result;
	}
}
