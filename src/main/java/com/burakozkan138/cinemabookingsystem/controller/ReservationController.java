package com.burakozkan138.cinemabookingsystem.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burakozkan138.cinemabookingsystem.dto.Request.ReservationCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.ReservationUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponse;
import com.burakozkan138.cinemabookingsystem.dto.Response.ReservationResponseDto;
import com.burakozkan138.cinemabookingsystem.service.ReservationService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
@PreAuthorize("isAuthenticated()")
public class ReservationController {
        private final ReservationService reservationService;

        @GetMapping
        public BaseResponse<List<ReservationResponseDto>> getMyReservations()
                        throws BadRequestException {
                List<ReservationResponseDto> reservations = reservationService.getMyReservations();
                return new BaseResponse<>(reservations, "Reservations fetched successfully", true, HttpStatus.OK);
        }

        @GetMapping("/all")
        @RolesAllowed("ADMIN")
        public BaseResponse<List<ReservationResponseDto>> getAllReservations() {
                List<ReservationResponseDto> reservations = reservationService.getAllReservations();
                return new BaseResponse<>(reservations, "Reservations fetched successfully", true, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public BaseResponse<ReservationResponseDto> getReservationById(@PathVariable String id)
                        throws BadRequestException {
                ReservationResponseDto reservation = reservationService.getReservationById(id);
                return new BaseResponse<>(reservation, "Reservation fetched successfully", true, HttpStatus.OK);
        }

        @PostMapping
        public BaseResponse<ReservationResponseDto> createReservation(
                        @Valid @RequestBody ReservationCreateRequestDto reservationCreateRequestDto)
                        throws BadRequestException {
                ReservationResponseDto reservation = reservationService.createReservation(reservationCreateRequestDto);
                return new BaseResponse<>(reservation, "Reservation created successfully", true, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public BaseResponse<ReservationResponseDto> updateReservation(@PathVariable String id,
                        @Valid @RequestBody ReservationUpdateRequestDto reservationCreateRequestDto)
                        throws BadRequestException {
                ReservationResponseDto reservation = reservationService.updateReservationById(id,
                                reservationCreateRequestDto);
                return new BaseResponse<>(reservation, "Reservation updated successfully", true, HttpStatus.OK);
        }

        @DeleteMapping("/{id}/cancel")
        public BaseResponse<Boolean> cancelReservationById(@PathVariable String id)
                        throws BadRequestException {
                Boolean data = reservationService.cancelReservationById(id);
                return new BaseResponse<>(data, "Reservation cancelled successfully", true, HttpStatus.OK);
        }
}
