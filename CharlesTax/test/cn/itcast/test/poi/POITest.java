package cn.itcast.test.poi;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
@SuppressWarnings("all")
public class POITest {
	
	@Test
	public void testWriter2003() throws Exception {
		
		//创建工作簿

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("test");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("ID");
		workbook.write(new FileOutputStream("D:/test.xls"));
		
	}
	@Test
	public void testWriter2007() throws Exception {
		
		//创建工作簿
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("test1");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("ID");
		workbook.write(new FileOutputStream("D:/test2007.xlsx"));
		
	}
	@Test
	public void testReader2003() throws Exception {
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("D:/test.xls"));
		HSSFSheet sheet = workbook.getSheet("test");
		HSSFCell cell = sheet.getRow(0).getCell(0);
		System.out.println(cell.getStringCellValue());
	}
	@Test
	public void testReader2007() throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("D:/test2007.xlsx"));
		XSSFSheet sheet = workbook.getSheet("test1");
		XSSFCell cell = sheet.getRow(0).getCell(0); 
		System.out.println(cell.getStringCellValue());
	}
	@Test
	public void testCellAdd() throws Exception {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
	
		CellRangeAddress  cra = new CellRangeAddress(0, 0, 0, 3);
		XSSFCellStyle style = workbook.createCellStyle();
		//水平居中
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);
		//创建字体样式 
		XSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    font.setFontHeightInPoints((short) 24);
	        //设定字体样式
	        style.setFont(font);
		XSSFSheet sheet = workbook.createSheet("add");
		sheet.addMergedRegion(cra);
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		workbook.write(new FileOutputStream("D:/add.xlsx"));
	}
}
