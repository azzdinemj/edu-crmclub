<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                List of receipts
            </h4>
        </div>
        <div class="modal-body">
            <div class="">
                <#--<form>-->
                    <#--<div class="col-sm-4">-->
                        <#--<div class="form-group">-->
                            <#--<label class="control-label col-xs-4">code</label>-->
                            <#--<div class="col-xs-8">-->
                                <#--<input id="code" name="name" class="form-control" type="text">-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="col-sm-4">-->
                        <#--<div class="form-group">-->
                            <#--<label class="control-label col-xs-4">Employee name</label>-->
                            <#--<div class="col-xs-8"><input id="caption" name="name" class="form-control"-->
                                                         <#--type="text"></div>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="col-sm-4">-->
                        <#--<div class="form-group">-->
                            <#--<a class="btn btn-newblue btn-sm search" onclick="querys()">search</a>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</form>-->
                <table id="pagingTable2" class="table table-color" cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div>
        <div class="modal-footer">
            <input type="hidden" id="pkReceivable" value="${(pkReceivable)!}">
            <input type="hidden" id="pkStudent" value="${(pkStudent)!}">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" onclick="savePayables();" class="btn btn-newblue btn-sm">Determine</button>
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

    // var columns = [
    //     {"sTitle":"Choice","data" : "pkReceipt",
    //         "render": function (data, type, row){
    //             return '<input class="check_item" type="checkbox" value="'+data+'" caption="'+row.code+'" class="px">';
    //         }} ,
    //     {"sTitle":"code","data":"code"},
    //     {"sTitle":"Cost","data" : "cost"} ,
    //     {"sTitle":"Date,","data" : "date","render":renderDate}
    // ];

    $(document).ready(function() {
        querys();
    } );

    function  renderPk(data, type, row){
        return '<input class="check_item" type="checkbox" value="'+data+'" caption="'+row.code+'" class="px">';
    }

    function querys() {
        var data  = {"pkParent":$("#pkReceivable").val(),"ispayment":0,"isaudit":1};
        //init(columns,"/finance/studentCharge/queryByPaging",data,"pagingTable2");
        init(GetTableColumn("receiptListModel"), "/finance/studentCharge/queryByPaging", data,"pagingTable2");
    }

    function savePayables() {
        var recriptList = "";
        $(".check_item").each(function () {
            var values = $(this).is(':checked');
            if (values) {
                recriptList = recriptList + $(this).attr("value") + ",";
            }
        });
        if(recriptList.length > 0) {
            recriptList = recriptList.substring(0, recriptList.length - 1)
        }

        $.ajax({
            url:"/finance/payables/receiptSavePayables",
            data:{"pkParent":$("#pkReceivable").val(),"pkExpenseItem":recriptList,"pkStudent":$("#pkStudent").val()},
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

