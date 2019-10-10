<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<div region="north" border="false">
    <table id="table" cellspacing="5" width="100%">
        <tr>
            <td width="180px;">北京焱炎科技|MIS系统</td>
            <td><input type="hidden" id="firstModuleId" value="${module.id}"/></td>
            <td><input type="hidden" id="firstModuleCode" value="${module.code}"/></td>
            <c:forEach items="${modules}" var="m">
                <td width="150px">
                    <input id="${m.code}" type="button" class="top-menu" value="${m.name}" onclick="topMenuToggle('${m.code}', '${m.id}');"/>
                </td>
            </c:forEach>
            <td style="text-align: right">
                欢迎您，亲爱的${sessionScope.userName}！
                <a href="javascript:openPwd();">修改密码</a>
                |
                <a href="javascript:loginOut('${sessionScope.loginName}');">安全退出</a>
            </td>
        </tr>
    </table>
</div>