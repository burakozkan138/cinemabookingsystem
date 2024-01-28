package com.burakozkan138.cinemabookingsystem.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.burakozkan138.cinemabookingsystem.dto.Request.SessionCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.SessionUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.model.Session;

@Configuration
public class GeneralConfig {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    addMappingForSessions(modelMapper);
    return modelMapper;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private void addMappingForSessions(ModelMapper modelMapper) {
    modelMapper.addMappings(new PropertyMap<SessionCreateRequestDto, Session>() {
      @Override
      protected void configure() {
        using(ctx -> null).map().setId(null);
        map().getMovie().setId(source.getMovieId());
        map().getHall().setId(source.getHallId());
      }
    });

    modelMapper.addMappings(new PropertyMap<SessionUpdateRequestDto, Session>() {
      @Override
      protected void configure() {
        using(ctx -> null).map().setId(null);
        map().getMovie().setId(source.getMovieId());
        map().getHall().setId(source.getHallId());
      }
    });
  }
}
