// 自动加载
$(function () {
    initPlanDatagridParam();
    initPlanDatagrid();
});


// 初始化查询条件
function initPlanDatagridParam() {
    $('#planName').textbox('setValue', '');
    $('#updateName').textbox('setValue', '');
    // 加载货柜类型下拉框-查询
    $('#cabinetCategroyId').combobox({
        url: '/gzg/categroy/loadCategroyComboboxSelect',
        mode: 'remote',
        valueField: 'id',
        textField: 'name',
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            $('#cabinetCategroyId').combobox('setValue', JSON.parse(data.result)[0].id);
        }
    });
    loadPlanDatagrid();
}


// 初始化表格
function initPlanDatagrid() {
    $('#planDatagrid').datagrid({
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
            {title: '方案id', field: 'id', hidden: true},
            {title: '货道数', field: 'aisleNumber', hidden: true},
            {title: '舱门数', field: 'doorNumber', hidden: true},
            {title: '方案名称', field: 'planName', align: 'center', width: '20%'},
            {title: '货柜类型', field: 'cabinetCategroyName', align: 'center', width: '20%'},
            {title: '使用方案货柜数量', field: 'planCount', align: 'center', width: '20%'},
            {title: '可放商品数', field: 'goodsCount', align: 'center', width: '10%'},
            {title: '操作人', field: 'updateName', align: 'center', width: '10%'},
            {title: '操作时间', field: 'updateTime', align: 'center', width: '20%'}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 查询
function loadPlanDatagrid() {
    var param = {
        planName: $('#planName').textbox('getValue'),
        updateName: $('#updateName').textbox('getValue'),
        cabinetCategroyId: $('#cabinetCategroyId').combobox('getValue')
    }
    $("#planDatagrid").datagrid({
        url: '/gzg/plan/loadPlanDatagrid',
        queryParams: param
    });
}


// 新增
function openAdd() {
    $('#planAddDialog').dialog({
        title: '货柜类型新增',
        width: '50%',
        height: '50%',
        closed: false,
        cache: false,
        href: '/gzg/plan/planAdd',
        modal: true
    });
}


// 修改
function openEdit() {
    var row = $('#planDatagrid').datagrid('getSelected');
    if (null == row) {
        alertMsg("请选择要修改的货柜类型!");
        return;
    }
    $('#planEditDialog').dialog({
        title: '货柜类型修改',
        width: '50%',
        height: '50%',
        closed: false,
        cache: false,
        href: '/gzg/plan/planEdit',
        modal: true
    });
}