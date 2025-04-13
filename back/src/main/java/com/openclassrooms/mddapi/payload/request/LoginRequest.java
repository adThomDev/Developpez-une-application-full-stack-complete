package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
  @NotBlank
  private String login;

  @NotBlank
  private String password;

}