package com.openclassrooms.mddapi.security.services;

import com.openclassrooms.mddapi.models.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.mddapi.repositories.UserEntityRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  UserEntityRepository userEntityRepository;

  UserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
    this.userEntityRepository = userEntityRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userEntityRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));

    return UserDetailsImpl
        .builder()
        .id(userEntity.getId())
        .username(userEntity.getUsername())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .build();
  }

}
