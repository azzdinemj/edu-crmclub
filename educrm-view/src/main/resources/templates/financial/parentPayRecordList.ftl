
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>点餐支付记录 <span>...</span></h2>
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
                                <select id="selectId" name="className" class="selectpicker show-tick form-control" data-live-search="true">
                                    <option value="">请选择</option>
                                    <#if classinfoList??>
                                        <#list classinfoList as c>
                                            <option value="${(c.pkClassinfo)!}">${(c.caption)!}</option>
                                        </#list>
                                    </#if>
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

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dateTime": $("#datas").val(),"pkClassInfo":$("#selectId").val(),"studentName":$("#studentName").val()};
        init(GetTableColumn("parentPayRecord"),"/finance/parentPayRecord/queryByPaging",data,"pagingTable");
    }


</script>