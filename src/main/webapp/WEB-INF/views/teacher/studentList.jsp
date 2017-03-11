<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>学生列表</title>
		<style>
			.infor{width: 200px;margin: 0 auto;border: solid 1px #ccc;}
			img{margin: 0 auto;display: block;width: 120px;height: 140px;}
			.row{margin-top:20px ;}
			.text-center{margin: 5px;}
			.caption{height:115px;}
			.margin-bottom{margin-bottom: 50px;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<c:forEach items="${studentList}" var="student">
					<div class="col-md-3 col-lg-3 col-sm-6 margin-bottom">
						<div class="infor">
							<a href="#"><img src="${ctxStatic}/${student.headerPic}"></a>
							<div class="caption">
								<p class="text-center">${student.student.node} ，${student.name}</p>
								<p class="text-center">${student.student.department.name}</p>
								<p class="text-center">${student.student.major.name}</p>
								<p class="text-center">课题:${student.student.titleName}</p>
							</div>
							<div class="center-block" style="width: 70px;margin-top: 5px;margin-bottom: 5px;">
								<a href="${ctx}/teacher/studentInfo?id=${student.id}" class="btn btn-info btn-sm">查看更多</a>
							</div>
						</div>
					</div>
			    </c:forEach>
			</div>
		</div>
	</body>
</html>