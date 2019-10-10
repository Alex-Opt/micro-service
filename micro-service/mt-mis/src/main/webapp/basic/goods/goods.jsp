<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品管理</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 查询 -->
    <div region="north" title="查询条件" border="false" style="height:108px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>商品类目:</td>
                <td style="width: 250px"><select id="spuCid"/></td>

                <td>SPU编码:</td>
                <td style="width: 250px"><input id="spuId" class="easyui-textbox"/></td>

                <td>SPU名称:</td>
                <td style="width: 250px"><input id="spuName" class="easyui-textbox"/></td>

                <td>SKU编码:</td>
                <td style="width: 250px"><input id="skuId" class="easyui-textbox"/></td>

                <td>SKU名称:</td>
                <td style="width: 250px"><input id="skuName" class="easyui-textbox"/></td>
            </tr>
            <tr height="1px"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="10" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadGoodsDatagrid();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="initGoodsDatagridParam();">重置</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格 -->
    <div region="center" border="false" style="lines:true">
        <table id="goodsDatagrid"></table>
    </div>
    <!-- 查看弹窗 -->
    <div id="goodsInfoDialog"></div>
</div>
<script type="text/javascript" src="/js/basic/goods/goods.js?ver=${sv}"></script>
</body>
</html>