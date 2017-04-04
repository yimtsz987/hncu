<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>查看批阅</title>
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
			<div class="part3 text-center">
				<h3>查看教师批阅信息</h3>
			</div>
			<div class="part3 text-center">
				<span class="label label-success label-custom">
					${gpms:getDictLabel(marking.state, 'marking')}
				</span>
			</div>
			<div class="table_a center-block">
				<div class="page-title">课题：${studentInfo.student.titleName} ----  ${studentInfo.name}</div>
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
								<a href="${ctx}/student/downloadStudentMarking?id=${marking.id}&studentCheckStr=${marking.studentCheckStr}" class="btn btn-xs btn-primary">下载</a>
							</th>
						</tr>
						<c:if test="${marking.tuploadFileOldName != null}">
							<tr>
								<th><input type="checkbox" name="node"/></th>
								<th>2</th>
								<th>教师附件</th>
								<th><fmt:formatDate value="${marking.tuploadDate}" pattern="yyyy-MM-dd" /></th>
								<th>
									<a href="${ctx}/student/downloadTeacherMarking?id=${marking.id}&teacherCheckStr=${marking.teacherCheckStr}" class="btn btn-xs btn-primary">下载</a>
								</th>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
				<c:if test="${marking.studentQuestion != null}">
					<div class="teacher_proposal">
						<h4>学生问题：</h4>
						<div class="teacher_proposal_text">
								${marking.studentQuestion}
						</div>
					</div>
				</c:if>
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
