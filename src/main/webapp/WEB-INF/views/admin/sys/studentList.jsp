<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>学生管理</title>
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
				<div class="search-title">学生搜索查询</div>
				<form:form id="searchForm" modelAttribute="studentInfo" action="${ctx}/admin/studentList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${userPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${userPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="node" class="col-lg-3 control-label">学号</label>
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
						<label for="major" class="col-lg-3 control-label">专业</label>
						<div class="col-lg-9">
							<form:select path="major.id" cssClass="form-control" id="major">
								<form:option value="" label="选择专业" />
								<form:options items="${majorList}" itemLabel="name" itemValue="id"  />
							</form:select>
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="type" class="col-lg-3 control-label">班级</label>
						<div class="col-lg-9">
							<form:select path="classes.classId" cssClass="form-control" id="classes">
								<form:option value="" label="选择班级" />
								<form:options items="${classesList}" itemLabel="classId" itemValue="classId" />
							</form:select>
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="${ctx}/admin/studentEdit" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>新增学生
					</a>
					<a href="javascript:void(0)" class="btn btn-danger">
						<i class="glyphicon glyphicon-trash"></i>批量上传
					</a>
				</span>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>院系</th>
							<th>专业</th>
							<th>班级</th>
							<th>级数</th>
							<th>手机</th>
							<th>邮箱</th>
							<th>进度</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${userPageInfo.list}" var="student">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${student.node}</td>
							<td>${student.name}</td>
							<td>${gpms:getDictLabel(student.sex, 'sex')}</td>
							<td>${student.age}</td>
							<td>${student.department.name}</td>
							<td>${student.major.name}</td>
							<td>${student.classes.classId}</td>
							<td>${student.year}</td>
							<td>${student.mobile}</td>
							<td>${student.email}</td>
							<td>%64</td>
							<td>
								<span class="label label-success label-custom">
									进行中
								</span>
							</td>
							<td>
								<a href="chakan.html" class="btn btn-xs btn-info">查看</a>
								<a href="javascript:void(0)" class="btn btn-xs btn-primary" onclick="deleteBtn(this,${student.id})">删除</a>
								<a href="${ctx}/admin/studentEdit?id=${student.id}" class="btn btn-xs btn-danger">修改</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(userPageInfo)}
				</div>

		</div>
	</body>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
	</script>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
        function deleteBtn(obj,id){
            layer.confirm('您是否确定删除？', {
                btn: ['确定','取消'] //按钮
            },function(){
                window.location.href = "${ctx}/admin/deleteStudentInfo?id="+id;
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
