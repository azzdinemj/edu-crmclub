<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Class list
            </h4>
        </div>
        <div class="modal-body">
            <div class="">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Class number</label>
                            <div class="col-xs-8">
                                <input id="code" name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Class name</label>
                            <div class="col-xs-8"><input id="caption" name="name" class="form-control"
                                                         type="text"></div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
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
            <input type="hidden" id="pkStudent" value="${(pkStudent)!}">
            <input type="hidden" id="pkParentClassinfo" value="${(oldPkClassinfo)!}">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" onclick="selectClassinfo();" class="btn btn-newblue btn-sm">Determine</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">
//    $(document).ready(function() {
//        var columns = [
//            {"sTitle":"Choice","data" : "pkStudent",
//                "render": function (data, type, row){
//                    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
//                }} ,
//            {"sTitle":"number","data":"code"},
//            {"sTitle":"name","data" : "caption"} ,
//            {"sTitle":"Sex","data" : "sex"}
//        ];
//        var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"3"};
//        init(columns,"/student/studentSignup/queryByPaging",data);
//    } );

//        var columns = [
//            {"sTitle":"Choice","data" : "pkClassinfo",
//                "render": function (data, type, row){
//                    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
//                }} ,
//            {"sTitle":"number","data":"code"},
//            {"sTitle":"Name","data" : "caption"} ,
//            {"sTitle":"grade","data" : "grade"},
//            {"sTitle":"project","data" : "program"}
//        ];


$(document).ready(function() {
    querys();
});


function querys() {
    var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"1"};
    //init(columns,"/classinfo/classinfo/queryByPaging",data,"pagingTable2");
    init(GetTableColumn("classinfolistModel"), "/classinfo/classinfo/queryByPaging", data,"pagingTable2");
}


    function selectClassinfo() {
        var pkClassinfo = $("input[name='radio1']:checked").val();
        var pkStudent = $("#pkStudent").val();
        var oldPkClassinfo = $("#pkParentClassinfo").val();
        if(pkClassinfo == null){
            Notify.danger("Please choose the class");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/student/studentShift/save",
            data: {"pkStudent": pkStudent,"pkClassinfo":pkClassinfo,"pkParentClassinfo":oldPkClassinfo},
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

