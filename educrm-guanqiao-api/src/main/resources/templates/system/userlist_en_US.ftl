<#include "../commons/top.ftl"/>
<#include "../commons/left.ftl"/>

<!-- leftpanel -->
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>user management<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal"
                   data-url="/system/sysUser/create">add</a>
            <#--<a class="btn btn-sm btn-success" href="/system/sysUser/create" data-toggle="modal" data-target="#myModal" data-url="new-user.html">add</a>-->
            <#-- <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">delete</a>-->

                <button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle"
                        data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a onclick="del()" data-toggle="modal">delete</a></li>

                </ul>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <!--<div class="panel-btns">
                    <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                </div>--><!-- panel-btns -->
                <!--High search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">User name :</label>
                        <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                    </div>
                </form>
                <!--Hide search-->
                <!-- <div class="panel-body senior-search">
                     <div id="post-status" class="tab-pane active">
                         <form class="form-inline search white">
                             <div class="form-group">
                                 <label>Faculty</label>
                                 <div class="btn-group">
                                     <select class="form-control">
                                         <option>whole</option>
                                         <option>One</option>
                                         <option>Two</option>
                                     </select>
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">Parent name :</label>
                                 <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                             </div>
                             <div class="form-group">
                                 <label class="control-label">School year :</label>
                                 <div class="btn-group">
                                     <select class="form-control">
                                         <option>whole</option>
                                         <option>first grade</option>
                                         <option>second grade</option>
                                         <option>Grade three</option>
                                         <option>fourth grade</option>
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
                    <#--&lt;#&ndash;   <th>operation</th>&ndash;&gt;-->
                        <#--<th>Login account</th>-->
                        <#--<th>User name</th>-->
                        <#--<th>Subordinate department</th>-->
                        <#--<th>Sex</th>-->
                        <#--<th>state</th>-->
                        <#--<th>Founder</th>-->
                        <#--<th>Creation time</th>-->
                        <#--<th>Modifier</th>-->
                        <#--<th>Modified</th>-->
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
                        <#--<td><#if list.isvalid?? && list.isvalid ==1>Enable<#else >Disable</#if></td>-->
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
//        {"sTitle":"Login account","data" : "pkSysUser",
//            "render": function (data, type, row){
//                return '<a data-toggle="modal" data-target="#modal" data-url="/system/sysUser/edit?pkSysUser='+row.pkSysUser+'" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"User name","data" : "caption"},
//        {"sTitle":"Subordinate department","data" : "map.departmentEntity.caption"},
//        {"sTitle":"Sex","data" : "sex","render":function (data) {
//            if(data==1){
//                return '男';
//            }else {
//                return '女';
//            }
//        }},
//        {"sTitle":"state","data" : "isvalid","render":function (data) {
//            if(data==1){
//                return 'Enable';
//            }else {
//                return 'Disable';
//            }
//        }},
//        {"sTitle":"Founder","data" : "map.creatorEntity.caption"},
//        {"sTitle":"Creation time","data" : "creationDate","render": renderDate},
//        {"sTitle":"Modifier","data" : "map.modifierEntity.caption"},
//        {"sTitle":"Modified","data" : "lasteditDate","render": renderDate}
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

