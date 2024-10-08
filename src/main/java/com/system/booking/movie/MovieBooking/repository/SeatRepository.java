package com.system.booking.movie.MovieBooking.repository;

import com.system.booking.movie.MovieBooking.entity.BookTicket;
import com.system.booking.movie.MovieBooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    @Query("SELECT s FROM Seat s WHERE s.screen.screen_id = :screen_id")
    List<Seat> findByScreen_ScreenId(int screen_id);
    @Query("SELECT s FROM Seat s WHERE s.bookTicket.booking_id = :booking_id")
    List<Seat> findByBookingId(int booking_id);
}

