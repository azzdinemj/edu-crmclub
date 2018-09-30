<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Student registration <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

                        <!--  <button title="operations" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                             <span class="caret"></span>
                             <span class="sr-only"></span>
                         </button>
                         <ul class="dropdown-menu" role="menu">
                             <li><a href="" data-toggle="modal">Turn to a formal student</a></li>
                             <li><a href="" data-toggle="modal">Turn to a trainee</a></li>
                             <li><a href="" data-toggle="modal">Abolish transformation</a></li>
                             <li><a href="" data-toggle="modal">delete</a></li>
                             <li class="divider"></li>
                             <li><a href="" data-toggle="modal">Import</a></li>
                             <li><a href="" data-toggle="modal">export</a></li>
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
                                <i class="fa fa-calendar" ></i>
                                <input type="text" readonly title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">number :</label>
                                <input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                    <button type="button" onclick="query();" class="btn btn-newblue btn-sm search">search</button>
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
                                        <label class="control-label">SignUp grade :</label>
                                        <div class="btn-group">
                                            <select class="form-control strw" id="grade">
                                                <option value="">Please choose</option>
                                            <#if grade??>
                                                <#list grade as g>
                                                    <option value="${(g.pkSysDictValues)!}">${(g.caption)!}</option>
                                                </#list>
                                            </#if>
                                            </select>
                                        </div>
                                    </div>
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">Parent name :</label>-->
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
                                    <#--</div>-->
                                    <#--<div class="form-group">-->
                                        <#--<label class="control-label">School year :</label>-->
                                        <#--<div class="btn-group">-->
                                            <#--<select class="form-control strw">-->
                                                <#--<option>whole</option>-->
                                                <#--<option>first grade</option>-->
                                                <#--<option>second grade</option>-->
                                                <#--<option>Grade three</option>-->
                                                <#--<option>fourth grade</option>-->
                                                <#--<option></option>-->
                                            <#--</select>-->
                                        <#--</div>-->
                                    <#--</div>-->
                                </form>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="panel-body pd0">
                    <div class="table-responsive">
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">

                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

        </div><!-- contentpanel -->



<#include "../commons/footer.ftl" />
<script type="text/javascript">
    //    var columns = [
    //        {"sTitle":"编号","data" : "code","render": function (data, type, row){
    //            return '<a href="/student/studentReport/edit?pkStudentSignup='+row.pkStudentSignup+'" class="yellow" >'+data+'</a>';
    //        }},
    //        {"sTitle":"姓名","data" : "map.studentEntity.caption"} ,
    //        {"sTitle":"日期","data" : "date","render": renderDate},
    //        {"sTitle":"项目","data" : "map.sysdicEntity.caption"},
    //        {"sTitle":"年级","data" : "map.gradeEntity.caption"},
    //        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
    //        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
    //        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
    //        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
    //    ];
    $(document).ready(function() {
        query();
    } );
    function renderCaption(data, type, row) {
        var urlKey="${(urlKey)!""}";
        var hasClassInfo = "${(hasClassInfo)!""}";
        if (urlKey=="Audit"){
            return '<a href="/finance/studentSignupAudit/edit?pkStudentSignup='+row.pkStudentSignup+'" class="yellow" >'+data+'</a>';
        }else if (hasClassInfo=="true"){

            return '<a href="/student/oldstudentReport/edit?pkStudentSignup='+row.pkStudentSignup+'" class="yellow" >'+data+'</a>';
        }else {
            return '<a href="/student/studentReport/edit?pkStudentSignup='+row.pkStudentSignup+'" class="yellow" >'+data+'</a>';
        }
    }

    function query() {
        var urlKey='${(urlKey)!""}';
        var hasClassInfo = "${(hasClassInfo)!""}";
        var data  = { "code": $("#code").val(),"dateTime": $("#datas").val(),"grade":$("#grade").val(),"hasClassInfo":${hasClassInfo}};
        if (urlKey=="Audit"){
            init(GetTableColumn("studentReport"),"/finance/studentSignupAudit/queryByPaging",data,"pagingTable");
        }else if(hasClassInfo=="true"){

            init(GetTableColumn("oldstudentReport"),"/student/studentReport/queryByPaging",data,"pagingTable");
        }else {
            init(GetTableColumn("studentReport"),"/student/studentReport/queryByPaging",data,"pagingTable");
        }


    }

    function renderNone(data, type, row) {

        $(this).attr("style","display:none");
    }

    function del() {
        var id = $(".table-color-").attr("dataid");

        if (id == null) {
            alert("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/student/studentReport/delete",
                data: {"pkStudentSignup": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        alert(data.message)
                    }
                },
                error: function () {

                }
            });
        }


    }
</script>