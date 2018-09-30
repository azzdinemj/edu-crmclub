
<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<!--未使用 -->
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>题库选项管理<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" href="/question/questionlist/edit">新建题库</a>-->
                <#--<a class="btn btn-sm btn-newblue" onclick="del()">删除</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="tables"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>title</th>
                        <th>content</th>
                        <th>sort</th>
                        <th>status</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#if optionList??>
                        <#list optionList as que>
                        <tr>
                            <td>${(que.id)!}</td>
                            <td><a class="yellow" href="/question/questionOption/edit?id=${(que.id)!}">${(que.title)!}</a></td>
                            <td>${(que.content)!}</td>
                            <td>${(que.sort)!}</td>
                            <td>
                                <#if que.status??&&que.status==1>显示</#if>
                                <#if que.status??&&que.status==0>不显示</#if>
                            </td>
                        </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->


    </div><!-- contentpanel -->




<#include "../../commons/footer.ftl"/>

<script type="text/javascript">

    function del() {
        var id = $(".table-color-").attr("dataid");

        if(id ==null){
            Notify.danger("请先选择要删除的数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/system/dormRoom/delete",
                data: {"pkDormRoom": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        Notify.success(data.message);
                        setTimeout("location.reload()", 1);
                    } else {
                        Notify.danger(data.message);
                        window.location.reload();
                    }
                },
                error: function () {

                }
            });
        }


    }

    function query() {
        var caption=$("#captionQuestions").val();
        var type=$("#typeQuestions option:selected").val();
        $("#queryQuestions").attr("href","/question/questionlist/query?status="+type+"&&title="+caption)[0].click();
    }

</script>

