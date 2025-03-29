package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.models.entities.Theme;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;
  private final UserRepository userRepository;

  public UserController(UserService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @PostMapping("/create")
  public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
    User createdUser = userService.saveUser(user);
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(createdUser.getUsername());
    userDTO.setEmail(createdUser.getEmail());
    return ResponseEntity.ok(userDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    Optional<User> userOptional = userRepository.findById(id);

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
    Optional<UserDTO> updatedUserDTO = userService.updateUser(userId, userDTO);
    return updatedUserDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/{userId}/theme/unsub/{themeId}")
  public ResponseEntity<UserDTO> unsubscribeToTheme(@PathVariable Long userId, @PathVariable Long themeId) {
    Optional<UserDTO> updatedUserDTO = userService.unsubscribeToTheme(userId, themeId);
    return updatedUserDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/{userId}/theme/sub/{themeId}")
  public ResponseEntity<UserDTO> subscribeToTheme(@PathVariable Long userId, @PathVariable Long themeId) {
    Optional<UserDTO> updatedUserDTO = userService.subscribeToTheme(userId, themeId);
    return updatedUserDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
