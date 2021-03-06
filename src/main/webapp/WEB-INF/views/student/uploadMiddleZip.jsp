<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>上传中期检查材料</title>
		<style>
			
		</style>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">上传中期检查材料</a>
				</li>
			</ul>
             <form:form action="${ctx}/student/uploadMiddleCheck" enctype="multipart/form-data" method="post" cssClass="form col-lg-12" cssStyle="margin-top: 50px;" id="form-id" modelAttribute="middleCheck">
				 <form:hidden path="paramId" />
					<div class="col-lg-12" style="padding: 100px 0;">
						<div class="form-group col-lg-6 center-block" style="width: 600px;float: none;">
							<input type="file" accept="application/x-zip-compressed" name="file" id="input01">
						</div>
					</div>
					<div class="form-action col-lg-12">
						<div class="form-group center-block" style="width: 20%;">
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-ok"></span> 上传
							</button>
							<button type="submit" class="btn btn-primary" onclick="history.go(-1)">
								<span class="glyphicon"></span> 返回
							</button>
						</div>
					</div>
             </form:form>
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
			'buttonText':'选择文件夹'
		});
	</script>
</html>
