
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">Student score</h4>
        </div>
        <form class="form-ajax" method="post" action="/classinfo/classinfo/insertActivityStudent">
            <div class="modal-body">
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">class</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" value="${(activityStudent.className)!}" class="form-control" disabled>
                    </div>
                    <input type="hidden" name="pkClassinfo" value="${(activityStudent.pkClassinfo)!}">
                </div>
                <div class="form-group row">
                    <label class="control-label col-xs-3 col-md-3">Student</label>
                    <div class="col-xs-9 col-md-9">
                        <input type="text" value="${(activityStudent.studentName)!}" class="form-control" disabled>
                    </div>
                    <input type="hidden" name="pkStudent" value="${(activityStudent.pkStudent)!}">
                </div>
            <div class="gradecon  row" id="Addnewskill_119">
                <ul class="rev_pro clearfix">
                    <li>
                        <label class="revtit control-label col-xs-3 col-md-3">score</label>
                        <div class="revinp col-xs-9 col-md-9">
			                    	<span class="level">
				                    	<i class="level_solid" cjmark=""></i>
					                    <i class="level_hollow" cjmark=""></i>
										<i class="level_hollow" cjmark=""></i>
										<i class="level_hollow" cjmark=""></i>
										<i class="level_hollow" cjmark=""></i>
									</span>

                        </div>
                    </li>

                </ul>
            </div>
            <input type="hidden" name="score" id="score" value="">
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Submission</button>
        </div>
        </form>
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
                window.location.reload();
            }else{
                Notify.danger(data.message);
            }
        }, 'json');
    });

    //Point stars
    $(document).on('mouseover','i[cjmark]',function(){
        var num = $(this).index();
        var pmark = $(this).parents('.revinp');
        var mark = pmark.prevAll('input');

        if(mark.prop('checked')) return false;

        var list = $(this).parent().find('i');
        for(var i=0;i<=num;i++){
            list.eq(i).attr('class','level_solid');
        }
        for(var i=num+1,len=list.length-1;i<=len;i++){
            list.eq(i).attr('class','level_hollow');
        }

        $("#score").val(num);
//        $(this).parent().next().html(degree[num+1]);

    })
</script>