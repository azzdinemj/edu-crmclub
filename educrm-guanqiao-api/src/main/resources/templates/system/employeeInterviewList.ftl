<#include "../commons/top.ftl" >
<#include "../commons/left.ftl" >

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>员工面试 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <#--<div class="panel-btns">-->
                            <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                        <#--</div><!-- panel-btns &ndash;&gt;-->
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <div class="form-group demo">
                                <label class="control-label">面试日期 :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" title="" readonly name="reservation" id="datas" class="form-control form-input-time" value="" />

                            </div>
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">项目 :</label>-->
                                <#--<input type="text" title="" id="caption" name="reservation"  class="form-control form-input-lg" value="" />-->
                            <#--</div>-->
                            <div class="form-group">
                                <button type="button" onclick="query();" class="btn btn-newblue btn-sm search">搜索</button>
                            </div>
                        </form>
                        <!--隐藏搜索-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">

                            </div>

                        </div>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered lg-table" cellspacing="0">
                            <thead>

                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->


            </div><!-- panel -->

        </div><!-- contentpanel -->

<#include "../commons/footer.ftl" />
<script type="text/javascript">

//    var columns=[
//        {"sTitle":"姓名","data" : "map.employee.caption",
//            "render": function (data, type, row){
//                    return '<a href="/system/employeeInterview/edit?pkStudentInterview='+row.pkStudentInterview+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"面试hr","data" : "map.employeeAdmin.caption","render":function (data) {
//            if(data==null){
//                return 'null';
//            }else{
//                return data;
//            }
//        }},
//        {"sTitle":"面试时间","data" : "date","render": renderDate},
//        {"sTitle":"记录编号","data" : "code"},
////        {"sTitle":"面试项目","data" : "map.sysdicEntity.caption"},
//        {"sTitle":"面试项目","data" : "caption"},
//        {"sTitle":"面试情况","data" : "memo"},
////        {"sTitle":"备注","data" : "memo"},
//        {"sTitle":"是否审核","data" : "isaudit","render":function (data) {
//           if(data==2){
//               return '已审核';
//           }else {
//               return '未审核';
//           }
//        }},
//        {"sTitle":"审核人","data" : "map.auditorEntity.caption"},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ]

    $(document).ready(function() {
        query();
    } );

    function renderCaption (data, type, row){
        return '<a href="/system/employeeInterview/edit?pkStudentInterview='+row.pkStudentInterview+'" class="yellow" >'+data+'</a>';
    }

    function  renderHR(data) {
        if(data==null){
            return 'null';
        }else{
            return data;
        }
    }

    function renderIsaudit(data) {
        if(data==2){
            return '已审核';
        }else {
            return '未审核';
        }
    }

    function query() {
        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val()};
        //init(columns,"/system/employeeInterview/queryByPaging",data,"pagingTable");
        init(GetTableColumn("employeeInterview"), "/system/employeeInterview/queryByPaging", data,"pagingTable");
    }

    function del() {
        var id = $(".table-color-").attr("dataid");

        if (id == null) {
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/student/studentInterview/delete",
                data: {"pkStudentInterview": id},
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


    }

</script>