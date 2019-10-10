<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户角色</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <input type="hidden" id="userId" value="${userId}"/>
    <input type="hidden" id="projectType" value="${projectType}"/>
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center" style="width: 50%;">
            <ul id="userRoleDatagrid"></ul>
        </div>
        <div region="west" style="width: 50%;">
            <ul id="roleDatagrid"></ul>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/basic/user/user_role.js?ver=${sv}"></script>
</body>
</html>