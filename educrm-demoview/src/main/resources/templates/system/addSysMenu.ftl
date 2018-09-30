<#include "../commons/top.ftl"/>
<#include "../commons/left.ftl"/>

<!-- leftpanel -->


<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>用户管理 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <span class="label">你的位置:</span>
        <ol class="breadcrumb">
            <li><a href="../index.html">返回</a></li>
            <li class="active">用户管理</li>
        </ol>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <div class="btn-group mr5">
                    <a href="/system/sysUser/create" class="btn btn-info btn-sm add-new-event" data-toggle="modal"><i
                            class="fa fa-plus"></i>创建用户</a>
                </div>
                <form id="searchForm" action="/system/admin/search" method="GET" class="form-inline search ">
                    <div class="form-group">
                        <label>用户昵称</label>
                        <div class="btn-group">
                            <input class="form-control" name="loginName" value="${searchName!}" title=""
                                   placeholder="请输入查询内容">
                        </div>
                    </div>
                    <div class="form-group">
                        <span id="searchbtn" class="btn btn-info btn-sm add-new-event" data-toggle="modal"><i
                                class="fa fa-search"> </i>搜索</span>
                    </div>
                </form>
                <#--  <div class="panel-btns">
                      <a href="" class="minimize news-minimize">搜索<i class=" fa fa-chevron-down"></i></a>
                  </div>--><!-- panel-btns -->

            </div>
            <div class="panel-body senior-search">
                <div class="tab-content">
                    <div id="post-status" class="tab-pane active">
                        <input type="text" class="form-control" placeholder="What's your status?">
                    </div>
                </div><!-- tab-content -->

            </div><!-- panel-body -->
        </div>
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="30">
                            <input type="checkbox" id="chAll">
                        </th>
                        <th>操作</th>
                    <#--<th>用户ID</th>-->
                        <th>用户名称</th>
                        <th>所属部门</th>
                        <th>真实姓名</th>
                        <th>性别</th>
                        <th>所属角色</th>
                        <th>创建时间</th>
                        <th>离职时间</th>


                    </tr>
                    </thead>
                    <tbody>

                    <#list list as list>
                    <tr>
                        <td>
                            <input type="checkbox" class="px">
                        </td>
                        <td>
                            <span>
                                <a href="/system/sysUser/edit?pkSysUser=${list.pkSysUser}" class="btn btn-success btn-xs add-new-event" data-toggle="modal"><i class="fa fa-plus"></i>查看</a>
                            </span>
                            <span>
                                <a class="btn btn-danger btn-xs " href="#" class="del" onclick="del('${list.pkSysUser}')"
                                   data-toggle="modal"><#--${list.pkSysUser}-->删除</a>
                            </span>
                        </td>

                        <td>${list.pkSysUser}</td>

                        <td>${list.pkDepartment}</td>
                        <td>${list.caption}</td>
                        <td><#if list.sex ==1>男<#else >女</#if></td>
                        <td>aaaa</td>
                        <td>${list.creationDate ? string("yyyy-MM-dd HH:mm")}</td>
                        <td>${list.lasteditDate? string("yyyy-MM-dd HH:mm")}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>

            </div><!-- table-responsive -->
        </div><!-- panel-body -->
    </div><!-- panel -->

</div><!-- contentpanel -->
</div>


