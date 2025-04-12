package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.DTOs.ThemeDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.models.entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ThemeMapper {

  public static ThemeDTO convertToDTO(Theme theme) {
    ThemeDTO themeDTO = new ThemeDTO();
    themeDTO.setId(theme.getId());
    themeDTO.setTitle(theme.getTitle());
    themeDTO.setDescription(theme.getDescription());

    List<Long> userIds = theme.getUsers().stream()
        .map(UserEntity::getId)
        .collect(Collectors.toList());
    themeDTO.setUserIds(userIds);

    return themeDTO;
  }
}
