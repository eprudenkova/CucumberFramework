package com.hrms.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    private static Workbook book;
    private static Sheet sheet;

    /**
     * this method open excel file
     *
     * @param filepath
     */
    public static void openExcel(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            book = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * access sheet
     *
     * @param sheetName
     * @return
     */
    public static void getSheet(String sheetName) {
        sheet = book.getSheet(sheetName);
    }

    /**
     * returns number of rows
     *
     * @return
     */
    public static int getRowsCount() {

        return sheet.getPhysicalNumberOfRows();
    }

    /**
     * returns number of cells
     *
     * @param rowIndex
     * @return
     */
    public static int getColsCount(int rowIndex) {
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    /**
     * returns cell's value
     *
     * @param rowIndex
     * @param colIndex
     * @return
     */
    public static String getCellData(int rowIndex, int colIndex) {
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    /**
     * returns list of maps
     *
     * @param filePath
     * @return
     */
    public static List<Map<String, String>> excelIntoListMap(String filePath, String sheetName) {

        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String, String>> listData = new ArrayList<>();
        for (int row = 1; row < getRowsCount(); row++) {
            //creating a map for every row
            Map<String, String> map = new LinkedHashMap<>();
            //looping through all cells in the row
            for (int col = 0; col < getColsCount(row); col++) {
                //storing values from each cell into a map
                map.put(getCellData(0, col), getCellData(row, col));
            }
            //adding every map to thr list
            listData.add(map);
        }
        return listData;
    }

    public static Object[][] excelToArray(String filePath, String sheetName) {

        openExcel(filePath);
        getSheet(sheetName);

        Object[][] data = new Object[getRowsCount() - 1][getColsCount(0)];
        for (int row = 1; row < getRowsCount(); row++) {
            for (int col = 0; col < getColsCount(row); col++) {
                data[row-1][col] = sheet.getRow(row).getCell(col).toString();
            }
        }
        return data;
    }

//    public static void main(String[] args) {
//
//        FileInputStream fileInputStream;
//        XSSFWorkbook workbook = new XSSFWorkbook();
//
//        try {
//            fileInputStream = new FileInputStream("/Users/evgeniya/IdeaProjects/TestNGFramework/src/test/resources/testdata/hrmsData.xlsx");
//            workbook = new XSSFWorkbook(fileInputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Sheet sheet = workbook.getSheet("hrmsData");
//        int numberOfRows = sheet.getPhysicalNumberOfRows();
//        int numberOfCols = sheet.getRow(1).getPhysicalNumberOfCells();
//        String cell = sheet.getRow(0).getCell(0).toString();
//
//        Object[][] data = new Object[numberOfRows - 1][numberOfCols];
//        for (int row = 1; row < numberOfRows; row++) {
//            for (int col = 0; col < numberOfCols; col++) {
//                data[row - 1][col] = sheet.getRow(row).getCell(col).toString();
//            }
//        }
//        for (Object[] d : data) {
//            for (Object o : d) {
//                System.out.print(o + " ");
//            }
//            System.out.println();
//        }
//    }

}

