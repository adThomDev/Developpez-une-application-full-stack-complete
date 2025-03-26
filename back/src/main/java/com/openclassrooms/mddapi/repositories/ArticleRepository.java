package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findByUserId(Long userId);

  List<Article> findByThemeId(Long themeId);
}

