<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>C端客户</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 查询 -->
    <div region="north" title="查询条件" border="false" style="height:138px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>客户UID:</td>
                <td style="width: 250px"><input id="id" class="easyui-textbox"/></td>

                <td>客户姓名:</td>
                <td style="width: 250px"><input id="userName" class="easyui-textbox"/></td>

                <td>手机号:</td>
                <td style="width: 250px"><input id="mobile" class="easyui-textbox"/></td>

                <td>客户性别:</td>
                <td style="width: 250px"><select id="sex"/></td>
            </tr>
            <tr>
                <td>所在省:</td>
                <td style="width: 250px"><select id="provinceCode"/></td>

                <td>所在市:</td>
                <td style="width: 250px"><select id="cityCode"/></td>
            </tr>
            <tr style="height: 1px;"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="8" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchUser();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetSearch();">重置</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格 -->
    <div region="center" border="false" style="lines:true">
        <table id="userDatagrid"></table>
    </div>
    <!-- 订单弹窗 -->
    <div id="userOrderDialog"></div>
</div>
<script type="text/javascript" src="/js/home/user/homec_user.js?ver=${sv}"></script>
</body>
</html>