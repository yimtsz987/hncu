<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>通知公告内容</title>
		<style type="text/css">
			.notice-title {
				text-align: center;
			}
			.notice-issuer {
				text-align: center;
				line-height: 50px;
			}
			.notice-content{
				font-size: 25px;
				text-indent: 53px;
			}
			.notice-date{
				text-align: right;
				font-size: 25px;
				margin-top: 50px;
				margin-right: 100px;
			}
		</style>
	</head>
	<body>
		<div class="page-content">
			<div class="btn-wrap">
				<span class="btn-left">
					<a href="javascript:void(0)" class="btn btn-warning" onclick="history.go(-1)">
						<i class="glyphicon glyphicon-share-alt"></i>返回上一页
					</a>
				</span>
			</div>
			<h1 class="notice-title">${notice.title}</h1>
			<h4 class="notice-issuer">
				发送人：${gpms:queryUserById(notice.issueId).name}
			</h4>
			<div class="notice-content">
				${notice.content}
			</div>
			<div class="notice-date">
				<fmt:formatDate value="${notice.issueDate}" type="date" pattern="yyyy-MM-dd" />
			</div>
		</div>
	</body>
</html>
