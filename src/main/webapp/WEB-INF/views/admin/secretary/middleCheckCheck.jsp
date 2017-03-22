<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>中期检查审核</title>
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
					<a href="#">中期检查审核</a>
				</li>
			</ul>
			<form:form action="${ctx}/secretary/saveMiddleCheck" enctype="multipart/form-data" method="post" cssClass="form-horizontal" id="form-id" modelAttribute="teacherMiddleCheck">
				<form:hidden path="middleCheck.id" />
				<form:hidden path="user.id" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">课题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<div class="inp" style="margin: 7px;">${teacherMiddleCheck.user.student.titleName}</div>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">学生附件:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<strong><a href="${ctx}/secretary/downloadStudentMiddleCheck?id=${teacherMiddleCheck.middleCheck.id}&checkStr=${teacherMiddleCheck.middleCheck.checkStr}" class="inp" style="line-height: 30px;">${teacherMiddleCheck.middleCheck.uploadFileOldName}（点击下载）</a></strong>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">教师建议:</label>
					<div class="col-lg-5 col-xs-5 col-sm-5 has-feedback">
						<c:if test="${teacherMiddleCheck.middleCheck.state ne 1 and teacherMiddleCheck.middleCheck.state ne 0}">
							${teacherMiddleCheck.middleCheck.teacherAdvise}
						</c:if>
						<c:if test="${teacherMiddleCheck.middleCheck.state eq 1 and teacherMiddleCheck.middleCheck.state ne 0}">
							<form:textarea path="middleCheck.teacherAdvise" cols="60" rows="15" id="area1" cssStyle="width:100%;"/>
						</c:if>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">审核:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<c:if test="${teacherMiddleCheck.middleCheck.state ne 1 and teacherMiddleCheck.middleCheck.state ne 0}">
							<label class="control-label custom-label" style="margin-right: 30px;cursor: pointer;">
								<form:radiobutton path="middleCheck.state" value="3" disabled="true" />
								<span style="margin-left: 5px;font-size: 14px;">通过</span>
							</label>
							<label class="control-label custom-label" style="cursor: pointer;">
								<form:radiobutton path="middleCheck.state" value="2" disabled="true" />
								<span style="margin-left: 5px;font-size: 14px;color: #e9e7e3;">未通过</span>
							</label>
						</c:if>
						<c:if test="${teacherMiddleCheck.middleCheck.state eq 1 and teacherMiddleCheck.middleCheck.state ne 0}">
							<label class="control-label custom-label" style="margin-right: 30px;cursor: pointer;">
								<form:radiobutton path="middleCheck.state" value="3" />
								<span style="margin-left: 5px;font-size: 14px;">通过</span>
							</label>
							<label class="control-label custom-label" style="cursor: pointer;">
								<form:radiobutton path="middleCheck.state" value="2"/>
								<span style="margin-left: 5px;font-size: 14px;">未通过</span>
							</label>
						</c:if>
					</div>
				</div>
				<div class="form-action">
					<c:if test="${teacherMiddleCheck.middleCheck.state eq 1 and teacherMiddleCheck.middleCheck.state ne 0}">
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
    bkLib.onDomLoaded(function() {
        new nicEditor().panelInstance('area1');
    });
    $('#input01').filestyle({
        'buttonText':'选择文件夹'
    });
</script>
</html>
