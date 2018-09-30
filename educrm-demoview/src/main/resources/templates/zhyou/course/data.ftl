<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                添加资料
            </h4>
        </div>
        <div class="modal-body">
            <div id="fileUploadContent" class="fileUploadContent"></div>
            <#--<button onclick="testUpload()" class="btn btn-newblue btn-sm">上传</button>-->
         </div>
        <div class="modal-footer">
            <input type="hidden" id="menuList" name="menuList">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
        <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
            <input class="btn btn-newblue btn-sm saveButton" id="dosubmit" value="保存" type="submit">
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->
<script type="text/javascript">
    $("#fileUploadContent").initUpload({
        "uploadUrl":"#",//上传文件信息地址
        //"size":350,//文件大小限制，单位kb,默认不限制
        //"maxFileNumber":3,//文件个数限制，为整数
        //"filelSavePath":"",//文件上传地址，后台设置的根目录
        "beforeUpload":beforeUploadFun,//在上传前执行的函数
        //"onUpload":onUploadFun，//在上传后执行的函数
        //autoCommit:true,//文件是否自动上传
        "fileType":['png','jpg','docx','doc','xlsx','pdf']//文件类型限制，默认不限制，注意写的是文件后缀
    });
    function beforeUploadFun(opt){
        opt.otherData =[{"name":"name","value":"zxm"}];
    }
    function onUploadFun(opt,data){
        alert(data);
        uploadTools.uploadError(opt);//显示上传错误
        uploadTools.uploadSuccess(opt);//显示上传成功
    }


    function testUpload(){
        var opt = uploadTools.getOpt("fileUploadContent");
        uploadEvent.uploadFileEvent(opt);
    }
</script>