package com.system.booking.movie.MovieBooking.service;

import com.system.booking.movie.MovieBooking.entity.Movie;
import com.system.booking.movie.MovieBooking.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}
