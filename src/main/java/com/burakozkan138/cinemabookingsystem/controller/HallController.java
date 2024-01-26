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

import com.burakozkan138.cinemabookingsystem.dto.Request.HallCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.HallUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponseDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.HallResponseDto;
import com.burakozkan138.cinemabookingsystem.service.HallService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/halls")
@RolesAllowed("ADMIN")
public class HallController {
  private final HallService hallService;

  @GetMapping // get all halls
  public ResponseEntity<BaseResponseDto<List<HallResponseDto>>> getAllHalls() {
    List<HallResponseDto> halls = hallService.getAllHalls();
    return new ResponseEntity<>(
        new BaseResponseDto<List<HallResponseDto>>(halls, "Halls fetched successfully", true, HttpStatus.OK.value()),
        HttpStatus.OK);
  }

  @GetMapping("/{id}") // get hall by id
  public ResponseEntity<BaseResponseDto<HallResponseDto>> getHallById(@PathVariable String id)
      throws BadRequestException {
    HallResponseDto hall = hallService.getHallById(id);
    return new ResponseEntity<>(
        new BaseResponseDto<HallResponseDto>(hall, "Hall fetched successfully", true, HttpStatus.OK.value()),
        HttpStatus.OK);
  }

  @PostMapping // create hall
  public ResponseEntity<BaseResponseDto<HallResponseDto>> createHall(
      @Valid @RequestBody HallCreateRequestDto hallCreateRequestDto) {

    HallResponseDto hall = hallService.createHall(hallCreateRequestDto);
    return new ResponseEntity<>(
        new BaseResponseDto<HallResponseDto>(hall, "Hall created successfully", true, HttpStatus.CREATED.value()),
        HttpStatus.CREATED);
  }

  @PutMapping("/{id}") // update hall
  public ResponseEntity<BaseResponseDto<HallResponseDto>> updateHall(@PathVariable String id,
      @Valid @RequestBody HallUpdateRequestDto hallUpdateRequestDto) {
    HallResponseDto hall = hallService.updateHall(id, hallUpdateRequestDto);
    return new ResponseEntity<>(
        new BaseResponseDto<>(hall, "Hall updated successfully", true, HttpStatus.OK.value()),
        HttpStatus.OK);
  }

  @DeleteMapping("/{id}") // delete hall
  public ResponseEntity<BaseResponseDto<Boolean>> deleteHall(@PathVariable String id) {
    Boolean data = hallService.deleteHall(id);
    return new ResponseEntity<>(
        new BaseResponseDto<>(data, "Hall deleted successfully", true, HttpStatus.OK.value()),
        HttpStatus.OK);
  }
}
