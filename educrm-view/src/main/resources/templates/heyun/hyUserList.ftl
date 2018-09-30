<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>出行人管理 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" href="/teacher/teacherlist/edit">新建员工</a>-->

                <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">删除</a>-->
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
                        <label class="control-label">姓名 :</label>
                        <input type="text"  id="caption" name="reservation" class="form-control form-input-lg"
                               placeholder="姓名" />
                    </div>

                    <div class="form-group">
                          <label class="control-label">性别 :</label>
                          <select id="sex" class="form-control strw">
                              <option value="">请选择</option>
                              <option value="1">男</option>
                              <option value="0">女</option>
                          </select>
                    </div>

                    <div class="form-group">
                        <label class="control-label">活动类型 :</label>
                        <select id="productId" class="form-control  strw">
                            <option value="">请选择</option>
                            <option value="1">航天</option>
                            <option value="2">足球</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>

                </form>
                <!--隐藏搜索-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">

                    </div>

                </div>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable" class="table table-striped table-bordered" cellspacing="0">
                    <thead>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div><!-- panel-body -->

    </div><!-- panel -->





</div><!-- contentpanel -->

</div><!-- mainpanel -->



<#include "../commons/footer.ftl"/>
<script type="text/javascript">

    var columns = [
        {"sTitle":"姓名","data":"name"},
        {"sTitle":"性别","data" : "sex", "render":renderSex},
        {"sTitle":"年龄","data" : "birthday","render":renderAge},
        {"sTitle":"活动类型","data" : "productId","render":renderProductType},
        {"sTitle":"证件类型","data" : "cardType","render":renderCardType},
        {"sTitle":"证件号码","data" : "cardNum"},
        {"sTitle":"家长姓名","data" : "parentName"},
        {"sTitle":"家长电话","data" : "parentPhone"},
        {"sTitle":"创建时间","data" : "creatorDate","render": renderDate},

    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = {"name":$("input[name='reservation']").val(),"sex":$("#sex option:selected").val(),"productId":$("#productId option:selected").val()};
        init(GetTableColumn("hyUserList"),"/heyun/hyUser/queryByPaging",data,"pagingTable");
    }



    function del() {
        if(id == null){
            Notify.danger("请选择一条数据");
            return;
        }
        var flag = confirm("确认删除吗？");
        // if (flag) {
        //     $.ajax({
        //         type: "POST",
        //         url: "/system/employee/delete",
        //         data: {"pkEmployee": id},
        //         dataType: "json",
        //         success: function (data) {
        //             if (data.code == 0) {
        //                 Notify.success(data.message);
        //                 setTimeout("location.reload()", 1);
        //             } else {
        //                 Notify.danger(data.message)
        //             }
        //         },
        //         error: function () {
        //
        //         }
        //     });
        // }

    }

</script>