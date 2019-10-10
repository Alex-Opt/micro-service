// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveRoleFunc();
    }
};


// 自动加载
$(function () {
    var row = $('#roleTreegrid').treegrid('getSelected');
    $('#roleId').val(row.id);
    $('#roleFuncTree').tree({
        checkbox: true,
        loadFilter: responseJsonFilter,
        onLoadSuccess: function (node, data) {
            $.post(
                "/mis/role/listRoleFunc",
                {roleId: row.id},
                function (data) {
                    if (data.code == 0) {
                        var result = JSON.parse(data.result);
                        for (var i = 0; i < result.length; i++) {
                            if (4 != result[i].rightLevel) {
                                continue;
                            }
                            var rightId = result[i].rightId;
                            var node = $('#roleFuncTree').tree('find', rightId);
                            if (null != node) {
                                $('#roleFuncTree').tree('check', node.target);
                            }
                        }
                    } else {
                        alertMsg(data.msg);
                    }
                }
            );
        },
        onClick: function (node) {
            $('#roleFuncTree').tree('expand', node.target);
        },
        onDblClick: function (node) {
            $('#roleFuncTree').tree('collapse', node.target);
        },
        onCheck: function (node, checked) {
            if (checked) {
                $('#roleFuncTree').tree('expand', node.target);
            } else {
                $('#roleFuncTree').tree('collapse', node.target);
            }
        }
    });
    loadFuncTree('#roleFuncTree');
})


// 保存
function saveRoleFunc() {
    var param = {
        roleId: $('#roleId').val(),
        nodes: JSON.stringify($('#roleFuncTree').tree('getChecked', ['checked', 'indeterminate']))
    };
    $.post(
        "/mis/role/insertRoleFunc",
        param,
        function (data) {
            if (0 == data.code) {
                closeRoleFunc();
            }
            alertMsg(data.msg);
        }
    );
}


// 取消
function closeRoleFunc() {
    $('#roleRightDialog').dialog('close');
}