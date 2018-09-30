<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<style>
    ul li{
        float: left;
    }
    .attendance{
        margin-right;15px;
    }
</style>
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>学员积分 <span>...</span></h2>
        <div class="text-right text-left-sm">
            <a href="#addNewEventModal-1" class="btn btn-info add-new-event" data-toggle="modal">批量加分</a>
            <a href="#addNewEventModal-1" class="btn btn-danger add-new-event" data-toggle="modal">批量减分</a>
            <a href="#" class="btn btn-info">导出</a>
        </div>
    </div>
    <div class="smart-widget">
        <div class="smart-widget-inner">
            <ul class="nav nav-tabs tab-style1">
                <li class="active">
                    <a href="#style1Tab1" data-toggle="tab">
                        <span class="icon-wrapper"></i></span>
                        积分管理
                    </a>
                </li>
                <li>
                    <a href="#style1Tab2" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        积分明细
                    </a>
                </li>
                <li>
                    <a href="#style1Tab3" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        积分规则
                    </a>
                </li>
            </ul>
            <div class="smart-widget-body">
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="style1Tab1">
                        <form class="form-inline search white">
                            <div class=" m-bottom-sm row">
                                <div class="col-xs-8">
                                    <div class="form-group">
                                        <label class="control-label">日期</label>
                                        <input type="text" title=""  name="reservation" id="gj" class="form-control form-input-lg" value="" />
                                        <script type="text/javascript">
                                            $(document).ready(function() {
                                                $('#gj').daterangepicker(null, function(start, end, label) {
                                                    console.log(start.toISOString(), end.toISOString(), label);
                                                });
                                            });
                                        </script>
                                    </div>
                                    <div class="form-group">
                                        <label>孩子</label>
                                        <div class="btn-group">
                                            <input type="text" title="" class='form-control' placeholder="姓名/编号">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>搜索</span>
                                    </div>
                                </div>

                            </div>
                        </form>

                        <div class="message-table table-responsive">
                            <table class="table" >
                                <thead>
                                <tr>
                                    <th class="text-center">
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="chkAll" class="inbox-check">
                                            <label for="chkAll"></label>
                                        </div>
                                    </th>
                                    <th>编号</th>
                                    <th>姓名</th>
                                    <th>中心</th>
                                    <th>班级</th>
                                    <th>老师</th>
                                    <th>已上课次/总课次</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="add">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="a1" class="inbox-check">
                                            <label for="a1"></label>
                                        </div>
                                    </td>
                                    <td>DS20170603</td>
                                    <td>睿睿</td>
                                    <td>虹口</td>
                                    <td>夏春2班</td>
                                    <td>夏春</td>
                                    <td>5/20</td>
                                </tr>
                                <tr class="odd">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="a2" class="inbox-check">
                                            <label for="a2"></label>
                                        </div>
                                    </td>
                                    <td>DS20170603</td>
                                    <td>睿睿</td>
                                    <td>虹口</td>
                                    <td>夏春2班</td>
                                    <td>夏春</td>
                                    <td>5/20</td>
                                </tr>
                                <tr class="add">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="a3" class="inbox-check">
                                            <label for="a3"></label>
                                        </div>
                                    </td>
                                    <td>DS20170603</td>
                                    <td>睿睿</td>
                                    <td>虹口</td>
                                    <td>夏春2班</td>
                                    <td>夏春</td>
                                    <td>5/20</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>

                    </div><!-- ./tab-pane -->
                    <div class="tab-pane fade" id="style1Tab2">
                        <form class="form-inline search white">
                            <div class=" m-bottom-sm row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label>孩子</label>
                                        <div class="btn-group">
                                            <input type="text" title="" class='form-control' placeholder="姓名/编号">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>搜索</span>
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>重置</span>
                                    </div>
                                </div>
                                <div class="col-xs-6 text-right text-left-sm">

                                </div>
                            </div>

                        </form>
                        <div class="form-group row">
                            <div class="big">
                                <span>来源：</span>
                            </div>
                            <ul class="col-sm-11" id="pointDetail_ly">
                                <li class="pointDetail_ruleName_span">
                                    <input type="checkbox" id="all-attendance" class="attendance">
                                    <span>不限</span>
                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance">
                                    <span>学员交费加分</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>学员课消加分</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >

                                    <input type="checkbox"  class="attendance"><span>学员上课加分</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>学员获得好评加分</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>学员家长评价老师加分</span>

                            </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>好人好事11</span>

                                </li>

                               <#-- <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select7" class="attendance">
                                        <label for="select7"></label>
                                    </div><span>作业表现</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select8" class="attendance">
                                        <label for="select8"></label>
                                    </div><span>家长每评价够5次老师，加10分</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select9" class="attendance">
                                        <label for="select9"></label>
                                    </div><span>推荐新学员</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select10" class="attendance">
                                        <label for="select10"></label>
                                    </div><span>课堂回答一次问题</span>

                                </li>

                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select11" class="attendance">
                                        <label for="select11"></label>
                                    </div><span>续费</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select12" class="attendance">
                                        <label for="select12"></label>
                                    </div><span>转介绍</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select13" class="attendance">
                                        <label for="select13"></label>
                                    </div><span>学员交费时抵减学费减分</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select14" class="attendance">
                                        <label for="select14"></label>
                                    </div><span>缺勤减分</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select15" class="attendance">
                                        <label for="select15"></label>
                                    </div> <span>学员退费减分</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select16" class="attendance">
                                        <label for="select16"></label>
                                    </div><span>学员获得差评减分</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select17" class="attendance">
                                        <label for="select17"></label>
                                    </div><span>报名费</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select18" class="attendance">
                                        <label for="select18"></label>
                                    </div><span>换毛绒玩具</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select19" class="attendance">
                                        <label for="select19"></label>
                                    </div><span>兑换玩具车</span>

                                </li>-->


                            </ul>

                        </div>
                        <div class="message-table table-responsive">
                            <table class="table" >
                                <thead>
                                <tr>
                                    <th class="text-center">
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="student" class="inbox-check">
                                            <label for="student"></label>
                                        </div>
                                    </th>
                                    <th >学员</th>
                                    <th >积分</th>
                                    <th >积分来源</th>
                                    <th>原因</th>
                                    <th >时间</th>
                                    <th  width="150px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="add">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="pl1" class="inbox-check">
                                            <label for="pl1"></label>
                                        </div>
                                    </td>
                                    <td>王珞丹</td>
                                    <td>+105</td>
                                    <td>学员交费加分</td>
                                    <td>单次交费720元，加105分。</td>
                                    <td>2017-06-26 14:15:33</td>
                                    <td>
                                        <span class="btn btn-info btn-sm"><a href="#addNewEventModal-1" data-toggle="modal">修改</a> </span>
                                        <span class="btn btn-danger btn-sm"><a href="#addNewEventModal-4" data-toggle="modal">删除</a> </span>
                                    </td>
                                </tr>


                                </tbody>
                            </table>
                        </div>
                    </div><!-- ./tab-pane -->
                    <div class="tab-pane fade" id="style1Tab3">
                        <div class="row">
                            <div class="card-body col-sm-6">
                                <div class="add-integral">
                                    <a href="#addNewEventModal-2" data-toggle="modal" class="btn btn-info btn-sm">
                                        新增加分规则
                                    </a>
                                </div>
                                <div class="message-table table-responsive">
                                    <table class="table">
                                        <thead>
                                        <tr class="top-tr">
                                            <th>规则名称</th>
                                            <th>规则</th>
                                            <th>备注</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="">
                                        <tr>
                                            <td>认真写作</td>
                                            <td>加120分。</td>
                                            <td>参加国际集训营，可获120分钟/一期</td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        删除
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>认真写作</td>
                                            <td>加120分。</td>
                                            <td>参加国际集训营，可获120分钟/一期</td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        删除
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                            <div class="card-body col-sm-6">
                                <div class="add-integral">
                                    <a href="#addNewEventModal-3" data-toggle="modal" class="btn-sm btn btn-info theme-login-integral2" id="add">
                                        新增减分规则
                                    </a>
                                </div>
                                <div class="message-table table-responsive">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>规则名称</th>
                                            <th>规则</th>
                                            <th>备注</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="">
                                        <tr>
                                            <td>兑换六艺雨伞一把</td>
                                            <td title="减50分。">减50分。</td>
                                            <td></td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        删除
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>换毛绒玩具</td>
                                            <td title="减20分。">减20分。</td>
                                            <td></td>
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        删除
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>每次迟到</td>
                                            <td title="减10分。">减10分。</td>
                                            <td></td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        删除
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        </tbody>

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ./tab-pane -->
                </div><!-- ./tab-content -->
            </div>


        </div>
    </div>

</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>
