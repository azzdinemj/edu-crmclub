<#include "../../commons/top.ftl">
<#include "../../commons/left.ftl">

<form class="look form-ajax" method="POST" id="formId" action="/question/questionPaper/save" data-target="/question/questionPaper/query" >
<div class="contentpanel">
    <div class="pageheader">
        <h2><i class="fa fa-bookmark"></i>题库试卷<span>...</span></h2>
        <div class="breadcrumb-wrapper">
            <div class="btn-group fr title-btn">
                <a class="btn btn-sm btn-newblue" onClick="javascript:history.back(-1);">返回</a>
                <input class="btn btn-sm btn-newblue" type="submit" value="保存">
            </div>
        </div>
    </div>

    <!--content-->
    <div class="panel panel-default pl35">


        <div class="form-group">

            <label class="col-sm-2 control-label no-padding-top"> 试卷类型：</label>

            <div class="col-sm-10">
                <select name="type" class="form-control col-xs-6" required >


                    <option  value="1" >真题模考</option><option  value="2" >组卷模考</option>							</select>


            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-top"> 试卷名称:</label>

            <div class="col-sm-10">
                <input class="form-control" name="title" id="title"  value=""  placeholder="试卷名称" required />

            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-top"> 试卷说明:</label>

            <div class="col-sm-10">
                <textarea id="explain" name="explain"  class="form-control" rows="5" cols="80" placeholder="试卷说明" required></textarea>

            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label no-padding-top"> 分数：</label>

            <div class="col-sm-3">
                <div class="input-group">
						<span class="input-group-addon">
							<i class="ace-icon fa fa-check">总分</i>
						</span>
                    <span class="input-icon input-icon-right">
							<input type="text" name="score" class=" input-small" placeholder="总分" required />
							<i>分</i>
						</span>



                </div>


            </div>
            <div class="col-sm-3">
                <div class="input-group">

						<span class="input-group-addon">
							<i class="ace-icon fa fa-check">及格分</i>
						</span>
                    <span class="input-icon input-icon-right">
							<input type="text" name="passscore" class=" input-small" placeholder="及格分" required />
							<i>分</i>
						</span>


                </div>


            </div>

        </div>
        <div class="form-group">

            <label class="col-sm-2 control-label no-padding-top"> 考试时间：</label>

            <div class="col-sm-4">
                <div class="input-group">
						<span class="input-group-addon">
							<i class="ace-icon fa fa-check">考试时间</i>
						</span>
                    <span class="input-icon input-icon-right">
							<input type="text" name="takes" class=" input-small" placeholder="考试时间" required />
							<i>分钟</i>
						</span>




                </div>


            </div>
            <div class="col-sm-3">
                <div class="input-group">

						<span class="input-group-addon">
							<i class="ace-icon fa fa-check">年份</i>
						</span>
                    <span class="input-icon input-icon-right">
							<input type="text" name="year" class=" input-small" placeholder="年份" required />
							<i>年</i>
						</span>


                </div>


            </div>

        </div>

    </div>
</div>


    </div>
</div>
</form>
<#include "../../commons/footer.ftl"/>




<script type="text/javascript">




    $("#submitQuestions").click(function () {

        $.get("/question/questionlist/submitQuestions?pkQuestions=${(questions.pkQuestions)!}", function(data){
            Notify.success("提交成功！");
            //提交成功跳转列表页面
            location.href="/question/questionlist/query?"+new Date().getTime();
        });


    });


</script>