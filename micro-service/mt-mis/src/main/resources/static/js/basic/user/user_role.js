// 自动加载
$(function () {
    var userId = $('#userId').val();
    var projectType = $('#projectType').val();
    loadUserRoleDatagrid(userId);
    loadRoleDatagrid(userId, projectType);
})


// 已分配角色
function loadUserRoleDatagrid(userId) {
    $('#userRoleDatagrid').datagrid({
        title: '已分配角色',
        width: 'auto',
        height: 'auto',
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        url: '/basic/user/role/loadUserRoleDatagrid?userId=' + userId,
        loadMsg: '正在拼命加载中，请稍后...',
        columns: [[
            {title: '用户ID', field: 'userId', hidden: true},
            {title: '角色编号', field: 'roleId', align: 'center', width: '30%'},
            {title: '角色名称', field: 'roleName', align: 'left', width: '30%'},
            {title: '操作', field: 'op', align: 'center', width: '40%', formatter: userRoleOperationFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 操作按钮
function userRoleOperationFmt(value, row, index) {
    return '<input type="button" value="取消分配" style="cursor:pointer" onclick="deleteUserRole(\'' + row.userId + '\',\'' + row.roleId + '\');"/>';
}


// 取消分配
function deleteUserRole(userId, roleId) {
    $.post(
        "/basic/user/role/deleteUserRole",
        {userId: userId, roleId: roleId},
        function (data) {
            if (0 == data.code) {
                $('#userRoleDatagrid').datagrid('reload');
                $('#roleDatagrid').datagrid('reload');
                var projectType = $('#projectType').val();
                if (101 == projectType) {
                    $('#userGbDatagrid').datagrid('reload');
                } else {
                    $('#userMisDatagrid').datagrid('reload');
                }
            }
            alertMsg(data.msg);
        }
    );
}


// 未分配角色
function loadRoleDatagrid(userId, projectType) {
    $('#roleDatagrid').datagrid({
        title: '未分配角色',
        width: 'auto',
        height: 'auto',
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        url: '/basic/user/role/loadRoleDatagrid?userId=' + userId + '&projectType=' + projectType,
        loadMsg: '正在拼命加载中，请稍后...',
        columns: [[
            {title: '用户ID', field: 'userId', hidden: true},
            {title: '角色编号', field: 'roleId', align: 'center', width: '30%'},
            {title: '角色名称', field: 'roleName', align: 'left', width: '30%'},
            {title: '操作', field: 'op', align: 'center', width: '40%', formatter: roleOperationFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 操作按钮
function roleOperationFmt(value, row, index) {
    return '<input type="button" value="分配" style="cursor:pointer" onclick="insertUserRole(\'' + row.userId + '\',\'' + row.roleId + '\');"/>';
}


// 分配
function insertUserRole(userId, roleId) {
    $.post(
        "/basic/user/role/insertUserRole",
        {userId: userId, roleId: roleId},
        function (data) {
            if (0 == data.code) {
                $('#userRoleDatagrid').datagrid('reload');
                $('#roleDatagrid').datagrid('reload');
                var projectType = $('#projectType').val();
                if (101 == projectType) {
                    $('#userGbDatagrid').datagrid('reload');
                } else {
                    $('#userMisDatagrid').datagrid('reload');
                }
            }
            alertMsg(data.msg);
        }
    );
}