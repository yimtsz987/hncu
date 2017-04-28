<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>查看学生信息</title>
		<style type="text/css">
		    body,html{box-sizing: border-box;}
		    body{background: #f7f7f7;}
			.info-wrap{
				width: 100%;
				float: left;
				height: 600px;
				border-radius: 10px;
				background: #fff;
			}
			.info-wrap .info-left{
				float: left;
				width: 30%;
				height: 100%;
				background: #5bc0de;
				border-radius: 10px 0 0 10px;
				box-sizing: border-box;
				padding-top: 125px;
			}
			.info-wrap .info-right{
				float: right;
				width: 70%;
				height: 100%;
				border-radius: 0 10px 10px 0;
				box-sizing: border-box;
				padding-top: 20px;
				padding-right: 100px;
				padding-left: 100px;
			}
			
			.info-wrap .info-left .info-headerpic{
				width: 150px;
				height: 150px;
				background: #fff;
				border-radius: 50%;
				margin: 0 auto;
			}
			.info-wrap .info-left .info-headerpic img{
				width: 150px;
				height: 150px;
				border-radius: 50%;
			}
			.info-wrap .info-left .info-text{
				text-align: center;
				margin-top: 20px;
			}
			.info-wrap .info-left .info-text p{
				color: #fff;
				line-height: 25px;
				font-size: 16px;
			}
			.info-wrap .info-left .info-left-btn{
				width: 30px;
				height: 30px;
				border-radius: 50%;
				background: #fff;
				text-align: center;
				line-height: 30px;
				margin: 20px auto;
			}
			.info-wrap .info-left .info-left-btn i{
				color: #5bc0de;
			}
			.info-right .info-main .info-right-text{
				line-height: 32px;
				font-size: 14px;
				font-weight: 600;
			}
			.label-size{
				color: #999c9d;
				font-size: 14px;
				font-weight: 500;
			}
		</style>
	</head>
	<body>
		<div class="page-content">
			<div class="info-wrap">
				<div class="info-left">
					<div class="info-headerpic">
						<img src="${ctxStatic}/${studentInfo.headerPic}" />
					</div>
					<div class="info-text">
					   <p>${studentInfo.student.node}</p>
					   <p>${studentInfo.name}</p>
					   <p>${studentInfo.student.department.name}</p>
					   <p>${studentInfo.student.major.name}</p>
					   <p>${studentInfo.student.titleName}</p>
					</div>
					<div class="info-left-btn">
						<i class="glyphicon glyphicon-chevron-right"></i>
					</div>
				</div>
				<div class="info-right">
				    <div class="info-main form-horizontal">
				        <div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.student.node}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.name}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${gpms:getDictLabel(studentInfo.sex, 'sex')}</div>
							</div>
						</div><div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.student.department.name}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.student.major.name}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.student.classes}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.mobile}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.email}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.qq}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">课&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.student.titleName}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">当前进度:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${studentInfo.student.stepNow}</div>
							</div>
							<div class=""></div>
						</div>
				    </div>
				    <div class="center-block" style="width: 150px;">
				    	<div class="btn btn-info center-block" onclick="history.go(-1)">返回</div>
				    </div>
				     
				</div>
			</div>
		</div>
	</body>
		<script type="text/javascript">
			$(function(){
		    	var win_width = $(window).width();
				var win_height = $(window).height();
				$("body").height(win_height-20);
				if(win_height>700){
					$(".info-wrap").css({'margin-top':(win_height-700)/2});
				}else{
					$(".info-wrap").css({'margin-top':0});
				}
		    })
			$(window).resize(function(){
				var win_width = $(window).width();
				var win_height = $(window).height();
				$("body").height(win_height-20);
				if(win_height>700){
					$(".info-wrap").css({'margin-top':(win_height-700)/2});
				}else{
					$(".info-wrap").css({'margin-top':0});
				}
			});
		</script>
</html>
