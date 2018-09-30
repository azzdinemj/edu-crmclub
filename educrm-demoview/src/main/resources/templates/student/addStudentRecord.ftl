<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                新建学生访谈记录
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin look form-ajax" method="post" action="/student/studentInterviewRecord/save"
                  data-target="/student/student/edit?pkStudent=${(student.pkStudent)!}">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">校区名称</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(domain.caption)!}" readonly title="" type="text">
                            <input class="form-control" name="intre.pkDomain" value="${(domain.pkDomain)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">学生姓名</label>
                        <div class="col-lg-9">
                            <input class="form-control"  value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="intre.pkStudent" value="${(student.pkStudent)!}" readonly title="" type="hidden">
                        </div><!-- /.col -->
                    </div>

                </div>
                <div class="col-sm-6">

                    <div class="form-group">
                        <label class="control-label col-lg-3">访谈时间</label>
                        <div class="col-lg-9">
                            <input  name="intre.dateTime" class="form-control js-datepicker" data-bv-notempty data-bv-notempty-message="请录入时间" title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-3">年级</label>
                        <div class="col-lg-9">
                            <select  name="intre.grade">
                                <#if grade ??>
                                    <#list grade as g>
                                        <option value="${g.pkSysDictValues}">${g.caption}</option>
                                    </#list>

                                </#if>
                            </select>
                            <#--<input name="intre.grade"  class="form-control"  title="" type="text">-->
                        </div><!-- /.col -->
                    </div>


                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2">建&emsp;&emsp;议</label>
                    <div class="col-lg-10">
                        <textarea name="intre.advices" rows="3" class="form-control"></textarea>
                    </div><!-- /.col -->
                </div>

                <div class="form-group row">
                    <label class="control-label col-lg-2">访谈内容</label>
                    <div class="col-lg-10">
                        <textarea name="intre.what" data-bv-notempty data-bv-notempty-message="请录入访谈内容太" rows="3" class="form-control"></textarea>
                    </div><!-- /.col -->
                </div>

                <div class="form-group row">
                    <label class="control-label col-sm-2">备&emsp;&emsp;注</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="intre.notes" rows="3" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">创建人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(interRec.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">创建时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${interRec.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">修改人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(interRec.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">修改时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${interRec.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-success btn-sm">确定</button>
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

    $('.js-datepicker').datetimepicker({//年月日
        format: 'yyyy-mm-dd',
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0});
</script>