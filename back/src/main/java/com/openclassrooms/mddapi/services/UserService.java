package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.DTOs.UserDTO;
import com.openclassrooms.mddapi.models.entities.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<UserDTO> findById(Long id) {
    return userRepository.findById(id).map(user -> {
      UserDTO userDTO = new UserDTO();
      userDTO.setId(user.getId());
      userDTO.setUsername(user.getUsername());
      userDTO.setEmail(user.getEmail());
      userDTO.setPassword(user.getPassword());
      return userDTO;
    });
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public Optional<UserDTO> updateUser(Long id, UserDTO updatedUser) {
    return userRepository.findById(id).map(existingUser -> {
      if (updatedUser.getUsername() != null) {
        existingUser.setUsername(updatedUser.getUsername());
      }
      if (updatedUser.getEmail() != null) {
        existingUser.setEmail(updatedUser.getEmail());
      }
      if (updatedUser.getPassword() != null) {
        existingUser.setPassword(updatedUser.getPassword());
      }
      userRepository.save(existingUser);

      UserDTO userDTO = new UserDTO();
      userDTO.setId(existingUser.getId());
      userDTO.setUsername(existingUser.getUsername());
      userDTO.setEmail(existingUser.getEmail());
      userDTO.setPassword(existingUser.getPassword());
      return userDTO;
    });
  }
}
