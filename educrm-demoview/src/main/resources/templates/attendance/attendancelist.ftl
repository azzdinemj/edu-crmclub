
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>教练考勤<span>...</span></h2>
    </div>
    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title="" id="timeid"  name="datetime"  class="form-control js-datepicker"  />
                    </div>
                    <div class="form-group">
                        <label class="control-label">教练名称 :</label>
                        <input type="text" title="" id="captionQuestions"  name="name"  class="form-control"  />
                    </div>
                   <div class="form-group">
                        <label class="control-label">班级 :</label>
                        <select id="typeQuestions" class="form-control strw">
                            <option value="">全部</option>
                            <option value="0">一班</option>
                            <option value="1">二班</option>
                            <option value="2">三班</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <a id="queryQuestions" onclick="query()" class="btn btn-newblue btn-sm search">搜索</a>
                    </div>
                </form>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="tables"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>教练名称</th>
                        <th>班级名称</th>
                        <th>学员数</th>
                        <th>学员打卡次数</th>

                    </tr>
                    </thead>
                    <tbody>

                    <tr>
                        <td>张文宇</td>
                        <td><a class="yellow" href="/attendance/attendancelist/edit">少儿1班一班</a></td>
                        <td>10</td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <td>张文宇</td>
                        <td><a class="yellow" href="/attendance/attendancelist/edit">少儿1班一班</a></td>
                        <td>10</td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <td>张文宇</td>
                        <td><a class="yellow" href="/attendance/attendancelist/edit">少儿1班一班</a></td>
                        <td>10</td>
                        <td>10</td>
                    </tr>
                    <tr>
                        <td>张文宇</td>
                        <td><a class="yellow" href="/attendance/attendancelist/edit">少儿1班一班</a></td>
                        <td>10</td>
                        <td>10</td>
                    </tr>

                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

    <!--questions为pageinfo对象-->
    <!--分页页码-->
<#--<#if questions??>-->
    <#--<ul class="pagination"    id="page">-->
        <#--<li><a href="/question/questionlist/query?pageNo=${questions.prePage}">&laquo;</a></li>-->
        <#--<#assign tp=questions.pages/>-->
        <#--<#assign p=questions.pageNum/>-->
        <#--<#assign sp=p-3/>-->
        <#--<#assign ep=p+3/>-->
        <#--<#assign eoff=ep-tp/>-->
        <#--<#if (eoff>0)>-->
            <#--<#assign sp = sp - eoff/>-->
        <#--</#if>-->
        <#--<#if (sp<=0)>-->
            <#--<#assign ep = ep - sp+1/>-->
        <#--</#if>-->
        <#--<#list sp..ep as x>-->
            <#--<#if (x>0 && x<=tp)>-->
                <#--<li ><a href="/question/questionlist/query?pageNo=${x}">${x}</a></li>-->
            <#--</#if>-->
        <#--</#list>-->
        <#--<li ><a href="/question/questionlist/query?pageNo=${questions.nextPage}">&raquo;</a></li>-->
    <#--</ul>-->
<#--</#if>-->


</div><!-- contentpanel -->




<#include "../commons/footer.ftl"/>


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

