package com.openclassrooms.mddapi.models.DTOs;

import lombok.Data;

@Data
public class CreateArticleRequestDTO {
  private Long themeId;
  private String title;
  private String content;
}

