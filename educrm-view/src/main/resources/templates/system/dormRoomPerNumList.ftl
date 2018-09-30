<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>宿舍报表<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">


            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <#--<div class="panel-btns">-->
                    <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                <#--</div><!-- panel-btns &ndash;&gt;-->
                <#--<!--高显搜索&ndash;&gt;-->
                <#--<form class="form-inline search white">-->
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">学生姓名 :</label>-->
                        <#--<input id="caption" type="text" title="" name="reservation" class="form-control form-input-lg"-->
                               <#--value=""/>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<label></label>-->
                        <#--<div class="btn-group">-->
                            <#--<button type="button" onclick="query();" class="btn btn-newblue btn-sm search">搜索</button>-->
                        <#--</div>-->
                    <#--</div>-->

                <#--</form>-->
                <#--<!--隐藏搜索&ndash;&gt;-->
                <#--<div class="panel-body senior-search">-->
                    <#--<div id="post-status" class="tab-pane active">-->
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label>少数民族</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw" id="isminority">-->
                                        <#--<option value="">请选择</option>-->
                                        <#--<option value="0">是</option>-->
                                        <#--<option value="1">否</option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">手机号 :</label>-->
                                <#--<input type="text" title="" id="phone" name="reservation"-->
                                       <#--class="form-control form-input-lg" value=""/>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">入学年级 :</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw" id="grade">-->
                                        <#--<option value="">请选择</option>-->
                                    <#--<#if grade??>-->
                                        <#--<#list grade as g>-->
                                            <#--<option value="${(g.pkSysDictValues)!}">${(g.caption)!}</option>-->
                                        <#--</#list>-->
                                    <#--</#if>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</form>-->
                    <#--</div>-->

                <#--</div>-->
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

<#include "../commons/footer.ftl" />
<script type="text/javascript">


//    var columns = [
//        {"sTitle":"校区","data" : "map.domainEntity.caption"} ,
//        {"sTitle":"宿舍类型","data":"kind","render": function (data){
//            if (data==0){ return '学生宿舍';}
//            else {return '老师宿舍'; }
//        }},
//        {"sTitle":"宿舍性别","data":"sex","render": function (data){
//            if (data==1){ return '男';}
//            else {return '女'; }
//        }},
//        {"sTitle":"入住人数人数","data":"num"}
//
//    ];
//    function renderCaption(data, type, row) {
//        return '<a href="/student/studentInterview/edit?pkStudentInterview='+row.pkStudentInterview+'" class="yellow" >'+data+'</a>';
//    }

    $(document).ready(function () {
        query();
    });

    function query() {
        var data  = { "pkDoamin": $("#pkDomainId").val()};
        init(GetTableColumn("dormRoomPerNum"),"/system/dormRoomPerNum/queryByPaging",data,"pagingTable");
    }

function renderRoomKind(data, type, row) {
    if (data==0){
        return "学生宿舍";
    }else if (data==1){
        return "老师宿舍";
    }
}

</script>