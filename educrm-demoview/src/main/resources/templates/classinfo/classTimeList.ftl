<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>上课时间<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classTime/create">新建</a>
                        <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="del()" data-toggle="modal">删除</a></li>
                    </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">名称 :</label>
                                <input type="text" title="" id="caption"  name="reservation"  class="form-control" value="" />
                            </div>

                            <div class="form-group">
                                <a class="btn btn-newblue btn-sm search">搜索</a>
                            </div>

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                               </div>-->
                        </form>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="table"  class="table table-striped table-bordered lg-table" cellspacing="0">
                            <thead>

                            <tr>
                                <th>编号</th>
                                <th>名称</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>排序</th>
                                <th>是否启用</th>
                                <th>备注</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>修改人</th>
                                <th>修改时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list list as v>
                            <tr dataid="${(v.pkClassTime)!}">

                                <td align="left"> ${(v.pkClassTime)!}</td>
                                <td align="left">
                                    <a class="yellow" data-toggle="modal" data-target="#modal" data-url="/classinfo/classTime/edit?pkClassTime=${(v.pkClassTime)!}">
                                        ${(v.caption)!}
                                    </a>
                                </td>
                                <td align="left"> ${(v.startTime)!}</td>
                                <td align="left"> ${(v.endTime)!}</td>
                                <td align="right"> ${(v.sort)!}</td>
                                <td >
                                    <#if v.isvalid ?? && v.isvalid ==1 >是<#else >否</#if>

                                </td>
                                <td align="left"> ${(v.memo)!}</td>
                                <td align="left"> ${(v.map.creatorEntity.caption)!}</td>
                                <td align="left"> ${(v.creationDate?string("yyyy-MM-dd"))!}</td>
                                <td align="left"> ${(v.map.modifierEntity.caption)!}</td>
                                <td align="left"> ${(v.lasteditDate?string("yyyy-MM-dd"))!}</td>
                            </tr>
                            </#list>


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

    function query() {
        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val()};
//        init(columns,"/classinfo/classTime/queryByPaging",data);
    }


    function del() {
        var id = $(".table-color-").attr("dataid");
        if(id ==null){
            alert("请先选择要删除的数据");
            return;
        }
        var flag = confirm("确认删除吗？");
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