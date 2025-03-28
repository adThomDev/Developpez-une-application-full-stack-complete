package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
    User createdUser = userService.saveUser(user);
    UserDTO userDTO = new UserDTO();
    userDTO.setId(createdUser.getId());
    userDTO.setUsername(createdUser.getUsername());
    userDTO.setEmail(createdUser.getEmail());
    userDTO.setPassword(createdUser.getPassword());
    return ResponseEntity.ok(userDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
    Optional<UserDTO> userDTO = userService.findById(id);
    return userDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PatchMapping("/{id}")
  public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    Optional<UserDTO> updatedUserDTO = userService.updateUser(id, userDTO);
    return updatedUserDTO.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }


}
