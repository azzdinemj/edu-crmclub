
<#include "../../commons/top.ftl" />
<#include "../../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>题库科目 <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" href="/question/questionProject/edit">创建科目</a>
                <button title="更多操作" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a onclick="del()">删除</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">

            </div>
        </div>


    <#-- <div id="div1"></div>-->
        <div class="panel-body pd0">

            <div id="div1"></div>

        </div>
    </div>
</div><!-- contentpanel -->
<#include "../../commons/footer.ftl"/>
<script type="text/javascript" >
    window.onload = function() {
        $.ajax({
            type: "GET",
            url: "/question/questionProject/getData",
            data: {},
            dataType: "json",
            success: function (data) {
                var treeGrid = new TreeGrid(data);
                treeGrid.show()
            },
            error: function () {

            }
        });

    }

</script>
