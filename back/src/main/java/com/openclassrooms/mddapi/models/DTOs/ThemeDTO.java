package com.openclassrooms.mddapi.models.DTOs;

import lombok.Data;

@Data
public class ThemeDTO {
  private Long id;
  private String title;
  private String description;
  private Long userId;
}