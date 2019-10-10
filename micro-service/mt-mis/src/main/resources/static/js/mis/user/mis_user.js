// 自动加载
$(function () {
    initRoleCombotree();
    initUserMisDatagrid();
    initUserMisDatagridParam();
});


// 初始化角色下拉树
function initRoleCombotree() {
    $('#roleId').combotree({
        panelHeight: 250,
        onShowPanel: function () {
            loadRoleCombotree('#roleId', 3, 1000);
        },
        loadFilter: responseJsonFilter
    });
}


// 初始化查询条件
function initUserMisDatagridParam() {
    $('#id').textbox('setValue', '');
    $('#userName').textbox('setValue', '');
    $('#loginName').textbox('setValue', '');
    $('#mobile').textbox('setValue', '');
    loadDictComboboxSelect('#validStatus', 'ValidStatus');
    $('#createTimeStart').datebox('setValue', '');
    $('#createTimeEnd').datebox('setValue', '');
    $('#lastLoginTimeStart').datebox('setValue', '');
    $('#lastLoginTimeEnd').datebox('setValue', '');
    $('#roleId').combotree('setValue', '');
    loadUserMisDatagrid();
}


// 初始化表格
function initUserMisDatagrid() {
    $('#userMisDatagrid').datagrid({
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
            {title: '员工编号', field: 'id', align: 'center', width: '10%'},
            {title: '所属角色', field: 'roleNames', align: 'center', width: '20%', formatter: roleNamesFmt},
            {title: '用户姓名', field: 'userName', align: 'center', width: '10%'},
            {title: '登录名', field: 'loginName', align: 'center', width: '10%'},
            {title: '手机号', field: 'mobile', align: 'center', width: '10%'},
            {title: '创建时间', field: 'createTime', align: 'center', width: '10%'},
            {title: '最后登录时间', field: 'lastLoginTime', align: 'center', width: '10%'},
            {title: '操作', field: 'op', align: 'center', width: '20%', formatter: userMisDatagridFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 超长截取
function roleNamesFmt(value, row, index) {
    return strLengthFmt(value, 10);
}


// 操作按钮
function userMisDatagridFmt(value, row, index) {
    var userMisInfo = '<input type="button" value="查看" style="cursor:pointer" onclick="openUserMisInfoDialog();"/>';
    var userMisRole = '<input type="button" value="角色设置" style="cursor:pointer" onclick="openUserMisRoleDialog(\'' + row.id + '\',\'' + row.userName + '\',\'' + row.projectType + '\');"/>';
    var stoppedUserMis = '<input type="button" value="禁用" style="cursor:pointer" onclick="updateUserMisValidStatus(\'' + row.id + '\',\'0\');"/>';
    var enabledUserMis = '<input type="button" value="启用" style="cursor:pointer" onclick="updateUserMisValidStatus(\'' + row.id + '\',\'1\');"/>';
    var resetUserMisPassword = '<input type="button" value="密码重置" style="cursor:pointer" onclick="resetUserMisPassword(\'' + row.id + '\');"/>';
    if (0 == row.validStatus) {
        return userMisInfo + '&nbsp;&nbsp;' + enabledUserMis + '&nbsp;&nbsp;' + resetUserMisPassword + '&nbsp;&nbsp;' + userMisRole;
    } else {
        return userMisInfo + '&nbsp;&nbsp;' + stoppedUserMis + '&nbsp;&nbsp;' + resetUserMisPassword + '&nbsp;&nbsp;' + userMisRole;
    }
}


// 查询
function loadUserMisDatagrid() {
    if (!checkIntervalTime('#createTimeStart', '#createTimeEnd', '创建日期')) {
        return;
    }
    if (!checkIntervalTime('#lastLoginTimeStart', '#lastLoginTimeEnd', '最后登录日期')) {
        return;
    }
    var param = {
        id: $('#id').textbox('getValue'),
        userName: $('#userName').textbox('getValue'),
        loginName: $('#loginName').textbox('getValue'),
        mobile: $('#mobile').textbox('getValue'),
        validStatus: $('#validStatus').combobox('getValue'),
        roleId: $('#roleId').combotree('getValue'),
        createTimeStart: $('#createTimeStart').datebox('getValue'),
        createTimeEnd: $('#createTimeEnd').datebox('getValue'),
        lastLoginTimeStart: $('#lastLoginTimeStart').datebox('getValue'),
        lastLoginTimeEnd: $('#lastLoginTimeEnd').datebox('getValue')
    }
    $("#userMisDatagrid").datagrid({
        url: '/mis/user/loadUserDatagrid',
        queryParams: param
    });
}


// 禁用启用
function updateUserMisValidStatus(id, validStatus) {
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
                        $('#userMisDatagrid').datagrid('reload');
                    }
                    alertMsg(data.msg);
                }
            );
        }
    });
}


// 密码重置
function resetUserMisPassword(id) {
    $.messager.confirm('提示信息', '确定要重置该帐号密码?', function (r) {
        if (r) {
            $.post(
                "/basic/user/resetPassword",
                {id: id},
                function (data) {
                    if (data.code == 0) {
                        $('#userMisDatagrid').datagrid('reload');
                    }
                    alertMsg(data.msg);
                }
            );
        }
    });
}


// 新增
function openUserMisAddDialog() {
    $('#userMisAddDialog').dialog({
        title: '用户新增',
        width: '45%',
        height: '45%',
        closed: false,
        cache: false,
        href: '/mis/user/userAdd',
        modal: true
    });
}


// 修改
function openUserMisEditDialog() {
    var row = $('#userMisDatagrid').datagrid('getSelected');
    if (null == row) {
        alertMsg("请选择要修改的用户!");
        return;
    }
    if (0 == row.validStatus) {
        alertMsg("已停用的帐号不能修改!")
        return;
    }
    $('#userMisEditDialog').dialog({
        title: '用户修改',
        width: '45%',
        height: '45%',
        closed: false,
        cache: false,
        href: '/mis/user/userEdit',
        modal: true
    });
}


// 详情
function openUserMisInfoDialog() {
    $('#userMisInfoDialog').dialog({
        title: '用户信息',
        width: '60%',
        height: '65%',
        closed: false,
        cache: false,
        href: '/mis/user/userInfo',
        modal: true
    });
}


// 角色
function openUserMisRoleDialog(id, name, projectType) {
    $('#userMisRoleDialog').dialog({
        title: name + '角色设置',
        width: '100%',
        height: '100%',
        closed: false,
        cache: false,
        href: '/basic/user/role/userRole?id=' + id + '&projectType=' + projectType,
        modal: true
    });
}