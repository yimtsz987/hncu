<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>班级菜单字典</title>
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
				<form:form id="searchForm" modelAttribute="classes" action="${ctx}/admin/classesList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${classesPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${classesPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">班级</label>
						<div class="col-lg-9">
							<form:input path="classId" cssClass="form-control" maxlength="50" id="classId" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">专业</label>
						<div class="col-lg-9">
							<form:select path="major.id" cssClass="form-control" id="type">
								<form:option value="" label="选择专业" />
								<form:options items="${majorList}" itemLabel="name" itemValue="id"  />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">院系</label>
						<div class="col-lg-9">
							<form:select path="department.dNo" cssClass="form-control" id="type">
								<form:option value="" label="选择院系名称" />
								<form:options items="${departmentList}" itemLabel="name" itemValue="dNo" />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/admin/classesEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>添加班级
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
							<th>班级</th>
							<th>所属专业</th>
							<th>所属院系</th>
							<th>人数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody  id="tbodyId">
					<c:forEach items="${classesPageInfo.list}" var="classes">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${classes.classId}</td>
							<td>${classes.major.name}</td>
							<td>${classes.department.name}</td>
							<td>${classes.count}</td>
							<td>
								<a href="${ctx}/admin/classesEdit?id=${classes.id}" class="btn btn-xs btn-primary">修改</a>
								<a href="javascript:void(0)" class="btn btn-xs btn-danger"onclick="deleteBtn(this,${classes.id})">删除</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(classesPageInfo)}
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
				window.location.href = "${ctx}/admin/deleteClasses?id="+id;
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
