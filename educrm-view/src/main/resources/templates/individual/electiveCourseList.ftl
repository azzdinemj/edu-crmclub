<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>选课管理 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" onclick="save()">提交</a>-->
                <a class="btn btn-sm btn-newblue addSchedule" >班级排课</a>
                <a class="btn btn-sm btn-newblue" href="/classinfo/electiveCourse/create" >新建选修课班级</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <!--高显搜索-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">班级名称 :</label>
                        <input type="text" id="caption" name="caption" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>搜索</span>
                    </div>

                </form>

            </div>
        </div>
        <div class="panel-body pd0">
            <div class="table-responsive">
                <#--<table id="pagingTable"  class="table table-bordered table-optional" cellspacing="0">-->
                <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>

<script type="text/javascript">
    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {"type":"3","dateTime": $("#datas").val(),"caption":$("#caption").val()};
        init(GetTableColumn("individualClassinfo"),"/classinfo/electiveCourse/queryByPaging",data,"pagingTable");
    }

    function renderCaption(data, type, row){
//        return '<input name="checkbox" type="checkbox" value="'+data+'" pkClassinfo="'+row.pkClassinfo+'" class="px">';
        return '<a href="/classinfo/electiveCourse/edit?pkClassinfo='+row.pkClassinfo+'" class="yellow" >'+data+'</a>';
    }

    function renderIndividualStatus(data) {
        if (data == 0){
            return '下架';
        }else {
            return '上架';
        }
    }



//    $(".table-optional").on('click', 'tr', function (e) {
//
//        var s = $(this).find("input[type='checkbox']").attr("checked");
//        var index = $(this).index();
//
//        if (s==undefined){
//            var id = getTableSelectRow("pagingTable",index).map.scheduleEntity.pkSchedule;
//            $.ajax({
//                type: "GET",
//                url: "/individual/individual/verification",
//                data: {"pkSchedule": id},
//                dataType: "json",
//                success: function (data) {
//                    if (data.code == 0) {
//                        $(".table-optional").find("tbody").find("tr").eq(index).find('td:eq(0) input').prop('checked', true);
//                        $(".table-optional").find("tbody").find("tr").eq(index).addClass('table-color-');
//                        if ($(".table-optional").find("tbody").find("tr").eq(index).hasClass("odd")) {
//                            $(".table-optional").find("tbody").find("tr").eq(index).find("td").css("background-color", "#00539c");
//                        }
//                    } else {
//                        Notify.danger(data.message);
//                    }
//                },
//                error: function () {
//
//                }
//            });
//        }
//        else{
//            $(this).find('td:eq(0) input').prop('checked', false);
//            $(this).removeClass('table-color-');
//            if ($(this).hasClass("odd")) {
//                $(this).find("td").css("background-color", "#f5f5f5");
//            }
//        }
//
//
//    });



    function save() {
        obj = document.getElementsByName("checkbox");
        result = [];
        linkManList = new Array();
//        var object = {"pkSchedule","pkEmployee","pkClassRoom","startTime","endTime"};

        for(k in obj){
            if(obj[k].checked)
                result.push(obj[k].value);
        }
        object = new Array();
        $("tr.table-color-").each(function (i) {
            var s =$("tr.table-color-").eq(i).index();

            pkSchedule = getTableSelectRow("pagingTable",s).map.scheduleEntity.pkSchedule;
            pkEmployee =  getTableSelectRow("pagingTable",s).map.scheduleEntity.pkEmployee;
            pkClassRoom =  getTableSelectRow("pagingTable",s).map.scheduleEntity.pkClassRoom;
            startTimes = getTableSelectRow("pagingTable",s).map.scheduleEntity.startTime;
            endTimes = getTableSelectRow("pagingTable",s).map.scheduleEntity.endTime;

            var str = {"pkSchedule":pkSchedule,"pkEmployee":pkEmployee,"pkClassRoom":pkClassRoom,"startTime":startTimes,"endTime":endTimes};

            object.push(str);
        });

        pkScheduls = JSON.stringify(object);
        if (result.length>0){
            $.ajax({
                type: "POST",
                url: "/individual/individual/save",
                data: {"pkScheduls":pkScheduls},
                traditional: true,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        window.location.reload();
                    } else {
                        Notify.danger(data.message);
                        window.location.reload();
                    }
                },
                error: function () {

                }
            });

        }else {
            return false;
        }
    }


    $(".addSchedule").on("click",function(){
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassinfo;
        var className = getTableSelectRow("pagingTable",index).caption;
        if (id == null) {
            Notify.danger("请先选择班级");
            return false;
        }
//        var status = getTableSelectRow("pagingTable",index).map.classinfoScheduleStatus.code;
//        if (status == "1") {
//            Notify.danger("班级已存在课表，不可重复添加");
//            return false;
//        }
        window.location.href="/classinfo/classinfoschedule/addSchedule?pkClassinfo="+id+"&caption="+className;
        return true;
    });


</script>
