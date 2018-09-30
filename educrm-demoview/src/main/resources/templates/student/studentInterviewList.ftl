<#include "../commons/top.ftl" >
<#include "../commons/left.ftl" >

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>新生面试 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

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
                            <div class="form-group demo">
                                <label class="control-label">面试日期 :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" title="" readonly name="reservation" id="datas" class="form-control form-input-time" value="" />

                            </div>
                            <div class="form-group">
                                <label class="control-label">项目 :</label>
                                <input type="text" title="" id="caption" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <button onclick="query();" class="btn btn-newblue btn-sm search">搜索</button>
                            </div>
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
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
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
                        <table id="pagingTable"  class="table table-striped table-bordered lg-table" cellspacing="0">
                            <thead>
                            <#--<tr>-->
                               <#--&lt;#&ndash; <th width="30">-->
                                    <#--<input type="checkbox" id="chAll">-->
                                <#--</th>-->
                                <#--<th width="200">是否通过</th>&ndash;&gt;-->
                                <#--<th width="100">学生</th>-->
                                <#--<th width="150">面试老师</th>-->
                                <#--<th width="100">面试时间</th>-->
                                <#--<th width="150">记录编号</th>-->
                                <#--<th width="250">面试主题</th>-->
                                <#--<th width="250">面试情况</th>-->
                                <#--<th width="250">备注</th>-->
                                <#--<th width="200">是否审核</th>-->
                                <#--<th width="100">审核人</th>-->
                                <#--<th width="200">审核时间</th>-->
                                <#--<th width="200">创建人</th>-->
                                <#--<th width="200">创建日期</th>-->
                                <#--<th width="200">最后修改人</th>-->
                                <#--<th width="150">最后修改日期</th>-->

                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if student??>-->
                            <#--<#list student as v>-->
                            <#--<tr dataid="${(v.pkStudentInterview)!}">-->
                               <#--&lt;#&ndash; <td>-->
                                    <#--<input type="checkbox" class="px">-->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<a href="" class="btn btn-success btn-sm">-->
                                        <#--是-->
                                    <#--</a>-->
                                    <#--<a href="" class="btn btn-danger btn-sm">-->
                                        <#--否-->
                                    <#--</a>-->
                                <#--</td>&ndash;&gt;-->
                                <#--<td>-->
                                    <#--<a class="yellow" href="/student/studentInterview/edit?pkStudentInterview=${(v.pkStudentInterview)!}">-->
                                        <#--${(v.studentEntity.caption)!}-->
                                    <#--</a>-->
                                <#--</td>-->
                                <#--<td>${(v.pkEmployee)!}</td>-->
                                <#--<td>${(v.date ? string("yyyy-MM-dd"))!}</td>-->
                                <#--<td>${(v.code)!}</td>-->
                                <#--<td>${(v.caption)!}</td>-->
                                <#--<td>${(v.remark)!}</td>-->
                                <#--<td>${(v.memo)!}</td>-->
                                <#--<td>${(v.isaudit)!}</td>-->
                                <#--<td>${(v.auditor)!}</td>-->
                                <#--<td>${(v.auditDate ? string("yyyy-MM-dd"))!}</td>-->
                                <#--<td>${(v.creator)!}</td>-->
                                <#--<td>${(v.creationDate ? string("yyyy-MM-dd"))!}</td>-->
                                <#--<td>${(v.modifier)!}</td>-->
                                <#--<td>${(v.lasteditDate ? string("yyyy-MM-dd"))!}</td>-->
                            <#--</tr>-->
                            <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                    </div><!-- table-responsive -->
                </div><!-- panel-body -->


            </div><!-- panel -->

        </div><!-- contentpanel -->




<#include "../commons/footer.ftl" />
<script type="text/javascript">

//    var columns=[
//        {"sTitle":"姓名","data" : "map.studentEntity.caption",
//            "render": function (data, type, row){
//                    return '<a href="/student/studentInterview/edit?pkStudentInterview='+row.pkStudentInterview+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"面试老师","data" : "map.userEntity.caption"},
//        {"sTitle":"面试时间","data" : "date","render": renderDate},
//        {"sTitle":"记录编号","data" : "code"},
//        {"sTitle":"面试项目","data" : "map.sysdicEntity.caption"},
//        {"sTitle":"面试情况","data" : "remark"},
////        {"sTitle":"备注","data" : "memo"},
//        {"sTitle":"是否审核","data" : "isaudit","render":function (data) {
//           if(data==1){
//               return '已审核';
//           }else {
//               return '未审核';
//           }
//        }},
//        {"sTitle":"审核人","data" : "map.auditorEntity.caption"},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ]

    function renderCaption(data, type, row) {
        return '<a href="/student/studentInterview/edit?pkStudentInterview='+row.pkStudentInterview+'" class="yellow" >'+data+'</a>';
    }

    $(document).ready(function() {
        query();
    } );
    function query() {
        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val()};
        init(GetTableColumn("studentInterview"),"/student/studentInterview/queryByPaging",data,"pagingTable");
    }




    function del() {
        var id = $(".table-color-").attr("dataid");

        if (id == null) {
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/student/studentInterview/delete",
                data: {"pkStudentInterview": id},
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