<head>
<#assign staticPath=''>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>报名</title>
	<link rel="stylesheet" href="${staticPath}/css/layout.css"/>
    <link href="${staticPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${staticPath}/css/plug/bootstrapValidator.min.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap-dialog.css">
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/bootstrap-notify.css">
    <link href="${staticPath}/css/mobiscroll/mobiscroll.animation.css" rel="stylesheet" type="text/css" />
    <link href="${staticPath}/css/mobiscroll/mobiscroll.widget.css" rel="stylesheet" type="text/css" />
    <link href="${staticPath}/css/mobiscroll/mobiscroll.widget.ios.css" rel="stylesheet" type="text/css" />
    <link href="${staticPath}/css/mobiscroll/mobiscroll.scroller.css" rel="stylesheet" type="text/css" />
    <link href="${staticPath}/css/mobiscroll/mobiscroll.scroller.ios.css" rel="stylesheet" type="text/css" />


	<#--<script type="text/javascript">-->

		<#--$(function () {-->

			<#--var nowData=new Date();-->

			<#--var opt= {-->

				<#--theme:'ios', //设置显示主题-->

				<#--mode:'scroller', //设置日期选择方式，这里用滚动-->

				<#--display:'bottom', //设置控件出现方式及样式-->

				<#--preset : 'date', //日期:年 月 日 时 分-->

				<#--minDate: nowData,-->

				<#--maxDate:new Date(nowData.getFullYear(),nowData.getMonth(),nowData.getDate()+7,22,00),-->

<#--//              dateFormat: 'yy-mm-dd', // 日期格式-->

<#--//              dateOrder: 'yymmdd', //面板中日期排列格式-->

				<#--stepMinute: 5, //设置分钟步长-->

				<#--yearText:'年',-->

				<#--monthText:'月',-->

				<#--dayText:'日',-->

				<#--hourText:'时',-->

				<#--minuteText:'分',-->

				<#--lang:'zh' //设置控件语言};-->

			<#--};-->

			<#--$('#test').mobiscroll(opt);-->

		<#--});-->

	<#--</script>-->
</head>
<body>
	<div id="wrapper">
		<header id="header">

			<div id="headBox">
                 <img src="images/bg.jpg">
			</div>
		</header><!-- // header end -->
		<div class="content">
			<form action="/zhyou/member/save" method="post" id="step1_frm" class="form-ajax">
				<div class="mt5 row">
					<label class="frm_label col-xs-2">姓名</label>
					<div class="col-xs-10">
						<input type="text" data-bv-notempty data-bv-notempty-message="请录入姓名" name="caption" class="frm_input name" maxlength="32"/>
					</div>
				</div>

				<div class="mt5 row ">
					<label class="frm_label col-xs-2">手机</label>
					<div class="col-xs-10">
						<input type="text" name="phone" data-bv-phone="true" data-bv-phone-country="ZHCN" data-bv-phone-message="请输入正确手机号" data-bv-notempty data-bv-notempty-message="请录入家长手机号码" class="frm_input mobile"/>
					</div>

				</div>
				<div class="mt5 row">
					<label class="frm_label col-xs-2">性别</label>
					<div class="col-xs-10">
						<div class="frm_controls">
                            <select class="form-control " name="sex">
                                <option value='1' selected="selected">男</option>
                                <option value='0'>女</option>

                            </select>
						</div>
					</div>
				</div>

				<div class="toolBar col-xs-12">
                    <button id="nextBtn" type="submit" class="btn btn-green">报名</button>
				</div>
			</form>

		</div>

	</div><!-- // wrapper end -->
	

	<#--<script> -->
		<#--//显示提示框，目前三个参数(txt：要显示的文本；time：自动关闭的时间（不设置的话默认1500毫秒）；status：默认0为错误提示，1为正确提示；)-->
		<#--function showTips(txt,time,status)-->
		<#--{-->
			<#--var htmlCon = '';-->
			<#--if(txt != ''){-->
				<#--if(status != 0 && status != undefined){-->
					<#--htmlCon = '<div class="tipsBox" style="width:220px;padding:10px;background-color:#4AAF33;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="images/ok.png" style="vertical-align: middle;margin-right:5px;" alt="OK，"/>'+txt+'</div>';-->
				<#--}else{-->
					<#--htmlCon = '<div class="tipsBox" style="width:220px;padding:10px;background-color:#D84C31;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="images/err.png" style="vertical-align: middle;margin-right:5px;" alt="Error，"/>'+txt+'</div>';-->
				<#--}-->
				<#--$('body').prepend(htmlCon);-->
				<#--if(time == '' || time == undefined){-->
					<#--time = 1500; -->
				<#--}-->
				<#--setTimeout(function(){ $('.tipsBox').remove(); },time);-->
			<#--}-->
		<#--}-->
		<#---->
		<#--$(function(){-->
			<#--//AJAX提交以及验证表单-->
			<#--$('#nextBtn').click(function(){-->
				<#--var name = $('.name').val();-->
				<#--var mobile =$('.mobile').val();-->
				<#--var birthday = $('.birthday').val();-->
				<#--var sex= $('input:radio[name="sex"]:checked').val();-->
				<#--if(name == ''){-->
					<#--showTips('请填写您的姓名~');-->
				<#--}else if(mobile == ''){-->
					<#--showTips('请输入手机号')-->
				<#--}else if(birthday == ''){-->
					<#--showTips('请输入出生日期')-->
				<#--}else if(sex == null){-->
					<#--showTips('请选中一个')-->
				<#--}-->
			<#--});-->
			<#---->

		<#--});-->
	<#--</script>-->


    <script src="${staticPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${staticPath}/js/bootstrap.min.js"></script>
    <script src="${staticPath}/js/login/bootstrapValidator.min.js"></script>
    <script src="${staticPath}/js/login/bootstrap-notify.js"></script>
    <!--手机端时间-->
    <script src="${staticPath}/js/mobiscroll/mobiscroll.core.js"></script>
    <script src="${staticPath}/js/mobiscroll/mobiscroll.widget.js"></script>
    <script src="${staticPath}/js/mobiscroll/mobiscroll.scroller.js"></script>
    <script src="${staticPath}/js/mobiscroll/mobiscroll.util.datetime.js"></script>
    <script src="${staticPath}/js/mobiscroll/mobiscroll.datetimebase.js"></script>
    <script src="${staticPath}/js/mobiscroll/mobiscroll.widget.ios.js"></script>
    <script src="${staticPath}/js/mobiscroll/mobiscroll.i18n.zh.js"></script>

<script>
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function(data) {

            if(data.code==0){
                Notify.success(data.message);
				setTimeout("window.location.href='/zhyou/member/success'",1);
            }else{
                Notify.danger(data.message);
                window.location.reload();
            }
        }, 'json');
    });
</script>