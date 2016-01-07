package cn.itcast.core.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.itcast.nsfw.user.entity.User;

public class ExcelUtils {
	
	public static void  exportExcel(List<User> userList, ServletOutputStream outputStream) {
		
		try {
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress rangeAddress = new CellRangeAddress(0,0,0,4);
			//1.2、头标题样式
			HSSFCellStyle titleStyle = setCellStyle(workbook, (short)16);
			//1.3、列标题样式
			//
			HSSFCellStyle columStyle = setCellStyle(workbook, (short)13);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			//2.1、加载合并单元格对象
			sheet.addMergedRegion(rangeAddress);
			//设置默认列宽
			sheet.setDefaultColumnWidth(20);
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFCell titleCell = sheet.createRow(0).createCell(0);
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("用户列表");
		
			//3.2、创建列标题行；并且设置列标题	
			HSSFRow row1 = sheet.createRow(1);
			String[] colums = {"用户名","账号","所属部门","性别","电子邮箱"};
			int nums= colums.length;
			for (int j = 0; j < nums; j++) {
				
				HSSFCell cell = row1.createCell(j);
				cell.setCellValue(colums[j]);
				cell.setCellStyle(columStyle);
			}
			//4、操作单元格；将用户列表写入excel
			//设置数据绑定练得样式
			HSSFCellStyle dataBindStyle = setCellStyle(workbook,(short) 10);
			int length = userList.size();
			//如果有数据
			if(length>0){
				
				for (int i =0 ;i<length;i++) {
					//创建新的一行
					HSSFRow row2 = sheet.createRow(i+2);
					//创建第一行的第1列
					HSSFCell cell = row2.createCell(0);
					cell.setCellValue(userList.get(i).getName());
					cell.setCellStyle(dataBindStyle);
					//创建第一行的第2列
					HSSFCell cell1 = row2.createCell(1);
					cell1.setCellValue(userList.get(i).getAccount());
					cell1.setCellStyle(dataBindStyle);
					//创建第一行的第3列
					HSSFCell cell2= row2.createCell(2);
					cell2.setCellValue(userList.get(i).getDept());
					cell2.setCellStyle(dataBindStyle);
					//创建第一行的第4列
					HSSFCell cell3 = row2.createCell(3);
					cell3.setCellValue(userList.get(i).isGender()?"男":"女");
					cell3.setCellStyle(dataBindStyle);
					//创建第一行的第5列
					HSSFCell cell4 = row2.createCell(4);
					cell4.setCellValue(userList.get(i).getEmail());
					cell4.setCellStyle(dataBindStyle);
					
				}
			}
		
			//5、输出
			//
		
			workbook.write(outputStream);
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static HSSFCellStyle setCellStyle(HSSFWorkbook workbook, short fontsize) {
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontsize);
		style.setFont(font);
		return style;
	}
}
