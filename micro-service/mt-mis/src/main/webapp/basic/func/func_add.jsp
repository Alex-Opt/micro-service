<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>功能新增</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <form id="formAdd" method="post">
                <table align="center" cellspacing="20">
                    <tr>
                        <td>功能名称:</td>
                        <td><input id="nameAdd" name="name" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                    <tr>
                        <td>所属项目:</td>
                        <td><select id="projectTypeAdd" name="projectType"/></td>
                    </tr>
                    <tr>
                        <td>功能类型:</td>
                        <td><select id="funcTypeAdd" name="funcType"/></td>
                    </tr>
                    <tr>
                        <td>上级功能:</td>
                        <td><select id="parentIdAdd" name="parentId" data-options="required: true"/></td>
                    </tr>
                    <tr>
                        <td>功能代码:</td>
                        <td><input id="codeAdd" name="code" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                    <tr>
                        <td>功能链接:</td>
                        <td><input id="urlAdd" name="url" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                    <tr>
                        <td>功能图标:</td>
                        <td><input id="iconAdd" name="icon" class="easyui-textbox" data-options="required: true"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div align="center" region="south" style="height: 45px;">
            <div align="center">
                <table align="center">
                    <tr style="height: 1px;"></tr>
                    <tr>
                        <td>
                            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdd();">保存</a>
                            &nbsp;&nbsp;
                            <a href="#" class="easyui-linkbutton" iconCls="icon-no" onclick="closeAdd();">取消</a>
                        </td>
                    </tr>
                    <tr style="height: 1px;"></tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/basic/func/func_add.js?ver=${sv}"></script>
</body>
</html>