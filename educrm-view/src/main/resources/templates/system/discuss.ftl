
<#include "../commons/top.ftl" />
<#include "../commons/left.ftl" />
<link href="${staticPath}/css/bus.css" rel="stylesheet">


        <div class="contentpanel">
            <div class="pageheader">
                <h2><i class="fa fa-bookmark"></i>教师留言板</h2>
                <div class="breadcrumb-wrapper">
                    <div class="btn-group fr title-btn">
                        <a class="btn btn-sm btn-info" href="/discuss/discuss/query">返回</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel  panel-alt widget-quick-status-post mb0 bor0">
                    <div class="panel-heading pd10 bor0 new">
                        <div class="panel-body">
                            <form>
                                <input type="hidden" id="studentId" value="${(discuss.studentId)!}">
                                <textarea class="form-control" name="" id="content" placeholder="请输入你的留言..." rows="5"></textarea>
                                <br>
                                <div class="text-right">
                                    <button class="btn btn-sm btn-info" id="teacherSend">发送</button>
                                </div>
                            </form>
                            <div class="level">
                                <#if discussList??>
                                <#list discussList as dis>
                                    <#if dis.type?? && dis.type == 1>
                                        <div class="teacherlevel row">
                                            <div class="userPic col-xs-3"><img src="${(dis.userImg)!}" /></div>
                                            <div class="content col-xs-9">
                                                <h3 class="name">${(dis.userAppellation)!}</h3>
                                                <p class="time">${(dis.createTime?string("yyyy-MM-dd hh:mm"))!}</p>
                                                <p class="contents">${(dis.content)!}</p>
                                            </div>
                                        </div>
                                    <#else >
                                        <div class="studentlevel text-right row">
                                            <div class="userPic"><img src="${(dis.userImg)!}" /></div>
                                            <div class="content">
                                                <h3 class="name">${(dis.userAppellation)!}</h3>
                                                <p class="time">${(dis.createTime?string("yyyy-MM-dd hh:mm"))!}</p>
                                                <p class="contents">${(dis.content)!}</p>
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- panel -->
        </div>

<#include "../commons/footer.ftl"/>
<script>
    $("#teacherSend").on("click",function(){
        var studentId = $("#studentId").val();
        var content = $("#content").val();
        $.ajax({
            url:"/discuss/discuss/save",
            data:{"studentId":studentId,"content":content},
            type:"POST",
            dataType:"json",
            success:function (data) {
                console.log(data);
                if(data.code ==0){
                    Notify.success(data.message);
                    window.location.href="/discuss/discuss/query";
                }else {
                    Notify.danger(data.message);
                    window.location.reload();
                }
            },
            error:function (e) {
                console.log(e);
            }
        });
    })
</script>
