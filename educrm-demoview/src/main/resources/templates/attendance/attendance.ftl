<#include "../commons/top.ftl">
<#include "../commons/left.ftl">

<form class="look form-ajax" method="POST" id="formId" action="/question/questionlist/save" data-target="/question/questionlist/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>少儿一班<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
            </div>
        </div>
    </div>

    <!--content-->
    <div class="panel panel-default pl35">

        <div class="table-responsive">
            <table id="tables"  class="table table-striped table-bordered" cellspacing="0">
                <thead>
                <tr>
                    <th>学员名称</th>
                    <th>性别</th>
                    <th>打卡次数</th>
                    <th>打卡时间</th>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <td>张文宇</td>
                    <td>男</td>
                    <td>1</td>
                    <td>2018-08-08</td>
                </tr>
                <tr>
                    <td>张文宇</td>
                    <td>男</td>
                    <td>1</td>
                    <td>2018-08-08</td>
                </tr>
                <tr>
                    <td>张文宇</td>
                    <td>男</td>
                    <td>1</td>
                    <td>2018-08-08</td>
                </tr>
                <tr>
                    <td>张文宇</td>
                    <td>男</td>
                    <td>1</td>
                    <td>2018-08-08</td>
                </tr>

                </tbody>
            </table>
        </div><!-- table-responsive -->

    </div>
</div>
</form>
<#include "../commons/footer.ftl"/>




<script type="text/javascript">

    //试题 、 图片
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
                // hideLoading();
                if(result.code == 0){
                    document.getElementById("picUrl").value = result.data;
                    Notify.success("success");
                }
            }
        });
    }


    $("#submitQuestions").click(function () {

        $.get("/question/questionlist/submitQuestions?pkQuestions=${(questions.pkQuestions)!}", function(data){
            Notify.success("提交成功！");
            //提交成功跳转列表页面
            location.href="/question/questionlist/query?"+new Date().getTime();
        });


    });


</script>