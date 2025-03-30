package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.UserEntity;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.repositories.UserEntityRepository;
import com.openclassrooms.mddapi.services.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserEntityService userEntityService;
  private final UserEntityRepository userEntityRepository;

  public UserController(UserEntityService userEntityService, UserEntityRepository userEntityRepository) {
    this.userEntityService = userEntityService;
    this.userEntityRepository = userEntityRepository;
  }

  @PostMapping("/create")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserEntity userEntity) {
    UserEntity createdUserEntity = userEntityService.saveUser(userEntity);
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(createdUserEntity.getUsername());
    userDTO.setEmail(createdUserEntity.getEmail());
    return ResponseEntity.ok(userDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    Optional<UserEntity> userOptional = userEntityRepository.findById(id);

    return userOptional.map(user -> {
      String username = user.getUsername();
      String email = user.getEmail();
      Number[] subscribedThemes = user.getThemes().stream()
          .map(Theme::getId)
          .toArray(Number[]::new);

      UserDTO userDTO = new UserDTO(username, email, subscribedThemes);

      return ResponseEntity.ok(userDTO);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
    Optional<UserDTO> updatedUserDTO = userEntityService.updateUser(userId, userDTO);
    return updatedUserDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/{userId}/theme/unsub/{themeId}")
  public ResponseEntity<UserDTO> unsubscribeToTheme(@PathVariable Long userId, @PathVariable Long themeId) {
    Optional<UserDTO> updatedUserDTO = userEntityService.unsubscribeToTheme(userId, themeId);
    return updatedUserDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/{userId}/theme/sub/{themeId}")
  public ResponseEntity<UserDTO> subscribeToTheme(@PathVariable Long userId, @PathVariable Long themeId) {
    Optional<UserDTO> updatedUserDTO = userEntityService.subscribeToTheme(userId, themeId);
    return updatedUserDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
