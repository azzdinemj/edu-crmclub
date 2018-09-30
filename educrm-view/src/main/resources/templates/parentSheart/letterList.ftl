<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>家长信<span>...</span></h2>
    </div>
    <div class="smart-widget">
        <div class="smart-widget-inner">
            <ul class="nav nav-tabs tab-style1">
                <li class="active">
                    <a href="#style1Tab1" data-toggle="tab">
                        <span class="icon-wrapper"></i></span>
                        消息发送统计
                    </a>
                </li>
                <li>
                    <a href="#style1Tab2" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        家长回复统计
                    </a>
                </li>
                <li>
                    <a href="#style1Tab3" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        环比统计
                    </a>
                </li>
                <li>
                    <a href="#style1Tab3" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        消息查询
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
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>搜索</span>
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>重置</span>
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
                                    <th>校区</th>
                                    <th>发送次数</th>
                                    <th>上课人次</th>
                                    <th>发送比</th>

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
                    </div>
                    <!-- ./tab-pane -->
                </div><!-- ./tab-content -->
            </div>


        </div>
    </div>

</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>
