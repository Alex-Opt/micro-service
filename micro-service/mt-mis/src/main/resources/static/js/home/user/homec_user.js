// 自动加载
$(function () {
    initSearch();
    initDatagrid();
    searchUser();
});

// 初始化查询条件
function initSearch() {
    $('#id').textbox('setValue', '');
    $('#userName').textbox('setValue', '');
    $('#mobile').textbox('setValue', '');
    loadDictComboboxSelect('#sex', 'Sex');
    loadCityComoboxSelect('#provinceCode', '#cityCode');
}

// 初始化表格
function initDatagrid() {
    $('#userDatagrid').datagrid({
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
            {title: '注册时间', field: 'createTime', align: 'center', width: '10%'},
            {title: '客户ID', field: 'id', align: 'center', width: '10%'},
            {title: '客户姓名', field: 'userName', align: 'center', width: '10%'},
            {title: '性别', field: 'sexName', align: 'center', width: '10%'},
            {title: '城市', field: 'provinceName', align: 'center', width: '10%'},
            {title: '手机号', field: 'mobile', align: 'center', width: '10%'},
            {title: '最近购买商品', field: 'goodsNames', align: 'center', width: '20%', formatter: goodsNamesFmt},
            {title: '最近购买时间', field: 'orderTime', align: 'center', width: '10%'},
            {title: '操作', field: 'op', align: 'center', width: '10%', formatter: orderOperationFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}

// 超长截取
function goodsNamesFmt(value, row, index) {
    return strLengthFmt(value, 12);
}

// 操作按钮
function orderOperationFmt(value, row, index) {
    if ('' == row.orderId) {
        return '——';
    }
    return '<input type="button" value="查看订单" style="cursor:pointer" onclick="userOrderInfo();"/>';
}

// 查询
function searchUser() {
    var param = {
        id: $('#id').textbox('getValue'),
        userName: $('#userName').textbox('getValue'),
        mobile: $('#mobile').textbox('getValue'),
        sex: $('#sex').combobox('getValue'),
        provinceCode: $('#provinceCode').combobox('getValue'),
        cityCode: $('#cityCode').combobox('getValue')
    }
    $("#userDatagrid").datagrid({
        url: '/homec/user/loadUserDatagrid',
        queryParams: param
    });
}

// 重置
function resetSearch() {
    initSearch();
    searchUser();
}

// 查看订单
function userOrderInfo() {
    $('#userOrderDialog').dialog({
        title: '查看订单',
        width: '95%',
        height: '95%',
        closed: false,
        cache: false,
        href: '/homec/user/userOrderInfo',
        modal: true
    });
}