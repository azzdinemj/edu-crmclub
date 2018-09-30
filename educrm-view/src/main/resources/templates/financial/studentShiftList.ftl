
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>转班记录 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" id="auditBtn" data-toggle="modal" data-target="#modal" data-url="/student/studentShift/setFinancial">审核</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <#--<div class="panel-btns">-->
                            <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                        <#--</div><!-- panel-btns &ndash;&gt;-->
                        <!--高显搜索-->
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">日期 :</label>-->
                                <#--<input type="text" title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />-->
                            <#--</div>-->
                            <#--&lt;#&ndash;<div class="form-group">&ndash;&gt;-->
                                <#--&lt;#&ndash;<label class="control-label">单号 :</label>&ndash;&gt;-->
                                <#--&lt;#&ndash;<input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />&ndash;&gt;-->
                            <#--&lt;#&ndash;</div>&ndash;&gt;-->
                            <#--<div class="form-group">-->
                                <#--<a class="btn btn-newblue btn-sm search">搜索</a>-->
                            <#--</div>-->
                        <#--</form>-->
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <#--<tr>-->
                                <#--<th>学生</th>-->
                                <#--&lt;#&ndash;<th>费用项目</th>&ndash;&gt;-->
                                <#--<th>经办人</th>-->
                                <#--<th>单号</th>-->
                                <#--<th>日期</th>-->
                                <#--<th>费用</th>-->
                                <#--<th>已收款</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if receipt ??>-->
                                <#--<#list receipt as rec>-->
                            <#--<tr>-->
                               <#--&lt;#&ndash; <td>-->
                                    <#--<!--三个按钮不同时显示。。按照顺序，收款结束后出现详情按钮&ndash;&gt;-->
                                    <#--<a class="btn btn-success btn-sm"  data-toggle="modal" data-target="#myModal" data-url="collection.html">-->
                                        <#--收款-->
                                    <#--</a>-->

                                    <#--<a class="hide btn btn-sm btn-info" data-toggle="modal" data-target="#myModal" data-url="pay.html">详情</a>-->

                                    <#--<a class="hide btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">删除</a>-->

                                <#--</td>&ndash;&gt;-->
                                <#--<td> <a class="yellow" href="/finance/studentCharge/edit?pkReceipt=${(rec.pkReceipt)!}">${(rec.pkStudent)!} </a></td>-->
                                <#--<td>-->
                                <#--${(rec.pkExpenseItem)!}-->
                                <#--</td>-->
                                <#--<td>${(rec.pkSysUser)!}</td>-->
                                <#--<td>${(rec.code)!}</td>-->
                                <#--<td>${(rec.date?string("yyyy-MM-dd"))!}</td>-->
                                <#--<td>${(rec.cost)!}</td>-->
                                <#--<td>${(rec.money)!}</td>-->
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
    var columns = [
        {"sTitle":"编号","data":"pkStudentShift"},
        {"sTitle":"学生姓名","data":"map.studentEntity.caption"},
        {"sTitle":"原来班级","data" : "map.parentClassInfoEntity.caption"} ,
        {"sTitle":"转入班级","data" : "map.classInfoEntity.caption"},
        {"sTitle":"状态","data" : "isvalid","render":renderStudentShiftStatus},
        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {};
        init(GetTableColumn("studentShift"),"/student/studentShift/queryByPaging",data,"pagingTable");
    }

    //审核
    $("#auditBtn").on("click",function(){
        var index = $(".table-color-").index();
        if(index == null){
            Notify.danger("请选择一行数据!!!");
            return false;
        }

        var pkStudentShift = getTableSelectRow("pagingTable",index).pkStudentShift;
        var status = getTableSelectRow("pagingTable",index).isvalid;
        if(status != 2){
            $("#auditBtn").attr("data-url",$("#auditBtn").attr("data-url")+"?pkStudentShift="+pkStudentShift);
        }else{
            Notify.danger("不可重复提交!!!");
            return false;
        }

    });

</script>