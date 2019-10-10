// 有效状状态格式化
function validStatusFmt(value, row, index) {
    var validStatus = row.validStatus;
    if (validStatus == '0') {
        value = "<span style='color:red'>" + value + "</span>";
    }
    return value;
}

// 内容超长处理
function strLengthFmt(value, length) {
    if (null == value || 'undefined' == typeof(value) || '' == value) {
        return '';
    } else if (value.length > length) {
        return '<a style="cursor: pointer;" title="' + value + '" onclick="alertMsg(\'' + value + '\');">' + value.substring(0, length) + '...</a>';
    } else {
        return value;
    }
}