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
    var row = $('#roleTreegrid').treegrid('getSelected');
    $('#idEdit').val(row.id);
    $('#nameEdit').val(row.name);
    // 所属项目下拉框
    loadDictComboboxEdit('#projectTypeEdit', 'ProjectType', row.projectType);
    $('#projectTypeEdit').combobox('disable');
    // 角色类型下拉框
    loadDictComboboxEdit('#roleTypeEdit', 'RoleType', row.roleType);
    $('#roleTypeEdit').combobox('disable');
    // 角色等级下拉框
    if (101 == row.projectType && 1 == row.roleType) {
        loadDictComboboxAdd('#roleLevelEdit', 'RoleLevelGzgBD');
    } else {
        loadDictComboboxAdd('#roleLevelEdit', 'RoleLevel');
    }
    // 角色下拉树
    $('#parentIdEdit').combotree({
        panelHeight: 250,
        onShowPanel: function () {
            loadRoleCombotree('#parentIdEdit', $('#roleLevelEdit').combobox('getValue') - 1, $('#projectTypeEdit').combobox('getValue'));
        },
        loadFilter: responseJsonFilter,
        onBeforeSelect: function (node) {
            var roleLevel = $('#roleLevelEdit').combobox('getValue');
            var parentId = node.id;
            var id = $('#idEdit').val();
            if (parentId == id) {
                alertMsg('上级角色不能选择自己');
                return false;
            }
            var parentLevel = node.roleLevel;
            if (1 != (roleLevel - parentLevel)) {
                alertMsg('请正确选择上级角色');
                return false;
            }
        }
    });
    // 角色等级下拉框选择事件
    $('#roleLevelEdit').combobox({
        onSelect: function (data) {
            var id = data.id;
            if (1 == id) {// 一级
                $('#parentIdEdit').combobox('disable');
            } else {// 二级
                $('#parentIdEdit').combobox('enable');
            }
        },
        onChange: function (newValue, oldValue) {
            $('#parentIdEdit').combotree('clear');
        }
    });
    if (1 != row.roleLevel) {
        loadRoleCombotree('#parentIdEdit', row.roleLevel);
        $('#parentIdEdit').combotree({
            loadFilter: responseJsonFilter,
            onLoadSuccess: function () {
                $('#parentIdEdit').combotree('setValue', row.parentId);
            },
        })
    }
});

// 保存
function saveEdit() {
    $('#formEdit').form('submit', {
        url: '/basic/role/updateRole',
        success: function (data) {
            data = JSON.parse(data);
            if (0 == data.code) {
                $('#roleEditDialog').dialog('close');
                $('#roleTreegrid').treegrid('reload');
            }
            alertMsg(data.msg);
        }
    });
}

// 取消
function closeEdit() {
    $('#roleEditDialog').dialog('close');
}