$("#submitbtn").click(function () {
    var checkrole = $("#deptrole").val();
    $("#rolelist").val(checkrole);
    var checkcenter = $("#selcenter").val();
    $("#centerNumber").val(checkcenter);
    var checkdept = $("#deptsellist").val();
    $("#deptlist").val(checkdept);
    $("#fromid").submit();
});
function del() {

    var index = $(".table-color-").index();
    var id = getTableSelectRow("pagingTable",index).pkSysUser;

    if (id == null) {
        Notify.danger("请先选择要删除的数据");
        return;
    }
    var flag = confirm("确认删除吗？");
    if (flag) {
        $.ajax({
            type: "POST",
            url: "/system/sysUser/delete",
            data: {"pkSysUser": id},
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    Notify.success(data.message);
                    setTimeout("location.reload()", 1);
                } else {
                    Notify.danger(data.message)
                }
            },
            error: function () {

            }
        });
    }
}