<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>系统参数</title>
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
				<form:form id="searchForm" modelAttribute="answer" action="${ctx}/admin/answerGroupList" method="post" cssClass="form-horizontal">
					<input id="pageNum" name="pageNum" type="hidden" value="${answerPageInfo.pageNum}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${answerPageInfo.pageSize}"/>
					<div class="form-group pull-left col-lg-3">
						<label for="leaderId" class="col-lg-3 control-label">组长编号</label>
						<div class="col-lg-9">
							<form:input path="leaderId" cssClass="form-control" maxlength="50" id="leaderId" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="answerClasses" class="col-lg-3 control-label">分组班级</label>
						<div class="col-lg-9">
							<form:input path="answerClasses" cssClass="form-control" maxlength="50" id="answerClasses" />
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn" value="查询" />
					<div class="clearfix"></div>
				</form:form>
			</div>
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="javascript:void(0);" class="btn btn-warning">
						<i class="glyphicon glyphicon-plus"></i>新增系统参数
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
							<th>组号</th>
							<th>分组班级</th>
							<th>组长</th>
							<th>组员</th>
							<th>学生信息</th>
							<th>答辩地点</th>
							<th>答辩时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbodyId">
					    <c:forEach items="${answerPageInfo.list}" var="answer">
							<tr>
								<td><input type="checkbox" name="node"/></td>
								<td>${answer.id}</td>
								<td>${answer.answerClasses}</td>
								<td>
									<strong>
										<c:if test="${answer.leaderId != null}">
											${gpms:queryUserById(answer.leaderId).name}
										</c:if>
										<c:if test="${answer.leaderId == null}">
											-
										</c:if>
									</strong>
								</td>
								<td>
									<strong>
								       ${answer.teacherNameString}
									</strong>
								</td>
								<td>
									<a href="${ctx}/admin/classesInfoList?classes=${answer.answerClasses}" class="btn btn-xs btn-primary">分组学生</a>
								</td>
								<td>
									<c:if test="${answer.address != null}">
										${answer.address}
									</c:if>
									<c:if test="${answer.address == null}">
										-
									</c:if>
								</td>
								<td>
									<c:if test="${answer.answerTime != null}">
										${gpms:getParamValue("answerDate")}&nbsp;${answer.answerTime}
									</c:if>
									<c:if test="${answer.answerTime == null}">
										-
									</c:if>
								</td>
								<td>
									<a href="javascript:void(0);" class="btn btn-xs btn-primary">设置组长</a>
									<a href="javascript:void(0);" class="btn btn-xs btn-primary">设置组员</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<div class="page-info">
					${gpms:pageStr(answerPageInfo)}
				</div>
		</div>
	</body>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
		function deleteDict(obj,id){
  					layer.confirm('您是否确定删除？', {
  						btn: ['确定','取消'] //按钮
					},function(){
						window.location.href = "${ctx}/admin/deleteSysParam?id="+id;
					},function(){
						layer.msg('取消删除！',{icon:1});
					});
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
                        + "<td></td> "
						+ "<td></td> "
						+ "<td></td> "
						+ "</tr>");
			}
		}
	</script>
</html>
