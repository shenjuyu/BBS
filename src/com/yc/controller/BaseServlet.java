package com.yc.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.util.LogUtil;

/**
 * 
 * @author 俊羽
 *
 */
public class BaseServlet extends HttpServlet{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	/**
	 * easyUI分页json格式数据
	 * @param response
	 * @param obj
	 * @param total
	 * @throws IOException
	 */
	public void toPrintString(HttpServletResponse response,Object obj,int total) throws IOException{
		Gson gson=new Gson();
		String info =gson.toJson(obj);
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		sb.append("\"total\":"+total+",\"rows\":");
		sb.append(info);
		sb.append("}");
		response.getWriter().print(sb.toString());
	}
	
	/**
	 * 将对象转为json字符串
	 * @param response
	 * @param obj
	 * @throws IOException 
	 */
	public void toPrintString(HttpServletResponse response,Object obj) throws IOException{
		Gson gson=new Gson();
		String info =gson.toJson(obj);
		response.getWriter().print(info);
	}
	
	/**
	 * 整形
	 * @param response
	 * @param obj
	 * @throws IOException 
	 */
	public void toPrintString(HttpServletResponse response,Integer i) throws IOException{
		Gson gson=new Gson();
		String info =gson.toJson(i);
		response.getWriter().print(info);
	}
	
	/**
	 * 字符串
	 * @param response
	 * @param obj
	 * @throws IOException 
	 */
	public void toPrintString(HttpServletResponse response,String str) throws IOException{
		Gson gson=new Gson();
		String info =gson.toJson(str);
		response.getWriter().print(info);
	}
	
	/**
	 * 将两个对象转为json字符串
	 * @param response
	 * @param pageNum 
	 * @param str
	 * @throws IOException
	 */
	public void toPrintString(HttpServletResponse response,Object obj1 , Object obj2, Integer pageNum) throws IOException{
		Gson gson =new Gson();
		String info1 =gson.toJson(obj1);
		String info2 =gson.toJson(obj2);
		StringBuffer sb =new StringBuffer();
		sb.append("[");
		sb.append(info1);
		sb.append(",");
		sb.append(info2);
		sb.append(",");
		sb.append(pageNum);
		sb.append("]");
		response.getWriter().print(sb.toString());
		
	}
	
	/**
	 * 解析请求对象将数据封装到JavaBean对象
	 * @param request
	 * @param cls
	 * @return
	 */
	public <T> T parseRequestToObject(HttpServletRequest request,Class<T> cls){
		T t=null;
		try {
			//获得所有属性
			Field [] fields=cls.getDeclaredFields();
			//获得所有的方法
			Method [] methods=cls.getDeclaredMethods();
			//根据cls创建对象
			t=cls.newInstance();
			String value=null;
			String fieldName=null;
			for(Field field:fields){
				fieldName=field.getName();
				//根据属性取值  页面的name属性值必须和javabean对象的属性名一致
				value=request.getParameter(fieldName);
				//当页面没有传入值的时候
				if(null==value||"".equals(value)){
					continue;
				}
				for(Method m:methods){
					if(("set"+fieldName).equalsIgnoreCase(m.getName())){
						String paramType=m.getParameterTypes()[0].getName();
						if("java.lang.Integer".equals(paramType)){
							m.invoke(t, Integer.parseInt(value));
						}else if("java.lang.Double".equals(paramType)){
							m.invoke(t, Double.parseDouble(value));
						}else if("java.lang.Float".equals(paramType)){
							m.invoke(t, Float.parseFloat(value));
						}else if("java.lang.String".equals(paramType)){
							m.invoke(t, value);
						}
					}
				}
			}
		} catch (Exception e) {
			LogUtil.log.debug(e.getMessage());
			e.printStackTrace();
		}
		return t;
	}
}
