


package com.mergeproject.excelreader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mergeproject.entity.UserDetails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelReader {

    public static final String[] HEADER = { "id", "name", "role", "location" };
    public static final String SHEET_NAME = "userDetails";

    public static ByteArrayInputStream dataToExcel(List<UserDetails> userData) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
        		//Writes to a byte array, capturing output data in memory.
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet(SHEET_NAME);

            // title row and merge cells
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("User Details");
            
            // Merge cells for title across all columns
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADER.length - 1));
            
            // Create a style for the title
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 12);
            titleStyle.setFont(titleFont);
            titleCell.setCellStyle(titleStyle);

            // Create header row
            Row headerRow = sheet.createRow(1);
            for (int i = 0; i < HEADER.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HEADER[i]);
            }

            // Fill data rows
            int rowIndex = 2;
            for (UserDetails uData : userData) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(uData.getId());
                dataRow.createCell(1).setCellValue(uData.getName());
                dataRow.createCell(2).setCellValue(uData.getRole());
                dataRow.createCell(3).setCellValue(uData.getLocation());
            }

            workbook.write(byteArrayOutputStream);
            //Reads from a byte array, useful for converting byte arrays to input streams.
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
