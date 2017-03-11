<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>新增课题</title>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">课题添加</a>
				</li>
			</ul>
			<form:form action="${ctx}/teacher/saveTitle" cssClass="form-horizontal" id="form-id" method="post" modelAttribute="title">
				<form:hidden path="id" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">课题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input id="title" path="title" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请填写课题" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请填写课题！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">描述:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input id="description" path="description" cssClass="form-control input-sm" maxlength="100" datatype="*" nullmsg="请输入课题描述" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入课题描述！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">难度:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:select path="level" cssClass="form-control input-sm" id="department" datatype="*" nullmsg="请选择难度">
							<form:option value="" label="----请选择难度----" />
							<form:options items="${gpms:getDictList('level')}" itemLabel="label" itemValue="value"  />
						</form:select>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请选择难度！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">类别:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:select path="kind" cssClass="form-control input-sm" id="department" datatype="*" nullmsg="请选择类别">
							<form:option value="" label="----请选择类别----" />
							<form:options items="${gpms:getDictList('kind')}" itemLabel="label" itemValue="value"  />
						</form:select>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请选择类别！</span></div>
				</div>
				<div class="form-action">
					<input class="btn btn-primary" type="submit" value="保存" />
					<input class="btn btn-default" type="button" value="返回" onclick="history.go(-1)"/>
				</div>
			</form:form>
		</div>
	</body>
<script type="text/javascript">
    $("#form-id").Validform({
        tiptype: 2,
        datatype: {
            //"date": /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/
        }
    });
</script>
</html>
