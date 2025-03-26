package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

  List<Commentary> findByArticleId(Long articleId);
}
