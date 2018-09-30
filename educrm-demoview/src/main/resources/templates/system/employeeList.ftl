<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i><@spring.message "personnel.empemployee"/> <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/system/employee/create"><@spring.message "entire.add"/></a>

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
                    <div class="form-group">
                        <label class="control-label"><@spring.message "personnel.empJoinedDate"/> :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-time"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label"><@spring.message "personnel.empempname"/> :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label"><@spring.message "personnel.empempstation"/> :</label>
                        <select id="selectjobid" class="selectpicker show-tick form-control"  data-live-search="true">
                            <option value=''> 请选择</option>
                        <#if jobPosts??>
                            <#list jobPosts as j>
                                <option value="${(j.pkSysDictValues)!}"
                                        <#if employee.jobPost?? && employee.jobPost ==j.pkSysDictValues >selected="selected"</#if>>${(j.caption)!}</option>
                            </#list>
                        </#if>
                        </select>
                        <input type="hidden" title="" id="" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()"><@spring.message "entire.search"/></a>
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
                    <#--<tr>-->
                       <#--&lt;#&ndash; <th><input type="checkbox" id="chAll"></th>&ndash;&gt;-->
                        <#--<th>员工编号</th>-->
                        <#--<th>员工名称</th>-->
                        <#--<th>身份证号</th>-->
                        <#--<th>性别</th>-->
                        <#--<th>民族</th>-->
                        <#--<th>岗位</th>-->
                        <#--<th>状态</th>-->
                        <#--<th>联系电话</th>-->
                        <#--<th >毕业院校</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#list list as v>-->
                    <#--<tr dataid="${(v.pkEmployee)!}">-->
                       <#--&lt;#&ndash; <td><input type="checkbox" class="px"></td>&ndash;&gt;-->
                        <#--<td>-->

                            <#--${(v.code)!}-->

                        <#--</td>-->
                        <#--<td>-->
                            <#--<a class="yellow" href="/system/employee/edit?pkEmployee=${(v.pkEmployee)!}">-->
                                <#--${(v.caption)!}-->
                            <#--</a>-->
                        <#--</td>-->
                        <#--<td>${(v.idCard)!}</td>-->
                        <#--<td>${(v.sex)!}</td>-->
                        <#--<td>${(v.nation)!}</td>-->
                        <#--<td>${(v.jobPost)!}</td>-->
                        <#--<td >-->
                            <#--<#if v.isleave ?? && v.isleave ==1 >在职<#else >离职</#if>-->
                        <#--${(v.isleave)!}-->
                        <#--</td>-->
                        <#--<td>${(v.mobilePhone)!}</td>-->
                        <#--<td>${(v.graduatedSchool)!}</td>-->
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

<script>

//    var columns = [
//        {"sTitle":"员工编号","data":"code"},
//        {"sTitle":"员工名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/system/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
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

     $(document).ready(function() {
        query();
     });

     function renderCaption (data, type, row){
                return '<a href="/system/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
     }

     function renderisleave(data){
            if (data==1){ return '在职';}
            else {return '离职'; }
     }


    function query() {
        var job = "";
        for(i=1;i<document.getElementById('selectjobid').length;i++){
            if(document.getElementById('selectjobid')[i].selected==true){
                job = i;
            }
        }
        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val(),"jobPost":job};
        //init(columns,"/system/employee/queryByPaging",data,"pagingTable");
        init(GetTableColumn("employee"), "/system/employee/queryByPaging", data,"pagingTable");
    }


    function del() {
        var id = $(".table-color-").attr("dataid");
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