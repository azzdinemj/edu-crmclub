<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>学生列表 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
            <#if isType?? && isType!=1>
                <a class="btn btn-sm btn-newblue" href="/student/studentSignup/create">新建意向学生</a>
                <a class="btn btn-sm btn-newblue" onclick="stuInterview()">面试</a>
                <a class="btn btn-sm btn-newblue" onclick="stuReport()">报名</a>
                <a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>
            </#if>
                <#if mentality ?? &&mentality ==1>
                    <a data-toggle="modal" data-target="#modal" id="mentalityid" onclick="mentality()" class="btn btn-sm btn-newblue" > 心里健康 </a>
                </#if>
            <#-- <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                 <span class="caret"></span>
                 <span class="sr-only"></span>
             </button>
             <ul class="dropdown-menu" role="menu">
                 <li><a href="" data-toggle="modal" onclick="del()"> 删除 </a></li>
             </ul>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <div class="panel-btns">
                    <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                </div><!-- panel-btns -->
                <!--高显搜索-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">学生姓名 :</label>
                        <input id="caption" type="text" title="" name="reservation" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <#if mentality ?? && mentality ==1>
                        <div class="form-group">
                            <label class="control-label">心里咨询 :</label>
                            <div class="form-group">
                                <select class="form-group" id="selectId">
                                    <option value="">请选择</option>
                                    <option value="1">已咨询</option>
                                    <option value="0">未咨询</option>
                                </select>
                            </div>
                        </div>
                    </#if>

                    <div class="form-group">
                        <label></label>
                        <div class="btn-group">
                            <button type="button" onclick="query();" class="btn btn-newblue btn-sm search">搜索</button>
                        </div>
                    </div>

                </form>
                <!--隐藏搜索-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label>少数民族</label>
                                <div class="btn-group">
                                    <select class="form-control strw" id="isminority">
                                        <option value="">请选择</option>
                                        <option value="0">是</option>
                                        <option value="1">否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">手机号 :</label>
                                <input type="text" title="" id="phone" name="reservation"
                                       class="form-control form-input-lg" value=""/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">入学年级 :</label>
                                <div class="btn-group">
                                    <select class="form-control strw" id="grade">
                                        <option value="">请选择</option>
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
            Notify.danger("请先选择要删除的数据");
            return false;
        }

        var flag = confirm("确认删除吗？");
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

    //面试
    function stuInterview() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;

        if (id == null) {
            Notify.danger("请先选择要面试的学员");
            return;
        }

        window.location.href = "/student/studentInterview/create?pkStudent=" + id;

    }
    //报名
    function stuReport() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;

        if (id == null) {
            Notify.danger("请先选择要报名的学员");
            return;
        }

        window.location.href = "/student/studentReport/create?pkStudent=" + id;

    }
    //心里健康
    function mentality() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;
        if (id == null) {
            Notify.danger("请先选择学生");
            return false;
        }

        $("#mentalityid").attr("data-url","/individual/mentality/create?pkStudent=" + id);

    }
</script>