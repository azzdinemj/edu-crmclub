


<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<form class="form-ajax form-horizontal no-margin look" id="formid" method="post" action="/system/dormRoom/save" data-target="/system/dormRoom/query">

    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>Dormitory information<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <a class="btn btn-sm btn-newblue" style="display: none" id="delId" onClick="delStudent()">out dorm</a>
                    <a class="btn btn-sm btn-newblue"  onClick="javascript:history.back(-1);">Return</a>

                    <a class="btn btn-newblue btn-sm" onclick="sub()">
                        Preservation
                    </a>
                    <#--<a class="btn btn-newblue btn-sm" data-toggle="modal" data-target="#myModal" data-url="change-room.html">-->
                        <#--edit-->
                    <#--</a>-->
                    <#--<a class="btn btn-newblue btn-sm" data-toggle="modal" data-target="#myModal" data-url="info-room.html">-->
                        <#--Check in-->
                    <#--</a>-->
                    <#--<a class="btn btn-newblue btn-sm" data-toggle="modal" data-target="#myModal" data-url="out-room.html">-->
                        <#--out dorm-->
                    <#--</a>-->
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <input type="hidden" name="room.pkDomain" value="${(dormRoom.pkDomain)!}">
            <input type="hidden" id="pkDormRoomId" name="room.pkDormRoom" value="${(dormRoom.pkDormRoom)!}">
            <div class="contentpanel">
                <ul class="nav nav-tabs nav-yellow">
                    <li class="active"><a data-toggle="tab" href="#one"><strong>Dormitory information</strong></a></li>
                <#if dormRoom ?? && dormRoom.kind??&& dormRoom.kind ==0>
                    <li class=""><a data-toggle="tab" href="#two"><strong>Student information</strong></a></li>
                </#if>
                <#if dormRoom ?? && dormRoom.kind??&& dormRoom.kind ==1>
                    <li class=""><a data-toggle="tab" href="#teacher"><strong>Teacher information</strong></a></li>
                </#if>

                </ul>

               <div class="tab-content">
                    <div id="one" class="tab-pane active">
                           <div class="row">
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">code</label>
                                       <div class="col-xs-9 col-md-9">
                                           <input name="room.code"  readonly class="form-control" value="${(dormRoom.code)!}" title="" type="text">
                                       </div><!-- /.col -->
                                   </div>
                               </div>
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">name</label>
                                       <div class="col-xs-9 col-md-9">
                                           <input name="room.caption" value="${(dormRoom.caption)!}" class="form-control" title="" type="text">
                                       </div><!-- /.col -->
                                   </div>
                               </div>
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">Dormitory Category</label>
                                       <div class="col-xs-9 col-md-9">
                                           <select name="room.pkClass">
                                               <#if drclass ??>
                                                   <#list drclass as d>
                                                       <option value="${(d.pkSysDictValues)!}" <#if d.pkSysDictValues ?? &&dormRoom.pkClass??&& d.pkSysDictValues ==dormRoom.pkClass>selected</#if>>${(d.caption)!}</option>
                                                   </#list>
                                               </#if>
                                           </select>
                                           <#--<input name="room.pkClass" value="${(dormRoom.pkClass)!}" class="form-control" title="" type="text">-->
                                       </div><!-- /.col -->
                                   </div>
                               </div>
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">Dorm type</label>
                                       <div class="col-xs-9 col-md-9">
                                           <select name="room.kind">
                                               <option value="0" <#if dormRoom.kind ?? && dormRoom.kind ==0 >selected="selected"</#if>>Student dorm</option>
                                               <option value="1" <#if dormRoom.kind ?? && dormRoom.kind==1 >selected="selected"</#if>>Teacher dorm</option>
                                           </select>

                                       </div><!-- /.col -->
                                   </div>
                               </div>
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">sex</label>
                                       <div class="col-xs-9 col-md-9">
                                           <select name="room.sex">
                                               <option value="1" <#if dormRoom.sex ?? && dormRoom.sex ==1 >selected="selected"</#if>>男</option>
                                               <option value="2" <#if dormRoom.sex ?? && dormRoom.sex==2 >selected="selected"</#if>>女</option>
                                           </select>

                                       </div><!-- /.col -->
                                   </div>
                               </div>
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">Capacity</label>
                                       <div class="col-xs-9 col-md-9">
                                           <input name="room.num" value="${(dormRoom.num)!}" class="form-control" title="" type="number">
                                       </div><!-- /.col -->
                                   </div>
                               </div>
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">price</label>
                                       <div class="col-xs-9 col-md-9">
                                           <input name="room.expenses" value="${(dormRoom.expenses?c)!}" class="form-control"
                                                  data-bv-notempty data-bv-notempty-message="Please enter the price" title="" >
                                       </div><!-- /.col -->
                                   </div>
                               </div>
                               <div class="col-xs-12 col-md-4">
                                   <div class="form-group">
                                       <label class="control-label col-xs-3 col-md-3">Resident number</label>
                                       <div class="col-xs-9 col-md-9">
                                           <input name="room.currentNum" value="${(dormRoom.currentNum)!}"  class="form-control" title="" type="number">
                                       </div><!-- /.col -->
                                   </div>
                               </div>
                           </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">state</label>
                                <div class="col-xs-9 col-md-9">
                                    <span class="rdio rdio-warning">
                                                   <input name="room.isvalid" value="1" class="deldis"
                                                          <#if dormRoom.isvalid??&& dormRoom.isvalid ==0 ><#else >checked="checked"</#if>
                                                          id="radio1" type="radio">
                                                   <label for="radio1">Enable</label>
                                               </span>
                                    <span class="rdio rdio-warning">
                                                   <input name="room.isvalid" value="0" id="radio2" class="deldis"
                                                          <#if dormRoom.isvalid??&& dormRoom.isvalid ==0 >checked="checked"</#if>
                                                          type="radio">
                                                   <label for="radio2">Disable</label>
                                               </span>
                                </div><!-- /.col -->
                            </div>
                        </div>
                           <div class="row">
                           <div class="col-xs-12 col-md-12">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-1">Remarks</label>
                                   <div class="col-xs-9 col-md-11">
                                       <input name="room.memo" value="${(dormRoom.memo)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                       </div>
                    </div>
                   <div id="two" class="tab-pane">
                       <table id="pagingTableStu" class="table table-striped table-bordered" cellspacing="0">
                           <thead>
                           <#--<tr>-->
                               <#--<td>number</td>-->
                               <#--<td>name</td>-->
                               <#--<td>Sex</td>-->
                               <#--<td>Ethnic</td>-->
                               <#--<td>grade</td>-->
                               <#--<td>operation</td>-->

                           <#--</tr>-->
                           </thead>

                           <tbody>
                           <#--<#if studentList ??>-->
                               <#--<#list studentList as s>-->
                               <#--<tr dataid="${(s.pkStudent)!}" dataCaption="${(s.caption)!}" >-->

                                   <#--<td>${(s.code)!}</td>-->
                                   <#--<td>-->

                                       <#--${(s.caption)!}-->

                                   <#--</td>-->
                                   <#--<td>-->
                                       <#--<#if s.sex ?? && s.sex==1>男<#else >女</#if>-->
                                   <#--</td>-->
                                   <#--<td>${(s.nationEntity.caption)!}</td>-->
                                   <#--<td>${(s.map.gradeEntity.caption)!}</td>-->
                                   <#--<td>-->
                                       <#--<a class="danger" onclick="delStudent('${(s.map.dormRoom)!}')">-->
                                        <#--out dorm-->
                                       <#--</a>-->
                                   <#--</td>-->
                               <#--</tr>-->
                               <#--</#list>-->
                           <#--</#if>-->

                           </tbody>
                       </table>
                   </div>
                   <#--Teacher information-->
                   <div id="teacher" class="tab-pane">
                       <table id="pagingTableEmp" class="table table-striped table-bordered" cellspacing="0">
                           <thead>
                           <#--<tr>-->
                               <#--<td>number</td>-->
                               <#--<td>name</td>-->
                               <#--<td>Sex</td>-->
                               <#--<td>Ethnic</td>-->
                               <#--<td>operation</td>-->


                           <#--</tr>-->
                           </thead>

                           <tbody>
                           <#--<#if employeeList ??>-->
                               <#--<#list employeeList as e>-->
                               <#--<tr dataid="${(e.pkEmployee)!}" dataCaption="${(e.caption)!}">-->

                                   <#--<td>${(e.code)!}</td>-->
                                   <#--<td>-->
                                       <#--${(e.caption)!}-->
                                   <#--</td>-->
                                   <#--<td>-->
                                       <#--<#if e.sex ?? && e.sex==1>男<#else >女</#if>-->
                                   <#--</td>-->
                                   <#--<td>${(e.nationEntity.caption)!}</td>-->
                                   <#--<td>-->
                                       <#--<a class="danger" onclick="delEmployee('${(e.map.dormRoom)!}')">-->
                                           <#--out dorm-->
                                       <#--</a>-->
                                   <#--</td>-->

                               <#--</tr>-->
                               <#--</#list>-->
                           <#--</#if>-->

                           </tbody>
                       </table>
                   </div>
               </div>
            </div>


        </div><!-- contentpanel -->

    </div><!-- mainpanel -->
