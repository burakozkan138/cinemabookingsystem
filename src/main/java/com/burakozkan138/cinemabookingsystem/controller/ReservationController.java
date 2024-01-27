package com.burakozkan138.cinemabookingsystem.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burakozkan138.cinemabookingsystem.dto.Request.ReservationCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponseDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.ReservationResponseDto;
import com.burakozkan138.cinemabookingsystem.service.ReservationService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
@PreAuthorize("isAuthenticated()")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<BaseResponseDto<List<ReservationResponseDto>>> getMyReservations()
            throws BadRequestException {
        List<ReservationResponseDto> reservations = reservationService.getMyReservations();
        return new ResponseEntity<>(
                new BaseResponseDto<>(reservations, "Reservations fetched successfully", true,
                        HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    @RolesAllowed("ADMIN")
    public ResponseEntity<BaseResponseDto<List<ReservationResponseDto>>> getAllReservations() {
        List<ReservationResponseDto> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(
                new BaseResponseDto<>(reservations,
                        "Reservations fetched successfully", true, HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto<ReservationResponseDto>> getReservationById(@PathVariable String id)
            throws BadRequestException {
        ReservationResponseDto reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(
                new BaseResponseDto<>(reservation,
                        "Reservation fetched successfully", true, HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponseDto<ReservationResponseDto>> createReservation(
            @Valid @RequestBody ReservationCreateRequestDto reservationCreateRequestDto) throws BadRequestException {
        ReservationResponseDto reservation = reservationService.createReservation(reservationCreateRequestDto);
        return new ResponseEntity<>(
                new BaseResponseDto<>(reservation,
                        "Reservation created successfully", true, HttpStatus.CREATED.value()),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BaseResponseDto<Boolean>> cancelReservationById(@PathVariable String id)
            throws BadRequestException {
        Boolean data = reservationService.cancelReservationById(id);
        return new ResponseEntity<>(
                new BaseResponseDto<>(data, "Reservation cancelled successfully", true, HttpStatus.OK.value()),
                HttpStatus.OK);
    }
}
