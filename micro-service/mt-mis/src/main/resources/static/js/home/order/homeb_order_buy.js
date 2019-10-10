// 自动加载
$(function () {
    initSearch();
    initDatagrid();
    searchOrder();
});

// 初始化查询条件
function initSearch() {
    $('#sellerId').textbox('setValue', '');
    $('#id').textbox('setValue', '');
    $('#shopName').textbox('setValue', '');
    loadDictComboboxSelect('#paymentType', 'PaymentType');
    loadDictComboboxSelect('#orderStatus', 'OrderStatus');
    loadDictComboboxSelect('#distributeType', 'DistributeType');
    $('#createTimeStart').datebox('setValue', '');
    $('#createTimeEnd').datebox('setValue', '');
    $('#orderMoneyStart').numberbox('setValue', '');
    $('#orderMoneyEnd').numberbox('setValue', '');
}

// 初始化表格
function initDatagrid() {
    $('#orderDatagrid').datagrid({
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
            {title: '订单编号', field: 'id', align: 'center', width: '10%'},
            {title: '商家UID', field: 'userId', align: 'center', width: '10%'},
            {title: '注册时间', field: 'userRegistTime', align: 'center', width: '10%'},
            {title: '店铺名称', field: 'shopName', align: 'center', width: '10%'},
            {title: '下单时间', field: 'createTime', align: 'center', width: '10%'},
            {title: '商品信息', field: 'goodsNames', align: 'center', width: '10%', formatter: goodsNamesFmt},
            {title: '支付方式', field: 'paymentTypeName', align: 'center', width: '10%'},
            {title: '订单状态', field: 'orderStatusName', align: 'center', width: '10%'},
            {title: '实付金额', field: 'actualAmount', align: 'center', width: '10%'},
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
    return '<input type="button" value="详情" style="cursor:pointer" onclick="orderInfo(\'' + row.id + '\');"/>';
}

// 查询
function searchOrder() {
    if (!checkIntervalTime('#createTimeStart', '#createTimeEnd', '下单日期')) {
        return;
    }
    if (!checkIntervalNumber('#orderMoneyStart', '#orderMoneyEnd', '实付金额')) {
        return;
    }
    var param = {
        sellerId: $('#sellerId').textbox('getValue'),
        id: $('#id').textbox('getValue'),
        shopName: $('#shopName').textbox('getValue'),
        paymentType: $('#paymentType').combobox('getValue'),
        orderStatus: $('#orderStatus').combobox('getValue'),
        distributeType: $('#distributeType').combobox('getValue'),
        createTimeStart: $('#createTimeStart').datebox('getValue'),
        createTimeEnd: $('#createTimeEnd').datebox('getValue'),
        orderMoneyStart: $('#orderMoneyStart').numberbox('getValue'),
        orderMoneyEnd: $('#orderMoneyEnd').numberbox('getValue')
    }
    $("#orderDatagrid").datagrid({
        url: '/homeb/order/buy/loadOrderDatagrid',
        queryParams: param
    });
}

// 重置
function resetSearch() {
    initSearch();
    searchOrder();
}

// 导出
function exportOrder() {
    if (!checkIntervalTime('#createTimeStart', '#createTimeEnd', '下单日期')) {
        return;
    }
    if (!checkIntervalNumber('#orderMoneyStart', '#orderMoneyEnd', '实付金额')) {
        return;
    }
    var param =
        '?sellerId=' + $('#sellerId').textbox('getValue') +
        '&id=' + $('#id').textbox('getValue') +
        '&shopName=' + $('#shopName').textbox('getValue') +
        '&paymentType=' + $('#paymentType').combobox('getValue') +
        '&orderStatus=' + $('#orderStatus').combobox('getValue') +
        '&distributeType=' + $('#distributeType').combobox('getValue') +
        '&createTimeStart=' + $('#createTimeStart').datebox('getValue') +
        '&createTimeEnd=' + $('#createTimeEnd').datebox('getValue') +
        '&orderMoneyStart=' + $('#orderMoneyStart').numberbox('getValue') +
        '&orderMoneyEnd=' + $('#orderMoneyEnd').numberbox('getValue');
    var url = '/homeb/order/buy/exportOrder' + param;
    exportExcel(url);
}

// 查看
function orderInfo(id) {
    $('#orderInfoDialog').dialog({
        title: '订单详情',
        width: '95%',
        height: '95%',
        closed: false,
        cache: false,
        href: '/homeb/order/buy/orderInfo?id=' + id,
        modal: true
    });
}