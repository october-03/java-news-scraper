package com.crawler;

import com.dto.NewsDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CrawlingService {

  public static ArrayList<NewsDto> getNews(String keyword) {
    try {
      String URL = encodingUrl(keyword);

      return getNewsData(URL);
    } catch (Exception e) {
      System.out.println("에러가 발생했습니다.");
      System.out.println("Exception: " + e.getMessage());
      return null;
    }
  }

  private static String encodingUrl(String keyword) {
    String encodedKeyword = "%22" + URLEncoder.encode(keyword, StandardCharsets.UTF_8) + "%22";

    return "https://search.naver.com/search.naver?sm=tab_hty.top&where=news&sort=1&query=" + encodedKeyword;
  }

  private static ArrayList<NewsDto> getNewsData(String URL) throws Exception {
    try {
      ArrayList<NewsDto> newsData = new ArrayList<>();

      Document doc = Jsoup.connect(URL).get();
      Elements newsList = doc.select("ul.list_news > li a.news_tit");
      Elements dates = doc.select("ul.list_news > li span.info");

      for(int i = 0; i < dates.size(); i++) {
        if(isToday(dates.get(i))) {
          Element news = newsList.get(i);
          NewsDto result = new NewsDto(news.text(), news.attr("href"));

          newsData.add(result);
        } else {
          break;
        }
      }

      return newsData;
    } catch (Exception e) {
      System.out.println("에러가 발생했습니다.");
      System.out.println("Exception: " + e.getMessage());
      return null;
    }
  }

  private static Boolean isToday(Element Date) {
    if(Date.text().contains("시간 전")) {
      return true;
    };

    return false;
  }
}
