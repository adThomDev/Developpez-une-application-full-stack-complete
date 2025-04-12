package com.openclassrooms.mddapi.models.DTOs;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String username;
  private String email;
  private String password;
  private Number[] subscribedThemes;

  public UserDTO() {
  }

  public UserDTO(String username, String email, Number[] subscribedThemes) {
    this.username = username;
    this.email = email;
    this.subscribedThemes = subscribedThemes;
  }

  public UserDTO(Long id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
