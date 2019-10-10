// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveCategroyAdd();
    }
};


// 自动加载
$(function () {
    loadDictComboboxAdd('#cabinetTypeAdd', 'CabinetType');
});


// 保存
function saveCategroyAdd() {
    var cabinetName = $('#cabinetNameAdd').textbox('getValue');
    $.post(
        '/gzg/categroy/checkName?cabinetName=' + cabinetName,
        function (data) {
            if (0 != data.code) {
                alertMsg(data.msg);
            } else {
                $('#formAdd').form('submit', {
                    url: '/gzg/categroy/insertCategroy',
                    success: function (data) {
                        data = JSON.parse(data);
                        if (0 == data.code) {
                            closeCategroyAddDialog();
                            loadCategroyDatagrid();
                        }
                        alertMsg(data.msg);
                    }
                });
            }
        }
    );
}


// 取消
function closeCategroyAddDialog() {
    $('#categroyAddDialog').dialog('close');
}