package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
//  private String firstName;
//  private String lastName;

//  private Boolean admin;

  public JwtResponse(String accessToken, Long id, String username,String email) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
//    this.lastName = lastName;
//    this.admin = admin;
  }
}
