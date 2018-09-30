<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                用户
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" id="formId" method="POST" action="/system/sysUser/save"
                  data-target="/system/sysUser/query">
                <div class="panel panel-default">
                    <ul class="nav nav-tabs nav-success">
                        <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li>
                        <li class=""><a data-toggle="tab" href="#two"><strong>权限分配</strong></a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="all" class="tab-pane active">
                            <div class="form-group row">
                                <label class="control-label col-xs-2 col-md-2">登录名</label>
                                <div class="col-xs-4 col-md-4">
                                    <input name="user.pkSysUser" value="${(sysUser.pkSysUser)!}" class="form-control"
                                           title="" type="text">
                                    <input type="hidden" name="flag" value="${(flag)!}">
                                </div><!-- /.col -->
                                <label class="control-label col-xs-2 col-md-2">用户名</label>
                                <div class="col-xs-4 col-md-4">
                                    <input name="user.caption" class="form-control" value="${(sysUser.caption)!}"
                                           title="" type="text">
                                </div><!-- /.col -->

                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2 col-md-2">密码</label>
                                <div class="col-xs-4 col-md-4">
                                    <input name="user.password" value="${(sysUser.password)!}" class="form-control"
                                           title="" type="text">
                                </div><!-- /.col -->
                                <label class="control-label col-xs-2 col-md-2">性别</label>
                                <div class="col-xs-4 col-md-4">
                                    <select name="user.sex" class="form-control">
                                        <option <#if sysUser.sex?? && sysUser.sex == 1>selected="selected"</#if> value="1">男</option>
                                        <option <#if sysUser.sex?? && sysUser.sex == 0>selected="selected"</#if> value="2">女</option>
                                    </select>
                                </div><!-- /.col -->

                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2 col-md-2">所属校区</label>
                                <div class="col-xs-4 col-md-4">
                                    <select name="user.pkDomain" class="id_select_center form-control" id="selcenter"
                                            class="selectpicker bla bla bli" data-live-search="true">

                                    <#list domains as v>
                                        <option value="${(v.pkDomain)!}"
                                                <#if sysUser.pkDomain?? && sysUser.pkDomain==v.pkDomain>selected="selected"</#if>>${(v.caption)!}</option>
                                    </#list>

                                    </select>
                                </div><!-- /.col -->
                                <label class="control-label col-xs-2 col-md-2">所属部门</label>
                                <div class="col-xs-4 col-md-4">
                                    <select name="user.pkDepartment" class="form-control" id="deptsellist"
                                            class="selectpicker bla bla bli"
                                            data-live-search="true">

                                    <#list departments as department>
                                        <option value="${(department.pkDepartment)!}"
                                                <#if sysUser.pkDepartment?? && sysUser.pkDepartment==department.pkDepartment>selected="selected"</#if>>${(department.caption)!}</option>
                                    </#list>


                                    </select>
                                </div><!-- /.col -->

                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2 col-md-2">备注</label>
                                <div class="col-xs-10 col-md-10">
                                    <textarea class="form-control" name="sysUser.memo" rows="4"
                                              va>${(sysUser.memo)!}</textarea>
                                </div><!-- /.col -->
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2 col-md-2">创建人</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly class="form-control" value="${(sysUser.map.creatorEntity.caption)!}" type="text"/>
                                </div><!-- /.col -->
                                <label class="control-label col-xs-2 col-md-2">创建时间</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly class="form-control"
                                           value="${sysUser.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                                </div><!-- /.col -->
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-2 col-md-2">修改人</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly class="form-control" value="${(sysUser.map.modifierEntity.caption)!}" type="text"/>
                                </div><!-- /.col -->
                                <label class="control-label col-lg-2 col-md-2">修改时间</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly class="form-control"
                                           value="${sysUser.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                                </div><!-- /.col -->
                            </div>
                        </div><!-- tab-content -->
                        <#--2-->
                        <div class="tab-pane" id="two">
                            <div class="tree-view-menu-list font-16 open-user">
                                <#list sysRoles as role>
                                    <input type="checkbox" name="checkboxrole" value="${(role.pkSysRole)!}" <#list userRole as ur><#if ur.pkSysRole?? && role.pkSysRole?? && ur.pkSysRole == role.pkSysRole>checked</#if></#list>>${(role.code)!} - ${(role.caption)!}<br/>
                                </#list>
                            </div>
                        </div>

                    </div>


                </div><!-- contentpanel -->
                <div class="modal-footer">
                    <input type="hidden" id="roleList" name="roleList">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                <input class="btn btn-newblue btn-sm saveButton" id="dosubmit" value="保存" type="button">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">

    $("#dosubmit").click(function () {

        var obj = document.getElementsByName("checkboxrole");
        roleList = "";
        for(k in obj){
            if(obj[k].checked)
                roleList += obj[k].value + ",";
        }
        roleList=(roleList.substring(roleList.length-1)==',')?roleList.substring(0,roleList.length-1):roleList;
        $("#roleList").val(roleList);
        var dataForm = $("#formId").serialize();
        $.ajax({
            url:"/system/sysUser/save",
            data:dataForm,
            type:"POST",
            sync:false,
            dataType:"json",
            traditional: true,
            success:function (data) {
                if(data.code ==0){
                    window.location.reload();
                }else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            }
        })
    });

    $('.form-ajax').bootstrapValidator().on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function (data) {

            if (data.code == 0) {
                Notify.success(data.message);
                if ($form.data('target')) {
                    setTimeout("window.location.href='" + $form.data('target') + "'", 3000);
                }
            } else {
                Notify.danger(data.message);
                location.reload();
            }
        }, 'json');
    });
</script>