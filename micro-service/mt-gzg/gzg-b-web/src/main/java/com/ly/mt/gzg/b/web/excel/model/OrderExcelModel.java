package com.ly.mt.gzg.b.web.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class OrderExcelModel extends BaseRowModel {
    @ExcelProperty(index = 0 , value = "商品名称")
    private String skuName;
    @ExcelProperty(index = 1 , value = "订单编号")
    private String id;
    @ExcelProperty(index = 2 , value = "金额")
    private String orderMoney;
    @ExcelProperty(index = 3 , value = "时间")
    private String createTime;
    @ExcelProperty(index = 4 , value = "支付编号")
    private String paymentNo;
    @ExcelProperty(index = 5 , value = "状态")
    private String orderStatus;


}
