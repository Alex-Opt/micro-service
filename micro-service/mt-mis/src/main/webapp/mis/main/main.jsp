<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>MOTI MIS</title>
    <link rel="icon" type="image/x-icon" href="/images/favicon/favicon.ico">
    <link rel="stylesheet" type="text/css" href="/css/mis/main/main.css?ver=${sv}"/>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<!-- 顶部菜单 -->
<%@ include file="main_head.jsp" %>
<!-- 左侧导航 -->
<%@ include file="main_left.jsp" %>
<!-- 右侧页面 -->
<%@ include file="main_right.jsp" %>
<!-- tab页 -->
<%@ include file="main_tab.jsp" %>
<script type="text/javascript" src="/js/mis/main/main.js?ver=${sv}"></script>
</body>
</html>