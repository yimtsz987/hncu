<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>开题报告审核</title>
	<style type="text/css">
		.custom-label:hover{
			color: #2489c5;
		}
	</style>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">开题报告审核</a>
				</li>
			</ul>
			<form class="form-horizontal" id="form-id">
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">课题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<div class="inp" style="margin: 7px;">${teacherMarking.user.student.titleName}</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">学生附件:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<strong><a href="${ctx}/teacher/downloadStudentReview?id=${teacherMarking.marking.id}&checkStr=${teacherMarking.marking.studentCheckStr}" class="inp" style="line-height: 30px;">${teacherMarking.marking.suploadFileOldName}（点击下载）</a></strong>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师建议:</label>
					<div class="col-lg-5 col-xs-5 col-sm-5 has-feedback">
						<c:if test="${teacherMarking.marking.teacherAdvise != null}">
							${teacherMarking.marking.teacherAdvise}
						</c:if>
						<c:if test="${teacherMarking.marking.teacherAdvise == null}">
							无建议
						</c:if>
					</div>
				</div>
				<div class="form-group control-group">

						<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师附件:</label>
						<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
							<c:if test="${teacherMarking.marking.tuploadFileOldName != null}">
							  <strong><a href="${ctx}/teacher/downloadTeacherReview?id=${teacherMarking.marking.id}&checkStr=${teacherMarking.marking.teacherCheckStr}" class="inp" style="line-height: 30px;">${teacherMarking.marking.tuploadFileOldName}（点击下载）</a></strong>
							</c:if>
							<c:if test="${teacherMarking.marking.tuploadFileOldName == null}">
								无附件
							</c:if>
						</div>

				</div>
				<div class="form-action">
					<input class="btn btn-default" type="button" value="返回" onclick="history.go(-1)"/>
				</div>
			</form>
		</div>
	</body>
<script>
    $(document).ready(function(){
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
    bkLib.onDomLoaded(function() {
        new nicEditor().panelInstance('area1');
    });
    $('#input01').filestyle({
        'buttonText':'选择文件夹'
    });
</script>
</html>
