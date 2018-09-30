<div id="studentModel" class="modal-dialog lg-modal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close"
                    data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                家长列表
            </h4>
        </div>
        <div class="modal-body">
            <div class="">
                <form>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">家长姓名</label>
                            <div class="col-xs-8">
                                <input id="caption" name="caption" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label col-xs-4">学生姓名</label>
                            <div class="col-xs-8">
                                <input id="studentName" name="studentName" class="form-control" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                    <div class="form-group">
                        <a class="btn btn-newblue btn-sm search" onclick="query()">搜索</a>
                    </div>
                    </div>
                </form>
                <table id="pagingTable" class="table table-color" cellspacing="0">
                    <thead>

                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div><!-- table-responsive -->
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">关闭</button>
            <button type="button" onclick="selectStudent();" class="btn btn-newblue btn-sm">确定</button>
        </div>
    </div><!-- /.modal-content -->

</div><!-- /.modal -->

<script type="text/javascript">

    $(document).ready(function() {
        query();
    });

    function renderPk(data, type, row){
        return '<input name="radio1" type="radio" value="'+data+'" caption="'+row.caption+'" class="px">';
    }

    function query() {
        var data  = { "studentName": $("#studentName").val(),"caption":$("#caption").val()};

        init(GetTableColumn("linkmanModel"), "/student/linkman/queryByPaging", data,"pagingTable");
    }


    function selectStudent() {
//        var parentsex = 0;
        var tdHtml = '';
        var pkId = $("input[name='radio1']:checked").val();
//        var caption = $("input[name='radio1']:checked").attr("caption");
        var parentcaption = $("input[name='radio1']:checked").parents('tr').find('td').eq(1).text();
        var parentsexCha = $("input[name='radio1']:checked").parents('tr').find('td').eq(2).text();
        var parentphone = $("input[name='radio1']:checked").parents('tr').find('td').eq(3).text();
        var childnum = parent.parentNumber;
        if (parentsexCha=='男'){
//            parentsex = 1;
            tdHtml = '<td><select name="man[' + childnum + '].sex"><option value="">请选择</option><option selected value="1" >男</option><option value="2" >女</option></select></td>';
        }
        else if (parentsexCha=='女'){
//            parentsex=2;
            tdHtml = '<td><select name="man[' + childnum + '].sex"><option value="">请选择</option><option value="1" >男</option><option value="2" selected >女</option></select></td>';
        }else {
            tdHtml = '<td><select name="man[' + childnum + '].sex"><option value="">请选择</option><option value="1" >男</option><option value="2" >女</option></select></td>';
        }

        var childtrHtml = '<tr align="center"><td>' +
                '<input name="man[' + childnum + '].caption" type="text" class="form-control bor0 bg0" value="'+parentcaption+'">' +
                '<input name="man[' + childnum + '].pkLinkman" type="hidden" class="form-control bor0 bg0" value="'+pkId+'">' +
                '</td>' +
                '<td><input name="man[' + childnum + '].relationship" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
               tdHtml +
                '<td><select name="man[' + childnum + '].iskey"><option value="">请选择</option><option value="0" >否</option><option value="1" >是</option></select></td>' +
                '<td><input name="man[' + childnum + '].phone" type="text"  class="form-control bor0 bg0" value="'+parentphone+'"></td>' +
                '<td><input name="man[' + childnum + '].occupation" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + childnum + '].duty" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td><input name="man[' + childnum + '].workUnit" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td>' +
                '<input name="man[' + childnum + '].degreeCompleted" type="text"  class="form-control bor0 bg0" placeholder="">' +
                '</td>' +

                '<td><input name="man[' + childnum + '].address" type="text"  class="form-control bor0 bg0" placeholder=""></td>' +
                '<td ><i  onclick="deleteRow(tab,this.parentElement.parentElement.rowIndex)" class="fa fa-remove add_icon"></i>' +
                '</td></tr>';
        parent.addTr('tab',-1,childtrHtml);
        parent.parentNumber++;
        $("#modal").modal("hide");
    }


</script>

