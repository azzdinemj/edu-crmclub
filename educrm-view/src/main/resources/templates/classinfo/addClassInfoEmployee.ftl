
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                班级老师添加
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal look" method="post" action="/classinfo/classinfoEmployee/save">
                <div class="form-group">
                    <label class="control-label col-xs-3 col-md-3">入班老师</label>
                    <div class="col-xs-9 col-md-9">
                        <input readonly id="directorcap2" value="${(director.caption)!}" class="form-control changeemployee1"  data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees">
                        <input readonly name="pkEmployee" id="directorpk2" value="${(director.pkEmployee)!}" type="hidden">
                    </div>
                </div>
                <#--<input readonly name="clas.headTeacher" id="headTeacherpk" value="${(headTeacher.pkEmployee)!}" type="hidden">-->
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">创建人</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classinfoEmployee.creator)!}" type="text"/>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">创建时间</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classinfoEmployee.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">修改人</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classinfoEmployee.modifier)!}" type="text"/>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">修改时间</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classinfoEmployee.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="pkClassinfo" value="${(pkClassinfo)!}">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-newblue btn-sm">确定</button>
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
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
                window.location.reload();
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });

//    $(".changeemployee").on("click",function () {
//        var id = $(this).attr("id");
//        var id2 = $(this).next().attr("id");
//
//        // var pkStudent = $("#pkStudent").val();
//        $.layer({
//            type : 2,
//            title : ['选择员工',true],
//            skin: 'modal in',
//            iframe : {src : '/student/studentSignup/getEmployees?empid='+id2 +'&capid='+id},
//            area : ['90%' , '90%']
////            offset : ['20px','']
//
//        });
//
//    });

    $(".changeemployee1").on("click",function () {
        var id = $(this).attr("id");
        var id2 = $(this).next().attr("id");
        var url=$(this).attr("data-url");
        $(this).attr("data-url",url+"&id1="+id+"&id2="+id2);
        return true;
        // var pkStudent = $("#pkStudent").val();

    });
</script>