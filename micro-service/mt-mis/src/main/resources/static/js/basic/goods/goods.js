// 自动加载
$(function () {
    initCategoryCombotree();
    initGoodsDatagrid();
    initGoodsDatagridParam();
});


// 初始化类目下拉树
function initCategoryCombotree() {
    $('#spuCid').combotree({
        panelHeight: 250,
        onShowPanel: function () {
            loadCategoryCombotree('#spuCid');
        },
        loadFilter: responseJsonFilter
    });
}


// 初始化查询条件
function initGoodsDatagridParam() {
    $('#spuId').textbox('setValue', '');
    $('#spuName').textbox('setValue', '');
    $('#skuId').textbox('setValue', '');
    $('#skuName').textbox('setValue', '');
    $('#spuCid').combotree('setValue', '');
    loadGoodsDatagrid();
}


// 初始化表格
function initGoodsDatagrid() {
    $('#goodsDatagrid').datagrid({
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
            {title: '商品类目', field: 'spuCname', align: 'center', width: '10%'},
            {title: 'SPU编码', field: 'spuId', align: 'center', width: '10%'},
            {title: 'SPU名称', field: 'spuName', align: 'center', width: '20%', formatter: nameFmt},
            {title: '69码', field: 'skuBarCode', align: 'center', width: '10%'},
            {title: 'SKU编码', field: 'skuId', align: 'center', width: '10%'},
            {title: 'SKU名称', field: 'skuName', align: 'center', width: '20%', formatter: nameFmt},
            {title: '原始价格', field: 'spuMarketPrice', align: 'center', width: '10%'},
            {title: '商品状态', field: 'spuStatusName', align: 'center', width: '5%'},
            {title: '操作', field: 'op', align: 'center', width: '5%', formatter: goodsOperationFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 名称超长截取
function nameFmt(value, row, index) {
    return strLengthFmt(value, 25);
}


// 操作按钮
function goodsOperationFmt(value, row, index) {
    return '<input type="button" value="查看" style="cursor:pointer" onclick="goodsInfo();"/>';
}


// 查询
function loadGoodsDatagrid() {
    var param = {
        spuId: $('#spuId').textbox('getValue'),
        spuName: $('#spuName').textbox('getValue'),
        skuId: $('#skuId').textbox('getValue'),
        skuName: $('#skuName').textbox('getValue'),
        spuCid: $('#spuCid').combotree('getValue')
    }
    $("#goodsDatagrid").datagrid({
        url: '/basic/goods/loadGoodsDatagrid',
        queryParams: param
    });
}


// 查看
function goodsInfo() {
    $('#goodsInfoDialog').dialog({
        title: '商品信息',
        width: '70%',
        height: '75%',
        closed: false,
        cache: false,
        href: '/basic/goods/goodsInfo',
        modal: true
    });
}