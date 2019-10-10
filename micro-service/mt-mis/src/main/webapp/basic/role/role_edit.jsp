<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色修改</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <form id="formEdit" method="post">
                <table align="center" cellspacing="20">
                    <tr>
                        <td>角色名称:</td>
                        <td><input id="nameEdit" name="name" class="easyui-textbox" data-options="required:true, validType:'maxLength[100]'"/></td>
                    </tr>
                    <tr>
                        <td>所属项目:</td>
                        <td><select id="projectTypeEdit" name="projectType"/></td>
                    </tr>
                    <tr>
                        <td>角色类型:</td>
                        <td><select id="roleTypeEdit" name="roleType"/></td>
                    </tr>
                    <tr>
                        <td>角色等级:</td>
                        <td><select id="roleLevelEdit" name="roleLevel"/></td>
                    </tr>
                    <tr>
                        <td>上级角色:</td>
                        <td><select id="parentIdEdit" name="parentId" data-options="required:true"/></td>
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
<script type="text/javascript" src="/js/basic/role/role_edit.js?ver=${sv}"></script>
</body>
</html>