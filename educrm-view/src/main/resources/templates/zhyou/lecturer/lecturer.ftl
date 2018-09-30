<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">

<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>添加讲师<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>
               <#-- <a class="btn btn-sm btn-newblue changeemployee">教师</a>-->
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <form class="look form-ajax" method="POST" id="formId" action="" data-target="" >
            <div class="row">
                <div class="col-xs-12 col-md-4">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">姓名</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="" type="text">
                            <input name="" value="" type="hidden">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">email</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control"  name="" value="" type="email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">学校</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" name="" value="" type="text">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">地址</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" name="" value="" type="text">

                        </div>
                    </div>

                </div><!-- col-sm-4 -->
                <div class="col-xs-12 col-md-4">
                    <div class="form-group">
                        <label class="control-label col-xs-3">电话</label>
                        <div class="col-xs-9">
                            <input class="form-control" name="" value="" type="text">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3">分组类型</label>
                        <div class="col-xs-9">
                            <select class="form-control">
                                <option>高级讲师</option>
                                <option>资深讲师</option>
                            </select>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">性别</label>
                        <div class="col-xs-9 col-md-9">
                                         <span class="rdio rdio-warning">
                                           <input name="sex" value="1" checked="checked" id="radio2" type="radio">
                                           <label for="radio2">男</label>
                                       </span>
                            <span class="rdio rdio-warning">
                                           <input name="sex" value="0" id="radio3"  type="radio">
                                           <label for="radio3">女</label>
                                       </span>
                        </div>
                    </div>
                </div><!-- col-sm-4 -->
                <div class="col-xs-12 col-md-4">
                    <div class="student-img upload text-center">
                        <input type="file" name="file" id="doc" multiple="multiple" style="width:80px;"
                               onchange="javascript:setImagePreviews();" accept="image/*"/>
                        <div id="dd">
                            <img src="${staticPath}/images/photos/icon-h.jpg">
                        </div>
                        <p class="gray">上传头像</p>
                    </div>


                </div>

            </div>
            <div class="row">

                <div class="col-xs-12 col-md-12">
                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">试听视频</label>
                        <div class="col-xs-11 col-md-11">
                            <textarea class="form-control" rows="4"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-1 col-md-1">备注</label>
                        <div class="col-md-11">
                            <textarea class="form-control" rows="4"></textarea>

                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<#include "../../commons/footer.ftl"/>
<script type="text/javascript">

    function clear(str) {
        str = str.replace(/,/g, "");//取消字符串中出现的所有逗号
        return str;
    }
    function setImagePreviews(avalue) {
        var docObj = document.getElementById("doc");
        var dd = document.getElementById("dd");
        dd.innerHTML = "";
        var fileList = docObj.files;
        for (var i = 0; i < fileList.length; i++) {

            dd.innerHTML += "<div> <img id='img" + i + "'  /> </div>";
            var imgObjPreview = document.getElementById("img" + i);
            if (docObj.files && docObj.files[i]) {
                //火狐下，直接设img属性
                imgObjPreview.style.display = 'block';
                imgObjPreview.style.width = '112px';
                imgObjPreview.style.height = '152px';
                //imgObjPreview.src = docObj.files[0].getAsDataURL();
                //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
                imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
            }
            else {
                //IE下，使用滤镜
                docObj.select();
                var imgSrc = document.selection.createRange().text;
//                alert(imgSrc)
                var localImagId = document.getElementById("img" + i);
                //必须设置初始大小
                localImagId.style.width = "112px";
                localImagId.style.height = "152px";
                //图片异常的捕捉，防止用户修改后缀来伪造图片
                try {
                    localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                    localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                }
                catch (e) {
                    Notify.danger("您上传的图片格式不正确，请重新选择!");
                    return false;
                }
                imgObjPreview.style.display = 'none';
                document.selection.empty();
            }
        }

        return true;
    }
</script>