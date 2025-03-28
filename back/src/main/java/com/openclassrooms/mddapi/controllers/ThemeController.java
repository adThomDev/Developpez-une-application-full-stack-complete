package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.ThemeDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/theme")
public class ThemeController {

  private final ThemeRepository themeRepository;

  public ThemeController(ThemeRepository themeRepository) {
    this.themeRepository = themeRepository;
  }

  @GetMapping
  public ResponseEntity<List<ThemeDTO>> getAllThemes() {
    List<Theme> themes = themeRepository.findAll();
    List<ThemeDTO> themeDTOs = themes.stream().map(this::convertToDTO).collect(Collectors.toList());
    return ResponseEntity.ok(themeDTOs);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ThemeDTO>> getThemesByUserId(@PathVariable Long userId) {
    List<Theme> themes = themeRepository.findByUserId(userId);
    List<ThemeDTO> themeDTOs = themes.stream().map(this::convertToDTO).collect(Collectors.toList());
    return ResponseEntity.ok(themeDTOs);
  }

  //TODO mapper
  private ThemeDTO convertToDTO(Theme theme) {
    ThemeDTO themeDTO = new ThemeDTO();
    themeDTO.setId(theme.getId());
    themeDTO.setTitle(theme.getTitle());
    themeDTO.setDescription(theme.getDescription());
    themeDTO.setUserId(theme.getUser().getId());
    return themeDTO;
  }
}