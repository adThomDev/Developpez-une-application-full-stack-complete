package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.ArticleDTO;
import com.openclassrooms.mddapi.models.entities.Article;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/article")
public class ArticleController {

  private final ArticleRepository articleRepository;

  public ArticleController(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @GetMapping
  public ResponseEntity<List<ArticleDTO>> getAllArticles() {
    List<Article> articles = articleRepository.findAll();
    List<ArticleDTO> articleDTOs = articles.stream().map(this::convertToDTO).collect(Collectors.toList());
    return ResponseEntity.ok(articleDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
    Optional<Article> article = articleRepository.findById(id);
    return article.map(value -> ResponseEntity.ok(convertToDTO(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  private ArticleDTO convertToDTO(Article article) {
    ArticleDTO articleDTO = new ArticleDTO();
    articleDTO.setId(article.getId());
    articleDTO.setTitle(article.getTitle());
    articleDTO.setCreatedAt(article.getCreatedAt());
    articleDTO.setUserId(article.getUser().getId());
    articleDTO.setThemeId(article.getTheme().getId());
    return articleDTO;
  }
}