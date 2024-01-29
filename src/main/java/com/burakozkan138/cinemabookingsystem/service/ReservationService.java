package com.burakozkan138.cinemabookingsystem.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.burakozkan138.cinemabookingsystem.dto.Request.ReservationCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.ReservationUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.ReservationResponseDto;
import com.burakozkan138.cinemabookingsystem.model.Point;
import com.burakozkan138.cinemabookingsystem.model.Reservation;
import com.burakozkan138.cinemabookingsystem.model.Session;
import com.burakozkan138.cinemabookingsystem.model.User;
import com.burakozkan138.cinemabookingsystem.repository.ReservationRepository;
import com.burakozkan138.cinemabookingsystem.repository.SessionRepository;
import com.burakozkan138.cinemabookingsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
  private final ReservationRepository reservationRepository;
  private final SessionRepository sessionRepository;
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  public List<ReservationResponseDto> getMyReservations() throws BadRequestException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(authentication.getName())
        .orElseThrow(() -> new BadRequestException("User not found"));
    List<Reservation> reservations = reservationRepository.findByUser(user);

    return reservations.stream().map(reservation -> {
      return modelMapper.map(reservation, ReservationResponseDto.class);
    }).toList();
  }

  public List<ReservationResponseDto> getAllReservations() {
    List<Reservation> reservations = reservationRepository.findAll();
    return reservations.stream().map(reservation -> {
      return modelMapper.map(reservation, ReservationResponseDto.class);
    }).toList();
  }

  public ReservationResponseDto getReservationById(String id) throws BadRequestException {
    Reservation reservation = reservationRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Reservation not found"));

    return modelMapper.map(reservation, ReservationResponseDto.class);
  }

  public ReservationResponseDto createReservation(ReservationCreateRequestDto reservationCreateRequestDto)
      throws BadRequestException {
    Session session = sessionRepository.findById(reservationCreateRequestDto.getSessionId())
        .orElseThrow(() -> new BadRequestException("Session not found"));
    User user = userRepository
        .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
        .orElseThrow(() -> new BadRequestException("User not found"));

    if ((session.getReservations() != null) && (session.getReservations().size() >= session.getMaxCapacity())) {
      throw new BadRequestException("Hall is full");
    }

    if (session.getBookedSeats().contains(new Point(reservationCreateRequestDto.getX(),
        reservationCreateRequestDto.getY()))) {
      throw new BadRequestException("Seat is already booked");
    }

    Reservation reservation = modelMapper.map(reservationCreateRequestDto, Reservation.class);
    reservation.setId(null);
    reservation.setSession(session);
    reservation.setUser(user);
    reservation.setSeat(reservationCreateRequestDto.getX(), reservationCreateRequestDto.getY());

    reservationRepository.save(reservation);

    session.getBookedSeats().add(reservation.getSeat());
    sessionRepository.save(session);

    return modelMapper.map(reservation, ReservationResponseDto.class);
  }

  public Boolean cancelReservationById(String id) throws BadRequestException {
    Reservation reservation = reservationRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Reservation not found"));
    User user = userRepository
        .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
        .orElseThrow(() -> new BadRequestException("User not found"));

    if (!reservation.getUser().equals(user)) {
      throw new BadRequestException("You can only cancel your own reservations");
    }

    Session session = reservation.getSession();

    session.getBookedSeats().remove(reservation.getSeat());
    sessionRepository.save(session);

    reservationRepository.delete(reservation);
    return true;
  }

  public ReservationResponseDto updateReservationById(String id,
      ReservationUpdateRequestDto reservationCreateRequestDto) throws BadRequestException {
    Reservation reservation = reservationRepository.findById(id)
        .orElseThrow(() -> new BadRequestException("Reservation not found"));
    Session session = reservation.getSession();
    User user = userRepository
        .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
        .orElseThrow(() -> new BadRequestException("User not found"));

    if (!reservation.getUser().equals(user)) {
      throw new BadRequestException("You can only update your own reservations");
    }

    Point newSeat = new Point(reservationCreateRequestDto.getX(), reservationCreateRequestDto.getY());
    if (session.getBookedSeats().contains(newSeat)) {
      throw new BadRequestException("Seat is already booked");
    }

    session.getBookedSeats().remove(reservation.getSeat());
    reservation.setSeat(newSeat);
    sessionRepository.save(session);
    reservationRepository.save(reservation);

    return modelMapper.map(reservation, ReservationResponseDto.class);
  }

}
