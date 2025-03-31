package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.ThemeDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.models.entities.UserEntity;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//TODO limiter le cross origin
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/theme")
public class ThemeController {

  private final ThemeRepository themeRepository;
  private final UserEntityRepository userRepository;

  public ThemeController(ThemeRepository themeRepository, UserEntityRepository userRepository) {
    this.themeRepository = themeRepository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public ResponseEntity<List<ThemeDTO>> getAllThemes() {
    List<Theme> themes = themeRepository.findAll();
    List<ThemeDTO> themeDTOs = themes.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(themeDTOs);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ThemeDTO>> getThemesByUserId(@PathVariable Long userId) {
    List<Theme> themes = themeRepository.findByUsers_Id(userId);
    List<ThemeDTO> themeDTOs = themes.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(themeDTOs);
  }

  //TODO : mapper
  private ThemeDTO convertToDTO(Theme theme) {
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
