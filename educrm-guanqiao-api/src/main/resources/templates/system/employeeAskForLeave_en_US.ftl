<#include "../commons/top.ftl">
    <#include "../commons/left.ftl">

<form class="look form-ajax" method="POST" id="formId" action="/system/employeeaskforleave/save" data-target="/system/employeeaskforleave/query" >
        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>Add<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">Return</a>
                        <input class="btn btn-sm btn-newblue" type="submit" value="Preservation">
                    </div>
                </div>
            </div>

            <div class="panel panel-default pl35">

                <input type="hidden" name="pkAskForLeave" value="${(stuAskFL.pkAskForLeave)!}">

                      <div class="row">
                       <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-3">Teacher</label>
                            <div class="col-xs-9 ">
                                <input id="stuCaption" readonly name="stuEmpCaption" value="${(stuAskFL.stuEmpCaption)!}" class="form-control" <#if stuAskFL ??&& stuAskFL.pkStudentEmployee??><#else >data-toggle="modal" data-target="#modal" data-url="/student/studentSignup/getEmployees?employeekey=1" </#if>
                                       placeholder="Please choose the teacher"     data-bv-notempty data-bv-notempty-message="Please choose the teacher">
                                <input id="pkStudent" name="pkStudentEmployee" value="${(stuAskFL.pkStudentEmployee)!}" type="hidden">
                            </div>
                        </div>
                        </div>
                    </div>
                <!--end-->
                      <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 ">start time</label>

                                <div class="col-xs-9 ">
                                    <input class="form-control js-datetimepickerYMDHI" name="startTimeStr"  value="${(stuAskFL.startTime? string("yyyy-MM-dd HH:mm"))!}"  type="text"
                                           placeholder="Please choose the start time" data-bv-notempty data-bv-notempty-message="Please choose the start time">
                                </div>
                            </div>

                        </div>
                     </div>
                <!--end-->
                      <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 ">End time</label>

                                <div class="col-xs-9 ">
                                    <input class="form-control js-datetimepickerYMDHI" name="endTimeStr" value="${(stuAskFL.startTime? string("yyyy-MM-dd HH:mm"))!}"  type="text"
                                           placeholder="Please choose the end time" data-bv-notempty data-bv-notempty-message="Please choose the end time">
                                </div>
                            </div>

                        </div>
                    </div>
                <!--end-->
                      <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 ">leave Reason</label>
                                <div class="col-xs-9 ">
                                    <input class="form-control" name="remark"  value="${(stuAskFL.remark)!}" type="text"
                                           placeholder="Please enter the leave of leave" data-bv-notempty data-bv-notempty-message="Please enter the leave of leave">
                                </div>
                            </div>

                        </div>
                      </div>
                <!--end-->

                      <div class="row">
                        <!-- col-sm-4 -->
                          <div class="col-xs-12 col-md-4">

                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">state</label>
                                <div class="col-xs-9 col-md-9">
                                    <select id="selectStatus" name="status" class="form-control">
                                        <option  selected="" <#if stuAskFL??&& stuAskFL.status?? && stuAskFL.status=='0' > selected</#if> value="0">
                                            normal
                                        </option>
                                        <option  <#if stuAskFL??&& stuAskFL.status?? && stuAskFL.status =='1' > selected</#if> value="1">
                                            abnormal
                                        </option>
                                    </select>
                                </div>
                            </div>

                        </div>
                      </div>
                <!--end-->
                      <div class="row" id="statusRemark"  style="display:none">
                          <div class="col-xs-12 col-md-12">
                              <div class="form-group">
                                  <label class="control-label col-xs-3 col-md-1">State remark</label>

                                  <div class="col-xs-9 col-md-7">
                                      <input class="form-control"  name="statusRemark" value="${(stuAskFL.statusRemark)!}" type="text">
                                  </div>
                              </div>
                          </div>
                       </div>
                <!--end-->

             </div>
        </div>
   </form>
<#include "../commons/footer.ftl"/>
<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//Cancel all commas that appear in the string
        return str;
    }

    function selectEmp(pkId,caption) {
        $("#modal").modal("hide");
        $("#stuCaption").val(caption);
        $("#pkStudent").val(pkId);
    }

    $(function(){

        <#if stuAskFL??&& stuAskFL.status?? && stuAskFL.status=='1' >
        $("#statusRemark").show();
        </#if>

        $("#selectStatus").change(function(){
            var options=$("#selectStatus option:selected").val();
            if(options=='1'){
                $("#statusRemark").show();
            }else{
                $("#statusRemark").hide();
            }
        });
    });

    $('.js-datetimepickerYMDHI').datetimepicker({//Day and day
        format: 'yyyy-mm-dd hh:ii',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 1,
        forceParse: 0
    });
</script>