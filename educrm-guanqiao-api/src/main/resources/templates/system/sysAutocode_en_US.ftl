<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                Create coding
            </h4>
        </div>
        <div class="modal-body">
            <form class="look form-ajax form-horizontal" method="POST" action="/system/sysAutocode/save"
                  data-target="/system/sysAutocode/query">

                <input type="hidden" name="sysA.pkDomain" value="${(sysAutocode.pkDomain)!}">
                <input type="hidden" name="sysA.pkSysAutocode" value="${(sysAutocode.pkSysAutocode)!}">
                <div class="tab-content">
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">Code name</label>
                            <div class="col-xs-4 col-md-4">
                                <input  name="sysA.caption" value="${(sysAutocode.caption)!}" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the name"  title="" type="text">
                            </div><!-- /.col -->
                            <label class="control-label col-xs-2 col-md-2">prefix</label>
                            <div class="col-xs-4 col-md-4">
                                <input  name="sysA.prefix" value="${(sysAutocode.prefix)!}" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the prefix"  title="" type="text">
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">Coded digits</label>
                            <div class="col-xs-4 col-md-4">
                                <input  name="sysA.zeroWidth" value="${(sysAutocode.zeroWidth)!}" class="form-control" data-bv-notempty data-bv-notempty-message="Please enter the number of digits"  title="" type="number">
                            </div><!-- /.col -->
                            <label class="control-label col-xs-2 col-md-2">Coding type</label>
                            <div class="col-xs-4 col-md-4">
                                <select class="form-control" name="sysA.date">
                                    <option value="yyyy" <#if sysAutocode.date?? && sysAutocode.date == "yyyy">selected="selected"</#if>>年</option>
                                    <option value="yyyyMM" <#if sysAutocode.date?? && sysAutocode.date == "yyyyMM">selected="selected"</#if>>Years</option>
                                    <option value="yyyyMMdd" <#if sysAutocode.date?? && sysAutocode.date == "yyyyMMdd">selected="selected"</#if>>Specific date</option>
                                </select>
                            </div><!-- /.col -->
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">Segmenting line</label>
                            <div class="col-xs-4 col-md-4">
                                <select class="form-control" name="sysA.isline">
                                    <option value="1" <#if sysAutocode.isline?? && sysAutocode.isline == 1>selected="selected"</#if>>Enable</option>
                                    <option value="0" <#if sysAutocode.isline?? && sysAutocode.isline == 0>selected="selected"</#if>>Disable</option>
                                </select>
                            </div><!-- /.col -->
                            <label class="control-label col-xs-2 col-md-2">sort</label>
                            <div class="col-xs-4 col-md-4">
                                <input  name="sysA.sort" class="form-control" value="${(sysAutocode.sort)!}" title="" type="number">
                            </div><!-- /.col -->
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">Remarks</label>
                            <div class="col-xs-10 col-md-10">
                                <textarea  rows="2" name="sysA.memo" class="form-control"  title="">${(sysAutocode.memo)!}</textarea>
                            </div><!-- /.col -->
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-md-2">Founder</label>
                            <div class="col-xs-4 col-md-4">
                                <input name="sysA.creator" value="${(sysAutocode.map.creatorEntity.caption)!}" class="form-control" data-bv-notempty data-bv-notempty-message="" title="" type="text">
                            </div><!-- /.col -->
                            <label class="control-label col-xs-2 col-md-2">Creation time</label>
                            <div class="col-xs-4 col-md-4">
                                <input name="sysA.createDateStr" value="${(sysAutocode.creationDate? string("yyyy-MM-dd HH:mm:ss"))!}" class="form-control" data-bv-notempty data-bv-notempty-message="" title="" type="text">
                            </div><!-- /.col -->
                        </div>


                        <div class="form-group">
                            <label class="control-label  col-xs-2 col-md-2">Modifier</label>
                            <div class="col-xs-4 col-md-4">
                                <input name="sysA.modifier" value="${(sysAutocode.map.modifierEntity.caption)!}" class="form-control" data-bv-notempty data-bv-notempty-message="" title="" type="text">
                            </div><!-- /.col -->
                            <label class="control-label col-xs-2 col-md-2">Modified</label>
                            <div class="col-xs-4 col-md-4">
                                <input name="sysA.lastEditDateStr" value="${(sysAutocode.lasteditDate? string("yyyy-MM-dd HH:mm:ss"))!}" class="form-control" data-bv-notempty data-bv-notempty-message="" title="" type="text">
                            </div><!-- /.col -->
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    <#--<button type="submit" class="btn btn-success btn-sm">Determine</button>-->
                        <input class="btn	btn-newblue btn-sm"  name="dosubmit" value="Preservation" type="submit">
                    </div>
            </form>
                </div>

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
</script>





