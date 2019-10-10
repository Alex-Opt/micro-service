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
                    <tr style="height: 50px;"></tr>
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
                </table>
                <input type="hidden" id="idEdit" name="id"/>
                <input type="hidden" id="oldMobileEdit"/>
                <input type="hidden" name="projectType" value="101"/>
            </form>
        </div>
        <div region="south" align="center" style="height: 45px;">
            <div align="center">
                <table align="center">
                    <tr style="height: 1px;"></tr>
                    <tr>
                        <td>
                            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUserGbEdit();">保存</a>
                            &nbsp;&nbsp;
                            <a href="#" class="easyui-linkbutton" iconCls="icon-no" onclick="closeUserGbEditDialog();">取消</a>
                        </td>
                    </tr>
                    <tr style="height: 1px;"></tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/gzg/user/gzg_user_edit.js?ver=${sv}"></script>
</body>
</html>