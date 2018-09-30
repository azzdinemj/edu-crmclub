<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader"><#--Small class page-->
        <h2><i class="fa fa-bookmark"></i><#--${(sysDict.caption)!}-->data dictionary<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal"
                   data-url="/system/sysDict/create">New big class</a>-->
                <a class="btn btn-sm btn-newblue" id="createId" data-toggle="modal" data-target="#modal">Newly build</a>

<button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
                <span class="sr-only"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li> <a data-toggle="modal" onclick="del()">delete</a></li>
            </ul>
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <div class="panel-btns">
                    <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                </div><!-- panel-btns -->

                <!--Hide search-->
                <div class="form-group">
                    <label class="control-label col-lg-1">name :</label>
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
                                <#--<label>Faculty</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>whole</option>-->
                                        <#--<option>One</option>-->
                                        <#--<option>Two</option>-->
                                    <#--</select>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">Parent name :</label>-->
                                <#--<input type="text" title="" name="reservation" class="form-control form-input-lg"-->
                                       <#--value=""/>-->
                            <#--</div>-->
                            <#--<div class="form-group">-->
                                <#--<label class="control-label">School year :</label>-->
                                <#--<div class="btn-group">-->
                                    <#--<select class="form-control strw">-->
                                        <#--<option>whole</option>-->
                                        <#--<option>first grade</option>-->
                                        <#--<option>second grade</option>-->
                                        <#--<option>Grade three</option>-->
                                        <#--<option>fourth grade</option>-->
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
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <#--<tr>-->

                        <#--<th>Name</th>-->
                        <#--<th>Identification value</th>-->
                        <#--<th>sort</th>-->
                        <#--<th>Is default</th>-->

                        <#--<th>Founder</th>-->
                        <#--<th>Creation time</th>-->
                        <#--<th>Modifier</th>-->
                        <#--<th>Modified</th>-->
                    <#--</tr>-->
                    </thead>
                    <tbody>
                    <#--<#list list as v>-->
                    <#--<tr dataid="${(v.pkSysDictValues)!}">-->

                        <#--<td align="left"><a class="yellow" data-toggle="modal" data-target="#modal"data-url="/system/sysDictValues/edit?pkSysDictValues=${(v.pkSysDictValues)!}">-->
                        <#--${(v.caption)!}-->
                        <#--</a>-->
                        <#--</td>-->
                        <#--<td align="left">${(v.value)!}</td>-->
                        <#--<td>${(v.sort)!}</td>-->
                        <#--<td><#if v.isdefault?? && v.isdefault==1>是<#else >否</#if></td>-->

                        <#--<td align="left">${(v.map.creatorEntity.caption)!}</td>-->
                        <#--<td align="left">${(v.creationDate?string("yyyy-MM-dd"))!}</td>-->
                        <#--<td align="left">${(v.map.modifierEntity.caption)!}</td>-->
                        <#--<td align="left">${(v.lasteditDate?string("yyyy-MM-dd"))!}</td>-->

                    <#--</tr>-->
                    <#--</#list>-->

                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">
//    var columns=[
//        {"sTitle":"Name","data" : "caption",
//            "render": function (data, type, row){
////                return '<a href="/student/studentInterview/edit?pkStudentInterview='+row.pkStudentInterview+'" class="yellow" >'+data+'</a>';
//                return '<a class="yellow" data-toggle="modal" data-target="#modal"data-url="/system/sysDictValues/edit?pkSysDictValues='+row.pkSysDictValues+'">'+data+'</a>';
//            }} ,
//        {"sTitle":"Identification value","data" : "value"},
//        {"sTitle":"sort","data" : "sort"},
//        {"sTitle":"Is default","data" : "isdefault"},
//        {"sTitle":"Founder","data" : "map.creatorEntity.caption"},
//        {"sTitle":"Creation time","data" : "creationDate","render": renderDate},
//        {"sTitle":"Modifier","data" : "map.modifierEntity.caption"},
//        {"sTitle":"Modified","data" : "lasteditDate","render": renderDate}
//    ]

    function renderCaption(data, type, row) {
        return '<a class="yellow" data-toggle="modal" data-target="#modal"data-url="/system/sysDictValues/edit?pkSysDictValues='+row.pkSysDictValues+'">'+data+'</a>';
    }

    $(document).ready(function() {
        query();
    } );
    function query() {
        var checkValue=$("#select_id").val();
//        if(checkValue==null || checkValue==""){
//            checkValue="educations";
//        }
        var data  = { "dateTime": $("#datas").val(),"caption":$("#caption").val()};
        init(GetTableColumn("sysDictValues"),"/system/sysDictValues/queryByPaging?pkSysDict="+checkValue,data,"pagingTable");
    }






    function del() {
        var id = $(".table-color-").attr("dataid");

        if (id == null) {
            alert("Please select the data to delete first");
            return;
        }
        var flag = confirm("Do you confirm the deletion？");
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
        query();
//        var checkValue=$("#select_id").val();
//
//        window.location.href="/system/sysDictValues/query?pkSysDict="+checkValue;

    }

    $("#createId").on("click",function () {
        var pkSysDict = $("#select_id").val();
        <#--data-url=${(sysDict.pkSysDict)!}"-->
        $("#createId").attr("data-url","/system/sysDictValues/create?pkSysDict="+pkSysDict);
        return true;

    })

</script>
