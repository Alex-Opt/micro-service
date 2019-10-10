<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户信息</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <form id="formInfo" method="post">
                <table align="center" cellspacing="20">
                    <tr>
                        <td align="right">用户ID:</td>
                        <td>
                            <input id="idInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">登录名:</td>
                        <td>
                            <input id="loginNameInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">用户姓名:</td>
                        <td>
                            <input id="userNameInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">手机号:</td>
                        <td>
                            <input id="mobileInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">电子邮箱:</td>
                        <td>
                            <input id="emailInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">所属角色:</td>
                        <td>
                            <input id="roleInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">注册时间:</td>
                        <td>
                            <input id="createTimeInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">最后登录时间:</td>
                        <td>
                            <input id="lastLoginTimeInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">禁用时间:</td>
                        <td>
                            <input id="validTimeInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">禁用人:</td>
                        <td>
                            <input id="validUserInfo" class="easyui-textbox"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/mis/user/mis_user_info.js?ver=${sv}"></script>
</body>
</html>