<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>分校财务 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" data-url="/finance/expenseItem/create" data-toggle="modal" data-target="#modal">新建项目</a>-->
                <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">删除</a>-->

            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <ul  id="myTab" class="nav nav-tabs nav-success">
                <li class="active"><a href="#one" data-toggle="tab">直营</a></li>
                <li><a href="#two" data-toggle="tab">分校</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="one">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                        <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                        </div><!-- panel-btns -->
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg" value=""/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">名称 :</label>
                                <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                            </div>
                            <div class="form-group">
                                <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                            </div>
                        <#--<div class="form-group">-->
                        <#--<label>国籍</label>-->
                        <#--<div class="btn-group">-->
                        <#--<select class="form-control strw">-->
                        <#--<option>全部</option>-->
                        <#--<option>中国</option>-->
                        <#--<option>美国</option>-->
                        <#--<option>英国</option>-->
                        <#--<option>法国</option>-->
                        <#--<option></option>-->
                        <#--</select>-->
                        <#--</div>-->
                        <#--</div>-->

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                               </div>-->
                        </form>
                        <!--隐藏搜索-->
                        <div class="panel-body senior-search">
                            <div id="post-status" class="tab-pane active">

                            </div>

                        </div>
                    </div>
                    <div class="table-responsive">
                        <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th>直营校名</th>
                                <th>学生数量</th>
                                <th>成单量</th>
                                <th>缴费总额度</th>

                            </tr>
                            </thead>
                            <tbody>
                            <#--<#if list??>-->
                            <#--<#list list as v >-->
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>


                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr> <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>


                            <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li><a href="#">&laquo;</a></li>
                            <li class="active"><a href="#">1</a></li>
                        <#--<li class="disabled"><a href="#">2</a></li>-->
                        <#--<li><a href="#">3</a></li>-->
                        <#--<li><a href="#">4</a></li>-->
                        <#--<li><a href="#">5</a></li>-->
                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div><!-- table-responsive -->
                </div>
                <div class="tab-pane fade" id="two">
                    <div class="panel-heading pd10 bor0 new">

                        <div class="panel-btns">
                        <#--<a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>-->
                        </div><!-- panel-btns -->
                        <!--高显搜索-->
                        <form class="form-inline search white">
                            <div class="form-group">
                                <label class="control-label">日期 :</label>
                                <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg" value=""/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">名称 :</label>
                                <input type="text" title="" id="caption" name="reservation" class="form-control form-input-lg" value=""/>
                            </div>
                            <div class="form-group">
                                <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                            </div>
                        <#--<div class="form-group">-->
                        <#--<label>国籍</label>-->
                        <#--<div class="btn-group">-->
                        <#--<select class="form-control strw">-->
                        <#--<option>全部</option>-->
                        <#--<option>中国</option>-->
                        <#--<option>美国</option>-->
                        <#--<option>英国</option>-->
                        <#--<option>法国</option>-->
                        <#--<option></option>-->
                        <#--</select>-->
                        <#--</div>-->
                        <#--</div>-->

                            <!--   <div class="form-group">
                                   <span class="btn btn-success btn-sm"><i class="fa fa-search"> </i>搜索</span>
                               </div>-->
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
                    <div class="table-responsive">
                        <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                            <thead>
                            <tr>
                                <th>分校校名</th>
                                <th>学生数量</th>
                                <th>成单量</th>
                                <th>缴费总额度</th>
                            </tr>


                            </thead>
                            <tbody>
                            <#--<#if list??>-->
                            <#--<#list list as v >-->
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>  <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>
                            <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>  <tr dataid="${(v.pkExpenseItem)!}">
                                <td align="left">北京苍龙分校</td>
                                <td align="left">360</td>
                                <td align="left">300 </td>
                                <td>3500000</td>
                            </tr>




                            <#--</#list>-->
                            <#--</#if>-->
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li><a href="#">&laquo;</a></li>
                            <li class="active"><a href="#">1</a></li>
                        <#--<li class="disabled"><a href="#">2</a></li>-->
                        <#--<li><a href="#">3</a></li>-->
                        <#--<li><a href="#">4</a></li>-->
                        <#--<li><a href="#">5</a></li>-->
                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div><!-- table-responsive -->
                </div>


            </div>


        </div>

    </div><!-- panel -->

</div><!-- contentpanel -->

</div><!-- mainpanel -->


<#include "../commons/footer.ftl"/>

<script type="text/javascript">

//    var columns = [
//        {"sTitle":"编号","data":"code"},
//        {"sTitle":"项目名称","data" : "caption",
//            "render": function (data, type, row){
//                return '<a data-url="/finance/expenseItem/edit?pkExpenseItem='+row.pkExpenseItem+'" data-toggle="modal" data-target="#modal" class="yellow" >'+data+'</a>';
//            }} ,
//        {"sTitle":"项目状态","data" : "kind","render": function (data){
//            if (data==1){ return '收'; }
//            else {return '付'; }
//        }},
//        {"sTitle":"是否启用","data" : "isvalid","render": function (data){
//            if (data==1){ return '启用';}
//            else {return '禁用'; }
//        }},
//        {"sTitle":"创建人","data" : "map.creatorEntity.caption"},
//        {"sTitle":"创建时间","data" : "creationDate","render": renderDate},
//        {"sTitle":"修改人","data" : "map.modifierEntity.caption"},
//        {"sTitle":"修改时间","data" : "lasteditDate","render": renderDate}
//    ];
//
//    $(document).ready(function() {
//        query();
//    } );
//
//    function query() {
//        var data  = { "caption":$("#caption").val()};
//        init(columns,"/finance/expenseItem/queryByPaging",data);
//    }
//
    function del() {
        var id = $(".table-color-").attr("dataid");
        if(id == null){
            alert("请选择一条数据");
            return;
        }
        var flag = confirm("确定要删除吗？");

//        if(flag){
//            $.ajax({
//                type:"POST",
//                url:"/finance/expenseItem/delete",
//                data:{"pkExpenseItem":id},
//                dataType:"json",
//                success:function (data) {
//                    if (data.code==0){
//                        Notify.success(data.message);
//                        setTimeout("location.reload()", 1);
//                    }else {
//                        Notify.danger(data.message);
//                        window.location.reload();
//                    }
//                }
//            });
//        }

    }
</script>