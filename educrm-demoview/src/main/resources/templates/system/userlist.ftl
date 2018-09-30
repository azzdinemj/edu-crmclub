<#include "../commons/top.ftl"/>
<#include "../commons/left.ftl"/>

<!-- leftpanel -->
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>用户管理<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal"
                   data-url="/system/sysUser/create">创建用户</a>
            <#--<a class="btn btn-sm btn-success" href="/system/sysUser/create" data-toggle="modal" data-target="#myModal" data-url="new-user.html">创建用户</a>-->
            <#-- <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">删除</a>-->

                <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle"
                        data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a onclick="del()" data-toggle="modal">删除</a></li>

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
                    <div class="form-group">
                        <label class="control-label">用户名称 :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
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
                <table id="pagingTable" class="table table-striped table-bordered min-table" cellspacing="0">
                    <thead>
                    <#--<tr>-->
                    <#--&lt;#&ndash; <th width="30">-->
                         <#--<input type="checkbox" id="chAll">-->
                     <#--</th>&ndash;&gt;-->
                    <#--&lt;#&ndash;   <th>操作</th>&ndash;&gt;-->
                        <#--<th>登录帐户</th>-->
                        <#--<th>用户名称</th>-->
                        <#--<th>所属部门</th>-->
                        <#--<th>性别</th>-->
                        <#--<th>状态</th>-->
                        <#--<th>创建人</th>-->
                        <#--<th>创建时间</th>-->
                        <#--<th>修改人</th>-->
                        <#--<th>修改时间</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#if list??>-->
                    <#--<#list list as list>-->
                    <#--<tr dataid="${list.pkSysUser}">-->
                        <#--<input type="hidden" name="delId" value="${(list.pkSysUser)!}">-->
                        <#--<td align="left">-->
                            <#--&lt;#&ndash;<a class="yellow" data-url="/system/sysUser/edit?pkSysUser=${list.pkSysUser}" data-toggle="modal" data-target="#modal">&ndash;&gt;-->
                            <#--${list.pkSysUser}-->
                            <#--&lt;#&ndash;</a>&ndash;&gt;-->
                        <#--</td>-->
                        <#--<td align="left">-->
                            <#--<a class="yellow" data-url="/system/sysUser/edit?pkSysUser=${list.pkSysUser}" data-toggle="modal" data-target="#modal">-->
                                <#--${(list.caption)!}-->
                            <#--</a>-->
                        <#--</td>-->
                        <#--<td align="left">${(list.departmentEntity.caption)!}</td>-->
                        <#--<td><#if list.sex?? && list.sex ==1>男<#else >女</#if></td>-->
                        <#--<td><#if list.isvalid?? && list.isvalid ==1>启用<#else >禁用</#if></td>-->
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
</div>
<#include "../commons/footer.ftl" />
<script>
//    var columns=[
//        {"sTitle":"登录帐户","data" : "pkSysUser",
//            "render": function (data, type, row){
//                return '<a data-toggle="modal" data-target="#modal" data-url="/system/sysUser/edit?pkSysUser='+row.pkSysUser+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"用户名称","data" : "caption"},
//        {"sTitle":"所属部门","data" : "map.departmentEntity.caption"},
//        {"sTitle":"性别","data" : "sex","render":function (data) {
//            if(data==1){
//                return '男';
//            }else {
//                return '女';
//            }
//        }},
//        {"sTitle":"状态","data" : "isvalid","render":function (data) {
//            if(data==1){
//                return '启用';
//            }else {
//                return '禁用';
//            }
//        }},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ]

    function renderCaption (data, type, row){
        return '<a data-toggle="modal" data-target="#modal" data-url="/system/sysUser/edit?pkSysUser='+row.pkSysUser+'" class="yellow" >'+data+'</a>';
    }

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val()};
        init(GetTableColumn("sysUser"),"/system/sysUser/queryByPaging",data,"pagingTable");
    }
</script>
<script src="${staticPath}/js/system/userlist.js">

//    $(document).ready(function() {
//        query();
//    } );
//
//    function query() {
//        var data  = { "dataTime": $("#datas").val(),"caption":$("#caption").val()};
////        init(columns,"/system/sysUser/queryByPaging",data);
//    }


</script>

