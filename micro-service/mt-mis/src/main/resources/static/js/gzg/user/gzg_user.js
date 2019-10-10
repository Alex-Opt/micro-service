// 自动加载
$(function () {
    initRoleCombotree();
    initUserGbDatagrid();
    initUserGbDatagridParam();
});


// 初始化角色下拉树
function initRoleCombotree() {
    $('#roleId').combotree({
        panelHeight: 250,
        onShowPanel: function () {
            loadRoleCombotree('#roleId', 3, 101);
        },
        loadFilter: responseJsonFilter
    });
}


// 初始化查询条件
function initUserGbDatagridParam() {
    $('#id').textbox('setValue', '');
    $('#userName').textbox('setValue', '');
    $('#mobile').textbox('setValue', '');
    loadDictComboboxSelect('#validStatus', 'ValidStatus');
    $('#createTimeStart').datebox('setValue', '');
    $('#createTimeEnd').datebox('setValue', '');
    $('#roleId').combotree('setValue', '');
    loadUserGbDatagrid();
}

// 初始化表格
function initUserGbDatagrid() {
    $('#userGbDatagrid').datagrid({
        width: 'auto',
        height: 'auto',
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        loadMsg: '正在拼命加载中，请稍后...',
        pagination: true,
        pageSize: 20,
        pageList: [20, 30, 50],
        columns: [[
            {title: '用户编号', field: 'id', align: 'center', width: '10%'},
            {title: '所属角色', field: 'roleNames', align: 'center', width: '15%', formatter: roleNamesFmt},
            {title: '用户姓名', field: 'userName', align: 'center', width: '10%'},
            {title: '所属地区', field: 'areaName', align: 'center', width: '20%', formatter: areaNamesFmt},
            {title: '手机号', field: 'mobile', align: 'center', width: '10%'},
            {title: '创建时间', field: 'createTime', align: 'center', width: '15%'},
            {title: '创建人', field: 'createUserName', align: 'center', width: '10%'},
            {title: '操作', field: 'op', align: 'center', width: '10%', formatter: userGbDatagridFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 角色名称超长截取
function roleNamesFmt(value, row, index) {
    return strLengthFmt(value, 10);
}


// 区域名称超长截取
function areaNamesFmt(value, row, index) {
    return strLengthFmt(value, 20);
}


// 操作按钮
function userGbDatagridFmt(value, row, index) {
    var userGbRole = '<input type="button" value="角色设置" style="cursor:pointer" onclick="openUserGbRoleDialog(\'' + row.id + '\',\'' + row.userName + '\',\'' + row.projectType + '\');"/>';
    var stoppedUser = '<input type="button" value="禁用" style="cursor:pointer" onclick="updateUserGbValidStatus(\'' + row.id + '\',\'0\');"/>';
    var enabledUser = '<input type="button" value="启用" style="cursor:pointer" onclick="updateUserGbValidStatus(\'' + row.id + '\',\'1\');"/>';
    if (0 == row.validStatus) {
        return enabledUser + '&nbsp;&nbsp;' + userGbRole;
    } else {
        return stoppedUser + '&nbsp;&nbsp;' + userGbRole;
    }
}

// 查询
function loadUserGbDatagrid() {
    if (!checkIntervalTime('#createTimeStart', '#createTimeEnd', '创建时间')) {
        return;
    }
    var param = {
        id: $('#id').textbox('getValue'),
        userName: $('#userName').textbox('getValue'),
        mobile: $('#mobile').textbox('getValue'),
        roleId: $('#roleId').combogrid('getValue'),
        validStatus: $('#validStatus').combobox('getValue'),
        createTimeStart: $('#createTimeStart').datebox('getValue'),
        createTimeEnd: $('#createTimeEnd').datebox('getValue')
    }
    $("#userGbDatagrid").datagrid({
        url: '/gzg/user/loadUserDatagrid',
        queryParams: param
    });
}


// 禁用启用
function updateUserGbValidStatus(id, validStatus) {
    var msg = '禁用';
    if (1 == validStatus) {
        msg = '启用';
    }
    $.messager.confirm('提示信息', '确定要' + msg + '该帐号？', function (r) {
        if (r) {
            $.post(
                "/basic/user/updateValidStatus",
                {id: id, validStatus: validStatus},
                function (data) {
                    if (0 == data.code) {
                        $('#userGbDatagrid').datagrid('reload');
                    }
                    alertMsg(data.msg);
                }
            );
        }
    });
}


// 新增
function openUserGbAddDialog() {
    $('#userGbAddDialog').dialog({
        title: '用户新增',
        width: '45%',
        height: '45%',
        closed: false,
        cache: false,
        href: '/gzg/user/userAdd',
        modal: true
    });
}


// 修改
function openUserGbEditDialog() {
    var row = $('#userGbDatagrid').datagrid('getSelected');
    if (null == row) {
        alertMsg("请选择要修改的用户!");
        return;
    }
    if (0 == row.validStatus) {
        alertMsg("已停用的帐号不能修改!")
        return;
    }
    $('#userGbEditDialog').dialog({
        title: '用户修改',
        width: '45%',
        height: '45%',
        closed: false,
        cache: false,
        href: '/gzg/user/userEdit',
        modal: true
    });
}


// 角色
function openUserGbRoleDialog(id, name, projectType) {
    $('#userGbRoleDialog').dialog({
        title: name + '角色设置',
        width: '100%',
        height: '100%',
        closed: false,
        cache: false,
        href: '/basic/user/role/userRole?id=' + id + '&projectType=' + projectType,
        modal: true
    });
}