package com.openclassrooms.mddapi.models.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDTO {
  private Long id;
  private String title;
  private String content;
  private Date createdAt;
  private String author;
  private String themeTitle;
}