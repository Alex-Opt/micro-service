// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveRoleArea();
    }
};


// 自动加载
$(function () {
    var row = $('#roleTreegrid').treegrid('getSelected');
    $('#roleId').val(row.id);
    $('#roleAreaTree').tree({
        checkbox: true,
        loadFilter: responseJsonFilter,
        onLoadSuccess: function (node, data) {
            $.post(
                "/gzg/role/listRoleArea",
                {roleId: row.id},
                function (data) {
                    if (data.code == 0) {
                        var result = JSON.parse(data.result);
                        for (var i = 0; i < result.length; i++) {
                            if (2 != result[i].rightLevel) {
                                continue;
                            }
                            var rightId = result[i].rightId;
                            var node = $('#roleAreaTree').tree('find', rightId);
                            if (null != node) {
                                $('#roleAreaTree').tree('check', node.target);
                            }
                        }
                    } else {
                        alertMsg(data.msg);
                    }
                }
            );
        },
        onClick: function (node) {
            $('#roleAreaTree').tree('expand', node.target);
        },
        onDblClick: function (node) {
            $('#roleAreaTree').tree('collapse', node.target);
        },
        onCheck: function (node, checked) {
            if (checked) {
                $('#roleAreaTree').tree('expand', node.target);
            } else {
                $('#roleAreaTree').tree('collapse', node.target);
            }
        }
    });
    loadAreaTree('#roleAreaTree', 2);
})


// 保存
function saveRoleArea() {
    var param = {
        roleId: $('#roleId').val(),
        nodes: JSON.stringify($('#roleAreaTree').tree('getChecked', ['checked', 'indeterminate']))
    };
    $.post(
        "/gzg/role/insertRoleArea",
        param,
        function (data) {
            if (0 == data.code) {
                closeRoleArea();
            }
            alertMsg(data.msg);
        }
    );
}


// 取消
function closeRoleArea() {
    $('#roleRightDialog').dialog('close');
}