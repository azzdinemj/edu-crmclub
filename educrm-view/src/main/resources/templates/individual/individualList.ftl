<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>个性化课表 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onclick="save()">提交</a>
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
                        <label class="control-label">学生姓名 :</label>
                        <input type="text" title="" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>

                </form>

            </div>
        </div>
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable"  class="table table-bordered table-optional" cellspacing="0">
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
        init(GetTableColumn("individualSchedule"),"/individual/individual/queryByPaging",data,"pagingTable");
    }

    function renderCaption(data, type, row){
//        var  s= "false";
        if (row.map.isType==1){
            s = "checked";
        }else {
            s = "";
        }
        return '<input name="checkbox" type="checkbox" '+s+' value="'+data+'" pkSchedule="'+row.pkSchedule+'"  class="px">';
//        return '<a href="/individual/individual/edit?pkClassinfo='+row.pkClassinfo+'" class="yellow" >'+data+'</a>';
    }

    function renderIndividualStatus(data) {
        if (data == 0){
            return '下架';
        }else {
            return '上架';
        }
    }

    function checkFtl(startTime, entTime) {

    }

    $(".table-optional").on('click', 'tr', function (e) {
//        function checkYanzheng(obj) {

        var s = $(this).find("input[type='checkbox']").attr("checked");
        var index = $(this).index();
//            alert(obj);
//            return;
//        var s = $(obj).attr("checked");
//        var index = $(obj).index();

        if (s !=undefined){
            var id = getTableSelectRow("pagingTable",index).pkSchedule;
//            var id = $(obj).pkSchedule;
//            var id = "0001";
            $.ajax({
                type: "GET",
                url: "/individual/individual/verification",
                data: {"pkSchedule": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {

                        if (confirm("您确定要报名该课程吗？")){
                            var pkClassinfo = getTableSelectRow("pagingTable",index).map.classInfoEntity.pkClassinfo;
//                            var pkClassinfo = $(obj).map.classInfoEntity.pkClassinfo;
                            $.ajax({
                                type: "POST",
                                url: "/individual/individual/save",
                                data: {"pkClassinfo":pkClassinfo},
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
                            $(".table-optional").find("tbody").find("tr").eq(index).find('td:eq(0) input').prop('checked', false);
                        }

//                        $(".table-optional").find("tbody").find("tr").eq(index).find('td:eq(0) input').prop('checked', true);
//                        $(".table-optional").find("tbody").find("tr").eq(index).addClass('table-color-');
//                        if ($(".table-optional").find("tbody").find("tr").eq(index).hasClass("odd")) {
//                            $(".table-optional").find("tbody").find("tr").eq(index).find("td").css("background-color", "#00539c");
//                        }
                    } else {
                        Notify.danger(data.message);
                        $(".table-optional").find("tbody").find("tr").eq(index).find('td:eq(0) input').prop('checked', false);
//                        query();
                    }
                },
                error: function () {

                }
            });
        }
        else{
            if (confirm("您确定要取消该报名该课程吗")){
                var pkClassinfo = getTableSelectRow("pagingTable",index).map.classInfoEntity.pkClassinfo;
                $.ajax({
                    type: "POST",
                    url: "/individual/individual/delete",
                    data: {"pkClassinfo":pkClassinfo},
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
                $(".table-optional").find("tbody").find("tr").eq(index).find('td:eq(0) input').prop('checked', true);
            }
//            $(this).find('td:eq(0) input').prop('checked', false);
//            $(this).removeClass('table-color-');
//            if ($(this).hasClass("odd")) {
//                $(this).find("td").css("background-color", "#f5f5f5");
//            }
        }


    });



    function save() {
        obj = document.getElementsByName("checkbox");
        result = [];
        linkManList = new Array();
//        var object = {"pkSchedule","pkEmployee","pkClassRoom","startTime","endTime"};
        var arr = $("input[name='checkbox']:checked");
        object = new Array();
        $.each(obj,function (i) {
           if (obj[i].checked){
               pkClassinfo = getTableSelectRow("pagingTable",i).map.classInfoEntity.pkClassinfo;
//               pkEmployee =  getTableSelectRow("pagingTable",i).pkEmployee;
//               pkClassRoom =  getTableSelectRow("pagingTable",i).pkClassRoom;
//               startTimes = getTableSelectRow("pagingTable",i).startTime;
//               endTimes = getTableSelectRow("pagingTable",i).endTime;
//               var str = {"pkSchedule":pkSchedule,"pkEmployee":pkEmployee,"pkClassRoom":pkClassRoom,"startTime":startTimes,"endTime":endTimes};

               object.push(pkClassinfo);
           }
//            alert(obj.eq(i).index());
        });

        for(k in obj){
            if(obj[k].checked)
                result.push(obj[k].value);
        }
//        object = new Array();
//        $("tr.table-color-").each(function (i) {
//            var s =$("tr.table-color-").eq(i).index();
//
//            pkSchedule = getTableSelectRow("pagingTable",s).map.scheduleEntity.pkSchedule;
//            pkEmployee =  getTableSelectRow("pagingTable",s).map.scheduleEntity.pkEmployee;
//            pkClassRoom =  getTableSelectRow("pagingTable",s).map.scheduleEntity.pkClassRoom;
//            startTimes = getTableSelectRow("pagingTable",s).map.scheduleEntity.startTime;
//            endTimes = getTableSelectRow("pagingTable",s).map.scheduleEntity.endTime;
//
//            var str = {"pkSchedule":pkSchedule,"pkEmployee":pkEmployee,"pkClassRoom":pkClassRoom,"startTime":startTimes,"endTime":endTimes};
//
//            object.push(str);
//        });

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


</script>
