<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Class time<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classTime/create">Newly build</a>
                        <button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="del()" data-toggle="modal">delete</a></li>
                    </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <!--High search-->
                        <form class="form-inline search white">
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Date, :</label>-->
                                <#--<input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />-->
                            <#--</div>-->
                            <div class="form-group">
                                <label class="control-label">Name :</label>
                                <input type="text" title="" id="caption"  name="reservation"  class="form-control" value="" />
                            </div>

                            <div class="form-group">
                                <a onclick="query()" class="btn btn-newblue btn-sm search">search</a>
                            </div>

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>search</span>
                               </div>-->
                        </form>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered lg-table" cellspacing="0">
                            <thead>

                            <#--<tr>-->
                                <#--<th>number</th>-->
                                <#--<th>Name</th>-->
                                <#--<th>start time</th>-->
                                <#--<th>End time</th>-->
                                <#--<th>sorting</th>-->
                                <#--<th>type</th>-->
                                <#--<th>Remarks</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>Modifier</th>-->
                                <#--<th>Modified</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#list list as v>-->
                            <#--<tr dataid="${(v.pkClassTime)!}">-->

                                <#--<td align="left"> ${(v.pkClassTime)!}</td>-->
                                <#--<td align="left">-->
                                    <#--<a class="yellow" data-toggle="modal" data-target="#modal" data-url="/classinfo/classTime/edit?pkClassTime=${(v.pkClassTime)!}">-->
                                        <#--${(v.caption)!}-->
                                    <#--</a>-->
                                <#--</td>-->
                                <#--<td align="left"> ${(v.startTime)!}</td>-->
                                <#--<td align="left"> ${(v.endTime)!}</td>-->
                                <#--<td align="right"> ${(v.sort)!}</td>-->
                                <#--<td align="right"> <#if v.type?? && v.type == 0>preschool<#elseif v.type == 1>Primary school<#else >middle school</#if></td>-->
                                <#--<td align="left"> ${(v.memo)!}</td>-->
                                <#--<td align="left"> ${(v.map.creatorEntity.caption)!}</td>-->
                                <#--<td align="left"> ${(v.creationDate?string("yyyy-MM-dd"))!}</td>-->
                                <#--<td align="left"> ${(v.map.modifierEntity.caption)!}</td>-->
                                <#--<td align="left"> ${(v.lasteditDate?string("yyyy-MM-dd"))!}</td>-->
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

    $(document).ready(function() {
        query();
    } );

    function renderCaption(data, type, row) {
        return '<a data-toggle="modal" data-target="#modal" data-url="/classinfo/classTime/edit?pkClassTime=' + row.pkClassTime + '" class="yellow" >' + data + '</a>';
    <#--<a class="yellow" data-toggle="modal" data-target="#modal" data-url="/classinfo/classTime/edit?pkClassTime=${(v.pkClassTime)!}">-->
    }

    function renderType(data) {
        if(data==0){
           return  "preschool";
        }else if(data==1){
            return "Primary school";
        }else{
            return "middle school"
        }
    }

    function query() {
        var data  = {"caption":$("input[name='reservation']").val()};
        init(GetTableColumn("classTime"),"/classinfo/classTime/queryByPaging",data,"pagingTable");
    }


    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkClassTime;
        if(id ==null){
            alert("Please select the data to delete first");
            return;
        }
        var flag = confirm("Do you confirm the deletion？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/classinfo/classTime/delete",
                data: {"pkClassTime": id},
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