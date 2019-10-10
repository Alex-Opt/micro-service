// 模版下载
function trackingCodeDownload() {
    var url = "/basic/goods/tracking/trackingCodeDownload";
    exportExcel(url);
}

// 上传校验
function trackingCodeUpload() {
    var fileName = $('#trackingCodeFile').filebox('getValue');
    if (null == fileName || '' == fileName) {
        alertMsg("请选择xls/xlsx文件。。。");
        return;
    }
    $('#trackingCodeUpload').form('submit', {
        url: '/basic/goods/tracking/trackingCodeUpload',
        success: function (data) {
            checkUploadResult(JSON.parse(data), '/basic/goods/tracking/trackingCodeImport?tempKey=');
        }
    });
}