
function setImagePreviews(avalue) {
    var docObj = document.getElementById("doc");
    var dd = document.getElementById("dd");
    dd.innerHTML = "";
    var fileList = docObj.files;
    for (var i = 0; i < fileList.length; i++) {

        dd.innerHTML += "<div> <img id='img" + i + "'  /> </div>";
        var imgObjPreview = document.getElementById("img"+i);
        if (docObj.files && docObj.files[i]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '80px';
            imgObjPreview.style.height = '80px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
        }
        else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            alert(imgSrc)
            var localImagId = document.getElementById("img" + i);
            //必须设置初始大小
            localImagId.style.width = "80px";
            localImagId.style.height = "80px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }

    return true;
}
/*多张图片预览*/
$("#fileUploadContent").initUpload({
    "uploadUrl":"#",//上传文件信息地址
    //"size":350,//文件大小限制，单位kb,默认不限制
    //"maxFileNumber":3,//文件个数限制，为整数
    //"filelSavePath":"",//文件上传地址，后台设置的根目录
    "beforeUpload":beforeUploadFun,//在上传前执行的函数
    //"onUpload":onUploadFun，//在上传后执行的函数
    //autoCommit:true,//文件是否自动上传
    "fileType":['png','jpg','docx','doc']//文件类型限制，默认不限制，注意写的是文件后缀
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