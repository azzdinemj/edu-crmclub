<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                New achievements
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-horizontal no-margin look">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-4">Student number</label>
                        <div class="col-lg-8">
                            <input class="form-control" readonly name="code" value="${(student.code)!}" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                    <div class="form-group ">
                        <label class="control-label col-sm-4">Examination project</label>
                        <div class="col-sm-8">
                            <select name="pls.subjectName">
                            <#if sysDictValue?? >
                                <#list sysDictValue as sys>
                                    <option value="${(sys.pkSysDictValues)!}">${(sys.caption)!}</option>
                                </#list>
                            </#if>
                            </select>
                        <#-- <input class="form-control" name="pls.subjectName"  title="" type="text">-->
                        </div><!-- /.col -->
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label col-lg-4">Student name</label>
                        <div class="col-lg-8">
                            <input class="form-control" readonly value="${(student.caption)!}" title="" type="text">
                            <input class="form-control" name="pls.pkStudent" value="${(student.pkStudent)!}" title="" type="hidden">
                        </div><!-- /.col -->
                    </div>


                    <div class="form-group">
                        <label class="control-label col-xs-4">Exam results</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="pls.scores" title="" type="text">
                        </div><!-- /.col -->
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="control-label col-sm-2">Curriculum type</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="cre.typeCourse">
                                <#if discipline??>
                                    <#list discipline as dis>
                                        <option value="${(dis.pkSysDictValues)!}">${(dis.caption)!}</option>
                                    </#list>
                                </#if>
                            </select>
                        </div><!-- /.col -->
                    </div>
                </div>



                <div class="form-group row">
                    <label class="control-label col-sm-2">evaluate</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" name="time.memo" rows="4" va></textarea>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Founder</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(plSco.map.creatorEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Creation time</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${plSco.creationDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->
                </div>
                <div class="form-group row">
                    <label class="control-label col-lg-2">Modifier</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control" value="${(plSco.map.modifierEntity.caption)!}" type="text"/>
                    </div><!-- /.col -->
                    <label class="control-label col-lg-2">Modified</label>
                    <div class="col-lg-4">
                        <input readonly class="form-control"
                               value="${plSco.lasteditDate? string("yyyy-MM-dd HH:mm:ss")!}" type="text"/>
                    </div><!-- /.col -->

            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-success btn-sm">Determine</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">
    $(document).ready(function(){
        $("#s1").change(function(){
            var item = $(this).val();
            $(".div").hide();
            $("#div"+item).show();
        });
    });
</script>