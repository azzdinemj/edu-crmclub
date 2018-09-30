<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                into class
            </h4>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
                <h5>Please choose the class</h5>
                <form>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Class number</label>
                            <div class="col-xs-8">
                                <input disabled="disabled" name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Class name</label>
                            <div class="col-xs-8"><input disabled="disabled" name="name" class="form-control"
                                                         type="text"></div>
                        </div>
                    </div>
                </form>
                <input type="hidden" id="pkStudent" value="${(clastu)!}">
                <input type="hidden" id="pkStudentSignup" value="${(pkStudentSignup)!}">
                <table id="pagingTable2" class="table table-color" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>-->
                            <#--<input type="radio" id="chAll">-->
                        <#--</th>-->
                        <#--<th width="150">Class number</th>-->
                        <#--<th width="100">Class name</th>-->
                        <#--<th width="100">Headmaster</th>-->
                        <#--<th width="100">project</th>-->
                        <#--<th width="200">grade</th>-->
                        <#--<th width="200">Classroom</th>-->
                        <#--<th width="200">Academic Director</th>-->

                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#list classinfo as clas>-->
                    <#--<tr>-->
                        <#--<td>-->
                            <#--<input name="radio1" type="radio" value="${(clas.pkClassinfo)!}" class="px">-->
                        <#--</td>-->
                        <#--<td>${(clas.code)!}</td>-->
                        <#--<td>${(clas.caption)!}</td>-->
                        <#--<td>${(clas.headTeacher)!}</td>-->
                        <#--<td>${(clas.program)!}</td>-->
                        <#--<td>${(clas.grade)!}</td>-->
                        <#--<td>${(clas.classRoom)!}</td>-->
                        <#--<td>${(clas.director)!}</td>-->
                    <#--</tr>-->
                    <#--</#list>-->


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
    $(document).ready(function() {
        querys();
    });

    function querys() {
        var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"1"};
        //init(columns,"/classinfo/classinfo/queryByPaging",data,"pagingTable2");
        init(GetTableColumn("classinfolistModel"), "/classinfo/classinfo/queryByPaging", data,"pagingTable2");
    }

    function sub() {
        var pkClassinfo = $("input[name='radio1']:checked").val();

        if(pkClassinfo == null){
            alert("Please choose the class");
            return;
        }

        var classtu = $("#pkStudent").val();
        var pkStudentSignup = $("#pkStudentSignup").val();
        var data = {"clas.pkClassinfo": pkClassinfo, "clas.pkStudent": classtu,"clas.pkStudentSignup":pkStudentSignup};

        $.ajax({
            url:"/classinfo/classinfoStudent/save",
            data:data,
            type:"POST",
            traditional: true,//Transfer array
            sync:false,
            dataType:"json",
            success:function (data) {
                if(data.code ==0){
                    location.reload();
                }else {
                    Notify.danger(data.message);
                }
            }
            /*error : function(data) {
                alert("Save failure");
                Notify.danger(data.message);
            }*/
        });

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

