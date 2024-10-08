package com.system.booking.movie.MovieBooking.repository;

import com.system.booking.movie.MovieBooking.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
}
