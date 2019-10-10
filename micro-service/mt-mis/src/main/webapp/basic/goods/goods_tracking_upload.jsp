<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>追踪码导入</title>
</head>
<body>
<div class="easyui-layout" fit="true" modal="true">
    <div region="center" border="false" style="height:108px;background:#fafafa;">
        <p style="font-size:15px;font-weight:bold;">模版下载：</p>
        <div class="easyui-panel" style="width:100%;" align="center">
            <table align="center" style="width:90%;">
                <tr></tr>
                <tr>
                    <td colspan="12" align="center">
                        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-down'" style="width:10%" onclick="trackingCodeDownload();">下载</a>
                    </td>
                </tr>
                <tr></tr>
            </table>
        </div>
        <p style="font-size:15px;font-weight:bold;">追踪码导入：</p>
        <div class="easyui-panel" style="width:100%;" align="center">
            <div style="width:99%;">
                <p style="font-size:15px;"></p>
                <form id="trackingCodeUpload" method="post" enctype="multipart/form-data">
                    <input class="easyui-filebox" id="trackingCodeFile" name="file" data-options="prompt:'请选择xls/xlsx文件。。。',buttonText:'&nbsp;选&nbsp;择&nbsp;',accept:'application/vnd.ms-excel'" style="width:100%">
                </form>
            </div>
            <div>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-top'" style="width:10%" onclick="trackingCodeUpload();">上传</a>
            </div>
            <br/>
        </div>
        <!-- 提示信息 -->
        <div id="msgInfo" style="display: none" align="center">
            <p id="errorMsg" style="font-size:15px;font-weight:bold;"></p>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/basic/goods/goods_tracking_upload.js?ver=${sv}"></script>
</body>
</html>