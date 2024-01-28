package com.burakozkan138.cinemabookingsystem.service;

import java.time.LocalTime;
import java.util.ArrayList;
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
import com.burakozkan138.cinemabookingsystem.utils.TimeUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {
  private final SessionRepository sessionRepository;
  private final MovieRepository movieRepository;
  private final HallRepository hallRepository;
  private final ModelMapper modelMapper;
  private final TimeUtils timeUtils;

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

  public List<SessionResponseDto> createSession(SessionCreateRequestDto createSessionRequestDto)
      throws BadRequestException {
    Movie movie = movieRepository.findById(createSessionRequestDto.getMovieId())
        .orElseThrow(() -> new BadRequestException("Movie not found"));

    Hall hall = hallRepository.findById(createSessionRequestDto.getHallId())
        .orElseThrow(() -> new BadRequestException("Hall not found"));

    int maxCapacity = hall.getMaxColumn() * hall.getMaxRow();
    List<Session> mappedSessions = new ArrayList<>();

    for (LocalTime startTime : createSessionRequestDto.getTimes()) {
      LocalTime endTime = timeUtils.calculateEndTime(startTime, movie.getDuration());
      List<Session> sessions = sessionRepository.findByTimestampBetween(createSessionRequestDto.getDate(), startTime,
          endTime, hall.getId());

      if (!sessions.isEmpty()) {
        throw new BadRequestException("Session already exists for this time: " + startTime.toString() + " - "
            + endTime.toString() + " in hall: " + hall.getName());
      }

      Session mappedSession = modelMapper.map(createSessionRequestDto, Session.class);
      mappedSession.setMovie(movie);
      mappedSession.setHall(hall);
      mappedSession.setStartTime(startTime);
      mappedSession.setEndTime(endTime);
      mappedSession.setMaxCapacity(maxCapacity);

      mappedSessions.add(mappedSession);
    }

    List<Session> createdSessions = sessionRepository.saveAll(mappedSessions);
    return createdSessions.stream().map(session -> modelMapper.map(session, SessionResponseDto.class)).toList();
  }

  public SessionResponseDto updateSession(String id, SessionUpdateRequestDto sessionUpdateRequestDto)
      throws BadRequestException {
    Session session = sessionRepository.findById(id).orElseThrow(() -> new BadRequestException("Session not found"));

    Session mappedSession = modelMapper.map(sessionUpdateRequestDto, Session.class);
    mappedSession.setMovie(session.getMovie());
    mappedSession.setHall(session.getHall());
    mappedSession.setMaxCapacity(session.getMaxCapacity());

    Session updatedSession = sessionRepository.save(mappedSession);

    return modelMapper.map(updatedSession, SessionResponseDto.class);
  }

  public Boolean deleteSession(String id) throws BadRequestException {
    Session session = sessionRepository.findById(id).orElseThrow(() -> new BadRequestException("Session not found"));
    sessionRepository.delete(session);
    return true;
  }
}
