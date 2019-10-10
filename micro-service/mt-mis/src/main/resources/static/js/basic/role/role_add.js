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
    // 角色下拉树
    $('#parentIdAdd').combotree({
        panelHeight: 250,
        onShowPanel: function () {
            loadRoleCombotree('#parentIdAdd', $('#roleLevelAdd').combobox('getValue') - 1, $('#projectTypeAdd').combobox('getValue'));
        },
        loadFilter: responseJsonFilter,
        onBeforeSelect: function (node) {
            var roleLevel = $('#roleLevelAdd').combobox('getValue');
            var parentLevel = node.roleLevel;
            if (1 != (roleLevel - parentLevel)) {
                alertMsg('请正确选择上级功能');
                return false;
            }
        }
    });
    // 所属项目下拉框选择事件
    loadDictComboboxEdit('#projectTypeAdd', 'ProjectType', 1000);
    loadDictComboboxEdit('#roleTypeAdd', 'RoleType', 2);
    $('#projectTypeAdd').combobox({
        onSelect: function (data) {
            var id = data.id;
            if (1000 == id) {// 运营系统
                loadDictComboboxEdit('#roleTypeAdd', 'RoleType', 2);
                $('#roleTypeAdd').combobox('disable');
            } else if (101 == id) {
                $('#roleTypeAdd').combobox('enable');
            } else {
                alertMsg('所选项目暂不支持角色添加');
                loadDictComboboxEdit('#projectTypeAdd', 'ProjectType', 1000);
            }
        }
    });
    // 角色类型下拉框选择事件
    $('#roleTypeAdd').combobox({
        onSelect: function (data) {
            var id = data.id;
            if (1 == id) {// 格子柜B端BD角色
                loadDictComboboxAdd('#roleLevelAdd', 'RoleLevelGzgBD');
            } else {
                loadDictComboboxAdd('#roleLevelAdd', 'RoleLevel');
            }
        }
    });
    // 角色等级下拉框选择事件
    $('#roleLevelAdd').combobox({
        onSelect: function (data) {
            var id = data.id;
            if (1 == id) {// 一级
                $('#parentIdAdd').combobox('disable');
            } else {
                $('#parentIdAdd').combobox('enable');
            }
        },
        onChange: function (newValue, oldValue) {
            $('#parentIdAdd').combotree('clear');
        }
    });
});

// 保存
function saveAdd() {
    $('#formAdd').form('submit', {
        url: '/basic/role/insertRole',
        success: function (data) {
            data = JSON.parse(data);
            if (0 == data.code) {
                $('#roleAddDialog').dialog('close');
                $('#roleTreegrid').treegrid('reload');
            }
            alertMsg(data.msg);
        }
    });
}

// 取消
function closeAdd() {
    $('#roleAddDialog').dialog('close');
}