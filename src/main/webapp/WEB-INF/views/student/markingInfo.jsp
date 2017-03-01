<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>查看批阅</title>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">查看批阅</a>
				</li>
			</ul>
			<form action="" class="form-horizontal"id="form-id">
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">课题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<div class="inp" style="margin: 7px;">${gpms:getUser().student.titleName}</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">问题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<div class="inp" style="margin: 7px;">${marking.studentQuestion}</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">上传附件:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<strong><a href="${ctx}/student/downloadStudentMarking?id=${marking.id}" class="inp" style="line-height: 30px;">${marking.suploadFileOldName}（点击下载）</a></strong>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师附件:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<strong><a href="${ctx}/student/downloadTeacherMarking?id=${marking.id}" class="inp" style="line-height: 30px;">${marking.tuploadFileOldName}（点击下载）</a></strong>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师建议:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						${marking.teacherAdvise}
					</div>
				</div>
				<div class="form-action">
					<input class="btn btn-default" type="button" value="返回" onclick="history.go(-1)"/>
				</div>
			</form>
		</div>
	</body>
</html>
