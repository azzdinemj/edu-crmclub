
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>接送记录<span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <#--<a class="btn btn-sm btn-newblue" href="/system/dormRoom/create">新建宿舍</a>
                        <a class="btn btn-sm btn-newblue" id="checkInId">入住</a>-->
                        <#--<a class="btn btn-sm btn-newblue" id="checkOutId">退宿</a>-->
                        <#--<a class="btn btn-sm btn-newblue" onclick="del()">禁用</a>-->
                        <#--<a class="btn btn-sm btn-newblue" onclick="sub()">新建宿舍111</a>-->
                        <#--<a class="btn btn-sm btn-danger" data-toggle="modal" data-target="myModal" data-url="">删除</a>-->

                          <#--<button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">-->
                        <#--<span class="caret"></span>-->
                        <#--<span class="sr-only"></span>-->
                    <#--</button>-->
                    <#--<ul class="dropdown-menu" role="menu">-->
                        <#--&lt;#&ndash;<li><a href="" data-toggle="modal">删除</a></li>&ndash;&gt;-->
                        <#--<li><a onclick="del()">删除</a></li>-->
                        <#--<li class="divider"></li>-->
                    <#--</ul>-->
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">

                        <#--<div class="panel-btns">-->
                            <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                        <#--</div><!-- panel-btns &ndash;&gt;-->
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">进出校 :</label>
                                <select id="shuttleTypeId" name="shuttleType" onchange="query()">
                                    <option value="0">进校</option>
                                    <option value="1">出校</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title="" id="datas"  name="dateTime"  class="form-control form-input-lg"  />
                            </div>

                            <div class="form-group">
                                <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                            </div>

                        </form>
                        <!--隐藏搜索-->
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
                                        <#--<input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />-->
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
                        <table id="pagingTable"  class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <#--<tr>-->
                                <#--<th>宿舍编号</th>-->
                                <#--<th>宿舍名称</th>-->
                                <#--<th>类型</th>-->
                                <#--<th>容纳人数</th>-->
                                <#--<th>当前已住人数</th>-->
                                <#--<th>性别</th>-->
                                <#--<th>备注</th>-->
                            <#--</tr>-->
                            </thead>
                            <tbody>
                            <#--<#if dormlist ??>-->
                            <#--<#list dormlist as v>-->
                            <#--<tr dataid="${v.pkDormRoom}">-->
                                <#--<td>-->
                                    <#--${(v.code)!}-->
                                <#--</td>-->
                                <#--<td>-->
                                    <#--<a class="yellow" href="/system/dormRoom/edit?pkDormRoom=${(v.pkDormRoom)!}">-->
                                        <#--${(v.caption)!}-->
                                    <#--</a>-->
                                <#--</td>-->
                                <#--<td>-->
                                <#--&lt;#&ndash;${(v.kind)!}&ndash;&gt;-->
                                    <#--<#if v.kind?? && v.kind==0>学生宿舍<#else >老师宿舍</#if>-->
                                <#--</td>-->
                                <#--<td>${(v.num)!}</td>-->
                                <#--<td>${(v.currentNum)!}</td>-->
                                <#--<td>-->
                                    <#--<#if v.sex?? && v.sex==1>男<#else >女</#if>-->
                                <#--</td>-->
                                <#--<td>${(v.memo)!}</td>-->
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

    $(document).ready(function() {
        query();
    } );


    function query() {

//        var shuttleType= $("#shuttleTypeId").val();
        var data  = { "shuttleType":$("#shuttleTypeId").val(),"dateTime":$("#datas").val()};
//        if (shuttleType =="0"){
            init(GetTableColumn("boardingRecord"),"/system/shuttleRecord/queryByPaging",data,"pagingTable");
//        }else {
//            init(GetTableColumn("deBusRecord"),"/system/shuttleRecord/queryByPaging",data,"pagingTable");
//        }

    }




</script>
