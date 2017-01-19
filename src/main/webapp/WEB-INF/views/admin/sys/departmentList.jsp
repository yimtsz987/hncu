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
				<form:form id="searchForm" modelAttribute="department" action="${ctx}/admin/departmentList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${departmentPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${departmentPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">编号</label>
						<div class="col-lg-9">
							<form:select path="dNo" cssClass="form-control" id="type">
								<form:option value="" label="选择院系编号" />
								<form:options items="${nodeList}" />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">名称</label>
						<div class="col-lg-9">
							<form:select path="name" cssClass="form-control" id="type">
								<form:option value="" label="选择院系名称" />
								<form:options items="${nameList}" />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/admin/departmentEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>添加院系
					</a>
					<a href="javascript:void(0)" class="btn btn-danger">
						<i class="glyphicon glyphicon-trash"></i>批量删除
					</a>
				</span>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>院系编号</th>
							<th>院系名称</th>
							<th>人数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody  id="tbodyId">
					<c:forEach items="${departmentPageInfo.list}" var="department">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${department.dNo}</td>
							<td>${department.name}</td>
							<td>${department.count}</td>
							<th>
								<a href="${ctx}/admin/departmentEdit?id=${department.id}" class="btn btn-xs btn-primary">修改</a>
								<a href="javascript:void(0)" class="btn btn-xs btn-danger"onclick="deleteBtn(this,${department.id})">删除</a>
							</th>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(departmentPageInfo)}
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
						+ "</tr>");
			}
		}
	</script>
</html>
