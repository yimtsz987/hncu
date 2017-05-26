<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>成绩报告单</title>
		<style>
			.results{
				position: relative;
				margin: 50px 80px 30px 80px;
				
			}
			.results .pass{
				position: absolute;
				left: 120px;
				top: 0px;
				height: 35px;
			 	width: 70px;
			 	border: 1px solid red;
				color: red;
				line-height: 28px;
				text-align: center;
				font-size: 16px;
				transform:rotate(-9deg);
			}
			.results .title{
				font-weight: 600;
				font-size: 20px;
			}
			.number{
				margin: 0px 0px 10px 30px;
			}
			.bottom{
				color: #1a4b38;
				
			}
			.bottom1{
				position: absolute;
				right: 10px;
				bottom: 68px;
				font-size: 22px;
				font-weight: 600;
				letter-spacing: 5px;
			}
			.bottom2{
				position: absolute;
				right: 3px;
				bottom: 20px;
				font-size: 20px;
			}
			.stamp{
				position: absolute;
				right: 17px;
				top: 130px;
			}
			.info{
				height: 190px;
				margin: 35px 80px 0px 80px;
			}
			.info .infos{
				font-size: 20px;
				float: left;
			}
			.info .infos p{
				margin-bottom: 15px;
			}
			.info .infos p span{
				font-size: 16px;
				color: #72897f;
			}
			.info .photo{
				float: right;
			}
			.prompt{height: 237px;
				font-size: 19px;
				margin-top: 25px;
				line-height: 40px;
				color: #FF0000;
			}
		</style>
	</head>
	<body>
		<div class="page-content">
			<div class="col-lg-12">
				<div class="center-block" style="width: 750px; background: #c5e1d5;box-shadow: 2px 2px 10px #696969">
					<hr style="background: #70a18e; height: 3px; margin: 0 0 5px 0;"/>
					<hr style="background: #70a18e; height: 2px; margin: 0 0 5px 0;"/>
					<hr style="background: #70a18e; height: 1px; margin: 0 0 5px 0;"/>
					<hr style="background: #70a18e; height: 1px; margin: 0 0 25px 0;"/>
					<p class="number">成绩单编号：<span>${schoolReport.reportId}</span></p>
					<h1 class="text-center text-success" style="letter-spacing:20px; line-height: 70px;">湖南城市学院毕业设计</h1>
					<h1 class="text-center text-success" style="letter-spacing:30px; text-indent: 30px;">成绩报告单</h1>
					<div class="center-block" style="width: 100px; margin-top: 20px;">
						<img src="${ctxStatic}/img/hunan.png" width="100px" />
					</div>
					<div class="info">
						<div class="infos">
							<p>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:&nbsp;<span>${gpms:getUser().name}</span></p>
							<p>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;:&nbsp;<span>${gpms:getUser().student.node}</span></p>
							<p>院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系&nbsp;:&nbsp;<span>${gpms:getUser().student.department.name}</span></p>
							<p>课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题&nbsp;:&nbsp;<span>${gpms:getUser().student.titleName}</span></p>
							<p>指导老师&nbsp;:&nbsp;<span>${gpms:getUser().student.teacherName}</span></p>
						</div>
						<div class="photo">
							<img src="${ctxStatic}/${gpms:getUser().headerPic}" width="170px" height="200px" />
						</div>
					</div>
					<div class="results">
						<p class="title">总分：<span>${schoolReport.score}</span></p>
						<div class="pass">${gpms:getDictLabel(schoolReport.passFlag, 'report')}</div>
						<div class="prompt">
							<c:if test="${schoolReport.passFlag eq 0}">
								<p>很遗憾，本次答辩未通过！</p>
								<p>请于XXXX年XX月XX日进行二次答辩。</p>
							</c:if>
							<c:if test="${schoolReport.passFlag eq 1}">
								<p style="color: #000;">教师评语：</p>
								<p style="text-indent: 30px;color: #000;">${schoolReport.teacherComment}</p>
							</c:if>
						</div>
						<div class="bottom1 bottom">湖南城市学院</div>
						<div class="bottom2 bottom">${gpms:getParamValue('department')}</div>
					</div>
					
					<hr style="background: #70a18e; height: 1px; margin: 0px 0 5px 0;"/>
					<hr style="background: #70a18e; height: 1px; margin: 0 0 5px 0;"/>
					<hr style="background: #70a18e; height: 2px; margin: 0 0 5px 0;"/>
					<hr style="background: #70a18e; height: 3px; margin: 0 0 5px 0;"/>
				</div>
			</div>
		</div>
	</body>
</html>
