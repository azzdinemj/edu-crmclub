<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>

        <#if isNot?? && isNot==0>
            <h4 class="modal-title" id="myModalLabel">
                修改课时
            </h4>
        </#if>

        <#if isNot?? && isNot==1>
            <h4 class="modal-title" id="myModalLabel">
                添加课时
            </h4>
        </#if>

        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" id="formSumbit" method="POST" action="/course/courseList/editOrAddChapter"
                  data-target="/course/courseList/courseware?pkCourse=${(Lessonname.pkCourse)!}">
                <div class="panel panel-default pl35">
                    <div class="row">

                        <input name="sheetType" value="0" type="hidden">
                        <input name="pkCourseLesson" value="${(Lessonname.pkCourseLesson)!}" type="hidden">
                        <input name="pkCourse" value="${(Lessonname.pkCourse)!}" type="hidden">
                        <input name="pkChapter" value="${(Lessonname.pkChapter)!}" type="hidden">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-2">课时名称</label>
                            <div class="col-xs-9 col-md-10">
                                <input name="caption" value="${(Lessonname.caption)!}" class="form-control"
                                       title="" type="text">
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-2">课件类型</label>
                            <div class="col-xs-9 col-md-10">
                                <select name="type" class="form-control">

                                    <option <#if Lessonname.type??&&Lessonname.type=='0' > selected </#if> value="0">
                                        视频
                                    </option>
                                    <option <#if Lessonname.type??&& Lessonname.type=='1' > selected</#if> value="1">
                                        ppt
                                    </option>
                                    <option <#if Lessonname.type??&& Lessonname.type=='2' > selected</#if> value="1">
                                        word
                                    </option>
                                    <option <#if Lessonname.type??&& Lessonname.type=='3' > selected</#if> value="1">
                                        excel
                                    </option>

                                </select>
                            </div><!-- /.col -->
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-2">学员开放</label>
                            <div class="col-xs-9 col-md-10">
                                <select name="status" class="form-control">
                                    <option <#if Lessonname.status?? && Lessonname.status==0 > selected</#if> value="0" >
                                        开放
                                    </option>
                                    <option <#if Lessonname.status?? && Lessonname.status==1 > selected</#if> value="1" >
                                        不开放
                                    </option>
                                </select>
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-2">上传资料</label>
                            <div class="col-xs-9 col-md-10">
                                <#--<div id="fileUploadContent" class="fileUploadContent"></div>-->
                                    <input type="file" id="file" name="file" onchange="ajaxSubmitForm();"/>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-2">视频地址</label>
                            <div class="col-xs-9 col-md-10">
                                <textarea name="url" id="url" class="form-control">${(Lessonname.url)!}</textarea>
                            </div><!-- /.col -->
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-2">课时介绍</label>
                            <div class="col-xs-9 col-md-10">
                                <textarea name="instruction" class="form-control">${(Lessonname.instruction)!}</textarea>
                            </div><!-- /.col -->
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <input type="hidden" id="menuList" name="menuList">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                    <input class="btn btn-newblue btn-sm saveButton" id="dosubmits" value="保存" type="submit">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script>
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function (e) {
// Prevent form submission
        e.preventDefault();

// Get the form instance
        var $form = $(e.target);

// Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

// Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function (data) {

            if (data.code == 0) {
                alert(data.message);
                Notify.success(data.message);
                if ($form.data('target')) {
                    setTimeout("window.location.href='" + $form.data('target') + "'", 1);
                }
            } else {
                Notify.danger(data.message);
            }
        }, 'json');
    });

</script>


<script>

    function ajaxSubmitForm(){
        var requestData = {};
        var value = $("#file").val();
        requestData.file = value;
        var id_str = "file";
        $.ajaxFileUpload({
            url : '/file/upload',
            type : 'post',
            secureuri : false,
            data : requestData,
            fileElementId : id_str,  //文件             //普通数据
            dataType : 'json',
            success : function(result) {
                // hideLoading();
                if(result.code == 0){
                    document.getElementById("url").value = result.data;
                    Notify.success("success");
                }
            }
        });
    }



//    $("#fileUploadContent").initUpload({
//        "uploadUrl":"/file/upload",//上传文件信息地址
//        //"size":350,//文件大小限制，单位kb,默认不限制
//        //"maxFileNumber":3,//文件个数限制，为整数
//        "filelSavePath":"",//文件上传地址，后台设置的根目录
//        "beforeUpload":beforeUploadFun,//在上传前执行的函数
//        "onUpload":onUploadFun，//在上传后执行的函数
//        autoCommit:true,//文件是否自动上传
//        "fileType":['png','jpg','docx','doc','xlsx','pdf']//文件类型限制，默认不限制，注意写的是文件后缀
//    });
//    function beforeUploadFun(opt){
//        opt.otherData =[{"name":"name","value":"zxm"}];
//    }
//    function onUploadFun(opt,data){
//        alert(data);
//        uploadTools.uploadError(opt);//显示上传错误
//        uploadTools.uploadSuccess(opt);//显示上传成功
//    }
//    function testUpload(){
//        var opt = uploadTools.getOpt("fileUploadContent");
//        uploadEvent.uploadFileEvent(opt);
//    }


</script>
