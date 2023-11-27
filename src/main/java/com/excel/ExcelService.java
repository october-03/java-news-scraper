package com.excel;

import com.dto.NewsDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExcelService {
  private static final String today = LocalDate.now().toString();

  public static void createExcel(ArrayList<NewsDto> newsData, String keyword) {
    String fileName = keyword + "_" + today + ".xlsx";
    String filePath = "./" + keyword;

    if(!newsData.isEmpty()) {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet(today);

      newsData.add(0, new NewsDto("제목", "URL"));

      for(int i = 0; i < newsData.size(); i++) {
        sheet.createRow(i).createCell(0).setCellValue(newsData.get(i).getTitle());
        sheet.getRow(i).createCell(1).setCellValue(newsData.get(i).getUrl());
      }

      try {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath + "/" + fileName);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
      } catch (Exception e) {
        System.out.println("에러가 발생했습니다.");
        System.out.println("Exception: " + e.getMessage());
      }
    }
  }
}
