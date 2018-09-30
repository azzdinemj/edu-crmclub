
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>学生成绩单<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                 <!--   <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">删除</a>
                        <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal" data-url="new_student_planing.html">新建记录</a>

                        &lt;!&ndash;     <button title="更多操作" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="" data-toggle="modal">删除</a></li>
                        <li class="divider"></li>
                    </ul>&ndash;&gt;
                    </div>-->
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
                                <label class="control-label">学生名称 :</label>
                                <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
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
                                <th><input type="checkbox" id="chAll"></th>
                                <th width="200">校区</th>
                                <th width="200">学生</th>
                                <th width="250">年级</th>
                                <th width="300">班主任</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>

                                <td><input type="checkbox" class="px"></td>
                                <td><a class="success" href="/student/studentTestPlansScores/edit">微微</a></td>
                                <td>美国</td>
                                <td>喜欢</td>
                                <td>化学</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" class="px"></td>
                                <td><a class="success" href="/student/studentTestPlansScores/edit">微微</a></td>
                                <td>美国</td>
                                <td>喜欢</td>
                                <td>化学</td>

                            </tr>
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>