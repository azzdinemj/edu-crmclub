
<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />
<link rel="stylesheet" type="text/css" href="${staticPath}/plus/bootstrap-dialog/css/bootstrap-dialog.css">
<form class="look form-ajaxadd" id="formid" method="post" action="/jiedian/schedule/save" data-target="<#if delete?? && delete==1>/jiedian/schedule/query?defaultView=${(defaultView)!}&viewType=${(viewType)!}&<#if pkEmployee??>pkEmployee=${(pkEmployee)!}</#if><#if pkStudent??>pkStudent=${(pkStudent)!}</#if><#else >/jiedian/schedule/create?defaultView=${(defaultView)!}&viewType=${(viewType)!}&<#if pkEmployee??>pkEmployee=${(pkEmployee)!}</#if><#if pkStudent??>pkStudent=${(pkStudent)!}</#if></#if>">

    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>排课<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <#if bykey ?? && bykey=="1">
                        <a class="btn btn-sm btn-newblue" onclick="validate()" >验证排课</a>
                    <#else >
                        <a class="btn btn-sm btn-newblue schedule" data-type="1" >老师已排课</a>
                        <a class="btn btn-sm btn-newblue schedule" data-type="2" >学生已排课</a>
                        <a class="btn btn-sm btn-newblue schedule" data-type="3" >教室已排课</a>
                    </#if>

                    <#if viewType?? &&viewType == "0">
                        <a class="btn btn-sm btn-newblue" onClick="history.back(-1);">返回</a>
                        <#else >
                        <a class="btn btn-sm btn-newblue" href="/jiedian/schedule/query?defaultView=${(defaultView)!}">返回</a>
                    </#if>
                    <input class="btn btn-sm btn-newblue" type="submit" value="保存">
                        <#if delete?? && delete==1>
                            <a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>
                        </#if>
                    <#--<a class="btn btn-newblue btn-sm" >-->
                        <#--保存-->
                    <#--</a>-->
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <input type="hidden" name="pkSchedule" id="pkSchedule" value="${(schedule.pkSchedule)!}">
            <input type="hidden" name="pkDomain" value="3">
            <input id="listkey" name="listkey" value="" type="hidden">
            


      

           

           

            <div class="row">
            	 <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">产品</label>

                        <div class="col-xs-9">
                            <select name="pkProduct" class="form-control" data-bv-notempty data-bv-notempty-message="请选择产品">
                            <option value="">请选择</option>
                            	 <#if product ??>
                                            <#list product as s>
                                                <option value="${(s.pkProduct)!}"
                                                        <#if s.pkProduct ?? && schedule.pkProduct??&& s.pkProduct==schedule.pkProduct>selected="selected"</#if>> ${(s.caption)!}</option>
                                            </#list>
                                        </#if>
                       
                            </select>
                        </div>
                    </div>
                </div>
                 <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">学生</label>

                        <div class="col-xs-9 ">
                        	 <input id="stuCaption" name="stuCaption" readonly data-bv-notempty data-bv-notempty-message="请选择学生"  value="${(schedule.map.studentobj)!}" class="form-control pkStudent" <#if student ??&& student.pkStudent??><#else >data-toggle="modal" data-target="#modal" data-url="/classinfo/classinfo/findstu?pkClassinfo=${(classinfo.pkClassinfo)!}"</#if> >
                             <input id="pkStudent" name="pkStudent" value="${(schedule.pkStudent)!}" type="hidden">
                           
                        </div>
                    </div>
                </div>
                 <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">老师</label>

                        <div class="col-xs-9">
                            <select name="pkEmployee" id="pkEmployee" class="form-control pkEmployee" data-bv-notempty data-bv-notempty-message="请选择老师">
                            <option value="">请选择</option>
                            	 <#if employee ??>
                                            <#list employee as s>
                                                <option value="${(s.pkEmployee)!}"
                                                        <#if s.pkEmployee ?? && schedule.pkEmployee??&& s.pkEmployee==schedule.pkEmployee>selected="selected"</#if>> ${(s.caption)!}</option>
                                            </#list>
                                        </#if>
                       
                            </select>
                        </div>
                    </div>
                </div>
                 <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">教室</label>

                        <div class="col-xs-9 ">
                            <select name="pkClassRoom" id="pkClassRoom" class="form-control pkClassRoom" data-bv-notempty data-bv-notempty-message="请选择教室">
                            <option value="">请选择</option>
                            	 <#if classRoom ??>
                                            <#list classRoom as s>
                                                <option value="${(s.pkClassRoom)!}"
                                                        <#if s.pkClassRoom ?? && schedule.pkClassRoom??&& s.pkClassRoom==schedule.pkClassRoom>selected="selected"</#if>> ${(s.caption)!}</option>
                                            </#list>
                                        </#if>
                       
                            </select>
                        </div>
                    </div>
                </div>
                 <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">开始时间</label>

                        <div class="col-xs-9 ">
                            <input class="form-control js-datetimepicker"  value="${(schedule.startTime? string("yyyy-MM-dd HH:mm"))!}" name="startTime" type="text" data-bv-notempty data-bv-notempty-message="请选择开始时间">
                        </div>
                    </div>
                </div>
                 <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">结束时间</label>

                        <div class="col-xs-9 ">
                            <input class="form-control js-datetimepicker"  value="${(schedule.endTime? string("yyyy-MM-dd HH:mm"))!}" name="endTime" type="text" data-bv-notempty data-bv-notempty-message="请选择结束时间">
                        </div>
                    </div>
                </div>
                <#if bykey ?? && bykey=="1">
                    <div class="col-lg-6 col-xs-12 col-md-12">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-1">课程周期</label>

                            <div class="col-xs-9 ">
                                <input class="form-control" id="datas" type="text" name="datas" value="" data-bv-notempty data-bv-notempty-message="请录入课程周期">
                            </div>
                        </div>
                    </div>
                 <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">排课周期</label>

                        <div class="col-xs-9 ">
                            <select name="cycle">
                                <option value="1">周</option>
                                <option value="2">月</option>
                            </select>
                        </div>
                    </div>
                </div>
                </#if>
				 <#if schedule.pkSchedule ??>
                 <div class="row">
                     <div class="form-group">
                         <label class="control-label col-xs-3"><i class="red">*</i>反馈审核</label>
                         <div class="col-xs-9">
                                         <span class="rdio rdio-warning">
                                           <input name="isfeedback" value="0" class="deldis"
                                                  <#if schedule.isfeedback??&& schedule.isfeedback ==1 ><#else >checked="checked"</#if>
                                                  id="radio1" type="radio">
                                           <label for="radio1">不通过</label>
                                       </span>
                             <span class="rdio rdio-warning">
                                           <input name="isfeedback" value="1" id="radio2" class="deldis"
                                                  <#if schedule.isfeedback??&& schedule.isfeedback ==1 >checked="checked"</#if>
                                                  type="radio">
                                           <label for="radio2">通过</label>
                                       </span>
                         <#--<select name="stu.sex">-->
                         <#--<option value="1" <#if student.sex??&& student.sex==1>selected="selected"</#if>>-->
                         <#--男-->
                         <#--</option>-->
                         <#--<option value="2" <#if student.sex??&& student.sex==2>selected="selected"</#if>>-->
                         <#--女-->
                         <#--</option>-->
                         <#--</select>-->
                         </div>
                     </div>
                 </div>
                <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1 ">反馈</label>
                        <div class="col-xs-9 ">
                            <textarea  class="form-control"   name="notes" style="width:100%;height:300px;">${(schedule.notes)!}</textarea>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1 ">反馈翻译</label>
                        <div class="col-xs-9 ">
                            <textarea  class="form-control"  name="notesEng" style="width:100%;height:300px;">${(schedule.notesEng)!}</textarea>
                        </div>
                    </div>
                </div>
            </#if>
                <div class=" col-lg-6 ">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">状态</label>

                        <div class="col-xs-9 ">
                            <select name="status" class="form-control">
                                <option <#if schedule??&&schedule.status??&&schedule.status==0> selected="" </#if> value="0">启用</option>
                                <option <#if  schedule??&&schedule.status??&&schedule.status==1> selected="" </#if>  value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class=" col-lg-6 ">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">授课类型</label>
                        <div class="col-xs-9 ">
                            <select name="lectureType" class="form-control">
                                <option <#if schedule??&&schedule.lectureType??&&schedule.lectureType==0> selected="" </#if> value="0">面授</option>
                                <option <#if schedule??&&schedule.lectureType??&&schedule.lectureType==1> selected="" </#if> value="1">网课</option>
                                <option <#if  schedule??&&schedule.lectureType??&&schedule.lectureType==2> selected="" </#if>  value="2">其他</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
   <#--<#if syslog ??>-->
 <#--<div class="panel-body pd0">-->
                    <#--<div class="table-responsive">-->
                        <#--<table   class="table table-striped table-bordered" cellspacing="0">-->
                            <#--<thead>-->
                            <#--<tr>-->
                               <#---->
                                <#--<th>标题</th>-->
                                <#--<th>变更前</th>-->
                                <#--<th>变更后</th>-->
                                <#--<th>变更时间</th>-->
                                <#--<th>变更人</th>-->
                                <#---->
                            <#--</tr>-->
                            <#--</thead>-->
                            <#--<tbody>-->
                         <#---->
                            <#--<#list syslog as v>-->
                            <#--<tr >-->
                               <#---->
                                <#--<td>-->
                                	<#--<#switch v.oldField>-->
                                	<#--<#case "pkProduct">-->
                                	<#--产品-->
                                	<#--<#break>-->
                                	<#--<#case "pkEmployee">-->
                                	<#--老师-->
                                	<#--<#break>-->
                                	<#--<#case "pkStudent">-->
                                	<#--学生-->
                                	<#--<#break>-->
                                	<#--<#case "pkClassRoom">-->
                                	<#--教室-->
                                	<#--<#break>-->
                                	<#--<#case "startTime">-->
                                	<#--开始时间-->
                                	<#--<#break>-->
                                	<#--<#case "endTime">-->
                                	<#--结束时间-->
                                	<#--<#break>-->
                                	<#--<#case "notes">-->
                                	<#--老师反馈-->
                                	<#--<#break>-->
                                	<#--<#case "notesEng">-->
                                	<#--反馈翻译-->
                                	<#--<#break>-->
                                	<#--<#case "status">-->
                                	<#--状态-->
                                	<#--<#break>-->
                                	<#--</#switch>-->
                                  <#---->
                                <#--</td>-->
                                <#--<td>-->
                                	<#--<#switch v.oldField>-->
                                	<#--<#case "pkProduct">-->
                                	 <#--${(v.map.oldvalue)!}-->
                                	<#--<#break>-->
                                	<#--<#case "pkEmployee">-->
                                	 <#--${(v.map.oldvalue)!}-->
                                	<#--<#break>-->
                                	<#--<#case "pkStudent">-->
                                	 <#--${(v.map.oldvalue)!}-->
                                	<#--<#break>-->
                                	<#--<#case "pkClassRoom">-->
                                	 <#--${(v.map.oldvalue)!}-->
                                	<#--<#break>-->
                                	<#---->
                                	<#---->
                                	<#--<#default>-->
                                	 <#--${(v.oldFieldValue)!}-->
                                	 <#--<#break>-->
                                	<#--</#switch>-->
                                	<#---->
                                <#--</td>-->
                                <#--<td><#switch v.oldField>-->
                                	<#--<#case "pkProduct">-->
                                	 <#--${(v.map.newvalue)!}-->
                                	<#--<#break>-->
                                	<#--<#case "pkEmployee">-->
                                	 <#--${(v.map.newvalue)!}-->
                                	<#--<#break>-->
                                	<#--<#case "pkStudent">-->
                                	 <#--${(v.map.newvalue)!}-->
                                	<#--<#break>-->
                                	<#--<#case "pkClassRoom">-->
                                	 <#--${(v.map.newvalue)!}-->
                                	<#--<#break>-->
                                	<#---->
                                	<#--<#default>-->
                                	 <#--${(v.newFieldValue)!}-->
                                	 <#--<#break>-->
                                	<#--</#switch>-->
                                	<#--</td>-->
                                <#--<td>${(v.datetime?string("yyyy-MM-dd HH:mm:ss"))!}</td>-->
                                <#--<td>-->
                                   <#--${(v.map.creatorEntity.caption)!}-->
                                <#--</td>-->
                                <#---->
                            <#--</tr>-->
                            <#--</#list>-->
                          <#---->
                            <#--</tbody>-->
                        <#--</table>-->
                    <#--</div><!-- table-responsive &ndash;&gt;-->
                <#--</div><!-- panel-body &ndash;&gt;-->

  <#--</#if>-->

