package com.burakozkan138.cinemabookingsystem.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
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
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponse;
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
  public BaseResponse<List<HallResponseDto>> getAllHalls() {
    List<HallResponseDto> halls = hallService.getAllHalls();
    return new BaseResponse<>(halls, "Halls fetched successfully", true, HttpStatus.OK);
  }

  @GetMapping("/{id}") // get hall by id
  public BaseResponse<HallResponseDto> getHallById(@PathVariable String id)
      throws BadRequestException {
    HallResponseDto hall = hallService.getHallById(id);
    return new BaseResponse<>(hall, "Hall fetched successfully", true, HttpStatus.OK);
  }

  @PostMapping // create hall
  public BaseResponse<HallResponseDto> createHall(
      @Valid @RequestBody HallCreateRequestDto hallCreateRequestDto) {

    HallResponseDto hall = hallService.createHall(hallCreateRequestDto);
    return new BaseResponse<>(hall, "Hall created successfully", true, HttpStatus.CREATED);
  }

  @PutMapping("/{id}") // update hall
  public BaseResponse<HallResponseDto> updateHall(@PathVariable String id,
      @Valid @RequestBody HallUpdateRequestDto hallUpdateRequestDto) {
    HallResponseDto hall = hallService.updateHall(id, hallUpdateRequestDto);
    return new BaseResponse<>(hall, "Hall updated successfully", true, HttpStatus.OK);
  }

  @DeleteMapping("/{id}") // delete hall
  public BaseResponse<Boolean> deleteHall(@PathVariable String id) {
    Boolean data = hallService.deleteHall(id);
    return new BaseResponse<>(data, "Hall deleted successfully", true, HttpStatus.OK);
  }
}
