<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>教师添加</title>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">教师信息填写</a>
				</li>
			</ul>
            <form:form cssClass="form-horizontal" action="${ctx}/admin/saveTeacherInfo" method="post" modelAttribute="teacherInfo" id="form-id">
				<form:hidden path="id" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">教工号:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="node" id="node" cssClass="form-control input-sm" datatype="*" nullmsg="请输入教工号" maxlength="50"/>
					</div>
				    <div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left ${empty msg ? '' : 'Validform_wrong'}">${empty msg ? '请输入教工号' : msg.content}</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">姓名:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="name" id="name" cssClass="form-control input-sm" datatype="*" nullmsg="请输入姓名" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入姓名</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">身份证:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="idcard" id="idcard" cssClass="form-control input-sm" datatype="*" nullmsg="请输入身份证" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入身份证</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">性别:</label>
					<div class="col-lg-4 col-xs-3 col-sm-3 has-feedback">
						<label class="control-label col-lg-2 col-xs-2 col-sm-2"><form:radiobutton path="sex" value="0" />男</label>
						<label class="control-label col-lg-2 col-xs-2 col-sm-2"><form:radiobutton path="sex" value="1" />女</label>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">年龄:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="age" id="age" cssClass="form-control input-sm" datatype="*" nullmsg="请输入年龄" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入年龄</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">手机号码:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="mobile" id="mobile" cssClass="form-control input-sm" datatype="*" nullmsg="请输入手机号码" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入手机号码</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">邮箱:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="email" id="email" cssClass="form-control input-sm" datatype="*" nullmsg="请输入邮箱" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入邮箱</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">QQ号码:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="qq" id="qq" cssClass="form-control input-sm" datatype="*" nullmsg="请输入邮箱" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入QQ号码</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">地址:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="address" id="address" cssClass="form-control input-sm" datatype="*" nullmsg="请输入地址" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入地址</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">院系:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:select path="department.dNo" cssClass="form-control input-sm" id="department" datatype="*" nullmsg="请选择所属院系">
							<form:option value="" label="选择院系名称" />
							<form:options items="${departmentList}" itemLabel="name" itemValue="dNo" />
						</form:select>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">职称:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:select path="professionalTitle" cssClass="form-control input-sm" id="department" datatype="*" nullmsg="请选择职称">
							<form:option value="" label="请选择职称" />
							<form:options items="${gpms:getDictList('professional')}" itemLabel="label" itemValue="value" />
						</form:select>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入职称</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">研究方向:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input path="researchDirection" id="researchDirection" cssClass="form-control input-sm" datatype="*" nullmsg="请输入研究方向" maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入研究方向</span>
					</div>
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
