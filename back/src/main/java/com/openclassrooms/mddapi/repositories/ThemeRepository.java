package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

  List<Theme> findByUsersId(Long userId);
}
