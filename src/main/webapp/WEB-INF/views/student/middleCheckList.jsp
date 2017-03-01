<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>中期检查</title>
	</head>
	<body>
		<div class="page-content">
			<div class="page-search">
				<div class="search-title">搜索查询</div>
				<form:form id="searchForm" modelAttribute="middleCheckParam" action="${ctx}/student/middleCheckList" method="post" cssClass="form-horizontal">
					<div class="form-group pull-left col-lg-3">
						<label for="sort" class="col-lg-3 control-label">编号</label>
						<div class="col-lg-9">
							<form:input path="id" cssClass="form-control" maxlength="50" id="sort" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="middleName" class="col-lg-3 control-label">标题</label>
						<div class="col-lg-9">
							<form:input path="name" cssClass="form-control" maxlength="50" id="middleName" />
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				课题：${gpms:getUser().student.titleName}
			</div>
			<div class="table-responsive table-custom table_top">
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
					<c:forEach items="${middleCheckParamPageInfo.list}" var="middleCheckParam">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${middleCheckParam.id}</td>
							<td>${middleCheckParam.name}</td>
							<td>${middleCheckParam.description}</td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(middleCheckParam.studentState, 'schedule')}
								</span>
							</td>
							<td>
								<c:if test="${middleCheckParam.studentState eq 0}">
									<a href="${ctx}/student/uploadMiddleEdit${middleCheckParam.suffix}?paramId=${middleCheckParam.id}" class="btn btn-xs btn-primary">上传</a>
								</c:if>
								<c:if test="${middleCheckParam.studentState eq 1}">
									<strong>正在审查，请耐心等待</strong>
								</c:if>
								<c:if test="${middleCheckParam.studentState eq 2}">
									<a href="${ctx}/student/uploadMiddleEdit${middleCheckParam.suffix}?paramId=${middleCheckParam.id}" class="btn btn-xs btn-danger">重新上传</a>
								</c:if>
								<c:if test="${middleCheckParam.studentState eq 3}">
									<a href="${ctx}/student/downloadMiddleCheck?id=${middleCheckParam.middleCheckReportId}" class="btn btn-xs btn-primary">下载</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
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
