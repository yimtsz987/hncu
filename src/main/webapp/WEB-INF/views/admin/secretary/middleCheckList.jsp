<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>中期检查资料学生列表</title>
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
				<form:form id="searchForm" modelAttribute="teacherMiddleCheck" action="${ctx}/secretary/middleCheckList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${teacherMiddleCheckPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${teacherMiddleCheckPageInfo.pageSize}"/>
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
						<label for="state" class="col-lg-3 control-label">状态</label>
						<div class="col-lg-9">
							<form:select path="user.student.step7" cssClass="form-control" id="state">
								<form:option value="" label="选择状态" />
								<form:options items="${gpms:getDictList('step')}" itemLabel="label" itemValue="value" />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="查询" />
					<div class="clearfix"></div>
                </form:form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				中期检查资料学生列表
			</div>
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>学号</th>
							<th>姓名</th>
							<th>课题</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody  id="tbodyId">
					<c:forEach items="${teacherMiddleCheckPageInfo.list}" var="middleCheck">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${middleCheck.user.student.node}</td>
							<td>${middleCheck.user.name}</td>
							<td>${middleCheck.user.student.titleName}</td>
							<td>
								<span class="label label-success label-custom">
										${gpms:getDictLabel(middleCheck.user.student.step7, 'step')}
								</span>
							</td>
							<td>
								<a href="${ctx}/secretary/middleCheckInfoList?middleCheck.studentId=${middleCheck.user.id}"  class="btn btn-xs btn-primary" >查看详情</a>
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
                    + "</tr>");
            }
        }
	</script>

</html>
