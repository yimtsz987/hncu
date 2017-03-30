<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>湖南城市学院毕业设计管理系统</title>
	</head>
	<body>
		<div class="header">
			<div class="logo pull-left">
				<div class="logo-img">
					<img src="${ctxStatic}/img/logo1.png" width="700" height="75"/>
				</div>
			</div>
			<div class="logo-info pull-left">—— ${gpms:getParamValue("department")}${gpms:getParamValue("year")}级启用</div>
			<div class="header-btn pull-right">
				<ul>
					<li>
						<a class="dropdown dropdown-cursor" data-toggle="dropdown">
						  <span class="glyphicon glyphicon-bell"></span>
						</a>
						<div class="dropdown-menu dropdown-notice pull-right">
							<div class="notice-arrow"></div>
							<h3>你有5条新通知</h3>
							<ul>
								<li>
									<i class="glyphicon glyphicon-warning-sign notice-icon"></i>
									<div class="notice-item">距提交开题报告到期时间仅剩一天</div>
									<span class="time">
										<i class="glyphicon glyphicon-time notice-time-icon"></i>
										2016-12-05
									</span>
								</li>
								<li>
									<i class="glyphicon glyphicon-warning-sign notice-icon"></i>
									<div class="notice-item">距提交开题报告到期时间仅剩一天</div>
									<span class="time">
										<i class="glyphicon glyphicon-time notice-time-icon"></i>
										2016-12-05
									</span>
								</li>
								<li>
									<i class="glyphicon glyphicon-warning-sign notice-icon"></i>
									<div class="notice-item">距提交开题报告到期时间仅剩一天</div>
									<span class="time">
										<i class="glyphicon glyphicon-time notice-time-icon"></i>
										2016-12-05
									</span>
								</li>
								<li>
									<i class="glyphicon glyphicon-warning-sign notice-icon"></i>
									<div class="notice-item">距提交开题报告到期时间仅剩一天</div>
									<span class="time">
										<i class="glyphicon glyphicon-time notice-time-icon"></i>
										2016-12-05
									</span>
								</li>
								<li>
									<i class="glyphicon glyphicon-warning-sign notice-icon"></i>
									<div class="notice-item">距提交开题报告到期时间仅剩一天</div>
									<span class="time">
										<i class="glyphicon glyphicon-time notice-time-icon"></i>
										2016-12-05
									</span>
								</li>
							</ul>
							<div class="notice-more">
								<a href="#">点击查看更多</a>
							</div>
						</div>
						<span class="badge bg-primary">50</span>
					</li>
					<li id="screen-full">
						<a class=" dropdown-cursor" onclick="fullscreen()">
						  <span class="glyphicon glyphicon-resize-full"></span>
						</a>
					</li>
					<li id="screen-small">
						<a class=" dropdown-cursor" onclick="exitFullscreen()">
						  <span class="glyphicon glyphicon-resize-small"></span>
						</a>
					</li>
					<li class="user">
						<a class=" dropdown-cursor" class="dropdown" data-toggle = "dropdown">
							${gpms:getUser().name} <span class="caret"></span>
						</a>
						<div class="dropdown-menu menu-user dropdown-user">
							<div class="user-arrow"></div>
							<ul>
								<li><a href="#"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;个人资料</a></li>
								<li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;修改密码</a></li>
								<li class="divider"></li>
								<li><a href="/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;注销</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
			<div class="content">
				<div class="nav">
					<div class="nav-title">
						<div class="nav-title-lager">
							系统管理员端
						</div>
						<div class="nav-title-small">
							<i class="glyphicon glyphicon-th-large nav-small-icon"></i>
						</div>
					</div>
					<div class="nav-user-info">
					     <div class="info-headerpic">
					     	<img src="${ctxStatic}/${gpms:getUser().headerPic}" width="100" height="100" />
					     </div>
					     <div class="info-text">
					     	<p>${gpms:getUser().name}</p>
					     	<p>用户名：${gpms:getUser().username}</p>
					     </div>
					</div>
					<ul class="nav-list">
						<li class="first-nav" >
							<a href="javascript:void(0)" title="系统首页" class="active-nav iframeurl" name="${ctx}/admin/home">
							   <i class="glyphicon glyphicon-home"></i>系统首页
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="个人信息" class="iframeurl" name="${ctx}/admin/info">
							   <i class="glyphicon glyphicon-home" ></i>个人信息
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="学生管理" class="iframeurl" name="${ctx}/admin/studentList">
							   <i class="glyphicon glyphicon-home" ></i>学生管理
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="教师管理" >
							   <i class="glyphicon glyphicon-home" ></i>教师管理
							   <b class="nav-sign arrow glyphicon glyphicon-plus"></b>
							</a>
						</li>
						<ul class="submenu">
							<li>
								<a href="javascript:void(0)" title="设置当前年级" class="iframeurl" name="${ctx}/admin/teacherList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>1、教师管理
								</a>
							</li>
							<li>
								<a href="javascript:void(0)" title="发送通知" class="iframeurl" name="${ctx}/admin/answerGroupList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>2、答辩分组
								</a>
							</li>
						</ul>
						<li class="first-nav">
							<a href="javascript:void(0)" title="系统管理">
							   <i class="glyphicon glyphicon-home"></i>系统管理
							   <b class="nav-sign arrow glyphicon glyphicon-plus"></b>
							</a>
						</li>
						<ul class="submenu">
							<li>
								<a href="javascript:void(0)" title="系统参数" class="iframeurl" name="${ctx}/admin/sysParamList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>1、系统参数
								</a>
							</li>
							<li>
								<a href="javascript:void(0)" title="发送通知"class="iframeurl" name="inform.html">
									<i class="subicon glyphicon glyphicon-hand-right"></i>2、发送通知
								</a>
							</li>
						</ul>
						<li class="first-nav">
							<a href="javascript:void(0)" title="成绩查询">
							   <i class="glyphicon glyphicon-home"></i>字典管理
							     <b class="nav-sign arrow glyphicon glyphicon-plus"></b>
							</a>
						</li>
						<ul class="submenu">
							<li>
								<a href="#" title="院系菜单" class="iframeurl" name="${ctx}/admin/departmentList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>1、院系菜单
								</a>
							</li>
							<li>
								<a href="#" title="专业菜单" class="iframeurl" name="${ctx}/admin/majorList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>2、专业菜单
								</a>
							</li>
							<li>
								<a href="#" title="班级菜单" class="iframeurl" name="${ctx}/admin/classesList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>3、班级菜单
								</a>
							</li>
							<li>
								<a href="#" title="专业菜单" class="iframeurl" name="${ctx}/admin/dictList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>4、通用字典
								</a>
							</li>
						</ul>
						<li class="first-nav">
							<a href="javascript:void(0)" title="通知公告" class="iframeurl" disabled="disabled">
							   <i class="glyphicon glyphicon-home"></i>通知公告
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="相关资料">
								<i class="glyphicon glyphicon-home"></i>相关资料
								<b class="nav-sign arrow glyphicon glyphicon-plus"></b>
							</a>
						</li>
						<ul class="submenu">
							<li>
								<a href="javascript:void(0)" title="上传资料"class="iframeurl" name="${ctx}/admin/uploadDate">
									<i class="subicon glyphicon glyphicon-hand-right"></i>3、资料上传
								</a>
							</li>
							<li>
								<a href="javascript:void(0)" title="专业菜单" class="iframeurl" name="${ctx}/admin/downloadList">
									<i class="subicon glyphicon glyphicon-hand-right"></i>2、资料下载
								</a>
							</li>
						</ul>
						<li class="first-nav">
							<a href="javascript:void(0)" title="修改密码">
							   <i class="glyphicon glyphicon-home"></i>修改密码
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="关于系统">
							   <i class="glyphicon glyphicon-home"></i>关于系统
							</a>
						</li>
						<div class="clearfix"></div>
					</ul>
					<ul class="nav-list-sm">
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
					</ul>
					<div id="nav-collapse" class="nav-collapse nav-collapse-large">
						<i id="nav-collapse-btn" class="glyphicon glyphicon-ok-sign icon"></i>
					</div>
				</div>
				<div class="main-content">
					<div class="location">
						<ul>
							<li>
								<i class="glyphicon glyphicon-home"></i>
								<a href="#">系统首页</a>
								<span>&gt;</span>
							</li>
							<li>
								<span class="location-nav">个人信息</span>
							</li>
							<li class="menu-li">
								<span>&gt;</span>
								<span class="location-menu">选择教师</span>
							</li>
						</ul>
					</div>
					<div class="iframe-wrap">
						<iframe id="iframe" name="iframe" frameborder="0" src="${ctx}/admin/home" height="100%" width="100%" scrolling="yes"></iframe>
					</div>
				</div>
			</div>
		<div class="footer">
			<p class="pull-left">版权所有：湖南城市学院信息科学与工程学院</p>
			<p class="pull-right">地址：湖南省益阳市银城南路湖南城市学院    技术支持：智相科技</p>
		</div>
	</body>
	<script type="text/javascript">
	    $(function(){
	    	var win_width = $(window).width();
			var win_height = $(window).height();
			if(win_width < 1366){
				$(".logo").css({'width':440});
				$(".logo-img").css({'width':440});
				$(".logo-img img").attr('src','${ctxStatic}/img/logo-sm.png');
				$(".logo-img img").attr('width','440');
				$("#nav-collapse").attr("class","nav-collapse nav-collapse-small");
				nav_collapse();
			}else if(win_width >= 1366){
				$(".logo").css({'width':700});
				$(".logo-img").css({'width':700});
				$(".logo-img img").attr('src','${ctxStatic}/img/logo1.png');
				$(".logo-img img").attr('width','700');
				$("#nav-collapse").attr("class","nav-collapse nav-collapse-large");
				nav_collapse();
			}
			$(".content,.nav").css({'height':win_height-95});
			$(".nav-list").css({'maxHeight':win_height-448});
			$(".main-content").css({'height':win_height-95-22});
			$(".iframe-wrap").css({'height':win_height-95-22-43});
	    })
		$(window).resize(function(){
			var win_width = $(window).width();
			var win_height = $(window).height();
			if(win_width < 1300){
				$(".logo").css({'width':440});
				$(".logo-img").css({'width':440});
				$(".logo-img img").attr('src','${ctxStatic}/img/logo-sm.png');
				$(".logo-img img").attr('width','440');
				$("#nav-collapse").attr("class","nav-collapse nav-collapse-small");
				nav_collapse();
			}else if(win_width >= 1366){
				$(".logo").css({'width':700});
				$(".logo-img").css({'width':700});
				$(".logo-img img").attr('src','${ctxStatic}/img/logo1.png');
				$(".logo-img img").attr('width','700');
				$("#nav-collapse").attr("class","nav-collapse nav-collapse-large");
				nav_collapse();
			}
			$(".content,.nav").css({'height':win_height-95});
			$(".nav-list").css({'maxHeight':win_height-448});
			$(".main-content").css({'height':win_height-95-22});
			$(".iframe-wrap").css({'height':win_height-95-22-43});
		});
		$(".nav-list>li").click(function(){
			var nav_title = $(this).children("a").attr("title");
			if($(this).next(".submenu").css("display") == "none"){
				$(".nav-list .submenu").slideUp();
				$(".nav-list>li").children("a").removeClass('active-nav');
				$(this).children("a").addClass('active-nav');
				$(this).next(".submenu").slideDown();
				$(this).find(".nav-sign").attr("class","nav-sign arrow glyphicon glyphicon-minus");
				$(".submenu>li").removeClass("active-menu");
				$(".menu-li").hide();
				if(nav_title == "系统首页"){
					$(".location-nav").html("");
				} else{
					$(".location-nav").html(nav_title);
				}
			} else {
				$(".nav-list .submenu").slideUp();
				$(".nav-list>li").children("a").removeClass('active-nav');
				$(this).children("a").addClass('active-nav');
				$(this).next(".submenu").slideUp();
				$(".nav-list>li").find(".nav-sign").attr("class","nav-sign arrow glyphicon glyphicon-plus");
				$(".submenu>li").removeClass("active-menu");
				$(".location-nav").html(nav_title);
				$(".menu-li").hide();
				if(nav_title == "系统首页"){
					$(".location-nav").html("");
				} else{
					$(".location-nav").html(nav_title);
				}
			}
		});
		$(".iframeurl").click(function() {
				var cid = $(this).attr("name");
				$("#iframe").attr("src", cid).ready();
		});
		$(".submenu>li").children("a").click(function(){
			var menu_title = $(this).attr("title");
			$(".submenu>li").removeClass("active-menu");
			$(this).addClass("active-menu");
			$(".menu-li").show();
			$(".location-menu").html(menu_title);
		});
		$("#nav-collapse-btn").click(function(){
			nav_collapse();
		});
		var nav_collapse = function(){
			if($("#nav-collapse").hasClass("nav-collapse-small")){
				$(".nav").css({"width":"42px"});
				$(".main-content").css({"marginLeft":'42px'});
				$("#nav-collapse").attr("class","nav-collapse nav-collapse-large");
				$(".nav-list").hide();
				$(".nav-list-sm").show();
				$(".nav-user-info").hide();
				$(".nav-title-lager").hide();
				$(".nav-title-small").show();
				$("#nav-collapse").css({"width":"41px"});
			} else if($("#nav-collapse").hasClass("nav-collapse-large")){
				$(".nav").css({"width":"220px"});
				$(".main-content").css({"marginLeft":'220px'});
				$("#nav-collapse").attr("class","nav-collapse nav-collapse-small");
				$(".nav-list-sm").hide();
				$(".nav-list").show();
				$(".nav-user-info").show();
				$(".nav-title-small").hide();
				$(".nav-title-lager").show();
				$("#nav-collapse").css({"width":"219px"});
			}
		}
		var fullscreen=function(){
			document.getElementById("screen-full").style.display = 'none';
			document.getElementById("screen-small").style.display = 'block';
		    elem=document.body;  
		    if(elem.webkitRequestFullScreen){  
		        elem.webkitRequestFullScreen();     
		    }else if(elem.mozRequestFullScreen){  
		        elem.mozRequestFullScreen();  
		    }else if(elem.requestFullScreen){  
		        elem.requestFullscreen();  
		    }else{  
		        alert("对不起，浏览器不支持全屏或已被禁用  "); 
		    }  
		}  
		var exitFullscreen=function(){
			document.getElementById("screen-small").style.display = 'none';
			document.getElementById("screen-full").style.display = 'block';
		    var elem=document;  
		    if(elem.webkitCancelFullScreen){  
		        elem.webkitCancelFullScreen();      
		    }else if(elem.mozCancelFullScreen){  
		        elem.mozCancelFullScreen();  
		    }else if(elem.cancelFullScreen){  
		        elem.cancelFullScreen();  
		    }else if(elem.exitFullscreen){  
		        elem.exitFullscreen();  
		    }else{  
		        alert("对不起，浏览器不支持全屏或已被禁用  "); 
		    }  
		} 
	</script>
</html>
