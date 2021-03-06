<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>答辩分组</title>
		<style>
			.infor{width: 240px;margin: 0 auto;border: solid 1px #ccc;}
			.img{margin: 0 auto;display: block;width: 120px;height: 140px;}
			.row{margin-top:20px ;}
			.text-center{margin: 5px;font-size: 14px !important;}
			.caption{height:115px;}
			.nameplate{background: url(${ctxStatic}/img/3.png) no-repeat center;font-size: 14px !important;}
		</style>
	</head>
	<body>
		<div class="container">
			<c:if test="${answerFlag == true}">
				<div class="row">
					<div class="col-md-4 col-lg-4 col-sm-4">
						<div class="infor">
							<p class="text-center text-info nameplate">组长</p>
							<img class="img" src="${ctxStatic}/${leader.headerPic}">
							<div class="caption">
								<p class="text-center">${leader.name}</p>
								<p class="text-center">${leader.teacher.department.name}</p>
								<p class="text-center">${gpms:getDictLabel(leader.teacher.professionalTitle, 'professional')}</p>
								<p class="text-center">${leader.teacher.researchDirection}</p>
							</div>
							<div class="center-block" style="width: 70px;margin-top: 5px;margin-bottom: 5px;">
								<a href="${ctx}/student/answerTeacherInfo?id=${leader.id}" class="btn btn-info btn-sm">查看更多</a>
							</div>
						</div>
					</div>
					<c:forEach items="${teacherList}" var="teacher">
						<div class="col-md-4 col-lg-4 col-sm-4">
							<div class="infor">
								<p class="text-center text-info nameplate">组员</p>
								<img class="img" src="${ctxStatic}//${teacher.headerPic}">
								<div class="caption">
									<p class="text-center">${teacher.name}</p>
									<p class="text-center">${teacher.teacher.department.name}</p>
									<p class="text-center">${gpms:getDictLabel(teacher.teacher.professionalTitle, 'professional')}</p>
									<p class="text-center">${teacher.teacher.researchDirection}</p>
								</div>
								<div class="center-block" style="width: 70px;margin-top: 5px;margin-bottom: 5px;">
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