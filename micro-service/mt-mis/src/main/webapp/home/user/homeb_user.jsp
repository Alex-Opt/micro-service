<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商家列表</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <!-- 查询 -->
    <div region="north" title="查询条件" border="false" style="height:138px;background:#fafafa;">
        <table id="tableSrarch" align="center">
            <tr height="1px"></tr>
            <!-- 查询条件 -->
            <tr>
                <td>商家UID:</td>
                <td style="width: 250px"><input id="id" class="easyui-textbox"/></td>

                <td>手机号:</td>
                <td style="width: 250px"><input id="mobile" class="easyui-textbox"/></td>

                <td>身份证号:</td>
                <td style="width: 250px"><input id="idCard" class="easyui-textbox"/></td>

                <td>商家姓名:</td>
                <td style="width: 250px"><input id="userName" class="easyui-textbox"/></td>

                <td>注册来源:</td>
                <td style="width: 250px"><select id="quickType"/></td>
            </tr>
            <tr>
                <td>所在省:</td>
                <td style="width: 250px"><select id="provinceCode"/></td>

                <td>所在市:</td>
                <td style="width: 250px"><select id="cityCode"/></td>

                <td>所在区:</td>
                <td style="width: 250px"><select id="districtCode"/></td>

                <td>注册时间:</td>
                <td style="width: 250px"><input id="createTimeStart" class="easyui-datebox" editable="false"></td>

                <td>至</td>
                <td style="width: 250px"><input id="createTimeEnd" class="easyui-datebox" editable="false"/></td>
            </tr>
            <tr style="height: 1px;"></tr>
            <!-- 操作按钮 -->
            <tr>
                <td colspan="10" align="center">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="searchUser();">查询</a>
                    &nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetSearch();">重置</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 表格 -->
    <div region="center" border="false" style="lines:true">
        <table id="userHbDatagrid"></table>
    </div>
    <!-- 售卖单弹窗 -->
    <div id="orderHbsDialog"></div>
    <!-- 进货单弹窗 -->
    <div id="orderHbpDialog"></div>
</div>
<script type="text/javascript" src="/js/home/user/homeb_user.js?ver=${sv}"></script>
</body>
</html>