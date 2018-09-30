<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Employee list <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/system/employee/create">Add staff</a>

                <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">delete</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <#--<div class="panel-btns">-->
                    <#--<a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>-->
                <#--</div><!-- panel-btns &ndash;&gt;-->
                <!--High search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">entry date :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-time"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">name :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">post :</label>
                        <select id="selectjobid" class="selectpicker show-tick form-control"  data-live-search="true">
                            <option value=''> Please choose</option>
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
                        <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                    </div>

                    <!--   <div class="form-group">
                           <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>search</span>
                       </div>-->
                </form>
                <!--Hide search-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label>Faculty</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>whole</option>-->
                                        <#--<option>One</option>-->
                                        <#--<option>Two</option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Parent name :</label>-->
                                <#--<input type="text" title="" name="reservation" class="form-control form-input-lg"-->
                                       <#--value=""/>-->
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
                        <#--<th>code</th>-->
                        <#--<th>Employee name</th>-->
                        <#--<th>ID number</th>-->
                        <#--<th>Sex</th>-->
                        <#--<th>Ethnic</th>-->
                        <#--<th>post</th>-->
                        <#--<th>state</th>-->
                        <#--<th>Contact number</th>-->
                        <#--<th >Graduated school</th>-->
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
                            <#--<#if v.isleave ?? && v.isleave ==1 >In service<#else >Quit</#if>-->
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
//        {"sTitle":"code","data":"code"},
//        {"sTitle":"Employee name","data" : "caption",
//            "render": function (data, type, row){
//                return '<a href="/system/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"ID number","data" : "idCard"},
//        {"sTitle":"Sex","data" : "sex","render": function (data){
//            if (data==1){ return '男';}
//            else {return '女'; }
//        }},
//        {"sTitle":"Ethnic","data" : "map.nationEntity.caption"},
//        {"sTitle":"post","data" : "map.jobPostEntity.caption"},
//        {"sTitle":"state","data" : "isleave","render": function (data){
//            if (data==1){ return 'In service';}
//            else {return 'Quit'; }
//        }},
//        {"sTitle":"Creation time","data" : "creationDate","render": renderDate},
//        {"sTitle":"Contact number","data" : "phone"},
//        {"sTitle":"Graduated school","data" : "graduatedSchool"}
//    ];

     $(document).ready(function() {
        query();
     });

     function renderCaption (data, type, row){
                return '<a href="/system/employee/edit?pkEmployee='+row.pkEmployee+'" class="yellow" >'+data+'</a>';
     }

     function renderisleave(data){
            if (data==1){ return 'In service';}
            else {return 'Quit'; }
     }


    function query() {
        var job = "";
        for(i=1;i<document.getElementById('selectjobid').length;i++){
            if(document.getElementById('selectjobid')[i].selected==true){
                job = i;
            }
        }
        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val(),"jobPost":job};
        //init(columns,"/system/employee/queryByPaging",data,"pagingTable");
        init(GetTableColumn("employee"), "/system/employee/queryByPaging", data,"pagingTable");
    }


    function del() {
        var id = $(".table-color-").attr("dataid");
        if(id == null){
            Notify.danger("Please select a piece of data");
            return;
        }
        var flag = confirm("Do you confirm the deletion？");
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