package com.openclassrooms.mddapi.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  private String description;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "theme_user",
      joinColumns = @JoinColumn(name = "theme_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private List<UserEntity> users = new ArrayList<>();

  @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Article> articles;
}


