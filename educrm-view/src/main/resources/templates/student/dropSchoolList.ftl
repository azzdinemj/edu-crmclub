<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>学生列表<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" id="dropSchoolId" onclick="dropSchool()">退学</a>
            <#--<#if isType?? && isType!=1>-->
                <#--<a class="btn btn-sm btn-newblue" href="/student/studentSignup/create">添加</a>-->
                <#--<a class="btn btn-sm btn-newblue" onclick="stuInterview()">面试</a>-->
                <#--<a class="btn btn-sm btn-newblue" onclick="stuReport()">报名</a>-->
                <#--<a href="" class="btn btn-sm btn-newblue" onclick="del()">删除 </a>-->
            <#--</#if>-->
                <#--<#if mentality ?? &&mentality ==1>-->
                    <#--<a data-toggle="modal" data-target="#modal" id="mentalityid" onclick="mentality()" class="btn btn-sm btn-newblue" >心理健康</a>-->
                <#--</#if>-->
            <#-- <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
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
                    <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                </div><!-- panel-btns -->
                <!--高显搜索-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">学生姓名 :</label>
                        <input id="caption" type="text" title="" name="reservation" class="form-control form-input-lg"
                               value=""/>
                    </div>


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
                                <label class="control-label">电话 :</label>
                                <input type="text" title="" id="phone" name="reservation"
                                       class="form-control form-input-lg" value=""/>
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

        return '<a href="/student/student/edit?pkStudent=' + row.pkStudent + '" class="yellow" >' + data + '</a>';

    }
    function query() {
        var data = {
            "caption": $("#caption").val(),
            "istype":1,
            "phone": $("#phone").val(),
        };

        init(GetTableColumn("student"), "/student/studentSignup/queryByPaging", data,"pagingTable");


    }



    //退学
    function dropSchool() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;
        var isType = getTableSelectRow("pagingTable",index).istype;
        if (isType==3 ){
            Notify.danger("该学生以退学");
            return;
        }
        if (isType==4 ){
            Notify.danger("该学生以申请退学");
            return;
        }
        if (id == null) {
            Notify.danger("请先选择要退学的学员");
            return;
        }



        if (confirm("确定要退学吗？")){

//            $("#dropSchoolId").attr("data-toggle","modal");
//            $("#dropSchoolId").attr("data-target","#modal");
//            data-toggle="modal" data-target="#modal";
            window.location.href="/student/dropSchool/create?pkStudent=" + id;


        }

    }

</script>