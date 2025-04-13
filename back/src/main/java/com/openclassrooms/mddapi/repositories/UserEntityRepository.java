package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByEmail(String email);

  Boolean existsByEmail(String email);

  Boolean existsByUsername(String username);
}
