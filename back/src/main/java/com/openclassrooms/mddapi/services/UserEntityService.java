package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
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

  public Optional<UserDTO> unsubscribeToTheme(Long userId, Long themeId) {
    return userEntityRepository.findById(userId).map(user -> {
      user.getThemes().removeIf(theme -> theme.getId().equals(themeId));
      UserEntity updatedUserEntity = userEntityRepository.save(user);
      UserDTO userDTO = new UserDTO();
      userDTO.setId(updatedUserEntity.getId());
      userDTO.setUsername(updatedUserEntity.getUsername());
      userDTO.setEmail(updatedUserEntity.getEmail());
      userDTO.setPassword(updatedUserEntity.getPassword());
      return userDTO;
    });
  }

  //TODO si theme/user pas présent, gérer erreurs. idem au dessus
  public Optional<UserDTO> subscribeToTheme(Long userId, Long themeId) {
    return userEntityRepository.findById(userId).map(user -> {
      user.getThemes().add(themeRepository.findById(themeId).get());
      UserEntity updatedUserEntity = userEntityRepository.save(user);
      UserDTO userDTO = new UserDTO();
      userDTO.setId(updatedUserEntity.getId());
      userDTO.setUsername(updatedUserEntity.getUsername());
      userDTO.setEmail(updatedUserEntity.getEmail());
      userDTO.setPassword(updatedUserEntity.getPassword());
      return userDTO;
    });
  }
}
