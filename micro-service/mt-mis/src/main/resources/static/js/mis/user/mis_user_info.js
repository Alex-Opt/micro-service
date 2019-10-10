// 自动加载
$(function () {
    var row = $('#userMisDatagrid').datagrid('getSelected');
    $.post(
        '/mis/user/loadUserInfo?id=' + row.id,
        function (data) {
            if (0 != data.code) {
                alertMsg(data.msg);
            } else {
                var result = JSON.parse(data.result);
                $('#idInfo').textbox('setValue', result.id);
                $('#idInfo').textbox('disable', true);
                $('#loginNameInfo').textbox('setValue', result.loginName);
                $('#loginNameInfo').textbox('disable', true);
                $('#userNameInfo').textbox('setValue', result.userName);
                $('#userNameInfo').textbox('disable', true);
                $('#mobileInfo').textbox('setValue', result.mobile);
                $('#mobileInfo').textbox('disable', true);
                $('#emailInfo').textbox('setValue', result.email);
                $('#emailInfo').textbox('disable', true);
                $('#roleInfo').textbox('setValue', result.roleNames);
                $('#roleInfo').textbox('disable', true);
                $('#createTimeInfo').textbox('setValue', result.createTime);
                $('#createTimeInfo').textbox('disable', true);
                $('#lastLoginTimeInfo').textbox('setValue', result.lastLoginTime);
                $('#lastLoginTimeInfo').textbox('disable', true);
                $('#validTimeInfo').textbox('setValue', result.validTime);
                $('#validTimeInfo').textbox('disable', true);
                $('#validUserInfo').textbox('setValue', result.validUser);
                $('#validUserInfo').textbox('disable', true);
            }
        }
    );
});