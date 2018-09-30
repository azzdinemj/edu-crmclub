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
        <h2><i class="fa fa-bookmark"></i>Student score <span>...</span></h2>
        <div class="text-right text-left-sm">
            <a href="#addNewEventModal-1" class="btn btn-info add-new-event" data-toggle="modal">Batch bonus</a>
            <a href="#addNewEventModal-1" class="btn btn-danger add-new-event" data-toggle="modal">Mass reduction</a>
            <a href="#" class="btn btn-info">export</a>
        </div>
    </div>
    <div class="smart-widget">
        <div class="smart-widget-inner">
            <ul class="nav nav-tabs tab-style1">
                <li class="active">
                    <a href="#style1Tab1" data-toggle="tab">
                        <span class="icon-wrapper"></i></span>
                        Integral management
                    </a>
                </li>
                <li>
                    <a href="#style1Tab2" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        Integral detail
                    </a>
                </li>
                <li>
                    <a href="#style1Tab3" data-toggle="tab">
                        <span class="icon-wrapper"></span>
                        Integral rule
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
                                        <label>Children</label>
                                        <div class="btn-group">
                                            <input type="text" title="" class='form-control' placeholder="name/number">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <span class="btn btn-info add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>search</span>
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
                                    <th>number</th>
                                    <th>name</th>
                                    <th>core</th>
                                    <th>class</th>
                                    <th>Teacher</th>
                                    <th>Class time/Total class</th>
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
                                    <td>Summer and spring</td>
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
                                    <td>Rui Rui</td>
                                    <td>Hongkou</td>
                                    <td>Summer and spring2班</td>
                                    <td>Summer and spring</td>
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
                                    <td>Rui Rui</td>
                                    <td>Hongkou</td>
                                    <td>Summer and spring2班</td>
                                    <td>Summer and spring</td>
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
                        <div class="form-group row">
                            <div class="big">
                                <span>source：</span>
                            </div>
                            <ul class="col-sm-11" id="pointDetail_ly">
                                <li class="pointDetail_ruleName_span">
                                    <input type="checkbox" id="all-attendance" class="attendance">
                                    <span>Unlimited</span>
                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance">
                                    <span>Students pay and pay for the bonus</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>Student class elimination</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >

                                    <input type="checkbox"  class="attendance"><span>Students' attendance in class</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>Students get good ratings</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>Students' parents appraise teacher's bonus</span>

                            </li>
                                <li class="pointDetail_ruleName_span" >
                                    <input type="checkbox"  class="attendance"><span>Good people and good deeds11</span>

                                </li>

                               <#-- <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select7" class="attendance">
                                        <label for="select7"></label>
                                    </div><span>Job performance</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select8" class="attendance">
                                        <label for="select8"></label>
                                    </div><span>Parents have enough for each evaluation5Teacher，加10分</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select9" class="attendance">
                                        <label for="select9"></label>
                                    </div><span>Recommending new students</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select10" class="attendance">
                                        <label for="select10"></label>
                                    </div><span>Answer a question in class</span>

                                </li>

                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select11" class="attendance">
                                        <label for="select11"></label>
                                    </div><span>renew</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select12" class="attendance">
                                        <label for="select12"></label>
                                    </div><span>Introduction</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select13" class="attendance">
                                        <label for="select13"></label>
                                    </div><span>Deductions of tuition fees for students when they pay their fees</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select14" class="attendance">
                                        <label for="select14"></label>
                                    </div><span>Absenteeism</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select15" class="attendance">
                                        <label for="select15"></label>
                                    </div> <span>Student refund and reduction</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select16" class="attendance">
                                        <label for="select16"></label>
                                    </div><span>Students get poor evaluation and subtraction</span>

                                </li>
                                <li class="pointDetail_ruleName_span" >
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select17" class="attendance">
                                        <label for="select17"></label>
                                    </div><span>Registration fee</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select18" class="attendance">
                                        <label for="select18"></label>
                                    </div><span>Plush toys</span>

                                </li>
                                <li class="pointDetail_ruleName_span">
                                    <div class="custom-checkbox">
                                        <input type="checkbox" id="select19" class="attendance">
                                        <label for="select19"></label>
                                    </div><span>Convertible toy car</span>

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
                        <div class="row">
                            <div class="card-body col-sm-6">
                                <div class="add-integral">
                                    <a href="#addNewEventModal-2" data-toggle="modal" class="btn btn-info btn-sm">
                                        add
                                    </a>
                                </div>
                                <div class="message-table table-responsive">
                                    <table class="table">
                                        <thead>
                                        <tr class="top-tr">
                                            <th>Rule name</th>
                                            <th>rule</th>
                                            <th>Remarks</th>
                                            <th>operation</th>
                                        </tr>
                                        </thead>
                                        <tbody class="">
                                        <tr>
                                            <td>Careful writing</td>
                                            <td>加120分。</td>
                                            <td>Attend an international training camp，Available120Minute/One phase</td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        delete
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Careful writing</td>
                                            <td>加120分。</td>
                                            <td>Attend an international training camp，Available120Minute/One phase</td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        delete
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
                                        New rule of subtraction
                                    </a>
                                </div>
                                <div class="message-table table-responsive">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>Rule name</th>
                                            <th>rule</th>
                                            <th>Remarks</th>
                                            <th>operation</th>
                                        </tr>
                                        </thead>
                                        <tbody class="">
                                        <tr>
                                            <td>Exchange the umbrella of the six art umbrellas</td>
                                            <td title="减50分。">减50分。</td>
                                            <td></td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        delete
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Plush toys</td>
                                            <td title="减20分。">减20分。</td>
                                            <td></td>
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        delete
                                                                    </a>
                                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Late every time</td>
                                            <td title="减10分。">减10分。</td>
                                            <td></td>
                                            <!--ms-if-->
                                            <td>
                                                                <span  class="btn btn-info btn-sm">
                                                                    <a href="#addNewEventModal-4" data-toggle="modal">
                                                                        delete
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
