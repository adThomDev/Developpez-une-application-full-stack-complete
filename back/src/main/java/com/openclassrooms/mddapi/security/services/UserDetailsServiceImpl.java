package com.openclassrooms.mddapi.security.services;

import com.openclassrooms.mddapi.models.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.mddapi.repositories.UserEntityRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  UserEntityRepository userEntityRepository;

  UserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
    this.userEntityRepository = userEntityRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Optional<UserEntity> userOpt = userEntityRepository.findByEmail(login);

    if (userOpt.isEmpty()) {
      userOpt = userEntityRepository.findByUsername(login);
    }

    UserEntity userEntity = userOpt.orElseThrow(
        () -> new UsernameNotFoundException("User Not Found with login: " + login)
    );

    return UserDetailsImpl.builder()
        .id(userEntity.getId())
        .username(userEntity.getUsername())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .build();
  }

}
