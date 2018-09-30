<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>教室管理<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal"
                   data-url="/classinfo/classRoom/create">新建教室</a>
                <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle"
                        data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a onclick="del()" data-toggle="modal">删除</a></li>
                </ul>
                <#--<form action="/classinfo/classRoom/uploads" method="post" enctype="multipart/form-data">-->
                    <#--<input type="file" name="fileupload"/>-->
                    <#--<input type="submit" value="提交">-->
                <#--</form>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <form class="form-inline search white">
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">日期 :</label>-->
                        <#--<input type="text" title="" name="reservation" id="datas" class="form-control form-input-time"value=""/>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="control-label">教室名称 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                       </div>-->
                </form>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered " cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>教室编号</th>-->
                        <#--<th>教室名称</th>-->
                        <#--<th>容纳人数</th>-->
                        <#--<th>状态</th>-->
                        <#--<th>创建人</th>-->
                        <#--<th>创建时间</th>-->
                        <#--<th>修改人</th>-->
                        <#--<th>修改时间</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#list list as v>-->
                    <#--<tr dataid="${(v.pkClassRoom)!}">-->
                        <#--<td align="left">-->
                            <#--<a class="yellow" data-toggle="modal" data-target="#modal"-->
                               <#--data-url="/classinfo/classRoom/edit?pkClassRoom=${(v.pkClassRoom)!}">${(v.code)!}</a>-->
                        <#--</td>-->
                        <#--<td align="left">${(v.caption)!}</td>-->
                        <#--<td>${(v.num)!}</td>-->
                        <#--<td><#if v.isvalid?? && v.isvalid ==1>启用<#else >禁用</#if></td>-->
                        <#--<td align="left">${(v.map.creatorEntity.caption)!}</td>-->
                        <#--<td align="right">${v.creationDate ? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                        <#--<td align="left">${(v.map.modifierEntity.caption)!}</td>-->
                        <#--<td align="right">${v.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                    <#--</tr>-->
                    <#--</#list>-->

                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">


//    var columns = [
//        {"sTitle":"教室编号","data":"code"},
//        {"sTitle":"教室名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/classinfo/classRoom/edit?pkClassRoom='+row.pkClassRoom+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"容纳人数","data" : "num"},
//        {"sTitle":"状态","data" : "isvalid","render": function (data){
//            if (data==1){ return '启用';}
//            else {return '禁用'; }
//        }},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];

    function renderCaption(data, type, row) {
        return '<a data-toggle="modal" data-target="#modal" data-url="/classinfo/classRoom/edit?pkClassRoom='+row.pkClassRoom+'" class="yellow" >'+data+'</a>';
    }

$(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val()};
        init(GetTableColumn("classRoom"),"/classinfo/classRoom/queryByPaging",data,"pagingTable");
    }

    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassRoom;
        if (id == null) {
            alert("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/classinfo/classRoom/delete",
                data: {"pkClassRoom": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        alert(data.message)
                    }
                },
                error: function () {

                }
            });
        }


    }
</script>