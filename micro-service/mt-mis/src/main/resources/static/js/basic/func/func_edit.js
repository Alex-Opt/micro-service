// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveEdit();
    }
};


// 自动加载
$(function () {
    var node = $('#funcTreegrid').treegrid('getSelected');
    var funcLevel = node.funcLevel;
    console.log(funcLevel);
    $('#idEdit').val(node.id);
    $('#nameEdit').val(node.name);
    $('#codeEdit').val(node.code);
    $('#urlEdit').val(node.url);
    $('#iconEdit').val(node.icon);
    if (1 == funcLevel) {// 一级模块
        $('#urlEdit').attr('disabled', true);
        $('#iconEdit').attr('disabled', true);
    } else if (2 == funcLevel) {// 二级导航
        $('#codeEdit').attr('disabled', true);
        $('#urlEdit').attr('disabled', true);
    } else if (3 == funcLevel) {// 二级菜单
        $('#codeEdit').attr('disabled', true);
        $('#iconEdit').attr('disabled', true);
    } else {// 三级按钮
        $('#iconEdit').attr('disabled', true);
        $('#urlEdit').attr('disabled', true);
    }
});


// 取消
function closeEdit() {
    $('#funcEditDialog').dialog('close');
}


// 保存
function saveEdit() {
    $('#formEdit').form('submit', {
        url: '/basic/func/updateFunc',
        success: function (data) {
            data = JSON.parse(data);
            if (0 == data.code) {
                $('#funcTreegrid').treegrid('update', {
                    id: $('#idEdit').val(),
                    row: {
                        name: $('#nameEdit').val().trim(),
                        icon: $('#iconEdit').val().trim(),
                        url: $('#urlEdit').val().trim(),
                        code: $('#codeEdit').val().trim()
                    }
                });
                closeEdit();
            }
            alertMsg(data.msg);
        }
    });
}