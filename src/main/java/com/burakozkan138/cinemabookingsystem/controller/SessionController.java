package com.burakozkan138.cinemabookingsystem.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burakozkan138.cinemabookingsystem.dto.Request.SessionCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.SessionUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponseDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.SessionResponseDto;
import com.burakozkan138.cinemabookingsystem.service.SessionService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<BaseResponseDto<List<SessionResponseDto>>> getSessions() {
        List<SessionResponseDto> sessions = sessionService.getSessions();
        return new ResponseEntity<>(
                new BaseResponseDto<List<SessionResponseDto>>(sessions, "Sessions are fetched successfully", true,
                        HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponseDto<SessionResponseDto>> getSessionById(@PathVariable String id)
            throws BadRequestException {
        SessionResponseDto session = sessionService.getSessionById(id);
        return new ResponseEntity<>(
                new BaseResponseDto<SessionResponseDto>(session, "Session is fetched successfully", true,
                        HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<BaseResponseDto<SessionResponseDto>> createSession(
            @Valid @RequestBody SessionCreateRequestDto sessionCreateRequestDto) throws BadRequestException {

        SessionResponseDto session = sessionService.createSession(sessionCreateRequestDto);
        return new ResponseEntity<>(
                new BaseResponseDto<SessionResponseDto>(session, "Session is created successfully", true,
                        HttpStatus.CREATED.value()),
                HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<BaseResponseDto<SessionResponseDto>> updateSession(@PathVariable String id,
            @Valid @RequestBody SessionUpdateRequestDto sessionUpdateRequestDto) throws BadRequestException {
        SessionResponseDto session = sessionService.updateSession(id, sessionUpdateRequestDto);
        return new ResponseEntity<>(
                new BaseResponseDto<SessionResponseDto>(session, "Session is updated successfully", true,
                        HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<BaseResponseDto<Boolean>> deleteSession(@PathVariable String id) throws BadRequestException {
        Boolean data = sessionService.deleteSession(id);
        return new ResponseEntity<>(
                new BaseResponseDto<Boolean>(data, "Session is deleted successfully", true,
                        HttpStatus.OK.value()),
                HttpStatus.OK);
    }
}
