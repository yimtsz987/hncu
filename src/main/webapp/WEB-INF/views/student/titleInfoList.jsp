<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<title>选择课题</title>
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
				    <form:form id="searchForm" modelAttribute="title" action="${ctx}/student/chooseTitleList" method="post" cssClass="form-horizontal">
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
			<sys:msg msgObj="${msg}" />
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				选择课题
			</div>
			<div class="table-responsive table-custom table_top">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>编号</th>
							<th>课题</th>
							<th>描述</th>
							<th>难度</th>
							<th>类别</th>
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
							<td>${gpms:getDictLabel(title.level, 'level')}</td>
							<td>${gpms:getDictLabel(title.kind, 'kind')}</td>
							<td>
								<span class="label label-success label-custom">
									${gpms:getDictLabel(title.selectFlag, 'state')}
								</span>
							</td>
							<td>
								<c:if test="${title.selectFlag eq 0}">
									<a href="javascript:void(0)" class="btn btn-xs btn-primary" onclick="chooseBtn(this,${title.id},'${title.teacherId}')">选择</a>
								</c:if>
								<c:if test="${title.selectFlag eq 1}">
									<strong>不可操作</strong>
								</c:if>
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
    function chooseBtn(obj,id, teacherId){
        layer.confirm('您是否确定选择？（注意：选择之后不允许更改，请慎重选择！！）', {
            btn: ['确定','取消'] //按钮
        },function(){
            window.location.href =  "${ctx}/student/chooseTitleAndTeacher?id="+id+"&teacherId="+teacherId;
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
                + "</tr>");
        }
    }
</script>
</html>
