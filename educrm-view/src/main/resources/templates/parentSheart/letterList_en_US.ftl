<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Parents' letter<span>...</span></h2>
    </div>
    <div class="smart-widget">
        <div class="smart-widget-inner">
            <ul class="nav nav-tabs tab-style1">
                <li class="active">
                    <a href="#style1Tab1" data-toggle="tab">
                        <span class="icon-wrapper"></i></span>
                        Message sending statistics
                    </a>
                </li>
                <li>
                    <a href="#style1Tab2" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        Parental reversion statistics
                    </a>
                </li>
                <li>
                    <a href="#style1Tab3" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        Loop ratio statistics
                    </a>
                </li>
                <li>
                    <a href="#style1Tab3" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        Message query
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
                                        <label class="control-label">Date,</label>
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
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>search</span>
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>Reset</span>
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
                                    <th>campus</th>
                                    <th>Sending times</th>
                                    <th>Class person</th>
                                    <th>Transmission ratio</th>

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
                                    <td>Rui Rui</td>
                                    <td>Hongkou</td>
                                    <td>Summer and spring2班</td>

                                </tr>
                                <tr class="odd">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="a2" class="inbox-check">
                                            <label for="a2"></label>
                                        </div>
                                    </td>
                                    <td>DS20170603</td>
                                    <td>Rui Rui</td>
                                    <td>Hongkou</td>
                                    <td>Summer and spring2班</td>

                                </tr>
                                <tr class="add">
                                    <td>
                                        <div class="custom-checkbox">
                                            <input type="checkbox" id="a3" class="inbox-check">
                                            <label for="a3"></label>
                                        </div>
                                    </td>
                                    <td>DS20170603</td>
                                    <td>Rui Rui</td>
                                    <td>Hongkou</td>
                                    <td>Summer and spring2班</td>

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
                                        <label>Children</label>
                                        <div class="btn-group">
                                            <input type="text" title="" class='form-control' placeholder="name/number">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>search</span>
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>Reset</span>
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
                                    <th >Student</th>
                                    <th >integral</th>
                                    <th >Integral source</th>
                                    <th>Reason</th>
                                    <th >time</th>
                                    <th  width="150px">operation</th>
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
                                    <td>Wang Luodan</td>
                                    <td>+105</td>
                                    <td>Students pay and pay for the bonus</td>
                                    <td>Single charge720元，加105分。</td>
                                    <td>2017-06-26 14:15:33</td>
                                    <td>
                                        <span class="btn btn-info btn-sm"><a href="#addNewEventModal-1" data-toggle="modal">modify</a> </span>
                                        <span class="btn btn-danger btn-sm"><a href="#addNewEventModal-4" data-toggle="modal">delete</a> </span>
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
                                    <th >Student</th>
                                    <th >integral</th>
                                    <th >Integral source</th>
                                    <th>Reason</th>
                                    <th >time</th>
                                    <th  width="150px">operation</th>
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
                                    <td>Wang Luodan</td>
                                    <td>+105</td>
                                    <td>Students pay and pay for the bonus</td>
                                    <td>Single charge720元，加105分。</td>
                                    <td>2017-06-26 14:15:33</td>
                                    <td>
                                        <span class="btn btn-info btn-sm"><a href="#addNewEventModal-1" data-toggle="modal">modify</a> </span>
                                        <span class="btn btn-danger btn-sm"><a href="#addNewEventModal-4" data-toggle="modal">delete</a> </span>
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
