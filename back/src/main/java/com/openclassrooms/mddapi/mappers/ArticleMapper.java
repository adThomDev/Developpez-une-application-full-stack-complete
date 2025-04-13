package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.DTOs.ArticleDTO;
import com.openclassrooms.mddapi.models.DTOs.CommentaryDTO;
import com.openclassrooms.mddapi.models.entities.Article;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleMapper {

  public static ArticleDTO toDTO(Article article, boolean includeCommentaries) {
    ArticleDTO articleDTO = new ArticleDTO();
    articleDTO.setId(article.getId());
    articleDTO.setTitle(article.getTitle());
    articleDTO.setContent(article.getContent());
    articleDTO.setCreatedAt(article.getCreatedAt());
    articleDTO.setAuthor(article.getUserEntity().getUsername());
    articleDTO.setThemeTitle(article.getTheme().getTitle());

    if (includeCommentaries) {
      List<CommentaryDTO> commentaryDTOs = article.getCommentaries().stream()
          .map(commentary -> {
            CommentaryDTO dto = new CommentaryDTO();
            dto.setId(commentary.getId());
            dto.setContent(commentary.getContent());
            dto.setAuthor(commentary.getUserEntity().getUsername());
            return dto;
          })
          .collect(Collectors.toList());
      articleDTO.setCommentaries(commentaryDTOs);
    }

    return articleDTO;
  }
}
