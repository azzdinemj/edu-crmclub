<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                into class
            </h4>
        </div>
        <div class="modal-body">
            <div class="table-responsive">
                <h5>${(classInfo.caption)!}</h5>
                <form>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Student number</label>
                            <div class="col-xs-8">
                                <input disabled="disabled" name="name" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label col-xs-4">Student name</label>
                            <div class="col-xs-8"><input disabled="disabled" name="name" class="form-control"
                                                         type="text"></div>
                        </div>
                    </div>
                </form>
                <table class="table table-color" cellspacing="0">
                    <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="chAll">
                        </th>

                        <th width="150">Student number</th>
                        <th width="100">Student name</th>

                        <th width="100">project</th>
                        <th width="200">grade</th>



                    </tr>
                    </thead>
                    <tbody>
                    <#list student as stu>
                    <tr>
                        <td>
                            <input name="checkbox" type="checkbox" value="${(stu.pkStudent)!}" class="px">
                        </td>
                        <td>${(stu.code)!}</td>
                        <td>${(stu.caption)!}</td>
                        <td>${(stu.program)!}</td>
                        <td>${(stu.grade)!}</td>

                    </tr>
                    </#list>


                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
            <button type="button" onclick="sub()" class="btn btn-success btn-sm">Determine</button>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

<script type="text/javascript">

    <#--function sub() {-->
        <#--var pkClassinfo = $("input[name='checkbox']:checked").val();-->


        <#--var data = {"pkClassinfo": pkClassinfo, "pkStudent": "${(stu.pkStudent)!}"};-->

        <#--$.post("/classinfo/classinfoStudent/save", data, function (data) {-->

            <#--if (data.code == 0) {-->
                <#--// Notify.success(data.message);-->

                <#--setTimeout("location.reload()", 1);-->

            <#--} else {-->
                <#--Notify.danger(data.message);-->
            <#--}-->
        <#--}, 'json');-->

    <#--}-->


</script>

