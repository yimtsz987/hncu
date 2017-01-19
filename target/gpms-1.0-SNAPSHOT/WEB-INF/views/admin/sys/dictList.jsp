<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>通用字典</title>
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
				<form:form id="searchForm" modelAttribute="dict" action="${ctx}/admin/dictList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${pageInfoDict.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${pageInfoDict.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">职称</label>
						<div class="col-lg-9">
							<form:select path="type" cssClass="form-control" id="type">
								<form:option value="" label="所有类型" />
								<form:options items="${typeList}" />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">描述</label>
						<div class="col-lg-9">
							<form:input path="description" cssClass="form-control" maxlength="50" id="description" />
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/admin/dictEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>新增字典
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
							<th>键值</th>
							<th>标签</th>
							<th>类型</th>
							<th>描述</th>
							<th>排序</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					    <c:forEach items="${pageInfoDict.list}" var="dict">
							<tr>
								<td><input type="checkbox" name="node"/></td>
								<td>${dict.value}</td>
								<td>${dict.label}</td>
								<td>${dict.type}</td>
								<td>${dict.description}</td>
								<td>${dict.sort}</td>
								<td>
									<a href="${ctx}/admin/dictEdit?id=${dict.id}" class="btn btn-xs btn-primary">修改</a>
									<a href="javascript:void(0)" class="btn btn-xs btn-danger"onclick="deleteDict(this,${dict.id},'${dict.type}')">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(pageInfoDict)}
				</div>
		</div>
	</body>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
		function deleteDict(obj,id,dictType){
  					layer.confirm('您是否确定删除？', {
  						btn: ['确定','取消'] //按钮
					},function(){
						window.location.href = "${ctx}/admin/deleteDict?id="+id+"&type="+dictType;
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
				+ "<td></td> "
				+ "</tr>");
			}
		}
	</script>
</html>
