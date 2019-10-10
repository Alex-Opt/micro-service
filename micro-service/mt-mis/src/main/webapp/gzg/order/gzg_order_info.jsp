<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单详情</title>
</head>
<body>
<div class="easyui-panel" fit="true" modal="true">
    <div class="easyui-layout" fit="true" modal="true">
        <div region="center">
            <table align="center" width="95%">
                <tr>
                    <td style="height: 35px;">|&nbsp;&nbsp;<strong>基本信息</strong></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="1" width="97%" style="border-collapse: collapse;text-align: center;">
                            <tr>
                                <td style="height: 35px;background-color: lightgrey;">订单编号</td>
                                <td style="background-color: lightgrey;">下单时间</td>
                                <td style="background-color: lightgrey;">订单状态</td>
                            </tr>
                            <tr>
                                <td style="height: 35px;">
                                    <c:choose>
                                        <c:when test="${not empty order.id}">${order.id}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.createTime}">${order.createTime}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.orderStatusName}">${order.orderStatusName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="height: 35px;">|&nbsp;&nbsp;<strong>商品信息</strong></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="1" width="97%" style="border-collapse: collapse;text-align: center;">
                            <tr>
                                <td style="height: 35px;background-color: lightgrey;">商品SPU名称</td>
                                <td style="background-color: lightgrey;">商品SKU</td>
                                <td style="background-color: lightgrey;">商品名称</td>
                                <td style="background-color: lightgrey;">舱门编号</td>
                                <td style="background-color: lightgrey;">追踪码</td>
                                <td style="background-color: lightgrey;">数量</td>
                                <td style="background-color: lightgrey;">原始金额</td>
                                <td style="background-color: lightgrey;">实付金额</td>
                            </tr>
                            <c:forEach items="${order.goods}" var="g">
                                <tr>
                                    <td style="height: 35px;">
                                        <c:choose>
                                            <c:when test="${not empty g.spuName}">${g.spuName}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty g.skuId}">${g.skuId}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty g.skuName}">${g.skuName}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty g.cabinetNo}">${g.cabinetNo}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty g.code}">${g.code}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty g.skuNum}">${g.skuNum}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty g.oldMoney}">${g.oldMoney}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty g.paymentPrice}">${g.paymentPrice}</c:when>
                                            <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="height: 35px;">|&nbsp;&nbsp;<strong>格子柜信息</strong></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="1" width="97%" style="border-collapse: collapse;text-align: center;">
                            <tr>
                                <td style="height: 35px;background-color: lightgrey;">设备ID</td>
                                <td style="background-color: lightgrey;">门店名称</td>
                                <td style="background-color: lightgrey;">门店地址</td>
                                <td style="background-color: lightgrey;">商务名称</td>
                                <td style="background-color: lightgrey;">商务手机号</td>
                                <td style="background-color: lightgrey;">管理人手机号</td>
                                <td style="background-color: lightgrey;">配货方案</td>
                            </tr>
                            <tr>
                                <td style="height: 35px;">
                                    <c:choose>
                                        <c:when test="${not empty order.code}">${order.code}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.name}">${order.name}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.address}">${order.address}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.bdName}">${order.bdName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.bdMobile}">${order.bdMobile}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.hotelAdminMobile}">${order.hotelAdminMobile}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.planName}">${order.planName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="height: 35px;">|&nbsp;&nbsp;<strong>支付信息</strong></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="1" width="97%" style="border-collapse: collapse;text-align: center;">
                            <tr>
                                <td style="height: 35px;background-color: lightgrey;">支付方式</td>
                                <td style="background-color: lightgrey;">支付时间</td>
                                <td style="background-color: lightgrey;">支付参考号</td>
                                <td style="background-color: lightgrey;">原始金额</td>
                                <td style="background-color: lightgrey;">实际支付金额</td>
                            </tr>
                            <tr>
                                <td style="height: 35px;">
                                    <c:choose>
                                        <c:when test="${not empty order.paymentTypeName}">${order.paymentTypeName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.payTime}">${order.payTime}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.paymentNo}">${order.paymentNo}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.oldMoney}">${order.oldMoney}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.payMoney}">${order.payMoney}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>