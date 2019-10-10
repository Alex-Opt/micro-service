// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveUserMisAdd();
    }
};


// 保存
function saveUserMisAdd() {
    var loginName = $('#loginNameAdd').textbox('getValue');
    $.post(
        '/basic/user/checkLoginName?projectType=1000&loginName=yykj' + loginName,
        function (data) {
            if (0 != data.code) {
                alertMsg(data.msg);
            } else {
                $('#formAdd').form('submit', {
                    url: '/basic/user/insertUser',
                    success: function (data) {
                        data = JSON.parse(data);
                        if (0 == data.code) {
                            closeUserMisAddDialog();
                            loadUserMisDatagrid();
                        }
                        alertMsg(data.msg);
                    }
                });
            }
        }
    );
}

// 取消
function closeUserMisAddDialog() {
    $('#userMisAddDialog').dialog('close');
}