package com.ly.mt.gzg.b.web.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class HotelStockExcelModel extends BaseRowModel {
    @ExcelProperty(index = 1 , value = "SKU码")
    private Long skuId;
    @ExcelProperty(index = 0 , value = "商品名称")
    private String skuName;

    private String skuCode;

    @ExcelProperty(index = 2 , value = "总数")
    private Integer totalNum;
    @ExcelProperty(index = 3 , value = "已出售")
    private Integer sellOutNum;
    @ExcelProperty(index = 4 , value = "已退货")
    private Integer returnNum;
    @ExcelProperty(index = 5 , value = "剩余")
    private Integer surplusNum;
}
