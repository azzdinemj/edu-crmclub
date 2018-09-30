<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i><@spring.message "teaching.studentaskforleave"/> <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/student/studentaskforleave/edit"><@spring.message "entire.add"/></a>
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
                        <label class="control-label"><@spring.message "teaching.stuname"/> :</label>
                        <input id="caption" type="text" title="" name="reservation" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label></label>
                        <div class="btn-group">
                            <button type="button" onclick="query();" class="btn btn-newblue btn-sm search"><@spring.message "entire.search"/></button>
                        </div>
                    </div>

                </form>

                <!--Hide search-->
                <#--<div class="panel-body senior-search">-->
                    <#--<div id="post-status" class="tab-pane active">-->
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label>minority</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw" id="isminority">-->
                                        <#--<option value="">Please choose</option>-->
                                        <#--<option value="0">是</option>-->
                                        <#--<option value="1">否</option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">phone :</label>-->
                                <#--<input type="text" title="" id="phone" name="reservation"-->
                                       <#--class="form-control form-input-lg" value=""/>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">School year :</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw" id="grade">-->
                                        <#--<option value="">Please choose</option>-->
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
                <table id="pagingTable" class="table table-striped table-bordered " cellspacing="0">
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
//        {"sTitle":"number","data":"pkAskForLeave"},
//        {"sTitle":"Student name","data" : "stuEmpCaption",
//            "render": function (data, type, row){
//                return '<a href="/student/studentaskforleave/edit?pkAskForLeave='+row.pkAskForLeave+'" class="yellow" >'+data+'</a>';
//            }},
//        {"sTitle":"leave Reason","data" : "remark"},
//        {"sTitle":"leave Subject","data" : "map.sysDictValues.caption","render":function (data) {
//            if (data==null){ return '';}
//            else {return data; }
//        }},
//        {"sTitle":"Leave state","data" : "status","render":function (data) {
//            if (data==0){ return 'normal';}
//            else {return 'abnormal'; }
//        }},
//        {"sTitle":"start time","data" : "startTime","render": renderDate},
//        {"sTitle":"End time","data" : "endTime","render": renderDate}
//    ];


    $(document).ready(function() {
        query();
    } );

    function renderCaption (data, type, row){
                return '<a href="/student/studentaskforleave/edit?pkAskForLeave='+row.pkAskForLeave+'" class="yellow" >'+data+'</a>';
    }

    function rendersysDictValues(data) {
                if (data==null){ return '';}
                else {return data; }
    }

    function renderStatus(data){
        if (data==0) {return 'normal';}
        else {return 'abnormal'; }
    }

//    function query() {
//        var data  = { "dataTime": $("#datas").val(),"stuEmpCaption":$("#caption").val()};
//        init(columns,"/student/studentaskforleave/queryByPaging",data,"pagingTable");
//    }

    //Chinese and English versions
    function query() {
        var data  = { "dataTime": $("#datas").val(),"stuEmpCaption":$("#caption").val()};
        init(GetTableColumn("studentAskForLeave"), "/student/studentaskforleave/queryByPaging", data,"pagingTable");
    }
</script>