
<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>讲师管理<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
             <a class="btn btn-sm btn-newblue" href="/lecturer/lecturerlist/edit">新建讲师</a>
                <a class="btn btn-sm btn-newblue" onclick="del()">删除</a>

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
                    <div class="form-group">
                        <label class="control-label">日期 :</label>
                        <input type="text" title="" id="timeid"  name="datetime"  class="form-control js-datepicker"  />
                    </div>
                    <div class="form-group">
                        <label class="control-label">讲师名称 :</label>
                        <input type="text" title="" id="nameid"  name="name"  class="form-control"  />
                    </div>
                <#--    <div class="form-group">
                        <label class="control-label">类别 :</label>
                        <select class="form-control strw">
                            <option>全部</option>
                            <option>发布</option>
                            <option>草稿</option>
                            <option>下架</option>
                        </select>
                    </div>-->
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search">搜索</a>
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
                <table id="table"  class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>帐号</th>
                        <th>姓名</th>
                        <th>电话</th>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>56</td>
                        <td><a class="yellow" href="/lecturer/lecturerlist/edit">讲师</a></td>
                        <td></td>
                        <td></td>

                    </tr>
                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->

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

</script>

