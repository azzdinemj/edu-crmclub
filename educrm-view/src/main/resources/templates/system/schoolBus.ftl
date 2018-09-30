


<#include "../commons/top.ftl" />
<link rel="stylesheet" type="text/css" href="${staticPath}/css/bus.css">
<#include "../commons/left.ftl" />



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
                    <a class="btn btn-newblue btn-sm" onclick="subLine()">
                        保存路线
                    </a>
                </div>
            </div>
        </div>
        <div class="panel panel-default">


            <div class="contentpanel">
                <ul class="nav nav-tabs nav-yellow">
                    <li class="active"><a data-toggle="tab" href="#one"><strong>校车信息</strong></a></li>

                    <li class="" ><a data-toggle="tab" href="#two"><strong>学生信息</strong></a></li>
                    <li class="" ><a data-toggle="tab" href="#three"><strong>校车路线</strong></a></li>


                </ul>
                <div class="tab-content">
                    <div id="one" class="tab-pane active">
                        <form class="form-ajax form-horizontal no-margin look" id="formid" method="post" action="/system/schoolBus/save" data-target="/system/schoolBus/query">

                        <input type="hidden" name="bus.pkDomain" value="${(schoolBus.pkDomain)!}">
                        <input type="hidden" name="bus.creator" value="${(schoolBus.creator)!}">
                        <input type="hidden" id="bykey" name="bykey" value="${(bykey)!}">
                        <div class="row">
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <img class="del" src="${staticPath}/images/delete.png">
                                   <label class="control-label col-xs-3 col-md-3">车牌号</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.pkSchoolBus" id="pkSchoolBus" class="form-control" value="${(schoolBus.pkSchoolBus)!}"
                                              data-bv-notempty data-bv-notempty-message="请录入车牌号" title="" type="text">
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
                           <#--<div class="col-xs-12 col-md-4">-->
                               <#--<div class="form-group">-->
                                   <#--<label class="control-label col-xs-3 col-md-3">车牌号</label>-->
                                   <#--<div class="col-xs-9 col-md-9">-->
                                       <#--<input name="bus.plateNumber" value="${(schoolBus.plateNumber)!}" class="form-control" title="" type="text">-->
                                   <#--</div><!-- /.col &ndash;&gt;-->
                               <#--</div>-->
                           <#--</div>-->

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
                                   <label class="control-label col-xs-3 col-md-3">校外发车时间</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.offschDepartDateTime" value="${(schoolBus.offschDepartDate?string("hh:mm"))!}" class="form-control js-datepickertime" title="" data-bv-notempty data-bv-notempty-message="请录入校外发车时间" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">校内发车时间</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.schoolDepartDateTime" value="${(schoolBus.schoolDepartDate?string("hh:mm"))!}" class="form-control js-datepickertime" title="" data-bv-notempty data-bv-notempty-message="请录入校内发车时间" >
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">校车价格</label>
                                   <div class="col-xs-9 col-md-9">
                                       <input name="bus.expenses" value="${(schoolBus.expenses?c)!}" class="form-control"
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
                                   <label class="control-label col-xs-3 col-md-3">司机</label>
                                   <div class="col-xs-9 col-md-9">
                                       <select name="bus.driver">
                                           <option value="">请选择</option>
                                           <#if drivers ??>
                                               <#list drivers as d>
                                                   <option value="${(d.pkEmployee)!}" <#if schoolBus.driver?? &&d.pkEmployee?? && d.pkEmployee==schoolBus.driver>selected</#if>>${(d.caption)!}</option>
                                               </#list>
                                           </#if>
                                       </select>
                                   </div><!-- /.col -->
                               </div>
                           </div>
                           <div class="col-xs-12 col-md-4">
                               <div class="form-group">
                                   <label class="control-label col-xs-3 col-md-3">监护老师</label>
                                   <div class="col-xs-9 col-md-9">
                                       <select name="bus.guardianTeacher">
                                           <option value="bus.guardianTeacher">请选择</option>
                                       <#if guardianTeachers ??>
                                           <#list guardianTeachers as g>
                                               <option value="${(g.pkEmployee)!}" <#if schoolBus.guardianTeacher?? &&g.pkEmployee?? && g.pkEmployee==schoolBus.guardianTeacher>selected</#if>>${(g.caption)!}</option>
                                           </#list>
                                       </#if>
                                       </select>
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
                        </form>
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
                    <div id="three" class="tab-pane">
                        <#--<table id="linepagingTable" class="table table-striped table-bordered" cellspacing="0">-->
                            <#--<form id="lineId" class=" form-horizontal no-margin look" >-->
                            <#--<input name="schoolbusId" class="form-control" value="${(schoolBus.pkSchoolBus)!}" title="" type="hidden">-->
                            <#--<div class="col-sm-3">-->
                                <#--<input name="lines[0].stationname" class="form-control" title="" type="text">-->
                            <#--</div>-->
                            <#--<div class="col-sm-3">-->
                                <#--<input name="lines[1].stationname" class="form-control" title="" type="text">-->

                            <#--</div>-->
                        <#--</form>-->
                        <#--</table>-->
                            <div id="msgBox">

                                <div class="list rescontent">
                                    <h3 class="addresstitle">出校路线<span>(按照顺序)</span></h3>
                                    <ul >
                                        <#--<#list list as list >-->

                                        <#--</#list>-->

                                       <li class="box" style="margin-right: 15px;">
                                            <div class="content address">
                                                <div class="addbtn"><a class="blue" id="oBlue" data-toggle="modal" data-target="#myModal">+添加站点</a></div>
                                            </div>
                                        </li>
                                       <li >
                                            <div class="content address">
                                                <div id="lineBtn" class="addbtn"><a class=" blue" type="button">生成反向路线</a></div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="clearfix"></div>
                                    <div class="list rescontent">
                                    <h3>反向路线</h3>
                                    <ul >
                                        <#--<#list list as list >-->

                                        <#--</#list>-->

                                       <li class="reversebox">
                                            <#--<div class="content address">-->
                                                <#--<div class="addbtn"><a class="blue" id="oBlue" data-toggle="modal" data-target="#myModal">+添加站点</a></div>-->
                                            <#--</div>-->
                                        </li>
                                       <#--<li >-->
                                            <#--<div class="content address">-->
                                                <#--<div id="lineBtn"><a class="blue" type="button">生成反向路线</a></div>-->
                                            <#--</div>-->
                                        <#--</li>-->
                                    </ul>
                                    </div>
                                </div>

                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title" id="myModalLabel">编辑站点</h4>
                                            </div>
                                            <form id="formModelId">
                                                <input id="indexNoId" name="indexno" type="hidden" class="form-control delvalue">
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <label class="col-xs-2 control-label">站点序号：</label>
                                                        <div class="col-xs-10"><input id="stationid" type="number" name="stationid" class="f-text form-control" value="" /></div>

                                                    </div>
                                                     <br>
                                                    <div class="row">
                                                        <label class="col-xs-2 control-label">站点名称：</label>
                                                        <div class="col-xs-10"><input id="stationname" name="stationname" class="f-text form-control"></div>
                                                    </div>
                                                    <br>
                                                    <div class="row">
                                                        <label class="col-xs-2 control-label">站点类型：</label>
                                                        <div class="col-xs-10">
                                                            <select name="type" id="typeid">
                                                                <option id="optionId2" value="2">中间站</option>
                                                                <option id="optionId1" <#--style="display: none"--> value="1">起始站</option>
                                                                <option id="optionId3" <#--style="display: none"--> value="3">终点站</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">取消</button>
                                                    <input id="buttonId"  class="btn btn-sm btn-info" type="button" value="确定" />
                                                </div>
                                            </form>
                                        </div><!-- /.modal-content -->
                                    </div><!-- /.modal -->
                                </div>

                            </div>
                    </div>

            </div>
                </div>
        </div><!-- contentpanel -->

    </div><!-- mainpanel -->






