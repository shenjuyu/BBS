package com.yc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/fileupload.action")
@MultipartConfig
public class FileUploadServlet extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		if("addfile".equals(op)){
			doAddFile(request,response);
		}
	}

	private void doAddFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取服务端保存上传文件的目录路径
		String path=request.getServletContext().getRealPath("/");
		//从请求中获取上传文件multipart请求中的上传文件部分对象
		Part part= request.getPart("upload");
		//获取指定的头部属性
		String header=part.getHeader("Content-Disposition");
		
		String filerName=(""+ part.getSubmittedFileName());
		String imagepath="../bbs_images/"+System.currentTimeMillis()+filerName;
		//完成文件上传
		part.write(path+imagepath);
		
		toPrintString(response, imagepath);
	}
}
