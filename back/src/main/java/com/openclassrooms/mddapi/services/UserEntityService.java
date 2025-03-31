package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.models.entities.UserEntity;
import com.openclassrooms.mddapi.repositories.UserEntityRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
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
//TODO dans mapper
  public Optional<UserDTO> findById(Long id) {
    return userEntityRepository.findById(id).map(user -> {
      UserDTO userDTO = new UserDTO();
      userDTO.setId(user.getId());
      userDTO.setUsername(user.getUsername());
      userDTO.setEmail(user.getEmail());
      userDTO.setPassword(user.getPassword());
      return userDTO;
    });
  }

  public UserEntity saveUser(UserEntity userEntity) {
    return userEntityRepository.save(userEntity);
  }

  public Optional<UserDTO> updateUser(Long id, UserDTO updatedUser) {
    return userEntityRepository.findById(id).map(existingUser -> {
      if (updatedUser.getUsername() != null) {
        existingUser.setUsername(updatedUser.getUsername());
      }
      if (updatedUser.getEmail() != null) {
        existingUser.setEmail(updatedUser.getEmail());
      }
      if (updatedUser.getPassword() != null) {
        existingUser.setPassword(updatedUser.getPassword());
      }
      userEntityRepository.save(existingUser);

      UserDTO userDTO = new UserDTO();
      userDTO.setId(existingUser.getId());
      userDTO.setUsername(existingUser.getUsername());
      userDTO.setEmail(existingUser.getEmail());
      userDTO.setPassword(existingUser.getPassword());
      return userDTO;
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
