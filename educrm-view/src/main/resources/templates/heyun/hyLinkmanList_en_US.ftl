<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>The order management <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" href="/teacher/teacherlist/edit">The new employee</a>-->

                <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">delete</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <div class="row">
                    <div class="col-md-4"><div class="tjj"><span class="tjbig" >Today's new :</span> <span class="tjcolor">${(hyOrderNumToday)!}</span></div></div>
                    <div class="col-md-4"><div class="tjj"><span class="tjbig" >Total number of orders :</span> <span class="tjcolor">${(hyOrderNum)!}</span></div></div>
                    <div class="col-md-4"><div class="tjj"><span class="tjbig" >Total number of pedestrians :</span><span class="tjcolor">${(hyUserNum)!}</span></div></div>
                </div>
            <#--<div class="panel-btns">-->
                    <#--<a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>-->
                <#--</div><!-- panel-btns &ndash;&gt;-->
                <!--High show search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">The name :</label>
                        <input type="text"  id="caption" name="reservation" class="form-control form-input-lg"
                               placeholder="The name" />
                    </div>

                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                    </div>

                </form>
                <!--Hide the search-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">

                    </div>

                </div>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
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

    var columns = [
        {"sTitle":"The order no.","data":"map.hyOrder.id","render":renderHyOrder},
        {"sTitle":"The name","data":"name","render":renderHyOrder},
        {"sTitle":"Mobile phone","data" : "phone"},
        {"sTitle":"QQ","data" : "qq"},
        {"sTitle":"WeChat","data" : "wechat"},
        {"sTitle":"The number of travel","data" : "num"},
        {"sTitle":"The progress of","data" : "map.hyOrder.status","render":renderHyOrderStatus},
        {"sTitle":"Creation time","data" : "creatorDate","render": renderDate},
        {"sTitle":"operation","data" : "id","render":renderHyLinkmanEdit}
    ];


    $(document).ready(function() {
        query();
    });


    function query() {
        var data  = {"name":$("input[name='reservation']").val()};
        init(GetTableColumn("hyLinkmanList"),"/heyun/hyLinkman/queryByPaging",data,"pagingTable");
    }


</script>