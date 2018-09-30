<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />

<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>接送管理 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <div class="btn-group fr title-btn">
            <a class="btn btn-sm btn-success" href="/junhua/pickUpAnalyze/pickUpAnalyze">查看分析报告</a>
        </div>
    </div>
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
                        <th>姓名</th>
                        <th>性别</th>
                        <th>班级</th>
                        <th>上学方式</th>
                        <th>到校时间</th>
                        <th>下学方式</th>
                        <th>离校时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            莉莉
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>

                    </tr>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            莉莉
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>
                    </tr>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                           许仙
                        <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>
                    </tr>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            高小能
                        <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>
                    </tr>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            郭莉莉
                        <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>
                    </tr>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            张立友
                        <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>
                    </tr>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            李立群
                        <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>
                    </tr>
                    <tr>
                        <td>
                            <#--  <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            张丽萍
                            <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>校车</td>
                        <td>08:00</td>
                        <td>校车</td>
                        <td>04:30</td>
                    </tr>
                    <tr>
                        <td>
                        <#--<a href="/junhua/studentlist/edit" class="success" >-->
                            李卫国
                        <#--</a>-->
                        </td>
                        <td>女</td>
                        <td>一班</td>
                        <td>家长送</td>
                        <td>08:00</td>
                        <td>家长接</td>
                        <td>04:30</td>
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