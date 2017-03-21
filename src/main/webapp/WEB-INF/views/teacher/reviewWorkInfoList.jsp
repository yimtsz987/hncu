<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>教师批阅</title>
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
				<form:form id="searchForm" modelAttribute="teacherMarking" action="${ctx}/teacher/reviewWorkInfoList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${teacherReviewPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${teacherReviewPageInfo.pageSize}"/>
					<form:hidden path="marking.studentId" />
					<div class="form-group pull-left col-lg-3">
						<label for="node" class="col-lg-3 control-label">序号</label>
						<div class="col-lg-9">
							<form:input path="marking.sort" cssClass="form-control" maxlength="50" id="node" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="name" class="col-lg-3 control-label">标题</label>
						<div class="col-lg-9">
							<form:input path="marking.suploadFileOldName" cssClass="form-control" maxlength="50" id="name" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="state" class="col-lg-3 control-label">状态</label>
						<div class="col-lg-9">
							<form:select path="marking.state" cssClass="form-control" id="state">
								<form:option value="" label="选择状态" />
								<form:options items="${gpms:getDictList('review')}" itemLabel="label" itemValue="value" />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				课题：${studentInfo.student.titleName} —— ${studentInfo.name}
			</div>
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>序号</th>
							<th>标题</th>
							<th>上传时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${teacherReviewPageInfo.list}" var="review">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${review.marking.sort}</td>
							<td>${fn:substringBefore( review.marking.suploadFileOldName, '.')}(<fmt:formatDate value="${review.marking.suploadDate}" pattern="MM-dd" />)</td>
							<td><fmt:formatDate value="${review.marking.suploadDate}" pattern="yyyy年MM月dd日" /></td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(review.marking.state, 'review')}
								</span>
							</td>
							<td>
								<c:if test="${review.marking.state eq 0}">
									<a href="${ctx}/teacher/markingCheck?id=${review.marking.id}" class="btn btn-xs btn-danger">评阅</a>
								</c:if>
								<c:if test="${review.marking.state ne 0}">
									<a href="${ctx}/teacher/markingCheck?id=${review.marking.id}" class="btn btn-xs btn-primary">查看评阅</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(teacherReviewPageInfo)}
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
