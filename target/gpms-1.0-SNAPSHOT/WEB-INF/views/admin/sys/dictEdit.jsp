<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>字典信息</title>
		<style>
			.Validform_checktip{
				color: #999;
				font-size: 12px;
			}
		</style>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">字典${not empty dict.id ? '修改' : '添加'}</a>
				</li>
			</ul>
			</br></br>
			<form:form cssClass="form-horizontal" action="${ctx}/admin/saveDict" method="post" modelAttribute="dict" id="form-id">
				<form:hidden path="id" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">键值:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input id="valueInput" path="value" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请输入键值" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入键值！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">标签:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="label" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请输入标签" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入标签！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">类型:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="type" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请输入类型"/>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入类型！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">描述:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="description" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="请输入描述" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入描述！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">排序:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="sort" cssClass="form-control input-sm" maxlength="11" datatype="*" nullmsg="请输入排序数" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入排序数！</span></div>
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
