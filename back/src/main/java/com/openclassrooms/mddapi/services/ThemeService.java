package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.DTOs.ThemeDTO;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.mappers.ThemeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService {

  private final ThemeRepository themeRepository;

  public ThemeService(ThemeRepository themeRepository) {
    this.themeRepository = themeRepository;
  }

  public List<ThemeDTO> getAllThemes() {
    List<Theme> themes = themeRepository.findAll();

    return themes.stream()
        .map(ThemeMapper::convertToDTO)
        .collect(Collectors.toList());
  }

  public List<ThemeDTO> getThemesByUserId(Long userId) {
    List<Theme> themes = themeRepository.findByUsers_Id(userId);

    return themes.stream()
        .map(ThemeMapper::convertToDTO)
        .collect(Collectors.toList());
  }

}
