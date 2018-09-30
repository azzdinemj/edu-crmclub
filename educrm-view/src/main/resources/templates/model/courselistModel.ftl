<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                课程列表
            </h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2">老师名称</label>
                            <div class="col-xs-10">
                                <select id="selectjobid" class="selectpicker show-tick form-control" data-live-search="true">
                                    <option value="">请选择</option>
                                <#if employee??>
                                    <#list employee as j>
                                        <option value="${(j.pkEmployee)!}">${(j.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">课程名称</label>
                            <div class="col-xs-8">
                                <input  name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <#--<div class="col-sm-4">-->
                        <#--<div class="form-group">-->
                            <#--<label class="control-label col-xs-4">员工姓名</label>-->
                            <#--<div class="col-xs-8"><input id="caption" name="name" class="form-control"-->
                                                         <#--type="text"></div>-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="col-sm-4">
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="querys()">搜索</a>
                    </div>
                    </div>
                </form>
                <table id="pagingTable2" class="table table-color" cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <button type="button" onclick="saveBtn();" class="btn btn-newblue btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">


        var columns2 = [
            {"sTitle":"选择","data" : "pkSysDictValues",
                "render": function (data, type, row){
                    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
                }} ,
            {"sTitle":"名称","data":"caption"},
            {"sTitle":"标识值","data":"value"},
            {"sTitle":"排序","data" : "sort"} ,
            {"sTitle":"备注","data" : "memo"}
        ];


        $(document).ready(function() {
            querys();

            $('#selectjobid').selectpicker({

            });
        } );

        function  renderPk(data, type, row){
            return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
        }


        function querys() {
            var data2  = {"caption":$("input[name='name']").val(),"isvalid":"1","pkSysDict":"discipline"};
            //init(columns2,"/system/sysDictValues/queryByPaging",data2,"pagingTable2");
            init(GetTableColumn("courselistModel"), "/system/sysDictValues/queryByPaging", data2,"pagingTable2");
        }


    function saveBtn() {
        var pkSysDictValues = $("input[name='radio1']:checked").val();
        var pkEmployee= $("#selectjobid").val();

        if(pkSysDictValues == null){
            Notify.danger("请选择课程");
            return;
        }
        if(!pkEmployee){
            Notify.danger("请选择老师");
            return;
        }

       $.ajax({
           type: "POST",
           url: "/classinfo/courseTeacher/save",
           data: {"pkEmployee": pkEmployee,"pkSysDictValues":pkSysDictValues},
           dataType: "json",
           success: function (data) {
               if (data.code == 0) {
                   Notify.success(data.message);
                   setTimeout("location.reload()", 1);
               } else {
                   Notify.danger(data.message)
               }
           },
           error: function () {

           }
       });
    }


</script>

