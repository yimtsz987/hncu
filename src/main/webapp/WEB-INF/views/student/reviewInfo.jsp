<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>查看评阅</title>
	<style>
		.table_a{
			width: 1000px;
		}
		.table_1{
			margin-bottom: 50px;
		}
		.part3{
			margin: 50px 0px 50px 0px;
		}
		.submit_btn{
			width: 200px;
		}
		.teacher_proposal_text{
			height: 100px;
		}
	</style>
</head>
<body>
<div class="page-content">
	<c:if test="${marking.state eq 0}">
		<h2>教师正在审核，请耐心等待！</h2>
	</c:if>
	<c:if test="${marking.state ne 0}">
		<c:if test="${marking.state eq 1}">
			<h3 class="part3 text-center"><span style="color: red;">${gpms:getUser().name}</span>同学，很遗憾，你<strong>论文评阅</strong>未通过！<small>（请修改后重新上传）</small></h3>
		</c:if>
		<c:if test="${marking.state eq 2}">
			<h3 class="part3 text-center">恭喜<span style="color: red;">${gpms:getUser().name}</span>同学，你<strong>论文评阅</strong>通过，现在开始准备你下一个步骤吧！</h3>
		</c:if>
	</c:if>
	<div class="table_a center-block">
		<div class="table_1 table-responsive table-custom ">
			<table class="table table-hover table-bordered table-striped">
				<thead>
				<tr>
					<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
					<th>序号</th>
					<th>标题</th>
					<th>上传时间</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<th><input type="checkbox" name="node"/></th>
					<th>1</th>
					<th>学生附件</th>
					<th><fmt:formatDate value="${marking.suploadDate}" pattern="yyyy-MM-dd" /></th>
					<th>
						<a href="${ctx}/student/downloadStudentReview?id=${marking.id}&studentCheckStr=${marking.studentCheckStr}" class="btn btn-xs btn-primary">下载</a>
					</th>
				</tr>
				<c:if test="${marking.tuploadFileOldName != null}">
					<tr>
						<th><input type="checkbox" name="node"/></th>
						<th>2</th>
						<th>教师附件</th>
						<th><fmt:formatDate value="${marking.tuploadDate}" pattern="yyyy-MM-dd" /></th>
						<th>
							<a href="${ctx}/student/downloadTeacherReview?id=${marking.id}&teacherCheckStr=${marking.teacherCheckStr}" class="btn btn-xs btn-primary">下载</a>
						</th>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<c:if test="${marking.teacherAdvise != null}">
			<div class="teacher_proposal">
				<h4>老师建议：</h4>
				<div class="teacher_proposal_text">
						${marking.teacherAdvise}
				</div>
			</div>
		</c:if>
		<div class="submit_btn center-block">
			<button class="btn btn-info col-lg-12" onclick="history.go(-1)">返回</button>
		</div>
	</div>
</div>
</body>
</html>
