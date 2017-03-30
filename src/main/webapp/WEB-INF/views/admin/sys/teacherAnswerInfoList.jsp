<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>分组教师信息</title>
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
				<form id="searchForm" class="form-horizontal">
					<div class="form-group pull-left col-lg-3">
						<label for="node" class="col-lg-3 control-label">教工号</label>
						<div class="col-lg-9">
							<input name="node" class="form-control disabled" maxlength="50" id="node" disabled="disabled" />
						</div>
					</div>
					<div class="form-group pull-left col-lg-3">
						<label for="name" class="col-lg-3 control-label">姓名</label>
						<div class="col-lg-9">
							<input name="name" class="form-control disabled" maxlength="50" id="name" disabled="disabled" />
						</div>
					</div>
					<input type="submit" class="btn btn-primary mybtn disabled" value="查询" disabled />
					<div class="clearfix"></div>
				</form>
			</div>
			<sys:msg msgObj="${msg}" />
			<div class="btn-wrap">
				<span class="btn-left">
					<c:if test="${answerInfo.teacherNum < maxAnswerNum}">
						<a href="${ctx}/admin/settingAnswerTeacher?queryClasses=${answerInfo.answerClasses}&answerId=${answerInfo.id}" class="btn btn-primary">
							<i class="glyphicon glyphicon-plus"></i>新增组员
						</a>
					</c:if>
					<c:if test="${answerInfo.teacherNum >= maxAnswerNum}">
						<a href="javascript:void(0)" class="btn btn-primary disabled">
							<i class="glyphicon glyphicon-plus"></i>新增组员
						</a>
					</c:if>
				</span>
				<span class="btn-right">
					<a href="javascript:void(0)" class="btn btn-warning" onclick="history.go(-1);location.reload()">
						<i class="glyphicon glyphicon-share-alt"></i>返回上一页
					</a>
				</span>
				<span class="btn-title" style="letter-spacing: normal">分组班级：${answerInfo.answerClasses}班</span>
			</div>
			<div class="page-title" style="font-weight:600;text-indent:20px;">
				组长：${gpms:queryUserById(answerInfo.leaderId).name}
			</div>
			<div class="table-responsive table-custom">
				<table class="table table-hover table-bordered table-striped">
					<thead>
					<tr>
						<th><input id="checkbox-btn" type="checkbox" name="node" onclick="checkAll(this)"/></th>
						<th>教工号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>手机</th>
						<th>邮箱</th>
						<th>职称</th>
						<th>研究方向</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="tbodyId">
					<c:forEach items="${teacherInfoList}" var="teacher">
						<tr>
							<td><input type="checkbox" name="node"/></td>
							<td>${teacher.node}</td>
							<td>${teacher.name}</td>
							<td>${gpms:getDictLabel(teacher.sex,'sex')}</td>
							<td>${teacher.age}</td>
							<td>${teacher.mobile}</td>
							<td>${teacher.email}</td>
							<td>${gpms:getDictLabel(teacher.professionalTitle, 'professional')}</td>
							<td>${teacher.researchDirection}</td>
							<td>
								<a href="javascript:void(0)" class="btn btn-xs btn-danger" onclick="deleteBtn(this,${teacher.id},${answerInfo.id},${answerInfo.answerClasses},'${teacher.name}')">撤除</a>
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
	</script>
	<script type="text/javascript">
		function checkAll(obj){
		    $(".table-custom input[type='checkbox']").prop('checked', $(obj).prop('checked'));
		}
        function deleteBtn(obj,id,answerId,answerClasses,teacherName){
            layer.confirm('您是否确定撤除【'+ teacherName +'】？', {
                btn: ['确定','取消'] //按钮
            },function(){
                window.location.href = "${ctx}/admin/deleteAnswerTeacher?teacherId="+id+"&answerId="+answerId+"&answerClasses="+answerClasses;
                layer.msg('撤除成功！',{icon:1});
            },function(){
                layer.msg('取消撤除！',{icon:1});
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
                    + "</tr>");
            }
        }
	</script>
</html>
