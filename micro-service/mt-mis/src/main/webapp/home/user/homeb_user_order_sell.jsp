<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>C端客户订单</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 表格 -->
    <div region="center" border="false" style="lines:true">
        <table id="userHbsOrderDatagrid"></table>
    </div>
</div>
<script type="text/javascript" src="/js/home/user/homeb_user_order_sell.js?ver=${sv}"></script>
</body>
</html>