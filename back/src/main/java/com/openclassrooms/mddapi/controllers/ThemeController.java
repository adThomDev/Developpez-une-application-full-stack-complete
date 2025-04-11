package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.ThemeDTO;
import com.openclassrooms.mddapi.services.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO limiter le cross origin ?
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/theme")
public class ThemeController {

  private final ThemeService themeService;

  public ThemeController(ThemeService themeService) {
    this.themeService = themeService;
  }

  @GetMapping
  public ResponseEntity<List<ThemeDTO>> getAllThemes() {
    List<ThemeDTO> themeDTOs = themeService.getAllThemes();

    return ResponseEntity.ok(themeDTOs);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ThemeDTO>> getThemesByUserId(@PathVariable Long userId) {
    List<ThemeDTO> themeDTOs = themeService.getThemesByUserId(userId);

    return ResponseEntity.ok(themeDTOs);
  }
}
