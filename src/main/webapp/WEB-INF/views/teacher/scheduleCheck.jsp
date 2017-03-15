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
			<form:form action="${ctx}/teacher/saveScheduleCheck" enctype="multipart/form-data" method="post" cssClass="form-horizontal" cssStyle="margin-top: 50px;" id="form-id" modelAttribute="teacherSchedule">
				<form:hidden path="schedule.id" />
				<form:hidden path="schedule.studentId" />
				<form:hidden path="schedule.lastSort" />
				<form:hidden path="schedule.reportId" />
				<form:hidden path="schedule.sort" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">课题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<div class="inp" style="margin: 7px;">${teacherSchedule.user.student.titleName}</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">学生附件:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<strong><a href="${ctx}/teacher/downloadStudentOpenTitle?id=${teacherSchedule.schedule.id}&checkStr=${teacherSchedule.schedule.checkStr}" class="inp" style="line-height: 30px;">${teacherSchedule.schedule.uploadFileOldName}（点击下载）</a></strong>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师建议:</label>
					<div class="col-lg-5 col-xs-5 col-sm-5 has-feedback">
						<c:if test="${teacherSchedule.schedule.reportFlag eq 3}">
							${teacherSchedule.schedule.teacherAdvise}
						</c:if>
						<c:if test="${teacherSchedule.schedule.reportFlag ne 3}">
							<form:textarea path="schedule.teacherAdvise" cols="60" rows="15" id="area1" cssStyle="width:100%;"/>
						</c:if>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">审核:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<c:if test="${teacherSchedule.schedule.reportFlag eq 3}">
							<label class="control-label custom-label" style="margin-right: 30px;cursor: pointer;">
								<form:radiobutton path="schedule.reportFlag" value="3" disabled="true" />
								<span style="margin-left: 5px;font-size: 14px;">通过</span>
							</label>
							<label class="control-label custom-label" style="cursor: pointer;">
								<form:radiobutton path="schedule.reportFlag" value="2" disabled="true" />
								<span style="margin-left: 5px;font-size: 14px;color: #e9e7e3;">未通过</span>
							</label>
						</c:if>
						<c:if test="${teacherSchedule.schedule.reportFlag ne 3}">
							<label class="control-label custom-label" style="margin-right: 30px;cursor: pointer;">
								<form:radiobutton path="schedule.reportFlag" value="3" />
								<span style="margin-left: 5px;font-size: 14px;">通过</span>
							</label>
							<label class="control-label custom-label" style="cursor: pointer;">
								<form:radiobutton path="schedule.reportFlag" value="2"/>
								<span style="margin-left: 5px;font-size: 14px;">未通过</span>
							</label>
						</c:if>
					</div>
				</div>
				<div class="form-action">
					<c:if test="${teacherSchedule.schedule.reportFlag eq 1}">
						<input class="btn btn-primary" type="submit" value="提交" />
					</c:if>
					<c:if test="${teacherSchedule.schedule.reportFlag eq 2}">
						<input class="btn btn-primary" type="submit" value="修改" />
					</c:if>
					<input class="btn btn-default" type="button" value="返回" onclick="history.go(-1)"/>
				</div>
                </form:form>
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
</script>
</html>
