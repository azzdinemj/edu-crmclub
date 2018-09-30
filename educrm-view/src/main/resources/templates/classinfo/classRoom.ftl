
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                教室维护
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax form-horizontal look" method="post" action="/classinfo/classRoom/save"
                  data-target="/classinfo/classRoom/query">
                <input type="hidden" name="room.pkDomain" value="${(classRoom.pkDomain)!}">
                <input type="hidden"name="room.pkClassRoom" value="${(classRoom.pkClassRoom)!}">
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">教室编码</label>
                    <div class=" col-xs-4 col-md-4">
                        <input name="room.code"  class="form-control" value="${(classRoom.code)!}"   type="text"  data-bv-notempty data-bv-notempty-message="请录入编码..."/>
                    </div><!-- /.col -->
                    <label class="control-label col-xs-2 col-md-2">教室名称</label>
                    <div class=" col-xs-4 col-md-4">
                        <input name="room.caption"  class="form-control" value="${(classRoom.caption)!}"   type="text" data-bv-notempty data-bv-notempty-message="请录入名称..."/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">容纳人数</label>
                    <div class=" col-xs-4 col-md-4">
                        <input name="room.num"  class="form-control" value="${(classRoom.num)!}"   type="text"/>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">启用状态</label>
                    <div class=" col-xs-4 col-md-4" >
                        <span class="rdio rdio-warning">
                                   <input name="room.isvalid" value="1" <#if classRoom.isvalid??&& classRoom.isvalid ==1 >checked="checked"</#if> id="radio1" type="radio">
                                   <label for="radio1">是</label>
                               </span>
                        <span class="rdio rdio-warnings">
                                   <input name="room.isvalid" value="0" id="radio2" <#if classRoom.isvalid??&& classRoom.isvalid ==0 >checked="checked"</#if> type="radio">
                                   <label for="radio2">否</label>
                               </span>
                    </div><!-- /.col -->
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">备注</label>
                    <div class=" col-xs-10 col-md-10">
                        <textarea class="form-control" name="room.memo" rows="1" >${(classRoom.memo)!}</textarea>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">创建人</label>
                    <div class="col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classRoom.map.creatorEntity.caption)!}" type="text"/>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">创建时间</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classRoom.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">修改人</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${(classRoom.map.modifierEntity.caption)!}" type="text"/>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">修改时间</label>
                    <div class=" col-xs-4 col-md-4">
                        <input readonly class="form-control" value="${classRoom.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
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