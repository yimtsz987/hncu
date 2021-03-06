<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>湖南城市学院毕业设计管理系统</title>
		<style type="text/css">
			.pass-flag{
				position: absolute !important;
				top: 10px !important;
				right: 35px !important;
				font-size: 15px;
				color: red !important;
			}
		</style>
	</head>
	<body>
		<div class="header">
			<div class="logo pull-left">
				<div class="logo-img">
					<img src="${ctxStatic}/img/logo1.png" width="700" height="75"/>
				</div>
			</div>
			<div class="logo-info pull-left">—— ${gpms:getParamValue("department")}${gpms:getParamValue("year")}级启用(测试版Beta)</div>
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
							${gpms:getUser().name}，同学 <span class="caret"></span>
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
							学生端
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
					     	<p>${gpms:getUser().name}，学生</p>
					     	<p>${gpms:getUser().student.department.name}</p>
					     	<p>${gpms:getUser().student.major.name}</p>
					     	<p>${gpms:getUser().student.node}</p>
					     </div>
					</div>
					<ul class="nav-list">
						<li class="first-nav" >
							<a href="javascript:void(0)" title="系统首页" class="active-nav iframeurl" name="${ctx}/student/home">
							   <i class="glyphicon glyphicon-home"></i>系统首页
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="个人信息" class="iframeurl" name="${ctx}/student/info">
							   <i class="glyphicon glyphicon-home" ></i>个人信息
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="毕设流程" class="iframeurl">
							   <i class="glyphicon glyphicon-home" ></i>毕设流程
							   <b class="nav-sign arrow glyphicon glyphicon-plus"></b>
							</a>
						</li>
						<ul class="submenu">
								<li>
									<c:if test="${gpms:getUser().student.step1 eq 0}">
									<a href="javascript:void(0)" title="选择教师" class="iframeurl" name="${ctx}/student/chooseTeacherList">
										<i class="subicon glyphicon glyphicon-hand-right"></i>1、选择教师
									</a>
									</c:if>
									<c:if test="${gpms:getUser().student.step1 ne 0}">
										<a href="javascript:void(0)" title="查看教师" class="iframeurl" name="${ctx}/student/chooseTeacherInfo">
											<i class="subicon glyphicon glyphicon-hand-right"></i>1、查看教师
											<c:if test="${gpms:getUser().student.step1 eq 2}">
												<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
											</c:if>
										</a>
									</c:if>
								</li>
							<c:if test="${gpms:getUser().student.stepNow >= 2 and gpms:getUser().student.step1 eq 2}">
								<li>
									<c:if test="${gpms:getUser().student.step2 eq 0}">
										<a href="javascript:void(0)" title="选择课题" class="iframeurl" name="${ctx}/student/chooseTitleList">
											<i class="subicon glyphicon glyphicon-hand-right"></i>2、选择课题
										</a>
									</c:if>
									<c:if test="${gpms:getUser().student.step2 ne 0}">
										<a href="javascript:void(0)" title="查看课题" class="iframeurl" name="${ctx}/student/chooseTitleInfo">
											<i class="subicon glyphicon glyphicon-hand-right"></i>2、查看课题
											<c:if test="${gpms:getUser().student.step2 eq 2}">
												<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
											</c:if>
										</a>
									</c:if>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow >= 3 and gpms:getUser().student.step2 eq 2}">
								<li>
									<c:if test="${gpms:getUser().student.step3 eq 0}">
										<a href="javascript:void(0)" title="审题" class="iframeurl" name="${ctx}/student/understanding">
											<i class="subicon glyphicon glyphicon-hand-right"></i>3、审题
										</a>
									</c:if>
									<c:if test="${gpms:getUser().student.step3 ne 0}">
										<a href="javascript:void(0)" title="查看审题" class="iframeurl" name="${ctx}/student/understandingInfo">
											<i class="subicon glyphicon glyphicon-hand-right"></i>3、查看审题
											<c:if test="${gpms:getUser().student.step3 eq 2}">
												<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
											</c:if>
										</a>
									</c:if>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow >= 4 and gpms:getUser().student.step3 eq 2}">
								<li>
									<c:if test="${gpms:getUser().student.step4 eq 0}">
										<a href="javascript:void(0)" title="开题" class="iframeurl" name="${ctx}/student/openTitlePage">
											<i class="subicon glyphicon glyphicon-hand-right"></i>4、开题
										</a>
									</c:if>
									<c:if test="${gpms:getUser().student.step4 ne 0}">
										<a href="javascript:void(0)" title="查看开题" class="iframeurl" name="${ctx}/student/openTitleInfo">
											<i class="subicon glyphicon glyphicon-hand-right"></i>4、查看开题
											<c:if test="${gpms:getUser().student.step4 eq 2}">
												<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
											</c:if>
										</a>
									</c:if>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow >= 5 and gpms:getUser().student.step4 eq 2}">
								<li>
									<a href="javascript:void(0)" title="设计和论文撰写" class="iframeurl" name="${ctx}/student/scheduleList">
										<i class="subicon glyphicon glyphicon-hand-right"></i>5、设计和论文撰写
										<c:if test="${gpms:getUser().student.step5 eq 2}">
											<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
										</c:if>
									</a>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow >= 6 and gpms:getUser().student.step5 eq 2}">
								<li>
									<a href="javascript:void(0)" title="教师批阅" class="iframeurl" name="${ctx}/student/markingList">
										<i class="subicon glyphicon glyphicon-hand-right"></i>6、教师批阅
										<c:if test="${gpms:getUser().student.step6 eq 2}">
											<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
										</c:if>
									</a>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow >= 7 and gpms:getUser().student.step6 eq 2}">
								<li>
									<a href="javascript:void(0)" class="iframeurl" name="${ctx}/student/middleCheckList" title="中期检查">
										<i class="subicon glyphicon glyphicon-hand-right"></i>7、中期检查
										<c:if test="${gpms:getUser().student.step7 eq 2}">
											<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
										</c:if>
									</a>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow >= 8 and gpms:getUser().student.step7 eq 2}">
								<li>
									<a href="javascript:void(0)" class="iframeurl" name="${ctx}/student/reviewList" title="评阅">
										<i class="subicon glyphicon glyphicon-hand-right"></i>8、评阅
										<c:if test="${gpms:getUser().student.step8 eq 2}">
											<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
										</c:if>
									</a>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow >= 9 and gpms:getUser().student.step8 eq 2}">
								<li>
									<a href="javascript:void(0)" class="iframeurl" name="${ctx}/student/answerList" title="答辩">
										<i class="subicon glyphicon glyphicon-hand-right"></i>9、答辩
										<c:if test="${gpms:getUser().student.step9 eq 2}">
											<i class="pass-flag glyphicon glyphicon glyphicon-ok"></i>
										</c:if>
									</a>
								</li>
							</c:if>
							<c:if test="${gpms:getUser().student.stepNow == 10}">
								<li>
									<a href="javascript:void(0)" class="iframeurl" name="${ctx}/student/schoolReport" title="成绩查询">
										<i class="subicon glyphicon glyphicon-hand-right"></i>10、查看成绩
									</a>
								</li>
							</c:if>
							</ul>
						<c:if test="${gpms:getUser().student.teacherId != null}">
							<li class="first-nav">
								<a href="javascript:void(0)" title="问题咨询" class="iframeurl" name="${ctx}/student/questionList">
									<i class="glyphicon glyphicon-home" ></i>问题咨询
								</a>
							</li>
						</c:if>
						<li class="first-nav">
							<a href="javascript:void(0)" class="iframeurl" name="procedure.html" title="流程进度">
							   <i class="glyphicon glyphicon-home"></i>流程进度
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" class="iframeurl" name="${ctx}/notice/noticeStudentList" title="通知公告">
							   <i class="glyphicon glyphicon-home"></i>通知公告
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" class="iframeurl" name="download.html" title="资料下载">
							   <i class="glyphicon glyphicon-home"></i>资料下载
							</a>
						</li>
						<li class="first-nav">
							<a href="javascript:void(0)" title="修改密码" class="iframeurl" name="${ctx}/user/changePwdEdit">
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
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="home.html" title="系统首页">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="studentinfo.html" title="个人信息">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="ChooseTeacher.html" title="选择教师">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="procedure.html" title="流程进度">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="" title="成绩查询">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="Announcements.html" title="通知公告">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="download.html" title="资料下载">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="password.html" title="修改密码">
								<i class="glyphicon glyphicon-heart"></i>
							</a>
						</li>
						<li>
							<a class="nav-collapse-btn iframeurl" href="javascript:" name="" title="关于系统">
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
						<iframe id="iframe" name="iframe" frameborder="0" src="${ctx}/student/home" height="100%" width="100%" scrolling="yes"></iframe>
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
                $(".logo").css({'width':630});
                $(".logo-img").css({'width':630});
                $(".logo-img img").attr('src','${ctxStatic}/img/logo1.png');
                $(".logo-img img").attr('width','630');
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
                $(".logo").css({'width':630});
                $(".logo-img").css({'width':630});
                $(".logo-img img").attr('src','${ctxStatic}/img/logo1.png');
                $(".logo-img img").attr('width','630');
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
