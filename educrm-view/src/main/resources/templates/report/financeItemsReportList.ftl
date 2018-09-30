
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>财务收入报表 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">


                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                                <tr>
                                    <#if list??>
                                        <#list list as v>
                                            <th>
                                                <#if v.pkExpenseItem?? && v.pkExpenseItem=="count">合计<#else >${(v.map.expItem.caption)!"无费用项目"}</#if>
                                            </th>
                                        </#list>

                                    </#if>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                <#if list??>
                                    <#list list as v>
                                        <td>
                                            ${(v.sum?c)!0}
                                        </td>
                                    </#list>

                                </#if>
                                </tr>

                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

