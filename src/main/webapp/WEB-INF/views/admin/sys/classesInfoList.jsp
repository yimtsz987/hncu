<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>班级学生信息</title>
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
				<form:form id="searchForm" modelAttribute="studentInfo" action="${ctx}/admin/classesInfoList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${studentInfoPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${studentInfoPageInfo.pageSize}"/>
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
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="btn-wrap">
				<span class="btn-right">
					<a href="javascript:void(0)" class="btn btn-warning" onclick="history.go(-1)">
						<i class="glyphicon glyphicon-share-alt"></i>返回上一页
					</a>
				</span>
				<span class="btn-title" style="letter-spacing: normal">分组班级：${classesInfo.classId}班</span>
			</div>
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				${classesInfo.department.name} —— ${classesInfo.major.name}
			</div>
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
							<th>学号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>级数</th>
							<th>手机</th>
							<th>邮箱</th>
							<th>所选教师</th>
							<th>所选课题</th>
							<th>进度</th>
							<th>成绩</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${studentInfoPageInfo.list}" var="student">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${student.node}</td>
							<td>${student.name}</td>
							<td>${gpms:getDictLabel(student.sex, 'sex')}</td>
							<td>${student.age}</td>
							<td>${student.year}</td>
							<td>${student.mobile}</td>
							<td>${student.email}</td>
							<td>
								<c:if test="${student.teacher.id ne null}">
									${student.teacher.name}
								</c:if>
								<c:if test="${student.teacher.id eq null}">
									<span class="label label-success label-custom">
									    未选择
								    </span>
								</c:if>
							</td>
							<td>
								<c:if test="${student.title.title ne null}">
									${student.title.title}
								</c:if>
								<c:if test="${student.title.title eq null}">
									<span class="label label-success label-custom">
									    未选择
								    </span>
								</c:if>
							</td>
							<td>${(student.stepNow / 10)*100}%</td>
							<td>
								<strong>
									<c:if test="${student.schoolReport.score eq null}">
										成绩待定
									</c:if>
									<c:if test="${student.schoolReport.score ne null}">
										${student.schoolReport.score}分
									</c:if>
							    </strong>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(studentInfoPageInfo)}
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
                    + "</tr>");
            }
        }
	</script>
</html>