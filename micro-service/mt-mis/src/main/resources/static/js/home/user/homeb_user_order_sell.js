// 自动加载
$(function () {
    var row = $('#userHbDatagrid').datagrid('getSelected');
    $('#userHbsOrderDatagrid').datagrid({
        width: 'auto',
        height: 'auto',
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        url: '/homeb/user/loadUserHbsOrderDatagrid?sellerId=' + row.id,
        loadMsg: '正在拼命加载中，请稍后...',
        pagination: true,
        pageSize: 20,
        pageList: [20, 30, 50],
        columns: [[
            {title: '订单编号', field: 'orderNo', align: 'center', width: '10%'},
            {title: '用户UID', field: 'buyerId', align: 'center', width: '10%'},
            {title: '下单时间', field: 'createTime', align: 'center', width: '10%'},
            {title: '商品信息', field: 'goodsNames', align: 'center', width: '20%', formatter: goodsNamesFmt},
            {title: '支付方式', field: 'paymentTypeName', align: 'center', width: '10%'},
            {title: '订单状态', field: 'orderStatusName', align: 'center', width: '10%'},
            {title: '店铺名称', field: 'shopName', align: 'center', width: '20%'},
            {title: '实付金额', field: 'orderMoney', align: 'center', width: '10%'}
        ]],
        loadFilter: responseJsonFilter
    });
});

// 超长截取
function goodsNamesFmt(value, row, index) {
    return strLengthFmt(value, 15);
}