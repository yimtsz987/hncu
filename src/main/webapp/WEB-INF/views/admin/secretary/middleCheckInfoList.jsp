<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>中期检查</title>
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
				<form:form id="searchForm" modelAttribute="teacherMiddleCheck" action="${ctx}/secretary/middleCheckInfoList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${teacherMiddleCheckPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${teacherMiddleCheckPageInfo.pageSize}"/>
					<form:hidden path="middleCheck.studentId" />
					<div class="form-group pull-left col-lg-3">
						<label for="node" class="col-lg-3 control-label">编号</label>
						<div class="col-lg-9">
							<form:input path="middleCheck.paramId" cssClass="form-control" maxlength="50" id="node" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="name" class="col-lg-3 control-label">内容</label>
						<div class="col-lg-9">
							<form:input path="middleCheck.paramName" cssClass="form-control" maxlength="50" id="name" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="state" class="col-lg-3 control-label">状态</label>
						<div class="col-lg-9">
							<form:select path="middleCheck.state" cssClass="form-control" id="state">
								<form:option value="" label="选择状态" />
								<form:options items="${gpms:getDictList('schedule')}" itemLabel="label" itemValue="value" />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="btn-wrap">
				<span class="btn-left">
					<c:if test="${studentInfo.student.step6 eq 2 && studentInfo.student.step7 eq 1}">
						<a href="${ctx}/secretary/updateStudentStep?studentId=${studentInfo.id}" class="btn btn-warning" >
							<i class="glyphicon glyphicon-ok"></i>通过
						</a>
					</c:if>
					<c:if test="${studentInfo.student.step6 ne 2 && studentInfo.student.step7 eq 0}">
						<a href="javascript:void(0);" class="btn btn-warning disabled" disabled="disabled">
							<i class="glyphicon glyphicon-ok"></i>通过
						</a>
					</c:if>
					<c:if test="${studentInfo.student.step6 eq 2 && studentInfo.student.step7 eq 2}">
						<a href="javascript:void(0);" class="btn btn-danger" disabled="disabled">
							<i class="glyphicon glyphicon-ok"></i>已通过
						</a>
					</c:if>
					<c:if test="${studentInfo.student.step6 eq 2 && studentInfo.student.step7 eq 0}">
						<a href="javascript:void(0);" class="btn btn-warning disabled" disabled="disabled" >
							<i class="glyphicon glyphicon-remove"></i>未上传
						</a>
					</c:if>
				</span>
				<span class="btn-right">
					<a href="javascript:void(0)" class="btn btn-warning" onclick="history.go(-1)">
						<i class="glyphicon glyphicon-share-alt"></i>返回上一页
					</a>
				</span>
				<span class="btn-title">中期检查</span>
			</div>
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				课题：${studentInfo.student.titleName} —— ${studentInfo.name}
			</div>
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>编号</th>
							<th>内容</th>
							<th>描述</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${teacherMiddleCheckPageInfo.list}" var="middleCheck">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${middleCheck.middleCheck.paramId}</td>
							<td>${middleCheck.middleCheck.paramName}</td>
							<td>${middleCheck.middleCheck.paramDescription}</td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(middleCheck.middleCheck.state, 'schedule')}
								</span>
							</td>
							<td>
								<c:if test="${review.marking.state eq 0}">
									<a href="${ctx}/secretary/middleCheckCheck?id=${middleCheck.middleCheck.id}" class="btn btn-xs btn-danger">审核</a>
								</c:if>
								<c:if test="${review.marking.state ne 0}">
									<a href="${ctx}/secretary/middleCheckCheck?id=${middleCheck.middleCheck.id}" class="btn btn-xs btn-primary">查看审核</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(teacherMiddleCheckPageInfo)}
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
