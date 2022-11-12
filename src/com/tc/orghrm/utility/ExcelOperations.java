package com.tc.orghrm.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

	public static Object[][] readExcelData(String inputFile, String sheetName) throws IOException {
		File file = new File(inputFile);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum(); // total number of rows
		int totalColumns = sheet.getRow(0).getLastCellNum();// total number of columns

//		System.out.println("total columns:" + totalColumns);
//		System.out.println("total rows: " + totalRows);

		Object[][] data = new Object[totalRows][totalColumns];
		Cell cell = null;
		// To fill data from excel sheet in 2-D Array of Objects
		// 4 options available - String, Double, Date and Boolean - getCellType()
		// 3 options available - STRING, DOUBLE, BOOLEAN - CellType
		for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
			for (int colIndex = 0; colIndex < totalColumns; colIndex++) {
				cell = sheet.getRow(rowIndex + 1).getCell(colIndex);
				if (cell.getCellType() == CellType.STRING)
					data[rowIndex][colIndex] = cell.getStringCellValue();
				else if (cell.getCellType() == CellType.BOOLEAN)
					data[rowIndex][colIndex] = cell.getBooleanCellValue();
				else if (cell.getCellType() == CellType.NUMERIC)
					if(DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						data[rowIndex][colIndex] = formatter.format(cell.getDateCellValue());
					}	
					else
						data[rowIndex][colIndex] = cell.getNumericCellValue();
			}
		}

		sheet.getRow(1).getCell(0).getCellType();
		// Print the row values
//		System.out.println(data[2][0]);
//		System.out.println(data[2][1]);
//		System.out.println(data[2][2]);
//		System.out.println(data[2][3]);
		wb.close();
		return data;
	}

//	public static void main(String[] args) throws IOException {
//		readExcelData(".//testdata//LoginData.xlsx", "Data");
//	}
}
