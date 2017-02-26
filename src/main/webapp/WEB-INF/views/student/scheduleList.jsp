<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>设计和论文撰写</title>
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
                    <form:form id="searchForm" modelAttribute="schedule" action="${ctx}/student/scheduleList" method="post" cssClass="form-horizontal">
						<input id="pageNum" name="pageNum" type="hidden" value="${schedulePageInfo.pageNum}"/>
						<input id="pageSize" name="pageSize" type="hidden" value="${schedulePageInfo.pageSize}"/>
						<div class="form-group pull-left col-lg-3">
							<label for="sort" class="col-lg-3 control-label">序号</label>
							<div class="col-lg-9">
								<form:input path="sort" cssClass="form-control" maxlength="50" id="sort" />
							</div>
						</div>
						<div class="form-group pull-left col-lg-3">
							<label for="content" class="col-lg-3 control-label">内容</label>
							<div class="col-lg-9">
								<form:input path="content" cssClass="form-control" maxlength="50" id="content" />
							</div>
						</div>
						<div class="form-group pull-left col-lg-3">
							<label for="reportFlag" class="col-lg-3 control-label">状态</label>
							<div class="col-lg-9">
								<form:select path="reportFlag" cssClass="form-control" id="reportFlag">
									<form:option value="" label="--请选择状态--" />
									<form:options items="${gpms:getDictList('schedule')}" itemLabel="label" itemValue="value"  />
								</form:select>
							</div>
						</div>

						<input type="submit" class="btn btn-primary" value="查询" />
						<div class="clearfix"></div>
					</form:form>
			</div>
			<div class="page-title"style="font-weight:600;text-indent:20px;">
				课题：${gpms:getUser().student.titleName}
			</div>
			<div class="table-responsive table-custom table_top">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>序号</th>
							<th>内容</th>
							<th>起止时间</th>
							<th>剩余时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${schedulePageInfo.list}" var="schedule">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${schedule.sort}</td>
							<td>${schedule.content}</td>
							<td><fmt:formatDate value="${schedule.startDate}" pattern="yyyy年MM月dd日" /> - <fmt:formatDate value="${schedule.endDate}" pattern="yyyy年MM月dd日" /></td>
							<td>
							   <c:if test="${schedule.remainingDate >= 0 and schedule.reportFlag eq '0'}">
								   <strong>${schedule.remainingDate} 天</strong>
							   </c:if>
								<c:if test="${schedule.remainingDate < 0 and schedule.reportFlag ne '2'}">
									<strong>已逾期（<i style="color: red">${schedule.remainingDate * (-1)} 天</i>）</strong>
								</c:if>
								<c:if test="${schedule.remainingDate < 0 and schedule.reportFlag eq '2'}">
									<strong>-</strong>
								</c:if>
							</td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(schedule.reportFlag, 'schedule')}
								</span>
							</td>
							<td>
								<a href="uploadDesign.html" class="btn btn-xs btn-primary">上传</a>
								<a href="uploadDesign.html" class="btn btn-xs btn-success">修改</a>
								<a href="javascript:void(0)" class="btn btn-xs btn-danger" onclick="remove(this,1)">下载</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(schedulePageInfo)}
			</div>
		</div>
	</body>
	<script type="text/javascript">
        function checkAll(obj){
            $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
        }
        function chooseBtn(obj,id){
            layer.confirm('您是否确定选择？（注意：选择之后不允许更改，请慎重选择！！）', {
                btn: ['确定','取消'] //按钮
            },function(){
                window.location.href = "${ctx}/student/chooseTitle?id="+id;
                layer.msg('删除成功！',{icon:1});
            },function(){
                layer.msg('取消删除！',{icon:1});
            });
        }
        if($("#tbodyId tr").length<10){
            var trLength = $("#tbodyId tr").length;
            for (var i=0;i<10-trLength;i++) {
                $("#tbodyId").append("<tr>"
                    + "<td></td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "<td></td>"
                    + "</tr>");
            }
        }
	</script>
</html>
