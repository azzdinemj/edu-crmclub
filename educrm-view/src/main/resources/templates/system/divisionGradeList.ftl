
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>学部年级<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal" data-url="/system/divisionGrade/create">新建</a>

                        <a class="btn btn-sm btn-newblue" onclick="del()">删除</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                            <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                        </div><!-- panel-btns -->
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">日期 :</label>-->
                                <#--<input type="text" title="" id="timeid"  name="datetime"  class="form-control form-input-lg js-datepicker"  />-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">宿舍名称 :</label>-->
                                <#--<input type="text" title="" id="nameid"  name="name"  class="form-control form-input-lg"  />-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>-->
                            <#--</div>-->

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

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>


<script type="text/javascript">

    $(document).ready(function() {
        query();
    } );

    function renderCaption(data, type, row) {

        return '<a data-toggle="modal" data-target="#modal" data-url="/system/divisionGrade/edit?pkDivisionGrade=' + row.pkDivisionGrade + '" class="yellow" >' + data + '</a>';
    }



    function query() {
        var data  = { "caption":$("#nameid").val()};
        init(GetTableColumn("divisionGrade"),"/system/divisionGrade/queryByPaging",data,"pagingTable");
    }


    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkDivisionGrade;

        if(id ==null){
            alert("请先选择要删除的数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/divisionGrade/delete",
                data: {"pkDivisionGrade": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        alert(data.message);
                        window.location.reload();
                    }
                },
                error: function () {

                }
            });
        }


    }




</script>

