<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Schedule <span>...</span></h2>
    </div>
    <div class="panel panel-default">

        <div class="panel-heading">
            <div class="row">

                <form class="form-inline white">
                    <div class="form-group">
                        <label class="control-label">Date, :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <div class="btn-group  mr5">
                        <label class="checkbox-inline checkbox-styled">
                            <input value="4" type="checkbox"><span>Collective class</span>
                        </label>&nbsp;&nbsp;
                        <label class="checkbox-inline checkbox-styled">
                            <input value="1" type="checkbox"><span>One-on-one</span>
                        </label>&nbsp;&nbsp;
                        <label class="checkbox-inline checkbox-styled">
                            <input value="2" type="checkbox"><span>One to many</span>
                        </label><div style="display: inline-block;margin: 0 12px;padding: 0 12px;border-left: 1px solid #e0e0e0;border-right: 1px solid #e0e0e0;">
                        <label class="checkbox-inline checkbox-styled">
                            <input value="1" type="checkbox"><span>Enable</span>
                        </label>&nbsp;&nbsp;
                        <label class="checkbox-inline checkbox-styled">
                            <input value="0" type="checkbox"><span>Disabled</span>
                        </label>
                    </div><label class="checkbox-inline checkbox-styled" id="ShiftGrantCB" config="EnableShiftGrant">
                        <input type="checkbox"><span>Just look at the course of the current campus</span>
                    </label>
                    </div>
                    <br><br>



                </form>

            </div>
            <div class="row">
                <div class="btn-group  title-btn">
                    <a class="btn btn-sm btn-info" href="">Collective shift</a>
                    <a class="btn btn-sm btn-success"  href=""> One to one shift </a>
                    <a class="btn btn-sm btn-danger"  href=""> One to many shift </a>
                </div>
            </div>
        </div>
        <div class="panel-body new-panel-body">
            <div class="table-responsive">
                <div id="table1_wrapper" class="dataTables_wrapper no-footer"><div class="dataTables_length" id="table1_length"><label>Show <select name="table1_length" aria-controls="table1" class=""><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div><div id="table1_filter" class="dataTables_filter"><label>Search:<input class="" placeholder="" aria-controls="table1" type="search"></label></div><table class="table sel-first dataTable no-footer" id="table1" role="grid" aria-describedby="table1_info">
                    <thead>
                    <tr class="table-header" role="row"><th class="sorting_asc" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 48px;" aria-sort="ascending" aria-label=": activate to sort column ascending" width="60"><input id="chkAll" type="checkbox"></th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 152px;" aria-label="Course name: activate to sort column ascending" width="150">Course name</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="Unit Price: activate to sort column ascending" width="80">Unit Price</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="Company: activate to sort column ascending" width="80">Company</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="grade: activate to sort column ascending" width="80">grade</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="Subject: activate to sort column ascending" width="80">Subject</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="type: activate to sort column ascending" width="80">type</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="Class type: activate to sort column ascending" width="80">Class type</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="One-on-one: activate to sort column ascending" width="80">One-on-one</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="Fee schedule: activate to sort column ascending" width="80">Fee schedule</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="deactivated: activate to sort column ascending" width="80">deactivated</th><th class="sorting" tabindex="0" aria-controls="table1" rowspan="1" colspan="1" style="width: 71px;" aria-label="Authorized: activate to sort column ascending" width="80">Authorized</th></tr>
                    </thead>

                    <tbody>




                    <tr class="gradeX odd" role="row">
                        <td class="sorting_1"><input class="px" type="checkbox"></td>
                        <td><a title="modify" href="#add" data-toggle="modal">002Technology and Life</a></td>
                        <td>1600</td>
                        <td>期</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>20次/期</td>
                        <td>Disabled</td>
                        <td><a href="#" data-toggle="modal">2campus</a> </td>
                    </tr><tr class="gradeX even" role="row">
                        <td class="sorting_1"><input class="px" type="checkbox"></td>
                        <td><a title="modify" href="#add" data-toggle="modal">002Technology and Life</a></td>
                        <td>1600</td>
                        <td>期</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>20次/期</td>
                        <td>Disabled</td>
                        <td><a href="#" data-toggle="modal">2campus</a> </td>
                    </tr><tr class="gradeX odd" role="row">
                        <td class="sorting_1"><input class="px" type="checkbox"></td>
                        <td><a title="modify" href="#add" data-toggle="modal">002Technology and Life</a></td>
                        <td>1600</td>
                        <td>期</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>20次/期</td>
                        <td>Disabled</td>
                        <td><a href="#" data-toggle="modal">2campus</a> </td>
                    </tr><tr class="gradeX even" role="row">
                        <td class="sorting_1"><input class="px" type="checkbox"></td>
                        <td><a title="modify" href="#add" data-toggle="modal">002Technology and Life</a></td>
                        <td>1600</td>
                        <td>期</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>20次/期</td>
                        <td>Disabled</td>
                        <td><a href="#" data-toggle="modal">2campus</a> </td>
                    </tr></tbody>
                </table><div class="dataTables_info" id="table1_info" role="status" aria-live="polite">Showing 1 to 4 of 4 entries</div><div class="dataTables_paginate paging_simple_numbers" id="table1_paginate"><a class="paginate_button previous disabled" aria-controls="table1" data-dt-idx="0" tabindex="0" id="table1_previous">Previous</a><span><a class="paginate_button current" aria-controls="table1" data-dt-idx="1" tabindex="0">1</a></span><a class="paginate_button next disabled" aria-controls="table1" data-dt-idx="2" tabindex="0" id="table1_next">Next</a></div></div>

            </div><!-- table-responsive -->
            <div class="clearfix mb30"></div>

        </div><!-- panel-body -->
    </div>

</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>
