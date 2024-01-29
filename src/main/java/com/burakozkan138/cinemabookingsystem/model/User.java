package com.burakozkan138.cinemabookingsystem.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "users")
public class User extends BaseEntity implements UserDetails {
  @Indexed(unique = true)
  private String username;
  private String password;
  @Indexed(unique = true)
  private String email;
  private String firstName;
  private String lastName;
  private Set<Role> roles = Set.of(new Role(Role.ROLE_USER));
  private ArrayList<Reservation> reservations = new ArrayList<>();
  private boolean enabled = true;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
  }

  @Override
  public boolean isAccountNonExpired() {
    return this.enabled;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.enabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return this.enabled;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}
