<#include "../commons/top.ftl" >
<#include "../commons/left.ftl" >


        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>新生面试 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

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
                            <div class="form-group demo">
                                <label class="control-label">面试日期 :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" title="" readonly name="reservation" id="datas" class="form-control form-input-time" value="" />

                            </div>
                            <div class="form-group">
                                <label class="control-label">姓名 :</label>
                                <input type="text" title="" id="name" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <button onclick="query();" type="button" class="btn btn-newblue btn-sm search">搜索</button>
                            </div>
                        </form>
                        <!--隐藏搜索-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <#--<form class="form-inline search white">-->
                                    <#--<div class="form-group">-->
                                        <#--<label>学部</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>全部</option>-->
                                                <#--<option>一部</option>-->
                                                <#--<option>二部</option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">家长姓名 :</label>-->
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">入学年级 :</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>全部</option>-->
                                                <#--<option>一年级</option>-->
                                                <#--<option>二年级</option>-->
                                                <#--<option>三年级</option>-->
                                                <#--<option>四年级</option>-->
                                                <#--<option></option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                <#--</form>-->
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


    function renderCaption(data, type, row) {
        return '<a href="/student/studentInterview/edit?pkStudentInterview='+row.pkStudentInterview+'" class="yellow" >'+data+'</a>';
    }

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dateTime": $("#datas").val(),"name":$("#name").val()};
        init(GetTableColumn("studentInterview"),"/student/studentInterview/queryByPaging",data,"pagingTable");
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