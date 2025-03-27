package com.openclassrooms.mddapi.models.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDTO {
  private Long id;
  private String title;
  private Date createdAt;
  private Long userId;   // The ID of the associated User
  private Long themeId;  // The ID of the associated Theme
}