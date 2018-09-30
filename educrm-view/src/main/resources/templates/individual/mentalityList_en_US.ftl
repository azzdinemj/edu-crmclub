<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Psychological counselling <span>...</span></h2>
    </div>
    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <!--High search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">Date, :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Student name :</label>
                        <input type="text" title="" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>

                </form>

            </div>
        </div>
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Student number</th>
                        <th>Student name</th>
                        <th>class</th>
                        <th>grade</th>
                        <th>Founder</th>
                        <th>Creation time</th>
                        <th>Modifier</th>
                        <th>Modified</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td align="left">DS45788</td>
                        <td align="left">Stamen</td>
                        <td align="left">Class one or two</td>
                        <td align="left">fifth grade</td>
                        <td align="left">Stamen</td>
                        <td align="left">2018\1\8</td>
                        <td align="left">Stamen</td>
                        <td align="left">2018\1\8</td>

                    </tr>
                    <tr>
                        <td align="left">DS45788</td>
                        <td align="left">Stamen</td>
                        <td align="left">Class one or two</td>
                        <td align="left">fifth grade</td>
                        <td align="left">Stamen</td>
                        <td align="left">2018\1\8</td>
                        <td align="left">Stamen</td>
                        <td align="left">2018\1\8</td>

                    </tr>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>
