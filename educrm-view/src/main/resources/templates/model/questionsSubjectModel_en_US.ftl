<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Menu list
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
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" onclick="selectStudent();" class="btn btn-newblue btn-sm">Determine</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">

    // var columns = [
    //     {"sTitle":"Choice","data" : "id",
    //         "render": function (data, type, row){
    //             return '<input name="radio1" type="checkbox" value="'+data+'" caption="'+row.name+'" class="px">';
    //         }} ,
    //     {"sTitle":"Primary","data":"id"},
    //     {"sTitle":"Name","data" : "name"}
    // ];

    $(document).ready(function() {
        query();
    } );

    function renderPk(data, type, row){
        return '<input name="radio1" type="checkbox" value="'+data+'" caption="'+row.name+'" class="px">';
    }

    function query() {
        var data  = { "code": $("#code").val(),"caption":$("#caption").val(),"istype":"0"};
        //init(columns,"/question/questionSubject/queryByPaging",data,"pagingTable");
        init(GetTableColumn("questionsSubjectModel"), "/question/questionSubject/queryByPaging", data,"pagingTable");
    }

    function selectStudent() {
        var pkId = $("input[name='radio1']:checked").val();

        obj = document.getElementsByName("radio1");
        check_val = [];
        check_caption = [];
        for(k in obj){
            if(obj[k].checked){
                check_val.push(obj[k].value);
                check_caption.push(obj[k].getAttribute('caption'));
            }
        }

        var caption = $("input[name='radio1']:checked").attr("caption");

        if(pkId == null){
            alert("Please choose");
            return;
        }
        //parent.selectStu(pkId,caption);
        parent.selectStu(check_val,check_caption);
    }


</script>

