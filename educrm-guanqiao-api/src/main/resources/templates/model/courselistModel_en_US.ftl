<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                curriculum schedule
            </h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2">Teacher name</label>
                            <div class="col-xs-10">
                                <select id="selectjobid" class="selectpicker show-tick form-control" data-live-search="true">
                                    <option value="">Please choose</option>
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
                            <label class="control-label col-xs-4">Course name</label>
                            <div class="col-xs-8">
                                <input  name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <#--<div class="col-sm-4">-->
                        <#--<div class="form-group">-->
                            <#--<label class="control-label col-xs-4">Employee name</label>-->
                            <#--<div class="col-xs-8"><input id="caption" name="name" class="form-control"-->
                                                         <#--type="text"></div>-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="col-sm-4">
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="querys()">search</a>
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
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" onclick="saveBtn();" class="btn btn-newblue btn-sm">Determine</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">


        var columns2 = [
            {"sTitle":"Choice","data" : "pkSysDictValues",
                "render": function (data, type, row){
                    return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
                }} ,
            {"sTitle":"Name","data":"caption"},
            {"sTitle":"Identification value","data":"value"},
            {"sTitle":"sort","data" : "sort"} ,
            {"sTitle":"Remarks","data" : "memo"}
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
            Notify.danger("Please choose the course");
            return;
        }
        if(!pkEmployee){
            Notify.danger("Please choose the teacher");
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

