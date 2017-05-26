<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>答辩分组</title>
	<style>
		.text-center{margin: 18px;}
		.infor{width: 260px;margin: 20px auto;border: solid 1px #ccc;}
		.img{margin: 0 auto;display: block;width: 140px;height: 140px;}
		.row{margin-top:20px ;}
		.text-center{margin: 5px;}
		.caption{height: 110px;width: 200px;margin: 12px auto;}
		.caption_left{float: left;}
		.caption_left p{margin: 0 0 10px;}
		.caption_right{float: right;}
		.caption_right p{margin: 0 0 10px;height: 20px;width: 126px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis}
		.text-custom{font-weight: 600;line-height: 45px;}
		.count-down{height: 100px;padding-left: 40px;padding-right: 40px;}
		.count-down .count{font-size: 50px;font-weight: 900;line-height: 100px;float: left;width: 120px;text-align: center;}
		.count-down .count-font{font-size: 50px;font-weight: 900;line-height: 100px;float: right;width: 50px;}
		.end{font-size: 50px;font-weight: 900;line-height: 100px;text-align: center;}
	</style>
</head>
	<body>
		<div class="container">
			<c:if test="${answerFlag == true}">
			<div class="row">
				<div class="col-md-4 col-lg-4 col-sm-4" >
					<div class="infor" style="height: 191px;">
						<h4 class="text-center text-custom">答辩信息</h4>
						<div class="caption">
							<div class="caption_left">
								<p>答辩地点：</p>
								<p>答辩时间：</p>
								<p>答辩分组：</p>
								<p>教师数量：</p>
							</div>
							<div class="caption_right">
								<p class="text-center">${answer.address}</p>
								<p class="text-center"><fmt:formatDate value="${answer.answerTime}" pattern="yyyy-MM-dd HH:mm" /></p>
								<p class="text-center">${answer.answerClasses}班</p>
								<p class="text-center">${answer.teacherNum}人</p>
							</div>
						</div>
					</div>
					<div class="infor" style="height: 160px;">
						<h4 class="text-center text-custom">倒计时</h4>
						<div class="count-down">
							<c:if test="${answer.countDown >= 0}">
								<div class="count">${answer.countDown}</div>
								<div class="count-font">天</div>
							</c:if>
							<c:if test="${answer.countDown < 0}">
								<div class="end">已结束</div>
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-md-4 col-lg-4 col-sm-4">
					<div class="infor">
						<p class="text-center text-info" style="margin: 18px; background: url(${ctxStatic}/img/3.png) no-repeat center;">组长</p>
						<a href="#"><img class="img" src="${ctxStatic}/${leader.headerPic}"></a>
						<div class="caption">
							<div class="caption_left">
								<p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</p>
								<p>院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系：</p>
								<p>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</p>
								<p>研究方向：</p>
							</div>
							<div class="caption_right">
								<p class="text-center">${leader.name}</p>
								<p class="text-center">${leader.teacher.department.name}</p>
								<p class="text-center">${gpms:getDictLabel(leader.teacher.professionalTitle, 'professional')}</p>
								<p class="text-center">${leader.teacher.researchDirection}</p>
							</div>
						</div>
						<div class="center-block" style="width: 70px;margin-top: 10px;margin-bottom: 10px;">
							<a href="${ctx}/student/answerTeacherInfo?id=${leader.id}" class="btn btn-info btn-sm">查看更多</a>
						</div>
					</div>
				</div>
				<c:forEach items="${teacherList}" var="teacher">
				<div class="col-md-4 col-lg-4 col-sm-4">
					<div class="infor">
						<p class="text-center text-info" style="margin: 18px; background: url(${ctxStatic}/img/3.png) no-repeat center;">组员</p>
						<a href="#"><img class="img" src="${ctxStatic}//${teacher.headerPic}"></a>
						<div class="caption">
							<div class="caption_left">
								<p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</p>
								<p>院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系：</p>
								<p>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</p>
								<p>研究方向：</p>
							</div>
							<div class="caption_right">
								<p class="text-center">${teacher.name}</p>
								<p class="text-center">${teacher.teacher.department.name}</p>
								<p class="text-center">${gpms:getDictLabel(teacher.teacher.professionalTitle, 'professional')}</p>
								<p class="text-center">${teacher.teacher.researchDirection}</p>
							</div>
						</div>
						<div class="center-block" style="width: 70px;margin-top: 10px;margin-bottom: 10px;">
							<a href="${ctx}/student/answerTeacherInfo?id=${teacher.id}" class="btn btn-info btn-sm">查看更多</a>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
			</c:if>
			<c:if test="${answerFlag == false}">
				<div class="row">
					<h2>还未分配教师，请耐心等待！！</h2>
				</div>
			</c:if>
		</div>
	</body>
</html>