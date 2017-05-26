<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<link rel="stylesheet" href="${ctxStatic}/css/gloab.css" />
		<title>查看中期检查</title>
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
			<c:if test="${middleCheck.state eq 1}">
				<h2>教师正在审核，请耐心等待！</h2>
			</c:if>
			<c:if test="${middleCheck.state ne 1 && middleCheck.state ne 0}">
				<c:if test="${middleCheck.state eq 2}">
					<h3 class="part3 text-center"><span style="color: red;">${gpms:getUser().name}</span>同学，很遗憾，你<strong>${middleCheck.paramName}</strong>审核未通过！<small>（请修改后重新提交）</small></h3>
				</c:if>
				<c:if test="${middleCheck.state eq 3}">
					<h3 class="part3 text-center">恭喜<span style="color: red;">${gpms:getUser().name}</span>同学，你<strong>${middleCheck.paramName}</strong>审核通过，现在开始准备你下一个步骤吧！</h3>
				</c:if>
				<div class="table_a center-block">
					<div class="page-title">${middleCheck.paramName}</div>
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
								<td>${middleCheck.paramId}</td>
								<td>${fn:substringBefore(middleCheck.uploadFileOldName, ".")}</td>
								<td><fmt:formatDate value="${middleCheck.uploadDate}" pattern="yyyy年MM月dd日" /></td>
								<td>
								<span class="label label-success label-custom">
										${gpms:getDictLabel(middleCheck.state, 'schedule')}
								</span>
								</td>
								<td>
									<a href="${ctx}/student/downloadMiddleCheck?id=${middleCheck.id}&checkStr=${middleCheck.checkStr}" class="btn btn-xs btn-primary">下载</a>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
					<div class="teacher_proposal">
						<h4>老师建议：</h4>
						<div class="teacher_proposal_text">
							${middleCheck.teacherAdvise}
						</div>
					</div>
					<div class="submit_btn center-block">
						<c:if test="${middleCheck.state eq 2}">
							<a href="${ctx}/student/uploadMiddleEdit${middleCheck.suffix}?paramId=${middleCheck.paramId}" class="btn btn-info col-lg-5" >重新提交</a>
				        </c:if>
						<a href="javascript:void(0);" class="btn btn-default col-lg-5"  onclick="history.go(-1)" style="margin-left: 30px">返回</a>
					</div>
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
