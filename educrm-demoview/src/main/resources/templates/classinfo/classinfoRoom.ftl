<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                班级列表
            </h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3">教室名称</label>
                            <div class="col-xs-9">
                                <select id="selectjobid" class="selectpicker show-tick form-control" data-live-search="true">
                                    <option value="">请选择</option>
                                <#if classRoom??>
                                    <#list classRoom as j>
                                        <option value="${(j.pkClassRoom)!}">${(j.caption)!}</option>
                                    </#list>
                                </#if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3">班级名称</label>
                            <div class="col-xs-9">
                                <input id="code" name="name" class="form-control" type="text">
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
<#--<script src="../../public/plus/js/jquery-1.11.1.min.js"></script>-->


<#--<script src="./plus/Bootstrap-select/js/bootstrap-select.js"></script>-->
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

    var columns2 = [
        {"sTitle":"选择","data" : "pkClassinfo",
            "render": function (data, type, row){
                return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
            }} ,
        {"sTitle":"名称","data":"caption"},
//        {"sTitle":"标识值","data":"value"},
//        {"sTitle":"排序","data" : "sort"} ,
        {"sTitle":"备注","data" : "notes"}
    ];
    //    var data  = [];
    //    init(columns,"/classinfo/classinfo/queryByPaging",data)

    $(document).ready(function() {
        querys();

        $('#selectjobid').selectpicker({

        });
    } );

    function querys() {
        var data2  = { "code": $("#code").val(),"caption":$("#caption").val(),"isvalid":"1","jobPost":"1"};

        init(columns2,"/classinfo/classinfo/queryByPaging",data2,"pagingTable2");
    }


    function saveBtn() {
        var pkClassinfo = $("input[name='radio1']:checked").val();
        var pkClassRoom= $("#selectjobid").val();
        if(pkClassinfo == ""){
            alert("请选择班级");
            return false;
        }
        if(pkClassRoom == ""){
            alert("请选择老师");
            return false;
        }
        $.ajax({
            type: "POST",
            url: "/classinfo/classinfoRoom/save",
            data: {"pkClassroom": pkClassRoom,"pkClassinfo":pkClassinfo},
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    setTimeout("location.reload()", 1);
                } else {
                    alert(data.message)
                }
            },
            error: function () {

            }
        });
    }


</script>

<#--<script src="../../public/plus/Bootstrap-select/js/bootstrap-select.js"></script>-->

