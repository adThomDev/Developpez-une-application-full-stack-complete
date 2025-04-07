package com.openclassrooms.mddapi.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 6, max = 50)
  @Column(nullable = false, unique = true)
  private String username;

  @Size(min = 6, max = 50)
  @Column(nullable = false, unique = true)
  private String email;

  @Size(min = 6, max = 50)
  @Column(nullable = false)
  private String password;

  @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
  private List<Theme> themes = new ArrayList<>();

  public UserEntity(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}

