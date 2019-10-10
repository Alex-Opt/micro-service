<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>货柜类型新增</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <form id="formAdd" method="post">
                <table align="center" cellspacing="20">
                    <tr>
                        <td align="right">货柜名称:</td>
                        <td>
                            <input id="cabinetNameAdd" name="cabinetName" class="easyui-textbox" data-options="{required:true}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">货柜名称缩写:</td>
                        <td>
                            <input id="cabinetCodeAdd" name="cabinetCode" class="easyui-textbox" data-options="{required:true, validType:'uppercase'}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">货柜类型:</td>
                        <td>
                            <select id="cabinetTypeAdd" name="cabinetType"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">可放商品数:</td>
                        <td>
                            <input id="goodsCountAdd" name="goodsCount" class="easyui-numberbox" data-options="{required:true}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">货道数:</td>
                        <td>
                            <input id="aisleNumberAdd" name="aisleNumber" class="easyui-numberbox" data-options="{required:true}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">舱门数:</td>
                        <td>
                            <input id="doorNumberAdd" name="doorNumber" class="easyui-numberbox" data-options="{required:true}"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div region="south" align="center" style="height: 45px;">
            <div align="center">
                <table align="center">
                    <tr style="height: 1px;"></tr>
                    <tr>
                        <td>
                            <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCategroyAdd();">保存</a>
                            &nbsp;&nbsp;
                            <a href="#" class="easyui-linkbutton" iconCls="icon-no" onclick="closeCategroyAddDialog();">取消</a>
                        </td>
                    </tr>
                    <tr style="height: 1px;"></tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/gzg/categroy/gzg_categroy_add.js?ver=${sv}"></script>
</body>
</html>