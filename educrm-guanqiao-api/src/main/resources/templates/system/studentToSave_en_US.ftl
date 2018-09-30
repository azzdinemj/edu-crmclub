
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />



<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Student<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
            </div>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

                <div class="panel-btns">
                    <a href="" class="minimize news-minimize">Advanced search<i class=" fa fa-chevron-down"></i></a>
                </div><!-- panel-btns -->
                <!--High search-->
                <form class="form-inline search white">
                    <div class="form-group">
                        <label class="control-label">Class name :</label>
                        <div class="col-xs-8">
                        <#--<input id="code" name="name" class="form-control" type="text">-->
                            <select id="pkClassinfoId" class="selectpicker show-tick form-control"  data-live-search="true">
                                <option value="">Please choose</option>
                                <#if classinfo??>
                                    <#list classinfo as c>
                                        <option value="${(c.pkClassinfo)!}">${(c.caption)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Student name :</label>
                        <input type="text" title="" id="nameid"  name="name"  class="form-control form-input-lg"  />
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search">search</a>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search">Can live${(dormRoom.num)!}人</a>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search">Have lived${(dormRoom.currentNum)!0}人</a>
                    </div>
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm " onclick="submit()">Check in</a>
                    </div>

                </form>
                <!--Hide search-->
                <div class="panel-body senior-search">
                    <div id="post-status" class="tab-pane active">
                    </div>

                </div>
            </div>
        </div>

        <div class="panel-body pd0">
            <div class="table-responsive">
                <table id="pagingTable"  class="table table1-striped table-bordered" cellspacing="0">
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
        {"sTitle":"Choice","data" : "pkStudent",
            "render": function (data, type, row){
                return '<input class="checkboxAll" type="checkbox" value="'+data+'" caption="'+row.caption+'" class="px">';
            }} ,
        {"sTitle":"number","data":"code"},
        {"sTitle":"name","data" : "caption"} ,
        {"sTitle":"Sex","data" : "sex"},
        {"sTitle":"class","data" : "sex"},
        {"sTitle":"grade","data" : "sex"},
        {"sTitle":"Ethnic","data" : "sex"}
    ];

    $(document).ready(function() {
        query();
    } );

    function query() {
        var data  = { "pkClassinfo": $("#pkClassinfoId").val(),"caption":$("#caption").val(),"istype":"1","pageSize":40};
        init(columns,"/student/studentSignup/queryByPaging",data);
    }


    function submit() {

        var result = new Array();
//        $(".checkboxAll").each(function () {
//            var values = $(this).is(':checked');
//            if (values) {
//                var s =$(this).attr("value");
//                alert(s);
//                employeeList = employeeList + $(this).attr("value") + ",";
//
//            }
//        });

        $.each($('input:checkbox:checked'),function(){
//             window.alert("You chose it："+
//            $('input[type=checkbox]:checked').length+"个，One of them is："+$(this).val());
        result.push($(this).attr("value"));
    });
        JSON.stringify(result);
        var num = ${(dormRoom.num)!0};
        var currentNum = ${(dormRoom.currentNum)!0};
        var s = num - currentNum;
        <#--number num = ${(dormRoom.num)!0}-${(dormRoom.currentNum)!0};-->
//        alert(s);
        if(result.length > s){
            alert("The dorm can only live"+num+"人");
            return;
        }
        $.ajax( {
            type: "POST",
            url: "/system/dormRoom/saveAll",
            data: {"pkStudents": result,"pkDormRoom":"${(dormRoom.pkDormRoom)!}"},
            dataType: "json",
            traditional: true,
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    setTimeout("location.reload()", 1);
                } else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            }
        })


    }

</script>