</form>





<#include "../commons/footer.ftl"/>

<script type="text/javascript">
    function sub() {
        $("#formid").submit();
    }

    /**
     * Tuisu
     * @param id
     */
    function delStudent() {

        var id = $("input[name='checkbox']:checked").val();
        if(id == null){
            alert("Please choose students");
            return;
        }

        if(confirm("Are you sure you want to retire")){
            $.ajax({
                type:"post",
                url: "/system/dormRoomStudent/delete",
                data: {"pkDormStudent":id},
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

    function delEmployee(id) {

        if(confirm("Are you sure you want to retire")){
            $.ajax({
                type:"post",
                url: "/system/dormRoomEmployee/delete",
                data: {"pkDormEmployee":id},
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


    $(document).ready(function() {
        queryStu();
        queryEmp();
        $(".nav-yellow li").click(function () {
            var index = $(this).index();
            if(index==1 || index ==2){
                $("#delId").show();
            }else {
                $("#delId").hide();
            }
        });
    } );

    function renderCaption(data, type, row) {

        return '<input class="checkboxAll" name="checkbox" type="checkbox" value="'+data+'" caption="'+row.caption+'" class="px">';
    }

    function queryStu() {
        var data  = {"pkDormRoom":$("#pkDormRoomId").val()};
        init(GetTableColumn("dormStudentModel"),"/system/dormRoomStudent/queryByPaging",data,"pagingTableStu");
    }
    function queryEmp() {
        var data  = {"pkDormRoom":$("#pkDormRoomId").val()};
        init(GetTableColumn("dormroomEmp"),"/system/dormRoomEmployee/queryByPaging",data,"pagingTableEmp");
    }


</script>