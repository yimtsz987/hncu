<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>个人信息</title>
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
			.form-horizontal .form-group{
				margin-bottom: 12px !important;
			}
		</style>
	</head>
	<body>
		<div class="page-content">
			<div class="info-wrap">
				<div class="info-left">
					<div class="info-headerpic">
						<img src="${ctxStatic}/${gpms:getUser().headerPic}" />
					</div>
					<div class="info-text">
					   <p>${user.name}</p>
					   <p>${user.teacher.node}</p>
						<p>${user.teacher.department.name}</p>
					   <p>${gpms:getDictLabel(user.teacher.professionalTitle, 'professional')}</p>
					   <p>${user.teacher.researchDirection}</p>
					</div>
					<div class="info-left-btn">
						<i class="glyphicon glyphicon-chevron-right"></i>
					</div>
				</div>
				<div class="info-right">
				    <div class="info-main form-horizontal">
				        <div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">教&nbsp;&nbsp;工&nbsp;&nbsp;号:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${user.teacher.node}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${user.name}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${gpms:getDictLabel(user.sex, 'sex')}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${gpms:getUser().age}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${user.teacher.department.name}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${gpms:getDictLabel(user.teacher.professionalTitle, 'professional')}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">研&nbsp;究&nbsp;方&nbsp;向:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${user.teacher.researchDirection}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${user.mobile}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${user.email}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${user.qq}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${gpms:getUser().address}</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<div class="info-right-text">${gpms:getDictLabel(gpms:getUser().expandFlag, 'role')}</div>
							</div>
						</div>
				    </div>
					<div class="center-block" style="width:48%;">
						<a href="${ctx}/teacher/teacherInfoEdit" class="btn btn-primary">修改信息</a>
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
