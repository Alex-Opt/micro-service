// 主页自动加载
$(document).ready(function () {
    var id = $('#firstModuleId').val();
    if ('' == id) {
        alertMsg("无权限,请联系管理员开启")
        return;
    }
    loadLeftMenu(id);
    $('#' + $('#firstModuleCode').val()).addClass('top-menu-hover');
})

// 主页顶部菜单点击事件
function topMenuToggle(menuId, id) {
    $('.top-menu-hover').removeClass('top-menu-hover');
    $('#' + menuId).addClass('top-menu-hover');
    loadLeftMenu(id);
}

// 主页加载左侧菜单
function loadLeftMenu(id) {
    $.post(
        '/mis/main/loadLeftMenu',
        {id: id},
        function (data) {
            if (0 == data.code) {
                var menuJson = JSON.parse(data.result);
                tabDoubleClickEven();
                tabRightClickEven();
                initMenu(menuJson);
            } else {
                alertMsg(data.msg);
            }
        }
    );
}

// tab事件
function tabDoubleClickEven() {
    // 双击关闭tab
    $('.tabs-inner').dblclick(function () {
        var title = $(this).children('span').text();
        if (title != '欢迎使用') {
            $('#tabs').tabs('close', title);
        }
    });
    $('.tabs-inner').bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        var title = $(this).children('span').text();
        $('#mm').data('currtab', title);
        return false;
    });
}

// tab右击事件
function tabRightClickEven() {
    // 右键-关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data('currtab');
        if (currtab_title != '欢迎使用') {
            $('#tabs').tabs('close', currtab_title);
        }
    });
    // 右键-全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != '欢迎使用') {
                $('#tabs').tabs('close', t);
            }
        });
    });
    // 右键-关闭除当前之外的
    $('#mm-tabcloseother').click(function () {
        var currtab_title = $('#mm').data('currtab');
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != currtab_title && t != '欢迎使用')
                $('#tabs').tabs('close', t);
        });
    });
    // 右键-关闭当前右侧的
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            return false;
        }
        nextall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    // 右键-关闭当前左侧的
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            return false;
        }
        prevall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            if (t != '欢迎使用') {
                $('#tabs').tabs('close', t);
            }
        });
        return false;
    });
    // 右键-退出
    $('#mm-exit').click(function () {
        $('#mm').menu('hide');
    });
}

// 加载菜单
function initMenu(menus) {
    $('#menuDiv').html('<div id="leftMenu" class="easyui-accordion"></div>');
    $('#leftMenu').accordion({// 导航菜单绑定初始化
        fit: true,
        border: false,
        animate: false
    });
    $.each(menus, function (i, n) {// 生成菜单
        var menulist = '';
        menulist = getMenuList(n, menulist);
        $('#leftMenu').accordion('add', {
            title: n.name,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });
    });
    $('.easyui-accordion li a').click(function () {// 菜单点击事件
        var tabTitle = $(this).children('.nav').text();
        var url = $(this).attr('rel');
        addTab(tabTitle, url);
        $('.easyui-accordion li div').removeClass('selected');
        $(this).parent().addClass('selected');
    }).hover(function () {
        $(this).parent().addClass('hover');
    }, function () {
        $(this).parent().removeClass('hover');
    });
    $('.easyui-accordion').accordion();
    $('.accordion-header-selected').click();
}

// 递归拼接菜单html
function getMenuList(data, menulist) {
    if (data.children == null) {
        return menulist;
    } else {
        $.each(data.children, function (j, o) {
            menulist += "<div title='" + o.name + "' icon='" + o.icon + "' style='overflow:auto;'>";
            menulist += '<ul>';
            if (o.url != null) {
                menulist += '<li><div>'
                    + '<a id="navTitle" target="mainFrame" rel="' + o.url + '">'
                    + '<span class="nav">' + o.name + '</span>'
                    + '</a>'
                    + '</div></li> ';
            } else {
                menulist += '<li state="closed"><div>'
                    + '<span class="nav">' + o.name + '</span>'
                    + '</div></li> ';
            }
            menulist = getMenuList(o, menulist);
            menulist += '</ul></div>';
        });
    }
    return menulist;
}

// 生成tab页
function addTab(title, url) {
    if (!$('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('add', {
            title: title,
            content: createFrame(url),
            closable: true
        });
        tabDoubleClickEven();
    } else {
        $('#tabs').tabs('select', title);
        var tab = $('#tabs').tabs('getSelected');
        $('#tabs').tabs('update', {
            tab: tab,
            options: {
                title: title,
                style: {
                    padding: '1px'
                },
                content: createFrame(url),
                closable: true,
                fit: true,
                selected: true
            }
        });
        $('#mm-tabupdate').click();
    }
}

// 根据url生成iframe
function createFrame(url) {
    return '<iframe name="mainFrame" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>';
}

// 安全退出
function loginOut(loginName) {
    $.post(
        '/mis/login/loginOut',
        {loginName: loginName},
        function (data) {
            if (0 == data.code) {
                window.location.href = "/mis/login/login";
            } else {
                alertMsg(data.msg);
            }
        }
    );
}

// 修改密码
function openPwd() {
    alertMsg('功能暂未开放,请耐心等待!');
}