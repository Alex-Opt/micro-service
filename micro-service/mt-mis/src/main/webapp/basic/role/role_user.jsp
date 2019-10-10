<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色包含用户</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center" border="false" style="lines:true">
            <table id="roleUserDatagrid"></table>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/basic/role/role_user.js?ver=${sv}"></script>
</body>
</html>