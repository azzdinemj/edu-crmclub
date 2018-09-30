<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Individualized management <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onclick="javascript:history.back(-1);">Return</a>
                <a class="btn btn-sm btn-newblue" onclick="sub()">Preservation</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body pd0">
            <ul class="nav nav-tabs nav-success">

                <li class="active"><a data-toggle="tab" href="#class1"><strong>Elective curriculum</strong></a></li>
                <li class=""><a data-toggle="tab" href="#class2"><strong>Student organizations</strong></a></li>
                <li class=""><a data-toggle="tab" href="#class3"><strong>Interest class</strong></a></li>

            </ul>
            <div class="tab-content">
                <div id="class1" class="tab-pane active">
                    <div class="table-responsive">
                        <table id="table"  class="newstable table  table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Course name</th>
                                <th>start time</th>
                                <th>End time</th>
                                <th>Class teacher</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" class="px"  name="checked"></td>

                                <td align="left">Music</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">Wen-Ya Chang</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" class="px"  name="checked"></td>
                                <td align="left">Music</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">Wen-Ya Chang</td>

                            </tr>
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div>
                <div id="class2" class="tab-pane">
                    <div class="table-responsive">
                        <table id="table2"  class="newstable table  table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Course name</th>
                                <th>start time</th>
                                <th>End time</th>
                                <th>Class teacher</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" class="px"  name="checked"></td>

                                <td align="left">Music</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">Wen-Ya Chang</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" class="px"  name="checked"></td>
                                <td align="left">Music</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">Wen-Ya Chang</td>

                            </tr>
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div>
                <div id="class3" class="tab-pane">
                    <div class="table-responsive">
                        <table id="table3"  class="newstable table  table-bordereds" cellspacing="0">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Course name</th>
                                <th>start time</th>
                                <th>End time</th>
                                <th>Class teacher</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" class="px"  name="checked"></td>

                                <td align="left">Music</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">Wen-Ya Chang</td>

                            </tr>
                            <tr>
                                <td><input type="checkbox" class="px"  name="checked"></td>
                                <td align="left">Music</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">2018\1\8</td>
                                <td align="left">Wen-Ya Chang</td>

                            </tr>
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- tab-pane -->
            </div>
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>
