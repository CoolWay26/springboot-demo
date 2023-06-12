package com.coolway.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zhuw
 * @date 2023-6-11
 */
public class PoiUtils {

    /**
     * 发送响应流方法
     */
    public static void setResponse4Excel(HttpServletResponse response, String fileName, HSSFWorkbook wb) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            wb.write(response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     *
     * @param sheetName 标题
     * @param headers  表头
     * @param dataList  表中元素
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbookWithDatas(String sheetName, String title, String[] headers, String[] colNameArr, List<?> dataList) throws NoSuchFieldException, IllegalAccessException {
        //创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetName);

        //创建标题合并行,addMergedRegion(firstRow, lastRow, firstCol, lastCol)是设置单元格合并
        hssfSheet.addMergedRegion(new CellRangeAddress(0, 0,0, headers.length - 1));

        //设置标题样式
        HSSFCellStyle titleStyle = hssfWorkbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);   //设置居中样式
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置标题字体
        Font titleFont = hssfWorkbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);
        titleStyle.setFont(titleFont);

        //设置值表头样式 设置表头居中
        HSSFCellStyle headerCellStyle = hssfWorkbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);   //设置居中样式
        headerCellStyle.setBorderBottom(BorderStyle.THIN);          //边框
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        //设置表头字体
        Font headerFont = hssfWorkbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerCellStyle.setFont(headerFont);

        //设置表内容样式
        //创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        //产生标题行，第0行
        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell titleCell = hssfRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(titleStyle);



        //产生表头，第1行
        hssfRow = hssfSheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell headerCell = hssfRow.createCell(i);
            headerCell.setCellValue(headers[i]);
            headerCell.setCellStyle(headerCellStyle);
        }

        //创建内容，第2行开始，每一条数据生成一行
        int rowNum = 2;
        for (Object data : dataList) {
            hssfRow = hssfSheet.createRow(rowNum);
            setObject2Cell(colNameArr, hssfRow, data, cellStyle);
            rowNum++;
        }

        return hssfWorkbook;
    }

    private static void setObject2Cell(String[] colNameArr, HSSFRow hssfRow, Object data, HSSFCellStyle cellStyle) throws NoSuchFieldException, IllegalAccessException {
        //通过反射获取data的字段，只获取本身的属性，不获取父类
        Class<?> dataClass = data.getClass();

        //写入当前行
        int colNum = 0;
        for (String colName : colNameArr) {
            Field declaredField = dataClass.getDeclaredField(colName);
            declaredField.setAccessible(true);
            HSSFCell cell = hssfRow.createCell(colNum);
            //返回传入对象的该属性值
            cell.setCellValue(declaredField.get(data).toString());
            cell.setCellStyle(cellStyle);
            colNum++;
        }
    }



}
