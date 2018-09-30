<#include "../commons/top.ftl" />
<link href="${staticPath}/css/upload.css" type="text/css" rel="stylesheet">

<#include "../commons/left.ftl" />

        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>发布班级活动
                    <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>
                        <a class="btn btn-sm btn-newblue" href="javascript:history.back(-1);">返回</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <form class="form-ajax search white" id="formId" method="post" action="/classinfo/classActivity/save"
                              data-target="/classinfo/classinfo/edit?pkClassinfo=${(classActivity.pkClassinfo)!}">
                            <div class="row dining">
                                <input name="act.pkClassinfo" type="hidden" class="form-control" value="${(classActivity.pkClassinfo)!}">
                                <input name="act.activityId" type="hidden" class="form-control" value="${(classActivity.activityId)!}">
                                <label class="control-label col-md-1">活动名称</label>
                                <div class="col-md-5"><input name="act.activityName" class="form-control" type="text" value="${(classActivity.activityName)!}" title=""></div>
                                <label class="control-label col-md-1">活动日期</label>
                                <div class="col-md-5">
                                    <input name="act.activityTimes" value="${(classActivity.activityTime?string("yyyy-MM-dd"))!}" class="form-control js-datepicker" type="text">
                                </div>
                                <div class="clearfix">

                                </div>
                                <label class="control-label col-md-1">活动描述</label>
                                <div class="col-md-11">
                                    <textarea name="act.description"  class="form-control " rows="6" style="margin-bottom: 15px;">${(classActivity.description)!}</textarea>
                                </div>
                                <div class="clearfix"></div>
                                <label class="control-label col-md-1">活动封面</label>
                                <div class="control-label col-md-11">
                                    <div class="student-img upload text-center">

                                        <input type="file" name="file" id="doc" class="newdoc"
                                               onchange="ajaxSubmitForm1(this.id)" />
                                        <div id="dd" class="newdd">
                                            <#if classActivity.activityImgList ?? &&(classActivity.activityImgList?size>0) >
                                                <img src="${(classActivity.activityImgList[0].imgUrl)!}" style="width: 35px;height: 35px;">
                                            <#else >
                                                <img src="${staticPath}/images/plus-white.png" style="width: 35px;height: 35px;">
                                            </#if>

                                        </div>
                                        <input type="hidden" name="act.activityImgList[0].imgUrl" id="studentImg" value="${(classActivity.activityImgList[0].imgUrl)!}">
                                        <input type="hidden" name="act.activityImgList[0].imgOrder" value="1">
                                        <input type="hidden" name="act.activityImgList[1].imgUrl" id="img2" value="${(classActivity.activityImgList[1].imgUrl)!}">
                                        <input type="hidden" name="act.activityImgList[1].imgOrder" value="2">
                                        <input type="hidden" name="act.activityImgList[2].imgUrl" id="img3" value="${(classActivity.activityImgList[2].imgUrl)!}">
                                        <input type="hidden" name="act.activityImgList[2].imgOrder" value="3">
                                        <#--<img src="">-->
                                    </div>

                                </div>
                                <label class="control-label col-md-1">活动图片</label>

                                <div class="col-xs-11">
                                <div id="fileUploadContent" class="fileUploadContent">
                                    <div class="box">
                                    <#assign x=0 />
                                    <#if classActivity.activityImgList ?? &&(classActivity.activityImgList?size>0) >
                                        <#list classActivity.activityImgList as c >

                                            <#if x == 0><#else >


                                                    <img src="${(c.imgUrl)!}" >


                                            </#if>
                                            <#assign x=x+1/>
                                        </#list>

                                    </#if>
                                    </div>
                                </div>

                                </div>
                                <#--<label class="control-label col-md-1">活动描述</label>-->
                                <#--<div class="col-md-11">-->
                                    <#--<textarea class="form-control " rows="6" ></textarea>-->
                                <#--</div>-->
                                <div class="clearfix"></div>
                            </div>

                        </form>

                    </div>
                </div>



            </div><!-- panel -->
<#include "../commons/footer.ftl"/>
            <script src="${staticPath}/js/fileUpload.js"></script>
            <script src="${staticPath}/js/upload.js"></script>

  <script type="text/javascript">

        function sub() {
            $("#formId").submit();
        }


      function ajaxSubmitForm1(id){
          var requestData = {};
          var value = $("#doc").val();
          requestData.file = value;
          var id_str = id;
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
                      setImagePreviews1();
                      $("#studentImg").val(result.data);
//                    alert(result.data);
//                    Notify.success("上传成功");
                  }
              }
          });
      }



      function setImagePreviews1(id) {
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
                  alert(imgSrc)
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
                      alert("您上传的图片格式不正确，请重新选择!");
                      return false;
                  }
                  imgObjPreview.style.display = 'none';
                  document.selection.empty();
              }
          }

          return true;
      }



  </script>

