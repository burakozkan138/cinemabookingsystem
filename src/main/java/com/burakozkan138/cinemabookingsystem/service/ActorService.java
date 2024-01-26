package com.burakozkan138.cinemabookingsystem.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burakozkan138.cinemabookingsystem.dto.Request.ActorCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.ActorUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.ActorResponseDto;
import com.burakozkan138.cinemabookingsystem.model.Actor;
import com.burakozkan138.cinemabookingsystem.repository.ActorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActorService {
  private final ActorRepository actorRepository;
  private final ModelMapper modelMapper;

  public List<ActorResponseDto> getAllActors() {
    List<Actor> actors = actorRepository.findAll();
    return actors.stream().map(actor -> modelMapper.map(actor, ActorResponseDto.class)).toList();
  }

  public ActorResponseDto getActorById(String id) throws BadRequestException {
    if (id == null) {
      throw new BadRequestException("Actor id could not be null.");
    }

    Actor actor = actorRepository.findById(id).orElseThrow(() -> new BadRequestException("Actor not found."));

    return modelMapper.map(actor, ActorResponseDto.class);
  }

  public ActorResponseDto createActor(ActorCreateRequestDto createActorRequestDto) throws BadRequestException {
    Actor mappedActor = modelMapper.map(createActorRequestDto, Actor.class);
    if (mappedActor == null) {
      throw new BadRequestException("Actor could not be null.");
    }

    Actor createdActor = actorRepository.save(mappedActor);
    return modelMapper.map(createdActor, ActorResponseDto.class);
  }

  public ActorResponseDto updateActor(String id, ActorUpdateRequestDto actorUpdateRequestDto)
      throws BadRequestException {
    if (id == null) {
      throw new BadRequestException("Actor id could not be null.");
    }

    Actor actor = actorRepository.findById(id).orElseThrow(() -> new BadRequestException("Actor not found."));
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.map(actorUpdateRequestDto, actor);

    actorRepository.save(actor);
    return modelMapper.map(actor, ActorResponseDto.class);
  }

  public Boolean deleteActor(String id) throws BadRequestException {
    if (id == null) {
      throw new BadRequestException("Actor id could not be null.");
    }

    Actor actor = actorRepository.findById(id).orElseThrow(() -> new BadRequestException("Actor not found."));
    actorRepository.delete(actor);
    return true;
  }

}
