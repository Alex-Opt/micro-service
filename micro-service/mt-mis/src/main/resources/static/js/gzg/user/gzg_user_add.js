// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveUserGbAdd();
    }
};


// 保存
function saveUserGbAdd() {
    var mobile = $('#mobileAdd').textbox('getValue');
    $.post(
        '/basic/user/checkMobile?projectType=101&mobile=' + mobile,
        function (data) {
            if (0 != data.code) {
                alertMsg(data.msg);
            } else {
                $('#formAdd').form('submit', {
                    url: '/basic/user/insertUser',
                    success: function (data) {
                        data = JSON.parse(data);
                        if (0 == data.code) {
                            closeUserGbAddDialog();
                            loadUserGbDatagrid();
                        }
                        alertMsg(data.msg);
                    }
                });
            }
        }
    );
}

// 取消
function closeUserGbAddDialog() {
    $('#userGbAddDialog').dialog('close');
}