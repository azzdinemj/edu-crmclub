
<#include "../commons/top.ftl" />
<link href="${staticPath}/css/upload.css" type="text/css" rel="stylesheet">
<#include "../commons/left.ftl" />



        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>添加竞赛记录
                    <span>...</span></h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-newblue" onclick="sub()">保存</a>
                        <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">

                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <form class="form-look search white" id="formId" method="post" action="/classinfo/raceRecord/save"
                              data-target="/classinfo/classinfo/edit?pkClassinfo=${(raceRecord.pkClassinfo)!}">
                            <input name="pkClassinfo" class="form-control lgwidth" type="hidden" value="${(raceRecord.pkClassinfo)!}" title="">
                            <input name="raceId" class="form-control lgwidth" type="hidden" value="${(raceRecord.raceId)!}" title="">
                            <div class="dining">
                                <div class="row">
                                    <label class="control-label col-md-1">竞赛名称</label>
                                    <div class="col-md-5"><input name="act.raceName" class="form-control" type="text" value="${(raceRecord.raceName)!}" title=""></div>

                                    <label class="control-label col-md-1">竞赛日期</label>
                                    <div class="col-md-5"><input name="raceDateTime" class="form-control  js-datepicker" value="${(raceRecord.raceDate?string("yyyy-MM-dd"))!}" type="text"></div>

                                </div>
                                <div class="row">
                                    <label class="control-label col-md-1">主办单位</label>
                                    <div class="col-md-5"><input name="sponsor" class="form-control" type="text" value="${(raceRecord.sponsor)!}"  title=""></div>
                                    <label class="control-label col-md-1">最高荣誉</label>
                                    <div class="col-md-5"><input name="maxHonor" class="form-control " value="${(raceRecord.maxHonor)!}" type="text"></div>

                                </div>

                                <div class="clearfix"></div>
                                <label class="control-label col-md-1">获奖记录</label>
                                <div class="col-md-11">
                                    <table id="table1"  class="table table-striped table-bordered" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>奖项</th>
                                            <th>获奖人</th>
                                            <th><span class="btn btn-sm" title="添加" onclick="addrow();" style="font-size: 25px;font-weight: 600;color: #0099FF">+</span></th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if raceRecord.raceAwardsList??>
                                            <#assign y=0 />
                                        <#list raceRecord.raceAwardsList as r>
                                        <tr>
                                            <td><input type="text" name="raceAwardsList[0].awardsName" title="" class="form-control lgwidth" value="${(r.awardsName)!}"></td>
                                            <td>
                                                <input type="hidden" title="" id="studentIds0" name="raceAwardsList[0].studentIds" value="${(r.studentIds)!} class="form-control lgwidth">
                                                <input type="text" title="" id="studnetNames0" value="${(r.map.studentNames)!}" onclick="findStudent(0,this.id)"
                                                       class="form-control lgwidth" data-toggle="modal" data-target="#modal" >
                                            </td>
                                            <td></td>

                                        </tr>
                                            <#assign y=y+1/>
                                        </#list>

                                        <#else >
                                        <tr>
                                            <td><input type="text" name="raceAwardsList[0].awardsName" title="" class="form-control lgwidth"></td>
                                            <td>
                                                <input type="hidden" title="" id="studentIds0" name="raceAwardsList[0].studentIds" class="form-control lgwidth">
                                                <input type="text" title="" id="studnetNames0" <#--name="raceAwardsList[0].map.studentName"--> onclick="findStudent(0,this.id)"
                                                       class="form-control lgwidth" data-toggle="modal" data-target="#modal" >
                                            </td>
                                            <td></td>

                                        </tr>
                                        </#if>


                                        </tbody>

                                    </table>
                                </div>
                                <#--<label class="control-label col-md-1">活动照片</label>-->
                                <#--<div class="col-md-11">-->
                                    <#--<div id="fileUploadContent" class="fileUploadContent">-->

                                    <#--</div>-->

                                <#--</div>-->
                            <#--</div>-->
                            <#--<div>-->
                                <div class="row">
                                <label class="control-label col-md-1">封面照片</label>
                                <div class="control-label col-md-3">
                                    <div class="student-img upload text-center">
                                        <input type="file" name="file" id="doc" class="newdoc"
                                               onchange="ajaxSubmitForm1(this.id)" />
                                        <div id="dd" class="newdd">
                                        <#if raceRecord.activityImgList ?? &&(raceRecord.activityImgList?size>0) >
                                            <img src="${(raceRecord.activityImgList[0].imgUrl)!}" style="width: 35px;height: 35px;">
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
                                </div>
                                    <label class="control-label col-md-1">竞赛图片</label>
                                    <div id="fileUploadContent" class="fileUploadContent">
                                        <div class="box">
                                            <#assign x=0 />
                                            <#if raceRecord.activityImgList ?? &&(raceRecord.activityImgList?size>0) >
                                                <#list raceRecord.activityImgList as c >

                                                    <#if x == 0><#else >
                                                        <img src="${(c.imgUrl)!}" >
                                                    </#if>
                                                    <#assign x=x+1/>
                                                </#list>

                                            </#if>
                                        </div>
                                    </div>
                                </div>

                                <div class="clearfix"></div>
                            </div>

                        </form>

                    </div>
                </div>



            </div><!-- panel -->
        </div>

        <!--添加部分结束==============================================-->
        <#include "../commons/footer.ftl"/>

<script src="${staticPath}/js/fileUpload.js"></script>
<script src="${staticPath}/js/upload.js"></script>

</section>

<script>
//    $(function(){
//        //禁止排序
//        $("#table").dataTable({
//            ordering:false
//        })
//    })

<#if raceRecord.raceAwardsList??>
var num =${raceRecord.raceAwardsList?size};
<#else >
var num = 1;
</#if>
    //添加一行"man[' + parentNumber + '].relationship"
    function addrow(){
        var tables = $('#table1');
        var addtr = $("<tr>"+
                "<td><input type='text' class='form-control lgwidth' name='raceAwardsList["+num+"].awardsName' /></td>"+
                "<td><input type='hidden' id='studentIds"+num+"' class='form-control lgwidth' name='raceAwardsList["+num+"].studentIds' />"+
                "<input type='text' id='studnetNames"+num+"' class='form-control lgwidth' name=''" +
                "data-toggle='modal' data-target='#modal' onclick='findStudent("+num+",this.id)'/></td>"+
                "<td><span  onclick='deleteTrRow(this);'>&nbsp;删除</span></td>"+
                "</tr>");
        addtr.appendTo(tables);
        num ++;
    }
    function deleteTrRow(tr){
        $(tr).parent().parent().remove();
    }
    var index = num;
    function findStudent(num,id) {
        index = num;
        var url = "/classinfo/raceRecord/findStu?pkClassinfo=${(raceRecord.pkClassinfo)!}";
        $("#"+id).attr("data-url",url);
        return true;

    }

    function assignmentStu(studentIds,names) {

        $("#modal").modal("hide");

        $("#studentIds"+index).val(studentIds);
        $("#studnetNames"+index).val(names);
    }

    function sub() {

        var formData= $("#formId").serialize();
        $.ajax({
            type: "POST",
            url: "/classinfo/raceRecord/save",
            data: formData,
            traditional:true,
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    window.location.href="/classinfo/classinfo/edit?pkClassinfo=${(raceRecord.pkClassinfo)!}";
                } else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            }
        });
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

