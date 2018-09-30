
<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>题库管理<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/question/questionlist/edit">新建题库</a>
                <#--<a class="btn btn-sm btn-newblue" onclick="del()">删除</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

            <#-- <div class="panel-btns">
                 <a href="" class="minimize news-minimize">高级搜索<i class=" fa fa-chevron-down"></i></a>
             </div>-->
                <!-- panel-btns -->
                <!--高显搜索-->
                <form class="form-inline search white">
                    <#--<div class="form-group">-->
                        <#--<label class="control-label">日期 :</label>-->
                        <#--<input type="text" title="" id="timeid"  name="datetime"  class="form-control js-datepicker"  />-->
                    <#--</div>-->
                    <div class="form-group">
                        <label class="control-label">题库名称 :</label>
                        <input type="text" title="" id="captionQuestions"  name="name"  class="form-control"  />
                    </div>
                   <div class="form-group">
                        <label class="control-label">类别 :</label>
                        <select id="typeQuestions" class="form-control strw">
                            <option value="">全部</option>
                            <option value="0">草稿</option>
                            <option value="1">发布</option>
                            <option value="2">已审核</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <a id="queryQuestions" onclick="query()" class="btn btn-newblue btn-sm search">搜索</a>
                    </div>
                </form>
                <!--隐藏搜索-->
            <#--<div class="panel-body senior-search">
                <div id="post-status" class="tab-pane active">
                    <form class="form-inline search white">
                        <div class="form-group">
                            <label>学部</label>
                            <div class="btn-group">
                                <select class="form-control strw">
                                    <option>全部</option>
                                    <option>一部</option>
                                    <option>二部</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">家长姓名 :</label>
                            <input type="text" title=""  name="reservation"  class="form-control form-input-lg" value="" />
                        </div>
                        <div class="form-group">
                            <label class="control-label">入学年级 :</label>
                            <div class="btn-group">
                                <select class="form-control strw">
                                    <option>全部</option>
                                    <option>一年级</option>
                                    <option>二年级</option>
                                    <option>三年级</option>
                                    <option>四年级</option>
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
                <table id="tables"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>名称</th>
                        <th>类型</th>
                        <th>状态</th>

                    </tr>
                    </thead>
                    <tbody>
                    <#if questions??>
                        <#list questions.list as que>
                    <tr>
                        <td>${(que.pkQuestions)!}</td>
                        <td><a class="yellow" href="/question/questionlist/edit?pkQuestions=${(que.pkQuestions)!}">${(que.title)!}</a></td>
                        <td>${(que.type)!}</td>
                        <td>
                            <#if que.status??&&que.status==0>草稿</#if>
                            <#if que.status??&&que.status==1>发布</#if>
                            <#if que.status??&&que.status==2>已审核</#if>
                        </td>
                    </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

    <!--questions为pageinfo对象-->
    <!--分页页码-->
<#if questions??>
    <ul class="pagination"    id="page">
        <li><a href="/question/questionlist/query?pageNo=${questions.prePage}">&laquo;</a></li>
        <#assign tp=questions.pages/>
        <#assign p=questions.pageNum/>
        <#assign sp=p-3/>
        <#assign ep=p+3/>
        <#assign eoff=ep-tp/>
        <#if (eoff>0)>
            <#assign sp = sp - eoff/>
        </#if>
        <#if (sp<=0)>
            <#assign ep = ep - sp+1/>
        </#if>
        <#list sp..ep as x>
            <#if (x>0 && x<=tp)>
                <li ><a href="/question/questionlist/query?pageNo=${x}">${x}</a></li>
            </#if>
        </#list>
        <li ><a href="/question/questionlist/query?pageNo=${questions.nextPage}">&raquo;</a></li>
    </ul>
</#if>


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

