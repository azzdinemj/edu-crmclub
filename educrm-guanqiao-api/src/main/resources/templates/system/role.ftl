<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                角色
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" id="formSumbit" method="POST" action="/system/sysRole/save"
                  data-target="/system/sysRole/query">
                <div class="panel panel-default">
                    <ul class="nav nav-tabs nav-success">
                        <li class="active"><a data-toggle="tab" href="#all"><strong>基本信息</strong></a></li>
                        <li class=""><a data-toggle="tab" href="#two"><strong>权限分配</strong></a></li>
                        <li class=""><a data-toggle="tab" href="#three"><strong>部门权限</strong></a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="all" class="tab-pane active">
                            <input type="hidden" name="role.pkDomain" value="${(role.pkDomain)!}">
                            <input type="hidden" id="pkSysRole" name="role.pkSysRole" value="${(role.pkSysRole)!}">
                            <div class="form-group row">
                                <label class="control-label col-xs-2 col-md-2">角色编号</label>
                                <div class="col-xs-4 col-md-4">
                                    <input name="role.code" value="${(role.code)!}" class="form-control"
                                           title="" type="text">
                                </div><!-- /.col -->
                                <label class="control-label col-xs-2 col-md-2">角色名称</label>
                                <div class="col-xs-4 col-md-4">
                                    <input name="role.caption" class="form-control" value="${(role.caption)!}"
                                           title="" type="text"
                                           data-bv-notempty data-bv-notempty-message="请录入角色名称...">
                                </div><!-- /.col -->

                            </div>
                            <div class="form-group row">
                                <label class="control-label col-xs-2 col-md-2">备注</label>
                                <div class="col-xs-10 col-md-10">
                                    <textarea class="form-control" name="role.memo" rows="4"
                                              va>${(role.memo)!}</textarea>
                                </div><!-- /.col -->
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-xs-2 col-md-2">创建人</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly name="role.creator" class="form-control" value="${(role.map.creatorEntity.caption)!}" type="text"/>
                                </div><!-- /.col -->
                                <label class="control-label col-xs-2 col-md-2">创建时间</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly name="role.creationDateTime" class="form-control"
                                           value="${role.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                                </div><!-- /.col -->
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-xs-2 col-md-2">修改人</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly name="role.modifier" class="form-control" value="${(role.map.modifierEntity.caption)!}" type="text"/>
                                </div><!-- /.col -->
                                <label class="control-label col-xs-2 col-md-2">修改时间</label>
                                <div class="col-xs-4 col-md-4">
                                    <input readonly name="role.lasteditDateTime" class="form-control"
                                           value="${role.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                                </div><!-- /.col -->
                            </div>
                        </div><!-- tab-content -->
                        <#--2-->
                        <div class="tab-pane" id="two">
                            <div class="tree-view-menu-list font-16 open-user">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="tab-pane" id="three">
                            <div class="tree-view-menu-list font-16 open-user">
                                <ul id="treedept" class="ztree"></ul>
                            </div>
                        </div>

                    </div>


                </div><!-- contentpanel -->
                <div class="modal-footer">
                    <input type="hidden" id="menuList" name="menuList">
                    <input type="hidden" id="deptList" name="deptList">
                <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                <input class="btn btn-newblue btn-sm saveButton" id="dosubmit" value="保存" type="submit">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script type="text/javascript">
    $("#dosubmit").click(function () {
       var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var checkedNodes = treeObj.getCheckedNodes(true);
        var menuArray= [];
        for(var i = 0;i<checkedNodes.length;i++){
            var nodes = checkedNodes[i];
            var str = nodes.children;
            if(str==null || str.length ==0){
                var parentnode = nodes.getParentNode();
                var checkedNode= {pkSysMenu:parentnode.id,pkSysButton:nodes.id};
                menuArray.push(checkedNode);
            }
        }
        $("#menuList").val(JSON.stringify(menuArray));
        //部分权限
        var depttreeObj = $.fn.zTree.getZTreeObj("treedept");
        var deptcheckedNodes = depttreeObj.getCheckedNodes(true);
        var deptArray= [];
        for(var i = 0;i<deptcheckedNodes.length;i++){
            var nodes = deptcheckedNodes[i];
            var str = nodes.children;
            if(str==null || str.length ==0){
                var parentnode = nodes.getParentNode();
                var checkedNode= {pkDepartment:nodes.id};
                deptArray.push(checkedNode);
            }
        }
        $("#deptList").val(JSON.stringify(deptArray));
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
            }
        }, 'json');
    });

    ///tree=============================================//
    var setting = {
        isSimpleData: true,              //数据是否采用简单 Array 格式，默认false
        treeNodeKey: "id",               //在isSimpleData格式下，当前节点id属性
        treeNodeParentKey: "pId",        //在isSimpleData格式下，当前节点的父节点id属性
        showLine: true,                  //是否显示节点间的连线


        view: {
            //addHoverDom: addHoverDom,
            // removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        check: {
            enable: true,
            chkStyle: "checkbox"
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: false,
            showRemoveBtn: false,
            showRenameBtn: false
        },
        callback: {

            //        onClick : zTreeOnClickRight,
            //         beforeRemove: beforeRemove,
//            onRename: zTreeOnRename,
            //        onRemove: zTreeOnRemove
        }
    };
    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, ${power});

        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes();
        for (var i = 0; i < nodes.length; i++) {
            // treeObj.selectNode(nodes[i], false);
            //treeObj.selectNode(nodes[i], true);
            treeObj.checkNode(nodes[i], true, true);
        }
     });
    $(document).ready(function(){
        $.fn.zTree.init($("#treedept"), setting, ${roledept});

        var treeObj = $.fn.zTree.getZTreeObj("treedept");
        var nodes = treeObj.getCheckedNodes();
        for (var i = 0; i < nodes.length; i++) {
            // treeObj.selectNode(nodes[i], false);
            //treeObj.selectNode(nodes[i], true);
            treeObj.checkNode(nodes[i], true, true);
        }
     });
</script>