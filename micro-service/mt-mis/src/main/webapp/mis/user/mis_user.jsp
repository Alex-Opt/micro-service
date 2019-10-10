<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 查询 -->
    <div region="north" title="查询条件" border="false" style="height:138px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>员工编号:</td>
                <td style="width: 250px"><input id="id" class="easyui-textbox"/></td>

                <td>用户名称:</td>
                <td style="width: 250px"><input id="userName" class="easyui-textbox"/></td>

                <td>登录名:</td>
                <td style="width: 250px"><input id="loginName" class="easyui-textbox"/></td>

                <td>手机号:</td>
                <td style="width: 250px"><input id="mobile" class="easyui-textbox"/></td>

                <td>状态:</td>
                <td style="width: 250px"><select id="validStatus"/></td>
            </tr>
            <tr>
                <td>所属角色:</td>
                <td style="width: 250px"><select id="roleId"/></td>

                <td>创建时间:</td>
                <td style="width: 250px"><input id="createTimeStart" class="easyui-datebox" editable="false"/></td>

                <td>至</td>
                <td style="width: 250px"><input id="createTimeEnd" class="easyui-datebox" editable="false"/></td>

                <td>最后登录时间:</td>
                <td style="width: 250px"><input id="lastLoginTimeStart" class="easyui-datebox" editable="false"/></td>

                <td>至</td>
                <td style="width: 250px"><input id="lastLoginTimeEnd" class="easyui-datebox" editable="false"/></td>
            </tr>
            <tr style="height: 1px;"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="10" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadUserMisDatagrid();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="initUserMisDatagridParam();">重置</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openUserMisAddDialog();">新增</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openUserMisEditDialog();">修改</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格 -->
    <div region="center" border="false" style="lines:true">
        <table id="userMisDatagrid"></table>
    </div>
    <!-- 新增弹窗 -->
    <div id="userMisAddDialog"></div>
    <!-- 修改弹窗 -->
    <div id="userMisEditDialog"></div>
    <!-- 查看弹窗 -->
    <div id="userMisInfoDialog"></div>
    <!-- 角色弹窗 -->
    <div id="userMisRoleDialog"></div>
</div>
<script type="text/javascript" src="/js/mis/user/mis_user.js?ver=${sv}"></script>
</body>
</html>