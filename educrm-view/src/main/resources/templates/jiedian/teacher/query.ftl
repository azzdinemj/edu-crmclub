<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>员工资料 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#if urlkey ?? && urlkey==1>
				<a class="btn btn-sm btn-newblue" onclick="stuSchedule()">排课</a>
                <#else >
                    <a class="btn btn-sm btn-newblue" href="/jiedian/employee/create">新建</a>
                    <a class="btn btn-sm btn-newblue" onclick="del()" >删除</a>
                </#if>

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
                        <label class="control-label">入职日期 :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-time"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">员工名称 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
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



<#include "../../commons/footer.ftl"/>

<script>

//    var columns = [
//        {"sTitle":"员工编号","data":"code"},
//        {"sTitle":"员工名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/jiedian/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"身份证号","data" : "idCard"},
//        {"sTitle":"性别","data" : "sex","render": function (data){
//            if (data==1){ return '男';}
//            else {return '女'; }
//        }},
//        {"sTitle":"民族","data" : "map.nationEntity.caption"},
//        {"sTitle":"岗位","data" : "map.jobPostEntity.caption"},
//        {"sTitle":"状态","data" : "isleave","render": function (data){
//            if (data==1){ return '在职';}
//            else {return '离职'; }
//        }},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"联系电话","data" : "phone"},
//        {"sTitle":"毕业院校","data" : "graduatedSchool"}
//    ];

    function renderCaption (data, type, row){
        return '<a href="/jiedian/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
    }

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val()};
        init(GetTableColumn("employee"),"/jiedian/employee/queryByPaging",data,"pagingTable");
    }

    //排课
    function stuSchedule() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkEmployee;

        if (id == null) {
            Notify.danger("请先选择要排课的老师");
            return;
        }

        window.location.href = "/jiedian/schedule/query?pkEmployee=" + id;

    }
    function del() {
        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkEmployee

        if(id == null){
            Notify.danger("请选择一条数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/employee/delete",
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