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
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponseDto;
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
  public BaseResponseDto<List<ActorResponseDto>> getAllActors() {
    List<ActorResponseDto> actors = actorService.getAllActors();
    return new BaseResponseDto<List<ActorResponseDto>>(actors, "Actors fetched successfully", true, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public BaseResponseDto<ActorResponseDto> getActorById(@PathVariable String id)
      throws BadRequestException {
    ActorResponseDto actor = actorService.getActorById(id);
    return new BaseResponseDto<ActorResponseDto>(actor, "Actor fetched successfully", true, HttpStatus.OK);
  }

  @PostMapping
  @RolesAllowed("ADMIN")
  public BaseResponseDto<ActorResponseDto> createActor(
      @Valid @RequestBody ActorCreateRequestDto actorCreateRequestDto) throws BadRequestException {

    ActorResponseDto actor = actorService.createActor(actorCreateRequestDto);
    return new BaseResponseDto<ActorResponseDto>(actor, "Actor created successfully", true, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @RolesAllowed("ADMIN")
  public BaseResponseDto<ActorResponseDto> updateActor(@PathVariable String id,
      @Valid @RequestBody ActorUpdateRequestDto actorUpdateRequestDto) throws BadRequestException {
    ActorResponseDto actor = actorService.updateActor(id, actorUpdateRequestDto);
    return new BaseResponseDto<ActorResponseDto>(actor, "Actor updated successfully", true, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ADMIN")
  public BaseResponseDto<Boolean> deleteActor(@PathVariable String id) throws BadRequestException {
    Boolean isDeleted = actorService.deleteActor(id);
    return new BaseResponseDto<>(isDeleted, "Actor deleted successfully", true, HttpStatus.OK);
  }
}
