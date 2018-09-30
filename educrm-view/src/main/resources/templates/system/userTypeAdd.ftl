<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>多身份用户<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>-->
                <a class="btn btn-sm btn-newblue" id="saveBtn">保存</a>

            </div>
        </div>
    </div>
    <form class="look form-ajax form-horizontal" id="formId" method="POST" action="/system/sysUser/saveUserType"
          data-target="/system/sysUser/query">
        <div class="panel panel-default">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li>
                <#--<li class=""><a data-toggle="tab" href="#two"><strong>权限分配</strong></a></li>-->
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">
                    <div class="row">
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">登录名</label>
                            <div class="col-xs-4 col-md-4">
                                <input name="user.pkSysUser" value="${(sysUser.pkSysUser)!}" class="form-control"
                                       placeholder="请输入手机号"  class="frm_input mobile" maxlength="11"   title="" type="text">
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">用户名</label>
                            <div class="col-xs-4 col-md-4">
                                <input name="user.caption" class="form-control" value="${(sysUser.caption)!}"
                                       title="" type="text">
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">密码</label>
                            <div class="col-xs-4 col-md-4">
                                <input name="user.password" value="" class="form-control"
                                       title="" type="text">
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">性别</label>
                            <div class="col-xs-4 col-md-4">
                                <select name="user.sex" class="form-control">
                                    <option <#if sysUser.sex?? && sysUser.sex == 1>selected="selected"</#if> value="1">男</option>
                                    <option <#if sysUser.sex?? && sysUser.sex == 0>selected="selected"</#if> value="2">女</option>
                                </select>
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">所属公司</label>
                            <div class="col-xs-4 col-md-4">
                                <select name="user.pkDomain" class="id_select_center form-control" id="selcenter"
                                        class="selectpicker bla bla bli" data-live-search="true">

                                <#list domains as v>
                                    <option value="${(v.pkDomain)!}"
                                            <#if sysUser.pkDomain?? && sysUser.pkDomain==v.pkDomain>selected="selected"</#if>>${(v.caption)!}</option>
                                </#list>

                                </select>
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
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
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">用户类型</label>
                            <div class="col-xs-4 col-md-4">
                            <#list userTypeList as users>
                                <div class="checkbox">
                                    <label>
                                    <#--<#list split as s><#if s = users.pkSysDictValues>checked</#if></#list>-->
                                        <input value="${(users.pkSysDictValues)!}" class="updateUserType" type="checkbox" >&nbsp;&nbsp;${(users.caption)!}
                                    </label>
                                </div>
                            <#--<option value="${(v.pkDomain)!}"-->
                            <#--<#if sysUser.pkDomain?? && sysUser.pkDomain==v.pkDomain>selected="selected"</#if>>${(v.caption)!}</option>-->
                            </#list>
                                <input type="hidden" id="userType" name="userType">
                            </div><!-- /.col -->
                        </div>
                    </div>
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-2 col-md-2">备注</label>
                                <div class="col-xs-10 col-md-10">
                                        <textarea class="form-control" name="sysUser.memo" rows="4"
                                                  va>${(sysUser.memo)!}</textarea>
                                </div><!-- /.col -->
                            </div>

                        </div>
                    </div>
                    <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-2">创建人</label>
                            <div class="col-md-8">
                                <input readonly class="form-control" value="${(sysUser.map.creatorEntity.caption)!}" type="text"/>
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-2">创建时间</label>
                            <div class="col-md-8">
                                <input readonly class="form-control"
                                       value="${sysUser.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-2">修改人</label>
                            <div class="col-md-8">
                                <input readonly class="form-control" value="${(sysUser.map.modifierEntity.caption)!}" type="text"/>
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label class="control-label col-md-2">修改时间</label>
                            <div class="col-md-8">
                                <input readonly class="form-control"
                                       value="${sysUser.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                            </div><!-- /.col -->
                        </div>
                    </div>

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
            </div>


        </div><!-- contentpanel -->
        <#--<div class="modal-footer">-->
            <#--<input type="hidden" id="roleList" name="roleList">-->
            <#--<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>-->
        <#--&lt;#&ndash;<button type="submit" class="btn btn-success btn-sm">确定</button>&ndash;&gt;-->
            <#--<input class="btn btn-newblue btn-sm saveButton" id="dosubmit" value="保存" type="button">-->
        <#--</div>-->
    </form>
</div>


<#include "../commons/footer.ftl"/>
<script>
    $(function(){
        $(".updateUserType").on("change",function(){
            var userType = "";
            $.each($('input:checkbox:checked'),function(){
                userType += $(this).val() + ",";
            });
            userType=userType.substr(0,userType.length-1);
            $("#userType").val(userType);
        });

        $("#saveBtn").on("click",function(){
            $("#formId").submit();
        })
    });
</script>
