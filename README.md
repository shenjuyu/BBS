BBS论坛的模仿项目
=================

## 功能

	<b>前端页面</b>

* 基础的帖子、板块显示
* 用户的登陆和注册
* 用户查看自己的信息
* 发表帖子/回复
* 删除帖子/回复
* 举报帖子/回复(举报信息会在后台处理)

	在发表帖子/回复时使用了百度的```ueditor```文本编辑器

	<b>后台页面</b>

* 板块的增、查、改
* 用户状态的查看和修改(是否被禁用)
* 管理员的状态的查看和修改(是否被禁用/是否通过管理员注册请求)
* 用户举报信息的查看和处理(此处只实现了查看功能/处理按钮中的逻辑代码还未完成)

## 项目环境

	jdk 1.8
	
	tomcat 8.0.53
	
	mysql 5.7
	
	服务器 阿里云服务器 系统 ```CentOS 7.6 64位```