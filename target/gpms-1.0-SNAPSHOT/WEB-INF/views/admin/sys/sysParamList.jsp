<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>系统参数</title>
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
				<form:form id="searchForm" modelAttribute="sysParam" action="${ctx}/admin/sysParamList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${sysParamPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${sysParamPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">标签</label>
						<div class="col-lg-9">
							<form:select path="label" cssClass="form-control" id="type">
								<form:option value="" label="所有标签" />
								<form:options items="${labelList}" />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">键名</label>
						<div class="col-lg-9">
							<form:select path="paramKey" cssClass="form-control" id="type">
								<form:option value="" label="所有键名" />
								<form:options items="${keyList}" />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/admin/sysParamEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>新增系统参数
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
							<th>编号</th>
							<th>标签</th>
							<th>键名</th>
							<th>键值</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					    <c:forEach items="${sysParamPageInfo.list}" var="sysParam">
							<tr>
								<td><input type="checkbox" name="node"/></td>
								<td>${sysParam.id}</td>
								<td>${sysParam.label}</td>
								<td>${sysParam.paramKey}</td>
								<td>${sysParam.paramValue}</td>
								<td>
									<a href="${ctx}/admin/sysParamEdit?id=${sysParam.id}" class="btn btn-xs btn-primary">修改</a>
									<a href="javascript:void(0)" class="btn btn-xs btn-danger"onclick="deleteDict(this,${sysParam.id})">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(sysParamPageInfo)}
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
				+ "<td></td> "
				+ "</tr>");
			}
		}
	</script>
</html>
