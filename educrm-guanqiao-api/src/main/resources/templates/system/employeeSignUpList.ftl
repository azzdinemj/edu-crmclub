<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>意向员工 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/system/employeeSignUp/create">新建意向员工</a>
                <a class="btn btn-sm btn-newblue" onclick="stuInterview()">面试</a>
                <a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>
                <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">删除</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <#--<div class="panel-btns">-->
                    <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                <#--</div><!-- panel-btns &ndash;&gt;-->
                <!--高显搜索-->
                <form class="form-inline search white">
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">入职日期 :</label>-->
                        <#--<input type="text" title="" name="reservation" id="datas" class="form-control form-input-time"-->
                               <#--value=""/>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="control-label">员工名称 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>

                    <#--<div class="form-group">-->
                        <#--<label class="control-label">岗位 :</label>-->
                        <#--<select id="selectjobid" class="selectpicker show-tick form-control"  data-live-search="true">-->
                            <#--<option value=''> 请选择</option>-->
                        <#--<#if jobPosts??>-->
                            <#--<#list jobPosts as j>-->
                                <#--<option value="${(j.pkSysDictValues)!}"-->
                                        <#--<#if employee.jobPost?? && employee.jobPost ==j.pkSysDictValues >selected="selected"</#if>>${(j.caption)!}</option>-->
                            <#--</#list>-->
                        <#--</#if>-->
                        <#--</select>-->
                        <#--<input type="hidden" title="" id="" name="reservation" class="form-control form-input-lg" value=""/>-->
                    <#--</div>-->

                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                       </div>-->
                </form>
                <!--隐藏搜索-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label>学部</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>全部</option>-->
                                        <#--<option>一部</option>-->
                                        <#--<option>二部</option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">家长姓名 :</label>-->
                                <#--<input type="text" title="" name="reservation" class="form-control form-input-lg"-->
                                       <#--value=""/>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">入学年级 :</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>全部</option>-->
                                        <#--<option>一年级</option>-->
                                        <#--<option>二年级</option>-->
                                        <#--<option>三年级</option>-->
                                        <#--<option>四年级</option>-->
                                        <#--<option></option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</form>-->
                    </div>

                </div>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->



<#include "../commons/footer.ftl"/>

<script>

//    var columns = [
//        {"sTitle":"员工编号","data":"code"},
//        {"sTitle":"员工名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/system/employeeSignUp/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"身份证号","data" : "idCard"},
//        {"sTitle":"性别","data" : "sex","render": function (data){
//            if (data==1){ return '男';}
//            else {return '女'; }
//        }},
////        {"sTitle":"民族","data" : "map.nationEntity.caption"},
//        {"sTitle":"岗位","data" : "map.jobPostEntity.caption"},
//        {"sTitle":"工作状态","data" : "isleave","render": function (data){
//            if (data==1){ return '在职';}
//            else {return '离职'; }
//        }},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"联系电话","data" : "mobilePhone"},
//        {"sTitle":"毕业院校","data" : "graduatedSchool"}
//    ];

    $(document).ready(function() {
        query();
    } );

    function  renderCaption(data, type, row){
        return '<a href="/system/employeeSignUp/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
    }

    function renderisleave (data){
        if (data==1){ return '在职';}
        else {return '离职'; }
    }

    function query() {
        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val()};
        //init(columns,"/system/employeeSignUp/queryByPaging",data,"pagingTable");
        init(GetTableColumn("employeeSignUp"), "/system/employeeSignUp/queryByPaging", data,"pagingTable");
    }

    //面试
    function stuInterview() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkEmployee;


        if (id == null) {
            Notify.danger("请先选择要面试的学员");
            return;
        }
        window.location.href = "/system/employeeInterview/create?pkStudent=" + id;

    }

    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkEmployee;

        if(id == null){
            Notify.danger("请选择一条数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/employeeSignUp/delete",
                data: {"pkEmployee": id},
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