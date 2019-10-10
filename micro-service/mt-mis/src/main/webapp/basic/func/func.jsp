<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>菜单管理</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 操作 -->
    <div region="north" border="false">
        <table align="center">
            <tr>
                <td colspan="12" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openFuncAddDialog();">新增</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openFuncEditDialog();">修改</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteFunc();">删除</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-top" onclick="sortFunc('upper');">上移</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-down" onclick="sortFunc('lower');">下移</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格树 -->
    <div region="center" border="false" style="lines:true">
        <table id="funcTreegrid"></table>
    </div>
    <!-- 新增弹窗 -->
    <div id="funcAddDialog"></div>
    <!-- 修改弹窗 -->
    <div id="funcEditDialog"></div>
</div>
<script type="text/javascript" src="/js/basic/func/func.js?ver=${sv}"></script>
</body>
</html>