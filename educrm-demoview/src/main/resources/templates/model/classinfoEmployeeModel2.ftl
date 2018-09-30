<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                员工列表
            </h4>
        </div>
        <div class="modal-body">
            <div class="">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">员工编号</label>
                            <div class="col-xs-8">
                                <input id="code" name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">员工姓名</label>
                            <div class="col-xs-8"><input id="caption" name="name" class="form-control"
                                                         type="text"></div>
                        </div>
                    </div>
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
            <input type="hidden" id="pkClassinfo" value="${(pkClassinfo)!}">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <button type="button" onclick="selectClassinfoEmployee();" class="btn btn-newblue btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">
//    $(document).ready(function() {
//        var columns = [
//            {"sTitle":"选择","data" : "pkStudent",
//                "render": function (data, type, row){
//                    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
//                }} ,
//            {"sTitle":"编号","data":"code"},
//            {"sTitle":"姓名","data" : "caption"} ,
//            {"sTitle":"性别","data" : "sex"}
//        ];
//        var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"3"};
//        init(columns,"/student/studentSignup/queryByPaging",data);
//    } );

//        var columns = [
//            {"sTitle":"选择","data" : "pkEmployee",
//                "render": function (data, type, row){
//                    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
//                }} ,
//            {"sTitle":"编号","data":"code"},
//            {"sTitle":"姓名","data" : "caption"} ,
////            {"sTitle":"性别","data" : "sex"},
//            {"sTitle":"部门","data" : "map.departmentEntity.caption"}
//        ];

    $(document).ready(function() {
        querys();
    } );

    function renderPk (data, type, row){
        return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
    }

    function querys() {
        var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"1"};
        //init(columns,"/system/employee/queryByPaging",data,"pagingTable2");
        init(GetTableColumn("classinfoEmployeeModel2"), "/system/employee/queryByPaging", data,"pagingTable2");
    }


    function selectClassinfoEmployee() {
        var employeeList = $("input[name='radio1']:checked").val();
        var classinfo = $("#pkClassinfo").val();

        $.ajax({
            url:"/classinfo/classinfoEmployee/save",
            data:{"pkEmployee":employeeList,"pkClassinfo":classinfo},
            type:"POST",
            dataType:"json",
            success:function (data) {
                if(data.code ==0){
                    Notify.success(data.message);
                    location.reload();
                }else {
                    Notify.danger(data.message);
                    location.reload();
                }
            }
        });
    }


</script>

