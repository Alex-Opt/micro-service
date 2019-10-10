// 自动加载
$(function () {
    initRecordImportDatagrid();
    initRecordImportDatagridParam();
});


// 重置
function initRecordImportDatagridParam() {
    $('#importId').textbox('setValue', '');
    $('#importName').textbox('setValue', '');
    $('#importTimeStart').datebox('setValue', '');
    $('#importTimeEnd').datebox('setValue', '');
    loadRecordImportDatagrid();
}


// 表格
function initRecordImportDatagrid() {
    $('#recordImportDatagrid').datagrid({
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
            {title: 'ID', field: 'id', hidden: true},
            {title: '上传ID', field: 'importId', align: 'center', width: '20%'},
            {title: '上传商品数', field: 'rowCount', align: 'center', width: '20%'},
            {title: '上传人', field: 'importName', align: 'center', width: '20%'},
            {title: '上传时间', field: 'importTime', align: 'center', width: '20%'},
            {title: '操作', field: 'op', align: 'center', width: '20%', formatter: trackingCodeOperationFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}


// 操作按钮
function trackingCodeOperationFmt(value, row, index) {
    return '<input type="button" value="删除" style="cursor:pointer" onclick="deleteTrackingCode(\'' + row.id + '\');"/>';
}


// 查询
function loadRecordImportDatagrid() {
    if (!checkIntervalTime('#importTimeStart', '#importTimeEnd', '上传时间')) {
        return;
    }
    var param = {
        importId: $('#importId').textbox('getValue'),
        importName: $('#importName').textbox('getValue'),
        importTimeStart: $('#importTimeStart').datebox('getValue'),
        importTimeEnd: $('#importTimeEnd').datebox('getValue')
    }
    $("#recordImportDatagrid").datagrid({
        url: '/basic/goods/tracking/loadRecordDatagrid',
        queryParams: param
    });
}


// 删除
function deleteTrackingCode(id) {
    $.messager.confirm('提示信息', '确定要删除该记录吗？', function (r) {
        if (r) {
            $.post("/basic/goods/tracking/deleteRecordImport?id=" + id, function (data) {
                if (data.code == 0) {
                    $('#recordImportDatagrid').datagrid('reload');
                }
                alertMsg(data.msg);
            });
        }
    });
}


// 导入
function openUpload() {
    $('#uploadTrackingCodeDialog').dialog({
        title: '追踪码导入',
        width: '60%',
        height: '40%',
        closed: false,
        cache: false,
        href: '/basic/goods/tracking/upload',
        modal: true,
        onClose: loadRecordImportDatagrid
    });
}