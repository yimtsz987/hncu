<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>系统首页-学生端</title>
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
			.ui-stepBar-wrap{position:relative;width:100%;height:130px;overflow:hidden;display:none;z-index:100;margin-top: 50px;}
			.ui-stepBar-wrap .ui-stepBar{position:relative;width:90%;height:12px;background:#cccccc;top:52px;left:5%;z-index:101;}
			.ui-stepBar-wrap .ui-stepBar .ui-stepProcess{position:relative;width:0;height:12px;background:#516784;top:0;left:0;z-index:102;}
			.ui-stepBar-wrap .ui-stepInfo-wrap{width:90%;margin:0 auto;height:100%;}
			.ui-stepBar-wrap .ui-stepInfo-wrap .ui-stepInfo{position:relative;float:left;padding-top:36px;text-align:center;}
			.ui-stepBar-wrap .ui-stepInfo-wrap .ui-stepInfo .ui-stepSequence{position:relative;padding:18px 25px;border-radius:50%;z-index:103;font-size: 18px;}
			.ui-stepBar-wrap .ui-stepInfo-wrap .ui-stepInfo .ui-stepName{position:relative;line-height:70px;z-index:103;font-size: 16px;}
			.ui-stepBar-wrap .ui-stepInfo-wrap .ui-stepInfo .judge-stepSequence-pre-change,
			.ui-stepBar-wrap .ui-stepInfo-wrap .ui-stepInfo .judge-stepSequence-hind-change{cursor:pointer;}
			.ui-stepBar-wrap .ui-stepInfo-wrap .ui-stepInfo .judge-stepSequence-pre-change:hover{box-shadow:0 0 3px 1px #516784;}
			.ui-stepBar-wrap .ui-stepInfo-wrap .ui-stepInfo .judge-stepSequence-hind-change:hover{box-shadow:0 0 3px 1px #cccccc;}
			.judge-stepSequence-pre{background:#516784;color:#ffffff;}
			.judge-stepSequence-hind{background:#cccccc;color:#000000;}
			.judge-stepSequence-pre:hover{text-decoration: none;background: #62a8d1;color: #fff}
			.author{position:absolute;bottom:0;width:100%;text-align:center;margin:40px auto;color:#1569ec;text-shadow:1px 1px 0 #e7e7e7, 0 1px 7px #fff;}
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
					<h2>${gpms:getUser().name}</h2>
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
				<div id="stepBar" class="ui-stepBar-wrap">
					<div class="ui-stepBar">
						<div class="ui-stepProcess"></div>
					</div>
				<div class="ui-stepInfo-wrap">
					<table class="ui-stepLayout" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td class="ui-stepInfo">
								<a href="${ctx}/student/chooseTeacherInfo" class="ui-stepSequence">1</a>
								<p class="ui-stepName">
								    ${gpms:getUser().student.step1 eq 0 ? '选择教师' : '查看教师'}
										<c:if test="${gpms:getUser().student.stepNow eq 1}">
											<strong style="color: red">（当前进度）</strong>
										</c:if>
								</p>
							</td>
							<td class="ui-stepInfo">
								<a href="${ctx}/student/chooseTitleInfo" class="ui-stepSequence">2</a>
								<p class="ui-stepName">
								    ${gpms:getUser().student.step2 eq 0 ? '选择课题' : '查看课题'}
										<c:if test="${gpms:getUser().student.stepNow eq 2}">
											<strong style="color: red">（当前进度）</strong>
										</c:if>
								</p>
							</td>
							<td class="ui-stepInfo">
								<strong class="ui-stepSequence">3</strong>
								<p class="ui-stepName">审题<c:if test="${gpms:getUser().student.stepNow eq 3}">
									<strong style="color: red">（当前进度）</strong>
								</c:if></p>
							</td>
							<td class="ui-stepInfo">
								<strong class="ui-stepSequence">4</strong>
								<p class="ui-stepName">开题</p>
							</td>
							<td class="ui-stepInfo">
								<strong class="ui-stepSequence">5</strong>
								<p class="ui-stepName">设计和论文撰写</p>
							</td>
							<td class="ui-stepInfo">
								<strong class="ui-stepSequence">6</strong>
								<p class="ui-stepName">教师批阅</p>
							</td>
							<td class="ui-stepInfo">
								<strong class="ui-stepSequence">7</strong>
								<p class="ui-stepName">中期检查</p>
							</td>
							<td class="ui-stepInfo">
								<strong class="ui-stepSequence">8</strong>
								<p class="ui-stepName">评阅</p>
							</td>
							<td class="ui-stepInfo">
								<strong class="ui-stepSequence">9</strong>
								<p class="ui-stepName">答辩</p>
							</td>
						</tr>
					</table>
				</div>
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
		stepBar.init("stepBar", {
			step : ${gpms:getUser().student.stepNow},
			animation : true
		});
	</script>
</html>
