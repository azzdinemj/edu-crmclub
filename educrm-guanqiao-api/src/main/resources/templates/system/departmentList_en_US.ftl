<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>Departmental information <span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue"  data-toggle="modal" data-target="#modal" data-url="/system/department/create">add</a>
                <button title="operations" type="button" class="btn btn-newblue btn-sm dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a onclick="del()" data-toggle="modal">delete</a></li>
                </ul>

            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
            <div class="panel-heading pd10 bor0 new">
                <!--High search-->
                <#--<form class="form-inline search white">-->
                    <#--<div class="form-group">-->
                        <#--<label class="control-label col-xs-5">Subordinate department :</label>-->
                        <#--<div class="col-xs-7">-->
                            <#--<select class="selectpicker form-control">-->
                                <#--<optgroup label="General school">-->
                                    <#--<option>Financial department</option>-->
                                    <#--<option>Financial department</option>-->
                                    <#--<option>Financial department</option>-->
                                <#--</optgroup>-->
                                <#--<option>Financial department</option>-->
                                <#--<option>Financial department</option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->

                <#--</form>-->
            </div>
        </div>

        <div class="panel-body pd0">
           <#-- <input class="button" value="Add the page link button" data-toggle="modal" data-target="#modal"data-url="/system/department/edit?pkDepartment=4">
           --> <div id="div1"></div>

        </div>

    </div>



<#include "../commons/footer.ftl"/>
<script type="text/javascript">
    window.onload = function() {
        $.ajax({
            type: "GET",
            url: "/system/department/getData",
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