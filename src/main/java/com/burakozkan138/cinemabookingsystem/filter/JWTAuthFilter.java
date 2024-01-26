package com.burakozkan138.cinemabookingsystem.filter;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.burakozkan138.cinemabookingsystem.service.UserService;
import com.burakozkan138.cinemabookingsystem.utils.JWTUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
  private final JWTUtils jwtUtils;
  private final UserService userService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String tokenPrefix = "Bearer ";

    final String token;
    final String username;

    if (authHeader == null || !authHeader.startsWith(tokenPrefix)) {
      filterChain.doFilter(request, response);
      return;
    }

    token = authHeader.substring(tokenPrefix.length());
    username = jwtUtils.extractUserName(token);

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails user = userService.loadUserByUsername(username);

      if (jwtUtils.isTokenValid(token, user)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}
