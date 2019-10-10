// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveUserMisEdit();
    }
};


// 自动加载
$(function () {
    var row = $('#userMisDatagrid').datagrid('getSelected');
    $('#idEdit').val(row.id);
    $('#loginNameEdit').val(row.loginName.slice(4));
    $('#oldLoginNameEdit').val(row.loginName);
    $('#userNameEdit').val(row.userName);
    $('#mobileEdit').val(row.mobile);
    $('#emailEdit').val(row.email);
});

// 校验帐号
function saveUserMisEdit() {
    var oldLoginName = $('#oldLoginNameEdit').val();
    var loginName = 'yykj' + $('#loginNameEdit').textbox('getValue');
    if (oldLoginName != loginName) {
        $.post(
            '/basic/user/checkLoginName?projectType=1000&loginName=' + loginName,
            function (data) {
                if (0 != data.code) {
                    alertMsg(data.msg);
                } else {
                    updateUserMis();
                }
            }
        );
    } else {
        updateUserMis();
    }
}

// 保存
function updateUserMis() {
    $('#formEdit').form('submit', {
        url: '/basic/user/updateUser',
        success: function (data) {
            data = JSON.parse(data);
            if (0 == data.code) {
                closeUserMisEditDialog();
                loadUserMisDatagrid();
            }
            alertMsg(data.msg);
        }
    });
}

// 取消
function closeUserMisEditDialog() {
    $('#userMisEditDialog').dialog('close');
}