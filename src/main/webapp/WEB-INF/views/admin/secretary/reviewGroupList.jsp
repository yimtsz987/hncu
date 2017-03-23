<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>院系菜单字典</title>
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
				<form:form id="searchForm" modelAttribute="teacherAndStudent" action="${ctx}/secretary/reviewGroupList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${teacherAndStudentPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${teacherAndStudentPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="node" class="col-lg-3 control-label">教工号</label>
						<div class="col-lg-9">
							<form:input path="teacherInfo.teacher.node" cssClass="form-control" maxlength="50" id="node" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="name" class="col-lg-3 control-label">姓名</label>
						<div class="col-lg-9">
							<form:input path="teacherInfo.name" cssClass="form-control" maxlength="50" id="name" />
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/secretary/randomGroup" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>随机分组
					</a>
				</span>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>教工号</th>
							<th>姓名</th>
							<th>学生数量</th>
							<th>评阅教师</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody  id="tbodyId">
					<c:forEach items="${teacherAndStudentPageInfo.list}" var="reviewGroup">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${reviewGroup.teacherInfo.teacher.node}</td>
							<td>${reviewGroup.teacherInfo.name}</td>
							<td>${reviewGroup.studentSum}</td>
							<td>${reviewGroup.reviewTeacherName}</td>
							<th>
								<a href="#}" class="btn btn-xs btn-primary">撤回</a>
								<a href="#" class="btn btn-xs btn-danger">分组</a>
							</th>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(teacherAndStudentPageInfo)}
				</div>
		</div>
	</body>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
		function deleteBtn(obj,id){
			layer.confirm('您是否确定删除？', {
				btn: ['确定','取消'] //按钮
			},function(){
				window.location.href = "${ctx}/admin/deleteDepartment?id="+id;
				layer.msg('删除成功！',{icon:1});
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
                        + "<td></td> "
						+ "</tr>");
			}
		}
	</script>
</html>
