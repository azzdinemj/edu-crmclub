
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>学生退费 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

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
                                <label class="control-label">日期 :</label>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">学生姓名 :</label>
                                <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label>国籍</label>
                                <div class="btn-group">
                                    <select class="form-control">
                                        <option>全部</option>
                                        <option>中国</option>
                                        <option>美国</option>
                                        <option>英国</option>
                                        <option>法国</option>
                                        <option></option>
                                    </select>
                                </div>
                            </div>

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                               </div>-->
                        </form>
                        <!--隐藏搜索-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <form class="form-inline search white">
                                    <div class="form-group">
                                        <label>学部</label>
                                        <div class="btn-group">
                                            <select class="form-control">
                                                <option>全部</option>
                                                <option>一部</option>
                                                <option>二部</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">家长姓名 :</label>
                                        <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">入学年级 :</label>
                                        <div class="btn-group">
                                            <select class="form-control">
                                                <option>全部</option>
                                                <option>一年级</option>
                                                <option>二年级</option>
                                                <option>三年级</option>
                                                <option>四年级</option>
                                                <option></option>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th width="250">操作</th>
                                <th width="200">学生</th>
                                <th width="200">费用项目</th>
                                <th width="200">经办人</th>
                                <th width="250">单号</th>
                                <th width="250">日期</th>
                                <th width="250">费用</th>
                                <th width="200">已收款</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <!--三个按钮不同时显示。。按照顺序，收款结束后出现详情按钮-->
                                    <a class="btn btn-danger btn-sm"  data-toggle="modal" data-target="#myModal" data-url="refund.html">
                                        退费
                                    </a>

                                    <a class="hide btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">删除</a>

                                </td>
                                <td>蕊蕊</td>
                                <td>
                                    学费
                                </td>
                                <td>张老师</td>
                                <td>245647544</td>
                                <td>2018-1-8</td>
                                <td>20万</td>
                                <td>20万</td>
                            </tr>
                            <tr>
                                <td>
                                    <a class="btn btn-danger btn-sm"  data-toggle="modal" data-target="#myModal" data-url="refund.html">
                                        退费
                                    </a>

                                </td>
                                <td>蕊蕊</td>
                                <td>
                                    学费
                                </td>
                                <td>张老师</td>
                                <td>245647544</td>
                                <td>2018-1-8</td>
                                <td>20万</td>
                                <td>20万</td>
                            </tr>
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>