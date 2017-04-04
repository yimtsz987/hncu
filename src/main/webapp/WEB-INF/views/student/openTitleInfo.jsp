<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<link rel="stylesheet" href="${ctxStatic}/css/gloab.css" />
		<title>审题报告</title>
	<style>
		.table_a{
			width: 1000px;
		}
		.table_1{
			margin-bottom: 50px;
		}
		.part3{
			margin: 50px 0px 50px 0px;
		}
		.submit_btn{
			width: 200px;
		}
		.teacher_proposal_text{
			height: 150px;
			text-indent: 45px;
		}
	</style>
</head>
	<body>
		<div class="page-content">
			<sys:msg msgObj="${msg}" />
			<c:if test="${openTitleInfo.reportFlag eq 0}">
			    <h2 class="text-center">教师正在查阅，请耐心等待！</h2>
			</c:if>
			<c:if test="${openTitleInfo.reportFlag ne 0}">
				<c:if test="${openTitleInfo.reportFlag eq 1}">
					<h3 class="part3 text-center"><span style="color: red;">${gpms:getUser().name}</span>同学，很遗憾，你<strong>开题报告</strong>未通过！<small>（请修改后重新提交）</small></h3>
				</c:if>
				<c:if test="${openTitleInfo.reportFlag eq 2}">
					<h3 class="part3 text-center">恭喜<span style="color: red;">${gpms:getUser().name}</span>同学，你<strong>开题报告</strong>通过，现在开始准备你下一个步骤吧！</h3>
				</c:if>
				<div class="table_a center-block">
					<div class="page-title">开题报告</div>
					<div class="table_1 table-responsive table-custom ">
						<table class="table table-hover table-bordered table-striped">
							<thead>
							<tr>
								<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
								<th>序号</th>
								<th>标题</th>
								<th>时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody id="tbodyId">
							<tr>
								<td><input type="checkbox" name="node"/></td>
								<td>${openTitleInfo.id}</td>
								<td>${fn:substringBefore(openTitleInfo.uploadFileOldName, ".")}</td>
								<td><fmt:formatDate value="${openTitleInfo.uploadDate}" pattern="yyyy年MM月dd日" /></td>
								<td>
								<span class="label label-success label-custom">
										${gpms:getDictLabel(openTitleInfo.reportFlag, 'understanding')}
								</span>
								</td>
								<td>
									<a href="${ctx}/student/downloadUnderstanding?id=${openTitleInfo.id}" class="btn btn-xs btn-primary">下载</a>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
					<div class="teacher_proposal">
						<h4>老师建议：</h4>
						<div class="teacher_proposal_text">
								${openTitleInfo.teacherAdvise}
						</div>
					</div>
					<c:if test="${openTitleInfo.reportFlag eq 1}">
						<div class="submit_btn center-block">
							<a href="${ctx}/student/uploadOpenTitleUpdate?id=${openTitleInfo.id}" class="btn btn-info col-lg-12" >重新提交</a>
						</div>
					</c:if>
				</div>
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