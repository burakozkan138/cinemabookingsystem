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

import com.burakozkan138.cinemabookingsystem.dto.Request.MovieCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.MovieUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponse;
import com.burakozkan138.cinemabookingsystem.dto.Response.MovieResponseDto;
import com.burakozkan138.cinemabookingsystem.service.MovieService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
  private final MovieService movieService;

  @GetMapping // get all movies
  public BaseResponse<List<MovieResponseDto>> getAllMovies() {
    List<MovieResponseDto> movies = movieService.getAllMovies();
    return new BaseResponse<>(movies, "Movies fetched successfully", true, HttpStatus.OK);
  }

  @GetMapping("/{id}") // get movie by id
  public BaseResponse<MovieResponseDto> getMovieById(@PathVariable String id)
      throws BadRequestException {
    MovieResponseDto movie = movieService.getMovieById(id);
    return new BaseResponse<MovieResponseDto>(movie, "Movie fetched successfully", true, HttpStatus.OK);
  }

  @PostMapping // add movie
  @RolesAllowed("ADMIN")
  public BaseResponse<MovieResponseDto> addMovie(
      @Valid @RequestBody MovieCreateRequestDto movieCreateRequestDto)
      throws BadRequestException {

    MovieResponseDto movie = movieService.createMovie(movieCreateRequestDto);
    return new BaseResponse<>(movie, "Movie created successfully", true, HttpStatus.CREATED);
  }

  @PutMapping("/{id}") // update movie
  @RolesAllowed("ADMIN")
  public BaseResponse<MovieResponseDto> updateMovie(@PathVariable String id,
      @Valid @RequestBody MovieUpdateRequestDto movieUpdateRequestDto)
      throws BadRequestException {
    MovieResponseDto movie = movieService.updateMovie(id, movieUpdateRequestDto);
    return new BaseResponse<>(movie, "Movie updated successfully", true, HttpStatus.OK);
  }

  @DeleteMapping("/{id}") // delete movie
  @RolesAllowed("ADMIN")
  public BaseResponse<Boolean> deleteMovie(@PathVariable String id) throws BadRequestException {
    Boolean data = movieService.deleteMovie(id);
    return new BaseResponse<>(data, "Movie deleted successfully", true, HttpStatus.OK);
  }
}
