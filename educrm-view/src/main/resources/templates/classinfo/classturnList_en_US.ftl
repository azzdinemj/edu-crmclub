
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Student shift <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <!--data-toggle="modal" data-target="#myModal" data-url=""-->
                        <a class="btn btn-sm btn-success" >Student shift</a>
                        <a class="btn btn-sm btn-danger" >delete</a>

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
                                <label class="control-label">Class name :</label>
                                <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
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
                                <th><input type="checkbox" id="chAll"></th>
                                <th width="200">Student number</th>
                                <th width="200">Student name</th>
                                <th width="250">Academic Director</th>
                                <th width="250">Headmaster</th>
                                <th width="250">second teacher</th>
                                <th width="200">grade</th>

                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox" class="px"></td>
                                <td>
                                    <a class="success" href="/classinfo/classreturn/edit">
                                        LM123456
                                    </a>
                                </td>
                                <td>A班</td>
                                <td>Director Zhang</td>
                                <td>Mr. Wang</td>
                                <td>Miss Li</td>
                                <td>second grade</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" class="px"></td>
                                <td>
                                    <a class="success" href="class-modal/look-class.html">
                                        LM123456
                                    </a>
                                </td>
                                <td>A班</td>
                                <td>Director Zhang</td>
                                <td>Mr. Wang</td>
                                <td>Miss Li</td>
                                <td>second grade</td>
                            </tr>
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>