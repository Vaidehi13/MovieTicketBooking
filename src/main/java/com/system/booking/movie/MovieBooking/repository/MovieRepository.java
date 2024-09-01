package com.system.booking.movie.MovieBooking.repository;

import com.system.booking.movie.MovieBooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
