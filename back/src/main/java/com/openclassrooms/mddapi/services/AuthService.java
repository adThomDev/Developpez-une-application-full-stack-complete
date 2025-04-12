package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.entities.UserEntity;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.repositories.UserEntityRepository;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder passwordEncoder;
  private final UserEntityRepository userEntityRepository;

  public AuthService(AuthenticationManager authenticationManager,
                     JwtUtils jwtUtils,
                     PasswordEncoder passwordEncoder,
                     UserEntityRepository userEntityRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.passwordEncoder = passwordEncoder;
    this.userEntityRepository = userEntityRepository;
  }

  public JwtResponse authenticateUser(LoginRequest loginRequest) {
    String login = loginRequest.getLogin();
    String password = loginRequest.getPassword();

    Optional<UserEntity> userOpt = userEntityRepository.findByUsername(login);
    if (userOpt.isEmpty()) {
      userOpt = userEntityRepository.findByEmail(login);
    }

    if (userOpt.isEmpty()) {
      throw new SecurityException("Invalid login");
    }

    String email = userOpt.get().getEmail();

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email, password)
    );

    String jwt = jwtUtils.generateJwtToken(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
  }

  public void registerUser(SignupRequest signUpRequest) {
    validateUserForRegistration(signUpRequest);

    UserEntity userEntity = new UserEntity(
        signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        passwordEncoder.encode(signUpRequest.getPassword())
    );

    userEntityRepository.save(userEntity);
  }


  public void validateUserForRegistration(SignupRequest signUpRequest) {
    if (userEntityRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new IllegalArgumentException("Error: Email is already taken");
    }
    if (userEntityRepository.existsByUsername(signUpRequest.getUsername())) {
      throw new IllegalArgumentException("Error: Username is already taken");
    }

    String password = signUpRequest.getPassword();
    if (!isPasswordValid(password)) {
      throw new IllegalArgumentException("Error: Password must have 8 characters min with at least one uppercase letter, one lowercase letter, one number, and one special character.");
    }
  }

  private boolean isPasswordValid(String password) {
    String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    return password != null && password.matches(passwordPattern);
  }

}

