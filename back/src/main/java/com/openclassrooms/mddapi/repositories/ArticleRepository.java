package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Article;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

//  List<Article> findByUserId(Long userId);
//
//  List<Article> findByThemeId(Long themeId);

  //TODO adapter la logique dans les services/controllers
  List<Article> findByUserEntity(UserEntity userEntity);
  List<Article> findByTheme(Theme theme);

}

