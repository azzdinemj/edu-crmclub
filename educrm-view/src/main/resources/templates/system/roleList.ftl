<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i><@spring.message "system.roleManager"/><span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
            <#--  <a class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal" data-url="addNewEventModal-1">创建角色</a>-->
                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal"
                   data-url="/system/sysRole/create">添加角色</a>
                <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle"
                        data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a onclick="del()" data-toggle="modal">删除</a></li>
                    <li class="divider"></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <!--<div class="panel-btns">
                    <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                </div>--><!-- panel-btns -->
                <!--高显搜索-->
                <form class="form-inline search white">
                    <!-- <div class="form-group">
                         <label class="control-label">日期 :</label>
                         <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                     </div>-->
                    <div class="form-group">
                        <label class="control-label">角色名称 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <a onclick="query()" class="btn btn-newblue btn-sm search">搜索</a>
                    </div>
                </form>
                <!--隐藏搜索-->
                <!-- <div class="panel-body senior-search">
                     <div id="post-status" class="tab-pane active">
                         <form class="form-inline search white">
                             <div class="form-group">
                                 <label>学部</label>
                                 <div class="btn-group">
                                     <select class="form-control">
                                         <option>全部</option>
                                         <option>一部</option>
                                         <option>二部</option>
                                     </select>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">家长姓名 :</label>
                                 <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                             </div>
                             <div class="form-group">
                                 <label class="control-label">入学年级 :</label>
                                 <div class="btn-group">
                                     <select class="form-control">
                                         <option>全部</option>
                                         <option>一年级</option>
                                         <option>二年级</option>
                                         <option>三年级</option>
                                         <option>四年级</option>
                                         <option></option>
                                     </select>
                                 </div>
                             </div>
                         </form>
                     </div>

                 </div>-->
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                    <#--&lt;#&ndash;<th width="30"><input type="checkbox" id="chAll"></th>&ndash;&gt;-->
                        <#--<th width="100">角色编号</th>-->
                        <#--<th width="100">角色名称</th>-->
                        <#--<th width="100">备注</th>-->
                        <#--<th width="100">创建人</th>-->
                        <#--<th width="100">创建时间</th>-->
                        <#--<th width="100">修改人</th>-->
                        <#--<th width="100">修改时间</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#if roles ??>-->
                    <#--<#list roles as list>-->
                    <#--<tr dataid="${(list.pkSysRole)!}">-->
                    <#--&lt;#&ndash; <td><input type="checkbox" class="px"></td>&ndash;&gt;-->
                        <#--<td align="left">${(list.code)!}</td>-->
                        <#--<td align="left">-->
                            <#--<a class="yellow" data-toggle="modal" data-target="#modal"-->
                               <#--data-url="/system/sysRole/edit?pkSysRole=${list.pkSysRole}">${(list.caption)!}</a>-->
                        <#--</td>-->
                        <#--<td align="left">${(list.memo)!}</td>-->
                        <#--<td align="left">${(list.map.creatorEntity.caption)!}</td>-->
                        <#--<td align="right">${list.creationDate ? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                        <#--<td align="left">${(list.map.modifierEntity.caption)!}</td>-->
                        <#--<td align="right">${list.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}</td>-->
                    <#--</tr>-->
                    <#--</#list>-->
                    <#--</#if>-->
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->
<#include "../commons/footer.ftl"/>



<script type="text/javascript">

    function renderCaption (data, type, row){
        return '<a data-toggle="modal" data-target="#modal" data-url="/system/sysRole/edit?pkSysRole='+row.pkSysRole+'" class="yellow" >'+data+'</a>';
    }


    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dataTime": $("#datas").val(),"caption":$("input[name='reservation']").val()};
        init(GetTableColumn("sysRole"),"/system/sysRole/queryByPaging",data,"pagingTable");
    }

    function del() {

        var index = $(".table-color-").index();
        var id = getTableSelectRow("pagingTable",index).pkSysRole;

        if (id == null) {
            Notify.danger("请先选择要删除的数据");
            return;
        }

        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/sysRole/delete",
                data: {"pkSysRole": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message)
                    }
                },
                error: function () {

                }
            });
        }


    }

</script>
