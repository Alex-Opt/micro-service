<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品信息</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <form id="formInfo" method="post">
                <table align="center" cellspacing="20">
                    <tr>
                        <td align="right">商品状态:</td>
                        <td>
                            <input id="spuStatusNameInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">商品类目:</td>
                        <td>
                            <input id="spuCnameInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">SPU编码:</td>
                        <td>
                            <input id="spuIdInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">SPU名称:</td>
                        <td>
                            <input id="spuNameInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">SKU编码:</td>
                        <td>
                            <input id="skuIdInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">SKU名称:</td>
                        <td>
                            <input id="skuNameInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">原始价格:</td>
                        <td>
                            <input id="spuMarketPriceInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">商品图片:</td>
                        <td>
                            <img id="pictureUrlInfo" title="单击查看原图" style="width: 200px;height: 200px;cursor: pointer;" border="1" onclick="openPreviewDialog();"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<!-- 图片预览窗口 -->
<div id="previewDialog" style="display: none">
    <img id="previewUrl" title="单击关闭" style="cursor: pointer;" border="1" onclick="closePreviewDialog();"/>
</div>
<script type="text/javascript" src="/js/basic/goods/goods_info.js?ver=${sv}"></script>
</body>
</html>