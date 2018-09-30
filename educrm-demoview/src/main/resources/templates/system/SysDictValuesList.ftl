<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader"><#--小类页面-->
        <h2><i class="fa fa-bookmark"></i><#--${(sysDict.caption)!}-->数据字典<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal"
                   data-url="/system/sysDict/create">新建大类</a>-->
                <a class="btn btn-sm btn-newblue" data-toggle="modal" data-target="#modal"
                   data-url="/system/sysDictValues/create?pkSysDict=${(sysDict.pkSysDict)!}">新建</a>

<button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
                <span class="sr-only"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li> <a data-toggle="modal" onclick="del()">删除</a></li>
            </ul>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <div class="panel-btns">
                    <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
                </div><!-- panel-btns -->

                <!--隐藏搜索-->
                <div class="form-group">
                    <label class="control-label col-lg-1">字典名称 :</label>
                    <div class="col-lg-3">
                        <select name="sds.pkSysDict" class="form-control strw" id="select_id" onchange="sub()">

                            <#list sysDicts as sD>
                                <option  value="${(sD.pkSysDict)!}" <#if sD.pkSysDict??&& sysDict.pkSysDict??&& sD.pkSysDict ==sysDict.pkSysDict >selected="selected"</#if>>${(sD.caption)!}</option>
                            </#list>

                        </select>
                    </div>

                </div>
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                        <#--<form class="form-inline search white">-->
                            <#--<div class="form-group">-->
                                <#--<label>学部</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>全部</option>-->
                                        <#--<option>一部</option>-->
                                        <#--<option>二部</option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">家长姓名 :</label>-->
                                <#--<input type="text" title="" name="reservation" class="form-control form-input-lg"-->
                                       <#--value=""/>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">入学年级 :</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>全部</option>-->
                                        <#--<option>一年级</option>-->
                                        <#--<option>二年级</option>-->
                                        <#--<option>三年级</option>-->
                                        <#--<option>四年级</option>-->
                                        <#--<option></option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                        <#--</form>-->
                    </div>

                </div>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>

                        <th>名称</th>
                        <th>标识值</th>
                        <th>排序</th>
                        <th>是否默认</th>

                        <th>创建人</th>
                        <th>创建时间</th>
                        <th>修改人</th>
                        <th>修改时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list list as v>
                    <tr dataid="${(v.pkSysDictValues)!}">

                        <td align="left"><a class="yellow" data-toggle="modal" data-target="#modal"data-url="/system/sysDictValues/edit?pkSysDictValues=${(v.pkSysDictValues)!}">
                        ${(v.caption)!}
                        </a>
                        </td>
                        <td align="left">${(v.value)!}</td>
                        <td>${(v.sort)!}</td>
                        <td><#if v.isdefault?? && v.isdefault==1>是<#else >否</#if></td>

                        <td align="left">${(v.map.creatorEntity.caption)!}</td>
                        <td align="left">${(v.creationDate?string("yyyy-MM-dd"))!}</td>
                        <td align="left">${(v.map.modifierEntity.caption)!}</td>
                        <td align="left">${(v.lasteditDate?string("yyyy-MM-dd"))!}</td>

                    </tr>
                    </#list>

                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">
    function del() {
        var id = $(".table-color-").attr("dataid");

        if (id == null) {
            alert("请先选择要删除的数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/sysDictValues/delete",
                data: {"pkSysDictValues": id},
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


    function sub() {

        var checkValue=$("#select_id").val();

        window.location.href="/system/sysDictValues/query?pkSysDict="+checkValue;

    }

</script>
