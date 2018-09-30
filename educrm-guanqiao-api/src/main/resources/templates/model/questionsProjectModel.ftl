<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                菜单列表
            </h4>
        </div>
        <div class="modal-body">
            <div class="">

                <table id="pagingTable" class="table table-color" cellspacing="0">
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

    // var columns = [
    //     {"sTitle":"选择","data" : "id",
    //         "render": function (data, type, row){
    //             return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.name+'" class="px">';
    //         }} ,
    //     {"sTitle":"主键","data":"id"},
    //     {"sTitle":"父类主键","data":"pid"},
    //     {"sTitle":"名称","data" : "name"}
    // ];


    $(document).ready(function() {
        query();
    } );

    function renderPk(data, type, row){
        return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.name+'" class="px">';
    }

    function query() {
        var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"0"};
       // init(columns,"/question/questionProject/queryByPaging",data,"pagingTable");
        init(GetTableColumn("questionsProjectModel"), "/question/questionProject/queryByPaging", data,"pagingTable");
    }


    function selectStudent() {
        var pkId = $("input[name='radio1']:checked").val();
        var caption = $("input[name='radio1']:checked").attr("caption");
        if(pkId == null){
            alert("请选择");
            return;
        }
        parent.selectStu(pkId,caption);
    }


</script>

