<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>审题报告</title>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">审题报告</a>
				</li>
			</ul>
			<sys:msg msgObj="${msg}" />
			<c:if test="${understandingInfo.passFlag eq 0}">
				<h2>教师正在查阅，请耐心等待！</h2>
			</c:if>
			<c:if test="${understandingInfo.passFlag eq 1}">
				<h2>很遗憾，审题报告未通过！<small>（请修改后重新提交）</small></h2>
				<h3><a href="${ctx}/student/understanding?id=${understandingInfo.id}">点击重新提交开题报告</a></h3>
			</c:if>
			<c:if test="${understandingInfo.passFlag eq 2}">
				<h2>恭喜，审题通过！请继续下一步骤</h2>
			</c:if>
		</div>
	</body>
	<script type="text/javascript">
		$('input[id=lefile]').change(function() {
		$('#photoCover').val($(this).val());
		});
		$("#form-id").Validform({
			tiptype: 2,
			datatype: {
				//"date": /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/
			}
		});
		$('#input01').filestyle({
			'buttonText':'选择文件'
		});
	</script>
</html>
