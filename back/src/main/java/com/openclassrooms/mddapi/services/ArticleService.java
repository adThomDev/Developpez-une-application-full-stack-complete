package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.ArticleMapper;
import com.openclassrooms.mddapi.models.DTOs.*;
import com.openclassrooms.mddapi.models.entities.*;
import com.openclassrooms.mddapi.repositories.*;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentaryRepository commentaryRepository;
    private final UserEntityRepository userRepository;
    private final ThemeRepository themeRepository;
    private final JwtUtils jwtUtils;

    public ArticleService(
            ArticleRepository articleRepository,
            CommentaryRepository commentaryRepository,
            UserEntityRepository userRepository,
            ThemeRepository themeRepository,
            JwtUtils jwtUtils
    ) {
        this.articleRepository = articleRepository;
        this.commentaryRepository = commentaryRepository;
        this.userRepository = userRepository;
        this.themeRepository = themeRepository;
        this.jwtUtils = jwtUtils;
    }

    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(article -> ArticleMapper.toDTO(article, false))
                .collect(Collectors.toList());
    }

    public Optional<ArticleDTO> getArticleById(Long id) {
        return articleRepository.findById(id).map(article -> ArticleMapper.toDTO(article, true));
    }

    public boolean addCommentary(String authHeader, Long articleId, CommentaryRequestDTO request) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtils.validateJwtToken(token)) return false;

        String userEmail = jwtUtils.getUserEmailFromJwtToken(token);
        Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);
        Optional<Article> articleOpt = articleRepository.findById(articleId);

        if (userOpt.isEmpty() || articleOpt.isEmpty()) return false;

        Commentary commentary = new Commentary();
        commentary.setContent(request.getContent());
        commentary.setUserEntity(userOpt.get());
        commentary.setArticle(articleOpt.get());
        commentary.setCreatedAt(new Date());

        commentaryRepository.save(commentary);
        return true;
    }

    public boolean createArticle(String authHeader, CreateArticleRequestDTO request) {
        String token = authHeader.replace("Bearer ", "");
        if (!jwtUtils.validateJwtToken(token)) return false;

        String userEmail = jwtUtils.getUserEmailFromJwtToken(token);
        Optional<UserEntity> userOpt = userRepository.findByEmail(userEmail);
        Optional<Theme> themeOpt = themeRepository.findById(request.getThemeId());

        if (userOpt.isEmpty() || themeOpt.isEmpty()) return false;

        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setCreatedAt(new Date());
        article.setUserEntity(userOpt.get());
        article.setTheme(themeOpt.get());

        articleRepository.save(article);
        return true;
    }
}
