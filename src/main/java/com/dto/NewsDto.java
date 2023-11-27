package com.dto;

public class NewsDto {
  private final String title;
  private final String Url;

  public NewsDto(String title, String Url) {
    this.title = title;
    this.Url = Url;
  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return Url;
  }
}
