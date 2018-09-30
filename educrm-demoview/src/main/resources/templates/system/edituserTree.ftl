<#include "../commons/top.ftl">
<#include "../commons/left.ftl">
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>用户 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-success" onClick="javascript:history.back(-1);">返回</a>
        </div>
    </div>
</div>

<div class="contentpanel">
    <div class="panel panel-default">
        <div class="contentpanel">
            <ul class="nav nav-tabs nav-success">
                <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li>
                <li class=""><a data-toggle="tab" href="#two"><strong>分配角色</strong></a></li>
                <li class=""><a data-toggle="tab" href="#three"><strong>tree</strong></a></li>
            </ul>
            <div class="tab-content">
                <div id="all" class="tab-pane active">
                    <form class="look form-ajax" id="formSumbit" method="post" action="/system/sysUser/save"
                          data-target="/system/queryUser">
                        <input type="hidden" name="idcode" value="1">
                        <input type="hidden" name="user.modifier" value="${(sysUser.modifier)!}">
                        <div class="form-group col-sm-4">
                            <label class="control-label col-sm-3">登录名</label>
                            <div class="col-sm-9">
                                <input name="user.pkSysUser" class="form-control" value="${(sysUser.pkSysUser)!}"
                                       type="text" readonly>
                            </div>
                        </div><!-- col-sm-4 -->
                        <div class="form-group col-sm-4">
                            <label class="control-label col-sm-3">用户名</label>
                            <div class="col-sm-9">
                                <input name="user.caption" value="${(sysUser.caption)!}" class="form-control"
                                       type="text">
                            </div>
                        </div><!-- col-sm-4 -->
                        <div class="form-group col-sm-4">
                            <label class="control-label col-sm-3">密码</label>
                            <div class="col-sm-9">
                                <input name="user.password" value="${(sysUser.password)!}" class="form-control"
                                       type="password">
                            </div>
                        </div><!-- col-sm-4 -->


                        <div class="form-group col-sm-4">
                            <label class="control-label col-sm-3">性别</label>
                            <div class="col-sm-9">
                                <select name="user.sex" class="form-control">
                                    <option <#if sysUser.sex == 1>selected="selected"</#if> value="1">男</option>
                                    <option <#if sysUser.sex == 2>selected="selected"</#if> value="2">女</option>
                                </select>
                            </div>
                        </div><!-- col-sm-4 -->

                        <div class="form-group col-sm-4" id="hdcenter">
                            <label class="control-label col-sm-3 ">所属校区</label>
                            <div class="col-sm-9">
                                <select name="user.pkDomain" class="id_select_center form-control" id="selcenter"
                                        class="selectpicker bla bla bli" data-live-search="true">

                                <#list domains as v>
                                    <option value="${(v.pkDomain)!}"
                                            <#if sysUser.pkDomain==v.pkDomain>selected="selected"</#if>>${(v.caption)!}</option>
                                </#list>

                                </select>
                            </div>
                        <#--<input type="hidden" id="centerNumber" name="user.pkDomain" value="${(sysUser.pkDomain)!}" />-->
                        </div><!--col-sm-4  -->
                        <div class="form-group col-sm-4">
                            <label class="control-label col-sm-3 ">所属部门</label>
                            <div class="col-sm-9">
                                <select name="user.pkDepartment" class="form-control" id="deptsellist"
                                        class="selectpicker bla bla bli"
                                        data-live-search="true">

                             <#--   <#list departments as department>
                                    <option value="${(department.pkDepartment)!}"
                                            <#if sysUser.pkDepartment==department.pkDepartment>selected="selected"</#if>>${(department.caption)!}</option>
                                </#list>-->


                                </select>
                            </div>

                        </div><!-- col-sm-4 -->
                        <div class="form-group col-sm-4">
                            <label class="control-label col-sm-3">是否有效</label>
                            <div class="col-sm-9  ckbox ckbox-success">
                                <input name="user.isvalid" <#if sysUser.isvalid ?? && sysUser.isvalid==1>checked="checked"</#if> value="${(sysUser.isvalid)!}" class="out-all" type="checkbox">
                            </div>
                        </div>
                       <#-- <div class="form-group col-sm-4">
                            <label class="control-label col-sm-3 ">所属角色</label>
                            <div class="col-sm-9">

                                <select
                                        class="selectpicker bla bla bli" multiple data-live-search="true"
                                &lt;#&ndash;data-live-search="true"&ndash;&gt; &lt;#&ndash;id="usertype" name="usertype"
                                                               class="selectpicker show-tick form-control" multiple
                                                               data-live-search="flase"&ndash;&gt;>
                                </select>
                                    <option value="1">请选择</option>
                                    <option value="2">请选择</option>
                                    <option value="3">请选择</option>
                                    <option value="4">请选择</option>
                                &lt;#&ndash; <#list sysRoles as dept>&ndash;&gt;
                                &lt;#&ndash;<option value="${dept.code!}">${dept.caption!}</option>
                            &lt;#&ndash;</#list>&ndash;&gt;&ndash;&gt;
                                </select>
                            </div>
                            <input type="hidden" id="rolelist" name=""/>
                        </div>--><!-- col-sm-4 -->
                        <#--<div class="form-group col-sm-4">
                            <label class="control-label">备注</label>
                            <div class="">
                                <textarea name="memo" class="form-group" type="text">
                            </div>
                        </div>--><!-- col-sm-4-->
                        <#-- <div class="form-group col-sm-4">
                             <label class="control-label col-sm-3">创建人</label>
                             <div class="col-sm-9">
                                 <input name="user.creator" value="${(sysUser.creator)!}" class="form-control" type="text">
                             </div>
                         </div>--><!-- col-sm-4 -->
                    <#-- <div class="col-sm-4">-->
                    <#-- <div class="form-group">
                         <label class="control-label">创建日期</label>
                         <input name="user.creationDate" &lt;#&ndash;value="${(sysUser.creationDate?string("yyyy-MM-dd"))!}"&ndash;&gt; class="form-control" type="text">
                     </div>-->
                        <#--</div>--><!-- col-sm-4 -->
                        <#-- <div class="form-group col-sm-4">
                             <label class="control-label col-sm-3">修改人</label>
                             <div class="col-sm-9">
                                 <input name="user.modifer" value="${(sysUser.modifer)!}" class="form-control" type="text">
                             </div>
                         </div>--><!-- col-sm-4 -->
                        <#--<div class="col-sm-3">
                            <div class="form-group">
                                <label class="control-label">修改日期</label>
                                <input name="user.lasteditDate" value="${(sysUser.lasteditDate?string("yyyy-MM-dd"))!}" class="form-control" type="date">
                            </div>
                        </div>--><!-- col-sm-4 -->

                        <div class="form-group">
                            <label class="col-sm-9 control-label no-padding-top"> </label>
                            <div class="pos-rel col-sm-3">
                                <input type="hidden" id="roleList" name="roleList">
                                <input class="btn btn-success btn-sm saveButton" value="保存" type="button">
                                <input class="btn  btn-sm" name="dosubmit" value="取消" type="reset">
                            </div>

                        </div>
                    </form>
                </div><!-- tab-content -->
            <#--2-->
                <div class="tab-pane" id="two">
                    <div class="tree-view-menu-list font-16 open-user">
                        <ul class="subtree">
                        <#list sysRoles as s>

                            <li class="openable">
                                    <span class="ckbox ckbox-success">
                                        <input type="checkbox" <#if s.roleKey ?? && s.roleKey==1>checked="checked"</#if> class="out-all" value="${(s.pkSysRole)!}">
                                    </span>
                                <a href="#" class="ab-left"><i
                                        class="fa fa-minus m-right-xs folder-icon"></i>${(s.caption)!}
                                </a>

                            </li>
                        </#list>
                        </ul>
                    </div>
                </div>
            <#--3-->
              <div class="tab-pane" id="three">
                    <div class="tree-view-menu-list font-16 open-user">
                        <ul id="treeDemo" class="ztree"></ul>
                    </div>
                </div>


            </div>

        </div><!-- panel -->

    </div><!-- contentpanel -->

