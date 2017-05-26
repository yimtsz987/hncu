<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>友情链接管理</title>
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
				<form:form id="searchForm" modelAttribute="link" action="${ctx}/admin/linkEdit" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${linkPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${linkPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">标题</label>
						<div class="col-lg-9">

						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/admin/linkEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>新增链接
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
							<th>标题</th>
							<th>链接地址</th>
							<th>排序</th>
							<th>打开方式</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					    <c:forEach items="${linkPageInfo.list}" var="link">
							<tr>
								<td><input type="checkbox" name="node"/></td>
								<td>${link.id}</td>
								<td>${link.title}</td>
								<td><a href="http://${link.url}" target="_blank">${link.url}</a></td>
								<td>${link.sort}</td>
								<td>${gpms:getDictLabel(link.target, 'target')}(${link.target})</td>
								<td>
									<a href="#" class="btn btn-xs btn-primary">修改</a>
									<a href="javascript:void(0)" onclick="deleteDict(this,'${link.id}')" class="btn btn-xs btn-danger">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(linkPageInfo)}
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
                        + "<td></td> "
						+ "</tr>");
			}
		}
	</script>
</html>
