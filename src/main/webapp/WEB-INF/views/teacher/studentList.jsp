<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>学生列表</title>
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
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<c:if test="${studentList == null}">
					<h1>请等待学生选择！！</h1>
				</c:if>
				<c:if test="${studentList != null}">
					<c:forEach items="${studentList}" var="student">
						<div class="col-md-3 col-lg-3 col-sm-3 custom-hover">
							<div class="infor">
								<p class="text-center text-info" style="margin: 18px; background: url(${ctxStatic}/img/3.png) no-repeat center;">学生</p>
								<a href="#"><img class="img" src="${ctxStatic}/${student.headerPic}"></a>
								<div class="caption">
									<div class="caption_left">
										<p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</p>
										<p>班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</p>
										<p>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：</p>
										<p>课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</p>
									</div>
									<div class="caption_right">
										<p class="text-center">${student.name}</p>
										<p class="text-center">${student.student.classes}</p>
										<p class="text-center">${student.student.major.name}</p>
										<p class="text-center">${student.student.titleName}</p>
									</div>
								</div>
								<div class="center-block" style="width: 70px;margin-top: 10px;margin-bottom: 10px;">
									<a href="${ctx}/teacher/studentInfo?id=${student.id}" class="btn btn-info btn-sm">查看更多</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</body>
</html>