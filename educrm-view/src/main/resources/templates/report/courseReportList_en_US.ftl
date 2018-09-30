
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Curriculum statistics <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" onclick="queryTeacherCourese()" >Teacher's Report</a>
                        <a class="btn btn-sm btn-newblue" onclick="querystudentCourese()" >Student report</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <#--<div class="panel-btns">-->
                            <#--<a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>-->
                        <#--</div><!-- panel-btns &ndash;&gt;-->
                        <!--High search-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">Date, :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <div class="form-group">
                                   <span id="queryBy" class="btn btn-newblue btn-sm search" ><i class="fa fa-search"> </i>search</span>
                           </div>
                        </form>

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
        queryTeacherCourese();
    } );

    function queryTeacherCourese() {
        var data  = {"type":"teacher","times":$("#datas").val()};
        $("#queryBy").attr("onclick","queryTeacherCourese()");
        init(GetTableColumn("teacherCoureseReport"),"/report/courseReport/queryByPaging",data,"pagingTable");
    }
    function querystudentCourese() {
        var data  = {"type":"student","times":$("#datas").val()};
        $("#queryBy").attr("onclick","querystudentCourese()");
        init(GetTableColumn("studentCourseReport"),"/report/courseReport/queryByPaging",data,"pagingTable");
    }

</script>
