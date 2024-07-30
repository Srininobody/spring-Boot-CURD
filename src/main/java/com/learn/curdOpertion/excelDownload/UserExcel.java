package com.learn.curdOpertion.excelDownload;


    import org.apache.poi.ss.usermodel.*;
    import org.apache.poi.ss.util.CellRangeAddress;
    import org.apache.poi.ss.util.CellRangeAddressList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

    @Service
    public class UserExcel {

        public ResponseEntity<InputStreamResource> userDetailsUpdateExcel() throws IOException {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Sheet1");

                // Create a header row
                Row headerRow = sheet.createRow(0);

                // Create a cell style for the header with a background color
                CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                Font headerFont = workbook.createFont();
                headerFont.setColor(IndexedColors.WHITE.getIndex()); // Change font color her

                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);

                // Manually create header cells
                Cell cellName = headerRow.createCell(0);
                cellName.setCellValue("Name");
                cellName.setCellStyle(headerStyle);

                Cell cellEmail = headerRow.createCell(1);
                cellEmail.setCellValue("Email");
                cellEmail.setCellStyle(headerStyle);

                Cell cellDateOfBirth = headerRow.createCell(2);
                cellDateOfBirth.setCellValue("Date of Birth (dd/mm/yyyy)");
                cellDateOfBirth.setCellStyle(headerStyle);

                Cell cellCountry = headerRow.createCell(3);
                cellCountry.setCellValue("Country");
                cellCountry.setCellStyle(headerStyle);

                Cell cellPhoto = headerRow.createCell(4);
                cellPhoto.setCellValue("Update Photo");
                cellPhoto.setCellStyle(headerStyle);

                // Define country options
                String[] countries = {"USA", "Canada", "Mexico", "Germany", "France", "UK", "China", "Japan", "India", "Brazil"};

                // Create a new sheet for the country options
                Sheet countrySheet = workbook.createSheet("CountryOptions");
                for (int i = 0; i < countries.length; i++) {
                    Row row = countrySheet.createRow(i);
                    row.createCell(0).setCellValue(countries[i]);
                }

                // Create a named range for the country options
                Name name = workbook.createName();
                name.setNameName("CountryList");
                name.setRefersToFormula("CountryOptions!$A$1:$A$" + countries.length);

                // Apply data validation to the "Country" column
                DataValidationHelper validationHelper = sheet.getDataValidationHelper();
                DataValidationConstraint constraint = validationHelper.createFormulaListConstraint("CountryList");
                CellRangeAddressList addressList = new CellRangeAddressList(1, 10, 3, 3);
                DataValidation validation = validationHelper.createValidation(constraint, addressList);
                validation.setShowErrorBox(true);
                sheet.addValidationData(validation);

                // Apply autofilter to the country column
                sheet.setAutoFilter(new CellRangeAddress(0, 0, 3, 3));

                // Add some example data


                // Write the output to a ByteArrayOutputStream
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);
                byte[] excelBytes = baos.toByteArray();
                ByteArrayInputStream bais = new ByteArrayInputStream(excelBytes);

                // Set headers and return as a ResponseEntity
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=userDetailsUpdate.xlsx");

                return new ResponseEntity<>(new InputStreamResource(bais), headers, HttpStatus.OK);
            }
        }


}
