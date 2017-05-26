<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>报告及文件列表</title>
		<script type="text/javascript">
			function page(n, s) {
				$("#pageNum").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
				return false;
			}
		</script>
	</head>
	<body>
		<div class="page-content">
			<div class="page-search">
				<div class="search-title">搜索查询</div>
				<form:form id="searchForm" modelAttribute="reportAndFile" action="${ctx}/data/reportAndFileList" method="post" cssClass="form-horizontal">
					<form:hidden path="studentId"/>
					<input id="pageNum" name="pageNum" type="hidden" value="${reportAndFilePageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${reportAndFilePageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">步骤</label>
						<div class="col-lg-9">

						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				课题：${gpms:queryUserById(studentId).student.titleName} —— ${gpms:queryUserById(studentId).name}  ——指导老师：${gpms:queryUserById(studentId).student.teacherName}
			</div>
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>学生文件</th>
							<th>教师文件</th>
							<th>步骤</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					    <c:forEach items="${reportAndFilePageInfo.list}" var="file">
							<tr>
								<td><input type="checkbox" name="node"/></td>
								<td>${file.sUploadFileOldName}</td>
								<td>${file.tUploadFileOldName}</td>
								<td>${file.step}</td>
								<td>
									<a href="#" class="btn btn-xs btn-danger">下载</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(reportAndFilePageInfo)}
				</div>
		</div>
	</body>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
		function deleteDict(obj,id){
  					layer.confirm('您是否确定删除？', {
  						btn: ['确定','取消'] //按钮
					},function(){
						window.location.href = "${ctx}/admin/deleteSysParam?id="+id;
					},function(){
						layer.msg('取消删除！',{icon:1});
					});
 				}
		if($("#tbodyId tr").length<10){
			var trLength = $("#tbodyId tr").length;
			for (var i=0;i<10-trLength;i++) {
				$("#tbodyId").append("<tr> "
						+ "<td></td> "
				        + "<td></td> "
						+ "<td></td> "
						+ "<td></td> "
						+ "<td></td> "
						+ "</tr>");
			}
		}
	</script>
</html>
