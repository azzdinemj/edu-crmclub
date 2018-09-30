<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                学员进班
            </h4>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
                <h5>请选择班级</h5>
                <form>
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="control-label col-xs-4">班级编号</label>
                            <div class="col-xs-8">
                                <input id="classCode" name="classCode" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label class="control-label col-xs-4">班级名称</label>
                            <div class="col-xs-8">
                                <input id="classCaption" name="classCaption" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="form-group" >
                        <span class="btn btn-newblue btn-sm search" onclick="querys()"><i class="fa fa-search"> </i>搜索</span>
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
                        <#--<th width="150">班级编号</th>-->
                        <#--<th width="100">班级名称</th>-->
                        <#--<th width="100">班主任</th>-->
                        <#--<th width="100">项目</th>-->
                        <#--<th width="200">年级</th>-->
                        <#--<th width="200">教室</th>-->
                        <#--<th width="200">学部主任</th>-->

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
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <button type="button" onclick="sub()" class="btn btn-success btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
    $(document).ready(function() {
        querys();
    });

    function querys() {
        var data  = { "code": $("#classCode").val(),"caption":$("#classCaption").val(),"istype":"1"};
        //init(columns,"/classinfo/classinfo/queryByPaging",data,"pagingTable2");
        init(GetTableColumn("classinfolistModel"), "/classinfo/classinfo/queryByPaging", data,"pagingTable2");
    }

    function sub() {
        var pkClassinfo = $("input[name='radio1']:checked").val();

        if(pkClassinfo == null){
            alert("请选择班级");
            return;
        }

        var classtu = $("#pkStudent").val();
        var pkStudentSignup = $("#pkStudentSignup").val();
        var data = {"clas.pkClassinfo": pkClassinfo, "clas.pkStudent": classtu,"clas.pkStudentSignup":pkStudentSignup};

        $.ajax({
            url:"/classinfo/classinfoStudent/save",
            data:data,
            type:"POST",
            traditional: true,//传递数组
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
                alert("保存失败");
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

