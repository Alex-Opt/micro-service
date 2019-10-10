// 登录超时跳转登录页面
jQuery.ajaxSetup({
    complete: function (xhr) {
        if (xhr.responseText.indexOf('window.parent.location') != -1) {
            alertMsg('登录超时,请重新登录!');
            setTimeout(function () {
                window.parent.location = "/mis/login/login";
            }, 1000);
        }
    }
});


// 输出提示信息
function alertMsg(msg) {
    $.messager.show({
        title: '提示信息',
        msg: msg,
        timeout: 3000,
        showType: 'slide'
    });
}


// ResponseJson解析处理
function responseJsonFilter(data, parent) {
    if (0 != data.code) {
        alertMsg(data.msg);
        return '';
    }
    var result = data.result;
    if (null == result) {
        alertMsg('接口返回数据出错');
        return '';
    }
    return JSON.parse(result);
}


// 导出
function exportExcel(url) {
    var targetFrame = document.getElementById("exportfile_iframe");
    if (!targetFrame) {
        $('<iframe id="exportfile_iframe" src ="' + url + '"></iframe>').appendTo('body');
    } else {
        targetFrame.src = url;
    }
}


// 时间区间校验
function checkIntervalTime(start, end, msg) {
    var startValue = $(start).datebox('getValue');
    var endValue = $(end).datebox('getValue');
    if ('' != startValue && '' != endValue) {
        if (startGeaterEnd(startValue, endValue)) {
            alertMsg(msg + '截止值必须大于等于起始值!');
            return false;
        }
    }
    return true;
}


// 数字区间校验
function checkIntervalNumber(start, end, msg) {
    var startValue = $(start).numberbox('getValue');
    var endValue = $(end).numberbox('getValue');
    if ('' != startValue && '' != endValue) {
        if (startGeaterEnd(startValue, endValue)) {
            alertMsg(msg + '截止值必须大于等于起始值!');
            return false;
        }
    }
    return true;
}


// 开始大于结束
function startGeaterEnd(start, end) {
    var start = start.replace("-", "");
    var end = end.replace("-", "");
    return start > end;
}


// 上传导入校验返回值处理
function checkUploadResult(data, url) {
    if (0 == data.code) {
        $.messager.confirm('提示信息', '校验通过,确定导入', function (r) {
            if (r) {
                $.post(url + data.result, function (data) {
                    alertMsg(data.msg);
                });
            }
        });
    } else {
        $('#errorMsg').empty();
        var msg = data.result.split(";");
        for (var i = 0; i < msg.length; i++) {
            $('#errorMsg').append(msg[i]);
            $('#errorMsg').append("<hr>");
        }
        $('#msgInfo').dialog({
            width: '90%',
            height: '90%',
            title: '文件内容有误，请修改后重新上传！',
            modal: true,
            cache: false
        });
    }
}


// 判断字符串是否包含中文字符
function isChines(str) {
    return /.*[\u4e00-\u9fa5]+.*$/.test(str);
}
