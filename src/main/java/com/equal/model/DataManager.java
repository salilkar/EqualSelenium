package com.equal.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class DataManager {

	public static int startRow, startCol, endRow, endCol, ci, cj;

	/*
	 *Read data content from excel (.xls) file  
	 */
	public  String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception {

		String[][] tabArray = null;

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));

		Sheet sheet = workbook.getSheet(sheetName);

		Cell tableStart = sheet.findCell(tableName);

		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();

		Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,100, 64000, false);
		endRow = tableEnd.getRow();
		endCol = tableEnd.getColumn();

		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;

		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}
		return tabArray;
	}

	/*
	 * Returns the test execution status of supplied test case
	 */
	@SuppressWarnings("resource")
	public  String executionController(String testCaseName) throws Exception {

		String tcStatus = "";

		getTableArray(ConfigurationLibrary.xlpath_controller, ConfigurationLibrary.xlsheet_controller, ConfigurationLibrary.xlDataTable_controller);

		HSSFWorkbook wb;
		try {
			InputStream inp = new FileInputStream(ConfigurationLibrary.xlpath_controller);
			wb = new HSSFWorkbook(inp);
		} catch (Exception e) {
			wb = new HSSFWorkbook();
		}

		try {

			if (wb.getNumberOfSheets()==0) {
				wb.createSheet("Overview");
			}

			HSSFSheet sheet = wb.getSheet(ConfigurationLibrary.xlsheet_controller);

			for (int i=1; i<=endRow; i++){

				HSSFRow row = sheet.getRow(startRow+i);
				if (row==null)
					row = sheet.createRow(startRow);

				HSSFCell cell = row.getCell(endCol-2);
				if (cell == null)
					cell = row.createCell(endCol-2);

				String tcName = cell.getStringCellValue();

				if(tcName.equals(testCaseName))
				{
					HSSFCell newcell = row.getCell(endCol-3);
					if (newcell == null)
						newcell = row.createCell(endCol-3);
					tcStatus = newcell.getStringCellValue();
					System.out.println(tcStatus);
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return tcStatus;
	}

}

