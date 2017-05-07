<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>湖南城市学院毕业设计管理系统-登录页面</title>
		<style type="text/css">
			html,body{
				background: #fafafa;
			}
			.login_header{
				width: 100%;
				min-width: 1366px;
				height: 120px;
			}
			.login_header .h_main{
				width: 1366px;
				height: 120px;
				margin: 0 auto;
				background: black;
			}
			.login-wrap{
				width: 100%;
				min-width: 1366px;
				height: 463px;
				position: relative;
				background: #fafafa url(${ctxStatic}/img/login_wrap.png) repeat-x;
			}
			.login-wrap .login-main{
				width: 1366px;
				height: 457px;
				margin: 0 auto;
				background: url(${ctxStatic}/img/login_background.jpg) center no-repeat;
				position: relative;
			}
			.login-wrap .login-main .login-box{
				width: 314px;
				height: 344px;
				position: absolute;
				top: 104px;
				right: 211px;
				box-sizing: border-box;
				padding: 10px;
			}
			.login-wrap .login-main .login-box .login-title{
				height: 18px;
				line-height: 18px;
				font-size: 18px;
				color: #28aafd;
				margin-top: 9px;
				padding-bottom: 8px;
				font-weight: 700;
				text-indent: 10px;
			}
			.login-wrap .login-main .login-box .login-message{
				text-align: center;
				color: red;
				height:36px;
				line-height: 36px;
				font-size: 14px;
				padding-top: 8px;
				padding-bottom: 8px;
			}
			.login-wrap .login-main .login-box .login-form{
				padding-left: 20px;
				padding-right: 20px;
			}
			.form-group{
				position: relative;
				margin-bottom: 20px !important;
				height: 34px;
			}
			.form-group .form-label{
				position: absolute;
				top: 1px;
				left: 1px;
				display: block;
				width: 32px;
				height: 32px;
				line-height: 32px;
				color: #fff;
				background: #ccc;
				text-align: center;
				outline: 0;
				border-radius: 4px 0 0 4px;
			}
			.form-group input:focus+.form-label{
				background: #66afe9;
				transition: all .3s ease;
			}
			.form-group .login-input{
				box-sizing: border-box;
				padding-left: 42px;
			}
			.login-node-label{
				float: left;
				line-height: 34px;
				font-weight: 500;
			}
			.login-node{
				float: left;
				width: 100px;
			}
			.login-node-img{
				float: right;
				width: 85px;
				height: 30px;
				margin-right: 10px;
			}
			.login-node-img img{
				width: 85px;
				height: 30px;
			}
			.remberPwd{
				font-size: 14px;
				font-weight: 500;
				float: left;
				line-height: 34px;
				color: #999;
			}
			.forgetPwd{
				float: right;
				line-height: 34px;
			}
			.forgetPwd a{
				font-size: 14px;
				color: #999;
			}
			.forgetPwd a:hover{
				color: #f90;
			}
			.checkbox-style{
				margin-right: 5px !important;
			}
			.btn-width{
				width: 254px;
			}
			.margin-bottom-10{
				margin-bottom: 10px !important;
			}

			.login_footer{
				width: 100%;
				margin-top: 230px;
			}
			.login_footer .f_main{
				width: 1366px;
				margin: 0 auto;
			}
			.login_footer .f_main .link{
				border-top: 1px solid #ddd;
				border-bottom: 1px solid #ddd;
				line-height: 36px;
				height:36px;
			}
			.login_footer .f_main .link ul li{
				float: left;
				padding: 0 10px;
			}
			.login_footer .f_main .link b {
				display: inline;
				float: left;
				margin: 0 3px;
				font-weight: 400;
				color: #ddd;
			}
			.login_footer .f_main .copyright{
				width: 100%;
				line-height: 50px;
				color: #9c9c9c;
				font-size: 16px;
				text-align: center;
				float: left;
			}
			.login_footer .f_main .about{
				width: 100%;
				line-height: 30px;
				color: #9c9c9c;
				font-size: 16px;
				text-align: center;
				float: left;
			}
		</style>
		<script language="JavaScript">
			if (window != top)
				top.location.href = location.href;
		</script>
	</head>
	<body>
	<div class="login_header">
		<div class="h_main"></div>
	</div>
	<div class="login-wrap">
		<div class="login-bg"></div>
		<div class="login-main">
			<div class="login-box">
				<div class="login-title">
					用户登录&nbsp;&nbsp;/&nbsp;&nbsp;Login
				</div>
				<div class="login-form"></div>
				<div class="login-message">
					${message}
				</div>
				<div class="login-form">
					<form action="${ctx}/login" method="post">
						<div class="form-group">
							<input type="text" name="username" id="username" value="" class="form-control login-input" placeholder="请输入用户名"/>
							<label class="form-label" for="username">
								<i class="glyphicon glyphicon-user"></i>
							</label>
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password" value="" class="form-control login-input" placeholder="请输入密码" />
							<label class="form-label" for="password">
								<i class="glyphicon glyphicon-lock"></i>
							</label>
						</div>
						<div class="form-group margin-bottom-10">
							<label class="login-node-label">验证码：</label>
							<input type="text" name="" id="" value="" class="form-control login-node" />
							<div class="login-node-img">
								<a href="#"><img src="${ctxStatic}/img/node.jpg" /></a>
							</div>
						</div><div class="form-group margin-bottom-10">
						<label class="remberPwd"><input type="checkbox" class="checkbox-style" id="rememberMe" name="rememberMe" ${rememberMe ? 'check' : ''}/>记住密码</label>
						<div class="forgetPwd">
							<a href="#">忘记密码？</a>
						</div>
					</div>
						<div class="form-group">
							<input type="submit" class="btn btn-warning btn-lg btn-width center-block" value="登录" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="login_footer">
		<div class="f_main">
			<div class="link">
				<ul>
					<li><a href="#">湖南城市学院主站</a></li>
					<b>|</b>
					<li><a href="#">信息与电子工程学院</a></li>
					<b>|</b>
					<li><a href="#">XXX网</a></li>
				</ul>
			</div>
			<div class="copyright">
				&copy; 湖南城市学院&nbsp;信息与电子工程学院&nbsp;&nbsp;版权所有
			</div>
			<div class="about">
				技术支持：智相科技
			</div>
		</div>
	</div>
	</body>
</html>