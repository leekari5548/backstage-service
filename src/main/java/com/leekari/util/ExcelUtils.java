package com.leekari.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litao
 * @date 2020/11/3 11:18
 * @description
 */
public class ExcelUtils {
    public static Workbook getExcelInputStream(InputStream is, Integer excelType) throws IOException {
        Workbook workbook = null;
        if (excelType == 0) {
            workbook = new XSSFWorkbook(is);
        }else {
            workbook = new HSSFWorkbook(is);
        }
        return workbook;
    }

    public static void getExcelDataWithXLS(InputStream is, Integer excelType) throws IOException {
        HSSFWorkbook workbook = (HSSFWorkbook) getExcelInputStream(is,
                excelType);
        int sheetNumber = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetNumber; i++) {
            HSSFSheet sheetAt = workbook.getSheetAt(i);
            int lastRowNum = sheetAt.getLastRowNum();
            int titleRowNum = sheetAt.getFirstRowNum();
        }

    }

    public static void main(String[] args) throws IOException {
        File baseFile = new File("/Users/litao/Desktop/test1.xlsx");
        File compareFile = new File("/Users/litao/Desktop/test2.xlsx");
        System.err.println(compareFile.exists());
        System.err.println(baseFile.exists());
        int baseColumn = 2;
        int compareColumn = 3;
        FileInputStream fis = new FileInputStream(baseFile);
        Workbook workbook = getExcelInputStream(fis, 0);
        int sheetNumber = workbook.getNumberOfSheets();
        int sheet = 0;
        Map<String, Map<Integer, String>> baseMap = new HashMap<>();
        Map<Integer, List<Map<Integer, String>>> map = new HashMap<>();
        for (int i = 0; i < sheetNumber; i++) {
            Sheet sheetAt = workbook.getSheetAt(i);
            int lastRowNum = sheetAt.getLastRowNum();
            int titleRowNum = sheetAt.getFirstRowNum();
            List<Map<Integer, String>> list = new ArrayList<>();

            for (int j = titleRowNum + 1; j < lastRowNum; ++j) {
                int count = 0;
                Map<Integer, String> rowMap = new HashMap<>();
                Row row = sheetAt.getRow(j);
                int cells = row.getPhysicalNumberOfCells();
                int start = row.getFirstCellNum();
                for (int k = start; k < cells; ++k) {
                    rowMap.put(count++, row.getCell(k) == null ? "": row.getCell(k).toString());
                }
                baseMap.put(rowMap.get(baseColumn - 1), rowMap);
                list.add(rowMap);
            }
            map.put(sheet++, list);
        }
        FileInputStream cfis = new FileInputStream(baseFile);
        Workbook cworkbook = getExcelInputStream(fis, 0);
        int csheetNumber = workbook.getNumberOfSheets();
        int csheet = 0;
        Map<String, Map<Integer, String>> cbaseMap = new HashMap<>();
        Map<Integer, List<Map<Integer, String>>> cmap = new HashMap<>();
        for (int i = 0; i < sheetNumber; i++) {
            Sheet csheetAt = cworkbook.getSheetAt(i);
            int clastRowNum = csheetAt.getLastRowNum();
            int ctitleRowNum = csheetAt.getFirstRowNum();
            List<Map<Integer, String>> clist = new ArrayList<>();

            for (int j = ctitleRowNum + 1; j < clastRowNum; ++j) {
                int count = 0;
                Map<Integer, String> crowMap = new HashMap<>();
                Row crow = csheetAt.getRow(j);
                int cells = crow.getPhysicalNumberOfCells();
                int start = crow.getFirstCellNum();
                for (int k = start; k < cells; ++k) {
                    crowMap.put(count++, crow.getCell(k) == null ? "": crow.getCell(k).toString());
                }
                baseMap.put(crowMap.get(baseColumn - 1), crowMap);
                clist.add(crowMap);
            }
            map.put(sheet++, clist);
        }
        System.err.println(map);
        System.err.println(baseMap);
    }
}
