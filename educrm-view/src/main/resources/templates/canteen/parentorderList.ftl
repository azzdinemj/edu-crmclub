
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>点餐记录 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <#--<a class="btn btn-sm btn-newblue" id="auditBtn">审核</a>-->
                        <#--<a class="btn btn-sm btn-newblue" id="retreatBtn">驳回</a>-->

                        <!--     <button title="更多操作" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="" data-toggle="modal">删除</a></li>
                        <li class="divider"></li>
                    </ul>-->
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
                            <div class="form-group">
                                <label class="control-label">班级 :</label>
                                <select id="selectClassinfo" name="className" class="selectpicker show-tick form-control" data-live-search="true">
                                    <option value="">请选择班级</option>
                                    <#if classinfoList??>
                                        <#list classinfoList as c>
                                            <option value="${(c.pkClassinfo)!}">${(c.caption)!}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">餐别 :</label>
                                <select id="selecttype" class="form-control" style="width: 175px!important;">
                                    <option value="">请选择餐别</option>
                                    <option value="0">早餐</option>
                                    <option value="1">午餐</option>
                                    <option value="2">晚餐</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">时段 :</label>
                                <input type="text" title="" id="datas" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">学生姓名 :</label>
                                <input type="text" title="" id="studentName" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <a onclick="query()" class="btn btn-newblue btn-sm search">搜索</a>
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
        query();
    } );

    function renderCaption(data, type, row) {
        return '<a href="/student/trophicAnalysis/query?pkStudent=' + row.pkStudent + '&studentName=' + row.studentName + '" class="yellow" >' + data + '</a>';
    }

    function query() {
        var data  = { "dateTime": $("#datas").val(),"pkClassinfo":$("#selectClassinfo").val(),"studentName":$("#studentName").val(),"type":$("#selecttype").val()};
        init(GetTableColumn("parentOrder"),"/canteen/parentOrderRecord/queryByPaging",data,"pagingTable");
    }

    function renderType(data, type, row) {
        if (data==0){
            return '早餐';
        }
        else if (data==1){
            return '午餐';
        }else {
            return '晚餐';
        }

    }


</script>