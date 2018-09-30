<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>费用项目 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" data-url="/finance/expenseItem/create" data-toggle="modal" data-target="#modal">新建项目</a>
                <a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">删除</a>

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
                        <#--<input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"-->
                               <#--value=""/>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="control-label">项目名称 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                        <div class="form-group">
                            <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                        </div>
                    <#--<div class="form-group">-->
                        <#--<label>国籍</label>-->
                        <#--<div class="btn-group">-->
                            <#--<select class="form-control strw">-->
                                <#--<option>全部</option>-->
                                <#--<option>中国</option>-->
                                <#--<option>美国</option>-->
                                <#--<option>英国</option>-->
                                <#--<option>法国</option>-->
                                <#--<option></option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                       </div>-->
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
                                <#--<input type="text" title="" name="reservation" class="form-control form-input-lg"-->
                                       <#--value=""/>-->
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
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>编号</th>-->

                        <#--<th>项目名称</th>-->
                        <#--<th>状态</th>-->
                        <#--<th>是否启用</th>-->
                        <#--<th>创建人</th>-->
                        <#--<th>创建时间</th>-->
                        <#--<th>修改人</th>-->
                        <#--<th>修改时间</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#if list??>-->
                    <#--<#list list as v >-->
                    <#--<tr dataid="${(v.pkExpenseItem)!}">-->
                        <#--<td align="left">-->

                            <#--${(v.code)!}-->

                        <#--</td>-->

                        <#--<td align="left">-->
                            <#--<a class="yellow" data-url="/finance/expenseItem/edit?pkExpenseItem=${(v.pkExpenseItem)!}" data-toggle="modal" data-target="#modal">-->
                                <#--${(v.caption)!}-->
                            <#--</a>-->
                        <#--</td>-->
                        <#--<td>${(v.kind)!}</td>-->
                        <#--<td>${(v.isvalid)!}</td>-->
                        <#--<td align="left">${(v.creator)!}</td>-->
                        <#--<td align="right">${v.creationDate ? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                        <#--<td align="left">${(v.modifier)!}</td>-->
                        <#--<td align="right">${v.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                    <#--</tr>-->
                    <#--</#list>-->
                    <#--</#if>-->
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
//        {"sTitle":"编号","data":"code"},
//        {"sTitle":"项目名称","data" : "caption","render": renderExpenseItemEdit} ,
//        {"sTitle":"项目状态","data" : "kind","render": renderExpenseItemKindStatus},
//        {"sTitle":"是否启用","data" : "isvalid","render": renderExpenseItemStatus},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "caption":$("#caption").val()};
        init(GetTableColumn("expenseItem"),"/finance/expenseItem/queryByPaging",data,"pagingTable");
    }

    function del() {
        var id = $(".table-color-").index();
        if(id == null){
            Notify.danger("请选择一条数据");
            return false;
        }
        var flag = confirm("确定要删除吗？");
        if(flag){
            $.ajax({
                type:"POST",
                url:"/finance/expenseItem/delete",
                data:{"pkExpenseItem":id},
                dataType:"json",
                success:function (data) {
                    if (data.code==0){
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    }else {
                        Notify.danger(data.message);
                        window.location.reload();
                    }
                }
            });
        }

    }
</script>