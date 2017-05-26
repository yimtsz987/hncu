<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>教师评阅审核</title>
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
					<a href="#">教师评阅审核</a>
				</li>
			</ul>
			<form:form action="${ctx}/teacher/saveReviewWorkCheck" enctype="multipart/form-data" method="post" cssClass="form-horizontal" id="form-id" modelAttribute="teacherMarking">
				<form:hidden path="marking.id" />
				<form:hidden path="user.id" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">课题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<div class="inp" style="margin: 7px;">${teacherMarking.user.student.titleName}</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">学生附件:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<strong><a href="${ctx}/teacher/downloadStudentReviewWork?id=${teacherMarking.marking.id}&checkStr=${teacherMarking.marking.studentCheckStr}" class="inp" style="line-height: 30px;">${teacherMarking.marking.suploadFileOldName}（点击下载）</a></strong>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师建议:</label>
					<div class="col-lg-5 col-xs-5 col-sm-5 has-feedback">
						<c:if test="${teacherMarking.marking.state ne 0}">
							${teacherMarking.marking.teacherAdvise}
						</c:if>
						<c:if test="${teacherMarking.marking.state eq 0}">
							<form:textarea path="marking.teacherAdvise" cols="60" rows="15" id="area1" cssStyle="width:100%;"/>
						</c:if>
					</div>
				</div>
				<div class="form-group control-group">
					<c:if test="${teacherMarking.marking.state ne 0}">
						<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师附件:</label>
						<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
							<strong><a href="${ctx}/teacher/downloadTeacherReviewWork?id=${teacherMarking.marking.id}&checkStr=${teacherMarking.marking.teacherCheckStr}" class="inp" style="line-height: 30px;">${teacherMarking.marking.tuploadFileOldName}（点击下载）</a></strong>
						</div>
					</c:if>
					<c:if test="${teacherMarking.marking.state eq 0}">
						<label class="control-label col-lg-2 col-xs-2 col-sm-2">附件上传:</label>
						<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
							<input type="file" accept="application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document" name="file" id="input01">
						</div>
					</c:if>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">评阅成绩:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="marking.score" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请输入成绩" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入成绩！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">审核:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<c:if test="${teacherMarking.marking.state eq 2}">
							<label class="control-label custom-label" style="margin-right: 30px;cursor: pointer;">
								<form:radiobutton path="marking.state" value="2" disabled="true" />
								<span style="margin-left: 5px;font-size: 14px;">通过</span>
							</label>
							<label class="control-label custom-label" style="cursor: pointer;">
								<form:radiobutton path="marking.state" value="1" disabled="true" />
								<span style="margin-left: 5px;font-size: 14px;color: #e9e7e3;">未通过</span>
							</label>
						</c:if>
						<c:if test="${teacherMarking.marking.state ne 2}">
							<label class="control-label custom-label" style="margin-right: 30px;cursor: pointer;">
								<form:radiobutton path="marking.state" value="2" />
								<span style="margin-left: 5px;font-size: 14px;">通过</span>
							</label>
							<label class="control-label custom-label" style="cursor: pointer;">
								<form:radiobutton path="marking.state" value="1"/>
								<span style="margin-left: 5px;font-size: 14px;">未通过</span>
							</label>
						</c:if>
					</div>
				</div>
				<div class="form-action">
					<c:if test="${teacherMarking.marking.state eq 0}">
						<input class="btn btn-primary" type="submit" value="提交" />
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
    $("#form-id").Validform({
        tiptype: 2,
        datatype: {
            //"date": /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/
        }
    });
    bkLib.onDomLoaded(function() {
        new nicEditor().panelInstance('area1');
    });
    $('#input01').filestyle({
        'buttonText':'选择文件夹'
    });
</script>
</html>
