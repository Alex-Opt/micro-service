package com.ly.mt.mis.home.order.util;

import com.ly.mt.mis.base.util.ExcelUtil;
import com.ly.mt.mis.home.order.vo.HomecOrderDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderBuyDatagridVo;
import com.ly.mt.mis.home.order.vo.HomebOrderSellDatagridVo;
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
public class HomeOrderExcelUtil extends ExcelUtil {
    // MOTI商家端-订单管理-进货订单导出
    /**
     * 标题
     */
    private final static String[] EXPORT_ORDER_HBP_TITLE = {
            "订单编号", "商家UID", "注册时间", "店铺名称", "下单时间", "商品信息", "支付方式", "订单状态", "实付金额"};
    /**
     * 字段
     */
    private final static String[] EXPORT_ORDER_HBP_CONTENT = {
            "id", "userId", "userRegisterTime", "shopName", "createTime", "goodsNames", "paymentTypeName", "orderStatusName", "actualAmount"};
    /**
     * 需要数字做文本处理的字段
     */
    private final static String EXPORT_ORDER_HBP_FILTER_NAMES = "id,userId";

    /**
     * 导出
     */
    public static void exportOrderHbp(HttpServletResponse response, List<HomebOrderBuyDatagridVo> list) throws Exception {
        createExcel(response, "进货订单.xls");
        HSSFWorkbook wk = new HSSFWorkbook();
        CellStyle titleStyle = createGrayTitleStyle(wk);
        int size = list.size();
        if (size <= 0) {
            wk.createSheet("导出条件没有匹配的进货订单");
        } else {
            CellStyle contentStyle = createContentStyle(wk);
            Sheet sheet = wk.createSheet("进货订单");
            int length = EXPORT_ORDER_HBP_TITLE.length;
            for (int i = 0; i < length; i++) {
                int width = 20;
                if (i == 3 || i == 5) {
                    width = 40;
                }
                sheet.setColumnWidth(i, width * 256 + 184);
            }
            makeTitleRow(sheet, EXPORT_ORDER_HBP_TITLE, titleStyle);
            makeContentRow(sheet, contentStyle, EXPORT_ORDER_HBP_CONTENT, list, EXPORT_ORDER_HBP_FILTER_NAMES);
        }
        outExcel(response, wk);
    }


    // MOTI商家端-订单管理-售卖订单导出
    /**
     * 标题
     */
    private final static String[] EXPORT_ORDER_HBS_TITLE = {
            "订单编号", "用户UID", "下单时间", "商品信息", "订单分类", "配送服务", "订单来源", "支付方式", "订单状态", "店铺名称", "实付金额"};
    /**
     * 字段
     */
    private final static String[] EXPORT_ORDER_HBS_CONTENT = {
            "id", "buyerId", "createTime", "goodsNames", "orderTypeName", "distributionName", "orderSourceName", "paymentTypeName", "orderStatusName", "shopName", "orderMoney"};
    /**
     * 需要数字做文本处理的字段
     */
    private final static String EXPORT_ORDER_HBS_FILTER_NAMES = "id,buyerId";

    /**
     * 导出
     */
    public static void exportOrderHbs(HttpServletResponse response, List<HomebOrderSellDatagridVo> list) throws Exception {
        createExcel(response, "售卖订单.xls");
        HSSFWorkbook wk = new HSSFWorkbook();
        CellStyle titleStyle = createGrayTitleStyle(wk);
        int size = list.size();
        if (size <= 0) {
            wk.createSheet("导出条件没有匹配的售卖订单");
        } else {
            CellStyle contentStyle = createContentStyle(wk);
            Sheet sheet = wk.createSheet("售卖订单");
            int length = EXPORT_ORDER_HBS_TITLE.length;
            for (int i = 0; i < length; i++) {
                int width = 20;
                if (i == 3 || i == 9) {
                    width = 40;
                }
                sheet.setColumnWidth(i, width * 256 + 184);
            }
            makeTitleRow(sheet, EXPORT_ORDER_HBS_TITLE, titleStyle);
            makeContentRow(sheet, contentStyle, EXPORT_ORDER_HBS_CONTENT, list, EXPORT_ORDER_HBS_FILTER_NAMES);
        }
        outExcel(response, wk);
    }


    // MOTI到家-订单管理-订单导出
    /**
     * 标题
     */
    private final static String[] EXPORT_ORDER_HC_TITLE = {
            "订单编号", "客户UID", "下单时间", "商品信息", "订单分类", "配送服务", "支付方式", "订单状态", "店铺名称", "实付金额"};
    /**
     * 字段
     */
    private final static String[] EXPORT_ORDER_HC_CONTENT = {
            "orderNo", "buyerId", "createTime", "goodsNames", "orderTypeName", "distributionName", "paymentTypeName", "orderStatusName", "shopName", "orderMoney"};
    /**
     * 需要数字做文本处理的字段
     */
    private final static String EXPORT_ORDER_HC_FILTER_NAMES = "orderNo,buyerId";

    /**
     * 导出
     */
    public static void exportOrderHc(HttpServletResponse response, List<HomecOrderDatagridVo> list) throws Exception {
        createExcel(response, "订单.xls");
        HSSFWorkbook wk = new HSSFWorkbook();
        CellStyle titleStyle = createGrayTitleStyle(wk);
        int size = list.size();
        if (size <= 0) {
            wk.createSheet("导出条件没有匹配的订单");
        } else {
            CellStyle contentStyle = createContentStyle(wk);
            Sheet sheet = wk.createSheet("订单");
            int length = EXPORT_ORDER_HC_TITLE.length;
            for (int i = 0; i < length; i++) {
                int width = 20;
                if (i == 3) {
                    width = 40;
                }
                sheet.setColumnWidth(i, width * 256 + 184);
            }
            makeTitleRow(sheet, EXPORT_ORDER_HC_TITLE, titleStyle);
            makeContentRow(sheet, contentStyle, EXPORT_ORDER_HC_CONTENT, list, EXPORT_ORDER_HC_FILTER_NAMES);
        }
        outExcel(response, wk);
    }
}