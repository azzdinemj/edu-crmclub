


<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<form class="form-ajax form-horizontal no-margin look" id="formid" method="post" action="/system/schoolBus/save" data-target="/system/schoolBus/query">

    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>校车资料<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <a class="btn btn-sm btn-newblue" style="display: none" id="delId" onClick="delStudent()">取消乘坐</a>
                    <a class="btn btn-sm btn-newblue"  onClick="javascript:history.back(-1);">返回</a>
                    <a class="btn btn-newblue btn-sm" onclick="sub()">
                        保存
                    </a>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <input type="hidden" name="bus.pkDomain" value="${(schoolBus.pkDomain)!}">
            <input type="hidden" id="pkSchoolBus" name="bus.pkSchoolBus" value="${(schoolBus.pkSchoolBus)!}">
            <div class="contentpanel">
                <ul class="nav nav-tabs nav-yellow">
                    <li class="active"><a data-toggle="tab" href="#one"><strong>校车信息</strong></a></li>

                    <li class="" ><a data-toggle="tab" href="#two"><strong>学生信息</strong></a></li>


                </ul>
                <div class="tab-content">
                    <div id="one" class="tab-pane active">
                        <div class="row">
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">编号</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.code"  readonly class="form-control" value="${(schoolBus.code)!}" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">校车名称</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.caption" value="${(schoolBus.caption)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">校车编号</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.busCode" value="${(schoolBus.busCode)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">校车分类</label>
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
                                   <label class="control-label col-xs-3 col-md-3">车牌号</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.plateNumber" value="${(schoolBus.plateNumber)!}" class="form-control" title="" type="text">
                                   </div><!-- /.col -->
                               </div>
                           </div>

                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">容纳人数</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.num" value="${(schoolBus.num)!}" class="form-control" maxlength="3" title="" data-bv-notempty data-bv-notempty-message="请录入容纳人数" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">校车价格</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.expenses" value="${(schoolBus.expenses?number)!}" class="form-control"
                                              data-bv-notempty data-bv-notempty-message="请录入价格" maxlength="6" title="" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">已坐人数</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.currentNum" value="${(schoolBus.currentNum)!}" maxlength="3" class="form-control" title="" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">排序</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.sort" value="${(schoolBus.sort)!}"  class="form-control" title="" type="number">
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">状态</label>
                                   <div class="col-xs-9 col-md-9">
                                       <#--<input name="bus.isvalid" value="${(schoolBus.isvalid)!}"  class="form-control" title="" type="number">-->
                                       <span class="rdio rdio-warning">
                                                   <input name="bus.isvalid" value="1" class="deldis"
                                                          <#if schoolBus.isvalid??&& schoolBus.isvalid ==0 ><#else >checked="checked"</#if>
                                                          id="radio1" type="radio">
                                                   <label for="radio1">启用</label>
                                               </span>
                                       <span class="rdio rdio-warning">
                                                   <input name="bus.isvalid" value="0" id="radio2" class="deldis"
                                                          <#if schoolBus.isvalid??&& schoolBus.isvalid ==0 >checked="checked"</#if>
                                                          type="radio">
                                                   <label for="radio2">禁用</label>
                                               </span>
                                   </div><!-- /.col -->
                               </div>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-xs-12 col-md-12">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-1">备注</label>
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
                                <#--<td>编号</td>-->
                                <#--<td>姓名</td>-->
                                <#--<td>性别</td>-->
                                <#--<td>民族</td>-->
                                <#--<td>年级</td>-->
                                <#--<td>操作</td>-->

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
                                            <#--退宿-->
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
            alert("请选择学生");
            return;
        }

        if(confirm("确定要退吗")){
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