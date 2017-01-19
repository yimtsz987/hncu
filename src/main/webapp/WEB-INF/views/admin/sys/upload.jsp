<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>上传资料</title>
		<style type="text/css">
			.mystyle3{width:265px;margin-right:-20px;position:relative;}
			.mystyle4{margin-left:38px;}
			.gap{width:14px;}
		</style>
	</head>
	<body>
		<div class="page-content">
			</br></br>
			<ul class="nav nav-tabs">
				<li class="active"><a href="#">上传文件</a></li>
			</ul>
			</br></br>
			<form name="uploadData" action="${ctx}/file/uploadData" enctype="multipart/form-data" method="post" class="form-horizontal" id="form-id">
			   <div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">标题:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<input type="text" class="form-control input-sm" name="title" id="title"datatype="*" nullmsg="请输入您的标题" value maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入您的标题</span>
					</div>
				</div>
				<div class="form-group control-group">
					<label  class="control-label col-lg-2 col-xs-2 col-sm-2">接收人:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<select id="receive" name="receive" class="selectpicker bla bla bli form-control " multiple data-live-search="true">
							<option value="2">教务秘书</option>
							<option value="3">教研室主任</option>
							<option value="4">教师</option>
							<option value="5">学生</option>
						</select>
					</div>
					<div class="form-message"><span class="Validform_wrong Validform_checktip"></span></div>
				</div>
				<div class="form-group control-group">
					<label class="control-label col-lg-2 col-xs-2 col-sm-2">描述:</label>
					<div class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<input type="text" class="form-control input-sm" name="description" id="description" datatype="*" nullmsg="请输入您的标题" value maxlength="50" />
					</div>
					<div class="form-message control-label col-lg-2 col-xs-2 col-sm-2">
						<span style="padding-left: 20px;" class="Validform_checktip pull-left">请输入您的标题</span>
					</div>
				</div>
				<label style="margin-left:-8px;" class="control-label col-lg-2 col-xs-2 col-sm-2">添加附件:</label>
				<div style="margin-left:-1px;" class="col-lg-3 col-xs-3 col-sm-3 has-feedback">
						<input type="file" name="file" class="filestyle" data-buttonName="btn-primary">
					</div>
				<div class="form-action">
					<input class="btn btn-primary" type="submit" value="保存" />
					<input class="btn btn-primary" type="button" id="test"  value="测试" />
					<input class="btn btn-default" type="button" value="返回" onclick="history.go(-1)"/>
				</div>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$("#form-id").Validform({
			tiptype: 2,
			datatype: {
				//"date": /([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/
			}
		});
		$(":file").filestyle({buttonText: "浏览文件"});
		</script>
	<script type="text/javascript">
        $(window).on('load', function () {
            $('.selectpicker').selectpicker({
                'selectedText': 'cat'
            });
            // $('.selectpicker').selectpicker('hide');
        });

	</script>
</html>
