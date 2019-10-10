<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色管理</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 查询 -->
    <div region="north" title="查询条件" border="false" style="height:108px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>角色编号:</td>
                <td style="width: 250px"><input id="id" style="width:200px;height:25px" class="easyui-textbox"/></td>

                <td>角色名称:</td>
                <td style="width: 250px"><input id="name" style="width:200px;height:25px" class="easyui-textbox"/></td>

                <td>状态:</td>
                <td style="width: 250px"><select id="validStatus" style="width:200px;height:25px"/></td>

                <td>创建时间:</td>
                <td style="width: 250px"><input id="createTimeStart" style="width:200px;height:25px" class="easyui-datebox" editable="false"/></td>

                <td>至</td>
                <td style="width: 250px"><input id="createTimeEnd" style="width:200px;height:25px" class="easyui-datebox" editable="false"/></td>
            </tr>
            <tr height="1px"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="10" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadRoleTreegrid();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetRoleTreegrid();">重 置</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openRoleAddDialog();">新增</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openRoleEditDialog();">修改</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格树 -->
    <div region="center" border="false" style="lines:true">
        <table id="roleTreegrid"></table>
    </div>
    <!-- 新增弹窗 -->
    <div id="roleAddDialog"></div>
    <!-- 修改弹窗 -->
    <div id="roleEditDialog"></div>
    <!-- 权限弹窗 -->
    <div id="roleRightDialog"></div>
    <!-- 权限用户弹窗 -->
    <div id="roleUserDialog"></div>
</div>
<script type="text/javascript" src="/js/basic/role/role.js?ver=${sv}"></script>
</body>
</html>