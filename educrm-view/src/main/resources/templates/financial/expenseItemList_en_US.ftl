<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>item <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" data-url="/finance/expenseItem/create" data-toggle="modal" data-target="#modal">New project</a>
                <a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">delete</a>

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
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">Date, :</label>-->
                        <#--<input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"-->
                               <#--value=""/>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="control-label">project name :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                        <div class="form-group">
                            <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                        </div>
                    <#--<div class="form-group">-->
                        <#--<label>nationality</label>-->
                        <#--<div class="btn-group">-->
                            <#--<select class="form-control strw">-->
                                <#--<option>whole</option>-->
                                <#--<option>China</option>-->
                                <#--<option>U.S.A</option>-->
                                <#--<option>Britain</option>-->
                                <#--<option>France</option>-->
                                <#--<option></option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>search</span>
                       </div>-->
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
                                <#--<input type="text" title="" name="reservation" class="form-control form-input-lg"-->
                                       <#--value=""/>-->
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
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--<th>number</th>-->

                        <#--<th>project name</th>-->
                        <#--<th>state</th>-->
                        <#--<th>Is enabled</th>-->
                        <#--<th>Founder</th>-->
                        <#--<th>Creation time</th>-->
                        <#--<th>Modifier</th>-->
                        <#--<th>Modified</th>-->
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
//        {"sTitle":"number","data":"code"},
//        {"sTitle":"project name","data" : "caption","render": renderExpenseItemEdit} ,
//        {"sTitle":"Project status","data" : "kind","render": renderExpenseItemKindStatus},
//        {"sTitle":"Is enabled","data" : "isvalid","render": renderExpenseItemStatus},
//        {"sTitle":"Founder","data" : "map.creatorEntity.caption"},
//        {"sTitle":"Creation time","data" : "creationDate","render": renderDate},
//        {"sTitle":"Modifier","data" : "map.modifierEntity.caption"},
//        {"sTitle":"Modified","data" : "lasteditDate","render": renderDate}
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
            Notify.danger("Please select a piece of data");
            return false;
        }
        var flag = confirm("Are you sure you want to delete it？");
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