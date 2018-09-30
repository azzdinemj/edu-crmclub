<div id="studentModel" class="modal-dialog lg-modal">
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
            <div class="">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Student number</label>
                            <div class="col-xs-8">
                                <input id="code" name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Student name</label>
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
                <table id="pagingTable" class="table table-color" cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" onclick="selectStudent();" class="btn btn-newblue btn-sm">Determine</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">
        // var columns = [
        //     {"sTitle":"Choice","data" : "pkStudent",
        //         "render": function (data, type, row){
        //             return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
        //         }} ,
        //     {"sTitle":"number","data":"code"},
        //     {"sTitle":"name","data" : "caption"} ,
        //     {"sTitle":"Sex","data" : "sex","render": function (data){
        //         if (data==1){ return '男';}
        //         else {return '女'; }
        //     }}
        // ];

    $(document).ready(function() {
        query();
    });

    function renderPk(data, type, row){
        return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
    }

    function query() {
        var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"1"};
        //init(columns,"/student/studentSignup/queryByPaging",data,"pagingTable");
        init(GetTableColumn("studentlistModel"), "/student/studentSignup/queryByPaging", data,"pagingTable");
    }


    function selectStudent() {
        var pkId = $("input[name='radio1']:checked").val();
        var caption = $("input[name='radio1']:checked").attr("caption");
        if(pkId == null){
            Notify.danger("Please choose students");
            return false;
        }
        var pkid1 = "${(capid)!}";
        var pkid2 = "${(pkid)!}";
        parent.selectEmp(pkId,caption,pkid1,pkid2);
    }


</script>

