<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>订单管理 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" href="/teacher/teacherlist/edit">新建员工</a>-->

                <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">删除</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <div class="row">
                    <div class="col-md-4"><div class="tjj"><span class="tjbig" >今日新增 :</span> <span class="tjcolor">${(hyOrderNumToday)!}</span></div></div>
                    <div class="col-md-4"><div class="tjj"><span class="tjbig" >订单总数 :</span> <span class="tjcolor">${(hyOrderNum)!}</span></div></div>
                    <div class="col-md-4"><div class="tjj"><span class="tjbig" >出行人总数量 :</span><span class="tjcolor">${(hyUserNum)!}</span></div></div>
                </div>
            <#--<div class="panel-btns">-->
                    <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                <#--</div><!-- panel-btns &ndash;&gt;-->
                <!--高显搜索-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">姓名 :</label>
                        <input type="text"  id="caption" name="reservation" class="form-control form-input-lg"
                               placeholder="姓名" />
                    </div>

                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
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
        {"sTitle":"订单编号","data":"map.hyOrder.id","render":renderHyOrder},
        {"sTitle":"姓名","data":"name","render":renderHyOrder},
        {"sTitle":"手机","data" : "phone"},
        {"sTitle":"QQ","data" : "qq"},
        {"sTitle":"微信","data" : "wechat"},
        {"sTitle":"出行人数","data" : "num"},
        {"sTitle":"进度","data" : "map.hyOrder.status","render":renderHyOrderStatus},
        {"sTitle":"创建时间","data" : "creatorDate","render": renderDate},
        {"sTitle":"操作","data" : "id","render":renderHyLinkmanEdit}
    ];


    $(document).ready(function() {
        query();
    });


    function query() {
        var data  = {"name":$("input[name='reservation']").val()};
        init(GetTableColumn("hyLinkmanList"),"/heyun/hyLinkman/queryByPaging",data,"pagingTable");
    }


</script>