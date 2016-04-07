package gcloud.email.Util;

import gcloud.email.entity.UserEmail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetUserEmail {
	public static List<UserEmail> GetAllEmailFromCSV(String CSVFIleName) {
		XSSFWorkbook wb = null;
		InputStream ifs = null;
		String filePath = CSVFIleName;
		List<UserEmail> list = new ArrayList<UserEmail>();
		try {
			ifs = new FileInputStream(filePath);
			wb = new XSSFWorkbook(ifs);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFCell cell;
			XSSFRow row = sheet.getRow(1);
			int cellNum = row.getLastCellNum();
			int rowNum = sheet.getLastRowNum();
			System.out.println("列数是" + cellNum);
			System.out.println("行数是" + rowNum);
			for (int i = 2; i <= rowNum; i++) {
				row = sheet.getRow(i);
				UserEmail email = new UserEmail();
				for (int j = 0; j < cellNum; j++) {
					cell = row.getCell(j);
					row.getCell(j).setCellType(cell.CELL_TYPE_STRING);

					if (j == 0) {
						email.setId(Integer.parseInt(cell.getStringCellValue()));
					} else if (j == 1) {
						email.setName(cell.getStringCellValue());
					} else if (j == 2) {
						email.setEmail(cell.getStringCellValue());
					} else if (j == 3) {
						email.setFoxaddrID(cell.getStringCellValue());
					}
				}
				list.add(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("#########" + list.toString());
		}
		return list;
	}
}
