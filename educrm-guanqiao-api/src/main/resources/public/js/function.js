

/*                    ��   ��1*/
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
                    if($form.data('target')){
                        setTimeout("window.location.href='"+$form.data('target')+"'",3000);
                    }
				}else{
						Notify.danger(data.message);
                       $('.btn-sm').attr("disabled",false);
                    //window.location.reload();
				}
            }, 'json');
        });

/*               ��  ��2*/
		$('.form-noajax').bootstrapValidator().on('success.form.bv', function(e) {
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');
			$form.off('submit').submit();
            // Use Ajax to submit form data
           
        });

        $(document).on('click.modal.data-api', '[data-toggle="modal"]', function(e) {


            // var imgUrl=app.config.loadImg;

            var $this = $(this),
                href = $this.attr('href'),
                url = $(this).data('url');

            if (url) {

                var $target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, '')));

                // var $loadingImg="<img src='"+imgUrl+"' class='modal-loading' style='z-index:1041;width:60px;height:60px;position:absolute;top:50%;left:50%;margin-left:-30px;margin-top:-30px;'/>";
                // $target.html($loadingImg);
                $target.load(url);
            }
        });


$('.js-datepicker').datetimepicker({//年月日
    format: 'yyyy-mm-dd',
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0});

$('.js-datepickertime').datetimepicker({//时间
    format: 'hh:ii',
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0});

$('.js-datetimepicker2').datetimepicker({//年月日时分秒
    format: 'yyyy-mm-dd hh:ii',
    autoclose: true,
    minView: 0,
    todayBtn:  1,//今日按钮
    todayHighlight:true,//高亮日期
    minuteStep:10,
    language:  'zh-CN',
});

$('.js-datetimepicker').datetimepicker({//年月日
    format: 'yyyy-mm-dd hh:mm:ss',
    autoclose: 1,
    language:  'zh-CN',
    minView:0,
   })
    .on('hide',function(e) {
    $("form").bootstrapValidator();
       $("form").data('bootstrapValidator')
       .updateStatus($('.js-datetimepicker'), 'NOT_VALIDATED',null)
       .validateField($('.js-datetimepicker'));
});

$(".changeemployee").on("click",function () {
    var id = $(this).attr("id");
    var id2 = $(this).next().attr("id");

    // var pkStudent = $("#pkStudent").val();
    $.layer({
        type : 2,
        title : ['选择员工',true],
        skin: 'modal in',
        iframe : {src : '/student/studentSignup/getEmployees?empid='+id2 +'&capid='+id},
        area : ['90%' , '90%'],
//            offset : ['20px','']

    });

});


function getEmp(pkEmployee,caption,pkid,capid) {

    $("#"+capid).val("");
    $("#"+capid).val(caption);
    $("#"+pkid).val(pkEmployee);

}


$('.form-loginajax').bootstrapValidator().on('success.form.bv', function(e) {
    // Prevent form submission
    e.preventDefault();

    // Get the form instance
    var $form = $(e.target);

    // Get the BootstrapValidator instance
    var bv = $form.data('bootstrapValidator');

    // Use Ajax to submit form data

    $.post($form.attr('action'), $form.serialize(), function(data) {

        if(data.code==0){


            if(data.data.map.sysEmployee!=null){
                //alert(data.data.map.sysEmployee.pkEmployee);
                setCookie("pk",data.data.map.sysEmployee.pkEmployee);
            }else{
                delCookie("pk");
            }
            // Notify.success(data.message);
            if($form.data('target')){
                setTimeout("window.location.href='"+$form.data('target')+"'",3000);
            }
        }else{
            Notify.danger(data.message);
            window.location.reload();
        }
    }, 'json');
});

function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
$(".changeemployee1").on("click",function () {
    var id = $(this).attr("id");
    var id2 = $(this).next().attr("id");
    var url=$(this).attr("data-url");
    $(this).attr("data-url",url+"&id1="+id+"&id2="+id2);
    return true;
    // var pkStudent = $("#pkStudent").val();

});

$(".getEmployeeList").on("click",function () {
    var url=$(this).attr("data-url");
    $(this).attr("data-url",url);
    return true;
});

function selectEmp(pkId,caption,id1,id2) {

    $("#modal").modal("hide");
    $("#"+id1).val(caption);
    $("#"+id2).val(pkId);
}


var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
    21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",
    34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",
    43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川"
    ,52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",
    64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
    }
function isCardID(sId){
    var iSum=0 ;
    var info="" ;
    if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";
    sId=sId.replace(/x$/i,"a");
    if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
    var d=new Date(sBirthday.replace(/-/g,"/")) ;
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
    if(iSum%11!=1) return "你输入的身份证号非法";
    return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")
}

/**
 * 获取指定的URL参数值
 * URL:http://www.quwan.com/index?name=tyler
 * 参数：paramName URL参数
 * 调用方法:getParam("name")
 * 返回值:tyler
 */
function getParam(paramName) {
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
}