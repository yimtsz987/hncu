<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>系统首页-教务秘书端</title>
		<style type="text/css">
			.page-personal{
				border: 1px solid #ddd;
				height: 320px;
				float: left;
				border-radius: 3px;
			}
			.page-personal .personal-top{
				background: #f5f5f5;
			    background-size: cover;
			    height: 175px;
			}
			.page-personal .personal-bottom{
				padding: 1.5em 2em;
			    position: relative;
			    background: #fff
			}
			.page-personal .personal-bottom h2{
				margin-top: 20px;
			    font-size: 1.5em;
			    color: #337AB7;
			    text-align: center;
			    margin-bottom: 20px;
			}
			.page-personal .personal-bottom p{
				font-size: 1em;
			    color: #000;
			    line-height: 120%;
			    text-align: center;
			    margin: 0 auto;
			}
			.page-personal .header-pic{
				width: 90px;
			    height: 90px;
			    display: inline-block;
			    position: absolute;
			    top: -60px;
			    left: 50%;
			    border: 2px solid #ccc;
			    border-radius: 63px;
			    -webkit-border-radius: 63px;
			    overflow: hidden;
			}
			.page-personal .header-pic img{
				width: 90px;
				height: 90px;
			}
			.page-content .page-notice{
				width: 280px;
				float: right;
				border: 1px solid #ddd;
				height: 320px;
			}
			.page-content .page-notice .notice-list{
				padding: 10px;
    			line-height: 26px
			}
			.page-content .page-notice .notice-list li{
				line-height: 26px;
				font-size: 14px;
			}
			.page-content .page-notice .notice-list i{
				color: #F63;
    			margin-right: 8px;
			}
			.page-content .page-course-wrap{
				height: 320px;
				width: 100%;
				margin-top: 20px;
				float: left;
				border-radius: 3px;
				border: 1px solid #ddd;
			}
		</style>
	</head>
	<body>
		<div class="page-content">
			<div class="alert alert-success">
				<span class="glyphicon glyphicon-ok"></span>欢迎使用<strong>毕业设计管理系统(${gpms:getParamValue("version")})</strong>,你本次登录时间为${nowDate},登录IP为:${loginIp}<span id="close-alert" class="close">&times;</span>
				<script type="text/javascript">
					$("#close-alert").click(function(){
						$(".alert").remove();
					});
					/*setInterval(function(){
						$(".alert").remove();
					},3000);*/
				</script>
			</div>
			<div class="page-personal">
				<div class="personal-top">
					
				</div>
				<div class="personal-bottom">
					<h2>${gpms:getUser().name}，教务秘书</h2>
					<p>欢迎使用毕业设计管理系统</p>
					<span class="header-pic"><img src="${ctxStatic}/${gpms:getUser().headerPic}"/></span>
				</div>
			</div>
			<div class="page-notice">
				<div class="page-title">
					通知公告
				</div>
				<ul class="notice-list">
					<li><i class="glyphicon glyphicon-bell"></i><a href="#">距提交开题报告到期时间仅剩一天</a></li>
					<li><i class="glyphicon glyphicon-bell"></i><a href="#">距提交开题报告到期时间仅剩一天</a></li>
					<li><i class="glyphicon glyphicon-bell"></i><a href="#">距提交开题报告到期时间仅剩一天</a></li>
					<li><i class="glyphicon glyphicon-bell"></i><a href="#">距提交开题报告到期时间仅剩一天</a></li>
					<li><i class="glyphicon glyphicon-bell"></i><a href="#">距提交开题报告到期时间仅剩一天</a></li>
					<li><i class="glyphicon glyphicon-bell"></i><a href="#">距提交开题报告到期时间仅剩一天</a></li>
					<li><i class="glyphicon glyphicon-bell"></i><a href="#">距提交开题报告到期时间仅剩一天</a></li>
				</ul>
			</div>
			<div class="page-course-wrap">
			    <div class="page-title">
			        流程进度
			    </div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	    $(function(){
	    	var page_content_width = $(".page-content").width();
	    	$(".page-personal").css({'width':page_content_width - 280 - 20});
	    	var page_personal_width = $(".page-personal").width();
	    	$(".header-pic").css({"left":page_personal_width/2 - 45 });
	    });
		$(window).resize(function(){
			var page_content_width = $(".page-content").width();
	    	$(".page-personal").css({'width':page_content_width - 280 - 20});
	    	var page_personal_width = $(".page-personal").width();
	    	$(".header-pic").css({"left":page_personal_width/2 - 45 });
		})
	</script>
</html>
