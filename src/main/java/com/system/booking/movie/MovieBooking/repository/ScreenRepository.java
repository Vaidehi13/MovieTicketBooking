package com.system.booking.movie.MovieBooking.repository;

import com.system.booking.movie.MovieBooking.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {
}
