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
			//1������������
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1�������ϲ���Ԫ�����
			CellRangeAddress rangeAddress = new CellRangeAddress(0,0,0,4);
			//1.2��ͷ������ʽ
			HSSFCellStyle titleStyle = setCellStyle(workbook, (short)16);
			//1.3���б�����ʽ
			//
			HSSFCellStyle columStyle = setCellStyle(workbook, (short)13);
			
			//2������������
			HSSFSheet sheet = workbook.createSheet("�û��б�");
			//2.1�����غϲ���Ԫ�����
			sheet.addMergedRegion(rangeAddress);
			//����Ĭ���п�
			sheet.setDefaultColumnWidth(20);
			//3��������
			//3.1������ͷ�����У���������ͷ����
			HSSFCell titleCell = sheet.createRow(0).createCell(0);
			titleCell.setCellStyle(titleStyle);
			titleCell.setCellValue("�û��б�");
		
			//3.2�������б����У����������б���	
			HSSFRow row1 = sheet.createRow(1);
			String[] colums = {"�û���","�˺�","��������","�Ա�","��������"};
			int nums= colums.length;
			for (int j = 0; j < nums; j++) {
				
				HSSFCell cell = row1.createCell(j);
				cell.setCellValue(colums[j]);
				cell.setCellStyle(columStyle);
			}
			//4��������Ԫ�񣻽��û��б�д��excel
			//�������ݰ�������ʽ
			HSSFCellStyle dataBindStyle = setCellStyle(workbook,(short) 10);
			int length = userList.size();
			//���������
			if(length>0){
				
				for (int i =0 ;i<length;i++) {
					//�����µ�һ��
					HSSFRow row2 = sheet.createRow(i+2);
					//������һ�еĵ�1��
					HSSFCell cell = row2.createCell(0);
					cell.setCellValue(userList.get(i).getName());
					cell.setCellStyle(dataBindStyle);
					//������һ�еĵ�2��
					HSSFCell cell1 = row2.createCell(1);
					cell1.setCellValue(userList.get(i).getAccount());
					cell1.setCellStyle(dataBindStyle);
					//������һ�еĵ�3��
					HSSFCell cell2= row2.createCell(2);
					cell2.setCellValue(userList.get(i).getDept());
					cell2.setCellStyle(dataBindStyle);
					//������һ�еĵ�4��
					HSSFCell cell3 = row2.createCell(3);
					cell3.setCellValue(userList.get(i).isGender()?"��":"Ů");
					cell3.setCellStyle(dataBindStyle);
					//������һ�еĵ�5��
					HSSFCell cell4 = row2.createCell(4);
					cell4.setCellValue(userList.get(i).getEmail());
					cell4.setCellStyle(dataBindStyle);
					
				}
			}
		
			//5�����
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
