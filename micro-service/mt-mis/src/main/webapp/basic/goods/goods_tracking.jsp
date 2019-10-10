<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>追踪码管理</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 查询 -->
    <div region="north" title="查询条件" border="false" style="height:108px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>上传ID:</td>
                <td style="width: 250px"><input id="importId" class="easyui-textbox"/></td>

                <td>上传人:</td>
                <td style="width: 250px"><input id="importName" class="easyui-textbox"/></td>

                <td>上传时间:</td>
                <td style="width: 250px"><input id="importTimeStart" class="easyui-datebox" editable="false"/></td>

                <td>至</td>
                <td style="width: 250px"><input id="importTimeEnd" class="easyui-datebox" editable="false"/></td>
            </tr>
            <tr height="1px"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="10" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="loadRecordImportDatagrid();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="initRecordImportDatagridParam();">重置</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-excel" onclick="openUpload();">导入</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格 -->
    <div region="center" border="false" style="lines:true">
        <table id="recordImportDatagrid"></table>
    </div>
    <!-- 导入弹窗 -->
    <div id="uploadTrackingCodeDialog"></div>
</div>
<script type="text/javascript" src="/js/basic/goods/goods_tracking.js?ver=${sv}"></script>
</body>
</html>