package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.ArticleDTO;
import com.openclassrooms.mddapi.models.DTOs.CommentaryRequestDTO;
import com.openclassrooms.mddapi.models.DTOs.CreateArticleRequestDTO;
import com.openclassrooms.mddapi.services.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ArticleController {

  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping
  public ResponseEntity<List<ArticleDTO>> getAllArticles() {
    return ResponseEntity.ok(articleService.getAllArticles());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
    return articleService.getArticleById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/commentary")
  public ResponseEntity<?> addCommentary(
      @PathVariable Long id,
      @RequestBody CommentaryRequestDTO request,
      @RequestHeader("Authorization") String authHeader
  ) {
    boolean success = articleService.addCommentary(authHeader, id, request);
    return success ? ResponseEntity.ok().build() : ResponseEntity.status(401).build();
  }

  @PostMapping
  public ResponseEntity<?> createArticle(
      @RequestBody CreateArticleRequestDTO request,
      @RequestHeader("Authorization") String authHeader
  ) {
    boolean success = articleService.createArticle(authHeader, request);
    return success ? ResponseEntity.ok().build() : ResponseEntity.status(401).build();
  }
}
