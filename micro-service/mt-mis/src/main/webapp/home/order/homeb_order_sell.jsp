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
    <div region="north" title="查询条件" border="false" style="height:170px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>商家UID:</td>
                <td style="width: 250px"><input id="sellerId" class="easyui-textbox"/></td>

                <td>订单编号:</td>
                <td style="width: 250px"><input id="orderNo" class="easyui-textbox"/></td>

                <td>店铺名称:</td>
                <td style="width: 250px"><input id="shopName" class="easyui-textbox"/></td>

                <td>商家手机号:</td>
                <td style="width: 250px"><input id="sellerMobile" class="easyui-textbox"/></td>

                <td>支付方式:</td>
                <td style="width: 250px"><select id="paymentType"/></td>
            </tr>
            <tr>
                <td>订单状态:</td>
                <td style="width: 250px"><select id="orderStatus"/></td>

                <td>下单时间:</td>
                <td style="width: 250px"><input id="createTimeStart" class="easyui-datebox" editable="false"/></td>

                <td>至</td>
                <td style="width: 250px"><input id="createTimeEnd" class="easyui-datebox" editable="false"/></td>

                <td>实付金额:</td>
                <td style="width: 250px"><input id="orderMoneyStart" class="easyui-numberbox"/></td>

                <td>至</td>
                <td style="width: 250px"><input id="orderMoneyEnd" class="easyui-numberbox"/></td>
            </tr>
            <tr style="height: 1px;"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="10" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchOrder();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetSearch();">重置</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-excel" onclick="exportOrder();">导出</a>
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
<script type="text/javascript" src="/js/home/order/homeb_order_sell.js?ver=${sv}"></script>
</body>
</html>