<#include "../commons/footer.ftl"/>
<#--<script src="${staticPath}/js/bus.js"></script>-->

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

    function subLine() {
//        $("#lineId").submit();
        var formData = $("#lineId").serialize();
        $.ajax({
            type:"post",
            url: "/shuttle/schoolbusLine/save",
            data: formData,
            traditional:true,
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

    var startingstation = 0;
    var terminus = 0;

    function initializationModel() {
        $( "body" ).delegate(".addreschan","mouseover",function(){
            $(this).find(".del").addClass("shows").removeClass("del")
        });

        $( "body" ).delegate(".addreschan","mouseout",function(){
            $(this).find(".shows").addClass("del").removeClass("shows")
        });

        $.ajax({
            type: "GET",
            url: "/shuttle/schoolbusLine/queryByPaging",
            data: {"schoolbusId":"${(schoolBus.pkSchoolBus)!}"},
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    var html = "";
                    var reverseHtml = "";
                    var lines = data.data[0];
                    var reverseLines = data.data[1];
                    $.each(lines,function (i) {
                        var stat = "";
                        if (lines[i].stationid==0){
                            startingstation=1;
                            stat = "始发站";
                        }
                        else if (lines[i].stationid==-1){
                            terminus=1;
                            stat = "终点站";
                        }else {
                            stat = ""+ lines[i].stationid;
                        }

                        html+= "<li class='liId'><div class=\"addreschan\">"+
                                "<b class=\"userName\">" + stat + "</b>"+
                                "<input type='hidden' value="+lines[i].schoolbusId+" />"+
                                "<input type='hidden' value="+lines[i].direction+" />"+
                                "<input type='hidden' value="+lines[i].stationid+" />"+
                                "<input type='hidden' value="+lines[i].stationname+" />"+
                                "<input type='hidden' value="+lines[i].indexno+" />"+
                                "<a onclick='showModel(this)' class=\"msginfo blue\">" + lines[i].stationname + "</a>"+
                                "<img class=\"del\" onclick='delLine(this)' id=\'DEL\' src=\"../../images/delete.png\">"+
                                "</div></li>";

                    });
                    $('.box').before(html);
                    $.each(reverseLines,function (i) {

                        var stat = "";
                        if (reverseLines[i].stationid==0){
                            stat = "始发站";
                        }
                        else if (reverseLines[i].stationid==-1){
                            stat = "终点站";
                        }else {
                            stat = ""+ reverseLines[i].stationid;
                        }

                        reverseHtml+= "<li class='liId'><div class=\"addreschan\">"+
                                "<b class=\"userName\">" + stat + "</b>"+
                                "<input type='hidden' value="+reverseLines[i].schoolbusId+" />"+
                                "<input type='hidden' value="+reverseLines[i].direction+" />"+
                                "<input type='hidden' value="+reverseLines[i].stationid+" />"+
                                "<input type='hidden' value="+reverseLines[i].stationname+" />"+
                                "<input type='hidden' value="+reverseLines[i].indexno+" />"+
                                "<a onclick='showModel(this)' class=\"msginfo blue\">" + reverseLines[i].stationname + "</a>"+
                                "<img class=\"del\" onclick='delLine(this)' id=\'DEL\' src=\"../../images/delete.png\">"+
                                "</div></li>";

                    });
                    $('.reversebox').before(reverseHtml);
                } else {
                    alert(data.message);
                    window.location.reload();
                }
            },
            error: function () {

            }
        });
    }

    $(document).ready(function(){

        initializationModel();
        showOrHide();

    });

    function showOrHide() {
        $("#optionId1").attr("style","display:none");
        $("#optionId2").attr("style","display:none");
        $("#optionId3").attr("style","display:none");
        $("#stationid").attr("disabled","disabled");
    }

   function showModel(obj) {
       showOrHide();
       var stationid = $(obj).parent().find("input").eq(2).val()
       $("#stationid").val(stationid);
       $("#stationname").val($(obj).parent().find("input").eq(3).val());
       $("#indexNoId").val($(obj).parent().find("input").eq(4).val());

       if(stationid == "-1"){
           var type = 3;


       }
       else if(stationid == "0"){
           var type = 1;
       }
       else{
           var type = 2;
           $("#stationid").attr("disabled",null);
       }



       $("#typeid").val(type);

       $("#myModal").modal('show');
   }
   
   $("#buttonId").click(function () {
       var stationidse = $("#typeid").val();
       var formData= $("#formModelId").serialize()+"&schoolbusId=${(schoolBus.pkSchoolBus)!}";


        $.ajax({
            type: "POST",
            url: "/shuttle/schoolbusLine/save",
            data: formData,
            dataType: "json",
            success:function (data) {
                if (data.code == 0){
                    Notify.success(data.message);
                    $("#stationid").val("");
                    $("#stationname").val("");
                    $("#indexNoId").val("");
                    $("#myModal").modal('hide');
                    $(".liId").remove();
                    if(stationidse ==1){
                        //起始站

                        $("#optionId1").attr("style","display:none");
                    }
                    if(stationidse==3){
                        //终点站
                        $("#optionId3").attr("style","display:none");
                    }
                    initializationModel();

                }else {
                    Notify.danger(data.message);
                    $("#stationid").val("");
                    $("#stationname").val("");
                    $("#indexNoId").val("");
                    $("#myModal").modal('hide');
                    $(".liId").remove();
                    initializationModel();
                }
            }
        });
    });

   function delLine(obj) {

       var stationid = $(obj).parent().find("input").eq(2).val();
       if (stationid==0 || stationid ==-1){
           Notify.danger("起点站和终点站不允许删除！！！");
           return false;
       }
       var schoolbusId = "${(schoolBus.pkSchoolBus)!}";
       var direction = $(obj).parent().find("input").eq(1).val();
       var stationname = $(obj).parent().find("input").eq(3).val();

       $.ajax({
           type: "POST",
           url: "/shuttle/schoolbusLine/delete",
           data: {"direction":direction,"schoolbusId":schoolbusId,"stationid":stationid,"stationname":stationname},
           dataType: "json",
           success:function (data) {
               if (data.code == 0){
                   Notify.success(data.message);
                   $(".liId").remove();
                   initializationModel();

               }else {
                   Notify.danger(data.message);
                   $(".liId").remove();
                   initializationModel();
               }
           }
       });
   }

    /**
     * 生成反向校车路线
     */
   $("#lineBtn").click(function () {
       if (confirm("确定生成反向路线吗？")){
           $.ajax({
               type: "POST",
               url: "/shuttle/schoolbusLine/reverseLines",
               data: {"schoolbusId":"${(schoolBus.pkSchoolBus)!}"},
               dataType: "json",
               success:function (data) {
                       if (data.code == 0) {
                           Notify.success(data.message);
                           $(".liId").remove();
                           initializationModel();

                   }else {
                       Notify.danger(data.message);
                       $(".liId").remove();
                       initializationModel();
                   }
               }
           });
       }
   });

   $("#oBlue").click(function () {
//       indexNoId  stationid  stationname typeid
       $("#indexNoId").val('');
       $("#stationid").val('');
       $("#stationname").val('');
       $("#typeid").val('');
//                        var startingstation = 0;
//                        var terminus = 0;
       $("#optionId2").attr("style","");
       $("#stationid").attr("disabled",null);
       if (startingstation ==0){
           $("#optionId1").attr("style","");
       }
       if (terminus== 0){
           $("#optionId3").attr("style","");
       }
   })
</script>