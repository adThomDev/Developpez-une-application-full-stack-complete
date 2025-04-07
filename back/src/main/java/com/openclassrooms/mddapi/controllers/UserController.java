package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.services.UserEntityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserEntityService userEntityService;

  public UserController(UserEntityService userEntityService) {
    this.userEntityService = userEntityService;
  }

  @PostMapping("/create")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    Optional<UserDTO> created = userEntityService.createUser(userDTO);
    return created.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    return userEntityService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
    return userEntityService.updateUser(userId, userDTO)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/{userId}/theme/sub/{themeId}")
  public ResponseEntity<Void> subscribeToTheme(@PathVariable Long userId, @PathVariable Long themeId) {
    boolean success = userEntityService.subscribeToTheme(userId, themeId);
    return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

  @PatchMapping("/{userId}/theme/unsub/{themeId}")
  public ResponseEntity<Void> unsubscribeToTheme(@PathVariable Long userId, @PathVariable Long themeId) {
    boolean success = userEntityService.unsubscribeToTheme(userId, themeId);
    return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }
}
