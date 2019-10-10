// 键盘确认保存
document.onkeydown = function (e) {
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (code == 13) {
        saveAdd();
    }
};


// 自动加载
$(function () {
    loadDictComboboxEdit('#projectTypeAdd', 'ProjectType', 1000);
    loadDictComboboxAdd('#funcTypeAdd', 'FuncType');
    // 功能下拉树
    $('#parentIdAdd').combotree({
        panelHeight: 250,
        onShowPanel: function () {
            loadFuncCombotree('#parentIdAdd', $('#funcTypeAdd').combobox('getValue') - 1, $('#projectTypeAdd').combobox('getValue'));
        },
        loadFilter: responseJsonFilter,
        onBeforeSelect: function (node) {
            var funcType = $('#funcTypeAdd').combobox('getValue');
            var parentLevel = node.funcLevel;
            if (1 != (funcType - parentLevel)) {
                alertMsg('请正确选择上级功能');
                return false;
            }
        },
    });
    $('#projectTypeAdd').combobox({
        onSelect: function (data) {
            var id = data.id;
            if (1000 != id) {
                alertMsg('所选项目暂不菜单添加');
                loadDictComboboxEdit('#projectTypeAdd', 'ProjectType', 1000);
            }
        },
        onChange: function (newValue, oldValue) {
            loadDictComboboxAdd('#funcTypeAdd', 'FuncType');
        }
    });
    $('#funcTypeAdd').combobox({
        onSelect: function (data) {
            var id = data.id;
            if (1 == id) {// 模块
                $('#parentIdAdd').combobox('disable');
                $('#codeAdd').textbox('disable');
                $('#urlAdd').textbox('disable');
                $('#iconAdd').textbox('disable');
            } else if (2 == id) {// 导航
                $('#parentIdAdd').combobox('enable');
                $('#codeAdd').textbox('disable');
                $('#iconAdd').textbox('enable');
                $('#urlAdd').textbox('disable');
            } else if (3 == id) {// 菜单
                $('#parentIdAdd').combobox('enable');
                $('#codeAdd').textbox('disable');
                $('#iconAdd').textbox('disable');
                $('#urlAdd').textbox('enable');
            } else {// 按钮
                $('#parentIdAdd').combobox('enable');
                $('#codeAdd').textbox('enable');
                $('#iconAdd').textbox('disable');
                $('#urlAdd').textbox('disable');
            }
        },
        onChange: function (newValue, oldValue) {
            $('#parentIdAdd').combotree('clear');
        }
    });
});


// 取消
function closeAdd() {
    $('#funcAddDialog').dialog('close');
}


// 保存
function saveAdd() {
    $('#formAdd').form('submit', {
        url: '/basic/func/insertFunc',
        success: function (data) {
            data = JSON.parse(data);
            if (0 == data.code) {
                $('#funcTreegrid').treegrid('reload');
                closeAdd();
            }
            alertMsg(data.msg);
        }
    });
}