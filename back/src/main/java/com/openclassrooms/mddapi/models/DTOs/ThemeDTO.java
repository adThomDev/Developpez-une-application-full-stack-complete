package com.openclassrooms.mddapi.models.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class ThemeDTO {
  private Long id;
  private String title;
  private String description;
  private List<Long> userIds;
}