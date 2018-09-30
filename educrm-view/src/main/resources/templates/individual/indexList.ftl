<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>个性化档案 <span>...</span></h2>
    </div>
    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <!--高显搜索-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title="" name="reservation" id="datas" class="form-control form-input-lg"
                               value=""/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">学生姓名 :</label>
                        <input type="text" title="" name="reservation" class="form-control form-input-lg" value=""/>
                    </div>

                </form>

            </div>
        </div>
        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>学生编号</th>
                        <th>学生姓名</th>
                        <th>班级</th>
                        <th>年级</th>
                        <th>创建人</th>
                        <th>创建时间</th>
                        <th>修改人</th>
                        <th>修改时间</th>
                    </tr>
                    </thead>
                    <tbody>
                       <tr>
                           <td align="left"> <a class="yellow" href="/individual/individualstudent/edit" target="_blank">DS45788</a></td>
                           <td align="left">蕊蕊</td>
                           <td align="left">一二班</td>
                           <td align="left">五年级</td>
                           <td align="left">蕊蕊</td>
                           <td align="left">2018\1\8</td>
                           <td align="left">蕊蕊</td>
                           <td align="left">2018\1\8</td>

                       </tr>
                       <tr>
                           <td align="left"> <a class="yellow" href="/individual/individualstudent/edit" target="_blank">DS45788</a></td>
                           <td align="left">蕊蕊</td>
                           <td align="left">一二班</td>
                           <td align="left">五年级</td>
                           <td align="left">蕊蕊</td>
                           <td align="left">2018\1\8</td>
                           <td align="left">蕊蕊</td>
                           <td align="left">2018\1\8</td>

                       </tr>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

</div><!-- contentpanel -->
<#include "../commons/footer.ftl"/>
