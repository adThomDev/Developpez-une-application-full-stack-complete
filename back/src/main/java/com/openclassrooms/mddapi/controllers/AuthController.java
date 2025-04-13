package com.openclassrooms.mddapi.controllers;

import javax.validation.Valid;

import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    JwtResponse jwtResponse = authService.authenticateUser(loginRequest);

    return ResponseEntity.ok(jwtResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    try {
      authService.registerUser(signUpRequest);

      return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    } catch (IllegalArgumentException e) {

      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

}
