// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveCategroyEdit();
    }
};


// 自动加载
$(function () {
    var row = $('#categroyDatagrid').datagrid('getSelected');
    $('#idEdit').val(row.id);
    $('#cabinetNameEdit').val(row.cabinetName);
    $('#oldCabinetNameEdit').val(row.cabinetName);
    $('#cabinetCodeEdit').val(row.cabinetCode);
    loadDictComboboxEdit('#cabinetTypeEdit', 'CabinetType', row.cabinetType);
    $('#goodsCountEdit').val(row.goodsCount);
    $('#aisleNumberEdit').val(row.aisleNumber);
    $('#doorNumberEdit').val(row.doorNumber);
});


// 保存
function saveCategroyEdit() {
    var cabinetName = $('#cabinetNameEdit').textbox('getValue');
    var oldCabinetName = $('#oldCabinetNameEdit').val();
    if (cabinetName != oldCabinetName) {
        $.post(
            '/gzg/categroy/checkName?cabinetName=' + cabinetName,
            function (data) {
                if (0 != data.code) {
                    alertMsg(data.msg);
                } else {
                    updateCategroy();
                }
            }
        );
    } else {
        updateCategroy();
    }
}


// 更新
function updateCategroy() {
    var cabinetType = $('#cabinetTypeEdit').combobox('getValue');
    if (isChines(cabinetType)) {
        $('#cabinetTypeEdit').combobox('setValue', $('#cabinetType').val());
    }
    $('#formEdit').form('submit', {
        url: '/gzg/categroy/updateCategroy',
        success: function (data) {
            data = JSON.parse(data);
            if (0 == data.code) {
                closeCategroyEditDialog();
                loadCategroyDatagrid();
            }
            alertMsg(data.msg);
        }
    });
}


// 取消
function closeCategroyEditDialog() {
    $('#categroyEditDialog').dialog('close');
}