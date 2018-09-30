
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                公告
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal look" method="post" action="/system/notice/save"
                  data-target="/system/notice/query">
                <#--<input type="hidden" name="pkDomain" value="${(notice.pkDomain)!}">-->
                <#--<input type="hidden" name="pkClassRoom" value="${(notice.pkClassRoom)!}">-->
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">公告编码</label>
                    <div class=" col-xs-4 col-md-4">
                        <input name="pkNotice" readonly  class="form-control" value="${(notice.pkNotice)!}"   type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">发布人</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(notice.map.userEntity.caption)!}"   type="text" />
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">标题</label>
                    <div class=" col-xs-10 col-md-10">
                        <input name="title" class="form-control" value="${(notice.title)!}" type="text" />
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">内容</label>
                    <div class=" col-xs-10 col-md-10">
                        <textarea class="form-control" name="content" rows="4" >${(notice.content)!}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">创建人</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(notice.map.creatorEntity.caption)!}" type="text"/>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">创建时间</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${notice.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">修改人</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(notice.map.modifierEntity.caption)!}" type="text"/>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">修改时间</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${notice.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-newblue btn-sm">确定</button>
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
</script>