// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveUserGbEdit();
    }
};


// 自动加载
$(function () {
    var row = $('#userGbDatagrid').datagrid('getSelected');
    $('#idEdit').val(row.id);
    $('#userNameEdit').val(row.userName);
    $('#mobileEdit').val(row.mobile);
    $('#oldMobileEdit').val(row.mobile);
});


// 校验手机号
function saveUserGbEdit() {
    var oldMobile = $('#oldMobileEdit').val();
    var mobile = $('#mobileEdit').textbox('getValue');
    if (oldMobile != mobile) {
        $.post(
            '/basic/user/checkMobile?projectType=101&mobile=' + mobile,
            function (data) {
                if (0 != data.code) {
                    alertMsg(data.msg);
                } else {
                    updateUserGb();
                }
            }
        );
    } else {
        updateUserGb();
    }
}

// 保存
function updateUserGb() {
    $('#formEdit').form('submit', {
        url: '/basic/user/updateUser',
        success: function (data) {
            data = JSON.parse(data);
            if (0 == data.code) {
                closeUserGbEditDialog();
                loadUserGbDatagrid();
            }
            alertMsg(data.msg);
        }
    });
}

// 取消
function closeUserGbEditDialog() {
    $('#userGbEditDialog').dialog('close');
}