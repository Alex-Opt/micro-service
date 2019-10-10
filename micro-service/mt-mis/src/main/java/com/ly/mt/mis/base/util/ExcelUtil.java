package com.ly.mt.mis.base.util;

import com.ly.mt.core.base.util.StringUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel导出工具类
 *
 * @author taoye
 */
public class ExcelUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 导出文件名
     *
     * @author taoye
     */
    public static void createExcel(HttpServletResponse response, String name) throws Exception {
        name = new String(name.getBytes("GB2312"), "ISO_8859_1");
        // 文件头信息
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
    }

    /**
     * 创建无背景色标题样式
     *
     * @author taoye
     */
    public static CellStyle createTitleStyle(HSSFWorkbook wk) {
        return createStyle(wk);
    }

    /**
     * 创建灰色背景标题样式
     *
     * @author taoye
     */
    public static CellStyle createGrayTitleStyle(HSSFWorkbook wk) {
        CellStyle style = createStyle(wk);
        // 前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 前景填充颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        return style;
    }

    /**
     * 创建标题样式、字体
     *
     * @author taoye
     * @Date 2018/10/24
     */
    private static CellStyle createStyle(HSSFWorkbook wk) {
        CellStyle style = wk.createCellStyle();
        // 边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        style.setWrapText(true);
        // 字体
        Font font = wk.createFont();
        font.setFontName("宋体");
        // 字体大小
        font.setFontHeightInPoints((short) 12);
        // 加粗
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 创建内容样式
     *
     * @author taoye
     */
    public static CellStyle createContentStyle(HSSFWorkbook wk) {
        CellStyle style = wk.createCellStyle();
        // 边框
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        // 水平左对齐
        style.setAlignment(HorizontalAlignment.LEFT);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        style.setWrapText(true);
        // 字体
        Font font = wk.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        return style;
    }

    /**
     * 设置统一列宽
     *
     * @author taoye
     */
    public static void setWidth(Sheet sheet, int lenght, int width) {
        for (int i = 0; i < lenght; i++) {
            sheet.setColumnWidth(i, width * 256 + 184);
        }
    }

    /**
     * 生成标题行
     *
     * @author taoye
     */
    public static void makeTitleRow(Sheet sheet, String[] headers, CellStyle style) {
        Row row = sheet.createRow(0);
        row.setHeightInPoints(35);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            RichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(style);
        }
    }

    /**
     * 生成内容行
     *
     * @author taoye
     */
    public static void makeContentRow(Sheet sheet, CellStyle style, String[] columns, List<?> list, String filterNames) throws Exception {
        int index = 1;
        int count = columns.length;
        for (Object object : list) {
            Row row = sheet.createRow(index);
            row.setHeightInPoints(25);
            for (int i = 0; i < count; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style);
                String fieldName = columns[i];
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method getMethod = object.getClass().getMethod(getMethodName, new Class[]{});
                String textValue = (String) getMethod.invoke(object, new Object[]{});
                setCellValue(cell, textValue, fieldName, filterNames);
            }
            index++;
        }
    }

    /**
     * 单元格赋值
     *
     * @author taoye
     */
    public static void setCellValue(Cell cell, String value, String fieldName, String filterNames) {
        try {
            if (StringUtil.isEmpty(value)) {
                return;
            }
            // 特殊字段数字按文本处理
            if (filterNames.contains(fieldName)) {
                RichTextString richString = new HSSFRichTextString(value);
                cell.setCellValue(richString);
                return;
            }
            setCellValue(cell, value);
        } catch (Exception e) {
            LOGGER.error("ExcelUtil.setCellValue feildName={} error ", fieldName, e);
        }
    }

    /**
     * 单元格赋值
     *
     * @author taoye
     */
    public static void setCellValue(Cell cell, String value) {
        try {
            if (StringUtils.isEmpty(value)) {
                return;
            }
            RichTextString richString = new HSSFRichTextString(value);
            // 数字处理
            if (StringUtil.isNumber(value)) {
                cell.setCellValue(Double.parseDouble(value));
                return;
            }
            cell.setCellValue(richString);
        } catch (Exception e) {
            LOGGER.error("ExcelUtil.setCellValue value={} error ", value, e);
        }
    }

    /**
     * 添加数据有效性验证 默认从第一行起到第五百行止
     *
     * @author taoye
     */

    public static void setValidationData(Sheet sheet, int columnNumber, String[] values) {
        try {
            CellRangeAddressList addressList = new CellRangeAddressList(1, 500, columnNumber, columnNumber);
            DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(values);
            DataValidation validation = new HSSFDataValidation(addressList, dvConstraint);
            sheet.addValidationData(validation);
        } catch (Exception e) {
            LOGGER.error("ExcelUtil.setValidationData error ", e);
        }
    }


    /**
     * 读取excel内容
     *
     * @author taoye
     */
    public static List<String[]> getExcelData(MultipartFile file) throws Exception {
        List<String[]> dataList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        Workbook wb;
        try {
            wb = new HSSFWorkbook(inputStream);
        } catch (Exception ex) {
            wb = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = wb.getSheetAt(0);
        int columnNum = 0;
        if (sheet.getRow(0) != null) {
            columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
        }
        if (columnNum > 0) {
            for (Row row : sheet) {
                String[] singleRow = new String[columnNum];
                for (int i = 0; i < columnNum; i++) {
                    Cell cell = row.getCell(i);
                    if (null == cell) {
                        singleRow[i] = "";
                        continue;
                    }
                    Object object = getValue(cell);
                    singleRow[i] = String.valueOf(object);
                }
                dataList.add(singleRow);
            }
        }
        IOUtils.closeQuietly(wb);
        IOUtils.closeQuietly(inputStream);
        return dataList;
    }

    /**
     * 从单元格获取值
     *
     * @author taoye
     */
    private static Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellType()) {
            case BOOLEAN:
                obj = cell.getBooleanCellValue();
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    obj = com.ly.mt.core.base.util.DateUtil.date2DateStr(date);
                } else {
                    obj = cell.getNumericCellValue();
                }
                break;
            case STRING:
                obj = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return obj;
    }

    /**
     * 输出excel
     *
     * @author taoye
     */
    public static void outExcel(HttpServletResponse response, HSSFWorkbook wk) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            wk.write(os);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(wk);
            IOUtils.closeQuietly(os);
        }
    }
}