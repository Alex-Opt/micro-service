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
                                <td style="background-color: lightgrey;">订单分类</td>
                                <td style="background-color: lightgrey;">订单来源</td>
                                <td style="background-color: lightgrey;">订单状态</td>
                            </tr>
                            <tr>
                                <td style="height: 35px;">
                                    <c:choose>
                                        <c:when test="${not empty order.orderNo}">${order.orderNo}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.createTime}">${order.createTime}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>抢单订单</td>
                                <td>H5</td>
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
                                <td style="background-color: lightgrey;">优惠卷</td>
                                <td style="background-color: lightgrey;">优惠金额</td>
                                <td style="background-color: lightgrey;">运费</td>
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
                                        <c:when test="${not empty order.discountRate}">${order.discountRate}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.denomination}">${order.denomination}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.freight}">${order.freight}</c:when>
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
                <tr>
                    <td style="height: 35px;">|&nbsp;&nbsp;<strong>物流信息</strong></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="1" width="97%" style="border-collapse: collapse;text-align: center;">
                            <tr>
                                <td style="height: 35px;background-color: lightgrey;">店铺名称</td>
                                <td style="background-color: lightgrey;">配送服务</td>
                                <td style="background-color: lightgrey;">物流单号</td>
                                <td style="background-color: lightgrey;">物流状态</td>
                                <td style="background-color: lightgrey;">物流信息</td>
                                <td style="background-color: lightgrey;">骑手</td>
                            </tr>
                            <tr>
                                <td style="height: 35px;">
                                    <c:choose>
                                        <c:when test="${not empty order.shopName}">${order.shopName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    一小时达
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.logisticsNo}">${order.logisticsNo}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.logisticsStatusName}">${order.logisticsStatusName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    蜂鸟配送
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.rider}">${order.rider}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="height: 35px;">|&nbsp;&nbsp;<strong>客户信息</strong></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="1" width="97%" style="border-collapse: collapse;text-align: center;">
                            <tr>
                                <td style="height: 35px;background-color: lightgrey;">客户UID</td>
                                <td style="background-color: lightgrey;">客户注册时间</td>
                                <td style="background-color: lightgrey;">客户姓名</td>
                                <td style="background-color: lightgrey;">联系方式</td>
                                <td style="background-color: lightgrey;">客户留言</td>
                                <td style="background-color: lightgrey;">收货地址</td>
                            </tr>
                            <tr>
                                <td style="height: 35px;">
                                    <c:choose>
                                        <c:when test="${not empty order.buyerId}">${order.buyerId}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.buyerRegisterTime}">${order.buyerRegisterTime}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.buyerName}">${order.buyerName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.buyerMobile}">${order.buyerMobile}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.buyerMemo}">${order.buyerMemo}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.receivingAddress}">${order.receivingAddress}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="height: 35px;">|&nbsp;&nbsp;<strong>商家信息</strong></td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="1" width="97%" style="border-collapse: collapse;text-align: center;">
                            <tr>
                                <td style="height: 35px;background-color: lightgrey;">商家UID</td>
                                <td style="background-color: lightgrey;">商家注册时间</td>
                                <td style="background-color: lightgrey;">商家姓名</td>
                                <td style="background-color: lightgrey;">店铺名称</td>
                                <td style="background-color: lightgrey;">店铺地址</td>
                                <td style="background-color: lightgrey;">联系方式</td>
                            </tr>
                            <tr>
                                <td style="height: 35px;">
                                    <c:choose>
                                        <c:when test="${not empty order.sellerId}">${order.sellerId}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.sellerRegisterTime}">${order.sellerRegisterTime}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.sellerName}">${order.sellerName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.shopName}">${order.shopName}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.shopAddress}">${order.shopAddress}</c:when>
                                        <c:otherwise><span style="color:red;">—</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty order.sellerMobile}">${order.sellerMobile}</c:when>
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