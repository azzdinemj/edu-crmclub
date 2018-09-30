
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Coding rules<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                         <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal" data-url="/system/sysAutocode/create">Create coding</a>

                        <button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a onclick="del()" data-toggle="modal">delete</a></li>

                    </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <!--<div class="panel-btns">
                            <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                        </div>--><!-- panel-btns -->
                        <!--High search-->
                        <form class="form-inline search white">
                           <!-- <div class="form-group">
                                <label class="control-label">Date, :</label>
                                <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                            </div>-->
                            <div class="form-group">
                                <label class="control-label">Code name :</label>
                                <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <a  onclick="query()" class="btn btn-newblue btn-sm search">search</a>
                            </div>
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
                        <table id="pagingTable"  class="table table-striped table-bordered min-table" cellspacing="0">
                            <thead>
                            <#--<tr>-->

                                <#--<th>Rule coding</th>-->
                                <#--<th>Code name</th>-->
                                <#--<th>prefix</th>-->
                                <#--<th>Coding type</th>-->
                                <#--<th>Separation line</th>-->
                                <#--<th>digit</th>-->
                                <#--<th>sort</th>-->
                                <#--<th>Founder</th>-->
                                <#--<th>Creation time</th>-->
                                <#--<th>Modifier</th>-->
                                <#--<th>Modified</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#list list as v>-->
                            <#--<tr dataid="${(v.pkSysAutocode)!}">-->
                                <#--<td align="left">-->
                                    <#--<a class="yellow" data-toggle="modal" data-target="#modal" data-url="/system/sysAutocode/edit?pkSysAutocode=${(v.pkSysAutocode)!}">-->
                                    <#--${(v.pkSysAutocode)!}-->
                                    <#--</a>-->

                                <#--</td>-->
                                <#--<td align="left">${(v.caption)!}</td>-->
                                <#--<td align="left">${(v.prefix)!}</td>-->
                                <#--<td align="left">${(v.kind)!}</td>-->
                                <#--<td><#if v.isline?? && v.isline==1>Enable<#else >Disable</#if></td>-->
                                <#--<td align="right">${(v.zeroWidth)!}</td>-->
                                <#--<td align="right">${(v.sort)!}</td>-->
                                <#--<td align="left">${v.map.creatorEntity.caption}</td>-->
                                <#--<td align="left">${v.creationDate?string("yyyy-MM-dd HH:mm:ss")}</td>-->
                                <#--<td align="left">${v.map.modifierEntity.caption}</td>-->
                                <#--<td align="left">${v.lasteditDate?string("yyyy-MM-dd HH:mm:ss")}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->



                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->

    </div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">

    function renderCaption(data, type, row) {
        return '<a data-url="/system/sysAutocode/edit?pkSysAutocode='+row.pkSysAutocode+'" data-toggle="modal" data-target="#modal"  class="yellow" >'+data+'</a>';
    }


    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {"caption":$("input[name='reservation']").val()};
        init(GetTableColumn("sysAutocode"),"/system/sysAutocode/queryByPaging",data,"pagingTable");
    }

    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkSysAutocode;

        if(id ==null){
            Notify.danger("Please select the data to delete first");
            return;
        }

        var flag = confirm("Do you confirm the deletion？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/sysAutocode/delete",
                data: {"pkSysAutocode": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message)
                    }
                },
                error: function () {

                }
            });
        }

    }

</script>