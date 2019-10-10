// 自动加载
$(function () {
    var row = $('#roleTreegrid').treegrid('getSelected');
    $('#roleUserDatagrid').datagrid({
        width: 'auto',
        height: 'auto',
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        url: '/basic/role/user/loadRoleUserDatagrid?roleId=' + row.id,
        loadMsg: '正在拼命加载中，请稍后...',
        columns: [[
            {title: '员工编号', field: 'id', align: 'center', width: '20%'},
            {title: '用户姓名', field: 'userName', align: 'center', width: '20%'},
            {title: '登录名', field: 'loginName', align: 'center', width: '15%'},
            {title: '手机号', field: 'mobile', align: 'center', width: '15%'},
            {title: '创建时间', field: 'createTime', align: 'center', width: '15%'},
            {title: '最后登录时间', field: 'lastLoginTime', align: 'center', width: '15%'}
        ]],
        loadFilter: responseJsonFilter
    });
})
