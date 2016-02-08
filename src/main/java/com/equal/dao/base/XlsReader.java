package com.equal.dao.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.equal.common.Config;
import com.equal.exceptions.XlsDataNotFoundException;

public class XlsReader {

    private XSSFSheet sheet;
    private Map<String, String> data = new HashMap<String, String>();
    private List<String> metaData = new ArrayList<String>();
    private List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

    public XlsReader(String fileName, String sheetName) {

        open(fileName, sheetName);
        getMetaData();
    }

    public void open(String fileName, String sheetName) {
        try {
            if (sheetName == null || sheetName.isEmpty()) {
                throw new IllegalArgumentException("Please, provide sheet name");
            }
            InputStream fis = Config.class.getClassLoader()
                    .getResourceAsStream(fileName);
//			InputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workBook = new XSSFWorkbook(fis);
            sheet = workBook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException(MessageFormat.format(
                        "Sheet is not found: {0}", sheetName));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> getDataListById(String testId) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        if (!dataList.isEmpty()) {
            dataList.clear();
        }
        while ((rowIterator.hasNext())) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            XSSFCell cell = row.getCell(0);
            if (cell != null
                    && cell.getStringCellValue().trim()
                    .contains(testId)) {
                Map<String, String> rowData = new HashMap<String, String>();

                for (int i = 0; i < row.getLastCellNum() - 1; i++) {
                    cell = row.getCell(i);
                    try {


                        rowData.put(metaData.get(i), getCellValue(cell));
                    } catch (NullPointerException e) {
                        rowData.put(metaData.get(i), "");
                    }
                }
                dataList.add(rowData);
            }
        }
        if (dataList.size() < 1) {
            throw new XlsDataNotFoundException("Data is not found by id: "
                    + testId);
        }
        return dataList;
    }

    public Map<String, String> getDataById(String testId) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        if (!data.isEmpty()) {
            data.clear();
        }
        while ((rowIterator.hasNext())) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            XSSFCell cell = row.getCell(0);
            if (cell != null
                    && cell.getStringCellValue().trim()
                    .contains(testId)) {
                for (int i = 0; i < row.getLastCellNum() - 1; i++) {
                    cell = row.getCell(i);
                    data.put(metaData.get(i), getCellValue(cell));
                }
            }
        }
        if (data.isEmpty()) {
            throw new XlsDataNotFoundException("Data is not found by id: "
                    + testId);
        }
        return data;
    }

    private String getCellValue(XSSFCell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return getString(cell.getStringCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return getNumericCellValue(String.valueOf(cell.getNumericCellValue()));
        }
        return null;
    }

    private String getNumericCellValue(String val) {
        return val.replace(".0", "");
    }

    private String getString(String val) {
        if (val.equalsIgnoreCase("<null>")) {
            return "";
        } else if (val.contains("< >")) {
            return val.replaceAll("< >", " ");
        } else {
            return val;
        }
    }

    private void getMetaData() {
        XSSFRow row = sheet.getRow(1);
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            metaData.add(cellIterator.next().toString());
        }
        sheet.removeRow(row);
    }

    public List<Map<String, String>> getAllList() {
        Iterator<Row> rowIterator = sheet.rowIterator();
        if (!dataList.isEmpty()) {
            dataList.clear();
        }
        while ((rowIterator.hasNext())) {
            XSSFRow row = (XSSFRow) rowIterator.next();
            XSSFCell cell = row.getCell(0);
            if (cell != null) {
                Map<String, String> rowData = new HashMap<String, String>();

                for (int i = 0; i < row.getLastCellNum() - 1; i++) {
                    cell = row.getCell(i);
                    rowData.put(metaData.get(i), getCellValue(cell));
                }
                dataList.add(rowData);
            }
        }
        if (dataList.size() < 1) {
            throw new XlsDataNotFoundException("Data is not found");
        }
        return dataList;
    }
}
