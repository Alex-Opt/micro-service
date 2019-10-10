<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>MOTI ERP</title>
    <link rel="icon" type="image/x-icon" href="/images/favicon/favicon.ico">
</head>
<body>
<div style="height: 250px;"></div>
<center>
    <div class="easyui-panel" title="用户登录" style="width:400px;">
        <form id="formLogin">
            <table cellpadding="5" style="text-align: center">
                <tr>
                    <td id="message" colspan="2" style="height: 40px;"></td>
                </tr>
                <tr>
                    <td>帐号:</td>
                    <td><input class="easyui-textbox" type="text" name="loginName" data-options="required:true" style="width:335px;height: 35px;"/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input class="easyui-textbox" type="password" name="password" data-options="required:true" style="width:335px;height: 35px;"/></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="width:200px;" onclick="loginIn()">登录</a>
        </div>
    </div>
</center>
</div>
<script type="text/javascript" src="/js/mis/login/login.js?ver=${sv}"></script>
</body>
</html>