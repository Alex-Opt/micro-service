// 自动加载
$(function () {
    initCategroyDatagridParam();
    initCategroyDatagrid();
});


// 初始化查询条件
function initCategroyDatagridParam() {
    $('#cabinetName').textbox('setValue', '');
    $('#updateName').textbox('setValue', '');
    loadDictComboboxSelect('#cabinetType', 'CabinetType');
    loadCategroyDatagrid();
}


// 初始化表格
function initCategroyDatagrid() {
    $('#categroyDatagrid').datagrid({
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
            {title: '货柜id', field: 'id', hidden: true},
            {title: '货柜名称缩写', field: 'cabinetCode', hidden: true},
            {title: '货柜类型id', field: 'cabinetType', hidden: true},
            {title: '货柜名称', field: 'cabinetName', align: 'center', width: '20%'},
            {title: '货柜类型', field: 'cabinetTypeName', align: 'center', width: '15%'},
            {title: '可放商品数', field: 'goodsCount', align: 'center', width: '15%'},
            {title: '货道数', field: 'aisleNumber', align: 'center', width: '10%'},
            {title: '舱门数', field: 'doorNumber', align: 'center', width: '10%'},
            {title: '最后操作人', field: 'updateName', align: 'center', width: '10%'},
            {title: '最后操作时间', field: 'updateTime', align: 'center', width: '20%'}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 查询
function loadCategroyDatagrid() {
    var param = {
        cabinetName: $('#cabinetName').textbox('getValue'),
        updateName: $('#updateName').textbox('getValue'),
        cabinetType: $('#cabinetType').combobox('getValue')
    }
    $("#categroyDatagrid").datagrid({
        url: '/gzg/categroy/loadCategroyDatagrid',
        queryParams: param
    });
}


// 新增
function openAdd() {
    $('#categroyAddDialog').dialog({
        title: '货柜类型新增',
        width: '50%',
        height: '50%',
        closed: false,
        cache: false,
        href: '/gzg/categroy/categroyAdd',
        modal: true
    });
}


// 修改
function openEdit() {
    var row = $('#categroyDatagrid').datagrid('getSelected');
    if (null == row) {
        alertMsg("请选择要修改的货柜类型!");
        return;
    }
    $('#categroyEditDialog').dialog({
        title: '货柜类型修改',
        width: '50%',
        height: '50%',
        closed: false,
        cache: false,
        href: '/gzg/categroy/categroyEdit',
        modal: true
    });
}