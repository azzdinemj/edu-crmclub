<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>排课 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/schedule/arrnging/arrangingAll">大班排班</a>
                <a class="btn btn-sm btn-newblue"  href="/schedule/arrnging/arrangingOne2One"> 私教排班 </a>
                <#--<a class="btn btn-sm btn-newblue"  href="/schedule/arrnging/arrangingOne2More"> 一对多排班 </a>-->
            </div>
         </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                <div class="panel-heading pd10 bor0 new">
                    <div class="panel-btns">
                        <form class="form-inline white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"
                                       value=""/>
                            </div>
                            <div class="btn-group">
                                <label class="checkbox-inline checkbox-styled">
                                    <input value="4" type="checkbox"><span>大班</span>
                                </label>&nbsp;&nbsp;
                                <label class="checkbox-inline checkbox-styled">
                                    <input value="1" type="checkbox"><span>私教</span>
                                </label>&nbsp;&nbsp;

                                <label class="checkbox-inline checkbox-styled">
                                    <input value="1" type="checkbox"><span>启用</span>
                                </label>&nbsp;&nbsp;
                                <label class="checkbox-inline checkbox-styled">
                                    <input value="0" type="checkbox"><span>停用</span>
                                </label>
                            </div><label class="checkbox-inline checkbox-styled" id="ShiftGrantCB" config="EnableShiftGrant">
                                <input type="checkbox"><span>只看授权给当前校区的课程</span>
                            </label>
                            </div>
                        </form>
                    </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th>班级编号</th>
                                <th>班级名称</th>
                                <th>上课老师</th>
                                <th>上课进度</th>
                                <th>上课时间</th>
                                <th>上课时长</th>
                                <th>状态</th>
                                <th>实到人数</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#--<#if list??>-->
                            <#--<#list list as v >-->
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">CL0000001</td>
                                <td align="left">黑带卡学生班</td>
                                <td align="left">
                                    <a class="yellow" data-url="/finance/expenseItem/edit?pkExpenseItem=${(v.pkExpenseItem)!}" data-toggle="modal" data-target="#modal">

                                    </a>
                                    李鬼
                                </td>
                                <td>1/2</td>
                                <td align="left">90min</td>
                                <td align="left">2018-04-01 14:00</td>
                                <td align="left">正常</td>
                                <td align="left">23</td>
                            </tr>


                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">CL0000002</td>
                                <td align="left">私教一对一班级</td>
                                <td align="left">
                                    <a class="yellow" data-url="/finance/expenseItem/edit?pkExpenseItem=${(v.pkExpenseItem)!}" data-toggle="modal" data-target="#modal">

                                    </a>
                                    李奎
                                </td>
                                <td>1/5</td>
                                <td align="left">60min</td>
                                <td align="left">2018-04-01 14:00</td>
                                <td align="left">正常</td>
                                <td align="left">1</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">CL0000001</td>
                                <td align="left">亲情卡学生班</td>
                                <td align="left">
                                    <a class="yellow" data-url="/finance/expenseItem/edit?pkExpenseItem=${(v.pkExpenseItem)!}" data-toggle="modal" data-target="#modal">

                                    </a>
                                    李鬼
                                </td>
                                <td>1/2</td>
                                <td align="left">90min</td>
                                <td align="left">2018-04-01 14:00</td>
                                <td align="left">正常</td>
                                <td align="left">23</td>
                            </tr>

                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">CL0000001</td>
                                <td align="left">一年卡学生班</td>
                                <td align="left">
                                    <a class="yellow" data-url="/finance/expenseItem/edit?pkExpenseItem=${(v.pkExpenseItem)!}" data-toggle="modal" data-target="#modal">

                                    </a>
                                    李鬼
                                </td>
                                <td>1/2</td>
                                <td align="left">90min</td>
                                <td align="left">2018-04-01 14:00</td>
                                <td align="left">正常</td>
                                <td align="left">23</td>
                            </tr>

                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">CL0000001</td>
                                <td align="left">钻石卡学生班</td>
                                <td align="left">
                                    <a class="yellow" data-url="/finance/expenseItem/edit?pkExpenseItem=${(v.pkExpenseItem)!}" data-toggle="modal" data-target="#modal">

                                    </a>
                                    李鬼
                                </td>
                                <td>1/2</td>
                                <td align="left">90min</td>
                                <td align="left">2018-04-01 14:00</td>
                                <td align="left">正常</td>
                                <td align="left">23</td>
                            </tr>

                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">CL0000001</td>
                                <td align="left">黑带卡学生班</td>
                                <td align="left">
                                    <a class="yellow" data-url="/finance/expenseItem/edit?pkExpenseItem=${(v.pkExpenseItem)!}" data-toggle="modal" data-target="#modal">

                                    </a>
                                    李鬼
                                </td>
                                <td>1/2</td>
                                <td align="left">90min</td>
                                <td align="left">2018-04-01 14:00</td>
                                <td align="left">正常</td>
                                <td align="left">23</td>
                            </tr>

                            <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li><a href="#">&laquo;</a></li>
                            <li class="active"><a href="#">1</a></li>
                        <#--<li class="disabled"><a href="#">2</a></li>-->
                        <#--<li><a href="#">3</a></li>-->
                        <#--<li><a href="#">4</a></li>-->
                        <#--<li><a href="#">5</a></li>-->
                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->
                </div>
            </div>
        </div>

        </div>
</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>
