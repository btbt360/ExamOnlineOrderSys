package com.wide.util;


	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
	 
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	 
	/**
	 * @author cg
	 * 
	 */
@SuppressWarnings("resource")
	public class ExcelPoi {
	    /**
	     * 创建XLS,XLSX
	     * @Description:
	     * @param 
	     * @return void
	     */
	    
	    public void newHSSFWorkbook(String path) {
	       
			Workbook wb = new HSSFWorkbook();
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(path);
	            wb.write(fileOut);
	            fileOut.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	 
	        System.out.println("finish.hSSF");
	    }
	 
	    
	    public void newXSSFWorkbook(String path) {
	        Workbook wb = new XSSFWorkbook();
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(path);
	            wb.write(fileOut);
	            fileOut.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("finish. xssf");
	    }
	 
	    
	    public void newSheet(String path) {
	        Workbook wb = new HSSFWorkbook();
	        // Workbook wb = new XSSFWorkbook();
	        @SuppressWarnings("unused")
			Sheet sheet1 = wb.createSheet("new sheet");
	        @SuppressWarnings("unused")
			Sheet sheet2 = wb.createSheet("second sheet");
	        String safeName = WorkbookUtil
	                .createSafeSheetName("[O'Brien's sales*?]"); // returns
	                                                                // " O'Brien's sales   "
	        @SuppressWarnings("unused")
			Sheet sheet3 = wb.createSheet(safeName);
	 
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(path);
	            wb.write(fileOut);
	            fileOut.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("finish. sheet");
	    }
	 
	    
	    //创建单元格
	    public void newCell(String path) {
	        Workbook wb = new HSSFWorkbook();
	        // Workbook wb = new XSSFWorkbook();
	        CreationHelper createHelper = wb.getCreationHelper();
	        Sheet sheet = wb.createSheet("new sheet");
	 
	        // Create a row and put some cells in it. Rows are 0 based.
	        Row row = sheet.createRow((short) 0);
	        // Create a cell and put a value in it.
	        Cell cell = row.createCell(0);
	        cell.setCellValue(1);
	 
	        // Or do it on one line.
	        row.createCell(1).setCellValue(1.2);
	        row.createCell(2).setCellValue(
	                createHelper.createRichTextString("This is a string"));
	        row.createCell(3).setCellValue(true);
	 
	        // Write the output to a file
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(path);
	            wb.write(fileOut);
	            fileOut.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	 
	        System.out.println("finish. cell");
	    }
	 
	    
	    //日期单元格
	    public void dateCells(String path) {
	        Workbook wb = new HSSFWorkbook();
	        // Workbook wb = new XSSFWorkbook();
	        CreationHelper createHelper = wb.getCreationHelper();
	        Sheet sheet = wb.createSheet("new sheet");
	 
	        // Create a row and put some cells in it. Rows are 0 based.
	        Row row = sheet.createRow(0);
	 
	        // Create a cell and put a date value in it. The first cell is not
	        // styled
	        // as a date.
	        Cell cell = row.createCell(0);
	        cell.setCellValue(new Date());
	 
	        // we style the second cell as a date (and time). It is important to
	        // create a new cell style from the workbook otherwise you can end up
	        // modifying the built in style and effecting not only this cell but
	        // other cells.
	        CellStyle cellStyle = wb.createCellStyle();
	        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
	                "m/d/yy h:mm"));
	        cell = row.createCell(1);
	        cell.setCellValue(new Date());
	        cell.setCellStyle(cellStyle);
	 
	        // you can also set date as java.util.Calendar
	        cell = row.createCell(2);
	        cell.setCellValue(Calendar.getInstance());
	        cell.setCellStyle(cellStyle);
	 
	        // Write the output to a file
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(path);
	            wb.write(fileOut);
	            fileOut.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("finish. dateCells");
	    }
	 
	    
	    //单元格样式
	    public void differentTypesOfCells(String path) {
	        Workbook wb = new HSSFWorkbook();
	        Sheet sheet = wb.createSheet("new sheet");
	        Row row = sheet.createRow((short) 2);
	        row.createCell(0).setCellValue(1.1);
	        row.createCell(1).setCellValue(new Date());
	        row.createCell(2).setCellValue(Calendar.getInstance());
	        row.createCell(3).setCellValue("a string");
	        row.createCell(4).setCellValue(true);
	        row.createCell(5).setCellType(Cell.CELL_TYPE_ERROR);
	 
	        // Write the output to a file
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(path);
	            wb.write(fileOut);
	            fileOut.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("finish. differentTypesOfCells");
	    }
	    /**
	     * 读取XLS,XLSX
	     * @Description:
	     * @param 
	     * @return void
	     * @date 2014-12-9上午11:30:03
	     */
	    
	    public void filesVsInputStreams(String readpath) {
	        // Use a file
	          try {
	            @SuppressWarnings("unused")
				Workbook wb = WorkbookFactory.create(new File(readpath));
	            // Use an InputStream, needs more memory
//	            Workbook wb = WorkbookFactory.create(new FileInputStream("d:/workbook.xls"));
	        } catch (InvalidFormatException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	          System.out.println("finish. filesVsInputStreams");
	    }
	    
	    public void hSSFWorkbookOrXSSFWorkbook(){
	        //TODO
	    } 
	    
	    public void variousAlignmentOptions(String exportpath){
	        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
	        Sheet sheet = wb.createSheet();
	        Row row = sheet.createRow((short) 2);
	        row.setHeightInPoints(30);
	 
	        createCell(wb, row, (short) 0, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_BOTTOM);
	        createCell(wb, row, (short) 1, CellStyle.ALIGN_CENTER_SELECTION, CellStyle.VERTICAL_BOTTOM);
	        createCell(wb, row, (short) 2, CellStyle.ALIGN_FILL, CellStyle.VERTICAL_CENTER);
	        createCell(wb, row, (short) 3, CellStyle.ALIGN_GENERAL, CellStyle.VERTICAL_CENTER);
	        createCell(wb, row, (short) 4, CellStyle.ALIGN_JUSTIFY, CellStyle.VERTICAL_JUSTIFY);
	        createCell(wb, row, (short) 5, CellStyle.ALIGN_LEFT, CellStyle.VERTICAL_TOP);
	        createCell(wb, row, (short) 6, CellStyle.ALIGN_RIGHT, CellStyle.VERTICAL_TOP);
	 
	        // Write the output to a file
	        FileOutputStream fileOut;
	        try {
	            fileOut = new FileOutputStream(exportpath);
	             wb.write(fileOut);
	                fileOut.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	          System.out.println("finish. variousAlignmentOptions");
	        
	    } 
	    /**
	     * Creates a cell and aligns it a certain way.
	     *
	     * @param wb     the workbook
	     * @param row    the row to create the cell in
	     * @param column the column number to create the cell in
	     * @param halign the horizontal alignment for the cell.
	     */
	    private static void createCell(Workbook wb, Row row, short column, short halign, short valign) {
	        Cell cell = row.createCell(column);
	        cell.setCellValue("Align It");
	        CellStyle cellStyle = wb.createCellStyle();
	        cellStyle.setAlignment(halign);
	        cellStyle.setVerticalAlignment(valign);
	        cell.setCellStyle(cellStyle);
	    }
	     
	    //图片
	    
	    public void pictureCell(String path) {
	        //create a new workbook
	        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
	 
	        //add picture data to this workbook.
	        InputStream is =null;
	        try {
	            is = new FileInputStream(path);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        byte[] bytes = null;
	        try {
	            bytes = IOUtils.toByteArray(is);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
	        try {
	            is.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	 
	        CreationHelper helper = wb.getCreationHelper();
	 
	        //create sheet
	        Sheet sheet = wb.createSheet();
	         
	        Row row = sheet.createRow((short) 0);
	        // Create a cell and put a value in it.
	        Cell cell = row.createCell(0);
	        cell.setCellValue(1);
	 
	        // Or do it on one line.
	        row.createCell(1).setCellValue(1.2);
	        row.createCell(2).setCellValue("This is a string");
	        row.createCell(3).setCellValue(true);
	 
	        // Create the drawing patriarch.  This is the top level container for all shapes. 
	        Drawing drawing = sheet.createDrawingPatriarch();
	 
	        //add a picture shape
	        ClientAnchor anchor = helper.createClientAnchor();
	        //set top-left corner of the picture,
	        //subsequent call of Picture#resize() will operate relative to it
	        anchor.setCol1(3);
	        anchor.setRow1(2);
	        Picture pict = drawing.createPicture(anchor, pictureIdx);
	 
	        //auto-size picture relative to its top-left corner
	        pict.resize();
	 
	        //save workbook
	        String file = "d:/workbook.xls";
	        if(wb instanceof XSSFWorkbook) file += "x";
	        FileOutputStream fileOut = null;
	        try {
	            fileOut = new FileOutputStream(file);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        try {
	            wb.write(fileOut);
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        try {
	            fileOut.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        System.out.println("执行完毕。");
	    }
}
