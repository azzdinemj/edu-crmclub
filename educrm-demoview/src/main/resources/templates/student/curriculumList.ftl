
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />


<div class="contentpanel lg-height">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>课程表<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal" data-url="/classinfo/curriulum/create">新建课程</a>
                <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">删除</a>
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

                <!--  <div class="panel-btns">
                      <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                  </div>&lt;!&ndash; panel-btns &ndash;&gt;-->
                <!--高显搜索-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">班级名称 :</label>
                        <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                    </div>

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                       </div>-->
                </form>
                <!--隐藏搜索-->
                <!-- <div class="panel-body senior-search">
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

                 </div>-->
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="chAll"></th>
                        <th width="250">班级名称</th>
                        <th width="300">年级</th>
                        <th width="200">教室</th>
                        <th width="200">学部主任</th>
                        <th width="200">班主任</th>
                        <th width="200">副班主任</th>
                        <th width="200">开始时间</th>
                        <th width="200">结束时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="checkbox" class="px"></td>
                        <td>
                            <a class="success" data-toggle="modal" data-target="#modal" data-url="/classinfo/curriulum/edit">
                                A班
                            </a>
                        </td>
                        <td>二年级</td>
                        <td>教室</td>
                        <td>张主任</td>
                        <td>张主任</td>
                        <td>张主任</td>
                        <td>2018-9-1</td>
                        <td>2018-9-1</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" class="px"></td>
                        <td>
                            <a class="success" data-toggle="modal" data-target="#modal" data-url="/classinfo/curriulum/edit">
                                A班
                            </a>
                        </td>
                        <td>二年级</td>
                        <td>教室</td>
                        <td>张主任</td>
                        <td>张主任</td>
                        <td>张主任</td>
                        <td>2018-9-1</td>
                        <td>2018-9-1</td>
                    </tr>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->
</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>
<script>
    $(function() {

        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next',
                center: 'title',
                right: 'month,agendaWeek'
            },
            today: [""],
            firstDay:1,
            editable: false,
            timeFormat: 'H:mm',
            axisFormat: 'H:mm',
            events: [
                {
                    title: '话剧',
                    start: new Date(y, m, 1),
                    color:'pink'
                },

                {
                    id: 999,
                    title: '语文 教室C',
                    start: new Date(y, m, d-6, 16, 0),
                    allDay: false,
                    color:'red'
                },
                {
                    id: 999,
                    title: '数学  教室A',
                    start: new Date(y, m, d+4, 16, 0),
                    allDay: false,
                    color:'blue'
                },
                {
                    title: '数学  教室A',
                    start: new Date(y, m, d, 10, 30),
                    allDay: false,
                    color:'blue'
                },
                {
                    title: '语文 教室C',
                    start: new Date(y, m, d, 11, 0),
                    end: new Date(y, m, d, 12, 0),
                    color:'red'
                },
                {
                    title: '英语 教室B',
                    start: new Date(y, m, d, 16, 0),
                    end: new Date(y, m, d, 18, 0),
                    allDay: false,
                    color:'orange'
                },
                {
                    title: '语文 教室C',
                    start: new Date(y, m, d+1, 19, 0),
                    end: new Date(y, m, d+1, 22, 30),
                    allDay: false,
                    color:'red'
                },

                {
                    title: '数学  教室A',
                    start: new Date(y, m, d+23),
                    color:'blue'
                }
            ]

        });

    });
</script>
</body>
</html>
