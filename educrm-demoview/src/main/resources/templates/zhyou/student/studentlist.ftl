<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>学生列表 <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                    <#--    <#if isType?? && isType==0>
                        <a class="btn btn-sm btn-newblue" href="/student/studentSignup/create" >新建意向学生</a>
                        <a class="btn btn-sm btn-newblue" onclick="stuInterview()">面试</a>
                        <a class="btn btn-sm btn-newblue" onclick="stuReport()">报名</a>
                        <a href="" class="btn btn-sm btn-newblue" onclick="del()"> 删除 </a>
                        </#if>-->
                       <#-- <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="" data-toggle="modal" onclick="del()"> 删除 </a></li>
                        </ul>-->
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
                                <label class="control-label">学生姓名 :</label>
                                <input id="caption" type="text" title=""  name="caption"  class="form-control form-input-lg" value="" />
                            </div>
                            <div class="form-group">
                                <label></label>
                                <div class="btn-group">
                                   <a id="queryStudent" href=""></a> <button type="button" onclick="query()" class="btn btn-success btn-sm">查找</button>
                                </div>
                            </div>

                        </form>
                        <!--隐藏搜索-->
                        <#--<div class="panel-body senior-search">-->
                            <#--<div id="post-status" class="tab-pane active">-->
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
                            <#--</div>-->

                        <#--</div>-->

                    </div>
                </div>

                <div class="panel-body pd0">
                   <div class="table-responsive">
                       <table id="pagingTable"  class="table table-striped table-bordered min-table " cellspacing="0">
                           <thead>
                           <tr>
                               <th>主键</th>
                               <th>姓名</th>
                               <th>电话</th>
                               <th>公司</th>
                               <th>部门</th>
                               <th>操作</th>
                           </tr>
                           </thead>
                           <tbody>
                           <#if Students.list??>
                               <#list Students.list as v>
                               <tr>
                                   <td>${(v.pkStudent)!}</td>
                                   <td><a class="yellow" href="/student/studentlist/edit?pkStudent=${(v.pkStudent)!}">${(v.caption)!}</a></td>
                                   <td>${(v.caption)!}</td>
                                   <td>${(v.company)!}</td>
                                   <td>${(v.branch)!}</td>
                                   <td>
                                       <#if v.isvalid??>
                                           <#if v.isvalid==0 >
                                               <a onclick="verify('${(v.pkStudent)!}')"  class="btn btn-newblue btn-sm">启用</a>
                                           </#if>
                                           <#if v.isvalid==1 >
                                               已启用
                                           </#if>
                                       </#if>
                                   </td>
                               </tr>
                               </#list>
                           </#if>
                           </tbody>
                       </table>
                   </div><!-- table-responsive -->
                </div><!-- panel-body -->

            </div><!-- panel -->

            <!--Students为pageinfo对象-->
            <!--分页页码-->
        <#if Students??>
            <ul class="pagination"    id="page">
                <li><a href="/student/studentlist/query?pageNo=${Students.prePage}">&laquo;</a></li>
                <#assign tp=Students.pages/>
                <#assign p=Students.pageNum/>
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
                        <li ><a href="/student/studentlist/query?pageNo=${x}">${x}</a></li>
                    </#if>
                </#list>
                <li ><a href="/student/studentlist/query?pageNo=${Students.nextPage}">&raquo;</a></li>
            </ul>
        </#if>


        </div><!-- contentpanel -->

<#include "${staticPath}/commons/footer.ftl" />
<script type="text/javascript">

function verify(id) {
      //  var id = $(".table-color-").attr("dataid");

      /*  if (id == null) {
            alert("请先选择要审核的数据");
            return;
        }*/

        var flag = confirm("确认审核吗？");
        if (flag) {
            $.ajax({
                type: "POST",
                url: "/student/studentlist/audit",
                data: {"pkStudent": id},
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        location.href="/student/studentlist/query";
                     /*   Notify.success(data.message);
                        setTimeout("location.reload()", 1);*/
                    } else {
                        Notify.danger(data.message)
                    }
                }
            });
        }


    }

function query() {

  var caption=$("input[name='caption']").val();
//    if(caption==null ||caption==""){
//        Notify.danger("请输入查询条件");
//        return;
//    }

    $("#queryStudent").attr("href","/student/studentlist/query?caption="+caption)[0].click();






}

</script>