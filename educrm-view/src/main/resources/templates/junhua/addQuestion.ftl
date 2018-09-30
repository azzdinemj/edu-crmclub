<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                新增题目
            </h4>
        </div>
        <div class="modal-body">
            <form class="form-ajax" method="post" action="/junhua/jhExamination/questionSave"
                  data-target="">
                <div class="form-group">
                    <label class="control-label col-xs-2 col-md-2">题干</label>
                    <div class="col-xs-10 col-md-10">
                        <textarea rows=＂3＂ cols=＂20＂ class="form-control" id="caption"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <input type="radio" name="selectType" value="0">单选&nbsp&nbsp
                    <input type="radio" name="selectType" value="1">多选&nbsp&nbsp
                    <input type="checkbox" id="thorw">是否存在其他
                </div>
                <div id="select">
                    <label>选择</label>
                    <div class="form-group">
                        <label class="col-md-1">A</label>
                        <div class="col-md-11"><input class="form-control" type="text" title="" id="num0"></div>
                    </div>
                </div>
                <div>
                    <button type="button" id="addSelect">添加一个选项</button>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="jhExaminationCaption" value="${(jhExamination.caption)!}">
                    <input type="hidden" id="datas" value="${(jhExamination.datas)!}">
                    <input type="hidden" id="section" value="${(jhExamination.section)!}">
                    <input type="hidden" id="objects" value="${(jhExamination.object)!}">
                    <input type="hidden" id="pkExamination" value="${(jhExamination.pkExamination)!}">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-newblue btn-sm">确定</button>
                </div>
            </form>
        </div>

    </div><!-- /.modal-content -->
</div><!-- /.modal -->


<script type="text/javascript">
    $(function(){
        var num = 1;
        $('.form-ajax').bootstrapValidator().on('success.form.bv', function (e) {
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data

            var requestData = {};
            requestData.types = $("input[name='selectType']:checked").val();
            if($("input[type='checkbox']").attr('checked') == true){
                requestData.other = 1;
            }else{
                requestData.other = 0;
            }
            requestData.caption  = $("#caption").val();
            requestData.pkExamination  = $("#pkExamination").val();
            var list = new Array();
            var jhOption;
            for(var i=0;i<num;i++){
                jhOption = new Object();
                jhOption.mark = getChar(i);
                jhOption.caption =  $("#num"+i).val();
                list.push(jhOption);
            }


//            问卷基础信息
            var jhExaminationCaption = $("#jhExaminationCaption").val();
            var datas = $("#datas").val();
            var section = $("#section").val();
            var objects = $("#objects").val();
            var pkExamination = $("#pkExamination").val();
            var url = '/junhua/jhExamination/create?caption='+jhExaminationCaption+'&datas='+datas+'&section='+section+"&object="+objects;
            var editUrl = '/junhua/jhExamination/edit?caption='+jhExaminationCaption+'&datas='+datas+'&section='+section+"&object="+objects+"&pkExamination="+pkExamination;

            $.post($form.attr('action'),
            {requestData:JSON.stringify(requestData),jhOptionList:JSON.stringify(list)} ,
                function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    if(${(quesionAddType)!} == 0){
                        setTimeout("window.location.href='"+ url +"'", 3000);
                    }else{
                        setTimeout("window.location.href='"+ editUrl +"'", 3000);
                    }
                } else {
                    Notify.danger(data.message);
                }
            }, 'json');
        });

        $("#addSelect").on("click",function(){
            var html = '<div class="form-group">' +
                    '<label class="col-md-1">'+ getChar(num) +'</label>' +
                    '<div class="col-md-11"><input class="form-control" type="text" title="" id="num'+ num +'"></div>' +
                    '</div>';
            $("#select").append(html);
            num += 1;
        });

    });

    function getChar(i){
        return String.fromCharCode(65 + i);
    }
</script>