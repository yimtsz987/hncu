<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>课题列表</title>
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
				<form:form id="searchForm" modelAttribute="title" action="${ctx}/teacher/titleList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${titlePageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${titlePageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="title" class="col-lg-3 control-label">课题</label>
						<div class="col-lg-9">
							<form:input path="title" cssClass="form-control" maxlength="50" id="node" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="level" class="col-lg-3 control-label">难度</label>
						<div class="col-lg-9">
							<form:select path="level" cssClass="form-control" id="level">
								<form:option value="" label="--请选择难度--" />
								<form:options items="${gpms:getDictList('level')}" itemLabel="label" itemValue="value"  />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="kind" class="col-lg-3 control-label">类别</label>
						<div class="col-lg-9">
							<form:select path="kind" cssClass="form-control" id="level">
								<form:option value="" label="--请选择类别--" />
								<form:options items="${gpms:getDictList('kind')}" itemLabel="label" itemValue="value"  />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="state" class="col-lg-3 control-label">状态</label>
						<div class="col-lg-9">
							<form:select path="selectFlag" cssClass="form-control" id="state">
								<form:option value="" label="--请选择类别--" />
								<form:options items="${gpms:getDictList('state')}" itemLabel="label" itemValue="value"  />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/teacher/titleEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>新增课题
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
							<th>课题</th>
							<th>描述</th>
							<th>类别</th>
							<th>难度程度</th>
							<th>已选人</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${titlePageInfo.list}" var="title">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${title.id}</td>
							<td>${title.title}</td>
							<td>${title.description}</td>
							<td>${gpms:getDictLabel(title.kind, 'kind')}</td>
							<td>${gpms:getDictLabel(title.level, 'level')}</td>
							<td>${title.studentName == null ? '-':title.studentName}</td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(title.selectFlag, 'state')}
								</span>
							</td>
							<td>
								<a href="${ctx}/teacher/titleEdit?id=${title.id}" class="btn btn-xs btn-primary">编辑</a>
								<a href="javascript:void(0)" onclick="deleteBtn(this,${title.id},'${title.checkTitle}')" class="btn btn-xs btn-danger">删除</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(titlePageInfo)}
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
        function deleteBtn(obj,id,checkTitle){
            layer.confirm('您是否确定删除？', {
                btn: ['确定','取消'] //按钮
            },function(){
                window.location.href = "${ctx}/teacher/deleteTitle?id="+ id +"&title=" + checkTitle;
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
                    + "<td></td>"
                    + "<td></td>"
                    + "</tr>");
            }
        }
	</script>
</html>
