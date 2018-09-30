<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                新建评分
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin">

                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-3">学校名称</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(domain.caption)!}" readonly title="" type="text">
                            <input class="form-control" name="cre.pkDomain" value="${(domain.pkDomain)!}" title=""
                                   type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group">
                        <label class="control-label col-lg-3">学生姓名</label>
                        <div class="col-lg-9">
                            <input class="form-control" value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="cre.pkStudent" value="${(student.pkStudent)!}" readonly
                                   title="" type="hidden">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">开始时间</label>
                        <div class="col-lg-9">
                            <input name="cre.startDateTime" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>

                </div>
                <div class="col-sm-6">

                    <div class="form-group row">
                        <label class="control-label col-lg-3">课程类型</label>
                        <div class="col-lg-9">
                            <input name="cre.typeCourse" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>

                    <div class="form-group row">
                        <label class="control-label col-lg-3">评分</label>
                        <div class="col-lg-9">
                            <input name="cre.coursesGrades" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">结束时间</label>
                        <div class="col-lg-9">
                            <input name="cre.endDateTime" class="form-control"  title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>

                <div class="form-group row">
                    <label class="control-label col-sm-2">备注</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="cre.notes" rows="4" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">创建人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(credit.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">创建时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${credit.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">修改人</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(credit.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">修改时间</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${credit.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->


            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-success btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

