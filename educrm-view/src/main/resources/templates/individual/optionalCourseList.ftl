<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>个性化管理 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/individual/optionalCourse/save" onclick="save()">保存</a>
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
    });

    function renderCaption(data, type, row){
        return '<input name="checkbox" type="checkbox" value="'+data+'" pkSchedul="'+row.pkSchedul+'" class="px">';
    }

//    function renderIsvalid (data) {
//        if (data==1){ return '已激活';}
//        else if(data==0){return '未激活'; }
//    }

    function query() {
        var data  = {"caption":$("#caption").val()};
//        init(GetTableColumn("optionalCourse"), "/individual/optionalCourse/queryByPaging", data,"pagingTable");
        init(GetTableColumn("optionalCourse"), "/individual/optionalCourse/queryByPaging", data,"pagingTable");
    }

    $(".table-optional").on('click', 'tr', function (e) {

        var s = $(this).find("input[type='checkbox']").attr("checked");
        var index = $(this).index();
        if (s==undefined){
            var id = $(this).find("input[type='checkbox']").val();
            $.ajax({
                type: "GET",
                url: "/individual/optionalCourse/verification",
                data: {"pkSchedul": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
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
        for(k in obj){
            if(obj[k].checked)
                result.push(obj[k].value);
        }
        pkScheduls = JSON.stringify(result);
        if (result.length>0){
            $.ajax({
                type: "POST",
                url: "/individual/optionalCourse/save",
                data: {"pkScheduls": pkScheduls},
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
