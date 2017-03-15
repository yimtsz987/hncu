<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>审题报告列表</title>
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
				<form:form id="searchForm" modelAttribute="teacherSchedule" action="${ctx}/teacher/scheduleInfoList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${teacherSchedulePageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${teacherSchedulePageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="node" class="col-lg-3 control-label">学号</label>
						<div class="col-lg-9">
							<form:input path="user.student.node" cssClass="form-control" maxlength="50" id="node" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="name" class="col-lg-3 control-label">姓名</label>
						<div class="col-lg-9">
							<form:input path="user.name" cssClass="form-control" maxlength="50" id="name" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="reportFlag" class="col-lg-3 control-label">状态</label>
						<div class="col-lg-9">
							<form:select path="schedule.reportFlag" cssClass="form-control" id="reportFlag">
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
			<c:forEach items="${teacherSchedulePageInfo.list}" var="schedule" varStatus="sc">
				<c:set var="lastSort" value="${teacherSchedulePageInfo.list[sc.last].schedule.sort}" scope="page" />
				<c:set var="titleName" value="${teacherSchedulePageInfo.list[sc.first].user.student.titleName}" scope="page" />
				<c:set var="studentName" value="${teacherSchedulePageInfo.list[sc.first].user.name}" scope="page" />
			</c:forEach>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="javascript:void(0)" class="btn btn-warning" onclick="history.go(-1)">
						<i class="glyphicon glyphicon-share-alt"></i>返回上一页
					</a>
				</span>
				<span class="btn-title">分期报告</span>
			</div>
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				课题：${titleName} —— ${studentName}
			</div>
			<div class="table-responsive table-custom">
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
					<tbody  id="tbodyId">
					<c:forEach items="${teacherSchedulePageInfo.list}" var="schedule" varStatus="sc">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${schedule.schedule.sort}</td>
							<td>${schedule.schedule.content}</td>
							<td><fmt:formatDate value="${schedule.schedule.startDate}" pattern="yyyy年MM月dd日" /> - <fmt:formatDate value="${schedule.schedule.endDate}" pattern="yyyy年MM月dd日" /></td>
							<td>
								<c:if test="${schedule.schedule.remainingDate >= 0 and schedule.schedule.reportFlag ne '3'}">
									<strong>${schedule.schedule.remainingDate} 天</strong>
								</c:if>
								<c:if test="${schedule.schedule.remainingDate < 0 and schedule.schedule.reportFlag ne '3'}">
									<strong>已逾期（<i style="color: red">${schedule.schedule.remainingDate * (-1)} 天</i>）</strong>
								</c:if>
								<c:if test="${schedule.schedule.reportFlag eq '3'}">
									<strong>已完成</strong>
								</c:if>
							</td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(schedule.schedule.reportFlag, 'schedule')}
								</span>
							</td>
							<td>
								<c:if test="${schedule.schedule.reportFlag eq 0}">
									<strong>等待学生上传</strong>
								</c:if>
								<c:if test="${schedule.schedule.reportFlag eq 1}">
									<a href="${ctx}/teacher/scheduleCheck?id=${schedule.schedule.id}&schedule.lastSort=${lastSort}"  class="btn btn-xs btn-danger" >审核</a>
								</c:if>
								<c:if test="${schedule.schedule.reportFlag ne 1 and schedule.schedule.reportFlag ne 0}">
									<a href="${ctx}/teacher/scheduleCheck?id=${schedule.schedule.id}&schedule.lastSort=${teacherSchedulePageInfo.list[sc.last].schedule.sort}"  class="btn btn-xs btn-primary" >查看审核</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(teacherSchedulePageInfo)}
			</div>
		</div>
	</body>
<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		} 
		function choose (obj,id) {
  					layer.confirm('您是否确定下载？', {
  						btn: ['确定','取消'] //按钮
					},function(){
						layer.msg('下载成功！');
					},function(){
						layer.msg('下载失败！',{time:2000});
					}, function(){
						window.location.href = "/purchase/delete?id="+id;
					}
  					);
 				}
 				function remove(obj,id){
  					layer.confirm('您是否确定删除？', {
  						btn: ['确定','取消'] //按钮
					},function(){
						layer.msg('删除成功！');
					},function(){
						layer.msg('删除失败！',{time:2000});
					}, function(){
						window.location.href = "/purchase/delete?id="+id;
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