<!--创建-->
<div class="modal fade" id="addNewEventModal-1">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">创建用户</h4>
            </div>
            <div class="modal-body">
                <div class="smart-widget-inner">
                    <div class="smart-widget-body">
                        <form class="form-horizontal no-margin form-ajax" id="fromid" action="/system/sysUser/create" method="POST">
                            <div class="form-group">
                                <label class="control-label col-lg-3">账号名</label>
                                <div class="col-lg-9">
                                    <input type="text" class="form-control" name="pkSysUser" data-bv-notempty
                                           data-bv-notempty-message="请录入账号名" title="" placeholder="限20个字符">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-3">密码</label>
                                <div class="col-lg-9">
                                    <input type="password" class="form-control" name="password" data-bv-notempty
                                           data-bv-notempty-message="请录入密码" title="" placeholder="至少6个字符">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-3">真实姓名</label>
                                <div class="col-lg-9">
                                    <input type="text" class="form-control" name="caption" data-bv-notempty
                                           data-bv-notempty-message="请录入真实姓名" title="" placeholder="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-lg-3">性别</label>
                                <div class="col-lg-9">
                                    <select class="form-control " name="sex">

                                        <option value='1'>男</option>
                                        <option value='2'>女</option>

                                    </select>
                                </div>
                            </div>
                        <#-- <div class="form-group">
                             <label class="control-label col-lg-3">学历</label>
                             <div class="col-lg-9">
                                 <select class="form-control " name="gender">

                                     <option value='1'>博士</option>
                                     <option value='2'>硕士</option>
                                     <option value='3'>本科</option>
                                     <option value='4'>大专</option>
                                     <option value='5'>大专以下</option>

                                 </select>
                             </div>
                         </div>-->
                            <div class="form-group" id="hdcenter">
                                <label class="control-label col-lg-3">所属校区</label>
                                <div class="col-lg-9">
                                    <select class="id_select_center form-control" id="selcenter" value=""
                                            class="selectpicker bla bla bli" data-live-search="true">

                                    </select>
                                </div>
                                <input type="hidden" id="centerNumber" name="pkDomain" value=""/>
                            </div>


                            　　　　　　　　　　　　　
                            <div class="form-group">
                                <label class="control-label col-lg-3">所属部门</label>
                                <div class="col-lg-9">
                                    <select class="form-control" id="deptsellist" value=""
                                            class="selectpicker bla bla bli"
                                            data-live-search="true">
                                        <option value="">请选择</option>
                                   <#-- <#list departments as dept>
                                        <option value="${dept.code!}">${dept.caption!}</option>
                                    </#list>-->
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="deptlist" name="department" value=""/>
                            <div class="form-group">
                                <label class="control-label col-lg-3">所属角色</label>
                                <div class="col-lg-9">
                                <#-- <select class="form-control" id="deptrole" multiple data-live-search="true">

                                 </select>-->
                                    <select class="form-control" id="deptrole" value=""
                                            class="selectpicker bla bla bli"
                                            data-live-search="true">
                                        <option value="">请选择</option>
                                    <#--<#list sysRoles as dept>
                                        <option value="${dept.code!}">${dept.caption!}</option>
                                    </#list>-->
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="rolelist" name="pkSysRole" value=""/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="submit"  class="btn btn-info">确定</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<!--删除-->
<div class="modal fade" id="addNewEventModal-3">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">删除</h4>
            </div>
            <div class="modal-body">
                <div class="smart-widget-inner">
                    <div class="smart-widget-body">
                        <form class="form-horizontal no-margin" data-validate="parsley" action="/system/deleteuser"
                              novalidate>
                            <input type="hidden" id="pkSysUser" value="">
                            <div class="form-group">
                                <label class="control-label-center col-lg-12">确定要删除？</label>
                            </div><!-- /form-group -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="submit" id="delUser" class="btn btn-info">确定</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<!--修改-->
