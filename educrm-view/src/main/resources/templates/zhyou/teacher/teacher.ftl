
<#assign staticPath=''>

<#include "${staticPath}/commons/top.ftl" />
<#include "${staticPath}/commons/left.ftl" />
<form class="look form-ajax" id="formid" method="post" action="/teacher/teacherlist/save" data-target="/teacher/teacherlist/query">
    <div class="contentpanel">
        <div class="pageheader">
            <h2><i class="fa fa-bookmark"></i>教师资料<span>...</span></h2>
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
            <input type="hidden" name="pkEmployee" value="${(employee.pkEmployee)!}">
            <input type="hidden" name="pkDomain" value="2">
            <input type="hidden" name="pkDepartment" value="zhyou">


            <div class="row">
                        <div class="col-xs-12 col-md-4">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-3">教师姓名</label>
                                <div class="col-xs-9 col-md-9">
                                    <input class="form-control"  value="${(employee.caption)!}" name="caption" type="text" placeholder="教师姓名" data-bv-notempty="" data-bv-notempty-message="请输入教师的名称">
                                </div>
                            </div>
                        </div>
                    </div>

            <div class="row">
                <div class="col-xs-12 col-md-4">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">教师年龄</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control"  value="${(employee.code)!}" name="code" type="text" placeholder="教师年龄" data-bv-notempty="" data-bv-notempty-message="请输入教师的年龄">
                        </div>
                    </div>
                </div>
            </div>

            <#--<div class="row">-->
                <#--<div class="col-xs-12 col-md-12">-->
                    <#--<div class="form-group">-->
                        <#--<label class="control-label col-xs-3 col-md-1">头像</label>-->
                        <#--<div class="col-xs-9 col-md-11">-->
                            <#--<img id="pic" src="" >-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->

            <#--</div>-->
            <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <div class="form-group">
                                <label class="control-label col-xs-3 col-md-1">教师头像</label>
                                <div class="col-xs-9 col-md-11">
                                    <input type="file" id="file1" name="file" onchange="ajaxSubmitForm(this.id)"/>
                                    <input type="hidden" id="picImg" name="address" value="${(employee.address)!}">
                                </div>
                            </div>
                        </div>

            </div>

            <div class="row">
                <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-1">性别</label>

                        <div class="col-xs-9 col-md-3">
                            <select name="sex" class="form-control">
                                <option <#if employee??&&employee.sex??&&employee.sex==0> selected="" </#if> value="0">女</option>
                                <option <#if employee??&&employee.sex??&&employee.sex==1> selected="" </#if>  value="1">男</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">教师简介</label>
                        <div class="col-xs-11 col-md-11">
                            <textarea  placeholder="教师简介"   name="memo" style="width:100%;height:300px;">${(employee.memo)!}</textarea>
                        </div>
                    </div>
                </div>
            </div>




        </div>
    </div>
</form>

<#include "${staticPath}/commons/footer.ftl"/>


<style>
    #pic{
        width:100px;
        height:100px;
        border-radius:50% ;
        margin:20px auto;
        cursor: pointer;
    }
</style>
<script type="text/javascript">

    // 图片
    function ajaxSubmitForm(ids){
        var i=layer.msg('正在上传...', {icon: 16,shade: [0.5, '#f5f5f5'],scrollbar: false,offset: '0px', time:100000}) ;

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
                layer.close(i);
                if(result.code == 0){
                    document.getElementById("picImg").value = result.data;
                    Notify.success("success");
                }
            },
            error:function(){
                layer.close(i);
                alert("上传异常。。。");
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
