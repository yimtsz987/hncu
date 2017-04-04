<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>新增批阅材料</title>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">新增批阅材料</a>
				</li>
			</ul>
             <form:form action="${ctx}/student/uploadMarking" enctype="multipart/form-data" method="post" cssClass="form col-lg-12" cssStyle="margin-top: 50px;" id="form-id" modelAttribute="marking">
				 <form:hidden path="sort" />
					<div class="col-lg-12" style="padding: 30px 0;">
						<div class="form-group col-lg-6 center-block" style="width: 600px;float: none;">
							<h4>批阅论文:</h4>
							<input type="file" accept="application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document" name="file" id="input01">
						</div>
					</div>
					<div class="col-lg-12" style="padding: 30px 0;">
						<div class="form-group col-lg-6 center-block" style="width: 600px;float: none;" id="sample">
							<h4>问题:</h4>
							<form:textarea path="studentQuestion" cols="60" rows="15" id="area1" cssStyle="width:100%;"/>
						</div>
					</div>
					<div class="form-action col-lg-12">
						<div class="form-group center-block" style="width: 20%;">
							<button type="submit" class="btn btn-primary">
								<span class="glyphicon glyphicon-ok"></span> 上传
							</button>
							<button type="submit" class="btn btn-warning" onclick="history.go(-1)">
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
        bkLib.onDomLoaded(function() {
            new nicEditor().panelInstance('area1');
        });
	</script>
</html>
