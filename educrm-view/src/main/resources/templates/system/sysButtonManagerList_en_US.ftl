<#include "../commons/top.ftl"/>
<#include "../commons/left.ftl"/>

<!-- leftpanel -->
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Button management<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
            <#--<a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal"-->
            <#--data-url="/system/sysMenuManager/edit">add</a>-->
                <a class="btn btn-sm btn-newblue" href="/system/sysButtonManager/edit">Create </a>
            <#-- <a class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal" data-url="">delete</a>-->

                <button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle"
                        data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
            <#--  <ul class="dropdown-menu" role="menu">
                  <li><a onclick="del()" data-toggle="modal">delete</a></li>
              </ul>-->
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
                <#--<div class="form-group">-->
                        <#--<label class="control-label">User name :</label>-->
                        <#--<input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>-->
                    <#--</div>-->
                    <#--<div class="form-group">-->
                        <#--<a class="btn btn-newblue btn-sm search">search</a>-->
                    <#--</div>-->
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
                <table id="table" class="table table-striped table-bordered min-table" cellspacing="0">
                    <thead>
                    <tr>
                    <#-- <th width="30">
                         <input type="checkbox" id="chAll">
                     </th>-->
                    <#--   <th>operation</th>-->
                        <th>pk_sys_menu</th>
                        <th>pk_sys_button</th>
                        <th>caption</th>
                        <th>caption_eng</th>
                        <th>display</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#if list??>
                    <#list list as li>
                    <tr>
                        <td align="left">
                        ${(li.pkSysMenu)!}
                        </td>
                        <td align="left">
                        ${(li.pkSysButton)!}
                        </td>
                        <td align="left">
                        <a class="yellow" href="/system/sysButtonManager/edit?pkSysMenu=${(li.pkSysMenu)!}&&pkSysButton=${(li.pkSysButton)!}" >
                                ${(li.caption)!}
                            </a>
                        </td>
                        <td align="left">
                        ${(li.captionEng)!}
                        </td>
                        <td align="left">
                        ${(li.display)!}
                        </td>
                    </tr>
                    </#list>
                    </#if>
                    </tbody>
                </table>

            </div><!-- table-responsive -->
        </div><!-- panel-body -->
    </div><!-- panel -->

</div><!-- contentpanel -->
</div>
<#include "../commons/footer.ftl" />

<#--<script src="${staticPath}/js/system/userlist.js">-->

    <#--$(document).ready(function () {-->
        <#--// query();-->
    <#--});-->

    <#--function query() {-->
        <#--var data = {"dataTime": $("#datas").val(), "caption": $("#caption").val()};-->
<#--//        init(columns,"/system/sysUser/queryByPaging",data);-->
    <#--}-->

<#--</script>-->

