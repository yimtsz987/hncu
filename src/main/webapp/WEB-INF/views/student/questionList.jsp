<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>问题咨询</title>
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
                 <form:form id="searchForm" modelAttribute="marking" action="${ctx}/student/questionList" method="post" cssClass="form-horizontal">
					 <input id="pageNum" name="pageNum" type="hidden" value="${questionPageInfo.pageNum}"/>
					 <input id="pageSize" name="pageSize" type="hidden" value="${questionPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="sort" class="col-lg-3 control-label">序号</label>
						<div class="col-lg-9">
							<form:input path="sort" cssClass="form-control" maxlength="50" id="sort" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="suploadFileOldName" class="col-lg-3 control-label">标题</label>
						<div class="col-lg-9">
							<form:input path="suploadFileOldName" cssClass="form-control" maxlength="50" id="suploadFileOldName" />
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="查询" />
					<div class="clearfix"></div>
				 </form:form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/student/questionEdit" class="btn btn-warning" >
						<i class="glyphicon glyphicon-plus"></i>新增问题
					</a>
				</span>
			</div>
			<div class="table-responsive table-custom">
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
					<c:forEach items="${questionPageInfo.list}" var="question">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${question.sort}</td>
							<td>${fn:substringBefore( question.suploadFileOldName, '.')}(<fmt:formatDate value="${question.suploadDate}" pattern="MM-dd" />)</td>
							<td><fmt:formatDate value="${question.suploadDate}" pattern="yyyy年MM月dd日" /></td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(question.state, 'marking')}
								</span>
							</td>
							<td>
								<c:if test="${question.state eq 0}">
									<strong>正在批阅，耐心等待</strong>
								</c:if>
								<c:if test="${question.state eq 1}">
									<a href="${ctx}/student/questionInfo?id=${question.id}" class="btn btn-xs btn-primary">查看批阅</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(questionPageInfo)}
			</div>
		</div>
	</body>
<script type="text/javascript">
    function checkAll(obj){
        $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
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
