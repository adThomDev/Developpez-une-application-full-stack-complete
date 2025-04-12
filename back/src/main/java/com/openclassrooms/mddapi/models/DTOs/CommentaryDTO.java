package com.openclassrooms.mddapi.models.DTOs;

import lombok.Data;
import java.util.Date;

@Data
public class CommentaryDTO {
  private Long id;
  private String content;
  private String author;
}
