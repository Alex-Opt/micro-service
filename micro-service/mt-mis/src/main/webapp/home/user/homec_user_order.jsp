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
        <table id="userOrderDatagrid"></table>
    </div>
</div>
<script type="text/javascript" src="/js/home/user/homec_user_order.js?ver=${sv}"></script>
</body>
</html>