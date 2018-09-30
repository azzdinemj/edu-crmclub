


<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<form class="form-ajax form-horizontal no-margin look" id="formid" method="post" action="/system/schoolBus/save" data-target="/system/schoolBus/query">

    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>bus information<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <a class="btn btn-sm btn-newblue" style="display: none" id="delId" onClick="delStudent()">Cancel ride</a>
                    <a class="btn btn-sm btn-newblue"  onClick="javascript:history.back(-1);">Return</a>
                    <a class="btn btn-newblue btn-sm" onclick="sub()">
                        Preservation
                    </a>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <input type="hidden" name="bus.pkDomain" value="${(schoolBus.pkDomain)!}">
            <input type="hidden" id="pkSchoolBus" name="bus.pkSchoolBus" value="${(schoolBus.pkSchoolBus)!}">
            <div class="contentpanel">
                <ul class="nav nav-tabs nav-yellow">
                    <li class="active"><a data-toggle="tab" href="#one"><strong>bus information</strong></a></li>

                    <li class="" ><a data-toggle="tab" href="#two"><strong>Student information</strong></a></li>


                </ul>
                <div class="tab-content">
                    <div id="one" class="tab-pane active">
                        <div class="row">
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">number</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.code"  readonly class="form-control" value="${(schoolBus.code)!}" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">name</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.caption" value="${(schoolBus.caption)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">code</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.busCode" value="${(schoolBus.busCode)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">classification</label>
                                   <div class="col-xs-9 col-md-9">
                                       <select name="bus.kind">
                                           <#if schoolBusKind ??>
                                               <#list schoolBusKind as d>
                                                   <option value="${(d.pkSysDictValues)!}" <#if d.pkSysDictValues ?? &&schoolBus.kind??&& d.pkSysDictValues ==schoolBus.kind>selected</#if>>${(d.caption)!}</option>
                                               </#list>
                                           </#if>
                                       </select>
                                       <#--<input name="bus.pkClass" value="${(schoolBus.pkClass)!}" class="form-control" title="" type="text">-->
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">numberPlate</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.plateNumber" value="${(schoolBus.plateNumber)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>

                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">Capacity</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.num" value="${(schoolBus.num)!}" class="form-control" maxlength="3" title="" data-bv-notempty data-bv-notempty-message="Please enter the number of seats" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">Price</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.expenses" value="${(schoolBus.expenses?number)!}" class="form-control"
                                              data-bv-notempty data-bv-notempty-message="Please enter the price" maxlength="6" title="" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">Occupants</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.currentNum" value="${(schoolBus.currentNum)!}" maxlength="3" class="form-control" title="" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">sort</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.sort" value="${(schoolBus.sort)!}"  class="form-control" title="" type="number">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">state</label>
                                   <div class="col-xs-9 col-md-9">
                                       <#--<input name="bus.isvalid" value="${(schoolBus.isvalid)!}"  class="form-control" title="" type="number">-->
                                       <span class="rdio rdio-warning">
                                                   <input name="bus.isvalid" value="1" class="deldis"
                                                          <#if schoolBus.isvalid??&& schoolBus.isvalid ==0 ><#else >checked="checked"</#if>
                                                          id="radio1" type="radio">
                                                   <label for="radio1">Enable</label>
                                               </span>
                                       <span class="rdio rdio-warning">
                                                   <input name="bus.isvalid" value="0" id="radio2" class="deldis"
                                                          <#if schoolBus.isvalid??&& schoolBus.isvalid ==0 >checked="checked"</#if>
                                                          type="radio">
                                                   <label for="radio2">Disable</label>
                                               </span>
                                   </div><!-- /.col -->
                               </div>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-xs-12 col-md-12">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-1">Remarks</label>
                                   <div class="col-xs-9 col-md-11">
                                       <input name="bus.memo" value="${(schoolBus.memo)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                       </div>
                    </div>
                    <div id="two" class="tab-pane">
                        <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
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
                                        <#--<a class="danger" onclick="delStudent('${(s.map.busStudent)!}')" >-->
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

    function delStudent() {

        var id = $("input[name='checkbox']:checked").val();
        if(id == null){
            alert("Please choose students");
            return;
        }

        if(confirm("Be sure to retire")){
            $.ajax({
                type:"post",
                url: "/system/schoolBusStudent/delete",
                data: {"pkSchooBusStudent":id},
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
        query();
        $(".nav-yellow li").click(function () {
            var index = $(this).index();
            if(index==1){
                $("#delId").show();
            }else {
                $("#delId").hide();
            }
        });
    } );

    function renderCaption(data, type, row) {

        return '<input class="checkboxAll" name="checkbox" type="checkbox" value="'+data+'" caption="'+row.caption+'" class="px">';
    }

    function query() {
        var data  = {"pkSchoolBus":$("#pkSchoolBus").val()};
        init(GetTableColumn("schoolBusStudent"),"/system/schoolBusStudent/queryStudent",data,"pagingTable");
    }


</script>