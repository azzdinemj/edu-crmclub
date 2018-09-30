<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Student
            </h4>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
                <h5>Please choose students</h5>
                <form>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Student number</label>
                            <div class="col-xs-8">
                                <input disabled="disabled" name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Student name</label>
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
                        <th width="150">Student number</th>
                        <th width="100">Student name</th>
                        <th width="100">Sex</th>
                        <th width="100">grade</th>

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
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" onclick="sub()" class="btn btn-success btn-sm">Determine</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">

    function sub() {
        var pkStudent = $("input[name='radio1']:checked").val();

        if(pkStudent == null){
            alert("Please choose students");
            return;
        }

        var data = { "pkStudents": pkStudent,"pkClassinfo":${(classinfo.pkClassinfo)!}};

        window.location.href="/classinfo/classinfo/stureport?pkStudents="+pkStudent+"&pkClassinfo=${(classinfo.pkClassinfo)!}";

//        $.ajax({
//            url:"/classinfo/classinfo/stureport",
//            data:data,
//            type:"GET",
//            traditional: true,//Transfer array
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

