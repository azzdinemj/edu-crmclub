<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Student<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
            <#if isType?? && isType!=1>
                <a class="btn btn-sm btn-newblue" href="/student/studentSignup/create">Add</a>
                <a class="btn btn-sm btn-newblue" onclick="stuInterview()">Interview</a>
                <a class="btn btn-sm btn-newblue" onclick="stuReport()">sign</a>
                <a href="" class="btn btn-sm btn-newblue" onclick="del()">delete </a>
            </#if>
                <#if mentality ?? &&mentality ==1>
                    <a data-toggle="modal" data-target="#modal" id="mentalityid" onclick="mentality()" class="btn btn-sm btn-newblue" >Mental health</a>
                </#if>
            <#-- <button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                 <span class="caret"></span>
                 <span class="sr-only"></span>
             </button>
             <ul class="dropdown-menu" role="menu">
                 <li><a href="" data-toggle="modal" onclick="del()"> <@spring.message "delete"/> </a></li>
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
                        <label class="control-label">Student name :</label>
                        <input id="caption" type="text" title="" name="reservation" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <#if mentality ?? && mentality ==1>
                        <div class="form-group">
                            <label class="control-label">psychological counseling:</label>
                            <div class="form-group">
                                <select class="form-group" id="selectId">
                                    <option value="">Please choose</option>
                                    <option value="1">Have been consulted</option>
                                    <option value="0">Not consulted</option>
                                </select>
                            </div>
                        </div>
                    </#if>

                    <div class="form-group">
                        <label></label>
                        <div class="btn-group">
                            <button type="button" onclick="query();" class="btn btn-newblue btn-sm search">search</button>
                        </div>
                    </div>

                </form>
                <!--Hide search-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label>minority</label>
                                <div class="btn-group">
                                    <select class="form-control strw" id="isminority">
                                        <option value="">Please choose</option>
                                        <option value="0">是</option>
                                        <option value="1">否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Telephone :</label>
                                <input type="text" title="" id="phone" name="reservation"
                                       class="form-control form-input-lg" value=""/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">School year :</label>
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
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered min-table " cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->
</div><!-- contentpanel -->

<#include "../commons/footer.ftl" />
<script type="text/javascript">
    $(document).ready(function () {
        query();
    });
    function renderCaption(data, type, row) {
        if (row.istype == 0) {
            return '<a href="/student/studentSignup/edit?pkStudent=' + row.pkStudent + '" class="yellow" >' + data + '</a>';
        } else {
            if(${(mentality)!0} ==1 ){
                return '<a href="/individual/mentality/edit?pkStudent=' + row.pkStudent + '&mentality=1" class="yellow" >' + data + '</a>';
            }else {
                return '<a href="/student/student/edit?pkStudent=' + row.pkStudent + '" class="yellow" >' + data + '</a>';
            }
        }
    }
    function query() {
        var data = {
            "code": "",
            "caption": $("#caption").val(),
            "istype":${isType},
            "isminority": $("#isminority").val(),
            "grade": $("#grade").val(),
            "phone": $("#phone").val(),
            "mentality":$("#selectId").val()
        };
        init(GetTableColumn("student"), "/student/studentSignup/queryByPaging", data,"pagingTable");
    }
    function del() {
        var index = $(".table-color-").index();
//        alert(getTableSelectValue("pkStudent"));
//        alert(getTableSelectRow(3).pkStudent);
        var id = getTableSelectRow("pagingTable",index).pkStudent;

        if (id == null) {
            Notify.danger("Please select the data to delete first");
            return false;
        }

        var flag = confirm("Do you confirm the deletion？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/student/studentSignup/delete",
                data: {"pkStudent": id},
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

    //Interview
    function stuInterview() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;

        if (id == null) {
            Notify.danger("Please choose the students to interview first");
            return;
        }

        window.location.href = "/student/studentInterview/create?pkStudent=" + id;

    }
    //sign
    function stuReport() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;

        if (id == null) {
            Notify.danger("Please choose the students to sign up first");
            return;
        }

        window.location.href = "/student/studentReport/create?pkStudent=" + id;

    }
    //Mental health
    function mentality() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;
        if (id == null) {
            Notify.danger("Please choose the students first");
            return false;
        }

        $("#mentalityid").attr("data-url","/individual/mentality/create?pkStudent=" + id);

    }
</script>