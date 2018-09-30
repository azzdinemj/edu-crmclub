

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
						window.location.reload();
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

$('.js-datetimepicker').datetimepicker({//年月日
    format: 'yyyy-mm-dd hh:ii',
    autoclose: 1,
    language:  'zh-CN',
   }).on('hide',function(e) {  
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
