<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单查询</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 查询 -->
    <div region="north" title="查询条件" border="false" style="height:138px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>订单来源:</td>
                <td style="width: 250px"><select id="orderCategory"/></td>

                <td>订单编号:</td>
                <td style="width: 250px"><input id="orderNo" class="easyui-textbox"/></td>

                <td>客户UID:</td>
                <td style="width: 250px"><input id="buyerId" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>客户姓名:</td>
                <td style="width: 250px"><input id="buyerName" class="easyui-textbox"/></td>

                <td>商家UID:</td>
                <td style="width: 250px"><input id="sellerId" class="easyui-textbox"/></td>

                <td>店铺名称:</td>
                <td style="width: 250px"><input id="shopName" class="easyui-textbox"/></td>
            </tr>
            <tr height="1px"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="6" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchOrder();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetSearch();">重置</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格 -->
    <div region="center" border="false" style="lines:true">
        <table id="orderDatagrid"></table>
    </div>
    <!-- 查看弹窗 -->
    <div id="orderInfoDialog"></div>
</div>
<script type="text/javascript" src="/js/customer/order/customer_order.js?ver=${sv}"></script>
</body>
</html>