</div><!-- mainpanel -->



<#include "../commons/footer.ftl" >

<script>
    var setting = {
        view: {
//            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: false
            }
        },
        edit: {
            enable:false
        }
    };

    var zNodes =[
        {id:1, pId:0, name:"第一级", open:true,
            children:[
                {name:"第二级",
                    children:[
                        {name:"第三级",checked:true},
                        {name:"第三级",
                            children:[
                                {name:"第四级",checked:true},
                                {name:"第四级"}]
                        }]
                },
                {name:"test1_2"}]
        }
       /* {id:101, pId:1, name:"市场管理"},
        {id:102, pId:1, name:"市场管理"},
        {id:103, pId:1, name:"市场管理"},
        {id:104, pId:1, name:"市场管理"},
        {id:105, pId:1, name:"市场管理"}

        {id:2, pId:0, name:"市场管理", open:false},
        {id:201, pId:2, name:"市场管理"},
        {id:206, pId:2, name:"市场管理"},
        {id:207, pId:2, name:"市场管理"}*/
    ];

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });

    var newCount = 1;
    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
            return false;
        });
    }
    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_"+treeNode.tId).unbind().remove();
    }

    $(".saveButton").on("click",function(){

        //添加角色
        var role = "";
        $.each($('input:checkbox:checked'),function(){
            role += $(this).val() + ",";
        });
        role=role.substr(0,role.length-1);
        $("#roleList").val(role);




        $("#formSumbit").submit();
    });

</script>