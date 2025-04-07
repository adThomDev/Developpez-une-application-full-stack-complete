package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.models.entities.UserEntity;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityService {

  private final UserEntityRepository userEntityRepository;
  private final ThemeRepository themeRepository;

  public UserEntityService(UserEntityRepository userEntityRepository, ThemeRepository themeRepository) {
    this.userEntityRepository = userEntityRepository;
    this.themeRepository = themeRepository;
  }

  public Optional<UserDTO> findById(Long id) {
    return userEntityRepository.findById(id)
        .map(UserMapper::toDTO);
  }

  public Optional<UserDTO> createUser(UserDTO userDTO) {
    UserEntity userEntity = UserMapper.toEntity(userDTO);
    UserEntity savedUser = userEntityRepository.save(userEntity);
    return Optional.of(UserMapper.toDTO(savedUser));
  }

  public Optional<UserDTO> updateUser(Long id, UserDTO updatedUser) {
    return userEntityRepository.findById(id).map(existingUser -> {
      if (!updatedUser.getUsername().isEmpty()) {
        existingUser.setUsername(updatedUser.getUsername());
      }
      if (!updatedUser.getEmail().isEmpty()) {
        existingUser.setEmail(updatedUser.getEmail());
      }
      if (!updatedUser.getPassword().isEmpty()) {
        existingUser.setPassword(updatedUser.getPassword());
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
