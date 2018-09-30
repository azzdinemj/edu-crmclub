<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Pedestrian management <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <#--<a class="btn btn-sm btn-newblue" href="/teacher/teacherlist/edit">The new employee</a>-->

                <#--<a class="btn btn-sm btn-newblue" onclick="del()" data-toggle="modal">delete</a>-->
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <#--<div class="panel-btns">-->
                    <#--<a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>-->
                <#--</div><!-- panel-btns &ndash;&gt;-->
                <!--High show search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">The name :</label>
                        <input type="text"  id="caption" name="reservation" class="form-control form-input-lg"
                               placeholder="The name" />
                    </div>

                    <div class="form-group">
                          <label class="control-label">gender :</label>
                          <select id="sex" class="form-control strw">
                              <option value="">Please select a</option>
                              <option value="1">男</option>
                              <option value="0">女</option>
                          </select>
                    </div>

                    <div class="form-group">
                        <label class="control-label">The activity type :</label>
                        <select id="productId" class="form-control  strw">
                            <option value="">Please select a</option>
                            <option value="1">space</option>
                            <option value="2">football</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">search</a>
                    </div>

                </form>
                <!--Hide the search-->
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
        {"sTitle":"The name","data":"name"},
        {"sTitle":"gender","data" : "sex", "render":renderSex},
        {"sTitle":"age","data" : "birthday","render":renderAge},
        {"sTitle":"The activity type","data" : "productId","render":renderProductType},
        {"sTitle":"Document type","data" : "cardType","render":renderCardType},
        {"sTitle":"I.d.","data" : "cardNum"},
        {"sTitle":"Parents names","data" : "parentName"},
        {"sTitle":"Parents call","data" : "parentPhone"},
        {"sTitle":"Creation time","data" : "creatorDate","render": renderDate},

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
            Notify.danger("Select a piece of data");
            return;
        }
        var flag = confirm("Confirm deletion？");
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