// 自动加载
$(function () {
    initSearch();
    initDatagrid();
    searchOrder();
});

// 初始化查询条件
function initSearch() {
    $('#orderNo').textbox('setValue', '');
    $('#buyerId').textbox('setValue', '');
    $('#buyerName').textbox('setValue', '');
    $('#sellerId').textbox('setValue', '');
    $('#shopName').textbox('setValue', '');
    loadDictComboboxSelect('#orderCategory', 'OrderCategory');
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
            {title: '订单来源', field: 'orderSourceName', align: 'center', width: '5%'},
            {title: '支付方式', field: 'paymentTypeName', align: 'center', width: '5%'},
            {title: '订单状态', field: 'orderStatusName', align: 'center', width: '5%'},
            {title: '店铺名称', field: 'shopName', align: 'center', width: '10%'},
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
    return '<input type="button" value="详情" style="cursor:pointer" onclick="orderInfo(\'' + row.orderType + '\',\'' + row.orderSource + '\',\'' + row.id + '\');"/>';
}

// 查询
function searchOrder() {
    var param = {
        orderNo: $('#orderNo').textbox('getValue'),
        buyerId: $('#buyerId').textbox('getValue'),
        buyerName: $('#buyerName').textbox('getValue'),
        sellerId: $('#sellerId').textbox('getValue'),
        shopName: $('#shopName').textbox('getValue'),
        orderCategory: $('#orderCategory').combobox('getValue')
    }
    $("#orderDatagrid").datagrid({
        url: '/customer/order/loadOrderDatagrid',
        queryParams: param
    });
}

// 重置
function resetSearch() {
    initSearch();
    searchOrder();
}

// 查看
function orderInfo(orderType, orderSource, id) {
    var orderInfoUrl = '';
    if (30 == orderSource) {
        orderInfoUrl = '/gzg/order/orderInfo?id=' + id;
    } else if (10 == orderSource) {
        if (4 == orderType) {
            orderInfoUrl = '/homeb/order/buy/orderInfo?id=' + id;
        } else {
            orderInfoUrl = '/homeb/order/sell/orderInfo?id=' + id;
        }
    } else {
        orderInfoUrl = '/homec/order/orderInfo?id=' + id;
    }
    $('#orderInfoDialog').dialog({
        title: '订单详情',
        width: '95%',
        height: '95%',
        closed: false,
        cache: false,
        href: orderInfoUrl,
        modal: true
    });
}