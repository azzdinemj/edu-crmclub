<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                学生列表
            </h4>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
                <h5>请选择学生</h5>
                <form>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">学生编号</label>
                            <div class="col-xs-8">
                                <input disabled="disabled" name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">学生姓名</label>
                            <div class="col-xs-8"><input disabled="disabled" name="name" class="form-control"
                                                         type="text"></div>
                        </div>
                    </div>
                </form>
                <table class="table table-color" cellspacing="0">
                    <thead>
                    <tr>
                        <th>
                            <input type="radio" id="chAll">
                        </th>
                        <th width="150">学生编号</th>
                        <th width="100">学生姓名</th>
                        <th width="100">性别</th>
                        <th width="100">年级</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#list students as s>
                    <tr>
                        <td>
                            <input name="radio1" type="radio" value="${(s.pkStudent)!}" class="px">
                        </td>
                        <td>${(s.code)!}</td>
                        <td>${(s.caption)!}</td>
                        <td>${(s.sex)!}</td>
                        <td>${(s.greade)!}</td>
                    </tr>
                    </#list>


                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <button type="button" onclick="sub()" class="btn btn-success btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">

    function sub() {
        var pkStudent = $("input[name='radio1']:checked").val();

        if(pkStudent == null){
            alert("请选择学生");
            return;
        }

        var data = { "pkStudents": pkStudent,"pkClassinfo":${(classinfo.pkClassinfo)!}};

        window.location.href="/classinfo/classinfo/stureport?pkStudents="+pkStudent+"&pkClassinfo=${(classinfo.pkClassinfo)!}";

//        $.ajax({
//            url:"/classinfo/classinfo/stureport",
//            data:data,
//            type:"GET",
//            traditional: true,//传递数组
//            sync:false,
//            dataType:"json",
//            success:function (data) {
//                if(data.code ==0){
//                    location.reload();
//                }else {
//                    Notify.danger(data.message);
//                }
//            }
//
//        });

//        $.post("/classinfo/classinfoStudent/saveall", data, function (data) {
//
//            if (data.code == 0) {
//                // Notify.success(data.message);
//
//                setTimeout("location.reload()", 1);
//
//            } else {
//                Notify.danger(data.message);
//            }
//        }, 'json');

    }


</script>

