<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>未入班学生 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" id="pkStudents">进班</a>
                <a type="hidden" class="btn btn-sm btn-newblue" style="display: none" id="goClassinfo" data-toggle="modal"  data-target="#modal" data-url="/student/studentSignups/createAll">进班</a>
            <#-- <a class="btn btn-sm btn-newblue" href="" data-toggle="modal">删除</a>-->
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
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">学生姓名 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-time" value=""/>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search">搜索</a>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label>国籍</label>-->
                        <#--<div class="btn-group">-->
                            <#--<select class="form-control strw">-->
                                <#--<option>全部</option>-->
                                <#--<option>中国</option>-->
                                <#--<option>美国</option>-->
                                <#--<option>英国</option>-->
                                <#--<option>法国</option>-->
                                <#--<option></option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->

                </form>
                <!--隐藏搜索-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label>学部</label>
                                <div class="btn-group">
                                    <select class="form-control strw">
                                        <option>全部</option>
                                        <option>一部</option>
                                        <option>二部</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">家长姓名 :</label>
                                <input type="text" title="" name="reservation" class="form-control form-input-lg"
                                       value=""/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">入学年级 :</label>
                                <div class="btn-group">
                                    <select class="form-control strw">
                                        <option>全部</option>
                                        <option>一年级</option>
                                        <option>二年级</option>
                                        <option>三年级</option>
                                        <option>四年级</option>
                                        <option></option>
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
                <table id="table" class="table table-striped table-bordered lg-table" cellspacing="0">
                    <thead>
                    <tr>
                        <#--<th></th>-->
                        <th>姓名</th>
                        <th>性别</th>
                        <th>班级</th>
                        <th>项目</th>
                        <th >年级</th>
                        <th>招生老师</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if student ??>
                    <#list student as students>
                    <tr  dataid="${(students.pkStudent)!}" dataSign="${(students.pkStudentSignup)!}" dataClassStatus="${(students.map.classInfoEntity.caption)!}">
                        <#--<td><input type="checkbox" class="px" value="${(students.pkStudent)!}" name="checked"></td>-->
                        <td>${(students.studentEntity.caption)!}</td>
                        <td>
                            <#if students.sex??&&students.sex==1>男<#else >女</#if>
                        <#--${(students.sex)!}-->
                        </td>
                        <#--<td>中国</td>-->
                        <td>${(students.map.classInfoEntity.caption)!}</td>
                        <td>${(students.gradeEntity.caption)!}</td>
                        <td>${(students.sysdicEntity.caption)!}</td>
                        <td>${(students.employeeEntity.caption)!}</td>
                    </tr>
                    </#list>
                    </#if>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl" />

<script type="text/javascript">

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val()};
//        init(columns,"/student/studentSignups/queryByPaging",data);
    }
    
    $("#pkStudents").on("click",function(){

        var id = $(".table-color-").attr("dataid");
        var sign = $(".table-color-").attr("dataSign");
        var classStatus = $(".table-color-").attr("dataClassStatus");
        if (id == null) {
            Notify.danger("清选择一行数据！！！");
            return;
        }

        var result = "?stu.pkStudent="+id+"&stu.pkStudentSignup="+sign;
        $.post(
//            type: "POST",
//            dataType: "json",
            "/student/studentSignups/getClassinfoStatus",
            {"stu.pkStudentSignup": sign},
            function (data) {
                if (data == 0) {
                    $("#goClassinfo").attr("data-url",$("#goClassinfo").attr("data-url")+result);
                    $("#goClassinfo").click();
                } else {
                    $.ajax({
                        type: "POST",
                        url: "/student/studentSignups/updateStatus",
                        data: {"stu.pkStudentSignup": sign,"stu.status":2},
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
            },"json");

//        var url = "/student/studentSignups/createAll?pkStudents="+result;
//        /student/studentSignups/createAll
//        $("#pkStudents").attr("data-url",url);

    });
    
    
//    function subCheck() {
       /* var result = new Array();
        $.each($('input:checkbox:checked'),function(){
           /!* window.alert("你选了："+
                    $('input[type=checkbox]:checked').length+"个，其中有："+$(this).val());*!/
            result.push($(this).attr("value"));
        });
        var s = result.length;
        var url = "/student/studentSignups/createAll?pkStudents="+result;
//        /student/studentSignups/createAll
        $("#pkStudents").attr("data-name",result);
        window.location.href=url;*/


       // window.location.resolveURL();
    //data-url="/student/studentSignups/create"
        /*$.ajax({
            url:"/student/studentSignups/createAll",
            type:"POST",
            data:{"pkStudents":result},
            dataType:"json",
            traditional: true,
            success:function (data) {
                if(data.code ==0){
                    alert(1);
                   window.open(data.url);
                }else {
                    Notify.danger(data.message);
                    location.reload();
                }
            },
        });*/

//    }
</script>

<script type="text/javascript">

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
                        url: "/student/studentSignups/delete",
                        data: {"pkStudent": id},
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
