<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Individualized timetable <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onclick="save()">Submission</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <!--High search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">Date, :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Student name :</label>
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
        init(GetTableColumn("individualClassinfo"),"/individual/individual/queryByPaging",data,"pagingTable");
    }

    function renderCaption(data, type, row){
        return '<input name="checkbox" type="checkbox" value="'+data+'" pkClassinfo="'+row.pkClassinfo+'" class="px">';
    }



    $(".table-optional").on('click', 'tr', function (e) {

        var s = $(this).find("input[type='checkbox']").attr("checked");
        var index = $(this).index();

        if (s==undefined){
            var id = getTableSelectRow("pagingTable",index).map.scheduleEntity.pkSchedule;
            $.ajax({
                type: "GET",
                url: "/individual/individual/verification",
                data: {"pkSchedule": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $(".table-optional").find("tbody").find("tr").eq(index).find('td:eq(0) input').prop('checked', true);
                        $(".table-optional").find("tbody").find("tr").eq(index).addClass('table-color-');
                        if ($(".table-optional").find("tbody").find("tr").eq(index).hasClass("odd")) {
                            $(".table-optional").find("tbody").find("tr").eq(index).find("td").css("background-color", "#00539c");
                        }
                    } else {
                        Notify.danger(data.message);
                    }
                },
                error: function () {

                }
            });
        }
        else{
            $(this).find('td:eq(0) input').prop('checked', false);
            $(this).removeClass('table-color-');
            if ($(this).hasClass("odd")) {
                $(this).find("td").css("background-color", "#f5f5f5");
            }
        }


    });



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
////            startTime222 = getTableSelectRow("pagingTable",s).map.scheduleEntity.startTime;
            endTimes = getTableSelectRow("pagingTable",s).map.scheduleEntity.endTime;


//            object.push("pkSchedule",pkSchedule);
//            object.push("pkEmployee",pkEmployee);
//            object.push("pkClassRoom",pkClassRoom);
//            object.push("startTimes",startTimes);
//            object.push("endTimes",endTimes);
            var str = {"pkSchedule":pkSchedule,"pkEmployee":pkEmployee,"pkClassRoom":pkClassRoom,"startTime":startTimes,"endTime":endTimes};

            object.push(str);
        });

//        alert(pkSchedul);
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
