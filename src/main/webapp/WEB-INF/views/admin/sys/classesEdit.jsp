<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>班级菜单字典</title>
		<style type="text/css">
			.mystyle3{width:353;margin-right:-20px;position:relative;}
			.mystyle4{margin-left:39px;}
			.gap{width:14px;}
		</style>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">班级菜单${not empty classes.id ? '修改' : '添加'}</a>
				</li>
			</ul>
			</br></br>
			<form:form action="${ctx}/admin/saveClasses" cssClass="form-horizontal" id="form-id" method="post" modelAttribute="classes">
				<form:hidden path="id" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">班级:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input id="valueInput" path="classId" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请院系编号" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请院系编号！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">所属专业:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:select path="major.id" cssClass="form-control input-sm" id="department" datatype="*" nullmsg="请选择所属院系">
							<form:option value="" label="选择所属专业" />
							<form:options items="${majorList}" itemLabel="name" itemValue="id"  />
						</form:select>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请选择所属院系！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">所属院系:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:select path="department.dNo" cssClass="form-control input-sm" id="department" datatype="*" nullmsg="请选择所属院系">
							<form:option value="" label="选择院系名称" />
							<form:options items="${departmentList}" itemLabel="name" itemValue="dNo" />
						</form:select>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请选择所属院系！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">级数:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input id="valueInput" path="year" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请输入级数" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入级数！</span></div>
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
		</script>
	</body>
</html>
