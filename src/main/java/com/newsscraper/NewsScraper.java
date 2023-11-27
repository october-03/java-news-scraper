package com.newsscraper;

import com.crawler.CrawlingService;
import com.dto.NewsDto;
import com.excel.ExcelService;

import java.util.ArrayList;

public class NewsScraper {
  public static void main(String[] args) {
    try {
      String keyword = args[0];
//      String keyword = "애플페이";

      ArrayList<NewsDto> newsData = CrawlingService.getNews(keyword);

      assert newsData != null;
      ExcelService.createExcel(newsData, keyword);
    } catch (Exception e) {
      System.out.println("에러가 발생했습니다.");
      System.out.println(e.getMessage());
    }
  }
}
