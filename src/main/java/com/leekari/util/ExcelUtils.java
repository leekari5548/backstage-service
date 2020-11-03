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
        File file = new File("/Users/litao/Desktop/文安县数据.xlsx");
        System.err.println(file.exists());
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = getExcelInputStream(fis, 0);
        int sheetNumber = workbook.getNumberOfSheets();
        int sheet = 0;
        Map<Integer, List<Map<Integer, String>>> map = new HashMap<>();
        for (int i = 0; i < sheetNumber; i++) {
            Sheet sheetAt = workbook.getSheetAt(i);
            int lastRowNum = sheetAt.getLastRowNum();
            int titleRowNum = sheetAt.getFirstRowNum();
            List<Map<Integer, String>> list = new ArrayList<>();
            int count = 0;
            for (int j = titleRowNum; j < lastRowNum; ++j) {
                Map<Integer, String> rowMap = new HashMap<>();
                Row row = sheetAt.getRow(j);
                int cells = row.getPhysicalNumberOfCells();
                int start = row.getFirstCellNum();
                for (int k = start; k < cells; ++k) {
                    rowMap.put(count++, row.getCell(k) == null ? "": row.getCell(k).toString());
                }
                list.add(rowMap);
            }
            map.put(sheet++, list);
        }
        File writeFile = new File("/Users/litao/Desktop/data.txt");
        if (!writeFile.exists()) {
            writeFile.createNewFile();
        }
        FileInputStream fileInputStream = new FileInputStream(writeFile);
        fileInputStream.read(JSONObject.toJSONBytes(map));
        fileInputStream.close();
        fis.close();
//        System.err.println(map);
    }
}
