
<#include "../commons/top.ftl"/>
<#include "../commons/left.ftl"/>

<div class="pageheader">
    <h2><i class="fa fa-bookmark"></i>操作日志 <span>...</span></h2>
    <div class="breadcrumb-wrapper">
        <span class="label">你的位置:</span>
        <ol class="breadcrumb">
            <li><a href="../index.html">返回</a></li>
            <li class="active">操作日志</li>
        </ol>
    </div>
</div>
<div class="contentpanel">

    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <form id="searchForm" action="/log/search" method="GET" class="form-inline search ">
                    <div class="form-group">
                        <label>用户昵称</label>
                        <div class="btn-group">
                            <input class="form-control" name="loginName" value="${searchName!}" title="" placeholder="请输入查询内容">
                        </div>
                    </div>
                    <div class="form-group">
                        <span id="searchbtn" class="btn btn-info btn-sm add-new-event" data-toggle="modal"><i class="fa fa-search"> </i>搜索</span>
                    </div>
                </form>


            </div>

        </div>
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table" class="display" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th width="30">
                            <input type="checkbox" id="chAll">
                        </th>

                        <th>编号</th>
                        <th>用户名称</th>
                        <th>所属部门</th>
                        <th>真实姓名</th>
                        <th>操作</th>
                        <th>操作时间</th>



                    </tr>
                    </thead>
                    <tbody>

                   <#-- <#list list as list>-->
                    <tr>
                        <td>
                            <input type="checkbox" class="px">
                        </td>


                        <td>1</td>

                        <td>admin</td>
                        <td>总部</td>
                        <td>总部</td>
                        <td>管理员</td>
                        <td>2018-01-7</td>

                    </tr>
                   <#-- </#list>-->
                    </tbody>
                </table>

            </div><!-- table-responsive -->
        </div><!-- panel-body -->
    </div><!-- panel -->

</div><!-- contentpanel -->
</div>


<#include "../commons/footer.ftl"/>
<script type="text/javascript">
    $("#searchbtn").click(function(){
        $("#searchForm").submit();
    });
</script>