package com.ly.mt.mis.basic.goods.util;

import com.ly.mt.mis.base.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletResponse;

/**
 * 追踪码Excel工具类
 *
 * @author taoye
 */
public class GoodsExcelUtil extends ExcelUtil {
    /**
     * 模版下载
     */
    private final static String[] TRACKING_CODE_DOWNLOAD_TITLE = {"SKU", "69码", "父码", "子码"};

    /**
     * 导出
     *
     * @author taoye
     */
    public static void trackingCodeDownload(HttpServletResponse response) throws Exception {
        createExcel(response, "追踪码导入模版.xls");
        HSSFWorkbook wk = new HSSFWorkbook();
        CellStyle titleStyle = createGrayTitleStyle(wk);
        Sheet sheet = wk.createSheet("追踪码导入");
        int length = TRACKING_CODE_DOWNLOAD_TITLE.length;
        for (int i = 0; i < length; i++) {
            int width = 20;
            sheet.setColumnWidth(i, width * 256 + 184);
        }
        makeTitleRow(sheet, TRACKING_CODE_DOWNLOAD_TITLE, titleStyle);
        outExcel(response, wk);
    }


    /**
     * 上传校验标题行
     *
     * @author taoye
     */
    public static StringBuilder trackingCodeTitleCheck(String[] importTitle) {
        StringBuilder sb = new StringBuilder();
        // 校验标题行列数
        if (TRACKING_CODE_DOWNLOAD_TITLE.length != importTitle.length) {
            sb.append("上传文件标题行列数不正确,请重新下载导入模版!;");
            return sb;
        }
        // 校验标题行内容
        for (int i = 0; i < TRACKING_CODE_DOWNLOAD_TITLE.length; i++) {
            if (!TRACKING_CODE_DOWNLOAD_TITLE[i].equals(importTitle[i])) {
                sb.append("上传文件标题行内容不正确,请重新下载导入模版!;");
                break;
            }
        }
        return sb;
    }
}