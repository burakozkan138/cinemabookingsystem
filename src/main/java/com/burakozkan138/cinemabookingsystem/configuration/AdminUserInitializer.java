package com.burakozkan138.cinemabookingsystem.configuration;

import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.burakozkan138.cinemabookingsystem.model.Role;
import com.burakozkan138.cinemabookingsystem.model.User;
import com.burakozkan138.cinemabookingsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    Optional<User> adminUser = userRepository.findByUsername("admin");

    if (adminUser.isEmpty()) {
      User user = new User();
      user.setUsername("admin");
      user.setPassword(passwordEncoder.encode("admin"));
      user.setEmail("admin@admin.com");
      user.setFirstName("Admin");
      user.setLastName("Admin");
      user.setRoles(Set.of(new Role(Role.ROLE_ADMIN)));
      user.setEnabled(true);
      userRepository.save(user);
    }
  }
}
