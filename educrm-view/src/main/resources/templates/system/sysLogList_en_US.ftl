<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Operation log<span>...</span></h2>
                <#--<div class="breadcrumb-wrapper">-->
                    <#--<div class="btn-group fr title-btn">-->
                        <#--<a class="btn btn-sm btn-newblue"  data-toggle="modal" data-target="#modal" data-url="/classinfo/classTime/create">Newly build</a>-->
                        <#--<button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">-->
                        <#--<span class="caret"></span>-->
                        <#--<span class="sr-only"></span>-->
                    <#--</button>-->
                    <#--<ul class="dropdown-menu" role="menu">-->
                        <#--<li><a onclick="del()" data-toggle="modal">delete</a></li>-->
                    <#--</ul>-->
                    <#--</div>-->
                <#--</div>-->
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <!--High search-->
                        <form class="form-inline search white">
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Name :</label>-->
                                <#--<input type="text" title="" id="caption"  name="reservation"  class="form-control" value="" />-->
                            <#--</div>-->

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>search</span>
                               </div>-->
                        </form>
                    </div>
                </div>
                <#if syslog ??>
                    <div class="panel-body pd0">
                        <div class="table-responsive">
                            <table   class="table table-striped table-bordered" cellspacing="0">
                                <thead>
                                <tr>

                                    <th>Title</th>
                                    <th>Before change</th>
                                    <th>After change</th>
                                    <th>Change time</th>
                                    <th>Change person</th>

                                </tr>
                                </thead>
                                <tbody>

                                    <#list syslog as v>
                                    <tr >

                                        <td>
                                            <#switch v.oldField>
                                                <#case "pkProduct">
                                                    product
                                                    <#break>
                                                <#case "pkEmployee">
                                                    Teacher
                                                    <#break>
                                                <#case "pkStudent">
                                                    Student
                                                    <#break>
                                                <#case "pkClassRoom">
                                                    Classroom
                                                    <#break>
                                                <#case "startTime">
                                                    start time
                                                    <#break>
                                                <#case "endTime">
                                                    End time
                                                    <#break>
                                                <#case "notes">
                                                    Teacher feedback
                                                    <#break>
                                                <#case "notesEng">
                                                    Feedback translation
                                                    <#break>
                                                <#case "status">
                                                    state
                                                    <#break>
                                            </#switch>

                                        </td>
                                        <td>
                                            <#switch v.oldField>
                                	<#case "pkProduct">
                                            ${(v.map.oldvalue)!}
                                                <#break>
                                                <#case "pkEmployee">
                                                ${(v.map.oldvalue)!}
                                                    <#break>
                                                <#case "pkStudent">
                                                ${(v.map.oldvalue)!}
                                                    <#break>
                                                <#case "pkClassRoom">
                                                ${(v.map.oldvalue)!}
                                                    <#break>


                                                <#default>
                                                ${(v.oldFieldValue)!}
                                                    <#break>
                                            </#switch>

                                        </td>
                                        <td><#switch v.oldField>
                                	<#case "pkProduct">
                                        ${(v.map.newvalue)!}
                                            <#break>
                                            <#case "pkEmployee">
                                            ${(v.map.newvalue)!}
                                                <#break>
                                            <#case "pkStudent">
                                            ${(v.map.newvalue)!}
                                                <#break>
                                            <#case "pkClassRoom">
                                            ${(v.map.newvalue)!}
                                                <#break>

                                            <#default>
                                            ${(v.newFieldValue)!}
                                                <#break>
                                        </#switch>
                                        </td>
                                        <td>${(v.datetime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                        <td>
                                        ${(v.map.creatorEntity.caption)!}
                                        </td>

                                    </tr>
                                    </#list>

                                </tbody>
                            </table>
                        </div><!-- table-responsive -->
                    </div><!-- panel-body -->

                </#if>

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>
<script type="text/javascript">

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {"caption":$("#caption").val()};
//        init(columns,"/classinfo/classTime/queryByPaging",data);
    }
</script>