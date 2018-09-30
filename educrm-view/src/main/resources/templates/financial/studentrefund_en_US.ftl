
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>refund <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

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

                        <div class="panel-btns">
                            <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                        </div><!-- panel-btns -->
                        <!--High search-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">Date, :</label>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">Student name :</label>
                                <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label>nationality</label>
                                <div class="btn-group">
                                    <select class="form-control">
                                        <option>whole</option>
                                        <option>China</option>
                                        <option>U.S.A</option>
                                        <option>Britain</option>
                                        <option>France</option>
                                        <option></option>
                                    </select>
                                </div>
                            </div>

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>search</span>
                               </div>-->
                        </form>
                        <!--Hide search-->
                        <div class="panel-body senior-search">
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

                        </div>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th width="250">operation</th>
                                <th width="200">Student</th>
                                <th width="200">item</th>
                                <th width="200">Agent</th>
                                <th width="250">code</th>
                                <th width="250">Date,</th>
                                <th width="250">Cost</th>
                                <th width="200">Receivables</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <!--The three buttons are not displayed at the same time。。In accordance with the order，The detail button appears after the end of the payment-->
                                    <a class="btn btn-danger btn-sm"  data-toggle="modal" data-target="#myModal" data-url="refund.html">
                                        Refund
                                    </a>

                                    <a class="hide btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">delete</a>

                                </td>
                                <td>Stamen</td>
                                <td>
                                    Tuition
                                </td>
                                <td>Mr. Zhang</td>
                                <td>245647544</td>
                                <td>2018-1-8</td>
                                <td>20万</td>
                                <td>20万</td>
                            </tr>
                            <tr>
                                <td>
                                    <a class="btn btn-danger btn-sm"  data-toggle="modal" data-target="#myModal" data-url="refund.html">
                                        Refund
                                    </a>

                                </td>
                                <td>Stamen</td>
                                <td>
                                    Tuition
                                </td>
                                <td>Mr. Zhang</td>
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