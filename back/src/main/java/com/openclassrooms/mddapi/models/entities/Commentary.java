package com.openclassrooms.mddapi.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commentary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @CreatedDate
  @Column(name = "created_at", updatable = false)
  private Date createdAt;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity userEntity;

  @ManyToOne
  @JoinColumn(name = "article_id", nullable = false)
  private Article article;
}

