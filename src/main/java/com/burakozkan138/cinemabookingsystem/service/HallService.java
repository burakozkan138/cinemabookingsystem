package com.burakozkan138.cinemabookingsystem.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burakozkan138.cinemabookingsystem.dto.Request.HallCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.HallUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.HallResponseDto;
import com.burakozkan138.cinemabookingsystem.model.Hall;
import com.burakozkan138.cinemabookingsystem.repository.HallRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HallService {

  private final HallRepository hallRepository;
  private final ModelMapper modelMapper;

  public HallResponseDto createHall(HallCreateRequestDto createHallRequestDto) {
    Hall mappedHall = modelMapper.map(createHallRequestDto, Hall.class);
    if (mappedHall == null) {
      throw new RuntimeException("Hall could not be null.");
    }
    Hall createdHall = hallRepository.save(mappedHall);

    return modelMapper.map(createdHall, HallResponseDto.class);
  }

  public List<HallResponseDto> getAllHalls() {
    List<Hall> halls = hallRepository.findAll();
    return halls.stream().map(hall -> modelMapper.map(hall, HallResponseDto.class)).toList();
  }

  public HallResponseDto getHallById(String id) throws BadRequestException {
    if (id == null) {
      throw new BadRequestException("Hall id could not be null.");
    }

    Hall hall = hallRepository.findById(id).orElseThrow(() -> new BadRequestException("Hall not found."));
    return modelMapper.map(hall, HallResponseDto.class);
  }

  public HallResponseDto updateHall(String id, HallUpdateRequestDto hallUpdateRequestDto) {
    Hall hall = hallRepository.findById(id).orElseThrow(() -> new RuntimeException("Hall not found."));
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.map(hallUpdateRequestDto, hall);

    Hall updatedHall = hallRepository.save(hall);
    return modelMapper.map(updatedHall, HallResponseDto.class);
  }

  public Boolean deleteHall(String id) {
    Hall hall = hallRepository.findById(id).orElseThrow(() -> new RuntimeException("Hall not found."));
    hallRepository.delete(hall);
    return true;
  }
}
