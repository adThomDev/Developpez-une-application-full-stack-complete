package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.models.entities.UserEntity;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserEntityRepository;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {

  private final UserEntityRepository userEntityRepository;
  private final ThemeRepository themeRepository;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder passwordEncoder;

  public UserEntityService(UserEntityRepository userEntityRepository, ThemeRepository themeRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
    this.userEntityRepository = userEntityRepository;
    this.themeRepository = themeRepository;
    this.jwtUtils = jwtUtils;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<UserDTO> findById(Long id) {
    return userEntityRepository.findById(id).map(UserMapper::toDTO);
  }

  public Optional<UserDTO> createUser(UserDTO userDTO) {
    if (userEntityRepository.existsByUsername(userDTO.getUsername())) {
      throw new IllegalArgumentException("Username is already taken.");
    }
    if (userEntityRepository.existsByEmail(userDTO.getEmail())) {
      throw new IllegalArgumentException("Email is already taken.");
    }

    UserEntity userEntity = UserMapper.toEntity(userDTO);
    UserEntity savedUser = userEntityRepository.save(userEntity);

    return Optional.of(UserMapper.toDTO(savedUser));
  }

  public Optional<UserDTO> updateUser(Long id, UserDTO updatedUser, String token) {
    String userEmailFromToken = jwtUtils.getUserEmailFromJwtToken(token);

    return userEntityRepository.findById(id).map(existingUser -> {
      // Validation
      if (!existingUser.getEmail().equals(userEmailFromToken)) {
        throw new SecurityException("You can only update your own account.");
      }
      if (!updatedUser.getUsername().isEmpty() && !existingUser.getUsername().equals(updatedUser.getUsername()) && userEntityRepository.existsByUsername(updatedUser.getUsername())) {
        throw new IllegalArgumentException("Username is already taken.");
      }
      if (!updatedUser.getEmail().isEmpty() && !existingUser.getEmail().equals(updatedUser.getEmail()) && userEntityRepository.existsByEmail(updatedUser.getEmail())) {
        throw new IllegalArgumentException("Email is already taken.");
      }

      // Update process
      if (!updatedUser.getUsername().isEmpty()) {
        existingUser.setUsername(updatedUser.getUsername());
      }
      if (!updatedUser.getEmail().isEmpty()) {
        existingUser.setEmail(updatedUser.getEmail());
      }
      if (!updatedUser.getPassword().isEmpty()) {
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
      }
      userEntityRepository.save(existingUser);

      return UserMapper.toDTO(existingUser);
    });
  }

  public boolean subscribeToTheme(Long userId, Long themeId) {
    Optional<UserEntity> userOpt = userEntityRepository.findById(userId);
    Optional<Theme> themeOpt = themeRepository.findById(themeId);

    if (userOpt.isPresent() && themeOpt.isPresent()) {
      UserEntity user = userOpt.get();
      Theme theme = themeOpt.get();

      user.getThemes().add(theme);
      theme.getUsers().add(user);
      themeRepository.save(theme);

      return true;
    }

    return false;
  }

  public boolean unsubscribeToTheme(Long userId, Long themeId) {
    Optional<UserEntity> userOpt = userEntityRepository.findById(userId);
    Optional<Theme> themeOpt = themeRepository.findById(themeId);

    if (userOpt.isPresent() && themeOpt.isPresent()) {
      UserEntity user = userOpt.get();
      Theme theme = themeOpt.get();

      user.getThemes().remove(theme);
      theme.getUsers().remove(user);
      themeRepository.save(theme);

      return true;
    }

    return false;
  }
}
