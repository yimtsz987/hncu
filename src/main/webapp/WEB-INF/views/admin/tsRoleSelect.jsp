<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/include/head.jsp"%>
<title>毕业设计管理系统 - 角色切换</title>

<script language="javascript">
	$(function(){
    $('.loginbox0').css({'position':'absolute','left':($(window).width()-405)/2});
	$(window).resize(function(){  
    $('.loginbox0').css({'position':'absolute','left':($(window).width()-405)/2});
    })  
});  
</script> 
<style>
*{font-size:9pt;border:0;margin:0;padding:0;}
body{font-family:'微软雅黑'; margin:0 auto;min-width:980px;}
ul{display:block;margin:0;padding:0;list-style:none;}
li{display:block;margin:0;padding:0;list-style: none;}
img{border:0;}
dl,dt,dd,span{margin:0;padding:0;display:block;}
a,a:focus{text-decoration:none;color:#000;outline:none;blr:expression(this.onFocus=this.blur());}
a:hover{color:#EB5409;text-decoration:none;}
table{border-collapse:collapse;border-spacing: 0;}
cite{font-style:normal;}
h2{font-weight:normal;}

.logintop{height:47px; position:absolute; top:0; background:url(${ctxStatic}/img/loginbg1.png) repeat-x;z-index:100; width:100%;}
.logintop span{color:#fff; line-height:47px; background:url(${ctxStatic}/img/loginsj.png) no-repeat 21px 18px; text-indent:44px; color:#fff; float:left;}
.logintop ul{float:right; padding-right:30px;}
.logintop ul li{float:left; margin-left:20px; line-height:47px;}
.logintop ul li a{color:#fff;}
.logintop ul li a:hover{color:#fff;}


.loginbm{height:50px; line-height:50px; text-align:center; background:url(${ctxStatic}/img/loginbg2.png) repeat-x;position:absolute; bottom:0; width:100%; color:#fff8f5;}
.loginbm a{font-weight:bold;color:#fff8f5;}
.loginbm a:hover{color:#fff;}


.loginbody1{background:url(${ctxStatic}/img/loginbg4.png) no-repeat center center; width:100%; height:585px; overflow:hidden; position:absolute; top:47px;}

.loginbox0{width:429px;margin-top:0px;height:252px;}
.loginlist{width:429px; overflow:hidden;}
.loginlist{margin-top:30px;}
.loginlist li{float:left; margin-left:10px; margin-right:10px;}
.loginlist li a{ display:block;width:196px; height:252px; background:url(${ctxStatic}/img/lbg.png) no-repeat; text-align:center; padding-top:25px; cursor:pointer;}
.loginlist li a:hover{background:url(${ctxStatic}/img/lbg1.png) no-repeat;}
.loginlist li a p{font-size:16px; color:#fff; padding-top:10px;line-height: 50px;}

.logintest{color: #FFFFFF;font-size:16px;text-align: center;    margin-top: 80px;}
</style>
</head>

<body style="background-color:#62a8d1; background-image:url(images/light1.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div class="logintop">    
    <span>欢迎进入湖南城市学院毕业设计管理系统</span>    
    <ul>
    <li><a href="/login">注销</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
   </div>
    <div class="loginbody1">
    <div class="loginbox0">
    	<div class="logintest">请选择登录角色</div>
    <ul class="loginlist">
    <li style="margin-left:0px"><a href="${ctx}/teacher/index"><img src="${ctxStatic}/img/jszc.png" width="135px" height="135px"  alt="教师"/><p>教师</p></a></li>
    <li style="margin-right:0px; float: right;"><a href="${ctx}/secretary/index"><img src="${ctxStatic}/img/l01.png"  alt="教务秘书"/><p>教务秘书</p></a></li>
    </ul>
    </div>
    </div>
    <div class="loginbm">版权所有  2017&nbsp;&nbsp;&nbsp; <strong>湖南城市学院</strong></div>
</body>
</html>
