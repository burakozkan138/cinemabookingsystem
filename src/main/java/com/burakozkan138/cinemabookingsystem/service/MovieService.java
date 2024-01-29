package com.burakozkan138.cinemabookingsystem.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.burakozkan138.cinemabookingsystem.dto.Request.MovieCreateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.MovieUpdateRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.MovieResponseDto;
import com.burakozkan138.cinemabookingsystem.model.Movie;
import com.burakozkan138.cinemabookingsystem.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;
  private final ModelMapper modelMapper;

  public List<MovieResponseDto> getAllMovies() {
    List<Movie> movies = movieRepository.findAll();

    return movies.stream().map(movie -> modelMapper.map(movie, MovieResponseDto.class)).toList();
  }

  public MovieResponseDto getMovieById(String id) throws BadRequestException {
    if (id == null)
      throw new BadRequestException("Movie id can not be null");

    Movie movie = movieRepository.findById(id).orElseThrow(() -> new BadRequestException("Movie not found."));

    return modelMapper.map(movie, MovieResponseDto.class);
  }

  public MovieResponseDto createMovie(MovieCreateRequestDto createMovieDto) throws BadRequestException {
    Movie mappedMovie = modelMapper.map(createMovieDto, Movie.class);
    if (mappedMovie == null) { // maybe this is not necessary
      throw new BadRequestException("Movie can not be null");
    }

    Movie createdMovie = movieRepository.save(mappedMovie);
    return modelMapper.map(createdMovie, MovieResponseDto.class);
  }

  public MovieResponseDto updateMovie(String id, MovieUpdateRequestDto movieUpdateRequestDto)
      throws BadRequestException {
    Movie movie = movieRepository.findById(id).orElseThrow(() -> new BadRequestException("Movie not found."));

    modelMapper.map(movieUpdateRequestDto, movie);

    Movie updatedMovie = movieRepository.save(movie);
    return modelMapper.map(updatedMovie, MovieResponseDto.class);
  }

  public Boolean deleteMovie(String id) throws BadRequestException {
    Movie movie = movieRepository.findById(id).orElseThrow(() -> new BadRequestException("Movie not found."));
    movieRepository.delete(movie);
    return true;
  }
}
