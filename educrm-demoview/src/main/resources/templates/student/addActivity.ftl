


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title"  id="myModalLabel">
                新建活动
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">班级</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">活动名称</label>
                        <div class="col-lg-9">
                            <input required="" class="form-control" value="" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">费用项目</label>
                        <div class="col-lg-9">
                            <input required="" class="form-control" value="" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>
                <div class="col-sm-6">
                <div class="form-group">
                    <label class="control-label col-lg-3">活动类型</label>
                    <div class="col-lg-9">
                        <input required="" class="form-control" value="" title="" type="text">
                    </div><!-- /.col -->
                </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">费用</label>
                        <div class="col-lg-9">
                            <input required="" class="form-control" value="" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">活动时间</label>
                        <div class="col-lg-9">
                            <input type="text" title=""  name="reservation" id="datas" class="form-control form-input-lg" value="" />
                        </div><!-- /.col -->
                    </div>

                </div>




                    <div class="form-group row">
                        <label class="control-label col-lg-2">备注</label>
                        <div class="col-lg-10">
                            <textarea class="form-control" name="time.memo" rows="4" va>${(activity.memo)!}</textarea>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-2">创建人</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(activity.creator)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-lg-2">创建时间</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${activity.creationDate? string("yyyy-MM-dd HH:mm:ss")!}"   type="text"/>
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-2">修改人</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${(activity.modifier)!}" type="text"/>
                        </div><!-- /.col -->
                        <label class="control-label col-lg-2">修改时间</label>
                        <div class="col-lg-4">
                            <input readonly class="form-control" value="${activity.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}"  type="text"/>
                        </div><!-- /.col -->

            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <button type="submit" class="btn btn-success btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

