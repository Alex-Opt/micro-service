// 自动加载
$(function () {
    initRoleTreegrid();
    initRoleTreegridParam();
    resetRoleTreegrid();
});


// 查询条件初始化
function initRoleTreegridParam() {
    $('#id').textbox('setValue', '');
    $('#name').textbox('setValue', '');
    $('#createTimeStart').datebox('setValue', '');
    $('#createTimeEnd').datebox('setValue', '');
    loadDictComboboxSelect('#validStatus', 'ValidStatus');
}


// 表格树初始化
function initRoleTreegrid() {
    $('#roleTreegrid').treegrid({
        fit: true,
        fitColumns: true,
        rownumbers: true,
        animate: true,
        idField: 'id',
        treeField: 'name',
        loadMsg: '数据加载中,请稍后...',
        columns: [[
            {title: '父角色ID', field: 'parentId', hidden: true},
            {title: '角色名称', field: 'name', width: '20%'},
            {title: '角色编号', field: 'id', width: '10%', align: 'center', formatter: idFmt},
            {title: '角色类型', field: 'roleTypeName', width: '10%', align: 'center'},
            {title: '包含用户数', field: 'countUser', width: '10%', align: 'center'},
            {title: '状态', field: 'validStatusName', width: '10%', align: 'center', formatter: validStatusFmt},
            {title: '创建人', field: 'createUserName', width: '10%', align: 'center'},
            {title: '创建时间', field: 'createTime', width: '15%', align: 'center'},
            {title: '操作', field: 'op', width: '15%', align: 'center', formatter: operationFmt}
        ]],
        loadFilter: responseJsonFilter,
        onClickRow: function (row) {
            $('#roleTreegrid').treegrid('expand', row.id);
        },
        onDblClickRow: function (row) {
            $('#roleTreegrid').treegrid('collapse', row.id);
        }
    });
}


// id
function idFmt(value, row, index) {
    if (checkParentId(row.parentId)) {
        return '';
    } else {
        return value;
    }
}


// 操作按钮
function operationFmt(value, row, index) {
    if (checkParentId(row.parentId)) {
        return '';
    }
    var stoppedRole = '<input type="button" value="禁用" style="cursor:pointer" onclick="updateValidStatus(\'' + row.id + '\',\'0\');"/>';
    var enabledRole = '<input type="button" value="启用" style="cursor:pointer" onclick="updateValidStatus(\'' + row.id + '\',\'1\');"/>';
    var roleUser = '<input type="button" value="包含用户" style="cursor:pointer" onclick="roleUser(\'' + row.name + '\',\'' + row.countUser + '\');"/>';
    var roleRight = '<input type="button" value="权限设置" style="cursor:pointer" onclick="roleRight(\'' + row.name + '\',\'' + row.projectType + '\');"/>';
    if (0 == row.validStatus) {
        return roleUser + '&nbsp;&nbsp;' + enabledRole + '&nbsp;&nbsp;' + roleRight;
    } else {
        return roleUser + '&nbsp;&nbsp;' + stoppedRole + '&nbsp;&nbsp;' + roleRight;
    }
}


// 查询
function loadRoleTreegrid() {
    if (!checkIntervalTime('#createTimeStart', '#createTimeEnd', '创建时间')) {
        return;
    }
    var param = {
        type: 'load',
        searchId: $('#id').textbox('getValue'),
        name: $('#name').textbox('getValue'),
        createTimeStart: $('#createTimeStart').datebox('getValue'),
        createTimeEnd: $('#createTimeEnd').datebox('getValue'),
        validStatus: $('#validStatus').combobox('getValue')
    }
    $("#roleTreegrid").treegrid({
        url: '/basic/role/loadRoleTreegrid',
        queryParams: param
    });
}


// 重置
function resetRoleTreegrid() {
    initRoleTreegridParam();
    $("#roleTreegrid").treegrid({
        url: '/basic/role/loadRoleTreegrid',
        queryParams: {id: null}
    });
}


// 禁用/启用
function updateValidStatus(id, validStatus) {
    var row = $('#roleTreegrid').treegrid('find', id);
    var msg = '禁用';
    if (1 == validStatus) {
        msg = '启用';
    }
    $.messager.confirm('提示信息', '确定要' + msg + '该角色？', function (r) {
        if (r) {
            $.post(
                "/basic/role/updateValidStatus",
                {id: id, parentId: row.parentId, roleLevel: row.roleLevel, validStatus: validStatus},
                function (data) {
                    if (0 == data.code) {
                        $('#roleTreegrid').treegrid('reload');
                    }
                    alertMsg(data.msg);
                }
            );
        }
    });
}


// 权限设置
function roleRight(name, projectType) {
    var href = '/mis/role/right';
    if (101 == projectType) {
        href = '/gzg/role/right';
    }
    $('#roleRightDialog').dialog({
        title: name + '角色权限设置',
        width: '100%',
        height: '100%',
        closed: false,
        cache: false,
        href: href,
        modal: true
    });
}


// 包含用户
function roleUser(name, countUser) {
    if (0 == countUser) {
        alertMsg('该角色暂无包含用户');
    } else {
        $('#roleUserDialog').dialog({
            title: name + '角色包含用户',
            width: '60%',
            height: '60%',
            closed: false,
            cache: false,
            href: '/basic/role/user/roleUser',
            modal: true
        });
    }
}


// 新增
function openRoleAddDialog() {
    $('#roleAddDialog').dialog({
        title: '角色新增',
        width: '45%',
        height: '45%',
        closed: false,
        cache: false,
        href: '/basic/role/roleAdd',
        modal: true
    });
}


// 修改
function openRoleEditDialog() {
    var row = $('#roleTreegrid').datagrid('getSelected');
    if (checkParentId(row.parentId)) {
        alertMsg('请正确选择要修改的角色');
        return;
    }
    if (null == row) {
        alertMsg("请选择要修改的角色!");
        return;
    }
    if (0 == row.validStatus) {
        alertMsg("该角色已禁用,不可修改!");
        return;
    }
    $('#roleEditDialog').dialog({
        title: '角色修改',
        width: '45%',
        height: '45%',
        closed: false,
        cache: false,
        href: '/basic/role/roleEdit',
        modal: true
    });
}


// 检查parentId是否为空
function checkParentId(parentId) {
    if ('' == parentId) {
        return true;
    } else {
        return false;
    }
}