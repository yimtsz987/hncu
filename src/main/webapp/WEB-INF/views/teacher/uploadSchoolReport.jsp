<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>成绩上传</title>
		<style>
			.Validform_checktip{
				color: #999;
				font-size: 12px;
			}
			.line-height-27{
				line-height: 27px;
			}
		</style>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">成绩上传</a>
				</li>
			</ul>
			</br></br>
			<form:form cssClass="form-horizontal" action="${ctx}/teacher/saveSchoolReport" method="post" modelAttribute="schoolReport" id="form-id">
				<form:hidden path="id" />
				<input type="hidden" name="studentId" value="${studentInfo.id}" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">学号:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback line-height-27">
						${studentInfo.node}
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">姓名:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback line-height-27">
							${studentInfo.name}
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">课题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback line-height-27">
							${studentInfo.title.title}
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">指导教师:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback line-height-27">
							${studentInfo.teacher.name}
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">成绩:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="score" cssClass="form-control input-sm disabled" maxlength="50" datatype="*" nullmsg="请输入成绩" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入成绩！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">评语:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="teacherComment" cssClass="form-control input-sm" maxlength="120"/>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入评语！（选填）</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">审核:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<label class="control-label custom-label" style="margin-right: 30px;cursor: pointer;">
							<form:radiobutton path="passFlag" value="1"/>
							<span style="margin-left: 5px;font-size: 14px;">通过</span>
						</label>
						<label class="control-label custom-label" style="cursor: pointer;">
							<form:radiobutton path="passFlag" value="0" />
							<span style="margin-left: 5px;font-size: 14px;">未通过</span>
						</label>
					</div>
				</div>
				<div class="form-action">
					<input class="btn btn-primary" type="submit" value="保存" />
					<input class="btn btn-default" type="button" value="返回" onclick="history.go(-1)"/>
				</div>
			</form:form>
		</div>
		<script type="text/javascript">
		$("#form-id").Validform({
			tiptype: 2,
			datatype: {
				//"date": /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/
			}
		});
        $(document).ready(function(){
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });
        });
		</script>
	</body>
</html>
