<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户修改</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <form id="formEdit" method="post">
                <table align="center" cellspacing="20">
                    <tr>
                        <td align="right">登录名:</td>
                        <td>
                            yykj<input id="loginNameEdit" name="loginName" class="easyui-textbox" data-options="{required:true, validType:['lowercase','maxLength[46]']}" style="width:270px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">用户姓名:</td>
                        <td>
                            <input id="userNameEdit" name="userName" class="easyui-textbox" data-options="{required:true, validType:['chinese','maxLength[20]']}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">手机号:</td>
                        <td>
                            <input id="mobileEdit" name="mobile" class="easyui-textbox" data-options="{required:true, validType:'mobile'}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">电子邮箱:</td>
                        <td>
                            <input id="emailEdit" name="email" class="easyui-textbox" data-options="{validType:['email','maxLength[50]']}"/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" id="idEdit" name="id"/>
                <input type="hidden" id="oldLoginNameEdit"/>
                <input type="hidden" name="projectType" value="1000"/>
            </form>
        </div>
        <div region="south" align="center" style="height: 45px;">
            <div align="center">
                <table align="center">
                    <tr style="height: 1px;"></tr>
                    <tr>
                        <td>
                            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUserMisEdit();">保存</a>
                            &nbsp;&nbsp;
                            <a href="#" class="easyui-linkbutton" iconCls="icon-no" onclick="closeUserMisEditDialog();">取消</a>
                        </td>
                    </tr>
                    <tr style="height: 1px;"></tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/mis/user/mis_user_edit.js?ver=${sv}"></script>
</body>
</html>