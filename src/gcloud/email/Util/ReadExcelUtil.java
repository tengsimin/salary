package gcloud.email.Util;

import gcloud.email.entity.UserEmail;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jxcell.CellFormat;
import com.jxcell.View;

public class ReadExcelUtil {
	public static void writeFile(List<UserEmail> list, String ExcelName, String month) {
		XSSFWorkbook wb = null;
		InputStream ifs = null;
		OutputStream ofs = null;
		String filePath = ExcelName;
		String content = "";
		String title = "";
		BufferedReader br = null;
		try {
			// 设置要读取的文件路径
			ifs = new FileInputStream(filePath);
			// HSSFWorkbook相当于一个excel文件，HSSFWorkbook是解析excel2007之前的版本（xls）
			// 之后版本使用XSSFWorkbook（xlsx）
			wb = new XSSFWorkbook(ifs);
			// 获得sheet工作簿HSSFSheet
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFSheet sheettable = wb.getSheetAt(0);
			XSSFCell cell;
			XSSFCell celltable;
			XSSFRow row = sheet.getRow(0);
			XSSFRow rowtable = sheettable.getRow(0);
			int cellNum = row.getLastCellNum();
			int rowNum = sheet.getLastRowNum();
			String username = "";
			File csv = new File("MailServer.properties");
			br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "utf-8"));
			Properties properties = new Properties();
			properties.load(br);
			content = properties.getProperty("mail.send.content");
			title = properties.getProperty("mail.send.execlTitle");
			System.err.println("content:"+content);
			System.err.println("title:"+title);
			for (int i = 1; i <= rowNum; i++) {
				row = sheet.getRow(i);
				View m_view = new View();// 产生工作表对象

				for (int j = 0; j < cellNum; j++) {
					System.out.println(j);
					celltable = rowtable.getCell(j);
					if (celltable.getStringCellValue().equals("")) {
						celltable.setCellValue("0");
					}
					rowtable.getCell(j).setCellType(celltable.CELL_TYPE_STRING);
					System.out.println(celltable.getStringCellValue());
					m_view.setTextAsValue(j, 1, celltable.getStringCellValue());
					if (j < cellNum - 2) {
						CellFormat cfmt1 = m_view.getCellFormat();
						m_view.setSelection(j + 2, 1, j + 2, 1);
						cfmt1 = m_view.getCellFormat();
						if (j == 11 || j == 16 || j == 18) {
							cfmt1.setFontBold(true);
							cfmt1.setFontColor(Color.RED);
						}
						cfmt1.setBottomBorder((short) 1);
						cfmt1.setBottomBorderColor(Color.BLACK);
						cfmt1.setTopBorder((short) 1);
						cfmt1.setTopBorderColor(Color.BLACK);
						cfmt1.setLeftBorder((short) 1);
						cfmt1.setLeftBorderColor(Color.BLACK);
						cfmt1.setRightBorder((short) 1);
						cfmt1.setRightBorderColor(Color.BLACK);
						cfmt1.setHorizontalAlignment(CellFormat.HorizontalAlignmentLeft);
						m_view.setCellFormat(cfmt1);
					}
				}
				for (int j = 0; j < cellNum; j++) {
					cell = row.getCell(j);
					if (cell == null) {
						cell = row.getCell(2);
						cell.setCellValue("0");
					}
					cell.setCellType(cell.CELL_TYPE_STRING);
					m_view.setTextAsValue(j, 2, cell.getStringCellValue());
					if (j == 2) {
						username = cell.getStringCellValue();
					}
					if (j < cellNum - 2) {
						CellFormat cfmt1 = m_view.getCellFormat();
						m_view.setSelection(j + 2, 2, j + 2, 2);
						cfmt1 = m_view.getCellFormat();
						if (j == 11 || j == 16 || j == 18) {
							cfmt1.setFontBold(true);
							cfmt1.setFontColor(Color.RED);
						}
						cfmt1.setBottomBorder((short) 1);
						cfmt1.setBottomBorderColor(Color.BLACK);
						cfmt1.setTopBorder((short) 1);
						cfmt1.setTopBorderColor(Color.BLACK);
						cfmt1.setLeftBorder((short) 1);
						cfmt1.setLeftBorderColor(Color.BLACK);
						cfmt1.setRightBorder((short) 1);
						cfmt1.setRightBorderColor(Color.BLACK);
						cfmt1.setHorizontalAlignment(CellFormat.HorizontalAlignmentRight);
						m_view.setCellFormat(cfmt1);
					}
				}

				CellFormat cfmt = m_view.getCellFormat();
				m_view.setSelection(0, 1, 1, 2);
				cfmt = m_view.getCellFormat();
				cfmt.setMergeCells(true);
				cfmt.setFontBold(true);
				cfmt.setBottomBorder((short) 1);
				cfmt.setBottomBorderColor(Color.BLACK);
				cfmt.setTopBorder((short) 1);
				cfmt.setTopBorderColor(Color.BLACK);
				cfmt.setLeftBorder((short) 1);
				cfmt.setLeftBorderColor(Color.BLACK);
				cfmt.setRightBorder((short) 1);
				cfmt.setRightBorderColor(Color.BLACK);
				cfmt.setHorizontalAlignment(CellFormat.HorizontalAlignmentCenter);
				cfmt.setFontSize(15);
				m_view.setCellFormat(cfmt);
				m_view.setTextAsValue(title);

				m_view.setSelection(0, 3, 50, 50);
				cfmt = m_view.getCellFormat();
				cfmt.setMergeCells(true);
				m_view.setCellFormat(cfmt);
				m_view.setSelection(0, 0, 50, 0);
				cfmt = m_view.getCellFormat();
				cfmt.setMergeCells(true);
				m_view.setCellFormat(cfmt);
				m_view.getLock();
				m_view.setColWidth(0, 700);
				m_view.setColWidth(1, 17 * 256);
				m_view.setColWidth(2, 16 * 256);
				// m_view.deleteRange(8, 1, 8, 2, (short) 2);
				// m_view.deleteRange(8, 1, 8, 2, (short) 2);
				// m_view.deleteRange(27, 1, 27, 2, (short) 2);
				// FileOutputStream fOut=new FileOutputStream("table.xlsx");
				System.out.println(username);
				for (int a = 0; a < list.size(); a++) {
					UserEmail email = list.get(a);
					System.out.print(a);
					if (email.getName().equals(username)) {
						System.out.println(email);
						m_view.write("绿云工资清单.xls");
						MailWithAttachment.Send("绿云" + month + "月工资条", content, email.getEmail(), "绿云工资清单.xls");
						System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!发送成功" + email.getEmail());
						m_view.editCopyRight();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			File csv = new File("MailServer.properties");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "utf-8"));
			Properties properties = new Properties();
			properties.load(br);
			String content = properties.getProperty("mail.send.content");
			String title = properties.getProperty("mail.send.execlTitle");
			System.out.println(content);
			System.out.println(title);
		} catch (Exception e) {
		}
		
	}
}
