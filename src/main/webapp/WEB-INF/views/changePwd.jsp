<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>修改密码</title>
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
					<a href="#">修改密码</a>
				</li>
			</ul>
			<sys:msg msgObj="${msg}" />
			<form:form cssClass="form-horizontal" action="${ctx}/user/savePassword" method="post" modelAttribute="user" id="form-id">
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">原密码:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<input type="password" class="form-control input-sm" name="oldPassword" id="oldPassword" placeholder="请输入原密码" datatype="*" nullmsg="请输入原密码" ajaxurl="${ctx}/user/queryPassword?oldPassword" sucmsg="用户名验证通过！" nullmsg="请输入用户名！" errormsg="请用邮箱或手机号码注册！"/>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left"></span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">新密码:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<input type="password" name="newPassword" id="newPassword" class="form-control input-sm" placeholder="请输入密码" plugin="passwordStrength" errormsg="密码至少6个字符,最多18个字符！" datatype="*6-18" nullmsg="请输入密码"/>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left"></span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">确认密码:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<input type="password" class="form-control input-sm" placeholder="请输入密码" recheck="newPassword" datatype="*6-18" errormsg="两次输入的密码不一致！" nullmsg="请填写信息！" datatype="*" nullmsg="请输入密码"/>
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left"></span></div>
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
                    "date": /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/
                },
                usePlugin: {
                    passwordstrength: {
                        minLen: 6,//设置密码长度最小值，默认为0;
                        maxLen: 18,//设置密码长度最大值，默认为30;
                        trigger: function (obj, error) {
                            //该表单元素的keyup和blur事件会触发该函数的执行;
                            //obj:当前表单元素jquery对象;
                            //error:所设密码是否符合验证要求，验证不能通过error为true，验证通过则为false;

                            //console.log(error);
                            if (error) {
                                obj.parent().next().find(".Validform_checktip").show();
                                obj.parent().next().find(".passwordStrength").hide();
                            } else {
                                obj.parent().next().find(".Validform_checktip").hide();
                                obj.parent().next().find(".passwordStrength").show();
                            }
                        }
                    }
                }
            });
		</script>
	</body>
</html>
