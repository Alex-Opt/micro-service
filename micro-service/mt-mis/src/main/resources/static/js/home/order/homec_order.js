// 自动加载
$(function () {
    initSearch();
    initDatagrid();
    searchOrder();
});

// 初始化查询条件
function initSearch() {
    $('#buyerId').textbox('setValue', '');
    $('#buyerName').textbox('setValue', '');
    $('#orderNo').textbox('setValue', '');
    $('#shopName').textbox('setValue', '');
    $('#createTimeStart').datebox('setValue', '');
    $('#createTimeEnd').datebox('setValue', '');
    $('#orderMoneyStart').numberbox('setValue', '');
    $('#orderMoneyEnd').numberbox('setValue', '');
    loadDictComboboxSelect('#paymentType', 'PaymentType');
    loadDictComboboxSelect('#orderStatus', 'OrderStatus');
    loadDictComboboxSelect('#distributionId', 'DistributeType');
    loadDictComboboxSelect('#orderType', 'OrderType');
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
            {title: '订单编号', field: 'orderNo', align: 'center', width: '10%'},
            {title: '客户UID', field: 'buyerId', align: 'center', width: '10%'},
            {title: '下单时间', field: 'createTime', align: 'center', width: '10%'},
            {title: '商品信息', field: 'goodsNames', align: 'center', width: '15%', formatter: goodsNamesFmt},
            {title: '订单分类', field: 'orderTypeName', align: 'center', width: '10%'},
            {title: '配送服务', field: 'distributionName', align: 'center', width: '10%'},
            {title: '支付方式', field: 'paymentTypeName', align: 'center', width: '5%'},
            {title: '订单状态', field: 'orderStatusName', align: 'center', width: '5%'},
            {title: '店铺名称', field: 'shopName', align: 'center', width: '15%'},
            {title: '实付金额', field: 'orderMoney', align: 'center', width: '5%'},
            {title: '操作', field: 'op', align: 'center', width: '5%', formatter: orderOperationFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}

// 超长截取
function goodsNamesFmt(value, row, index) {
    return strLengthFmt(value, 20);
}

// 操作按钮
function orderOperationFmt(value, row, index) {
    return '<input type="button" value="详情" style="cursor:pointer" onclick="orderInfo(\'' + row.id + '\');"/>';
}

// 查询
function searchOrder() {
    if (!checkParam()) {
        return;
    }
    var param = {
        buyerId: $('#buyerId').textbox('getValue'),
        buyerName: $('#buyerName').textbox('getValue'),
        orderNo: $('#orderNo').textbox('getValue'),
        shopName: $('#shopName').textbox('getValue'),
        paymentType: $('#paymentType').combobox('getValue'),
        orderStatus: $('#orderStatus').combobox('getValue'),
        distributionId: $('#distributionId').combobox('getValue'),
        orderType: $('#orderType').combobox('getValue'),
        createTimeStart: $('#createTimeStart').datebox('getValue'),
        createTimeEnd: $('#createTimeEnd').datebox('getValue'),
        orderMoneyStart: $('#orderMoneyStart').numberbox('getValue'),
        orderMoneyEnd: $('#orderMoneyEnd').numberbox('getValue')
    }
    $("#orderDatagrid").datagrid({
        url: '/homec/order/loadOrderDatagrid',
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
    if (!checkParam()) {
        return;
    }
    var param =
        '?buyerId=' + $('#buyerId').textbox('getValue') +
        '&buyerName=' + $('#buyerName').textbox('getValue') +
        '&orderNo=' + $('#orderNo').textbox('getValue') +
        '&shopName=' + $('#shopName').textbox('getValue') +
        '&paymentType=' + $('#paymentType').combobox('getValue') +
        '&orderStatus=' + $('#orderStatus').combobox('getValue') +
        '&distributionId=' + $('#distributionId').combobox('getValue') +
        '&orderType=' + $('#orderType').combobox('getValue') +
        '&createTimeStart=' + $('#createTimeStart').datebox('getValue') +
        '&createTimeEnd=' + $('#createTimeEnd').datebox('getValue') +
        '&orderMoneyStart=' + $('#orderMoneyStart').numberbox('getValue') +
        '&orderMoneyEnd=' + $('#orderMoneyEnd').numberbox('getValue');
    var url = '/homec/order/exportOrder' + param;
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
        href: '/homec/order/orderInfo?id=' + id,
        modal: true
    });
}

// 校验区间查询条件
function checkParam() {
    var createTimeStart = $('#createTimeStart').datebox('getValue');
    var createTimeEnd = $('#createTimeEnd').datebox('getValue');
    if ('' != createTimeStart && '' != createTimeEnd) {
        if (startGeaterEnd(createTimeStart, createTimeEnd)) {
            alertMsg('下单日期截止值必须大于等于起始值!');
            return false;
        }
    }
    var orderMoneyStart = $('#orderMoneyStart').numberbox('getValue');
    var orderMoneyEnd = $('#orderMoneyEnd').numberbox('getValue');
    if ('' != orderMoneyStart && '' != orderMoneyEnd) {
        if (startGeaterEnd(orderMoneyStart, orderMoneyEnd)) {
            alertMsg('实付金额截止值必须大于等于起始值!');
            return false;
        }
    }
    return true;
}