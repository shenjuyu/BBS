<%@page import="com.yc.po.AdminPO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=pageContext.getServletContext().getContextPath();
	path=path+"/";
	AdminPO adminpo=(AdminPO)session.getAttribute("admin");
	if(null==adminpo){
		%>
		<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>BBS论坛后台管理</title>
		<base href="<%=path %>">
		<!--1 导入图标的样式 -->
		<link rel="stylesheet" href="back/easyui/css/icon.css" />
		<!--2 导入easyUI皮肤样式-->
		<link rel="stylesheet" href="back/easyui/css/easyui.css" />
		<!--3 导入jquery文件-->
		<script type="text/javascript" src="back/easyui/js/jquery.min.js"></script>
		<!--4 导入easyUI.js文件-->
		<script type="text/javascript" src="back/easyui/js/jquery.easyui.min.js"></script>
		<!--5 导入中文-->
		<script type="text/javascript" src="back/easyui/js/easyui-lang-zh_CN.js" ></script>
		</head>
		<body></body>
		<script type="text/javascript">
			$.messager.show({
				title:'登陆提示',
				msg:'请先登录<a href="back/login.html">点击此处前往登陆页面</a>',
				timeout:10000,
				showType:'slide'
			});
		</script>
		<%
		return;
	}
%>

	<head>
		<meta charset="UTF-8">
		<title>BBS论坛后台管理</title>
		<base href="<%=path %>">
		<!--1 导入图标的样式 -->
		<link rel="stylesheet" href="back/easyui/css/icon.css" />
		<!--2 导入easyUI皮肤样式-->
		<link rel="stylesheet" href="back/easyui/css/easyui.css" />
		<!--3 导入jquery文件-->
		<script type="text/javascript" src="back/easyui/js/jquery.min.js"></script>
		<!--4 导入easyUI.js文件-->
		<script type="text/javascript" src="back/easyui/js/jquery.easyui.min.js"></script>
		<!--5 导入中文-->
		<script type="text/javascript" src="back/easyui/js/easyui-lang-zh_CN.js" ></script>
		<style type="text/css">
			ul{
				list-style: none;
			}
			#d1 ul li{
				margin-top: 10px;
				margin-left: -20px;
			}
			#d3 li{
				margin-top: -8px;
				float: left;
				display: list-item;
			}
			#d3 li a{
				margin-left: 650px;
			}
			#d3 li span{
				margin-left: 730px;
			}
			.northtitle{
				float:left;
				margin-top:-105px;
				margin-left:565px;
				line-height: 105px;
				text-align: center;
				font-size: 35px;
				font-weight: bold;
				color: 1B80D4;
			}
			.welcome{
				float:right;
				margin-top:-32px;
				margin-right:30px;
				text-align: center;
				color: 8983F0;
			}
		</style>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',split:true" style="height:107px;">
			<div>
				<img src="image/logo.gif">
			</div>
			<span class="northtitle">BBS论坛后台管理平台</span>
			<div class="welcome"></div>
		</div>
		<div data-options="region:'south',split:true" style="height:40px;">
			<ul id="d3">
				<li>
					<a href="index.html">BBS论坛©俊泽工作室</a>
				</li>
			</ul>
			
		</div>
		<div data-options="region:'east',title:'工具箱',split:true,collapsed:true" style="width:100px;"></div>
		<div data-options="region:'west',title:'导航菜单',split:true" style="width:150px;">
			<div id="d1" class="easyui-accordion" data-options="fit:true">
				
				<div title="板块管理" data-options="selected:true">
					<ul>
						<li><a class="btn easyui-linkbutton" data-options="plain:true" href="back/manager/showboard.html">浏览板块信息</a></li>
					</ul>
				</div>
				<div title="账户管理" style="padding: 10px;">
					<ul>
						<li><a class="btn easyui-linkbutton" data-options="plain:true" href="back/manager/showuser.html">浏览用户信息</a></li>
						<li><a class="btn easyui-linkbutton" data-options="plain:true" href="back/manager/showadmin.html">浏览管理员信息</a></li>
					</ul>
				</div>
				
				<div title="举报管理">
					<ul>
						<li><a class="btn easyui-linkbutton" data-options="plain:true" href="back/manager/reportuser.html">举报用户管理</a></li>
						<li><a class="btn easyui-linkbutton" data-options="plain:true" href="back/manager/reporttopic.html">举报帖子管理</a></li>
					</ul>
				</div>
			</div>

		</div>
		<div data-options="region:'center',title:'主操作区'">
			<div id="show_content" class="easyui-tabs" data-options="fit:true"></div>
		</div>
	</body>
<script>
$.post('admin.action',{op:'findsession'},function(data){
	if(6==data.a_power){
		$.messager.show({
			title:'登陆提示',
			msg:'请先登录<a href="back/login.html">点击此处前往登陆页面</a>',
			timeout:10000,
			showType:'slide'
		});
	}else{
		var power='';
		if(data.a_power=='1'){
			power='超级管理员';
		}else if(data.a_power=='2'){
			power='普通管理员'
		}
		var str='<span>欢迎  '+power+'：'+data.a_name+'</span>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="outlogin()">安全退出</a>';
		$('.welcome').html(str);
	}
},'json');

function outlogin(){
	$.post('admin.action',{op:'outlogin'},function(data){
		if(1==data){
			window.location.href='back/login.html';
		}
	});
}


$(function(){
	$('.btn').click(function(){
		var href=$(this).attr('href');
		var title=$(this).text();
		// 添加一个新的标签页面板（tab panel）
		if(!$('#show_content').tabs('exists',title)){ //判断 是否存在 不存在创建
			$('#show_content').tabs('add',{
			    title:title,
			    href:href,
			    closable:true,
			    tools:[{
					iconCls:'icon-mini-refresh',
					handler:function(){
						$.messager.confirm('温馨提示','是否刷新页面',function(r){
							if(r){
								var tab = $('#show_content').tabs('getSelected');
								var href=tab.panel('options').href;
								tab.panel('refresh', href);
								
								$.messager.show({
									title:'刷新提示',
									msg:'刷新成功',
									timeout:4000,
									showType:'slide'
								});
							}
						});
						
						
					}
			    }]
			});
		}else{
			//存在 
			$('#show_content').tabs('select',title);
		}
		return false;
	});
});
</script>