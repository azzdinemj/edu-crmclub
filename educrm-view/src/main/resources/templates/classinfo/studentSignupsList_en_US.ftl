<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Unscheduled students <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" id="pkStudents">Go into the class</a>
                <a type="hidden" class="btn btn-sm btn-newblue" style="display: none" id="goClassinfo" data-toggle="modal"  data-target="#modal" data-url="/student/studentSignups/createAll">Go into the class</a>
            <#-- <a class="btn btn-sm btn-newblue" href="" data-toggle="modal">delete</a>-->
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
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">Date, :</label>-->
                        <#--<input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"-->
                               <#--value=""/>-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="control-label">Student name :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-time" value=""/>
                    </div>
                    <div class="form-group">
                        <span class="btn btn-newblue btn-sm search" onclick="query()"><i class="fa fa-search"> </i>search</span>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label>nationality</label>-->
                        <#--<div class="btn-group">-->
                            <#--<select class="form-control strw">-->
                                <#--<option>whole</option>-->
                                <#--<option>China</option>-->
                                <#--<option>U.S.A</option>-->
                                <#--<option>Britain</option>-->
                                <#--<option>France</option>-->
                                <#--<option></option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->

                </form>
                <!--Hide search-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label>Faculty</label>
                                <div class="btn-group">
                                    <select class="form-control strw">
                                        <option>whole</option>
                                        <option>One</option>
                                        <option>Two</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Parent name :</label>
                                <input type="text" title="" name="reservation" class="form-control form-input-lg"
                                       value=""/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">School year :</label>
                                <div class="btn-group">
                                    <select class="form-control strw">
                                        <option>whole</option>
                                        <option>first grade</option>
                                        <option>second grade</option>
                                        <option>Grade three</option>
                                        <option>fourth grade</option>
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
                <table id="pagingTable" class="table table-striped table-bordered lg-table" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                        <#--&lt;#&ndash;<th></th>&ndash;&gt;-->
                        <#--<th>name</th>-->
                        <#--<th>Sex</th>-->
                        <#--<th>class</th>-->
                        <#--<th>project</th>-->
                        <#--<th >grade</th>-->
                        <#--<th>Admissions teacher</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#if student ??>-->
                    <#--<#list student as students>-->
                    <#--<tr  dataid="${(students.pkStudent)!}" dataSign="${(students.pkStudentSignup)!}" dataClassStatus="${(students.map.classInfoEntity.caption)!}">-->
                        <#--&lt;#&ndash;<td><input type="checkbox" class="px" value="${(students.pkStudent)!}" name="checked"></td>&ndash;&gt;-->
                        <#--<td>${(students.studentEntity.caption)!}</td>-->
                        <#--<td>-->
                            <#--<#if students.sex??&&students.sex==1>男<#else >女</#if>-->
                        <#--&lt;#&ndash;${(students.sex)!}&ndash;&gt;-->
                        <#--</td>-->
                        <#--&lt;#&ndash;<td>China</td>&ndash;&gt;-->
                        <#--<td>${(students.map.classInfoEntity.caption)!}</td>-->
                        <#--<td>${(students.gradeEntity.caption)!}</td>-->
                        <#--<td>${(students.sysdicEntity.caption)!}</td>-->
                        <#--<td>${(students.employeeEntity.caption)!}</td>-->
                    <#--</tr>-->
                    <#--</#list>-->
                    <#--</#if>-->
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
        var data  = {"studentName":$("#caption").val()};
        init(GetTableColumn("studentSignups"),"/student/studentSignups/queryByPaging",data,"pagingTable");
    }
    
    $("#pkStudents").on("click",function(){

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkStudent;
        var sign = getTableSelectRow("pagingTable",index).pkStudentSignup;
        var classStatus = getTableSelectRow("pagingTable",index).map.classInfoEntity.caption;
        if (id == null) {
            Notify.danger("Select a row of data！！！");
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
           /!* window.alert("You chose it："+
                    $('input[type=checkbox]:checked').length+"个，One of them is："+$(this).val());*!/
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
                    alert("Please select the data to delete first");
                    return;
                }

                var flag = confirm("Do you confirm the deletion？");
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
