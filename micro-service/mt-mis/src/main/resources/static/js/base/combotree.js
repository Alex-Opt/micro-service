// 角色下拉树加载
function loadRoleCombotree(id, roleLevel, parentId) {
    $(id).combotree('reload', '/basic/role/loadRoleCombotree?roleLevel=' + roleLevel + '&parentId=' + parentId);
}


// 类目下拉树加载
function loadCategoryCombotree(id) {
    $(id).combotree('reload', '/basic/goods/categroy/loadGoodsCategroyCombotree');
}


// 功能菜单下拉树加载
function loadFuncCombotree(id, funcLevel, parentId) {
    $(id).combotree('reload', '/basic/func/loadFuncCombotree?funcLevel=' + funcLevel + '&parentId=' + parentId);
}