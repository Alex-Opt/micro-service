package com.ly.mt.mis.gzg.order.util;

import com.ly.mt.mis.base.util.ExcelUtil;
import com.ly.mt.mis.gzg.order.vo.GzgOrderDatagridVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单Excel工具类
 *
 * @author taoye
 */
public class GzgOrderExcelUtil extends ExcelUtil {
    // MOTI售卖柜-订单管理-订单导出
    /**
     * 标题
     */
    private final static String[] EXPORT_ORDER_GZG_TITLE = {
            "订单编号", "下单时间", "订单来源", "支付方式", "订单状态", "门店名称", "实付金额"};
    /**
     * 字段
     */
    private final static String[] EXPORT_ORDER_GZG_CONTENT = {
            "id", "createTime", "orderSourceName", "paymentTypeName", "orderStatusName", "hotelName", "orderMoney"};
    /**
     * 需要数字做文本处理的字段
     */
    private final static String EXPORT_ORDER_GZG_FILTER_NAMES = "id";

    /**
     * 导出
     */
    public static void exportOrderGzg(HttpServletResponse response, List<GzgOrderDatagridVo> list) throws Exception {
        createExcel(response, "订单.xls");
        HSSFWorkbook wk = new HSSFWorkbook();
        CellStyle titleStyle = createGrayTitleStyle(wk);
        int size = list.size();
        if (size <= 0) {
            wk.createSheet("导出条件没有匹配的订单");
        } else {
            CellStyle contentStyle = createContentStyle(wk);
            Sheet sheet = wk.createSheet("订单");
            int length = EXPORT_ORDER_GZG_TITLE.length;
            for (int i = 0; i < length; i++) {
                int width = 20;
                if (i == 5) {
                    width = 40;
                }
                sheet.setColumnWidth(i, width * 256 + 184);
            }
            makeTitleRow(sheet, EXPORT_ORDER_GZG_TITLE, titleStyle);
            makeContentRow(sheet, contentStyle, EXPORT_ORDER_GZG_CONTENT, list, EXPORT_ORDER_GZG_FILTER_NAMES);
        }
        outExcel(response, wk);
    }
}