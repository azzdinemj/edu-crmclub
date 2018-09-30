
<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />
<form class="look form-ajax" id="formid" method="post" action="/jiedian/product/save" data-target="/jiedian/product/query">
    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>产品资料<span>...</span></h2>
            <div class="breadcrumb-wrapper">
                <div class="btn-group fr title-btn">
                    <a class="btn btn-sm btn-newblue" onClick="history.back(-1);">返回</a>
                    <input class="btn btn-sm btn-newblue" type="submit" value="保存">
                    <#--<a class="btn btn-newblue btn-sm" >-->
                        <#--保存-->
                    <#--</a>-->
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <input type="hidden" name="pkProduct" value="${(product.pkProduct)!}">
            <input type="hidden" name="pkDomain" value="3">
            


            <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">产品名称</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control"  value="${(product.caption)!}" name="pro.caption" type="text">
                                </div>
                            </div>
                        </div>
                    </div>

           

           

            <div class="row">
            	 <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">科目</label>

                        <div class="col-xs-9 col-md-3">
                            <select name="pro.subject" class="form-control">
                            	 <#if subject ??>
                                            <#list subject as s>
                                                <option value="${(s.pkSysDictValues)!}"
                                                        <#if s.pkSysDictValues ?? && product.subject??&& s.pkSysDictValues==product.subject>selected="selected"</#if>> ${(s.caption)!}</option>
                                            </#list>
                                        </#if>
                       
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">状态</label>

                        <div class="col-xs-9 col-md-3">
                            <select name="pro.status" class="form-control">
                                <option <#if product??&&product.status??&&product.status==0> selected="" </#if> value="0">启用</option>
                                <option <#if  product??&&product.status??&&product.status==1> selected="" </#if>  value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">产品简介</label>
                        <div class="col-xs-11 col-md-11">
                            <textarea   name="pro.notes" style="width:100%;height:300px;">${(product.notes)!}</textarea>
                        </div>
                    </div>
                </div>
            </div>




        </div>
    </div>
</form>




<#include "${staticPath}/commons/footer.ftl"/>

<script type="text/javascript">

    // 图片
    function ajaxSubmitForm(ids){
        var requestData = {};
        var value= document.getElementById(ids).value;
        requestData.file = value;
        var id_str=ids;
        $.ajaxFileUpload({
            url : '/file/upload',
            type : 'post',
            secureuri : false,
            data : requestData,
            fileElementId : id_str,  //文件             //普通数据
            dataType : 'json',
            success : function(result) {
                if(result.code == 0){
                    document.getElementById("picImg").value = result.data;
                    Notify.success("success");
                }
            }
        });
    }













    //删除课程
    function delCourse(id) {
        if (confirm("确定删除吗？")){
            var pkEmployee = "${(employee.pkEmployee)!}";
            // $.ajax({
            //     type: "POST",
            //     url: "/classinfo/courseTeacher/delete",
            //     data: {"pkEmployee": pkEmployee,"pkSysDictValues":id},
            //     dataType: "json",
            //     success: function (data) {
            //         if (data.code == 0) {
            //             Notify.success(data.message);
            //             setTimeout("location.reload()", 1);
            //         } else {
            //             alert(data.message)
            //         }
            //     },
            //     error: function () {
            //
            //     }
            // });

        }


        
    }

</script>
