<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                原料列表
            </h4>
        </div>
        <div class="modal-body">
            <div class="">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">原料名称</label>
                            <div class="col-xs-8">
                                <input id="code" name="materialName" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query2()">搜索</a>
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
            <button type="button" onclick="selectStudent();" class="btn btn-newblue btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">

//        var columns = [
//            {"sTitle":"选择","data" : "pkEmployee",
//                "render": function (data, type, row){
//                    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
//                }} ,
//            {"sTitle":"编号","data":"code"},
//            {"sTitle":"姓名","data" : "caption"} ,
//            {"sTitle":"性别","data" : "sex","render": function (data){
//                if (data==1){ return '男';}
//                else {return '女'; }
//            }},
//            {"sTitle":"部门","data" : "map.departmentEntity.caption"}
//        ];


$(document).ready(function() {
    query2();
} );

function  renderPk(data, type, row){
    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.materialName+'" class="px">';
}


function query2() {
    var data  = { "materialName":$("input[name='materialName']").val()};
    //init(columns,"/system/employee/queryByPaging",data,"pagingTable2");
    init(GetTableColumn("materiallistModel"), "/material/material/queryByPaging", data,"pagingTable2");
}


    function selectStudent() {
        var pkId = $("input[name='radio1']:checked").val();
        var caption = $("input[name='radio1']:checked").attr("caption");
        if(pkId == null){
            alert("请选择原料");
            return;
        }
        var pkid1 = "${(capid)!}";
        var pkid2 = "${(pkid)!}";
        parent.selectEmp(pkId,caption,pkid1,pkid2);
    }


</script>

