
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />


<div class="contentpanel lg-height">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>schedule<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal" data-url="/classinfo/curriulum/create">New curriculum</a>
                <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">delete</a>
                <!--     <button title="operations" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
                <span class="sr-only"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="" data-toggle="modal">delete</a></li>
                <li class="divider"></li>
            </ul>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <!--  <div class="panel-btns">
                      <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                  </div>&lt;!&ndash; panel-btns &ndash;&gt;-->
                <!--High search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">Date, :</label>
                        <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Class name :</label>
                        <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                    </div>

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>search</span>
                       </div>-->
                </form>
                <!--Hide search-->
                <!-- <div class="panel-body senior-search">
                     <div id="post-status" class="tab-pane active">
                         <form class="form-inline search white">
                             <div class="form-group">
                                 <label>Faculty</label>
                                 <div class="btn-group">
                                     <select class="form-control">
                                         <option>whole</option>
                                         <option>One</option>
                                         <option>Two</option>
                                     </select>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">Parent name :</label>
                                 <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                             </div>
                             <div class="form-group">
                                 <label class="control-label">School year :</label>
                                 <div class="btn-group">
                                     <select class="form-control">
                                         <option>whole</option>
                                         <option>first grade</option>
                                         <option>second grade</option>
                                         <option>Grade three</option>
                                         <option>fourth grade</option>
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
                        <th width="250">Class name</th>
                        <th width="300">grade</th>
                        <th width="200">Classroom</th>
                        <th width="200">Academic Director</th>
                        <th width="200">Headmaster</th>
                        <th width="200">second teacher</th>
                        <th width="200">start time</th>
                        <th width="200">End time</th>
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
                        <td>second grade</td>
                        <td>Classroom</td>
                        <td>Director Zhang</td>
                        <td>Director Zhang</td>
                        <td>Director Zhang</td>
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
                        <td>second grade</td>
                        <td>Classroom</td>
                        <td>Director Zhang</td>
                        <td>Director Zhang</td>
                        <td>Director Zhang</td>
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
                    title: 'Modern drama',
                    start: new Date(y, m, 1),
                    color:'pink'
                },

                {
                    id: 999,
                    title: 'Chinese ClassroomC',
                    start: new Date(y, m, d-6, 16, 0),
                    allDay: false,
                    color:'red'
                },
                {
                    id: 999,
                    title: 'Mathematics  ClassroomA',
                    start: new Date(y, m, d+4, 16, 0),
                    allDay: false,
                    color:'blue'
                },
                {
                    title: 'Mathematics  ClassroomA',
                    start: new Date(y, m, d, 10, 30),
                    allDay: false,
                    color:'blue'
                },
                {
                    title: 'Chinese ClassroomC',
                    start: new Date(y, m, d, 11, 0),
                    end: new Date(y, m, d, 12, 0),
                    color:'red'
                },
                {
                    title: 'English ClassroomB',
                    start: new Date(y, m, d, 16, 0),
                    end: new Date(y, m, d, 18, 0),
                    allDay: false,
                    color:'orange'
                },
                {
                    title: 'Chinese ClassroomC',
                    start: new Date(y, m, d+1, 19, 0),
                    end: new Date(y, m, d+1, 22, 30),
                    allDay: false,
                    color:'red'
                },

                {
                    title: 'Mathematics  ClassroomA',
                    start: new Date(y, m, d+23),
                    color:'blue'
                }
            ]

        });

    });
</script>
</body>
</html>
