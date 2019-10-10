// 自动加载
$(function () {
    initSearch();
    initDatagrid();
    searchUser();
});

// 初始化查询条件
function initSearch() {
    $('#id').textbox('setValue', '');
    $('#mobile').textbox('setValue', '');
    $('#idCard').textbox('setValue', '');
    $('#userName').textbox('setValue', '');
    loadAreaComoboxSelect('#provinceCode', '#cityCode', '#districtCode');
    $('#createTimeStart').datebox('setValue', '');
    $('#createTimeEnd').datebox('setValue', '');
    $('#quickType').combobox({
        valueField: 'id',
        textField: 'name',
        editable: false,
        onLoadSuccess: function (data) {
            if (data.length != null) {
                $('#quickType').combobox('setValue', data[0].id);
            }
        }
    });
    var quickType = JSON.parse('[{"id":"-1","name":"全部"},{"id":"9","name":"H5"},{"id":"10","name":"活动"}]');
    $('#quickType').combobox('loadData', quickType);
}

// 初始化表格
function initDatagrid() {
    $('#userHbDatagrid').datagrid({
        width: 'auto',
        height: 'auto',
        fit: true,
        striped: true,
        rownumbers: true,
        singleSelect: true,
        loadMsg: '正在拼命加载中，请稍后...',
        pagination: true,
        columns: [[
            {title: '商家ID', field: 'id', hidden: true},
            {title: '注册信息', field: 'registInfo', align: 'left', width: '15%'},
            {title: '商家信息', field: 'sellerInfo', align: 'left', width: '15%'},
            {title: '商家属性', field: 'sellerAttribute', align: 'left', width: '20%'},
            {title: '店铺信息', field: 'shopInfo', align: 'left', width: '20%'},
            {title: '售卖记录', field: 'salesRecord', align: 'left', width: '20%'},
            {title: '操作', field: 'op', align: 'center', width: '10%', formatter: userHbOperationFmt}
        ]],
        loadFilter: responseJsonFilter
    });
}

// 操作按钮
function userHbOperationFmt(value, row, index) {
    var button =
        '</br><input type="button" value="查看售卖订单" style="cursor:pointer" onclick="hbsOrder();"/></br></br>' +
        '<input type="button" value="查看进货订单" style="cursor:pointer" onclick="hbpOrder();"/></br></br>'
    ;
    return button;
}

// 查询
function searchUser() {
    if (!checkIntervalTime('#createTimeStart', '#createTimeEnd', '注册时间')) {
        return;
    }
    var param = {
        id: $('#id').textbox('getValue'),
        mobile: $('#mobile').textbox('getValue'),
        idCard: $('#idCard').textbox('getValue'),
        userName: $('#userName').textbox('getValue'),
        quickType: $('#quickType').combobox('getValue'),
        provinceCode: $('#provinceCode').combobox('getValue'),
        cityCode: $('#cityCode').combobox('getValue'),
        districtCode: $('#districtCode').combobox('getValue'),
        createTimeStart: $('#createTimeStart').datebox('getValue'),
        createTimeEnd: $('#createTimeEnd').datebox('getValue')
    }
    $("#userHbDatagrid").datagrid({
        url: '/homeb/user/loadUserDatagrid',
        queryParams: param
    });
}

// 重置
function resetSearch() {
    initSearch();
    searchUser();
}

// 查看售卖订单
function hbsOrder() {
    $('#orderHbsDialog').dialog({
        title: '查看售卖订单',
        width: '95%',
        height: '95%',
        closed: false,
        cache: false,
        href: '/homeb/user/userHbsOrder',
        modal: true
    });
}

// 查看进货订单
function hbpOrder() {
    $('#orderHbpDialog').dialog({
        title: '查看进货订单',
        width: '95%',
        height: '95%',
        closed: false,
        cache: false,
        href: '/homeb/user/userHbpOrder',
        modal: true
    });
}