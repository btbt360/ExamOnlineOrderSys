package com.wide.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportController {
	/**
     * 导出XLS数据公共方法
     * 方法根据xlsType的值自动触发是否是导出方法
     * @Description:
     * @param @param dataList 待导出的集合
     * @param @param fileName 文件名称，不包括后缀
     * @param @param columNameIndexMapping 导出字段名称与表列名称的对应关系（支持按序导出）
     * @param @param xlsType 文件格式 1--2003版，2--2007版 
     * @return void
     * @date 2015-7-30下午2:00:05
     */
    public static void exportXLSRecord(List<List<String>> dataList,String fileName,String[] tabletitle,String xlsType,
    		HttpServletResponse getHttpResponse){
        if(null != dataList && dataList.size() > 0){
            //导出类型
            try {
                OutputStream outputStream = getHttpResponse.getOutputStream();
                @SuppressWarnings("resource")
				Workbook wb = new HSSFWorkbook();
                String exportFileName = fileName + ".xls";//导出的文件名称
                if (xlsType.equals("2")) {
                    wb = new XSSFWorkbook();
                    exportFileName = fileName + ".xlsx";// xlsx 2007
                }
                exportFileName = new String(exportFileName.getBytes("utf-8"),"ISO8859-1");
                getHttpResponse.reset();// 清空输出流
                getHttpResponse.setHeader("Content-Disposition",
                        "attachment; filename=" + exportFileName);// 设定输出文件头
                getHttpResponse.setContentType("application/vnd.ms-excel;charset=ISO8859-1");// 定义输出类型
                 
                /**
                 *传入空的工作表对象（兼容2003/2007）,导出的文件名称，数据集合,返回已完成数据拼接的工作表，等待数据写出
                 * public Workbook getWorkbookObj(Workbook wb,String exportFileName,List<Map<String, Object>> dataList,Map<String,String> columNameIndexMapping){
                 * }
                 * 方法可分离
                 */
                
                //XLS标题头样式 header style
                Font headFont = wb.createFont();
                headFont.setFontHeightInPoints((short) 12);// 大小
                headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
                headFont.setFontName("黑体");
 
                CellStyle headStyle = wb.createCellStyle();
 
                headStyle.setFont(headFont);
 
                headStyle.setAlignment(CellStyle.ALIGN_CENTER);// 设置单元格水平方向对其方式
                headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 设置单元格垂直方向对其方式
 
                headStyle.setBorderTop(CellStyle.BORDER_THIN);
                headStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
                headStyle.setBorderLeft(CellStyle.BORDER_THIN); // 左边边框
                headStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边边框颜色
                headStyle.setBorderRight(CellStyle.BORDER_THIN); // 右边边框
                headStyle.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边边框颜色
                headStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
                headStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框颜色
 
                headStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND); // 前景色
 
                headStyle.setWrapText(true);// 自动换行
 
                //XLS内容样式  body style
                CellStyle bodyStyle = wb.createCellStyle();
                bodyStyle.setAlignment(CellStyle.ALIGN_CENTER);
                bodyStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
                bodyStyle.setBorderLeft(CellStyle.BORDER_THIN); // 左边边框
                bodyStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边边框颜色
                bodyStyle.setBorderRight(CellStyle.BORDER_THIN); // 右边边框
                bodyStyle.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边边框颜色
                bodyStyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
                bodyStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框颜色
                bodyStyle.setWrapText(true);// 自动换行
                  
                //创建sheet
                Sheet sheet = wb.createSheet(exportFileName);
                //查询记录信息
                //生成XLS sheet头
                Row sheetHeadRow = sheet.createRow(0);// 头标题
                sheetHeadRow.setHeight((short) 400);// 高度
                for (int i = 0; i < tabletitle.length; i++) {
                    Cell cell = sheetHeadRow.createCell(i);
                    cell.setCellValue(tabletitle[i].toString());
                    cell.setCellStyle(headStyle);
                    // 设置列宽
                    sheet.setColumnWidth(i, 5000);
                }
                //生成XLS sheet body 数据行
                for (int i = 0; i < dataList.size(); i++) {
                    Row sheetRow = sheet.createRow(i + 1);// sheet页数据行
                    List<String> dataRow = dataList.get(i);// 数据库数据行
                    for (int j = 0; j < tabletitle.length; j++) {
                        Cell cell = sheetRow.createCell(j);
                            Object dataValue = dataRow.get(j);
                            if (null != dataValue) {
                                cell.setCellValue(dataValue.toString());
                                cell.setCellStyle(bodyStyle);
                            }else{
                                //没有内容时也能生成XLS的边框格子美观些
                                cell.setCellValue("");
                                cell.setCellStyle(bodyStyle);
                            }
                    }
                }
                /**
                 * 分离end
                 */
                 
                wb.write(outputStream);
                outputStream.close(); // 关闭流
                System.out.println("导出完成");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }   
        }
    }
}
