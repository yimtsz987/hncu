<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<script type="text/javascript" src="${ctxStatic}/assets/laydate/laydate.js"></script>
		<title>答辩时间信息</title>
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
					<a href="javascript:void(0)">【${answer.answerClasses}】班答辩时间信息</a>
				</li>
			</ul>
			</br></br>
			<form:form action="${ctx}/admin/updateAnswerTimeInfo" cssClass="form-horizontal" id="form-id" method="post" modelAttribute="answer">
				<form:hidden path="answerClasses" />
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">答辩地点:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input id="valueInput" path="address" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="答辩地点" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">答辩地点！</span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">答辩时间:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<form:input id="answerTime" path="dateString" cssClass="form-control input-sm" maxlength="50" datatype="*" nullmsg="答辩时间" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2"><span style="padding-left: 20px;" class="Validform_checktip pull-left">答辩时间！</span></div>
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
		<script>
            laydate({
                elem: '#answerTime', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
                format: 'YYYY-MM-DD hh:mm:ss',
                min: laydate.now(),
                max: '2099-06-16 23:59:59',
                istime: true,
                istoday: false
            });
		</script>
	</body>
</html>
