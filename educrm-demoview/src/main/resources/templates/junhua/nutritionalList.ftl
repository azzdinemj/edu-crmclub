<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />
<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>营养分析 <span>...</span></h2>

</div>
<div class="contentpanel">

    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">学生姓名 :</label>
                        <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">班级名称 :</label>
                        <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                    </div>
                    <div class="form-group">
                        <label></label>
                        <div class="btn-group">
                            <a id="queryStudent" href=""></a> <button type="button" onclick="query()" class="btn btn-success btn-sm">搜索</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>班级</th>
                        <th>选餐记录</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>dj1233432</td>
                        <td>
                            <#--<a href="/junhua/studentlist/edit"" class="success" >-->
                                莉莉
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td><a href="/junhua/food/food" class="success" >查看</a></td>

                    </tr>
                    <tr>
                        <td>dj1233432</td>
                        <td>
                            <#--<a href="/junhua/studentlist/edit"" class="success" >-->
                                至尊宝
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td><a href="/junhua/food/food" class="success" >查看</a></td>

                    </tr>
                    <tr>
                        <td>dj1233432</td>
                        <td>
                            <#--<a href="/junhua/studentlist/edit"" class="success" >-->
                            高崎
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td><a href="/junhua/food/food" class="success" >查看</a></td>

                    </tr>
                    <tr>
                        <td>dj1233432</td>
                        <td>
                            <#--<a href="/junhua/studentlist/edit"" class="success" >-->
                            莉莉
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td><a href="/junhua/food/food" class="success" >查看</a></td>

                    </tr>
                    <tr>
                        <td>dj1233432</td>
                        <td>
                            <#--<a href="/junhua/studentlist/edit"" class="success" >-->
                            李立
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td><a href="/junhua/food/food" class="success" >查看</a></td>

                    </tr>
                    <tr>
                        <td>dj1233432</td>
                        <td>
                            <#--<a href="/junhua/studentlist/edit"" class="success" >-->
                            张库
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td><a href="/junhua/food/food" class="success" >查看</a></td>

                    </tr>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div>

<#include "${staticPath}/commons/footer.ftl" />
<script type="text/javascript">

</script>