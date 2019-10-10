<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>功能修改</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <form id="formEdit" method="post">
                <table align="center" cellspacing="20">
                    <tr style="height: 50px;"></tr>
                    <tr>
                        <td>功能名称:</td>
                        <td><input id="nameEdit" name="name" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                    <tr>
                        <td>功能代码:</td>
                        <td><input id="codeEdit" name="code" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                    <tr>
                        <td>功能链接:</td>
                        <td><input id="urlEdit" name="url" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                    <tr>
                        <td>功能图标:</td>
                        <td><input id="iconEdit" name="icon" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                </table>
                <input type="hidden" id="idEdit" name="id"/>
            </form>
        </div>
        <div align="center" region="south" style="width: 90%; height: 45px;">
            <div align="center">
                <table align="center">
                    <tr style="height: 1px;"></tr>
                    <tr>
                        <td>
                            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit();">保存</a>
                            &nbsp;&nbsp;
                            <a href="#" class="easyui-linkbutton" iconCls="icon-no" onclick="closeEdit();">取消</a>
                        </td>
                    </tr>
                    <tr style="height: 1px;"></tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/basic/func/func_edit.js?ver=${sv}"></script>
</body>
</html>