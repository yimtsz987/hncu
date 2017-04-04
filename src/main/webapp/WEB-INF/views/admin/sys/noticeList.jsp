<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>通知公告</title>
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
				<form:form id="searchForm" modelAttribute="notice" action="${ctx}/notice/noticeList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${noticePageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${noticePageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">编号</label>
						<div class="col-lg-9">

						</div>
					</div>
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
					<a href="${ctx}/notice/noticeEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>发送通知
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
							<th>发布人</th>
							<th>发布时间</th>
							<th>接收角色</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${noticePageInfo.list}" var="notice">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${notice.id}</td>
							<td>${notice.title}</td>
							<td>${gpms:queryUserById(notice.issueId).name}</td>
							<td><fmt:formatDate value="${notice.issueDate}" type="date" pattern="yyyy-MM-dd" /></td>
							<td>${notice.userRoleName}</td>
							<td>
								<a href="${ctx}/notice/noticeContent?id=${notice.id}" class="btn btn-xs btn-primary">查看</a>
								<a href="${ctx}/notice/noticeEdit?id=${notice.id}" class="btn btn-xs btn-success">修改</a>
								<a href="javascript:void(0)" class="btn btn-xs btn-danger" onclick="deleteBtn(this,${notice.id},'${notice.checkId}')">删除</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(noticePageInfo)}
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
        function deleteBtn(obj,id,checkId){
            layer.confirm('您是否确定删除？', {
                btn: ['确定','取消'] //按钮
            },function(){
                window.location.href = "${ctx}/notice/deleteNotice?id="+id+ "&checkId=" + checkId;
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

