<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>学生报名 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">

                        <!--  <button title="更多操作" type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown">
                             <span class="caret"></span>
                             <span class="sr-only"></span>
                         </button>
                         <ul class="dropdown-menu" role="menu">
                             <li><a href="" data-toggle="modal">转为正式学员</a></li>
                             <li><a href="" data-toggle="modal">转为试听学员</a></li>
                             <li><a href="" data-toggle="modal">取消转化</a></li>
                             <li><a href="" data-toggle="modal">删除</a></li>
                             <li class="divider"></li>
                             <li><a href="" data-toggle="modal">导入</a></li>
                             <li><a href="" data-toggle="modal">导出</a></li>
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
                                <label class="control-label">日期 :</label>
                                <i class="fa fa-calendar" ></i>
                                <input type="text" readonly title=""  name="reservation" id="datas" class="form-control form-input-time" value="" />
                            </div>
                            <div class="form-group">
                                <label class="control-label">编号 :</label>
                                <input type="text" title="" id="code" name="reservation"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                    <button type="button" onclick="query();" class="btn btn-newblue btn-sm search">搜索</button>
                                </div>
                            </div>
                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                               </div>-->
                        </form>
                        <!--隐藏搜索-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">
                                <form class="form-inline search white">
                                    <div class="form-group">
                                        <label class="control-label">报名年级 :</label>
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
        return '<a href="/student/studentReport/edit?pkStudentSignup='+row.pkStudentSignup+'" class="yellow" >'+data+'</a>';
    }

    function query() {
        var data  = { "code": $("#code").val(),"dateTime": $("#datas").val(),"grade":$("#grade").val(),"hasClassInfo":${hasClassInfo}};
        init(GetTableColumn("studentReport"),"/student/studentReport/queryByPaging",data,"pagingTable");
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