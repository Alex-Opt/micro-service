// 功能菜单树加载
function loadFuncTree(id) {
    $(id).tree('options').url='/basic/func/loadFuncTree';
    $(id).tree('reload');
}

// 行政区域树加载
function loadAreaTree(id, areaLevel) {
    $(id).tree('options').url='/basic/area/loadAreaTree?areaLevel=' + areaLevel;
    $(id).tree('reload');
}