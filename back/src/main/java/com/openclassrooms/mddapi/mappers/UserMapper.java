package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.models.entities.UserEntity;

public class UserMapper {

  public static UserDTO toDTO(UserEntity userEntity) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(userEntity.getId());
    userDTO.setUsername(userEntity.getUsername());
    userDTO.setEmail(userEntity.getEmail());

    if (userEntity.getThemes() != null) {
      Number[] subscribedThemes = userEntity.getThemes().stream()
          .map(Theme::getId)
          .toArray(Number[]::new);
      userDTO.setSubscribedThemes(subscribedThemes);
    }

    return userDTO;
  }

  public static UserEntity toEntity(UserDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(userDTO.getId());
    userEntity.setUsername(userDTO.getUsername());
    userEntity.setEmail(userDTO.getEmail());
    userEntity.setPassword(userDTO.getPassword());

    return userEntity;
  }
}

