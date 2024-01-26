package com.burakozkan138.cinemabookingsystem.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import com.burakozkan138.cinemabookingsystem.dto.Request.SessionCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.SessionUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.SessionResponseDto;
import com.burakozkan138.cinemabookingsystem.model.Hall;
import com.burakozkan138.cinemabookingsystem.model.Movie;
import com.burakozkan138.cinemabookingsystem.model.Session;
import com.burakozkan138.cinemabookingsystem.repository.HallRepository;
import com.burakozkan138.cinemabookingsystem.repository.MovieRepository;
import com.burakozkan138.cinemabookingsystem.repository.SessionRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {
  private final SessionRepository sessionRepository;
  private final MovieRepository movieRepository;
  private final HallRepository hallRepository;
  private final ModelMapper modelMapper;

  public List<SessionResponseDto> getSessions() {
    List<Session> sessions = sessionRepository.findAll();

    return sessions.stream().map(session -> modelMapper.map(session, SessionResponseDto.class)).toList();
  }

  public SessionResponseDto getSessionById(String id) throws BadRequestException {
    if (id == null) {
      throw new BadRequestException("Session id cannot be null");
    }

    Session session = sessionRepository.findById(id).orElseThrow(() -> new BadRequestException("Session not found"));

    return modelMapper.map(session, SessionResponseDto.class);
  }

  public SessionResponseDto createSession(SessionCreateRequestDto createSessionRequestDto)
      throws BadRequestException {
    Movie movie = movieRepository.findById(createSessionRequestDto.getMovieId())
        .orElseThrow(() -> new BadRequestException("Movie not found"));

    Hall hall = hallRepository.findById(createSessionRequestDto.getHallId())
        .orElseThrow(() -> new BadRequestException("Hall not found"));

    int maxCapacity = hall.getMaxColumn() * hall.getMaxRow();
    modelMapper.addMappings(new PropertyMap<SessionCreateRequestDto, Session>() {
      @Override
      protected void configure() {
        map().setId(null);
        map().getMovie().setId(source.getMovieId());
        map().getHall().setId(source.getHallId());
        map().setMaxCapacity(maxCapacity);
      }
    });
    Session mappedSession = modelMapper.map(createSessionRequestDto, Session.class);
    mappedSession.setMovie(movie);
    mappedSession.setHall(hall);

    Session createdSession = sessionRepository.save(mappedSession);

    return modelMapper.map(createdSession, SessionResponseDto.class);
  }

  public SessionResponseDto updateSession(String id, SessionUpdateRequestDto sessionUpdateRequestDto)
      throws BadRequestException {
    Session session = sessionRepository.findById(id).orElseThrow(() -> new BadRequestException("Session not found"));

    modelMapper.addMappings(new PropertyMap<SessionUpdateRequestDto, Session>() {
      @Override
      protected void configure() {
        map().setId(id);
        map().getMovie().setId(source.getMovieId());
        map().getHall().setId(source.getHallId());
      }
    });

    Session mappedSession = modelMapper.map(sessionUpdateRequestDto, Session.class);
    mappedSession.setMovie(session.getMovie());
    mappedSession.setHall(session.getHall());

    Session updatedSession = sessionRepository.save(mappedSession);

    return modelMapper.map(updatedSession, SessionResponseDto.class);
  }

  public Boolean deleteSession(String id) throws BadRequestException {
    Session session = sessionRepository.findById(id).orElseThrow(() -> new BadRequestException("Session not found"));
    sessionRepository.delete(session);
    return true;
  }

}
