// 表格树加载
$(function () {
    $('#funcTreegrid').treegrid({
        fit: true,
        fitColumns: true,
        rownumbers: true,
        animate: true,
        idField: 'id',
        treeField: 'name',
        url: '/basic/func/loadFuncTreegrid',
        loadMsg: '数据加载中,请稍后...',
        columns: [[
            {title: '功能名称', field: 'name', width: '20%'},
            {title: '功能排序', field: 'sort', width: '6%', align: 'center'},
            {title: '权限代码', field: 'code', width: '20%'},
            {title: '功能图标', field: 'icon', width: '10%', align: 'center'},
            {title: '功能级别', field: 'funcLevelName', width: '6%', align: 'center'},
            {title: '功能类型', field: 'funcTypeName', width: '7%', align: 'center'},
            {title: '功能链接', field: 'url', width: '30%'}
        ]],
        loadFilter: responseJsonFilter,
        onClickRow: function (row) {
            $('#funcTreegrid').treegrid('expand', row.id);
        },
        onDblClickRow: function (row) {
            $('#funcTreegrid').treegrid('collapse', row.id);
        }
    });
});


// 新增页面
function openFuncAddDialog() {
    $('#funcAddDialog').dialog({
        title: '功能新增',
        width: '55%',
        height: '55%',
        closed: false,
        cache: false,
        href: '/basic/func/funcAdd',
        modal: true
    });
}


// 修改页面
function openFuncEditDialog() {
    var node = $('#funcTreegrid').treegrid('getSelected');
    if (null == node) {
        alertMsg("请选择要修改的功能!");
        return;
    }
    var funcLevel = node.funcLevel;
    if ('' == funcLevel) {
        alertMsg("项目名称不能修改!");
        return;
    }
    $('#funcEditDialog').dialog({
        title: '功能修改',
        width: '55%',
        height: '55%',
        closed: false,
        cache: false,
        href: '/basic/func/funcEdit',
        modal: true
    });
}


// 删除
function deleteFunc() {
    var node = $('#funcTreegrid').treegrid('getSelected');
    if (null == node) {
        alertMsg("请选择要删除的功能!");
        return;
    }
    $.messager.confirm('提示信息', '确定要删除"' + node.name + '"功能吗？', function (r) {
        if (r) {
            $.post("/basic/func/deleteFunc?id=" + node.id, function (data) {
                if (data.code == 0) {
                    $('#funcTreegrid').treegrid('reload');
                }
                alertMsg(data.msg);
            });
        }
    });
}


// 上移、下移
function sortFunc(type) {
    var node = $('#funcTreegrid').treegrid('getSelected');
    if ('' == node.funcLevel) {
        alertMsg('项目名称不能排序!');
        return;
    }
    var moveId = node.id;
    var parent = $('#funcTreegrid').treegrid('getParent', moveId);
    var parentId = parent.id;
    var childrens = $('#funcTreegrid').treegrid('getChildren', parentId);
    var length = childrens.length;
    if ('upper' == type) {
        for (var i = 0; i < length; i++) {
            var children = childrens[i];
            if (children.id == moveId) {
                if (i == 0) {
                    alertMsg('已经不能再向上移动!');
                    return;
                } else {
                    var upper = null;
                    var upperParentId = null;
                    var j = i - 1;
                    while (upperParentId != parentId) {
                        upper = childrens[j];
                        upperParentId = $('#funcTreegrid').treegrid('getParent', upper.id).id;
                        j--;
                    }
                    var ids = moveId + ',' + upper.id;
                    var sorts = upper.sort + ',' + node.sort;
                    $.post("/basic/func/sortFunc?ids=" + ids + '&sorts=' + sorts, function (data) {
                        if (data.code == 0) {
                            $('#funcTreegrid').treegrid('reload', parentId);
                        }
                        alertMsg(data.msg);
                    });
                }
            }
        }
    } else {
        for (var i = 0; i < length; i++) {
            var children = childrens[i];
            if (children.id == moveId) {
                var lower = null;
                var lowerParentId = null;
                var j = i + 1;
                while (lowerParentId != parentId) {
                    if (j == length) {
                        alertMsg('已经不能再向下移动!');
                        return;
                    }
                    lower = childrens[j];
                    lowerParentId = $('#funcTreegrid').treegrid('getParent', lower.id).id;
                    j++;
                }
                var ids = moveId + ',' + lower.id;
                var sorts = lower.sort + ',' + node.sort;
                $.post("/basic/func/sortFunc?ids=" + ids + '&sorts=' + sorts, function (data) {
                    if (data.code == 0) {
                        $('#funcTreegrid').treegrid('reload', parentId);
                    }
                    alertMsg(data.msg);
                });
            }
        }
    }
}