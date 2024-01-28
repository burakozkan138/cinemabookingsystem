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

import com.burakozkan138.cinemabookingsystem.dto.Request.ActorCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.ActorUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.ActorResponseDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponse;
import com.burakozkan138.cinemabookingsystem.service.ActorService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actors")
public class ActorController {
  private final ActorService actorService;

  @GetMapping
  public BaseResponse<List<ActorResponseDto>> getAllActors() {
    List<ActorResponseDto> actors = actorService.getAllActors();
    return new BaseResponse<>(actors, "Actors fetched successfully", true, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public BaseResponse<ActorResponseDto> getActorById(@PathVariable String id)
      throws BadRequestException {
    ActorResponseDto actor = actorService.getActorById(id);
    return new BaseResponse<>(actor, "Actor fetched successfully", true, HttpStatus.OK);
  }

  @PostMapping
  @RolesAllowed("ADMIN")
  public BaseResponse<ActorResponseDto> createActor(
      @Valid @RequestBody ActorCreateRequestDto actorCreateRequestDto) throws BadRequestException {

    ActorResponseDto actor = actorService.createActor(actorCreateRequestDto);
    return new BaseResponse<>(actor, "Actor created successfully", true, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @RolesAllowed("ADMIN")
  public BaseResponse<ActorResponseDto> updateActor(@PathVariable String id,
      @Valid @RequestBody ActorUpdateRequestDto actorUpdateRequestDto) throws BadRequestException {
    ActorResponseDto actor = actorService.updateActor(id, actorUpdateRequestDto);
    return new BaseResponse<>(actor, "Actor updated successfully", true, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ADMIN")
  public BaseResponse<Boolean> deleteActor(@PathVariable String id) throws BadRequestException {
    Boolean isDeleted = actorService.deleteActor(id);
    return new BaseResponse<>(isDeleted, "Actor deleted successfully", true, HttpStatus.OK);
  }
}
