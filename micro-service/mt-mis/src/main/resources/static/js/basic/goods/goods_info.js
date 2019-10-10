// 自动加载
$(function () {
    var row = $('#goodsDatagrid').datagrid('getSelected');
    $.post(
        '/basic/goods/loadSkuPicture?skuId=' + row.skuId,
        function (data) {
            if (0 == data.code) {
                $('#pictureUrlInfo').attr('src', data.result);
                $('#previewUrl').attr('src', data.result);
                $('#spuStatusNameInfo').textbox('setValue', row.spuStatusName);
                $('#spuStatusNameInfo').textbox('disable', true);
                $('#spuCnameInfo').textbox('setValue', row.spuCname);
                $('#spuCnameInfo').textbox('disable', true);
                $('#spuIdInfo').textbox('setValue', row.spuId);
                $('#spuIdInfo').textbox('disable', true);
                $('#spuNameInfo').textbox('setValue', row.spuName);
                $('#spuNameInfo').textbox('disable', true);
                $('#skuIdInfo').textbox('setValue', row.skuId);
                $('#skuIdInfo').textbox('disable', true);
                $('#skuNameInfo').textbox('setValue', row.skuName);
                $('#skuNameInfo').textbox('disable', true);
                $('#spuMarketPriceInfo').textbox('setValue', row.spuMarketPrice);
                $('#spuMarketPriceInfo').textbox('disable', true);
            } else {
                alertMsg(data.msg);
            }
        }
    );
});

// 打开预览窗口
function openPreviewDialog() {
    $('#previewDialog').dialog({
        title: '',
        modal: true,
        cache: false
    });
}

// 关闭预览窗口
function closePreviewDialog() {
    $('#previewDialog').dialog('close');
}