<#include "${staticPath}/commons/footer.ftl"/>
<script src="${staticPath}/plus/bootstrap-dialog/js/bootstrap-dialog.js"></script>
<<script type="text/javascript">
<!--

//-->

function del() {
    var flag = confirm("确认删除吗？");
    if (flag) {
        $.ajax({
            type: "POST",
            url: "/jiedian/schedule/delete",
            data: {"pkSchedule": $("#pkSchedule").val()},
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    setTimeout("window.location.href='/jiedian/schedule/query'", 1);
                } else {
                    Notify.danger(data.message)
                }
            },
            error: function () {

            }
        });
    }


}

$(".schedule").on("click.modal.data-api", function(){
	var type=$(this).data("type");
	var  url="/jiedian/schedule/modalschedule"
	switch(type){
		case 1:
			
			$("form").data('bootstrapValidator').validateField($('.pkEmployee'));
		
			if($("#pkEmployee").val()==""){
				return;
			}else{
				url=url+"?pkEmployee="+$("#pkEmployee").val();
			}
			break;
		case 2:
			$("form").data('bootstrapValidator').validateField($('.pkStudent')); 
			if($("#pkStudent").val()==""){
				return;
			}else{
				url=url+"?pkStudent="+$("#pkStudent").val();
			}
			break;
		case 3:
			$("form").data('bootstrapValidator').validateField($('.pkClassRoom')); 
			if($("#pkClassRoom").val()==""){
				return;
			}else{
				url=url+"?pkClassRoom="+$("#pkClassRoom").val();
			}
			break;
	}
	  // var imgUrl=app.config.loadImg;

   href="";
    
    if (url) {

        var $target = $("#modal" || (href && href.replace(/.*(?=#[^\s]+$)/, '')));

        // var $loadingImg="<img src='"+imgUrl+"' class='modal-loading' style='z-index:1041;width:60px;height:60px;position:absolute;top:50%;left:50%;margin-left:-30px;margin-top:-30px;'/>";
        // $target.html($loadingImg);
        $target.load(url);
        $target.modal();
        //$target.show();
    }
});
function selectStu(pkId,caption) {
    $("#modal").modal("hide");
    $("#stuCaption").val(caption);
    $("#pkStudent").val(pkId);
    $("form").data('bootstrapValidator')
	.updateStatus($('#stuCaption'), 'NOT_VALIDATED',null)  
	.validateField($('#stuCaption')); 
}
$('.form-ajaxadd').bootstrapValidator().on('success.form.bv', function(e) {
	
  // Prevent form submission
  e.preventDefault();

  // Get the form instance
  var $form = $(e.target);

  // Get the BootstrapValidator instance
  var bv = $form.data('bootstrapValidator');
  $.post("/jiedian/schedule/checkschedule", $form.serialize(), function(da) {

		if(da.code==1){
			BootstrapDialog.confirm(da.message, function(result){
				if(result){
					
					$.post($form.attr('action'), $form.serialize(), function(data) {

						if(data.code==0){
								Notify.success(data.message);
				          if($form.data('target')){
				              setTimeout("window.location.href='"+$form.data('target')+"'",3000);
				          }
						}else{
								Notify.danger(data.message);
								window.location.reload();
						}
				  }, 'json');
				}
			});	
		}
		if(da.code==0){
			
					$.post($form.attr('action'), $form.serialize(), function(data) {

						if(data.code==0){
								Notify.success(data.message);
				          if($form.data('target')){
				              setTimeout("window.location.href='"+$form.data('target')+"'",3000);
				          }
						}else{
								Notify.danger(data.message);
								window.location.reload();
						}
				  }, 'json');
				
			
		}
		if(da.code==2){
			Notify.danger(da.message);
			
	
		}
}, 'json');
  // Use Ajax to submit form data
	
 
});

function validate() {
    $("#formid").bootstrapValidator();
    var bv = $("#formid").data('bootstrapValidator');
    bv.validate();
    if(bv.isValid()){

        $("#listkey").val("listkey");
        var formData = $("#formid").serialize();
        $.ajax({
                url: "/jiedian/schedule/checkschedule",
                type: "POST",
                data: formData,
                dataType: "json",
                success: function (data) {
                if (data.code == 0) {
    //                confirm("信息验证通过了，确认保存吗？");
                    if (confirm("信息验证通过了，确认保存吗？")){
                      $.ajax({
                          url: "/jiedian/schedule/saveAll",
                          type: "POST",
                          data: formData,
                          dataType: "json",
                          success: function (data1) {
                              if (data1.code==0){
                                  Notify.success(data.message);
                                  window.location.href="/jiedian/schedule/query?<#if pkEmployee??>pkEmployee=${(pkEmployee)!}</#if><#if pkStudent??>pkStudent=${(pkStudent)!}</#if>";
                              }else {
                                  Notify.danger(data.message);
                                  window.location.reload();
                              }
                          }
                      });
                    }
                } else {
    //                var register = "<span style='color: red;'>您的排课有冲突请重新排课</span>";
                    alert("您的排课有冲突请重新排课");

                }
            },
        });
    }
}


//function validate1() {
//    var formData = $("#formid").serialize();
//    $.ajax({
//        url: "/jiedian/schedule/saveAll",
//        type: "POST",
//        data: formData,
//        dataType: "json",
//        success: function (data1) {
//            if (data1.code==0){
//                Notify.success(data1.message);
//                window.location.reload();
//            }else {
//                Notify.danger(data1.message);
//                window.location.reload();
//            }
//        }
//    });
//}

</script>

