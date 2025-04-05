package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.ArticleDTO;
import com.openclassrooms.mddapi.models.DTOs.CommentaryDTO;
import com.openclassrooms.mddapi.models.DTOs.CommentaryRequestDTO;
import com.openclassrooms.mddapi.models.entities.Article;
import com.openclassrooms.mddapi.models.entities.Commentary;
import com.openclassrooms.mddapi.models.entities.UserEntity;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.CommentaryRepository;
import com.openclassrooms.mddapi.repositories.UserEntityRepository;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
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
  private final CommentaryRepository commentaryRepository;
  private final UserEntityRepository userRepository;
  private final JwtUtils jwtUtils;

  public ArticleController(ArticleRepository articleRepository,
                           CommentaryRepository commentaryRepository,
                           UserEntityRepository userRepository,
                           JwtUtils jwtUtils) {
    this.articleRepository = articleRepository;
    this.commentaryRepository = commentaryRepository;
    this.userRepository = userRepository;
    this.jwtUtils = jwtUtils;
  }

  @GetMapping
  public ResponseEntity<List<ArticleDTO>> getAllArticles() {
    List<Article> articles = articleRepository.findAll();
    List<ArticleDTO> articleDTOs = articles.stream()
        .map(article -> convertToDTO(article, false))
        .collect(Collectors.toList());
    return ResponseEntity.ok(articleDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
    Optional<Article> article = articleRepository.findById(id);
    return article.map(value -> ResponseEntity.ok(convertToDTO(value, true)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/commentary")
  public ResponseEntity<?> addCommentary(
      @PathVariable Long id,
      @RequestBody CommentaryRequestDTO request,
      @RequestHeader("Authorization") String authHeader
  ) {
    // Get JWT from "Bearer <token>"
    String token = authHeader.replace("Bearer ", "");

    if (!jwtUtils.validateJwtToken(token)) {
      return ResponseEntity.status(401).body("Invalid or expired token");
    }

    String userEmail = jwtUtils.getUserNameFromJwtToken(token);
    Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);
    if (userOpt.isEmpty()) {
      return ResponseEntity.status(401).body("User not found");
    }

    Optional<Article> articleOpt = articleRepository.findById(id);
    if (articleOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Commentary commentary = new Commentary();
    commentary.setContent(request.getContent());
    commentary.setUserEntity(userOpt.get());
    commentary.setArticle(articleOpt.get());

    commentaryRepository.save(commentary);

    return ResponseEntity.ok().build();
  }

  private ArticleDTO convertToDTO(Article article, boolean includeCommentaries) {
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