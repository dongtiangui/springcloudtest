package com.hik.common.commonmodule.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel工具类
 */
public class ExcelUtils {
    public static List<String> readExcelFile(InputStream inputStream, String fileName){
       List<String> result = new ArrayList<>();
        Workbook workbook = null;
        try {
            //判断什么类型文件
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workbook == null) {
            return result;
        } else {
            int numOfSheet = workbook.getNumberOfSheets();
            //遍历表
            for (int i = 0; i < numOfSheet; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if(sheet == null) continue;
                int lastRowNum = sheet.getLastRowNum();
                if(lastRowNum == 0) continue;
                Row row ;
                for (int j  = 1; j <= lastRowNum; j++) {
                    row = sheet.getRow(j);
                    if(row == null) {
                        continue;
                    }
                    short lastCellNum = row.getLastCellNum();
                    for (int k = 0; k <= lastCellNum; k++) {
                        if(row.getCell(k)==null) {
                            continue;
                        }
                        row.getCell(k).setCellType(CellType.STRING);
                        String res = row.getCell(k).getStringCellValue().trim();
                        result.add(res);
                    }
                }
            }
        }
        return result;
    }
}
