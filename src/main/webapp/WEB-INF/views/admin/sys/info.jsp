<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<title>form表单</title>
		<style>
			.Validform_checktip{
				color: #999;
				font-size: 12px;
			}
			.Validform_wrong{
				background: url(${ctxStatic}/img/error.png) no-repeat left center;
				color: red;
			}
			.Validform_right{
				background: url(${ctxStatic}/img/right.png) no-repeat left center;
				color: green;
			}
			
		</style>
		<style type="text/css">
			.info-wrap{
				width: 100%;
				min-width: 1100px;
				float: left;
				height: 600px;
				border-radius: 10px;
				background: #fff;
				margin-top: 20px !important;
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
				background: #f7f7f7;
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
			.form-control{
				width: 200px;
			}
			.form-group {
                margin-bottom: 20px;
            }
		</style>
	</head>
	<body>
		<div class="page-content">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#">个人信息</a>
				</li>
				<li>
					<a href="#">头像上传</a>
				</li>
			</ul>
			<div class="info-wrap">
				<div class="info-left">
					<div class="info-headerpic">
						<img src="${ctxStatic}/${gpms:getUser().headerPic}" />
					</div>
					<div class="info-text">
					   <p>系统管理员</p>
					   <p>陈立帆</p>
					</div>
					<div class="info-left-btn">
						<i class="glyphicon glyphicon-chevron-right"style="margin-top:5px;"></i>
					</div>
				</div>
				<div class="info-right">
				    <div class="info-main form-horizontal" id="info">
				        <div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;名:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<input type="text" class="form-control input-sm" name="title" id="title" value="系统管理员"  disabled="disabled" />							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<input type="text" class="form-control input-sm" name="title" id="title" value="15116741993"  disabled="disabled" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<input type="text" class="form-control input-sm" name="title" id="title" value="湖南城市学院毕业设计管理中心" disabled="disabled" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-lg-3 col-xs-3 col-sm-3 label-size">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限:</label>
							<div class="col-lg-6 col-xs-6 col-sm-6 has-feedback">
								<input type="text" class="form-control input-sm" name="title" id="title" value="系统管理员"  disabled="disabled" />
							</div>
						</div>
						
				    </div>
				    <div class="center-block" style="width:48%;">
					    <a href="javascript:void(0)" class="btn btn-primary" id="modify">修改信息</a>
					    <a href="javascript:void(0)" class="btn btn-success" style="margin-left: 20px;" id="save">保存信息</a>
			        </div>
				</div>
			</div>
		</div>
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
		<script type="text/javascript">
			$(function() {
				$("#modify").click(function() {
					$("#info input,select,textarea").each(function() {
						$(this).removeAttr("disabled");
					});
				});

				$("#save").click(function() {
					$("#info input,select,textarea").each(function() {
						$(this).attr("disabled", "disabled");
					});
				})

			})
		</script>
	</body>
</html>
