<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp"%>
	<link rel="stylesheet" href="${ctxStatic}/css/gloab.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/step.css"/>
	<script type="text/javascript" src="${ctxStatic}/assets/laydate/laydate.js"></script>
		<title>开题</title>
		<style>
			.node{
				text-align: center;
			}
		</style>
	</head>
<body>
<div class="login-box f-mt10 f-pb50">
	<div class="main bgf">
		<div class="reg-box-pan display-inline">
			<div class="step">
				<ul>
					<li class="col-xs-4 on">
						<span class="num"><em class="f-r5"></em><i>1</i></span>
						<span class="line_bg lbg-r"></span>
						<p class="lbg-txt">提交开题报告</p>
					</li>
					<li class="col-xs-4">
						<span class="num"><em class="f-r5"></em><i>2</i></span>
						<span class="line_bg lbg-l"></span>
						<span class="line_bg lbg-r"></span>
						<p class="lbg-txt">填写计划</p>
					</li>
					<li class="col-xs-4">
						<span class="num"><em class="f-r5"></em><i>3</i></span>
						<span class="line_bg lbg-l"></span>
						<p class="lbg-txt">提交</p>
					</li>
				</ul>
			</div>
			<div class="reg-box" id="verifyCheck" style="margin-top:20px;">
				<form:form action="${ctx}/student/uploadOpenTitleReport" enctype="multipart/form-data" method="post" cssClass="form col-xs-12" cssStyle="margin-top: 50px;" id="form-id" modelAttribute="openTitle">
					<div class="part1 col-lg-12" style="padding-left: 0px;">
						<div class="col-xs-2"></div>
						<div class="col-xs-7" style="padding-left: 0px;">
							<div class="form-group">
								<input type="file" name="example" id="input01">
							</div>
						</div>
						<div class="item col-xs-12">
							<span class="intelligent-label f-fl">&nbsp;</span>
							<div class="f-fl item-ifo center-block">
								<a href="javascript:;" class="btn btn-blue f-r3" id="btn_part1">下一步</a>
							</div>
						</div>
					</div>
					<div class="part2" style="display:none;padding-left: 0px;">
						<div class="btn-wrap">
								<span class="btn-left">
							<a href="javascript:void(0)" class="btn btn-warning" id="btn_addtr">
								<i class="glyphicon glyphicon-plus"></i>新增行
							</a>
						</span>
						</div>
						<div class="table-responsive table-custom">
							<table id="dynamicTable" class="table table-hover table-bordered table-striped">
								<thead>
								<tr>
									<th>编号</th>
									<th>各阶段内容</th>
									<th>开始日期</th>
									<th>截止日期</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<th><input type="text" class="form-control node" readonly name="sort" size="2" value="1" /></th>
									<th><input type="text" name="content" class="form-control" /></th>
									<th><input type="text" onclick="laydate()" name="startDate" class="form-control" /></th>
									<th><input type="text" onclick="laydate()" name="endDate" class="form-control" /></th>
									<th>
										<a href="javascript:void(0)" class="btn btn-danger" onclick="deltr(this)">删除行</a>

									</th>
								</tr>
								</tbody>
							</table>
						</div>
						<div class="item col-xs-6">
							<span class="intelligent-label f-fl">&nbsp;</span>
							<div class="f-fl item-ifo center-block">
								<a href="javascript:;" class="btn btn-blue f-r3" id="btn_part_2">上一步</a>
							</div>
						</div>
						<div class="item col-xs-6">
							<span class="intelligent-label f-fl">&nbsp;</span>
							<div class="f-fl item-ifo center-block">
								<input type="button" class="btn btn-blue f-r3" id="btn_part2" value="下一步" />
							</div>
						</div>

					</div>
					<div class="part3 text-center" style="display:none;padding: 0px;">
						<h3 class="text-center">提示:提交后无法更改，请确认无误后提交！</h3>
						<div class="item col-xs-6">
							<span class="intelligent-label f-fl">&nbsp;</span>
							<div class="f-fl item-ifo center-block">
								<a href="javascript:;" class="btn btn-blue f-r3" id="btn_part_3">上一步</a>
							</div>
						</div>
						<div class="item col-xs-6">
							<span class="intelligent-label f-fl">&nbsp;</span>
							<div class="f-fl item-ifo center-block">
								<input type="submit" class="btn btn-blue f-r3" value="提交" />
							</div>
						</div>

					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script>
    $(function() {

        function nextStep(){
            $(".part2").hide();
            $(".part3").show();
            $(".step li").eq(2).addClass("on");
        }
        //第一页的确定按钮
        $("#btn_part1").click(function() {
            //if(!verifyCheck._click()) return;
            $(".part1").hide();
            $(".part2").show();
            $(".step li").eq(1).addClass("on");
        });
        //第二页的确定按钮
        $("#btn_part_2").click(function() {
            //if(!verifyCheck._click()) return;
            $(".part2").hide();
            $(".part1").show();
            $(".step li").eq(0).addClass("on");
            $(".step li").eq(1).removeClass("on");
        });
        $("#btn_part2").click(function() {
            //if(!verifyCheck._click()) return;
            var fa=0;
            $("input[name^='content']").each(function(i) {
                var text = $(this).val();
                if (text == "") {
                    layer.alert('该表格不能有空值!', {
                        title: '提示框',
                        icon: 0,
                    });
                    fa=1;
                    return fa;
                }
            });
            $("input[name^='start_time']").each(function(i) {
                var text = $(this).val();
                if (text == "") {
                    layer.alert('该表格不能有空值!', {
                        title: '提示框',
                        icon: 0,
                    });
                    fa=1;
                    return fa;
                }
            });
            $("input[name^='end_time']").each(function(i) {
                var text = $(this).val();
                if (text == "") {
                    layer.alert('该表格不能有空值!', {
                        title: '提示框',
                        icon: 0,
                    });
                    fa=1;
                    return fa;
                }
            });
            if(fa==0){
                $(".part2").hide();
                $(".part3").show();
                $(".step li").eq(2).addClass("on");
            }

        });
        //第三页的确定按钮
        $("#btn_part_3").click(function() {
            //if(!verifyCheck._click()) return;
            $(".part3").hide();
            $(".part2").show();
            $(".step li").eq(1).addClass("on");
            $(".step li").eq(2).removeClass("on");
        });
    });

    function showoutc() {
        $(".m-sPopBg,.m-sPopCon").show();
    }

    function closeClause() {
        $(".m-sPopBg,.m-sPopCon").hide();
    }
    $('#input01').filestyle({
        'buttonText': '选择文件夹'
    });
    $(function() {
        var show_count = 20; //要显示的条数
        var count = 1; //递增的开始值，这里是你的ID
        $("#btn_addtr").click(function() {
            var length = $("#dynamicTable tbody tr").length;
            //alert(length);
            if (length < show_count) //点击时候，如果当前的数字小于递增结束的条件
            {
                $("#dynamicTable tbody").append("<tr>"
                    + "<th><input type='text' class='form-control node' readonly name='sort' size='2' value='1'/></th>"
                    + "<th><input type='text' name='content' class='form-control' /></th>"
                    + "<th><input type='text' name='startDate' class='form-control' onclick='laydate()' /></th>"
                    + "<th><input type='text' name='endDate' class='form-control' onclick='laydate()' /></th>"
                    + "<th><a href='javascript:void(0)' class='btn btn-danger' onclick='deltr(this)'>删除行</a></th>"
                    + "</tr>"); //在表格后面添加一行
                changeIndex(); //更新行号
            }
        });
    });

    function changeIndex() {
        var i = 1;
        $("#dynamicTable tbody tr").each(function() { //循环tab tbody下的tr
            $(this).find("input[name='NO']").val(i++); //更新行号
        });
    }

    function deltr(opp) {
        var length = $("#dynamicTable tbody tr").length;
        //alert(length);
        if (length <= 1) {
            layer.confirm('至少保留一行!', {
                btn: ['确定'] //按钮
            });
        } else {
            $(opp).parent().parent().remove(); //移除当前行
            changeIndex();
        }
    }

    function next_btn(flag) {
        if (flag == true) {
            $("#btn_part1").removeClass("disabled");
        }
    }
</script>
</body>
</html>