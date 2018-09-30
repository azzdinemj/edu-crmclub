
<#include "../commons/top.ftl"/>
<#include "../commons/left.ftl"/>

<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>Operation log <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <span class="label">Your position:</span>
        <ol class="breadcrumb">
            <li><a href="../index.html">Return</a></li>
            <li class="active">Operation log</li>
        </ol>
    </div>
</div>
<div class="contentpanel">

    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <form id="searchForm" action="/log/search" method="GET" class="form-inline search ">
                    <div class="form-group">
                        <label>nickname</label>
                        <div class="btn-group">
                            <input class="form-control" name="loginName" value="${searchName!}" title="" placeholder="Please enter the query content">
                        </div>
                    </div>
                    <div class="form-group">
                        <span id="searchbtn" class="btn btn-info btn-sm add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>search</span>
                    </div>
                </form>


            </div>

        </div>
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="30">
                            <input type="checkbox" id="chAll">
                        </th>

                        <th>number</th>
                        <th>User name</th>
                        <th>Subordinate department</th>
                        <th>Real name</th>
                        <th>operation</th>
                        <th>Operation time</th>



                    </tr>
                    </thead>
                    <tbody>

                   <#-- <#list list as list>-->
                    <tr>
                        <td>
                            <input type="checkbox" class="px">
                        </td>


                        <td>1</td>

                        <td>admin</td>
                        <td>headquarters</td>
                        <td>headquarters</td>
                        <td>Administrators</td>
                        <td>2018-01-7</td>

                    </tr>
                   <#-- </#list>-->
                    </tbody>
                </table>

            </div><!-- table-responsive -->
        </div><!-- panel-body -->
    </div><!-- panel -->

</div><!-- contentpanel -->
</div>


<#include "../commons/footer.ftl"/>
<script type="text/javascript">
    $("#searchbtn").click(function(){
        $("#searchForm").submit();
    });
</script>