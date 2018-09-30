<#include "../commons/top.ftl" >
<#include "../commons/left.ftl" >


        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Freshman interview <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                            <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                        </div><!-- panel-btns -->
                        <!--High search-->
                        <form class="form-inline search white">
                            <div class="form-group demo">
                                <label class="control-label">date :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" title="" readonly name="reservation" id="datas" class="form-control form-input-time" value="" />

                            </div>
                            <div class="form-group">
                                <label class="control-label">project :</label>
                                <input type="text" title="" id="caption" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <button onclick="query();" type="button" class="btn btn-newblue btn-sm search">search</button>
                            </div>
                        </form>
                        <!--Hide search-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <#--<form class="form-inline search white">-->
                                    <#--<div class="form-group">-->
                                        <#--<label>Faculty</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>whole</option>-->
                                                <#--<option>One</option>-->
                                                <#--<option>Two</option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">Parent name :</label>-->
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">School year :</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>whole</option>-->
                                                <#--<option>first grade</option>-->
                                                <#--<option>second grade</option>-->
                                                <#--<option>Grade three</option>-->
                                                <#--<option>fourth grade</option>-->
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
        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val()};
        init(GetTableColumn("studentInterview"),"/student/studentInterview/queryByPaging",data,"pagingTable");
    }


    function del() {
        var id = $(".table-color-").attr("dataid");

        if (id == null) {
            Notify.danger("Please select the data to delete first");
            return;
        }

        var flag = confirm("Do you confirm the deletion？");
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