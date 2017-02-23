<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>选择教师</title>
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
				<form:form id="searchForm" modelAttribute="teacherInfo" action="${ctx}/student/chooseTeacherList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${teacherInfoPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${teacherInfoPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="node" class="col-lg-3 control-label">教工号</label>
						<div class="col-lg-9">
							<form:input path="node" cssClass="form-control" maxlength="50" id="node" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="name" class="col-lg-3 control-label">姓名</label>
						<div class="col-lg-9">
							<form:input path="name" cssClass="form-control" maxlength="50" id="name" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="professional_title" class="col-lg-3 control-label">职称</label>
						<div class="col-lg-9">
							<form:select path="professionalTitle" cssClass="form-control" id="professional_title">
								<form:option value="" label="选择职称" />
								<form:options items="${gpms:getDictList('professional')}" itemLabel="label" itemValue="value"  />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="research_direction" class="col-lg-4 control-label">研究方向</label>
						<div class="col-lg-8">
							<form:input path="researchDirection" cssClass="form-control" maxlength="50" id="research_direction" />
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="查询教师" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="table-responsive table-custom table_top">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>教工号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>职称</th>
							<th>研究方向</th>
							<th>手机</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${teacherInfoPageInfo.list}" var="teacher">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${teacher.node}</td>
							<td>${teacher.name}</td>
							<td>${gpms:getDictLabel(teacher.sex, 'sex')}</td>
							<td>${gpms:getDictLabel(teacher.professionalTitle, 'professional')}</td>
							<td>${teacher.researchDirection}</td>
							<td>${teacher.mobile}</td>
							<td>
								<span class="label label-success label-custom">
									未被选
								</span>
							</td>
							<td>
								<c:if test="${teacher.studentSum <= 8}">
								   <a href="javascript:void(0)" class="btn btn-xs btn-primary"onclick="chooseBtn(this,${teacher.id})">选择</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="page-info">
				${gpms:pageStr(teacherInfoPageInfo)}
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
            window.location.href = "${ctx}/student/chooseTeacher?id="+id;
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