<div class="modal fade" id="addNewEventModal-4">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">修改</h4>
            </div>
            <div class="modal-body">
                <div class="smart-widget-inner">
                    <div class="smart-widget-body">
                        <form class="form-horizontal no-margin" action="/system/admin/edit" method="POST"
                              data-validate="parsley" novalidate>
                            <div class="form-group">
                                <label class="control-label col-lg-3">账号名</label>
                                <div class="col-lg-9">
                                    <div class="col-lg-9">
                                        <span id="uploginname" name="loginName"></span>
                                    </div><!-- /.col -->

                                    <input type="hidden" name="id" id="upid">
                                </div><!-- /.col -->
                            </div><!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-lg-3">新密码</label>
                                <div class="col-lg-9">
                                    <input type="password" name="passWord" data-bv-identical
                                           data-bv-identical-field="passWordAffirm" data-bv-identical-message="密码要保持一致"
                                           class="form-control" title="" id="passWord" placeholder="********">
                                </div><!-- /.col -->
                            </div><!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-lg-3">再次输入新密码</label>
                                <div class="col-lg-9">
                                    <input type="password" name="passWordAffirm" data-bv-identical
                                           data-bv-identical-field="passWord" data-bv-identical-message="密码要保持一致"
                                           class="form-control" title="" id="passWordAffirm" placeholder="********">
                                </div><!-- /.col -->
                            </div><!-- /form-group -->
                            <div class="form-group">
                                <label class="control-label col-lg-3">真实姓名</label>
                                <div class="col-lg-9">
                                    <input type="text" id="uprealname" data-bv-notempty
                                           data-bv-notempty-message="请录入真实姓名" name="realName" class="form-control"
                                           title="" placeholder="">
                                </div><!-- /.col -->
                            </div><!-- /form-group -->
                            <div class="form-group" id="uhdcenter">
                                <label class="control-label col-lg-3">所属校区</label>
                                <div class="col-lg-9">
                                    <select class="id_select_center form-control" id="upselcenter" value=""
                                            class="selectpicker bla bla bli" data-live-search="true">

                                    </select>
                                </div>
                                <input type="hidden" id="upcenterNumber" name="fromcenter" value=""/>
                            </div>
                            　　　　　　　　　　　　　
                            <div class="form-group">
                                <label class="control-label col-lg-3">所属部门</label>
                                <div class="col-lg-9">
                                    <select class="form-control" id="updeptsellist" value=""
                                            data-live-search="true">
                                        <option value="">请选择</option>
                                   <#-- <#list departments as dept>
                                        <option value="${dept.code!}">${dept.caption!}</option>
                                    </#list>-->
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="updeptlist" name="department" value=""/>
                            <div class="form-group">
                                <label class="control-label col-lg-3">部门角色</label>
                                <div class="col-lg-9">
                                    <select class="form-control id_select" id="updeptrole" value="" multiple
                                            data-live-search="true">
                                    <#--<#list deptsel as dept>
                                        <option value="${dept.id!}">${dept.name!}</option>
                                    </#list>-->
                                    </select>
                                </div>
                            </div>
                            <input type="hidden" id="uprolelist" name="roleids" value=""/>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="submit" id="upsunmitbtn" class="btn btn-info">确定</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>



<#include "../commons/footer.ftl" />


<script type="text/javascript">
    $(document).ready(function () {
        //时间选择器
        /* $('#gj').daterangepicker(null, function (start, end, label) {
             console.log(start.toISOString(), end.toISOString(), label);
         });*/
        //下拉框
        /*$('.selectpicker').selectpicker({});*/

    <#--if (${fromcenter!}!= ${groupid}){
         $("#hdcenter").css({"display": "none"})
         $("#uhdcenter").css({"display": "none"})

         $(".id_select_center").val(${fromcenter!});
         $("#centerNumber").val(${fromcenter!});
     }-->

        /* $.ajax({
             type: "GET",
             url: "",
             data: "",
             dataType: "json",
             success: function (data) {
                 var html = '';
                 html += '<option value=""> 请选择</option>';
                 if (data.errorNo == 0) {
                     var json = data.data;
                     for (var i = 0; i < json.length; i++) {
                         html += '<option value="' + json[i].id + '">' + json[i].name + '</option>';
                     }
                 }
                 $(".id_select_center").html('');
                 $(".id_select_center").html(html);
                 $('.id_select_center').selectpicker('refresh');
             },
             error: function () {

             }

         });*/
    });

    $("#submitbtn").click(function () {
        var checkrole = $("#deptrole").val();
        $("#rolelist").val(checkrole);
        var checkcenter = $("#selcenter").val();
        $("#centerNumber").val(checkcenter);
        var checkdept = $("#deptsellist").val();
        $("#deptlist").val(checkdept);
        $("#fromid").submit();
    });
</script>
<script type="text/javascript">
    function del(id) {

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/sysUser/delete",
                data: {"pkSysUser": id},
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


