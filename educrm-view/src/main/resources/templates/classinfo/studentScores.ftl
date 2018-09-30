
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                成绩修改
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal" method="post" action="/classinfo/studentScoress/save"
                  data-target="/classinfo/classinfo/edit?pkClassinfo=${(studentScore.pkClassinfo)!}">
                <input type="hidden" name="sc.pkDomain" value="${(studentScore.pkDomain)!}">
                <input type="hidden" name="sc.pkStudentTestPlansScores" value="${(studentScore.pkStudentTestPlansScores)!}">
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">成绩编号</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly name="sc.code" value="${(studentScore.code)!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">学生姓名</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.studentEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkStudent" value="${(studentScore.studentEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">课程</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.sysdicEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkCource" value="${(studentScore.sysdicEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">老师</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.userEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkEmployee" value="${(studentScore.userEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">班级</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" readonly value="${(studentScore.classInfoEntity.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkClassinfo" value="${(studentScore.classInfoEntity.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">考试类型</label>
                        <div class="col-xs-9 col-md-9">
                            <#--<input class="form-control" value="${(studentScore.testTypeEntity.caption)!}" title="" type="text">-->
                            <#--<input class="form-control" name="sc.pkPlans" value="${(studentScore.testTypeEntity.id)!}" title="" type="hidden">-->
                            <select name="sc.pkPlans">
                                <option value="">请选择</option>
                                <#if testPlansType??>
                                    <#list testPlansType as t>
                                        <option  value="${(t.pkSysDictValues)!}" <#if t.pkSysDictValues?? &&studentScore.pkPlans?? && t.pkSysDictValues==studentScore.pkPlans>selected</#if>>${(t.caption)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">考试名称</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control"  value="${(studentScore.caption)!}" title="" type="text">
                            <input class="form-control" name="sc.pkStudentTestPlans" value="${(studentScore.id)!}" title="" type="hidden">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">成绩</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" name="sc.scores" value="${(studentScore.scores)!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">权重</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control" name="sc.weight" value="${(studentScore.weight)!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">年份</label>
                        <div class="col-xs-9 col-md-9">
                            <input class="form-control js-datepickerYYYY" readonly name="sc.yearTime" value="${(studentScore.year?string("yyyy"))!}" title="" type="text">
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="form-group">
                        <label class="control-label col-xs-3 col-md-3">学期</label>
                        <div class="col-xs-9 col-md-9">
                            <#--<input class="form-control" name="sc.term" value="${(studentScore.term)!}" title="" type="text">-->
                            <select name="sc.term" class="form-control">
                                <option value="">请选择</option>
                                <#if terms ??>
                                    <#list terms as t >
                                        <option value="${(t.pkSysDictValues)!}" <#if studentScore.term?? &&t.pkSysDictValues?? && t.pkSysDictValues==studentScore.term >selected</#if>>${(t.caption)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-12">
                        <div class="form-group">
                            <label class="control-label  col-xs-1 col-md-1">备注</label>
                            <div class="col-xs-11 col-md-11">
                                <textarea rows="2" name="sc.memo"  class="form-control" title="">${(studentScore.memo)!}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group ">
                            <label class="control-label  col-xs-3 col-md-3">创建人</label>
                            <div class="col-xs-9 col-md-9 ">
                                <input readonly class="form-control" value="${(studentScore.creator)!}"
                                       type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">创建时间</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control"
                                       value="${(studentScore.creationDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                       type="text">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-md-6">
                        <div class="form-group ">
                            <label class="control-label col-xs-3 col-md-3">修改人</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control" value="${(studentScore.modifier)!}"
                                       type="text">
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12 col-md-6">
                        <div class="form-group">
                            <label class="control-label col-xs-3 col-md-3">修改时间</label>
                            <div class="col-xs-9 col-md-9">
                                <input readonly class="form-control"
                                       value="${(studentScore.lasteditDate?string("yyyy-MM-dd HH:mm:ss"))!}"
                                       type="text">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                <#--<button type="submit" class="btn btn-success btn-sm">确定</button>-->
                    <input class="btn	btn-success btn-sm"  name="dosubmit" value="确定" type="submit">
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
    $('.form-ajax').bootstrapValidator().on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data

        $.post($form.attr('action'), $form.serialize(), function(data) {

            if(data.code==0){
                Notify.success(data.message);
                if($form.data('target')){
                    setTimeout("window.location.href='"+$form.data('target')+"'",3000);
                }
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });

    $('.js-datepickerYYYY').datetimepicker({//年月日
        format: 'yyyy',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0});
</script>