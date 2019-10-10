// 接口返回json解析
function comboboxLoadFilter(data) {
    if (0 != data.code) {
        return '';
    }
    var result = data.result;
    if (null == result) {
        alertMsg('接口返回数据出错');
        return '';
    }
    return JSON.parse(result);
}


// 加载行政区域三级联动下拉框-查询
function loadAreaComoboxSelect(provinceId, cityId, areaId) {
    var loadDataJson = JSON.parse('{"code":"0","result":"{}"}');
    $(areaId).combobox({
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            var result = data.result;
            if ('{}' != result) {
                $(areaId).combobox('setValue', JSON.parse(result)[0].id);
            }
        }
    });
    $(cityId).combobox({
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            var result = data.result;
            if ('{}' != result) {
                $(cityId).combobox('setValue', JSON.parse(result)[0].id);
            }
        },
        onSelect: function (data) {
            var pid = data.id;
            if (-1 == pid) {
                $(areaId).combobox('clear');
                $(areaId).combobox('loadData', loadDataJson);
            } else {
                $(areaId).combobox('reload', '/basic/area/loadAreaCombobox?pid=' + pid);
            }
        }
    });
    $(provinceId).combobox({
        url: "/basic/area/loadAreaCombobox?pid=1",
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            $(provinceId).combobox('setValue', JSON.parse(data.result)[0].id);
        },
        onSelect: function (data) {
            var pid = data.id;
            if (-1 == pid) {
                $(cityId).combobox('clear');
                $(areaId).combobox('clear');
                $(cityId).combobox('loadData', loadDataJson);
                $(areaId).combobox('loadData', loadDataJson);
            } else {
                $(cityId).combobox('reload', '/basic/area/loadAreaCombobox?pid=' + pid);
            }
        }
    });
}


// 加载行政区域二级联动下拉框-查询
function loadCityComoboxSelect(provinceId, cityId) {
    var loadDataJson = JSON.parse('{"code":"0","result":"{}"}');
    $(cityId).combobox({
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            var result = data.result;
            if ('{}' != result) {
                $(cityId).combobox('setValue', JSON.parse(result)[0].id);
            }
        }
    });
    $(provinceId).combobox({
        url: "/basic/area/loadAreaCombobox?pid=1",
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            $(provinceId).combobox('setValue', JSON.parse(data.result)[0].id);
        },
        onSelect: function (data) {
            var pid = data.id;
            if (-1 == pid) {
                $(cityId).combobox('clear');
                $(cityId).combobox('loadData', loadDataJson);
            } else {
                $(cityId).combobox('reload', '/basic/area/loadAreaCombobox?pid=' + pid);
            }
        }
    });
}


// 加载字典下拉框-修改
function loadDictComboboxEdit(id, dictName, dictValue) {
    $(id).combobox({
        url: "/basic/dict/loadDictComboboxAdd?dictName=" + dictName,
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            $(id).combobox('setValue', dictValue);
        }
    });
}


// 加载字典下拉框-新增
function loadDictComboboxAdd(id, dictName) {
    $(id).combobox({
        url: "/basic/dict/loadDictComboboxAdd?dictName=" + dictName,
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            $(id).combobox('setValue', JSON.parse(data.result)[0].id);
        }
    });
}


// 加载字典下拉框-查询
function loadDictComboboxSelect(id, dictName) {
    $(id).combobox({
        url: "/basic/dict/loadDictComboboxSelect?dictName=" + dictName,
        valueField: 'id',
        textField: 'name',
        editable: false,
        loadFilter: comboboxLoadFilter,
        onLoadSuccess: function (data) {
            $(id).combobox('setValue', JSON.parse(data.result)[0].id);
        }
    });
}